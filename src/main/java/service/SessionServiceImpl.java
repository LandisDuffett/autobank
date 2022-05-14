package service;

import java.util.List;

import dao.SessionDao;
import dao.SessionDaoDatabaseImpl;
import model.SessionPojo;

public class SessionServiceImpl implements SessionService {

SessionDao serviceDao;
	
	public SessionServiceImpl() {
		
		serviceDao = new SessionDaoDatabaseImpl();
	}
	
	public SessionPojo addSession(SessionPojo sessionPojo) {
		return serviceDao.addSession(sessionPojo);
	}
	
	public List<SessionPojo> getAllSessions() {
		return serviceDao.getAllSessions();
	}
	
	public List<SessionPojo> getSessionsForOneUser(int userId) {
		return serviceDao.getSessionsForOneUser(userId);
	}
}
