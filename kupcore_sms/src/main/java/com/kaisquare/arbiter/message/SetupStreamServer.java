package com.kaisquare.arbiter.message;

import akka.japi.Pair;
import com.kaisquare.arbiter.dao.Port;

import java.util.List;

public class SetupStreamServer extends Message {
	public long serverId;
	public String type;
	public String ip;
	// Port{port, protocol}
	public List<Port> ports;
}
