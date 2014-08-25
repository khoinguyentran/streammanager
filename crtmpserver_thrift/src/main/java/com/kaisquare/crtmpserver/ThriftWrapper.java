package com.kaisquare.crtmpserver;

import com.kaisquare.stream.thrift.StreamServerService;
import com.kaisquare.util.ThriftUtil;
import com.typesafe.config.Config;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TServer;

public class ThriftWrapper {
	static Config config;
	static Logger log;

	static int thriftPort = -1;
	static int cliPort = -1;

	// Global access.
	public static Config getConf() {
		return config;
	}
	public static int getThriftPort() {
		return thriftPort;
	}
	public static int getCliPort() {
		return cliPort;
	}

	static boolean initLogger() {
		boolean ok = true;

		log = Logger.getLogger(ThriftWrapper.class.getSimpleName());
		BasicConfigurator.configure();

		return ok;
	}

	static void terminateWithUsage() {
		System.out.println("Usage: crtmpserver.thrift <thrift_port> <cli_port>");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length < 2) {
			terminateWithUsage();
		}

		if (!initLogger()) {
			System.err.println("failed to initialize logger");
			return;
		}

		log.info("Logger successfully started");


		try {
			thriftPort = Integer.parseInt(args[0]);
			cliPort = Integer.parseInt(args[1]);
		} catch (Exception e) {
			terminateWithUsage();
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
	}
}
