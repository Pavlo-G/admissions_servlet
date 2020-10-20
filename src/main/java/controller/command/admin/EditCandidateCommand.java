package controller.command.admin;

import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCandidateCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(EditCandidateCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));
        String role = request.getParameter("role");
        String candidateStatus = request.getParameter("candidateStatus");


        daoFactory.getCandidateDAO().updateCandidate(role, candidateStatus, candidateId);


        try {
            response.sendRedirect("/controller?command=candidatesList");
        } catch (IOException e) {
            LOG.error("Bad Request!", e);
        }

        return "";

    }
}
