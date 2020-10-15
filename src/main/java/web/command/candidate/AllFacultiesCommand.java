package web.command.candidate;


import entity.Faculty;
import web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllFacultiesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String sortBy;
        String sortDir;
        int pageNumber;
        int itemsPerPager;

        if ((sortBy = request.getParameter("sortBy")) == null) {
            sortBy = "name";
        }

        if ((sortDir = request.getParameter("sortDir")) == null) {
            sortDir = "ASC";
        }

        if (request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        } else {
            pageNumber = 1;
        }
        if (request.getParameter("size") != (null)) {
            itemsPerPager = Integer.parseInt(request.getParameter("size"));
        } else {
            itemsPerPager = 2;
        }


        List<Faculty> facultiesList = null;
        try {
            facultiesList = daoFactory.getFacultyDAO().getAllFaculties2(sortBy, sortDir, pageNumber, itemsPerPager);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        int totalPages = 1;
//        if (totalFaculties % itemsPerPager == 0) {
//            totalPages = totalFaculties / itemsPerPager;
//        } else {
//            totalPages = totalFaculties / itemsPerPager + 1;
//        }


        request.setAttribute("facultiesList", facultiesList);
//        request.setAttribute("totalPages", totalPages);

        return "/WEB-INF/jsp/candidate/faculties.jsp";
    }
}
