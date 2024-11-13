package org.example.Repositories;

import org.example.Models.Session;

import java.util.HashMap;
import java.util.Map;

public class InMemorySessionRepository implements ISessionRepository{
    private final Map<Long, Session> sessions;

    public InMemorySessionRepository() {
        this.sessions = new HashMap<>();
    }


    @Override
    public void addSession(Session session) {
        sessions.put(session.getId(), session);
    }

    @Override
    public void updateSession(Session session) {

    }

    @Override
    public void deleteSession(Session session) {
        sessions.remove(session.getId());
    }

    @Override
    public Session getSessionById(long id) {
        return sessions.get(id);
    }
}
