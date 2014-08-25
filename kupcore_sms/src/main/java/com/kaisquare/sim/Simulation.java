package com.kaisquare.sim;

import com.kaisquare.arbiter.StreamManager;
import com.kaisquare.stream.thrift.SmPortInfo;
import com.kaisquare.stream.thrift.SmStreamServerInfo;
import com.kaisquare.stream.thrift.StreamServerRegisterService;
import com.kaisquare.stream.thrift.StreamServerService;
import com.kaisquare.util.ThriftUtil;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TServer;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
	static Logger log;

	static int serverCount = 8;
	static List<Crtmpserver> serverList;
	static List<TServer> thriftServerList;

	public static final boolean init() {
		boolean ok = true;

		log = Logger.getLogger(Simulation.class);
		serverList = new ArrayList<>();
		thriftServerList = new ArrayList<>();

		return ok;
	}

	static final void createServers() {
		for (int i = 0; i < serverCount; i++) {
			Crtmpserver cs = new Crtmpserver(
				i,
				"127.0.0.1",
				2112 + i,
				1112 + i,
				1935 + i);
			serverList.add(cs);
		}
	}

	public static void registerServer(SmStreamServerInfo info) {
		int ssrPort = StreamManager.getConf()
			.getConfig("sms").getConfig("streamserverregister").getInt("port");

		try {
			ThriftUtil.Client<StreamServerRegisterService.Iface> client =
				ThriftUtil.newServiceClient(
					StreamServerRegisterService.Iface.class,
					StreamServerRegisterService.Client.class,
					"127.0.0.1",
					ssrPort,
					2000);
			if (!client.getIface().registerStreamingServer(info)) {
				System.out.println("failed to register with server");
			}
		} catch (Exception e) {
			System.out.println("failed to register with server");
		}
	}

	static final void registerServers() {
		for (int i = 0; i < serverCount; i++) {
			Crtmpserver s = serverList.get(i);
			List<SmPortInfo> ports = new ArrayList<>();

			ports.add(new SmPortInfo(String.valueOf(s.cliPort), "jsoncli"));
			ports.add(new SmPortInfo(String.valueOf(s.thriftPort), "thrift"));
			ports.add(new SmPortInfo(String.valueOf(s.rtmpPort), "rtmp"));

			SmStreamServerInfo serverInfo =
				new SmStreamServerInfo(s.serverId, "crtmpserver", s.ip, ports);
			registerServer(serverInfo);
		}
	}

	public static void startServerThriftService() {
		for (int i = 0; i < serverCount; i++) {
			Crtmpserver s = serverList.get(i);
			try {
				StreamServerService.Processor processor = new StreamServerService.Processor(s);
				TServer server = ThriftUtil.newServiceServer(processor, s.thriftPort, 10, 20);
				thriftServerList.add(server);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void simulate() {
		createServers();
		registerServers();
		startServerThriftService();
	}
}
