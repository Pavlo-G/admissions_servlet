package controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCandidateCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteCandidateCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));

        try {
            daoFactory.getCandidateDAO().deleteCandidate(candidateId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        LOG.info("candidate with id: {} deleted", candidateId);


            response.sendRedirect("/controller?command=candidatesList");


        return "";
    }
}
