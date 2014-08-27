package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Pair;
import com.kaisquare.arbiter.dao.Port;
import com.kaisquare.arbiter.dao.PortDaoMyBatis;
import com.kaisquare.arbiter.dao.StreamServer;
import com.kaisquare.arbiter.dao.StreamServerDaoMyBatis;
import com.kaisquare.arbiter.message.*;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public class HandleServerRegisterActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	ActorRef requestor;

	@Override
	public void onReceive(Object message) {
		if (message instanceof RegisterStreamServer) {
			processRegisterStreamServer((RegisterStreamServer)message);
		} else if (message instanceof DeregisterStreamServer) {
			processDeregisterStreamServer((DeregisterStreamServer)message);
		} else if (message instanceof UpdateStreamServerStatus) {
			processUpdateStreamServerStatus((UpdateStreamServerStatus)message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void preStart() {
		log.info("starting");
	}

	void processRegisterStreamServer(RegisterStreamServer r) {
		log.debug("{}/{} registers", r.type, r.serverId);

		requestor = getSender();

		StreamServerRegistered ok = new StreamServerRegistered();
		StreamServerRegisterFailed error = new StreamServerRegisterFailed();

		StreamServerDaoMyBatis sdao = new StreamServerDaoMyBatis();
		PortDaoMyBatis pdao = new PortDaoMyBatis();

		if (!pdao.deletePortsByServerId(r.serverId)) {
			log.error("{}/{} failed to remove old server ports");
			requestor.tell(error, getSelf());
			return;
		}

		StreamServer old = sdao.getStreamServerById(r.serverId);
		if (old == null) {
			StreamServer toInsert = new StreamServer();
			toInsert.setId(r.serverId);
			toInsert.setType(r.type);
			toInsert.setIp(r.ip);
			toInsert.setLastUpdated(new Date());

			if (!sdao.insertStreamServer(toInsert)) {
				log.error("{}/{} failed to register server", r.type, r.serverId);
				requestor.tell(error, getSelf());
				return;
			} else {
				for (Pair p : r.ports) {
					Port port = new Port();
					port.setServerId(r.serverId);
					port.setPort((int)p.first());
					port.setProtocol((String)p.second());

					if (!pdao.insertPort(port)) {
						log.error("{}/{} failed to insert port {}", r.type, r.serverId, port.toString());
						requestor.tell(error, getSelf());
						return;
					}
				}

				log.info("{}/{} registered", r.type, r.serverId);
				requestor.tell(ok, getSelf());
				return;
			}
		} else {
			StreamServer toUpdate = new StreamServer();
			toUpdate.setId(r.serverId);
			toUpdate.setType(r.type);
			toUpdate.setIp(r.ip);
			toUpdate.setLastUpdated(new Date());

			if (!sdao.updateStreamServer(toUpdate)) {
				log.error("{}/{} failed to update server", r.type, r.serverId);
				requestor.tell(error, getSelf());
				return;
			}

			for (Pair p : r.ports) {
				Port port = new Port();
				port.setServerId(r.serverId);
				port.setPort((int)p.first());
				port.setProtocol((String)p.second());

				if (!pdao.insertPort(port)) {
					log.error("{}/{} failed to insert port {}", r.type, r.serverId, port.toString());
					requestor.tell(error, getSelf());
					return;
				}
			}

			log.info("{}/{} updated", r.type, r.serverId);
			requestor.tell(ok, getSelf());
			return;
		}
	}
	void processDeregisterStreamServer(DeregisterStreamServer r) {
		log.debug("streamserver/{} deregisters", r.serverId);

		requestor = getSender();
		StreamServerDeregistered ok = new StreamServerDeregistered();
		StreamServerDeregisterFailed error = new StreamServerDeregisterFailed();

		StreamServerDaoMyBatis sdao = new StreamServerDaoMyBatis();
		PortDaoMyBatis pdao = new PortDaoMyBatis();

		long serverId = r.serverId;

		if (!pdao.deletePortsByServerId(serverId)) {
			log.error("streamserver/{} failed to remove ports", serverId);
		}

		if (!sdao.deleteStreamServer(serverId)) {
			log.error("streamserver/{} failed to deregister", serverId);
			requestor.tell(ok, getSelf());
			return;
		} else {
			log.info("streamserver/{} deregistered", serverId);
			requestor.tell(error, getSelf());
			return;
		}
	}
	void processUpdateStreamServerStatus(UpdateStreamServerStatus r) {
		log.debug("streamserver/{} pings", r.serverId);

		long serverId = r.serverId;
		requestor = getSender();
		StreamServerStatusUpdated ok = new StreamServerStatusUpdated();
		StreamServerStatusUpdateFailed error = new StreamServerStatusUpdateFailed();

		StreamServerDaoMyBatis sdao = new StreamServerDaoMyBatis();

		StreamServer server = sdao.getStreamServerById(serverId);
		if (server == null) {
			log.error("streamserver/{} does not exist", serverId);
			requestor.tell(error, getSelf());
			return;
		}

		server.setLastUpdated(new Date());
		if (!sdao.updateStreamServer(server)) {
			log.error("streamserver/{} status could not be updated");
			requestor.tell(error, getSelf());
			return;
		}

		requestor.tell(ok, getSelf());
	}
}
