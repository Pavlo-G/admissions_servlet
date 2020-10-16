package web.command.admin;

import entity.Candidate;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

public class EditCandidateFormCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long candidateId = Long.valueOf(request.getParameter("candidateId"));

        try {
            Optional<Candidate> candidate = daoFactory.getCandidateDAO().findCandidateById(candidateId);
            candidate.ifPresent(c->request.setAttribute("candidate",c));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "WEB-INF\\jsp\\admin\\adminEditCandidate.jsp";
    }
}
