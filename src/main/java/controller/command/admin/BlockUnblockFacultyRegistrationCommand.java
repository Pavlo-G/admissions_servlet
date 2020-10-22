package controller.command.admin;

import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class BlockUnblockFacultyRegistrationCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(BlockUnblockFacultyRegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String action = request.getParameter("action");
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        try {
            daoFactory.getFacultyDAO().changeAdmissionOpenStatus(action, facultyId);
            LOG.info("Admission {} for faculty with id: {}",action,facultyId);
        } catch (SQLException exp) {
            LOG.error("error occurred while changing admissions status: {}",exp.getMessage());
        }

            response.sendRedirect("/controller?command=adminWorkspace");

        return "";
    }
}
