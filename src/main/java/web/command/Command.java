package web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {



    public String execute(HttpServletRequest request, HttpServletResponse response);

}
