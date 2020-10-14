package web.command.candidate;


import entity.Faculty;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AllFacultiesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Faculty> facultiesList = null;
        try {
            facultiesList =  daoFactory.getFacultyDAO().getAllFaculties();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        request.setAttribute("facultiesList", facultiesList);
        return "/WEB-INF/jsp/candidate/faculties.jsp";
    }
}