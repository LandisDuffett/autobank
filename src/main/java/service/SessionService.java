package service;

import java.util.List;

import model.SessionPojo;

public interface SessionService {

	SessionPojo addSession(SessionPojo sessionPojo);
	
	List<SessionPojo> getAllSessions();
	
	List<SessionPojo> getSessionsForOneUser(int userId);
}
