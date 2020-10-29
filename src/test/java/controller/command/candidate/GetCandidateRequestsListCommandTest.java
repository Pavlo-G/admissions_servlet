package controller.command.candidate;

import Service.AdmissionRequestService;
import model.entity.AdmissionRequest;
import model.entity.Candidate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetCandidateRequestsListCommandTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    HttpSession session = mock(HttpSession.class);
    GetCandidateRequestsListCommand getCandidateRequestsListCommand = new GetCandidateRequestsListCommand(new AdmissionRequestService());

    @Before
    public void setUp() {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void execute() {
        Candidate candidate = new Candidate();
        candidate.setId(2L);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("candidate")).thenReturn(candidate);
        getCandidateRequestsListCommand.execute(request, response);

        verify(request,times(1)) .setAttribute(eq("requestsList"),anyListOf(AdmissionRequest.class));
        verify(request).setAttribute(eq("requestsList"),anyListOf(AdmissionRequest.class));

    }
}