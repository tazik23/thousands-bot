package org.example.Repositories;

import org.example.Models.Session;

public interface ISessionRepository {
    void addSession(Session session);
    void updateSession(Session session);
    void deleteSession(Session session);
    Session getSessionById(long Id);
}
