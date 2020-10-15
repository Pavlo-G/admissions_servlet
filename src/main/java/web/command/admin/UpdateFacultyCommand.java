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
        faculty.setNameEn(request.getParameter("name_en"));
        faculty.setNameUk(request.getParameter("name_uk"));
        faculty.setDescriptionEn(request.getParameter("description_en"));
        faculty.setDescriptionUk(request.getParameter("description_uk"));
        faculty.setBudgetCapacity(Integer.parseInt(request.getParameter("budgetCapacity")));
        faculty.setTotalCapacity(Integer.parseInt(request.getParameter("totalCapacity")));
        faculty.setRequiredSubject1En(request.getParameter("requiredSubject1_en"));
        faculty.setRequiredSubject1Uk(request.getParameter("requiredSubject1_uk"));
        faculty.setRequiredSubject2En(request.getParameter("requiredSubject2_en"));
        faculty.setRequiredSubject2Uk(request.getParameter("requiredSubject2_uk"));
        faculty.setRequiredSubject3En(request.getParameter("requiredSubject3_en"));
        faculty.setRequiredSubject3Uk(request.getParameter("requiredSubject3_uk"));
        daoFactory.getFacultyDAO().updateFaculty(faculty);

        return "/controller?command=adminWorkspace";
    }
}
