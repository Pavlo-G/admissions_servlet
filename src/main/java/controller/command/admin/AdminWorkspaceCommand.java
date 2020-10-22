package controller.command.admin;

import Service.FacultyService;
import exception.DbProcessingException;
import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminWorkspaceCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AdminWorkspaceCommand.class);
    private final FacultyService facultyService;


    public AdminWorkspaceCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Faculty> facultyList = null;
        try {
            facultyList = facultyService.findAll();
        } catch (DbProcessingException e) {
            LOG.error("Error occurred while getting all faculties list: {}",e.getMessage());
            request.setAttribute("errorMessage", e.getMessage());
            return "/WEB-INF/jsp/errorPage.jsp";
        }
        request.setAttribute("facultiesList", facultyList);


        return "/WEB-INF/jsp/admin/adminWorkspace.jsp";
    }
}
