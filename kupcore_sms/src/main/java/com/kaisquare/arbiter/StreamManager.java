package com.kaisquare.arbiter;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.kaisquare.sim.CliClient;
import com.kaisquare.sim.Simulation;
import com.kaisquare.sim.SmsClient;
import com.kaisquare.stream.thrift.StreamManagementService;
import com.kaisquare.stream.thrift.StreamServerRegisterService;
import com.kaisquare.util.ThriftUtil;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TServer;

import java.io.InputStream;

public class StreamManager {
	// Configuration storage.
	static Config conf;

	// General logging.
	static Logger log;

	// Database.
	static SqlSessionFactory sqlSessionFactory;

	// Thrift servers.
	static TServer streamServerRegisterServer = null;
	static TServer streamManagementServer = null;

	// Actor System.
	static final ActorSystem actorSystem = ActorSystem.create("as");
	static ActorRef dispatchRequestActor;
	static ActorRef handleServerRegisterActor;
	static ActorRef manageStreamServerActor;

	// Global access.
	public static Config getConf() {
		return conf;
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public static TServer getStreamServerRegisterServer() {
		return streamServerRegisterServer;
	}
	public static TServer getStreamManagementServer() {
		return streamManagementServer;
	}

	public static ActorSystem getActorSystem() {
		return actorSystem;
	}
	public static ActorRef getDispatchRequestActor() {
		return dispatchRequestActor;
	}
	public static ActorRef getHandleServerRegisterActor() {
		return handleServerRegisterActor;
	}
	public static ActorRef getManageStreamServerActor() {
		return manageStreamServerActor;
	}

	// Main functions.
	static boolean initLogger() {
		boolean ok = true;

		log = Logger.getLogger(StreamManager.class);

		return ok;
	}
	static boolean initDB() {
		boolean ok = true;
		String resource = "mybatis-config.xml";

		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (sqlSessionFactory == null)
			ok = false;

		return ok;
	}
	static final boolean initActors() {
		boolean ok = true;

		dispatchRequestActor = actorSystem.actorOf(
			Props.create(DispatchRequestActor.class), "dispatch");
		handleServerRegisterActor = actorSystem.actorOf(
			Props.create(HandleServerRegisterActor.class), "svreg");
		manageStreamServerActor = actorSystem.actorOf(
			Props.create(ManageStreamServerActor.class), "svmgr");

		return ok;
	}
	static boolean startStreamServerRegisterServer() {
		boolean ok = true;

		try {
			int streamServerRegisterPort = getConf()
				.getConfig("sms").getConfig("streamserverregister").getInt("port");

			StreamServerRegisterHandler handler = new StreamServerRegisterHandler();
			StreamServerRegisterService.Processor processor =
				new StreamServerRegisterService.Processor(handler);
			streamServerRegisterServer = ThriftUtil.newServiceServer(
				processor, streamServerRegisterPort, 10, 20);
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}
	static boolean startStreamManagementServer() {
		boolean ok = true;

		try {
			int streamManagementPort = getConf()
				.getConfig("sms").getConfig("streammanagement").getInt("port");
			int selectors = getConf()
				.getConfig("sms").getConfig("streammanagement").getInt("selectors");
			int workers = getConf()
				.getConfig("sms").getConfig("streammanagement").getInt("workers");

			StreamManagementHandler handler = new StreamManagementHandler();
			StreamManagementService.Processor processor =
				new StreamManagementService.Processor(handler);
			streamManagementServer = ThriftUtil.newServiceServer(
				processor, streamManagementPort, selectors, workers);
		} catch (Exception e) {
			e.printStackTrace();
			ok = false;
		}

		return ok;
	}
	static boolean startThriftServers() {
		boolean ok = true;

		ok &= startStreamServerRegisterServer();
		if (!ok)
			log.error("failed to start StreamServerRegisterServer");
		ok &= startStreamManagementServer();
		if (!ok)
			log.error("failed to start StreamManagementServer");

		return ok;
	}

	public static void main(String[] args) throws Exception {
		if (!initLogger()) {
			System.out.println("failed to initialize logger");
			return;
		}

		log.info("Logger successfully started");

		conf = ConfigFactory.load();

		if (!StreamManager.initDB()) {
			log.error("failed to initialize database");
			return;
		}

		if (!StreamManager.initActors()) {
			log.error("failed to initialize actors");
			return;
		}

		if (!startThriftServers()) {
			log.error("failed to start thrift servers");
			return;
		}

		SmsClient.init();
		CliClient.run();
	}
}
