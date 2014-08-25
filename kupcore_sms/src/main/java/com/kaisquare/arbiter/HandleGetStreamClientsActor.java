package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.kaisquare.arbiter.dao.Stream;
import com.kaisquare.arbiter.dao.StreamDaoMyBatis;
import com.kaisquare.arbiter.message.GetStreamClients;
import com.kaisquare.arbiter.message.GetStreamClientsResult;
import scala.concurrent.duration.Duration;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandleGetStreamClientsActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	ActorRef requestor;
	Stream streamInfo;

	@Override
	public void onReceive(Object message) {
		if (message instanceof GetStreamClients) {
			processGetStreamClients((GetStreamClients) message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void postStop() {
		log.debug("stopped");
	}

	void processGetStreamClients(GetStreamClients r) {
		// Save the original requestor.
		requestor = getSender();
		streamInfo = r.streamInfo;

		// Make up the name in case of empty stream name.
		if (streamInfo.getStreamName() == null || streamInfo.getStreamName().isEmpty())
			streamInfo.setStreamName(String.format("%d-%d",
				streamInfo.getDeviceId(), streamInfo.getChannelId()));

		// Get the id of the hosting server.
		StreamDaoMyBatis streamDao = new StreamDaoMyBatis();
		List<Stream> streams = streamDao.getStreamsByNameByOutputType(
			streamInfo.getStreamName(), streamInfo.getOutputType());

		if (!streams.isEmpty()) {
			GetStreamClients fr = new GetStreamClients();
			fr.streamInfo = streams.get(0);

			StreamManager.getManageStreamServerActor().forward(fr, getContext());
		} else {
			log.debug("{}.{}.{}.{} not found",
				streamInfo.getDeviceId(), streamInfo.getChannelId(),
				streamInfo.getStreamName(), streamInfo.getOutputType());

			GetStreamClientsResult result = new GetStreamClientsResult();
			requestor.tell(result, getSelf());
		}

		getContext().stop(getSelf());
	}
}
