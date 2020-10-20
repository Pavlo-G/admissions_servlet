package controller.command.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteFacultyCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        try {
            daoFactory.getFacultyDAO().deleteFaculty(Long.valueOf(request.getParameter("facultyId")));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            response.sendRedirect("/controller?command=adminWorkspace");
        } catch (IOException e) {
            LOG.error("Bad Request!", e);
        }

        return "";
    }
}
