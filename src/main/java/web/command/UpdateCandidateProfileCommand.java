package web.command;

import DAO.DAOFactory;
import entity.CandidateProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateCandidateProfileCommand implements Command {
    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        CandidateProfile candidateProfile =  new CandidateProfile();
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


        return "/controller?command=candidateProfile";
    }
}
