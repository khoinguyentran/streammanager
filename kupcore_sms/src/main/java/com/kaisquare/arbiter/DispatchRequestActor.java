package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.dsl.Inbox;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.kaisquare.arbiter.message.*;

import java.io.Serializable;
import java.util.Date;

public class DispatchRequestActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	@Override
	public void onReceive(Object message) {
		if (message instanceof AddStream) {
			processAddStream((AddStream)message);
		} else if (message instanceof AddPlaylist) {
			processAddStream((AddStream)message);
		} else if (message instanceof RemoveStream) {
			processRemoveStream((RemoveStream)message);
		} else if (message instanceof RemoveStreamsFromServer) {
			processRemoveStreamsFromServer((RemoveStreamsFromServer)message);
		} else if (message instanceof GetStreamClients) {
			processGetStreamClients((GetStreamClients)message);
		} else if (message instanceof GetServerPoolInfo) {
			processGetServerPoolInfo((GetServerPoolInfo)message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void preStart() {
		log.info("starting");
	}
	@Override
	public void postStop() {
		log.info("stopped");
	}

	void processAddStream(AddStream r) {
		log.debug("dispatch AddStream");

		ActorRef handler = getContext()
			.actorOf(Props.create(HandleAddStreamActor.class),
				String.format("addstream.%d.%d.%d",
					r.streamInfo.getDeviceId(),
					r.streamInfo.getChannelId(),
					System.currentTimeMillis()));
		handler.forward(r, getContext());
	}
	void processRemoveStream(RemoveStream r) {
		log.debug("dispatch RemoveStream");

		ActorRef handler = getContext()
			.actorOf(Props.create(HandleRemoveStreamActor.class),
				String.format("removestream.%d.%d.%s.%d",
					r.streamInfo.getDeviceId(),
					r.streamInfo.getChannelId(),
					r.streamInfo.getStreamName(),
					System.currentTimeMillis()));
		handler.forward(r, getContext());
	}
	void processRemoveStreamsFromServer(RemoveStreamsFromServer r) {
		log.debug("dispatch RemoveStreamsFromServer");

		ActorRef handler = getContext()
			.actorOf(Props.create(HandleRemoveStreamActor.class),
				String.format("removestreamsfromserver.%d.%d",
					r.serverId,
					System.currentTimeMillis()));
		handler.forward(r, getContext());
	}
	void processGetStreamClients(GetStreamClients r) {
		log.debug("dispatch GetStreamClients");

		ActorRef handler = getContext()
			.actorOf(Props.create(HandleGetStreamClientsActor.class),
				String.format("getclients.%d.%d.%d",
					r.streamInfo.getDeviceId(),
					r.streamInfo.getChannelId(),
					System.currentTimeMillis()));
		handler.forward(r, getContext());
	}
	void processGetServerPoolInfo(GetServerPoolInfo r) {
		log.debug("dispatch GetServerPoolInfo");

		ActorRef handler = getContext()
			.actorOf(Props.create(HandleGetServerPoolInfoActor.class),
				String.format("getserverpoolinfo.%d", System.currentTimeMillis()));
		handler.forward(r, getContext());
	}
}
