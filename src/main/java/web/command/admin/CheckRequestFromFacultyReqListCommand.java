package web.command.admin;

import entity.AdmissionRequest;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckRequestFromFacultyReqListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long admissionRequestId = Long.valueOf(request.getParameter("requestId"));

        AdmissionRequest admissionRequest =
                daoFactory.getAdmissionRequestDAO().findAdmissionRequest(admissionRequestId);


        return null;
    }
}
