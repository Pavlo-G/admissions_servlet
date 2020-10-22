package controller.command.admin;

import Service.CandidateService;
import exception.DbProcessingException;
import model.entity.Candidate;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AllCandidatesCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AllCandidatesCommand.class);
    private final CandidateService candidateService;

    public AllCandidatesCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Candidate> candidatesList = null;
        try {
            candidatesList = candidateService.findAll();
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while getting candidates list: {}",e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        request.setAttribute("candidatesList", candidatesList);
        return "/WEB-INF/jsp/admin/adminAllCandidatesList.jsp";
    }
}
