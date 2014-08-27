package com.kaisquare.crtmpserver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaisquare.stream.thrift.*;
import com.kaisquare.util.SimpleTcpCli;
import com.kaisquare.util.ThriftUtil;
import com.typesafe.config.Config;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TServer;

import java.util.ArrayList;
import java.util.List;

public class ThriftWrapper {
	static Config config;
	static Logger log;

	static String serverIp = "";
	static int thriftPort = -1;
	static int cliPort = -1;
	static int updateInterval = -1;
	static String arbiterIp = "";
	static int arbiterRegPort = -1;
	static int thriftTimeout = 5000;

	static SmStreamServerInfo serverInfo = null;

	// Global access.
	public static Config getConf() {
		return config;
	}
	public static int getThriftPort() {
		return thriftPort;
	}
	public static String getServerIp() {
		return serverIp;
	}
	public static int getCliPort() {
		return cliPort;
	}
	public static String getArbiterIp() { return arbiterIp; }
	public static int getArbiterRegPort() { return arbiterRegPort; }
	public static int getThriftTimeout() { return thriftTimeout; }
	public static int getUpdateInterval() { return updateInterval; }
	public static SmStreamServerInfo getServerInfo() {
		return serverInfo;
	}

	static boolean initLogger() {
		boolean ok = true;

		log = Logger.getLogger(ThriftWrapper.class.getSimpleName());
		BasicConfigurator.configure();

		return ok;
	}

	static void terminateWithUsage() {
		System.out.println("Usage: crtmpserver.thrift <server_ip> <server_thrift_port> <cli_port>" +
			" <arbiter_ip> <arbiter_reg_port> <update_interval_seconds>");
		System.exit(1);
	}

	static SmStreamServerInfo getServerInfo(String ip, int port) throws Exception {
		log.info("Getting server's info");

		SmStreamServerInfo server = new SmStreamServerInfo();

		String cmd = "status\n";
		String raw = SimpleTcpCli.execute(ip, port, cmd);

		Gson gson = new Gson();
		JsonObject root = gson.fromJson(raw.substring(raw.indexOf('{')), JsonObject.class);

		String status = root.get("status").getAsString();
		if (status.compareTo("SUCCESS") == 0) {
			server.setIp(root.get("data").getAsJsonObject().get("server_ip").getAsString());
			server.setId(root.get("data").getAsJsonObject().get("server_id").getAsLong());
			server.setType("crtmpserver");
		}

		SmPortInfo cliPort = new SmPortInfo();
		cliPort.setPort(String.valueOf(getCliPort()));
		cliPort.setProtocol("jsoncli");

		SmPortInfo thriftPort = new SmPortInfo();
		thriftPort.setPort(String.valueOf(getThriftPort()));
		thriftPort.setProtocol("thrift");

		List<SmPortInfo> ports = new ArrayList<>();
		ports.add(cliPort);
		ports.add(thriftPort);

		server.setPorts(ports);

		return server;
	}

	static boolean registerWithArbiter(SmStreamServerInfo serverInfo) {
		log.info("Registering server with arbiter");

		boolean ok;

		try {
			ThriftUtil.Client<StreamServerRegisterService.Iface> client =
				ThriftUtil.newServiceClient(
					StreamServerRegisterService.Iface.class,
					StreamServerRegisterService.Client.class,
					getArbiterIp(),
					getArbiterRegPort(),
					getThriftTimeout());

			ok = client.getIface().registerStreamingServer(serverInfo);
		} catch (Exception e) {
			ok = false;
			e.printStackTrace();
		}

		return ok;
	}

	static void keepAliveWithArbiter() {
		log.info("Keepalive with arbiter");

		SmStreamServerStatus status = new SmStreamServerStatus();
		status.setId(getServerInfo().getId());

		try {
			ThriftUtil.Client<StreamServerRegisterService.Iface> client =
				ThriftUtil.newServiceClient(
					StreamServerRegisterService.Iface.class,
					StreamServerRegisterService.Client.class,
					getArbiterIp(),
					getArbiterRegPort(),
					getThriftTimeout());
			client.getIface().updateStreamingServerStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args.length < 6) {
			terminateWithUsage();
		}

		if (!initLogger()) {
			System.err.println("failed to initialize logger");
			return;
		}

		log.info("Logger successfully started");

		// Get parameters from command line arguments.
		try {
			serverIp = args[0];
			thriftPort = Integer.parseInt(args[1]);
			cliPort = Integer.parseInt(args[2]);
			arbiterIp = args[3];
			arbiterRegPort = Integer.parseInt(args[4]);
			updateInterval = Integer.parseInt(args[5]);
		} catch (Exception e) {
			e.printStackTrace();
			terminateWithUsage();
		}

		// Get server info and status.
		while (true) {
			try {
				serverInfo = getServerInfo(getServerIp(), getCliPort());
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		while (true) {
			if (registerWithArbiter(getServerInfo()))
				break;

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}

		log.info(String.format(
			"Forward thrift requests on %d to CLI on %d", thriftPort, cliPort));

		// Start thrift server.
		TServer server = null;
		try {
			StreamServerServiceHandler handler = new StreamServerServiceHandler();
			StreamServerService.Processor processor =
				new StreamServerService.Processor(handler);
			server = ThriftUtil.newServiceServer(processor, thriftPort, 10, 20);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Start keepalive thread.
		Thread keepAliveThread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(getUpdateInterval() * 1000);
					} catch (Exception e) {
						log.error(e.getMessage());
					}
					keepAliveWithArbiter();
				}
			}
		};
		keepAliveThread.start();
	}
}
