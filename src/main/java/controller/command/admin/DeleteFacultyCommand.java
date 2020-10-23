package controller.command.admin;

import Service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(DeleteFacultyCommand.class);
    private final FacultyService facultyService;

    public DeleteFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

           facultyService.delete(facultyId);
            LOG.info("Faculty with id {} deleted", facultyId);



        response.sendRedirect("/controller?command=adminWorkspace");


        return "";
    }
}
