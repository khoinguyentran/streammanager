package com.kaisquare.arbiter.dao;

import com.kaisquare.arbiter.StreamManager;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.TransactionIsolationLevel;

import java.util.ArrayList;
import java.util.List;

public class PortDaoMyBatis {
	public List<Port> getPortsByServerId(long serverId) {
		List<Port> ports = new ArrayList<>();

		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			PortDao dao = sql.getMapper(PortDao.class);
			ports = dao.getPortsByServerId(serverId);
			sql.commit();
		} catch (Exception e) {
			sql.rollback();
			e.printStackTrace();
		} finally {
			sql.close();
		}

		return ports;
	}
	public boolean insertPort(Port p) {
		boolean ok = true;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			PortDao dao = sql.getMapper(PortDao.class);
			dao.insertPort(p);
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
	public boolean deletePortsByServerId(long serverId) {
		boolean ok = true;
		SqlSession sql = StreamManager.getSqlSessionFactory()
			.openSession(TransactionIsolationLevel.SERIALIZABLE);

		try {
			PortDao dao = sql.getMapper(PortDao.class);
			dao.deletePortsByServerId(serverId);
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
