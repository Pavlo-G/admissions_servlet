package web.command;


import DAO.DAOFactory;
import entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AllFacultiesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        // get menu items list
        List<Faculty> facultiesList= null;
        try {
            facultiesList = (List<Faculty>) daoFactory.getFacultyDAO().getAllFacultiesTO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        // sort menu by category
//        Collections.sort(menuItems, new Comparator<MenuItem>() {
//            public int compare(MenuItem o1, MenuItem o2) {
//                return (int)(o1.getCategoryId() - o2.getCategoryId());
//            }
//        });

        request.setAttribute("facultiesList", facultiesList);
        return "/WEB-INF/jsp/candidate/faculties.jsp";
    }
}
