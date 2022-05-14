package service;

import dao.SessionDao;
import dao.SessionDaoDatabaseImpl;
import model.SessionPojo;

public class SessionServiceImpl implements SessionService {

SessionDao accountDao;
	
	public SessionServiceImpl() {
		
		accountDao = new SessionDaoDatabaseImpl();
	}
	
	public SessionPojo addSession(SessionPojo sessionPojo) {
		return accountDao.addSession(sessionPojo);
	}
}
