package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.dsl.Inbox;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.kaisquare.arbiter.dao.Stream;
import com.kaisquare.arbiter.dao.StreamServer;
import com.kaisquare.arbiter.dao.StreamServerDaoMyBatis;
import com.kaisquare.arbiter.message.*;
import scala.concurrent.duration.Duration;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ManageStreamServerActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	Map<Long, ActorRef> servers = new HashMap<>();
	Queue<ActorRef> serverRound = new ArrayDeque<>();

	// Timer for refreshing server list.
	Cancellable refresh;

	@Override
	public void onReceive(Object message) {
		if (message.equals("refresh")) {
			processRefresh();
		} else if (message instanceof AddStream) {
			processAddStream((AddStream)message);
		} else if (message instanceof GetStreamClients) {
			processGetStreamClients((GetStreamClients)message);
		} else if (message instanceof RemoveStream) {
			processRemoveStream((RemoveStream)message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void preStart() {
		log.info("starting");

		long interval = StreamManager.getConf()
			.getConfig("sms").getConfig("servermanagement").getLong("refresh-interval");
		long delay =  StreamManager.getConf()
			.getConfig("sms").getConfig("servermanagement").getLong("initial-delay");

		// Schedule timer for refreshing server list.
		refresh = getContext().system().scheduler().schedule(
			Duration.create(delay, TimeUnit.MILLISECONDS),
			Duration.create(interval, TimeUnit.MILLISECONDS),
			getSelf(), "refresh", getContext().dispatcher(), null);
	}
	@Override
	public void postStop() {
		refresh.cancel();
		log.info("stopped");
	}

	void processRefresh() {
		log.info("refresh server list");

		Map<Long, ActorRef> newServers = new HashMap<>();

		StreamServerDaoMyBatis dao = new StreamServerDaoMyBatis();
		List<StreamServer> svlist = dao.getAllStreamServers();
		if (svlist.isEmpty()) {
			log.info("no stream server in database");
			return;
		}

		// Move all valid servers to new list and update them.
		for (StreamServer s : svlist) {
			long serverId = s.getId();
			if (servers.containsKey(s.getId())) {
				newServers.put(serverId, servers.get(serverId));
				servers.remove(serverId);
			} else {
				ActorRef newServer = getContext()
					.actorOf(Props.create(StreamServerActor.class), "server" + serverId);
				newServers.put(serverId, newServer);
			}

			SetupStreamServer r = new SetupStreamServer();
			r.serverId = s.getId();
			r.type = s.getType();
			r.ip = s.getIp();
			r.ports = s.getPorts();

			newServers.get(serverId).tell(r,  getSelf());
		}

		// Stop unused servers.
		for (ActorRef s : servers.values()) {
			s.tell(new DestroyActor(), getSelf());
		}

		// Update the list.
		servers = newServers;

		// Reset the server round.
		serverRound.clear();
		for (ActorRef s : newServers.values()) {
			serverRound.add(s);
		}
	}
	void processAddStream(AddStream r) {
		// Pick the server at the front of the queue.
		ActorRef sv = serverRound.poll();
		if (sv == null) {
			log.error("no server to handle AddStream");

			StreamAddFailed error = new StreamAddFailed();
			error.streamInfo = r.streamInfo;

			getSender().tell(error, getSelf());
			return;
		}

		// Send the command to it.
		sv.forward(r, getContext());

		// Place it at the back of the queue.
		serverRound.add(sv);
	}
	void processGetStreamClients(GetStreamClients r) {
		long serverId = r.streamInfo.getServerId();

		if (servers.containsKey(serverId)) {
			servers.get(serverId).forward(r, getContext());
		} else {
			GetStreamClientsResult result = new GetStreamClientsResult();
			getSender().tell(result, getSelf());
		}
	}
	void processRemoveStream(RemoveStream r) {
		long serverId = r.streamInfo.getServerId();
		Stream s = r.streamInfo;

		if (servers.containsKey(serverId)) {
			servers.get(serverId).forward(r, getContext());
		} else {
			log.warning("{}.{}.{}.{} hosting server is not available",
				s.getDeviceId(), s.getChannelId(), s.getStreamName(), s.getOutputType());
		}
	}
}
