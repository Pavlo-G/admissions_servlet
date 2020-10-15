package web.command.admin;

import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewFacultyFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {




        return "WEB-INF\\jsp\\admin\\adminCreateFacultyFrom.jsp";
    }
}
