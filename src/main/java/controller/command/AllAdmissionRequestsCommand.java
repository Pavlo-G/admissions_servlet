package controller.command;

import Service.AdmissionRequestService;
import exception.DbProcessingException;
import model.entity.AdmissionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllAdmissionRequestsCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AllAdmissionRequestsCommand.class);

    private final AdmissionRequestService admissionRequestService;

    public AllAdmissionRequestsCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        List<AdmissionRequest> admissionRequests = null;
        try {
            admissionRequests = admissionRequestService.findAll();
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while searching for admission requests : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("admissionRequests", admissionRequests);

        return "/WEB-INF/jsp/admissionRequests.jsp";
    }
}
