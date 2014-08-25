package com.kaisquare.sim;

import com.kaisquare.arbiter.StreamManager;
import com.kaisquare.stream.thrift.*;
import com.kaisquare.util.ThriftUtil;
import org.apache.log4j.Logger;

import java.util.List;

public class SmsClient {
	static Logger log;

	static int smPort;
	static int timeout = 2000;

	public static boolean init() {
		boolean ok = true;

		log = Logger.getLogger(SmsClient.class);
		smPort = StreamManager.getConf()
			.getConfig("sms").getConfig("streammanagement").getInt("port");

		return ok;
	}

	static ThriftUtil.Client<StreamManagementService.Iface> getSmsClient() throws Exception {
		return ThriftUtil.newServiceClient(
							StreamManagementService.Iface.class,
							StreamManagementService.Client.class,
							"127.0.0.1",
							smPort,
							timeout);
	}

	public static void addStream(SmStreamInfo s) {
		try {
			ThriftUtil.Client<StreamManagementService.Iface> client =
				ThriftUtil.newServiceClient(
					StreamManagementService.Iface.class,
					StreamManagementService.Client.class,
					"127.0.0.1",
					smPort,
					timeout);

			String url = client.getIface().addStream(s.getDeviceId(), s.getChannelId(),
				s.getStreamName(), s.getOutputType(), s.getSource(), s.getSourceType());

			if (url.isEmpty())
				log.debug(String.format("failed to add %s(%s -> %s)",
					s.getStreamName(), s.getSourceType(), s.getOutputType()));
			else
				log.debug(String.format("url=%s", url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void removeStream(SmStreamInfo s) {
		try {
			ThriftUtil.Client<StreamManagementService.Iface> client =
				ThriftUtil.newServiceClient(
					StreamManagementService.Iface.class,
					StreamManagementService.Client.class,
					"127.0.0.1",
					smPort,
					timeout);

			boolean ok = client.getIface().removeStream(
				s.getDeviceId(),
				s.getChannelId(),
				s.getStreamName(),
				s.getOutputType());

			if (ok) {
				log.debug(String.format("%d.%d.%s removed",
					s.getDeviceId(), s.getChannelId(), s.getStreamName()));
			} else {
				log.debug(String.format("%d.%d.%s failed to remove",
					s.getDeviceId(), s.getChannelId(), s.getStreamName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getClients(SmStreamInfo s) {
		try {
			ThriftUtil.Client<StreamManagementService.Iface> client =
				ThriftUtil.newServiceClient(
					StreamManagementService.Iface.class,
					StreamManagementService.Client.class,
					"127.0.0.1",
					smPort,
					timeout);

			List<SmStreamClientInfo> streamClients = client.getIface().getClients(
				s.getDeviceId(),
				s.getChannelId(),
				s.getStreamName(),
				s.getOutputType());

			log.debug(String.format("%d.%d.%s has %d clients",
				s.getDeviceId(), s.getChannelId(), s.getStreamName(), streamClients.size()));
			for (SmStreamClientInfo sc : streamClients) {
				String portDesc = "";
				for (SmPortInfo p : sc.getPorts()) {
					portDesc += String.format("%s(%s) ", p.getPort(), p.getProtocol());
				}

				log.debug(String.format("client: %s %s", sc.getIp(), portDesc));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void getServerPoolInfo() {
		try {
			List<SmStreamServerInfo> servers = getSmsClient().getIface().getServerPoolInfo();

			log.debug(String.format("%d server(s) in database", servers.size()));
			for (SmStreamServerInfo s : servers) {
				String portDesc = "";
				for (SmPortInfo p : s.getPorts()) {
					portDesc += String.format("%s(%s) ", p.getPort(), p.getProtocol());
				}

				log.debug(String.format("%s/%d %s %s",
					s.getType(), s.getId(), s.getIp(), portDesc));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
