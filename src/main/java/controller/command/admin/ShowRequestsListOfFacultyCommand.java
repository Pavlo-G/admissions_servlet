package controller.command.admin;

import model.entity.Faculty;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowRequestsListOfFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = null;
        try {
            faculty = daoFactory.getFacultyDAO().findById(facultyId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        request.setAttribute("faculty", faculty);
        return "/WEB-INF/jsp/admin/admin-faculty-requests.jsp";
    }
}
