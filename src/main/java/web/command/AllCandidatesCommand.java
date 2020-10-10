package web.command;

import DAO.DAOFactory;
import entity.Candidate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AllCandidatesCommand implements Command {



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //  log.debug("Command starts");
        // get menu items list
        List<Candidate> candidatesList = null;
        try {
            candidatesList = (List<Candidate>) daoFactory.getCandidateDAO().getAllCandidatesTO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();//add custom exception!
        }
        // log.trace("Found in DB: menuItemsList --> " + menuItems);

        // sort menu by category
//        Collections.sort(menuItems, new Comparator<MenuItem>() {
//            public int compare(MenuItem o1, MenuItem o2) {
//                return (int)(o1.getCategoryId() - o2.getCategoryId());
//            }
//        });

        // put menu items list to the request
        request.setAttribute("candidatesList", candidatesList);
        //  log.trace("Set the request attribute: menuItems --> " + menuItems);

        //   log.debug("Command finished");
        return "/WEB-INF/jsp/candidates.jsp";
    }
}
