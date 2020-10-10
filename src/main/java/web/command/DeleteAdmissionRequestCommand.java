package web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteAdmissionRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long admissionRequestId = Long.valueOf(request.getParameter("admissionRequestId"));

        try {
            daoFactory.getAdmissionRequestDAO().deleteAdmissionRequest(admissionRequestId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();// add custom exception!
        }

        return "/controller?command=getCandidateRequestsList";
    }
}
