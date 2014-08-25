package com.kaisquare.arbiter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StreamServer {
	private long id;
	private String ip;
	private String type;
	private Date lastUpdated;
	private List<Port> ports;

	public StreamServer() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date d) {
		this.lastUpdated = d;
	}
	public List<Port> getPorts() {
		return ports;
	}
	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	@Override
	public String toString() {
		String ss = String.format("%s/%d/%s", type, id, ip);

		String ps = "";
		if (ports != null && !ports.isEmpty()) {
			ps = "ports";
			for (Port p : ports) {
				ps += String.format(" %s:%d", p.getProtocol(), p.getPort());
			}
		}

		return String.format("%s %s", ss, ps);
	}
}
