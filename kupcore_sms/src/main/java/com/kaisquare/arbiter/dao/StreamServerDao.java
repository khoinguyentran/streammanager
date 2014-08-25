package com.kaisquare.arbiter.dao;


import java.util.List;

public interface StreamServerDao {
	public StreamServer getStreamServerById(long id);
	public List<StreamServer> getAllStreamServers();
	public void insertStreamServer(StreamServer s);
	public void updateStreamServer(StreamServer s);
	public void deleteStreamServer(long id);
}