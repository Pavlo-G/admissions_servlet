package listener;

import controller.command.LoginCommand;
import model.entity.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionListener implements HttpSessionListener {
    static final Logger LOG = LoggerFactory.getLogger(SessionListener.class);

    private static Map<String, String> candidatesInSessions = new ConcurrentHashMap<>();


    public static Map<String, String> getCandidatesInSessions() {
        return candidatesInSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        if (( arg0.getSession().getAttribute("candidate"))!=null){
            Candidate candidate= (Candidate) arg0.getSession().getAttribute("candidate");
            SessionListener.getCandidatesInSessions().remove(candidate.getUsername());
            LOG.info("User {} logout. Session destroyed",candidate.getUsername());
        }
    }


}
