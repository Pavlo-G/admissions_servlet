package web.command;

import DAO.DAOFactory;
import entity.Candidate;
import entity.CandidateProfile;
import entity.CandidateStatus;
import entity.Role;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationCommand implements Command {



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        Candidate candidate= new Candidate();
        candidate.setUsername( request.getParameter("username"));
        candidate.setPassword( BCrypt.hashpw(request.getParameter("password"),BCrypt.gensalt(12)));
        candidate.setRole(Role.USER);
        candidate.setCandidateStatus(CandidateStatus.ACTIVE);


        CandidateProfile candidateProfile =  new CandidateProfile();
        candidateProfile.setEmail(request.getParameter("email"));
        candidateProfile.setFirstName(request.getParameter("firstName"));
        candidateProfile.setLastName(request.getParameter("lastName"));
        candidateProfile.setAddress(request.getParameter("address"));
        candidateProfile.setCity(request.getParameter("city"));
        candidateProfile.setRegion(request.getParameter("region"));
        candidateProfile.setSchool(request.getParameter("school"));
        candidateProfile.setPhoneNumber(request.getParameter("phoneNumber"));
        candidateProfile.setCandidate(candidate);

        try {
            daoFactory.getCandidateDAO().insertCandidate(candidate, candidateProfile);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "/WEB-INF/jsp/login.jsp";
    }
}
