package web.command.admin;

import entity.Candidate;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AllCandidatesCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Candidate> candidatesList = null;
        try {
            candidatesList = daoFactory.getCandidateDAO().getAllCandidates();
        } catch (SQLException throwables) {
            throwables.printStackTrace();//add custom exception!
        }

        request.setAttribute("candidatesList", candidatesList);
        return "/WEB-INF/jsp/admin/adminAllCandidatesList.jsp";
    }
}
