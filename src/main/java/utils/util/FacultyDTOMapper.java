package utils.util;

import model.entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class FacultyDTOMapper {

    public  Faculty getFaculty(Map<String, String> facultyParameters) {
        Faculty faculty = new Faculty();
        faculty.setNameEn(facultyParameters.get("nameEn"));
        faculty.setNameUk(facultyParameters.get("nameUk"));
        faculty.setDescriptionEn(facultyParameters.get("descriptionEn"));
        faculty.setDescriptionUk(facultyParameters.get("descriptionUk"));
        faculty.setBudgetCapacity(Integer.parseInt(facultyParameters.get("budgetCapacity")));
        faculty.setTotalCapacity(Integer.parseInt(facultyParameters.get("totalCapacity")));
        faculty.setRequiredSubject1En(facultyParameters.get("requiredSubject1En"));
        faculty.setRequiredSubject1Uk(facultyParameters.get("requiredSubject1Uk"));
        faculty.setRequiredSubject2En(facultyParameters.get("requiredSubject2En"));
        faculty.setRequiredSubject2Uk(facultyParameters.get("requiredSubject2Uk"));
        faculty.setRequiredSubject3En(facultyParameters.get("requiredSubject3En"));
        faculty.setRequiredSubject3Uk(facultyParameters.get("requiredSubject3Uk"));
        faculty.setAdmissionOpen(true);
        return faculty;
    }


}
