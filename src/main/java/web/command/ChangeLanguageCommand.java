package web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String e= request.getServletContext().getRealPath(request.getServletPath());
        String x = request.getContextPath();
        String path2 = request.getRequestURI();

        return "";
    }
}
