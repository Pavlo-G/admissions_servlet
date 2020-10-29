package Service;

import model.DAO.DAOFactory;
import model.DAO.FacultyDAO;
import model.DAO.mysql.ConnectionPoolHolder;
import model.entity.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

public class AdmissionRequestServiceTest {

    Faculty faculty = new Faculty();


    @Before
    public void setUp() {

        SetUpDbTest.setUpDataBase();
        faculty = new Faculty();
        faculty.setTotalCapacity(2);
        AdmissionRequest admissionRequest1 = new AdmissionRequest();
        admissionRequest1.setId(2L);
        admissionRequest1.setAdmissionRequestStatus(AdmissionRequestStatus.APPROVED);
        admissionRequest1.setRequiredSubject1Grade(11);
        admissionRequest1.setRequiredSubject2Grade(11);
        admissionRequest1.setRequiredSubject3Grade(11);
        admissionRequest1.setCreationDateTime(LocalDateTime.parse("2020-10-25T10:15:30"));

        AdmissionRequest admissionRequest2 = new AdmissionRequest();
        admissionRequest2.setId(3L);
        admissionRequest2.setAdmissionRequestStatus(AdmissionRequestStatus.APPROVED);
        admissionRequest2.setRequiredSubject1Grade(10);
        admissionRequest2.setRequiredSubject2Grade(10);
        admissionRequest2.setRequiredSubject3Grade(10);
        admissionRequest2.setCreationDateTime(LocalDateTime.parse("2020-10-25T10:15:30"));

        AdmissionRequest admissionRequest3 = new AdmissionRequest();
        admissionRequest3.setId(4L);
        admissionRequest3.setAdmissionRequestStatus(AdmissionRequestStatus.APPROVED);
        admissionRequest3.setRequiredSubject1Grade(10);
        admissionRequest3.setRequiredSubject2Grade(10);
        admissionRequest3.setRequiredSubject3Grade(10);
        admissionRequest3.setCreationDateTime(LocalDateTime.parse("2020-10-25T10:14:30"));

        faculty.getAdmissionRequestList().add(admissionRequest1);
        faculty.getAdmissionRequestList().add(admissionRequest2);
        faculty.getAdmissionRequestList().add(admissionRequest3);
        faculty.setNameEn("Faculty");

    }


    @After
    public void tearDown() {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void getSortedListOfRequestForFacultySizeSameAsTotalCapacity() {

        AdmissionRequestService admissionRequestService = new AdmissionRequestService();
        List<AdmissionRequest> sortedList = admissionRequestService.getSortedListOfRequestForFaculty(faculty);
        Assert.assertEquals(2, sortedList.size());

    }

    @Test
    public void getSortedListOfRequestForFacultyFirstCandidate() {

        AdmissionRequestService admissionRequestService = new AdmissionRequestService();
        List<AdmissionRequest> sortedList = admissionRequestService.getSortedListOfRequestForFaculty(faculty);

        Assert.assertEquals(Long.valueOf(2L), sortedList.get(0).getId());

    }

    @Test
    public void getSortedListOfRequestForFacultySecondCandidate() {
        AdmissionRequestService admissionRequestService = new AdmissionRequestService();
        List<AdmissionRequest> sortedList = admissionRequestService.getSortedListOfRequestForFaculty(faculty);

        Assert.assertEquals(Long.valueOf(4L), sortedList.get(1).getId());

    }


    @Test
    public void selectAdmissionRequestsForCandidateWithId() {

        AdmissionRequestService admissionRequestService = new AdmissionRequestService();
        List<AdmissionRequest> requestsList = admissionRequestService.selectAdmissionRequestsForCandidateWithId(2L);

        Assert.assertEquals(Long.valueOf(2L), requestsList.get(0).getCandidate().getId());

    }


}