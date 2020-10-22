package controller.command.admin;

import Service.FacultyService;
import model.entity.Faculty;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminWorkspaceCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AdminWorkspaceCommand.class);
    private FacultyService facultyService;


    AdminWorkspaceCommand(FacultyService facultyService){
        this.facultyService=facultyService;
    }
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

            List<Faculty> facultyList = facultyService.getAllFaculties();
            request.setAttribute("facultiesList", facultyList);


        return "/WEB-INF/jsp/admin/adminWorkspace.jsp";
    }
}
