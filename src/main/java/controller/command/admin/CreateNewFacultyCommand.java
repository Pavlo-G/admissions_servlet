package controller.command.admin;

import Service.FacultyService;
import model.entity.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;
import utils.util.FacultyDTOMapper;
import utils.validation.FacultyValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CreateNewFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(CreateNewFacultyCommand.class);

    FacultyService facultyService;

    public CreateNewFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String lang = (String) request.getSession().getAttribute("lang");
        String errorMessage = null;
        String forward = "WEB-INF/jsp/admin/adminCreateFacultyFrom.jsp";

        FacultyDTOMapper facultyDTOMapper = new FacultyDTOMapper();

        String nameEn = request.getParameter("nameEn");
        String nameUk = request.getParameter("nameUk");
        String descriptionEn = request.getParameter("descriptionEn");
        String descriptionUk = request.getParameter("descriptionUk");
        String totalCapacity = request.getParameter("totalCapacity");
        String budgetCapacity = request.getParameter("budgetCapacity");
        String requiredSubject1En = request.getParameter("requiredSubject1En");
        String requiredSubject1Uk = request.getParameter("requiredSubject1Uk");
        String requiredSubject2En = request.getParameter("requiredSubject2En");
        String requiredSubject2Uk = request.getParameter("requiredSubject2Uk");
        String requiredSubject3En = request.getParameter("requiredSubject3En");
        String requiredSubject3Uk = request.getParameter("requiredSubject3Uk");


        FacultyValidator facultyValidator = new FacultyValidator(lang);
        Map<String, String> errors = facultyValidator.validateFaculty(nameEn, nameUk, descriptionEn, descriptionUk,
                budgetCapacity, totalCapacity, requiredSubject1En, requiredSubject1Uk,
                requiredSubject2En, requiredSubject2Uk, requiredSubject3En, requiredSubject3Uk);
        if (!errors.isEmpty()) {
            request.getParameterMap().entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()[0]));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return forward;
        }

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


        facultyService.create(faculty);
        LOG.info("Faculty created");


        forward = "";
        response.sendRedirect("/controller?command=adminWorkspace");


        return forward;
    }
}
