package com.kaisquare.arbiter.dao;

public class Port {
	long serverId;
	int port;
	String protocol;

	public Port() {
	}

	public long getServerId() {
		return this.serverId;
	}
	public void setServerId(long serverId) {
		this.serverId = serverId;
	}
	public int getPort() {
		return this.port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getProtocol() {
		return this.protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public static boolean validate(int port) {
		return (port > 0 && port < 65536);
	}
	@Override
	public String toString() {
		return String.format("%s(%s)", getProtocol(), getPort());
	}
}
