package dao;

import java.util.List;

import model.SessionPojo;

public interface SessionDao {

	SessionPojo addSession(SessionPojo sessionPojo);
	
	List<SessionPojo> getAllSessions();
	
	List<SessionPojo> getSessionsForOneUser(int userId);
}
