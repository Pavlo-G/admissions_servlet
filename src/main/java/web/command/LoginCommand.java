package web.command;

import DAO.DAOFactory;
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\errorPage.jsp";
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            errorMessage = "Login/password cannot be empty";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        Candidate candidate = daoFactory.getCandidateDAO().findCandidateByUsername(username);
        try {
            CandidateProfile candidateProfile =daoFactory.getCandidateDAO().getCandidateProfile(candidate)
                    .orElseThrow(()->new Exception());
            candidate.setCandidateProfile(candidateProfile);
        } catch (Exception e) {
            errorMessage = "Candidate Profile not found!";
        }


        if (candidate == null || !BCrypt.checkpw(password, candidate.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        } else {
            Role candidateRole = candidate.getRole();


            if (candidateRole == Role.ADMIN)
                forward = "/controller?command=adminWorkspace";

            if (candidateRole == Role.USER)
                forward = "/controller?command=facultiesList";;

            session.setAttribute("candidate", candidate);
            session.setAttribute("candidateRole", candidateRole);

            log.info("Candidate " + candidate + " logged as " + candidateRole.getName());

//            // work with i18n
//            String userLocaleName = candidate.getLocaleName();


//            if (userLocaleName != null && !userLocaleName.isEmpty()) {
//                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);

//                session.setAttribute("defaultLocale", userLocaleName);
//                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
//
//                log.info("Locale for user: defaultLocale --> " + userLocaleName);
//            }
//        }
//

            return forward;

        }
    }
}
