package controller.command.admin;

import Service.FacultyService;
import exception.DbProcessingException;
import model.DAO.mysql.ConnectionPoolHolder;
import model.entity.Faculty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Statement;

import static org.mockito.Mockito.*;

public class AdminWorkspaceCommandTest {
    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void setUp() {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown()  {
        SetUpDbTest.setUpDataBase();
    }






    @Test
    public void executeNormal() {
        AdminWorkspaceCommand adminWorkspaceCommand = new AdminWorkspaceCommand(new FacultyService());
        adminWorkspaceCommand.execute(request, response);
        verify(request, times(1)).setAttribute(eq("facultiesList"),anyListOf(Faculty.class));
    }

    @Test
    public void executeGetAllFacultiesListException() {

        FacultyService facultyService = mock(FacultyService.class);
        AdminWorkspaceCommand adminWorkspaceCommand = new AdminWorkspaceCommand(facultyService);
        when(facultyService.findAll()).thenThrow(new  DbProcessingException("exception"));

        adminWorkspaceCommand.execute(request, response);
        verify(facultyService, times(1)).findAll();
        verify(request, times(1)).setAttribute(eq("errorMessage"), eq("exception"));
    }
}

