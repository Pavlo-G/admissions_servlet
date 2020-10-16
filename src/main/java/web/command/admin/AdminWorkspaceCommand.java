package web.command.admin;

import entity.Faculty;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminWorkspaceCommand implements Command {
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
