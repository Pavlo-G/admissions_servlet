package web.command.admin;

import entity.Faculty;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRequestsListOfFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);


        request.setAttribute("faculty", faculty);
        return "/WEB-INF/jsp/admin/admin-faculty-requests.jsp";
    }
}
