package web.command.candidate;

import entity.Candidate;
import entity.CandidateProfile;
import entity.Faculty;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSubmitRequestFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Candidate candidate = (Candidate) session.getAttribute("candidate");
        CandidateProfile candidateProfile = daoFactory.getCandidateDAO().getCandidateProfile(candidate).get();
        candidate.setCandidateProfile(candidateProfile);
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = daoFactory.getFacultyDAO().findFaculty(facultyId);
        request.setAttribute("candidate", candidate);
        request.setAttribute("faculty", faculty);


        return "/WEB-INF/jsp/candidate/candidate-submit-request-form.jsp";
    }
}
