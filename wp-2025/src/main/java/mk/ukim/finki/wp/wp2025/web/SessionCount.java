package mk.ukim.finki.wp.wp2025.web;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionCount implements HttpSessionListener {

    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
        System.out.println("Session created. Active sessions: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (activeSessions > 0) activeSessions--;
        System.out.println("Session destroyed. Active sessions: " + activeSessions);
    }

    public static int getActiveSessions() {
        return activeSessions;
    }
}