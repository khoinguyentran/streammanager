package com.kaisquare.arbiter;

import akka.actor.Inbox;
import com.kaisquare.arbiter.dao.Port;
import com.kaisquare.arbiter.dao.StreamServer;
import com.kaisquare.arbiter.message.*;
import com.kaisquare.stream.thrift.SmPortInfo;
import com.kaisquare.stream.thrift.SmStreamClientInfo;
import com.kaisquare.stream.thrift.SmStreamServerInfo;
import com.kaisquare.stream.thrift.StreamManagementService;
import scala.concurrent.duration.FiniteDuration;
import sun.net.smtp.SmtpClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StreamManagementHandler implements StreamManagementService.Iface {
	final Inbox inbox = Inbox.create(StreamManager.getActorSystem());

	@Override
	public List<SmStreamServerInfo> getServerPoolInfo() {
		List<SmStreamServerInfo> servers = new ArrayList<>();

		GetServerPoolInfo r = new GetServerPoolInfo();
		inbox.send(StreamManager.getDispatchRequestActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streammanagement").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);
		if (msg instanceof GetServerPoolInfoResult) {
			for (StreamServer s : ((GetServerPoolInfoResult)msg).servers) {
				SmStreamServerInfo serverInfo = new SmStreamServerInfo();
				serverInfo.setId(s.getId());
				serverInfo.setType(s.getType());
				serverInfo.setIp(s.getIp());

				List<SmPortInfo> ports = new ArrayList<>();
				for (Port p : s.getPorts()) {
					SmPortInfo portInfo = new SmPortInfo();
					portInfo.setPort(String.valueOf(p.getPort()));
					portInfo.setProtocol(p.getProtocol());
					ports.add(portInfo);
				}
				serverInfo.setPorts(ports);

				servers.add(serverInfo);
			}
		}

		return servers;
	}
	@Override
	public String addStream(long deviceId, int channelId, String streamName, String outputType, String source, String sourceType) {
		String url = "";

		AddStream r = new AddStream();
		r.streamInfo.setDeviceId(deviceId);
		r.streamInfo.setChannelId(channelId);
		r.streamInfo.setStreamName(streamName);
		r.streamInfo.setOutputType(outputType);
		r.streamInfo.setSource(source);
		r.streamInfo.setSourceType(sourceType);

		inbox.send(StreamManager.getDispatchRequestActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streammanagement").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);
		if (msg instanceof StreamAdded) {
			url = ((StreamAdded)msg).streamInfo.getOutputUrl();
		} else if (msg instanceof StreamAddFailed) {
			url = "";
		}

		return url;
	}
	@Override
	public String addPlaylist(long deviceId, int channelId, String streamName, String outputType, List<String> sourceList, List<Long> timeMap) {
		String url = "";

		AddPlaylist r = new AddPlaylist();
		r.streamInfo.setDeviceId(deviceId);
		r.streamInfo.setChannelId(channelId);
		r.streamInfo.setStreamName(streamName);
		r.streamInfo.setOutputType(outputType);
		r.streamInfo.setSource("");
		r.streamInfo.setSourceType("playlist/mp4");
		r.sourceList = sourceList;
		r.timeMap = timeMap;

		inbox.send(StreamManager.getDispatchRequestActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streammanagement").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);
		if (msg instanceof StreamAdded)
			url = ((StreamAdded)msg).streamInfo.getOutputUrl();

		return url;
	}
	@Override
	public boolean removeStream(long deviceId, int channel, String streamName, String outputType) {
		boolean ok = true;

		RemoveStream r = new RemoveStream();
		r.streamInfo.setDeviceId(deviceId);
		r.streamInfo.setChannelId(channel);
		r.streamInfo.setStreamName(streamName);
		r.streamInfo.setOutputType(outputType);

		inbox.send(StreamManager.getDispatchRequestActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streammanagement").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);
		if (!(msg instanceof StreamRemoved))
			ok = false;

		return ok;
	}
	@Override
	public List<SmStreamClientInfo> getClients(long deviceId, int channelId, String streamName, String outputType) {
		List<SmStreamClientInfo> streamClients;

		GetStreamClients r = new GetStreamClients();
		r.streamInfo.setDeviceId(deviceId);
		r.streamInfo.setChannelId(channelId);
		r.streamInfo.setStreamName(streamName);
		r.streamInfo.setOutputType(outputType);

		inbox.send(StreamManager.getDispatchRequestActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streammanagement").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);

		if (msg instanceof GetStreamClientsResult)
			streamClients = ((GetStreamClientsResult)msg).clients;
		else
			streamClients = new ArrayList<>();

		return streamClients;
	}
}
