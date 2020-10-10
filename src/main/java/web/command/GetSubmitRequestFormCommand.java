package web.command;

import entity.Candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSubmitRequestFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Candidate candidate = (Candidate) session.getAttribute("candidate");



        request.setAttribute("candidate",candidate );
        request.setAttribute("facultyId",request.getParameter("facultyId") );
        request.setAttribute("facultyName",request.getParameter("facultyName") );

        return "/WEB-INF/jsp/candidate/candidate-submit-request-form.jsp";
    }
}
