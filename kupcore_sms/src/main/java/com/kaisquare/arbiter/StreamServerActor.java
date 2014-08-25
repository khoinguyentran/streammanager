package com.kaisquare.arbiter;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Pair;
import com.kaisquare.arbiter.dao.Port;
import com.kaisquare.arbiter.dao.Stream;
import com.kaisquare.arbiter.message.*;
import com.kaisquare.stream.thrift.SmStreamClientInfo;
import com.kaisquare.stream.thrift.SmStreamInfo;
import com.kaisquare.stream.thrift.StreamServerService;
import com.kaisquare.util.ThriftUtil;

import java.util.List;

public class StreamServerActor extends UntypedActor {
	LoggingAdapter log = Logging.getLogger(
		getContext().system().eventStream(), this.getClass());

	long serverId;
	String type;
	String ip;
	// Port{port, protocol}
	List<Port> ports;

	@Override
	public void preStart() {
		log.info("starting");
	}
	@Override
	public void postStop() {
		log.info("stopped");
	}
	@Override
	public void onReceive(Object message) {
		if (message instanceof SetupStreamServer) {
			processSetupStreamServer((SetupStreamServer)message);
		} else if (message instanceof DestroyActor) {
			processDestroyActor((DestroyActor)message);
		} else if (message instanceof AddStream) {
			processAddStream((AddStream)message);
		} else if (message instanceof GetStreamClients) {
			processGetStreamClients((GetStreamClients) message);
		} else if (message instanceof RemoveStream) {
			processRemoveStream((RemoveStream)message);
		} else {
			unhandled(message);
		}
	}

	String getDesc() {
		return String.format("%s/%d:%d", type, serverId, getThiftPort());
	}
	int getThiftPort() {
		int port = -1;

		for (Port p : ports) {
			if (p.getProtocol().equals("thrift")) {
				port = p.getPort();
				break;
			}
		}

		return port;
	}
	void processSetupStreamServer(SetupStreamServer r) {
		this.serverId = r.serverId;
		this.type = r.type;
		this.ip = r.ip;
		this.ports = r.ports;
	}
	void processDestroyActor(DestroyActor r) {
		getContext().stop(getSelf());
	}
	void processAddStream(AddStream r) {
		Stream s = r.streamInfo;
		Message resp = null;

		SmStreamInfo info =  new SmStreamInfo();
		info.setDeviceId(s.getDeviceId());
		info.setChannelId(s.getChannelId());
		info.setSource(s.getSource());
		info.setSourceType(s.getSourceType());
		info.setOutputType(s.getOutputType());
		info.setStreamName(s.getStreamName());

		int thriftPort = getThiftPort();

		log.debug(String.format("%s addstream %d/%d/%s/%s->%s", getDesc(),
			s.getDeviceId(), s.getChannelId(),
			s.getStreamName(), s.getSourceType(), s.getOutputType()));

		if (type.equals("crtmpserver")) {
			try {
				ThriftUtil.Client<StreamServerService.Iface> client =
					ThriftUtil.newServiceClient(
						StreamServerService.Iface.class,
						StreamServerService.Client.class,
						ip,
						thriftPort,
						2000);

				String url = client.getIface().addStream(info);

				if (!url.isEmpty()) {
					StreamAdded ok = new StreamAdded();
					ok.streamInfo = r.streamInfo;
					ok.streamInfo.setOutputUrl(url);
					ok.streamInfo.setServerId(serverId);
					resp = ok;
				} else {
					StreamAddFailed error = new StreamAddFailed();
					error.streamInfo = r.streamInfo;
					error.streamInfo.setOutputUrl("");
					resp = error;
				}
			} catch (Exception e) {
				e.printStackTrace();
				StreamAddFailed error = new StreamAddFailed();
				error = new StreamAddFailed();
				error.streamInfo = r.streamInfo;
				error.streamInfo.setOutputUrl("");
				resp = error;
			}
		}

		getSender().tell(resp, getSelf());
	}
	void processGetStreamClients(GetStreamClients r) {
		GetStreamClientsResult result = new GetStreamClientsResult();
		int thriftPort = getThiftPort();

		if (type.equals("crtmpserver")) {
			try {
				ThriftUtil.Client<StreamServerService.Iface> client =
					ThriftUtil.newServiceClient(
						StreamServerService.Iface.class,
						StreamServerService.Client.class,
						ip,
						thriftPort,
						2000);

				Stream s = r.streamInfo;
				List<SmStreamClientInfo> clients = client.getIface().getClients(
					s.getStreamName(), s.getOutputType());
				result.clients = clients;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		getSender().tell(result, getSelf());
	}
	void processRemoveStream(RemoveStream r) {
		int thriftPort = getThiftPort();

		if (type.equals("crtmpserver")) {
			try {
				ThriftUtil.Client<StreamServerService.Iface> client =
					ThriftUtil.newServiceClient(
						StreamServerService.Iface.class,
						StreamServerService.Client.class,
						ip,
						thriftPort,
						2000);

				Stream s = r.streamInfo;
				client.getIface().removeStream(s.getStreamName(), s.getOutputType());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
