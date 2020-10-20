package controller.command.candidate;

import model.entity.Candidate;
import model.entity.CandidateProfile;
import model.entity.Faculty;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class GetSubmitRequestFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Candidate candidate = (Candidate) session.getAttribute("candidate");


        CandidateProfile candidateProfile = null;
        try {
            candidateProfile = daoFactory.getCandidateDAO().getCandidateProfile(candidate).get();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        candidate.setCandidateProfile(candidateProfile);
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);
        request.setAttribute("candidate", candidate);
        request.setAttribute("faculty", faculty);


        return "/WEB-INF/jsp/candidate/candidate-submit-request-form.jsp";
    }
}
