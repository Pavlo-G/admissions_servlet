package web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/registration.jsp";
    }
}
