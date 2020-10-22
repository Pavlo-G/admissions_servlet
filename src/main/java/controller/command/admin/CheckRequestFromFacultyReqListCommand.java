package controller.command.admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class CheckRequestFromFacultyReqListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long admissionRequestId = Long.valueOf(request.getParameter("requestId"));

        try {
            daoFactory.getAdmissionRequestDAO().findAdmissionRequest(admissionRequestId)
                    .ifPresent(x -> request.setAttribute("admissionRequest", x));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return "WEB-INF\\jsp\\admin\\checkRequestFromFacultyRequestList.jsp";
    }
}
