package controller.command;

import model.entity.AdmissionRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AllAdmissionRequests implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<AdmissionRequest> admissionRequests = null;
        try {
            admissionRequests = daoFactory.getAdmissionRequestDAO().findAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.setAttribute("admissionRequests", admissionRequests);

        return "/WEB-INF/jsp/admissionRequests.jsp";
    }
}
