package controller.command.admin;

import Service.CandidateService;
import controller.command.Command;
import exception.DbProcessingException;
import model.entity.Candidate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;

public class AllCandidatesCommandTest {





    @Test
    public void executeGetAllCandidatesListAndSetAsAttribute() {
        CandidateService candidateService = mock(CandidateService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        AllCandidatesCommand allCandidatesCommand = new AllCandidatesCommand(candidateService);


        List<Candidate> candidates = new ArrayList<>();
        candidates.add(new Candidate());

        when(candidateService.findAll()).thenReturn(candidates);
        allCandidatesCommand.execute(request, response);


        verify(candidateService, times(1)).findAll();
        verify(request,times(1)).setAttribute("candidatesList",candidates);
    }




    @Test
    public void executeGetAllCandidatesListException() {
        CandidateService candidateService = mock(CandidateService.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        AllCandidatesCommand allCandidatesCommand = new AllCandidatesCommand(candidateService);


        when(candidateService.findAll()).thenThrow(new DbProcessingException("error"));
        allCandidatesCommand.execute(request, response);


        verify(candidateService, times(1)).findAll();
        verify(request,times(1)).setAttribute(eq("errorMessage"),anyObject());
    }

}