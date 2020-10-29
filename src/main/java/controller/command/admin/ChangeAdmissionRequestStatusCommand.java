package controller.command.admin;


import Service.AdmissionRequestService;
import exception.DbProcessingException;
import model.entity.AdmissionRequestStatus;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeAdmissionRequestStatusCommand implements Command {

    static final Logger LOG = LoggerFactory.getLogger(ChangeAdmissionRequestStatusCommand.class);

    private final  AdmissionRequestService admissionRequestService;

    public ChangeAdmissionRequestStatusCommand(AdmissionRequestService admissionRequestService){
        this.admissionRequestService= admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AdmissionRequestStatus newAdmissionRequestStatus = AdmissionRequestStatus.valueOf(request.getParameter("admissionRequestStatus"));
        Long admissionRequestId = Long.valueOf(request.getParameter("id"));
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
           admissionRequestService.changeAdmissionRequestStatus(admissionRequestId, newAdmissionRequestStatus);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while changing request status: {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        try {
            response.sendRedirect("/controller?command=showRequestsListOfFaculty&facultyId="+facultyId);
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }

        return "";
    }
}
