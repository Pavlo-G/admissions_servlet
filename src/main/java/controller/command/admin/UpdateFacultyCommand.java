package controller.command.admin;

import Service.FacultyService;
import exception.DbProcessingException;
import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.util.FacultyDTOMapper;
import utils.validation.FacultyCapacityValidator;
import utils.validation.FacultyValidator;
import utils.validation.FieldValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(UpdateFacultyCommand.class);

    FacultyService facultyService;

    public UpdateFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String lang = (String) request.getSession().getAttribute("lang");

        Map<String, String> facultyParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));


        FacultyValidator facultyValidator = new FacultyValidator(lang);
        Map<String, String> errors = facultyValidator.validateFaculty(facultyParameters);
        if (!errors.isEmpty()) {
            facultyParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/admin/adminEditFaculty.jsp";
        }
        FacultyDTOMapper facultyDTOMapper = new FacultyDTOMapper();
        Faculty faculty = facultyDTOMapper.getFaculty(facultyParameters);
        faculty.setId(Long.parseLong(facultyParameters.get("facultyId")));

        try {
            facultyService.update(faculty);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while updating faculty : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        response.sendRedirect("/controller?command=adminWorkspace");

        return "";
    }
}
