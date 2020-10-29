package controller.command.candidate;


import Service.CandidateService;
import controller.command.Command;
import exception.CandidateNotFoundException;
import exception.DbProcessingException;
import model.entity.CandidateProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateCandidateProfileCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(UpdateCandidateProfileCommand.class);

    private final CandidateService candidateService;

    public UpdateCandidateProfileCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


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


        String fileName = null;
        try {
            fileName = candidateService.saveFile(request);
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        if (fileName.isEmpty()) {
            CandidateProfile oldCp = candidateService.getCandidateProfileById(Long.valueOf(request.getParameter("candidateProfileId")))
                    .orElseThrow(() -> new CandidateNotFoundException("Can not find profile"));
            candidateProfile.setFileName(oldCp.getFileName());
        } else {
            candidateProfile.setFileName(fileName);
        }

        try {
            candidateService.updateCandidateProfile(candidateProfile);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while updating candidate profile : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        try {
            response.sendRedirect("/controller?command=candidateProfile");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";
    }
}
