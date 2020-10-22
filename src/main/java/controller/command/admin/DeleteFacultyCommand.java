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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        try {
            daoFactory.getFacultyDAO().deleteFaculty(facultyId);
            LOG.info("Faculty with id {} deleted",facultyId);
        } catch (SQLException throwables) {
            LOG.info(" Can not delete faculty with id {}",facultyId);

        }


        response.sendRedirect("/controller?command=adminWorkspace");


        return "";
    }
}
