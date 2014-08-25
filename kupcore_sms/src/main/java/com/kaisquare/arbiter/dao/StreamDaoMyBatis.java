package com.kaisquare.arbiter.dao;

import com.kaisquare.arbiter.StreamManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.util.List;

public class StreamDaoMyBatis {
	public List<Stream> getStreamsByNameByOutputType(String streamName, String outputType) {
		List<Stream> streams = null;

		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamDao dao = sql.getMapper(StreamDao.class);
			streams = dao.getStreamsByNameByOutputType(streamName, outputType);
			sql.commit();
		} catch (Exception e) {
			sql.rollback();
			e.printStackTrace();
		} finally {
			sql.close();
		}

		return streams;
	}

	public boolean insertStream(Stream stream) {
		boolean ok = true;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamDao dao = sql.getMapper(StreamDao.class);
			dao.insertStream(stream);
			sql.commit();
		} catch (Exception e) {
			ok = false;
			sql.rollback();
			e.printStackTrace();
		} finally {
			sql.close();
		}

		return ok;
	}

	public boolean deleteStream(long deviceId, int channelId, String streamName, String outputType) {
		boolean ok = true;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamDao dao = sql.getMapper(StreamDao.class);
			dao.deleteStream(deviceId, channelId, streamName, outputType);
			sql.commit();
		} catch (Exception e) {
			ok = false;
			sql.rollback();
			e.printStackTrace();
		} finally {
			sql.close();
		}

		return ok;
	}
}
