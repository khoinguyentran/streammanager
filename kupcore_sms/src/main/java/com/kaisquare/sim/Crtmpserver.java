package com.kaisquare.sim;

import com.kaisquare.stream.thrift.SmPortInfo;
import com.kaisquare.stream.thrift.SmStreamClientInfo;
import com.kaisquare.stream.thrift.SmStreamInfo;
import com.kaisquare.stream.thrift.StreamServerService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Crtmpserver implements StreamServerService.Iface {
	public long serverId;
	public String ip;
	public int thriftPort;
	public int cliPort;
	public int rtmpPort;
	public String flvApp = "live";
	public String config;
	public List<SmStreamInfo> streams;

	public Crtmpserver(long serverId, String ip, int thriftPort, int cliPort, int rtmpPort) {
		this.serverId = serverId;
		this.ip = ip;
		this.thriftPort = thriftPort;
		this.cliPort = cliPort;
		this.rtmpPort = rtmpPort;

		streams = new ArrayList<>();
	}

	boolean hasStream(SmStreamInfo toCheck) {
		boolean exists = false;

		for (SmStreamInfo s : streams) {
			if (s.getStreamName().equals(toCheck.getStreamName())
				&& s.getOutput().equals(toCheck.getOutputType())) {
				System.out.println(String.format("%s already added",
					toCheck.getStreamName(), toCheck.getOutputType()));
				exists = true;
				break;
			}
		}

		return exists;
	}

	@Override
	public long getServerId() {
		return this.serverId;
	}

	@Override
	public SmStreamInfo getStreamDetails(String streamName, String outputType) {
		SmStreamInfo retval = null;

		for (SmStreamInfo si : streams) {
			if (si.getStreamName().equals(streamName)
				&& si.getOutputType().equals(outputType)) {
				retval = si;
				break;
			}
		}

		return retval;
	}

	String generateRandomIp() {
		Random r = new Random();

		String ip = "78";
		for (int i = 0; i < 3; i++) {
			int x = r.nextInt(256);
			ip += "." + String.valueOf(x);
		}

		return ip;
	}

	@Override
	public List<SmStreamClientInfo> getClients(String streamName, String outputType)  {
		List<SmStreamClientInfo> clients = new ArrayList<>();

		boolean found = false;
		for (SmStreamInfo s : streams) {
			if (s.getStreamName().equals(streamName) && s.getOutputType().equals(outputType)) {
				found = true;
				break;
			}
		}

		if (!found)
			return clients;

		Random r = new Random();
		int nClients = r.nextInt(10);

		for (int i = 0; i < nClients; i++) {
			List<SmPortInfo> ports = new ArrayList<>();
			int p = r.nextInt(65536);
			SmPortInfo port = new SmPortInfo();
			port.port = String.valueOf(p);
			port.protocol = "rtmp";
			ports.add(port);

			SmStreamClientInfo client = new SmStreamClientInfo();
			client.ip = generateRandomIp();
			client.ports = ports;

			clients.add(client);
		}

		return clients;
	}

	@Override
	public boolean setConfigurations(String config) {
		this.config = config;

		return true;
	}

	@Override
	public String addStream(SmStreamInfo toAdd) {
		String url = String.format("rtmp://%s:%d/%s/%s", ip, rtmpPort, flvApp, toAdd.getStreamName());

		streams.add(toAdd);

		return url;
	}

	@Override
	public String addPlaylist(SmStreamInfo toAdd, List<String> sourceList, List<Long> timeMap) {
		String url = String.format("rtmp://%s:%d/%s/%s", ip, rtmpPort, flvApp, toAdd.getStreamName());

		streams.add(toAdd);

		return url;
	}

	@Override
	public void removeStream(String streamName, String outputType) {
		// Removal always succeeds.
		for (Iterator<SmStreamInfo> it = streams.iterator(); it.hasNext(); ) {
			SmStreamInfo s = it.next();
			if (s.getStreamName().equals(streamName) && s.getOutputType().equals(outputType)) {
				it.remove();
			}
		}
	}
}
