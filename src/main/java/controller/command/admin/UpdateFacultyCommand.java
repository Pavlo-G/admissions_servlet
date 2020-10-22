package controller.command.admin;

import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.validation.FacultyCapacityValidator;
import utils.validation.FieldValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(UpdateFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String lang = (String) request.getSession().getAttribute("lang");
        String errorMessage = null;
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        String forward = "/controller?command=editFacultyForm&facultyIf=" + facultyId;

        String totalCapacity = request.getParameter("totalCapacity");
        String budgetCapacity = request.getParameter("budgetCapacity");
        FacultyCapacityValidator facultyCapacityValidator = new FacultyCapacityValidator();
        FieldValidator fieldValidator = new FieldValidator();
        if (fieldValidator.validate(totalCapacity) || fieldValidator.validate(budgetCapacity)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Місця фкультету не можуть буди прожніми";
            } else {
                errorMessage = "Faculty capacity can not be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        if (!facultyCapacityValidator.isNumber(totalCapacity) || !facultyCapacityValidator.isNumber(budgetCapacity)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Місця фкультету повинні бути целим числом 1-999";
            } else {
                errorMessage = "Faculty capacity must be a number 1-999";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }

        if (!facultyCapacityValidator.validate(budgetCapacity, totalCapacity)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Бюджених місць не може бути більш ніж загальна кількість місць";
            } else {
                errorMessage = "Budget capacity can not overcome total capacity";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }


        String nameEn = request.getParameter("name_en");
        if (fieldValidator.validate(nameEn)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Назва Факультету Англійською не може буди пустим";
            } else {
                errorMessage = "Field Faculty Name English cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String nameUk = request.getParameter("name_uk");
        if (fieldValidator.validate(nameUk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Назва Факультету Українською не може буди пустим";
            } else {
                errorMessage = "Field Faculty Name Ukrainian cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String descriptionEn = request.getParameter("description_en");
        if (fieldValidator.validate(descriptionEn)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Опис Факультету Англійською не може буди пустим";
            } else {
                errorMessage = "Field Faculty Description English cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String descriptionUk = request.getParameter("description_uk");
        if (fieldValidator.validate(descriptionUk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Опис Факультету Українською не може буди пустим";
            } else {
                errorMessage = "Field Faculty Description Ukrainian cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String requiredSubject1En = request.getParameter("requiredSubject1_en");
        if (fieldValidator.validate(requiredSubject1En)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Необхідний Предмет 1 Англійською не може буди пустим";
            } else {
                errorMessage = "Field Required Subject 1 English cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String requiredSubject1Uk = request.getParameter("requiredSubject1_uk");
        if (fieldValidator.validate(requiredSubject1Uk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Необхідний Предмет 1 Українською не може буди пустим";
            } else {
                errorMessage = "Field Required Subject 1 Ukrainian cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String requiredSubject2En = request.getParameter("requiredSubject2_en");
        if (fieldValidator.validate(requiredSubject2En)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Необхідний Предмет 2 Англійською не може буди пустим";
            } else {
                errorMessage = "Field Required Subject 2 English cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String requiredSubject2Uk = request.getParameter("requiredSubject2_uk");
        if (fieldValidator.validate(requiredSubject2Uk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Необхідний Предмет 2 Українською не може буди пустим";
            } else {
                errorMessage = "Field Required Subject 2 Ukrainian cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String requiredSubject3En = request.getParameter("requiredSubject3_en");
        if (fieldValidator.validate(requiredSubject3En)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Необхідний Предмет 3 Англійською  не може буди пустим";
            } else {
                errorMessage = "Field Required Subject 3 English cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }
        String requiredSubject3Uk = request.getParameter("requiredSubject3_uk");
        if (fieldValidator.validate(requiredSubject3Uk)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Поле Необхідний Предмет 3 Українською не може буди пустим";
            } else {
                errorMessage = "Field Required Subject 3 Ukrainian cannot be empty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;
        }


        Faculty faculty = new Faculty();
        faculty.setId(facultyId);
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

        try {
            daoFactory.getFacultyDAO().update(faculty);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        response.sendRedirect("/controller?command=adminWorkspace");


        return "";
    }
}
