package controller.command.admin;

import Service.FacultyService;
import exception.DbProcessingException;
import model.DAO.mysql.ConnectionPoolHolder;
import model.entity.Faculty;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class CreateNewFacultyCommandTest {

    @Before
    public void setUp() {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void CreateNewFaculty() {

        FacultyService facultyService = new FacultyService();
        Faculty faculty = new Faculty();
        faculty.setNameEn("Faculty");
        faculty.setNameUk("Факультет");
        faculty.setDescriptionEn("Some description");
        faculty.setDescriptionUk("Some description");
        faculty.setBudgetCapacity(10);
        faculty.setTotalCapacity(15);
        faculty.setRequiredSubject1En("Subject1En");
        faculty.setRequiredSubject1Uk("Subject1Uk");
        faculty.setRequiredSubject2En("Subject2En");
        faculty.setRequiredSubject2Uk("Subject2Uk");
        faculty.setRequiredSubject3En("Subject3En");
        faculty.setRequiredSubject3Uk("Subject3Uk");
        faculty.setAdmissionOpen(true);

        facultyService.create(faculty);

        Assert.assertEquals(3, facultyService.findAll().size());

    }

    @Test(expected = DbProcessingException.class)
    public void CreateNewFacultyFailed() {
        FacultyService facultyService = new FacultyService();
        Faculty faculty = new Faculty();
        faculty.setNameUk("Факультет");
        faculty.setDescriptionEn("Some description");
        faculty.setDescriptionUk("Some description");
        faculty.setTotalCapacity(15);
        faculty.setRequiredSubject1En("Subject1En");
        faculty.setRequiredSubject1Uk("Subject1Uk");
        faculty.setRequiredSubject2En("Subject2En");
        faculty.setRequiredSubject2Uk("Subject2Uk");
        faculty.setRequiredSubject3En("Subject3En");
        faculty.setRequiredSubject3Uk("Subject3Uk");
        faculty.setAdmissionOpen(true);

        facultyService.create(faculty);

    }
}