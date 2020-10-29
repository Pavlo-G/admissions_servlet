package controller.command.admin;

import Service.CandidateService;
import exception.DbProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteCandidateCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteCandidateCommand.class);
    private final CandidateService candidateService;

    public DeleteCandidateCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));
        try {
            candidateService.delete(candidateId);
            LOG.info("candidate with id: {} deleted", candidateId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while delete candidate : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        try {
            response.sendRedirect("/controller?command=candidatesList");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }


        return "";
    }
}
