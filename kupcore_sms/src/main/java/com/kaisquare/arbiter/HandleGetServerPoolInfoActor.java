package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.kaisquare.arbiter.dao.StreamServer;
import com.kaisquare.arbiter.dao.StreamServerDaoMyBatis;
import com.kaisquare.arbiter.message.GetServerPoolInfo;
import com.kaisquare.arbiter.message.GetServerPoolInfoResult;
import com.kaisquare.arbiter.message.StreamAdded;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandleGetServerPoolInfoActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	ActorRef requestor;

	@Override
	public void onReceive(Object message) {
		if (message instanceof GetServerPoolInfo) {
			processGetServerPoolInfo((GetServerPoolInfo) message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void postStop() {
		log.debug("stopped");
	}

	void processGetServerPoolInfo(GetServerPoolInfo r) {
		requestor = getSender();

		StreamServerDaoMyBatis dao = new StreamServerDaoMyBatis();
		List<StreamServer> servers;

		servers = dao.getAllStreamServers();

		GetServerPoolInfoResult result = new GetServerPoolInfoResult();
		result.servers = servers;

		requestor.tell(result, getSelf());

		getContext().stop(getSelf());
	}
}
