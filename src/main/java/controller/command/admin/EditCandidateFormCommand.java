package controller.command.admin;

import Service.CandidateService;
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
        Candidate candidate = candidateService.findById(candidateId);
        request.setAttribute("candidate",candidate);

        return "WEB-INF\\jsp\\admin\\adminEditCandidate.jsp";
    }
}
