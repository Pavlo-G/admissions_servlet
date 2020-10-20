package controller.command.candidate;

import model.entity.Candidate;
import model.entity.CandidateProfile;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CandidateProfileCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Candidate candidate = (Candidate) session.getAttribute("candidate");

        CandidateProfile candidateProfile =
                null;
        try {
            candidateProfile = daoFactory.getCandidateDAO().getCandidateProfile(candidate)
                    .orElseThrow(() -> new Exception("CANDIDATE PROFILE NOT FOUND"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("candidateProfile", candidateProfile);
        return "WEB-INF/jsp/candidate/candidate-profile.jsp";
    }
}
