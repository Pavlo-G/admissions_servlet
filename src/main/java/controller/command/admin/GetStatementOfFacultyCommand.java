package controller.command.admin;

import Service.AdmissionRequestService;
import Service.FacultyService;
import exception.DbProcessingException;
import exception.FacultyNotFoundException;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetStatementOfFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(GetStatementOfFacultyCommand.class);
    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public GetStatementOfFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty;
        try {
            faculty = facultyService.findById(facultyId);

        } catch (FacultyNotFoundException ex) {
            LOG.error("Can not find faculty: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while preparing list of admission requests: {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        List<AdmissionRequest> admissionRequestsList = admissionRequestService.getSortedListOfRequestForFaculty(faculty);

        request.setAttribute("admissionRequestsList", admissionRequestsList);
        request.setAttribute("facultyId", facultyId);

        return "/WEB-INF/jsp/admin/statementOfFaculty.jsp";
    }


}
