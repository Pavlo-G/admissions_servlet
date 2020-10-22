package controller.command.admin;

import model.entity.Faculty;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EditFacultyFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = null;
        try {
            faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        request.setAttribute("faculty", faculty);
        return "WEB-INF\\jsp\\admin\\adminEditFaculty.jsp";
    }
}
