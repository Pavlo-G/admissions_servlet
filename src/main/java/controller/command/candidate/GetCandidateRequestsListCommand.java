package controller.command.candidate;

import model.entity.AdmissionRequest;
import model.entity.Candidate;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCandidateRequestsListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Candidate candidate = (Candidate) request.getSession().getAttribute("candidate");
        List<AdmissionRequest> admissionRequestList =
                daoFactory.getAdmissionRequestDAO().selectAdmissionRequestsForCandidateWithId(candidate.getId());

        request.setAttribute("requestsList", admissionRequestList);
        return "/WEB-INF/jsp/candidate/candidate-requests.jsp";
    }
}
