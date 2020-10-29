package controller.command;

import Service.CandidateService;
import com.sun.deploy.net.HttpRequest;
import listener.SessionListener;
import model.DAO.mysql.ConnectionPoolHolder;
import model.entity.Candidate;
import model.entity.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginCommandTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    LoginCommand loginCommand = new LoginCommand(new CandidateService());

    @Before
    public void setUp() {
        SetUpDbTest.setUpDataBase();
        SessionListener.getCandidatesInSessions().clear();
    }

    @After
    public void tearDown() {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void loginNormalUser() throws IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("1");
        when(session.getAttribute("lang")).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("123");


        loginCommand.execute(request, response);
        Assert.assertTrue(SessionListener.getCandidatesInSessions().containsKey("user"));
        verify(response, times(1)).sendRedirect("/controller?command=facultiesList");

    }

    @Test
    public void loginNormalAdmin() throws IOException {
        when(request.getParameter("username")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("1");
        when(session.getAttribute("lang")).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("123");


        loginCommand.execute(request, response);
        Assert.assertTrue(SessionListener.getCandidatesInSessions().containsKey("admin"));
        verify(response, times(1)).sendRedirect("/controller?command=adminWorkspace");

    }

    @Test
    public void loginUserBadCredentialsUsername() throws IOException {
        when(request.getParameter("username")).thenReturn("xxxxxx");
        when(request.getParameter("password")).thenReturn("1");
        when(session.getAttribute("lang")).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("123");


        String forward = loginCommand.execute(request, response);
        Assert.assertFalse(SessionListener.getCandidatesInSessions().containsKey("xxxxxx"));
        verify(response, times(0)).sendRedirect("/controller?command=adminWorkspace");
        verify(response, times(0)).sendRedirect("/controller?command=facultiesList");
        verify(request, times(1)).setAttribute("errorMessage", "Candidate not found!");
        Assert.assertEquals("WEB-INF\\jsp\\login.jsp", forward);

    }

    @Test
    public void loginUserBadCredentialsPasswordEn() throws IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("1111");
        when(session.getAttribute("lang")).thenReturn("en");
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("123");


        String forward = loginCommand.execute(request, response);
        Assert.assertFalse(SessionListener.getCandidatesInSessions().containsKey("user"));
        verify(response, times(0)).sendRedirect("/controller?command=adminWorkspace");
        verify(response, times(0)).sendRedirect("/controller?command=facultiesList");
        verify(request, times(1)).setAttribute("errorMessage", "Cannot find user with such login/password!");
        Assert.assertEquals("WEB-INF\\jsp\\login.jsp", forward);

    }


    @Test
    public void loginUserBadCredentialsPasswordUk() throws IOException {
        when(request.getParameter("username")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("1111");
        when(session.getAttribute("lang")).thenReturn("uk");
        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("123");


        String forward = loginCommand.execute(request, response);
        Assert.assertFalse(SessionListener.getCandidatesInSessions().containsKey("user"));
        verify(response, times(0)).sendRedirect("/controller?command=adminWorkspace");
        verify(response, times(0)).sendRedirect("/controller?command=facultiesList");
        verify(request, times(1)).setAttribute("errorMessage", "Не можу знайти юзера з таким логіном/паролем!");
        Assert.assertEquals("WEB-INF\\jsp\\login.jsp", forward);

    }
}