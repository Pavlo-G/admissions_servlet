package controller.command;

import model.entity.Candidate;
import model.entity.CandidateProfile;
import model.entity.CandidateStatus;
import model.entity.Role;


import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("lang");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\login.jsp";
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Логін/Пароль не можуть буди пустими";
            } else {
                errorMessage = "Login/password cannot be empty";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        Candidate candidate = daoFactory.getCandidateDAO().findCandidateByUsername(username);
        try {
            CandidateProfile candidateProfile = daoFactory.getCandidateDAO().getCandidateProfile(candidate)
                    .orElseThrow(() -> new Exception());
            candidate.setCandidateProfile(candidateProfile);
        } catch (Exception e) {
            errorMessage = "Candidate Profile not found!";
        }


        if (candidate == null || !BCrypt.checkpw(password, candidate.getPassword())) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Юзера з таким логіном/паролем неіснує!";
            } else {
                errorMessage = "Cannot find user with such login/password!";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }


        if (candidate.getCandidateStatus() == CandidateStatus.BLOCKED) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Юзер заблокований, зверніться до адміністратора";
            } else {
                errorMessage = "User blocked, contact admin";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        Role candidateRole = candidate.getRole();


        if (candidateRole == Role.ADMIN) {
            try {
                forward="";
                response.sendRedirect("/controller?command=adminWorkspace");
            } catch (IOException e) {
                errorMessage = "Bad Request!";
                request.setAttribute("errorMessage", errorMessage);
            }
        }


        if (candidateRole == Role.USER) {
            try {
                forward="";
                response.sendRedirect("/controller?command=facultiesList");
            } catch (IOException e) {
                LOG.error("Bad Request!",e);
                errorMessage = "Bad Request!";
                request.setAttribute("errorMessage", errorMessage);
            }

        }

        session.setAttribute("candidate", candidate);
        session.setAttribute("candidateRole", candidateRole);

        LOG.info("Candidate " + candidate + " logged as " + candidateRole.getName());

        return forward;

    }
}

