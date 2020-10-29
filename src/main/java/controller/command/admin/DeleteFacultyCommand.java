package controller.command.admin;

import Service.FacultyService;
import exception.DbProcessingException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        try {
            facultyService.delete(facultyId);
            LOG.info("Faculty with id {} deleted", facultyId);
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while delete faculty : {}", e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }


        try {
            response.sendRedirect("/controller?command=adminWorkspace");
        } catch (IOException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }


        return "";
    }
}
