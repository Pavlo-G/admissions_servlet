package controller.command.candidate;

import Service.AdmissionRequestService;
import Service.CandidateService;
import Service.FacultyService;
import model.entity.Candidate;
import model.entity.CandidateProfile;
import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class GetSubmitRequestFormCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(GetSubmitRequestFormCommand.class);
    private final FacultyService facultyService;
    public GetSubmitRequestFormCommand(FacultyService facultyService) {
        this.facultyService = facultyService;

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Candidate candidate = (Candidate) session.getAttribute("candidate");

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = facultyService.findById(facultyId);


        request.setAttribute("candidate", candidate);
        request.setAttribute("faculty", faculty);


        return "/WEB-INF/jsp/candidate/candidate-submit-request-form.jsp";
    }
}
