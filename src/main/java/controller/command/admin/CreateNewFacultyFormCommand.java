package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateNewFacultyFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "WEB-INF\\jsp\\admin\\adminCreateFacultyFrom.jsp";
    }
}
