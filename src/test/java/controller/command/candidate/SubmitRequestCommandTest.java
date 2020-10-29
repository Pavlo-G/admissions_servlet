package controller.command.candidate;

import Service.AdmissionRequestService;
import Service.FacultyService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SubmitRequestCommandTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    SubmitRequestCommand submitRequestCommand = new SubmitRequestCommand(new AdmissionRequestService(), new FacultyService());

    @Before
    public void setUp() {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void execute() throws IOException {

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put("facultyId", new String[]{"2"});
        parameters.put("candidateId", new String[]{"2"});
        parameters.put("requiredSubject1Grade", new String[]{"6"});
        parameters.put("requiredSubject2Grade", new String[]{"7"});
        parameters.put("requiredSubject3Grade", new String[]{"8"});

        when(request.getParameterMap()).thenReturn(parameters);
        when(request.getParameter("facultyId")).thenReturn("2");
        when(request.getParameter("candidateId")).thenReturn("2");
        when(request.getParameter("requiredSubject1Grade")).thenReturn("6");
        when(request.getParameter("requiredSubject2Grade")).thenReturn("7");
        when(request.getParameter("requiredSubject3Grade")).thenReturn("8");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("lang")).thenReturn("en");

        submitRequestCommand.execute(request, response);

        verify( response, times(1)).sendRedirect("/controller?command=getCandidateRequestsList");
        AdmissionRequestService admissionRequestService = new AdmissionRequestService();
        Assert.assertEquals(2,admissionRequestService.findAll().size() );


    }

}