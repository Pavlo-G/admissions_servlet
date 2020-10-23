package controller.command;

import Service.AdmissionRequestService;
import Service.CandidateService;
import Service.FacultyService;
import controller.command.admin.*;
import controller.command.candidate.*;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private CommandContainer() {

    }

    private static Map<String, Command> commandMap = new HashMap<>();

    static {
        // common commands
        commandMap.put("facultiesList", new AllFacultiesCommand());
        commandMap.put("candidatesList", new AllCandidatesCommand(new CandidateService()));
        commandMap.put("editCandidate", new EditCandidateCommand(new CandidateService()));
        commandMap.put("editCandidateForm", new EditCandidateFormCommand(new CandidateService()));
        commandMap.put("deleteCandidate", new DeleteCandidateCommand(new CandidateService()));
        commandMap.put("login", new LoginCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("admissionRequests", new AllAdmissionRequests());
        commandMap.put("loginForm", new LoginFormCommand());
        commandMap.put("submitRequest", new SubmitRequestCommand());
        commandMap.put("registrationForm", new RegistrationFormCommand());
        commandMap.put("adminWorkspace", new AdminWorkspaceCommand(new FacultyService()));
        commandMap.put("candidateProfile", new CandidateProfileCommand());
        commandMap.put("candidateProfileEdit", new CandidateProfileEditCommand());
        commandMap.put("updateCandidateProfile", new UpdateCandidateProfileCommand());
        commandMap.put("getSubmitRequestForm", new GetSubmitRequestFormCommand());
        commandMap.put("getCandidateRequestsList", new GetCandidateRequestsListCommand());
        commandMap.put("deleteAdmissionRequest", new DeleteAdmissionRequestCommand());
        commandMap.put("showRequestsListOfFaculty", new ShowRequestsListOfFacultyCommand());
        commandMap.put("checkRequestFromFacultyReqList", new CheckRequestFromFacultyReqListCommand(new AdmissionRequestService()));
        commandMap.put("createNewFacultyForm", new CreateNewFacultyFormCommand());
        commandMap.put("createNewFaculty", new CreateNewFacultyCommand(new FacultyService()));
        commandMap.put("editFacultyForm", new EditFacultyFormCommand(new FacultyService()));
        commandMap.put("updateFaculty", new UpdateFacultyCommand(new FacultyService()));
        commandMap.put("deleteFaculty", new DeleteFacultyCommand(new FacultyService()));
        commandMap.put("changeAdmissionRequestStatus", new ChangeAdmissionRequestStatusCommand(new AdmissionRequestService()));
        commandMap.put("blockUnblockFacultyRegistration", new BlockUnblockFacultyRegistrationCommand(new FacultyService()));
        commandMap.put("getStatementOfFaculty", new GetStatementOfFacultyCommand(new FacultyService(),new AdmissionRequestService()));
        commandMap.put("finalizeStatementForFaculty", new FinalizeStatementForFacultyCommand(new FacultyService(),new AdmissionRequestService()));

    }


    public static Command get(String commandName) {
        if (commandName == null || !commandMap.containsKey(commandName)) {

            return commandMap.get("noCommand");
        }

        return commandMap.get(commandName);


    }
}
