package web.command.admin;

import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckRequestFromFacultyReqListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long admissionRequestId = Long.valueOf(request.getParameter("requestId"));

        daoFactory.getAdmissionRequestDAO().findAdmissionRequest(admissionRequestId)
                .ifPresent(x -> request.setAttribute("admissionRequest", x));


        return "WEB-INF\\jsp\\admin\\checkRequestFromFacultyRequestList.jsp";
    }
}
