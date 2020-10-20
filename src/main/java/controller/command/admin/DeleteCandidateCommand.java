package controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCandidateCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteCandidateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));

        daoFactory.getCandidateDAO().deleteCandidate(candidateId);
        LOG.info("candidate with id: {} deleted", candidateId);

        try {
            response.sendRedirect("/controller?command=candidatesList");
        } catch (IOException e) {
            LOG.error("Bad Request!", e);
        }

        return "";
    }
}
