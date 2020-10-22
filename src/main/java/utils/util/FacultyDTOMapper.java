package utils.util;

import model.entity.Faculty;

import javax.servlet.http.HttpServletRequest;

public class FacultyDTOMapper {


    public Faculty mapFaculty(HttpServletRequest request) {
        String nameEn = request.getParameter("name_en");
        String nameUk = request.getParameter("name_uk");
        String descriptionEn = request.getParameter("description_en");
        String descriptionUk = request.getParameter("description_uk");
        String totalCapacity = request.getParameter("totalCapacity");
        String budgetCapacity = request.getParameter("budgetCapacity");
        String requiredSubject1En = request.getParameter("requiredSubject1_en");
        String requiredSubject1Uk = request.getParameter("requiredSubject1_uk");
        String requiredSubject2En = request.getParameter("requiredSubject2_en");
        String requiredSubject2Uk = request.getParameter("requiredSubject2_uk");
        String requiredSubject3En = request.getParameter("requiredSubject3_en");
        String requiredSubject3Uk = request.getParameter("requiredSubject3_uk");

        Faculty faculty = new Faculty();
        faculty.setNameEn(nameEn);
        faculty.setNameUk(nameUk);
        faculty.setDescriptionEn(descriptionEn);
        faculty.setDescriptionUk(descriptionUk);
        faculty.setBudgetCapacity(Integer.parseInt(budgetCapacity));
        faculty.setTotalCapacity(Integer.parseInt(totalCapacity));
        faculty.setRequiredSubject1En(requiredSubject1En);
        faculty.setRequiredSubject1Uk(requiredSubject1Uk);
        faculty.setRequiredSubject2En(requiredSubject2En);
        faculty.setRequiredSubject2Uk(requiredSubject2Uk);
        faculty.setRequiredSubject3En(requiredSubject3En);
        faculty.setRequiredSubject3Uk(requiredSubject3Uk);
        faculty.setAdmissionOpen(true);
        return faculty;
    }


}
