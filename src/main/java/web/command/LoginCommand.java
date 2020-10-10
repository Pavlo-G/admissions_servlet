package web.command;

import DAO.DAOFactory;
import entity.Candidate;
import entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

//        log.debug("Command starts");

        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
//        log.trace("Request parameter: loging --> " + login);

        String password = request.getParameter("password");

        // error handler
        String errorMessage = null;
      String forward ="WEB-INF\\jsp\\errorPage.jsp";
//        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
//            errorMessage = "Login/password cannot be empty";
//            request.setAttribute("errorMessage", errorMessage);
//            log.error("errorMessage --> " + errorMessage);
//            return forward;
//        }

        Candidate candidate = daoFactory.getCandidateDAO().findCandidateByLogin(login);
      //  log.trace("Found in DB: user --> " + user);

        if (candidate == null || !password.equals(candidate.getPassword())) {
            errorMessage = "Cannot find user with such login/password";
            request.setAttribute("errorMessage", errorMessage);
          //  log.error("errorMessage --> " + errorMessage);
            return forward;
        } else {
            Role userRole = candidate.getRole();
            // log.trace("userRole --> " + userRole);

//            if (userRole == Role.ADMIN)
//                forward = Path.COMMAND__LIST_ORDERS;
//
//            if (userRole == Role.CLIENT)
//                forward = Path.COMMAND__LIST_MENU;
//
//            session.setAttribute("user", user);
//            log.trace("Set the session attribute: user --> " + user);
//
//            session.setAttribute("userRole", userRole);
//            log.trace("Set the session attribute: userRole --> " + userRole);
//
//            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
//
//            // work with i18n
//            String userLocaleName = user.getLocaleName();
//            log.trace("userLocalName --> " + userLocaleName);
//
//            if (userLocaleName != null && !userLocaleName.isEmpty()) {
//                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
//
//                session.setAttribute("defaultLocale", userLocaleName);
//                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
//
//                log.info("Locale for user: defaultLocale --> " + userLocaleName);
//            }
//        }
//
//        log.debug("Command finished");
//        return forward;
            return "/WEB-INF/jsp/login.jsp";
        }
    }
}
