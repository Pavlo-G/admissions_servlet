package web.command;

import DAO.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginFormCommand implements Command {


    private static final DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "/WEB-INF/jsp/login.jsp";
    }
}


