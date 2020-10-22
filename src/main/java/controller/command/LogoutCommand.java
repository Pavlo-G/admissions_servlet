package controller.command;

import listener.SessionListener;
import model.entity.Candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            Candidate candidate = (Candidate) session.getAttribute("candidate");
            SessionListener.getCandidatesInSessions().remove(candidate.getUsername());
            session.invalidate();
        }


        return "index.jsp";

    }
}
