package controller.command.admin;

import Service.FacultyService;
import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowRequestsListOfFacultyCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(ShowRequestsListOfFacultyCommand.class);
    private final FacultyService facultyService;

    public ShowRequestsListOfFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty =  facultyService.findById(facultyId);

        request.setAttribute("faculty", faculty);
        return "/WEB-INF/jsp/admin/admin-faculty-requests.jsp";
    }
}
