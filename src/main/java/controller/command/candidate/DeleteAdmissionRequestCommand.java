package controller.command.candidate;

import Service.AdmissionRequestService;
import controller.command.Command;
import model.entity.AdmissionRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteAdmissionRequestCommand implements Command {
    private final AdmissionRequestService admissionRequestService;

    public DeleteAdmissionRequestCommand(AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long admissionRequestId = Long.valueOf(request.getParameter("admissionRequestId"));

            admissionRequestService.delete(admissionRequestId);

        response.sendRedirect("/controller?command=getCandidateRequestsList");

        return "";
    }
}
