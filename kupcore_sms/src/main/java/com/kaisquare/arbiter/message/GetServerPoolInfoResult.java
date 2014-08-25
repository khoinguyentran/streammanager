package com.kaisquare.arbiter.message;

import com.kaisquare.arbiter.dao.StreamServer;

import java.util.ArrayList;
import java.util.List;

public class GetServerPoolInfoResult extends Message {
	public List<StreamServer> servers = new ArrayList<>();
}
