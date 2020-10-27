package controller.command.candidate;

import Service.CandidateService;
import controller.command.admin.UpdateFacultyCommand;
import exception.CandidateNotFoundException;
import model.entity.Candidate;
import model.entity.CandidateProfile;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CandidateProfileEditCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(CandidateProfileEditCommand.class);
    private final CandidateService candidateService;

    public CandidateProfileEditCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        HttpSession session = request.getSession();
        Candidate candidate = (Candidate) session.getAttribute("candidate");

        CandidateProfile candidateProfile;
        try {
            candidateProfile = candidateService.getCandidateProfile(candidate)
                    .orElseThrow(() -> new CandidateNotFoundException("Candidate Profile  not found"));

        } catch (CandidateNotFoundException ex) {
            LOG.error("Can not find candidate profile : {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("candidateProfile", candidateProfile);
        return "WEB-INF/jsp/candidate/candidate-profile-edit.jsp";

    }
}
