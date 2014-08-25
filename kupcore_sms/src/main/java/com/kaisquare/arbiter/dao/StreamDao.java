package com.kaisquare.arbiter.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StreamDao {
	public List<Stream> getStreamsByNameByOutputType(
		@Param("streamName") String streamName,
		@Param("outputType") String outputType);
	public void insertStream(Stream stream);
	public void deleteStream(
		@Param("deviceId") long deviceId,
	    @Param("channelId") int channelId,
	    @Param("streamName") String streamName,
	    @Param("outputType") String outputType);
}
