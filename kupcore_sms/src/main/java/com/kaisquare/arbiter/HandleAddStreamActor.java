package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.kaisquare.arbiter.dao.Stream;
import com.kaisquare.arbiter.dao.StreamDaoMyBatis;
import com.kaisquare.arbiter.message.AddStream;
import com.kaisquare.arbiter.message.StreamAddFailed;
import com.kaisquare.arbiter.message.StreamAdded;
import scala.concurrent.duration.Duration;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HandleAddStreamActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	ActorRef requestor;
	Stream streamInfo;
	// Timer for aborting.
	Cancellable abort;

	@Override
	public void onReceive(Object message) {
		if (message.equals("abort")) {
			processAbort();
		} else if (message instanceof AddStream) {
			processAddStream((AddStream) message);
		} else if (message instanceof StreamAdded) {
			processStreamAdded((StreamAdded)message);
		} else if (message instanceof StreamAddFailed) {
			processStreamAddFailed((StreamAddFailed) message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void preStart() {
		long interval = 0;
		long delay = StreamManager.getConf()
			.getConfig("sms").getConfig("streammanagement").getLong("addstream-timeout");

		// Schedule timer for giving up.
		abort = getContext().system().scheduler().schedule(
			Duration.create(delay, TimeUnit.MILLISECONDS),
			Duration.create(interval, TimeUnit.MILLISECONDS),
			getSelf(), "abort", getContext().dispatcher(), null);
	}
	@Override
	public void postStop() {
		log.debug("stopped");
	}

	void processAddStream(AddStream r) {
		// Save original requestor.
		requestor = getSender();
		streamInfo = r.streamInfo;

		// Make up name for empty streamName.
		if (streamInfo.getStreamName() == null || streamInfo.getStreamName().isEmpty())
			streamInfo.setStreamName(String.format("%d-%d",
				streamInfo.getDeviceId(), streamInfo.getChannelId()));

		// If the stream already exists, return it.
		StreamDaoMyBatis dao = new StreamDaoMyBatis();

		List<Stream> streams = dao.getStreamsByNameByOutputType(
			streamInfo.getStreamName(), streamInfo.getOutputType());
		if (!streams.isEmpty()) {
			log.debug("{}/{}/{}/{} exists, simply return it",
				streamInfo.getDeviceId(), streamInfo.getChannelId(),
				streamInfo.getStreamName(), streamInfo.getOutputType());

			StreamAdded ok = new StreamAdded();
			ok.streamInfo = streams.get(0);
			requestor.tell(ok, getSelf());

			abort.cancel();

			getContext().stop(getSelf());
		} else {
			// Send command to streaming server.
			AddStream m = new AddStream();
			m.streamInfo = streamInfo;

			StreamManager.getManageStreamServerActor().tell(m, getSelf());
		}
	}
	void processStreamAdded(StreamAdded r) {
		// Update database.
		StreamDaoMyBatis dao = new StreamDaoMyBatis();
		Stream s = r.streamInfo;

		s.setLastUpdated(new Date());

		if (!dao.insertStream(s)) {
			log.error("{}/{}/{}/{} failed to update database",
				s.getDeviceId(), s.getChannelId(), s.getStreamName(), s.getOutputType());

			StreamAddFailed error = new StreamAddFailed();
			error.streamInfo = s;

			requestor.tell(error, getSelf());
		} else {
			log.info("{}/{}/{}/{} added",
				s.getDeviceId(), s.getChannelId(), s.getStreamName(), s.getOutputType());

			// Notify requestor.
			requestor.tell(r, getSelf());
		}

		abort.cancel();

		getContext().stop(getSelf());
	}
	void processStreamAddFailed(StreamAddFailed r) {
		Stream s = r.streamInfo;

		log.error("{}/{}/{}/{} failed to add stream",
			s.getDeviceId(), s.getChannelId(), s.getStreamName(), s.getOutputType());

		// Retry until timeout.
		AddStream m = new AddStream();
		m.streamInfo = streamInfo;

		StreamManager.getManageStreamServerActor().tell(m, getSelf());
	}
	void processAbort() {
		getContext().stop(getSelf());
	}
}
