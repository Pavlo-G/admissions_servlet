package web.command;

import entity.Candidate;
import entity.CandidateProfile;
import entity.Role;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String lang = (String) session.getAttribute("lang");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\login.jsp";
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            if(lang!=null
                    &&lang.equals("uk")) {
                errorMessage = "Логін/Пароль не можуть буди пустими";
            }else {
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
            if(lang!=null
                    &&lang.equals("uk")) {
                errorMessage = "Юзера з таким логіном/паролем неіснує!";
            }else {
                errorMessage = "Cannot find user with such login/password!";
            }

            request.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role candidateRole = candidate.getRole();


            if (candidateRole == Role.ADMIN)
                forward = "/controller?command=adminWorkspace";

            if (candidateRole == Role.USER)
                forward = "/controller?command=facultiesList";


            session.setAttribute("candidate", candidate);
            session.setAttribute("candidateRole", candidateRole);

            log.info("Candidate " + candidate + " logged as " + candidateRole.getName());

            return forward;

        }
    }
}
