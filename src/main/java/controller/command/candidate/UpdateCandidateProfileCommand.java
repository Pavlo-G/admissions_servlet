package controller.command.candidate;


import model.entity.CandidateProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateCandidateProfileCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(UpdateCandidateProfileCommand.class);

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
            daoFactory.getCandidateDAO().updateCandidateProfile(candidateProfile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        response.sendRedirect("/controller?command=candidateProfile");

        return "";
    }
}
