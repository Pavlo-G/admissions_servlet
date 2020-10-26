package controller.command.admin;

import Service.CandidateService;
import controller.command.Command;
import exception.DbProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditCandidateCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(EditCandidateCommand.class);
    private final CandidateService candidateService;

    public EditCandidateCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long candidateId = Long.valueOf(request.getParameter("candidateId"));
        String role = request.getParameter("role");
        String candidateStatus = request.getParameter("candidateStatus");
        try {
            candidateService.update(role, candidateStatus, candidateId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while updating candidate : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        response.sendRedirect("/controller?command=candidatesList");

        return "";

    }
}
