package controller.command;


import Service.CandidateService;
import exception.CandidateNotFoundException;
import listener.SessionListener;
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
import java.sql.SQLException;


public class LoginCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(LoginCommand.class);
    private final CandidateService candidateService;

    public LoginCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

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

        Candidate candidate = null;
        try {
            candidate = candidateService.findCandidateByUsername(username).orElseThrow(()->new CandidateNotFoundException("Candidate not found!"));
        } catch (CandidateNotFoundException e) {
            errorMessage = "Candidate not found!";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        CandidateProfile candidateProfile=null;
        try {
           candidateProfile = candidateService.getCandidateProfile(candidate)
                    .orElseThrow(()->new CandidateNotFoundException("Candidate profile not found!"));

        } catch (CandidateNotFoundException e) {
            errorMessage = "Candidate Profile not found!";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        candidate.setCandidateProfile(candidateProfile);

        if (!BCrypt.checkpw(password, candidate.getPassword())) {
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

        if (SessionListener.getCandidatesInSessions().containsKey(candidate.getUsername())) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Юзер вже у программі";
            } else {
                errorMessage = "User already inside";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }else{
           SessionListener.getCandidatesInSessions().put(candidate.getUsername(),session.getId());
        }



        Role candidateRole = candidate.getRole();


        if (candidateRole == Role.ADMIN) {
            try {
                forward="";
                response.sendRedirect("/controller?command=adminWorkspace");
            } catch (IOException e) {
                LOG.error("Bad Request!",e);
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

        LOG.info("Candidate {} logged as {}",candidate.getUsername(), candidateRole.getName());

        return forward;

    }
}

