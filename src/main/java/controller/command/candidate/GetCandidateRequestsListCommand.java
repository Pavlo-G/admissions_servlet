package controller.command.candidate;

import Service.AdmissionRequestService;
import controller.command.Command;
import exception.DbProcessingException;
import model.entity.AdmissionRequest;
import model.entity.Candidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCandidateRequestsListCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(GetCandidateRequestsListCommand.class);
    private final AdmissionRequestService admissionRequestService;

    public GetCandidateRequestsListCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Candidate candidate = (Candidate) request.getSession().getAttribute("candidate");
        try {
            request.setAttribute("requestsList", admissionRequestService.selectAdmissionRequestsForCandidateWithId(candidate.getId()));
            return "/WEB-INF/jsp/candidate/candidate-requests.jsp";
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while getting requests for candidate {} : {}",candidate.getUsername(), e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
    }
}
