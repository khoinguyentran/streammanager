package com.kaisquare.arbiter;

import akka.actor.Inbox;
import akka.japi.Pair;
import com.kaisquare.arbiter.message.*;
import com.kaisquare.stream.thrift.SmPortInfo;
import com.kaisquare.stream.thrift.SmStreamServerInfo;
import com.kaisquare.stream.thrift.SmStreamServerStatus;
import com.kaisquare.stream.thrift.StreamServerRegisterService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StreamServerRegisterHandler implements StreamServerRegisterService.Iface {
	final Inbox inbox = Inbox.create(StreamManager.getActorSystem());

	@Override
	public boolean registerStreamingServer(SmStreamServerInfo toRegister) {
		boolean ok;

		List<Pair> ports = new ArrayList<>();

		for (SmPortInfo p : toRegister.getPorts()) {
			Pair<Integer, String> pair = new Pair<>(Integer.parseInt(p.getPort()), p.getProtocol());
			ports.add(pair);
		}

		RegisterStreamServer r = new RegisterStreamServer();
		r.serverId = toRegister.getId();
		r.type = toRegister.getType();
		r.ip = toRegister.getIp();
		r.ports = ports;

		inbox.send(StreamManager.getHandleServerRegisterActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streamserverregister").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);
		if (msg instanceof StreamServerRegistered)
			ok = true;
		else
			ok = false;

		return ok;
	}
	@Override
	public void deregisterStreamingServer(long serverId) {
		DeregisterStreamServer r = new DeregisterStreamServer();
		r.serverId = serverId;

		inbox.send(StreamManager.getHandleServerRegisterActor(), r);
		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streamserverregister").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		inbox.receive(inboxWait);
	}
	@Override
	public boolean updateStreamingServerStatus(SmStreamServerStatus status) {
		boolean ok;

		UpdateStreamServerStatus r = new UpdateStreamServerStatus();
		r.serverId = status.getId();

		inbox.send(StreamManager.getHandleServerRegisterActor(), r);

		FiniteDuration inboxWait = FiniteDuration.create(
			StreamManager.getConf().getConfig("sms").getConfig("streamserverregister").getInt("inbox-wait"),
			TimeUnit.MILLISECONDS);
		Object msg = inbox.receive(inboxWait);
		if (msg instanceof StreamServerStatusUpdated)
			ok = true;
		else
			ok = false;


		return ok;
	}
}
