package controller.command.admin;

import model.entity.Faculty;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditFacultyFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);


        request.setAttribute("faculty", faculty);
        return "WEB-INF\\jsp\\admin\\adminEditFaculty.jsp";
    }
}
