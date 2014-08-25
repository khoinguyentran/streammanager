package com.kaisquare.arbiter.dao;

import java.util.List;

public interface PortDao {
	public List<Port> getPortsByServerId(long serverId);
	public void insertPort(Port port);
	public void deletePortsByServerId(long serverId);
}
