package com.kaisquare.arbiter;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Pair;
import com.kaisquare.arbiter.dao.Port;
import com.kaisquare.arbiter.dao.PortDaoMyBatis;
import com.kaisquare.arbiter.dao.StreamServer;
import com.kaisquare.arbiter.dao.StreamServerDaoMyBatis;
import com.kaisquare.arbiter.message.*;

import java.util.Date;

public class HandleServerRegisterActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	@Override
	public void onReceive(Object message) {
		if (message instanceof RegisterStreamServer) {
			processRegisterStreamServer((RegisterStreamServer)message);
		} else if (message instanceof DeregisterStreamServer) {
			processDeregisterStreamServer((DeregisterStreamServer)message);
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

		StreamServerRegistered ok = new StreamServerRegistered();
		StreamServerRegisterFailed error = new StreamServerRegisterFailed();

		StreamServerDaoMyBatis sdao = new StreamServerDaoMyBatis();
		PortDaoMyBatis pdao = new PortDaoMyBatis();

		if (!pdao.deletePortsByServerId(r.serverId)) {
			log.error("{}/{} failed to remove old server ports");
			getSender().tell(error, getSelf());
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
				getSender().tell(error, getSelf());
				return;
			} else {
				log.info("{}/{} registered", r.type, r.serverId);
				getSender().tell(ok, getSelf());
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
				getSender().tell(error, getSelf());
				return;
			}

			for (Pair p : r.ports) {
				Port port = new Port();
				port.setServerId(r.serverId);
				port.setPort((int)p.first());
				port.setProtocol((String)p.second());

				if (!pdao.insertPort(port)) {
					log.error("{}/{} failed to insert port {}", r.type, r.serverId, port.toString());
					getSender().tell(error, getSelf());
					return;
				}
			}

			log.info("{}/{} updated", r.type, r.serverId);
			getSender().tell(ok, getSelf());
			return;
		}
	}
	void processDeregisterStreamServer(DeregisterStreamServer r) {
		log.debug("streamserver/{} deregisters", r.serverId);

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
			getSender().tell(ok, getSelf());
			return;
		} else {
			log.info("streamserver/{} deregistered", serverId);
			getSender().tell(error, getSelf());
			return;
		}
	}
}
