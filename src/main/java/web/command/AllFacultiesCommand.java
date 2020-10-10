package web.command;


import DAO.DAOFactory;
import entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllFacultiesCommand implements Command{
    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
      //  log.debug("Command starts");
        // get menu items list
        List<Faculty> facultiesList= (List<Faculty>) daoFactory.getFacultyDAO().getAllFacultiesTO();
       // log.trace("Found in DB: menuItemsList --> " + menuItems);

        // sort menu by category
//        Collections.sort(menuItems, new Comparator<MenuItem>() {
//            public int compare(MenuItem o1, MenuItem o2) {
//                return (int)(o1.getCategoryId() - o2.getCategoryId());
//            }
//        });

        request.setAttribute("facultiesList", facultiesList);
        return "/WEB-INF/jsp/faculties.jsp";
    }
}
