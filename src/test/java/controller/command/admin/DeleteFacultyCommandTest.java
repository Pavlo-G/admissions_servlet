package controller.command.admin;

import Service.FacultyService;
import exception.DbProcessingException;
import model.DAO.mysql.ConnectionPoolHolder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class DeleteFacultyCommandTest {


    @Before
    public void setUp() throws Exception {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown() throws Exception {
        SetUpDbTest.tearDownDataBase();

    }

    @Test
    public void deleteSecondFaculty() {
        FacultyService facultyService = new FacultyService();

        facultyService.delete(2L);

        Assert.assertEquals(1, facultyService.findAll().size());
    }


}