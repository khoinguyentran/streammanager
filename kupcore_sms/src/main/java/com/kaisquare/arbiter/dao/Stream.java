package com.kaisquare.arbiter.dao;

import java.util.Date;

public class Stream {
	long id;
	long deviceId;
	int channelId;
	String streamName;
	String source;
	String sourceType;
	String outputUrl;
	String outputType;
	long serverId;
	private Date lastUpdated;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getStreamName() {
		return streamName;
	}
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getOutputUrl() {
		return outputUrl;
	}
	public void setOutputUrl(String outputUrl) {
		this.outputUrl = outputUrl;
	}
	public String getOutputType() {
		return outputType;
	}
	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}
	public long getServerId() {
		return serverId;
	}
	public void setServerId(long serverId) {
		this.serverId = serverId;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
