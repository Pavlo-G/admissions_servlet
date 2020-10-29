package controller.command.candidate;

import Service.CandidateService;
import model.DAO.mysql.ConnectionPoolHolder;
import model.entity.Candidate;
import model.entity.CandidateProfile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.Assert.*;

public class CandidateProfileCommandTest {

    @Before
    public void setUp()  {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown()  {
        SetUpDbTest.tearDownDataBase();
    }

    @Test
    public void findProfileByCandidate() {
        CandidateService candidateService= new CandidateService();
        Candidate candidate= new Candidate();
        candidate.setId(2L);

        CandidateProfile candidateProfile =  candidateService.getCandidateProfile(candidate).get();
        Assert.assertEquals("user@user.com",candidateProfile.getEmail());
    }

    @Test
    public void findProfileById() {
        CandidateService candidateService= new CandidateService();
        CandidateProfile candidateProfile =  candidateService.getCandidateProfileById(2L).get();
        Assert.assertEquals("user@user.com",candidateProfile.getEmail());
    }
}