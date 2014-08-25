package com.kaisquare.arbiter.dao;

import com.kaisquare.arbiter.StreamManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.util.ArrayList;
import java.util.List;

public class StreamServerDaoMyBatis {
	public StreamServer getStreamServerById(long id) {
		StreamServer s = null;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamServerDao dao = sql.getMapper(StreamServerDao.class);
			s = dao.getStreamServerById(id);
			sql.commit();
		} catch (Exception e) {
			sql.rollback();
			e.printStackTrace();
		} finally {
			sql.close();
		}

		return s;
	}
	public List<StreamServer> getAllStreamServers() {
		List<StreamServer> svlist = null;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamServerDao dao = sql.getMapper(StreamServerDao.class);
			svlist = dao.getAllStreamServers();
			sql.commit();
		} catch (Exception e) {
			sql.rollback();
			e.printStackTrace();
		} finally {
			sql.close();
		}

		return svlist;
	}
	public boolean insertStreamServer(StreamServer s) {
		boolean ok = true;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamServerDao dao = sql.getMapper(StreamServerDao.class);
			dao.insertStreamServer(s);
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
	public boolean updateStreamServer(StreamServer s) {
		boolean ok = true;

		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamServerDao dao = sql.getMapper(StreamServerDao.class);
			dao.updateStreamServer(s);

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

	public boolean deleteStreamServer(long id) {
		boolean ok = true;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			StreamServerDao dao = sql.getMapper(StreamServerDao.class);
			dao.deleteStreamServer(id);
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
