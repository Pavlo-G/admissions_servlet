package controller.command.admin;


import model.entity.AdmissionRequestStatus;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeAdmissionRequestStatusCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AdmissionRequestStatus newAdmissionRequestStatus = AdmissionRequestStatus.valueOf(request.getParameter("admissionRequestStatus"));
        Long admissionRequestId = Long.valueOf(request.getParameter("id"));
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        try {
            daoFactory.getAdmissionRequestDAO().changeAdmissionRequestStatus(admissionRequestId, newAdmissionRequestStatus);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        response.sendRedirect("/controller?command=showRequestsListOfFaculty&facultyId="+facultyId);

        return "";
    }
}
