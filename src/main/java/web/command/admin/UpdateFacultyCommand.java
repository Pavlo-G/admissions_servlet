package web.command.admin;

import entity.Faculty;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Faculty faculty = new Faculty();
        faculty.setId(Long.valueOf(request.getParameter("facultyId")));
        faculty.setName(request.getParameter("name"));
        faculty.setDescription(request.getParameter("description"));
        faculty.setBudgetCapacity(Integer.parseInt(request.getParameter("budgetCapacity")));
        faculty.setTotalCapacity(Integer.parseInt(request.getParameter("totalCapacity")));
        faculty.setRequiredSubject1(request.getParameter("requiredSubject1"));
        faculty.setRequiredSubject2(request.getParameter("requiredSubject2"));
        faculty.setRequiredSubject3(request.getParameter("requiredSubject3"));
        daoFactory.getFacultyDAO().updateFaculty(faculty);

        return "/controller?command=adminWorkspace";
    }
}
