package controller.command.admin;

import Service.CandidateService;
import exception.CanNotFindRequestById;
import exception.CandidateNotFoundException;
import exception.DbProcessingException;
import model.entity.Candidate;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

public class EditCandidateFormCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(EditCandidateCommand.class);
    private final CandidateService candidateService;

    public EditCandidateFormCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long candidateId = Long.valueOf(request.getParameter("candidateId"));
        Candidate candidate;
        try {
            candidate = candidateService.findById(candidateId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while searching for candidate : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        } catch (CandidateNotFoundException ex) {
            LOG.error("Can not find candidate: {}", ex.getMessage());
            request.setAttribute("errorMessage", ex.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        request.setAttribute("candidate", candidate);

        return "WEB-INF\\jsp\\admin\\adminEditCandidate.jsp";
    }
}
