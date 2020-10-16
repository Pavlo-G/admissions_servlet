package web.command.admin;

import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCandidateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));
        String role = request.getParameter("role");
        String candidateStatus = request.getParameter("candidateStatus");


        daoFactory.getCandidateDAO().updateCandidate(role, candidateStatus, candidateId);

        return "/controller?command=candidatesList";

    }
}
