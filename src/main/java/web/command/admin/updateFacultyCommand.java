package web.command.admin;

import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getParameter("facultyId");
        request.getParameter("name");
        request.getParameter("facultyId");
        request.getParameter("facultyId");
        request.getParameter("facultyId");
        request.getParameter("facultyId");
        return null;
    }
}
