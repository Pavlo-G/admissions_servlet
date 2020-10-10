package web.command;

import DAO.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitRequestCommand implements Command {
        private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
        @Override
        public String execute(HttpServletRequest request, HttpServletResponse response) {

//            request.setAttribute("requestsList", );
            return "/WEB-INF/jsp/candidate-requests.jsp";
        }
}
