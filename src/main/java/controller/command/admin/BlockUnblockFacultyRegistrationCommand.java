package controller.command.admin;

import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUnblockFacultyRegistrationCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(BlockUnblockFacultyRegistrationCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String action = request.getParameter("action");
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        daoFactory.getFacultyDAO().changeAdmissionOpenStatus(action, facultyId);

        try {
            response.sendRedirect("/controller?command=adminWorkspace");
        } catch (IOException e) {
            LOG.error("Bad Request!", e);
        }
        return "";
    }
}
