package web.command.admin;

import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCandidateCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));
        daoFactory.getCandidateDAO().deleteCandidate(candidateId);

        return "/controller?command=candidatesList";
    }
}
