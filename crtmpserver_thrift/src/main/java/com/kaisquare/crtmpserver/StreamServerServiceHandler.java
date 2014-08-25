package com.kaisquare.crtmpserver;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.kaisquare.stream.thrift.SmPortInfo;
import com.kaisquare.stream.thrift.SmStreamClientInfo;
import com.kaisquare.stream.thrift.SmStreamInfo;
import com.kaisquare.stream.thrift.StreamServerService;
import com.kaisquare.util.SimpleTcpCli;
import org.apache.log4j.Logger;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class StreamServerServiceHandler implements StreamServerService.Iface {
	static Logger log = Logger.getLogger("Handler");

	@Override
	public long getServerId() {
		long result = -1;

		try {
			log.debug("Forward getserverid");
			String cmd = "getserverid\n";
			String raw = SimpleTcpCli.execute("localhost", ThriftWrapper.getCliPort(), cmd);

			Gson gson = new Gson();
			JsonObject root = gson.fromJson(raw.substring(raw.indexOf('{')), JsonObject.class);

			String status = root.get("status").getAsString();
			if (status.compareTo("SUCCESS") == 0)
				result = root.get("data").getAsJsonObject().get("server_id").getAsLong();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	@Override
	public SmStreamInfo	getStreamDetails(String streamName, String outputType) {
		SmStreamInfo si = new SmStreamInfo();

		return si;
	}
	@Override
	public List<SmStreamClientInfo> getClients(String streamName, String outputType) {
		List<SmStreamClientInfo> result = new ArrayList<>();

		try {
			log.debug(String.format("Forward getclients %s", streamName));
			String cmd = String.format("queryclients streamName=%s\n", streamName);
			String raw = SimpleTcpCli.execute("localhost", ThriftWrapper.getCliPort(), cmd);

			Gson gson = new Gson();
			JsonObject root = gson.fromJson(raw.substring(raw.indexOf('{')), JsonObject.class);

			String status = root.get("status").getAsString();
			if (status.compareTo("SUCCESS") == 0) {
				JsonArray clients = root.get("data").getAsJsonObject().get("clients").getAsJsonArray();
				for (JsonElement je : clients) {
					JsonObject jsonClientInfo = je.getAsJsonObject();

					SmStreamClientInfo info = new SmStreamClientInfo();
					info.setIp(jsonClientInfo.get("ip").getAsString());

					List<SmPortInfo> ports = new ArrayList<>();
					SmPortInfo p = new SmPortInfo();
					p.setProtocol("rtmp");
					p.setPort(jsonClientInfo.get("port").getAsString());
					ports.add(p);
					info.setPorts(ports);

					result.add(info);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	@Override
	public boolean setConfigurations(String config) {
		boolean ok = true;

		return ok;
	}
	@Override
	public String addStream(com.kaisquare.stream.thrift.SmStreamInfo streamInfo) {
		String result = "";

		try {
			log.debug(String.format("Forward addstream %s", streamInfo.getStreamName()));
			String cmd = String.format("add streamName=%s uri=%s\n",
				streamInfo.getStreamName(), streamInfo.getSource());
			String raw = SimpleTcpCli.execute("localhost", ThriftWrapper.getCliPort(), cmd);

			Gson gson = new Gson();
			JsonObject root = gson.fromJson(raw.substring(raw.indexOf('{')), JsonObject.class);

			String status = root.get("status").getAsString();
			if (status.compareTo("SUCCESS") == 0)
				result = root.get("data").getAsJsonObject().get("url").getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	@Override
	public String addPlaylist(com.kaisquare.stream.thrift.SmStreamInfo streamInfo, List<String> sourceList, List<Long> timeMap) {
		String result = "";

		return result;
	}
	@Override
	public void removeStream(String streamName, String outputType) {
		try {
			log.debug(String.format("Forward remove %s", streamName));
			String cmd = String.format("delete streamName=%s\n", streamName);
			SimpleTcpCli.execute("localhost", ThriftWrapper.getCliPort(), cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
