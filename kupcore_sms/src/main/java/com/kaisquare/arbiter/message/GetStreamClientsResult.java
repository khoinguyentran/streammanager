package com.kaisquare.arbiter.message;

import com.kaisquare.stream.thrift.SmStreamClientInfo;

import java.util.ArrayList;
import java.util.List;

public class GetStreamClientsResult extends Message {
	public List<SmStreamClientInfo> clients = new ArrayList<>();
}
