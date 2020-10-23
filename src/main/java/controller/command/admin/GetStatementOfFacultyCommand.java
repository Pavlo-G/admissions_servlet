package controller.command.admin;

import Service.AdmissionRequestService;
import Service.FacultyService;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetStatementOfFacultyCommand implements Command {
    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public GetStatementOfFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = facultyService.findById(facultyId);
        List<AdmissionRequest> admissionRequestsList = admissionRequestService.getSortedListOfRequestForFaculty(faculty);

        request.setAttribute("admissionRequestsList", admissionRequestsList);
        request.setAttribute("facultyId", facultyId);

        return "/WEB-INF/jsp/admin/statementOfFaculty.jsp";
    }



}
