package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.kaisquare.arbiter.dao.Stream;
import com.kaisquare.arbiter.dao.StreamDaoMyBatis;
import com.kaisquare.arbiter.message.RemoveStream;
import com.kaisquare.arbiter.message.RemoveStreamsFromServer;
import com.kaisquare.arbiter.message.StreamRemoveFailed;
import com.kaisquare.arbiter.message.StreamRemoved;

import java.util.List;

public class HandleRemoveStreamActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(getContext().system().eventStream(), this.getClass());

	ActorRef requestor;
	Stream streamInfo;

	@Override
	public void onReceive(Object message) {
		if (message instanceof RemoveStream) {
			processRemoveStream((RemoveStream)message);
		} else if (message instanceof RemoveStreamsFromServer) {
			processRemoveStreamsFromServer((RemoveStreamsFromServer)message);
		} else {
			unhandled(message);
		}
	}
	@Override
	public void preStart() {
	}
	@Override
	public void postStop() {
		log.debug("stopped");
	}

	void processRemoveStream(RemoveStream r) {
		requestor = getSender();
		streamInfo = r.streamInfo;

		// Make up the name in case of empty stream name.
		if (streamInfo.getStreamName() == null || streamInfo.getStreamName().isEmpty())
			streamInfo.setStreamName(String.format("%d-%d",
				streamInfo.getDeviceId(), streamInfo.getChannelId()));

		StreamDaoMyBatis dao = new StreamDaoMyBatis();

		// Find all the servers hosting the streams and send remove command to them.
		List<Stream> streams = dao.getStreamsByNameByOutputType(
			streamInfo.getStreamName(), streamInfo.getOutputType());
		for (Stream s : streams) {
			RemoveStream m = new RemoveStream();
			m.streamInfo = s;
			StreamManager.getManageStreamServerActor().tell(m, getSelf());
		}

		if (!dao.deleteStream(streamInfo.getDeviceId(), streamInfo.getChannelId(),
			streamInfo.getStreamName(), streamInfo.getOutputType())) {

			log.error("{}.{}.{}.{} failed to remove stream",
				streamInfo.getDeviceId(), streamInfo.getChannelId(),
				streamInfo.getStreamName(), streamInfo.getOutputType());

			StreamRemoveFailed error = new StreamRemoveFailed();
			error.streamInfo = streamInfo;
			requestor.tell(error, getSelf());
		} else {
			log.info("{}.{}.{}.{} removed",
				streamInfo.getDeviceId(), streamInfo.getChannelId(),
				streamInfo.getStreamName(), streamInfo.getOutputType());

			StreamRemoved ok = new StreamRemoved();
			ok.streamInfo = streamInfo;
			requestor.tell(ok, getSelf());
		}

		getContext().stop(getSelf());
	}
	void processRemoveStreamsFromServer(RemoveStreamsFromServer r) {
		requestor = getSender();
		long serverId = r.serverId;

		log.info("Removing streams from streamserver/{}", serverId);
		StreamDaoMyBatis dao = new StreamDaoMyBatis();
		dao.deleteStreamsByServerId(serverId);

		getContext().stop(getSelf());
	}
}
