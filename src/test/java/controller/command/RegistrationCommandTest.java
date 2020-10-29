package controller.command;

import Service.CandidateService;
import listener.SessionListener;
import model.entity.Candidate;
import model.entity.CandidateProfile;
import model.entity.CandidateStatus;
import model.entity.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import setup.SetUpDbTest;
import utils.validation.RegistrationValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RegistrationCommandTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);

    RegistrationCommand registrationCommand = new RegistrationCommand(new CandidateService());
    RegistrationValidator registrationValidator= mock(RegistrationValidator.class);


    @Before
    public void setUp() throws Exception {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown() throws Exception {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void normalRegistration() throws IOException, ServletException {

        Map<String,String[]> parameters= new HashMap<>();
        parameters.put("username", new String[]{"newUser"});
        parameters.put("password", new String[]{"1"});
        parameters.put("email", new String[]{"newUser@user.com"});
        parameters.put("firstName", new String[]{"testUser"});
        parameters.put("lastName", new String[]{"testUser"});
        parameters.put("address", new String[]{"testUser"});
        parameters.put("city", new String[]{"testUser"});
        parameters.put("region", new String[]{"testUser"});
        parameters.put("school", new String[]{"testUser"});
        parameters.put("phoneNumber", new String[]{"123-123-1234"});

       when(request.getParameterMap()).thenReturn(parameters);
        when(request.getParameter("username")).thenReturn("newUser");
        when(request.getParameter("password")).thenReturn("1");
        when(request.getParameter("email")).thenReturn("newUser@user.com");
        when(request.getParameter("firstName")).thenReturn("testUser");
        when(request.getParameter("lastName")).thenReturn("testUser");
        when(request.getParameter("address")).thenReturn("test");
        when(request.getParameter("city")).thenReturn("test");
        when(request.getParameter("region")).thenReturn("test");
        when(request.getParameter("school")).thenReturn("test");
        when(request.getParameter("phoneNumber")).thenReturn("123-123-1234");
        when(request.getPart("file")).thenReturn(mock(Part.class));

        when(session.getAttribute("lang")).thenReturn("en");
        when(request.getSession()).thenReturn(session);


        when(registrationValidator.validateRegistration(anyMap())).thenReturn(new HashMap());

        registrationCommand.execute(request, response);

        verify(response, times(1)).sendRedirect("/controller?command=loginForm");
        CandidateService candidateService= new CandidateService();
        Assert.assertEquals(3,candidateService.findAll().size() );

    }
}