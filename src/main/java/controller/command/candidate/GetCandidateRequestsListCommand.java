package controller.command.candidate;

import Service.AdmissionRequestService;
import model.entity.AdmissionRequest;
import model.entity.Candidate;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
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
        List<AdmissionRequest> admissionRequestList = admissionRequestService.selectAdmissionRequestsForCandidateWithId(candidate.getId());

        request.setAttribute("requestsList", admissionRequestList);
        return "/WEB-INF/jsp/candidate/candidate-requests.jsp";
    }
}
