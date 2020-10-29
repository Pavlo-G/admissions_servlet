package controller.command;

import model.DAO.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FunctionalInterface
public interface Command {

    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);

    String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;


}
