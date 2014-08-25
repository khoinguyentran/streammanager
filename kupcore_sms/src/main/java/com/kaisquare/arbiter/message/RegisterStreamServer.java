package com.kaisquare.arbiter.message;

import akka.japi.Pair;

import java.util.List;

public class RegisterStreamServer extends Message {
	public long serverId;
	public String type;
	public String ip;
	// Port{port, protocol}
	public List<Pair> ports;
}
