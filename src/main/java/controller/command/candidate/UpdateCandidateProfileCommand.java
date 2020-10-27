package controller.command.candidate;


import Service.CandidateService;
import exception.DbProcessingException;
import exception.FacultyNotFoundException;
import model.entity.CandidateProfile;
import model.entity.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateCandidateProfileCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(UpdateCandidateProfileCommand.class);

    private final CandidateService candidateService;

    public UpdateCandidateProfileCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setId(Long.valueOf(request.getParameter("candidateProfileId")));
        candidateProfile.setEmail(request.getParameter("email"));
        candidateProfile.setFirstName(request.getParameter("firstName"));
        candidateProfile.setLastName(request.getParameter("lastName"));
        candidateProfile.setAddress(request.getParameter("address"));
        candidateProfile.setCity(request.getParameter("city"));
        candidateProfile.setRegion(request.getParameter("region"));
        candidateProfile.setSchool(request.getParameter("school"));
        candidateProfile.setPhoneNumber(request.getParameter("phoneNumber"));

        try {
            candidateService.updateCandidateProfile(candidateProfile);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while updating candidate profile : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        response.sendRedirect("/controller?command=candidateProfile");

        return "";
    }
}
