package controller.command.admin;

import Service.AdmissionRequestService;
import Service.FacultyService;
import controller.command.Command;
import model.entity.Faculty;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class FinalizeStatementForFacultyCommandTest {

    @Test
    public void execute() throws IOException, ServletException {
        FacultyService facultyService = mock(FacultyService.class);
        AdmissionRequestService admissionRequestService = mock(AdmissionRequestService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("facultyId")).thenReturn("1");
        when(facultyService.findById(1L)).thenReturn(new Faculty());
        when(admissionRequestService.finalizeStatement(any(Faculty.class))).thenReturn(new byte[10]);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        Command finalizeStatement = new FinalizeStatementForFacultyCommand(facultyService, admissionRequestService);
        finalizeStatement.execute(request, response);

        verify(facultyService, times(1)).findById(1L);
        verify(response, times(1)).getOutputStream();
        verify(servletOutputStream, times(1)).write(new byte[10]);


    }
}