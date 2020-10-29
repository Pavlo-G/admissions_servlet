package Service;

import model.entity.Candidate;
import model.entity.CandidateProfile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import setup.SetUpDbTest;

import java.util.List;

import static org.junit.Assert.*;

public class CandidateServiceTest {

    CandidateService candidateService = new CandidateService();

    @Before
    public void setUp() {
        SetUpDbTest.setUpDataBase();
    }

    @After
    public void tearDown() {
        SetUpDbTest.tearDownDataBase();
    }


    @Test
    public void findAll() {
        List<Candidate> candidateList = candidateService.findAll();
        Assert.assertEquals(2, candidateList.size());

    }

    @Test
    public void delete() {
        candidateService.delete(2L);
        List<Candidate> candidateList = candidateService.findAll();
        Assert.assertEquals(1, candidateList.size());
    }



    @Test
    public void findById() {
        Candidate candidate = candidateService.findById(1L);
        Assert.assertEquals("admin", candidate.getUsername());
    }


    @Test
    public void getCandidateProfile() {
        Candidate candidate = new Candidate();
        candidate.setId(2L);
        CandidateProfile candidateProfile = candidateService.getCandidateProfile(candidate).get();
        Assert.assertEquals("user@user.com", candidateProfile.getEmail());

    }

    @Test
    public void updateCandidateProfile() {
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setId(2L);
        candidateProfile.setFirstName("xxx");
        candidateProfile.setLastName("xxx");
        candidateProfile.setEmail("xxx");
        candidateProfile.setAddress("xxx");
        candidateProfile.setCity("xxx");
        candidateProfile.setRegion("xxx");
        candidateProfile.setPhoneNumber("xxx");
        candidateProfile.setFileName("xxx");

        candidateService.updateCandidateProfile(candidateProfile);
        Assert.assertEquals("xxx", candidateService.getCandidateProfileById(2L).get().getFileName());


    }


}