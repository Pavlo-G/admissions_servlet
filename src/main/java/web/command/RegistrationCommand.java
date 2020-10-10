package web.command;

import DAO.DAOFactory;
import entity.Candidate;
import entity.CandidateProfile;
import entity.CandidateStatus;
import entity.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Candidate candidate= new Candidate();
        candidate.setUsername( request.getParameter("username"));
        candidate.setPassword( request.getParameter("password"));
        candidate.setRole(Role.USER);
        candidate.setCandidateStatus(CandidateStatus.ACTIVE);
        daoFactory.getCandidateDAO().insertCandidate(candidate);

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

       daoFactory.getCandidateDAO().insertCandidateProfile(candidateProfile);


        return "/WEB-INF/jsp/login.jsp";
    }
}
