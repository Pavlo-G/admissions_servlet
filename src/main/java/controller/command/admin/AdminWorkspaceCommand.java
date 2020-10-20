package controller.command.admin;

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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Faculty> facultyList = daoFactory.getFacultyDAO().getAllFaculties();
            request.setAttribute("facultiesList", facultyList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "/WEB-INF/jsp/admin/adminWorkspace.jsp";
    }
}
