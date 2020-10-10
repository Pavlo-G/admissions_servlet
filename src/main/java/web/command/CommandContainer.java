package web.command;

import entity.CandidateProfile;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private CommandContainer(){

    }

    private static  Map<String, Command> commandMap = new HashMap<>();

    static {
        // common commands
        commandMap.put("facultiesList", new AllFacultiesCommand());
        commandMap.put("candidatesList", new AllCandidatesCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("admissionRequests", new AllAdmissionRequests());
        commandMap.put("loginForm", new LoginFormCommand());
        commandMap.put("submitRequest", new SubmitRequestCommand());
        commandMap.put("registrationForm", new RegistrationFormCommand());
        commandMap.put("adminWorkspace", new AdminWorkspaceCommand());
        commandMap.put("candidateProfile", new CandidateProfileCommand());
        commandMap.put("candidateProfileEdit", new CandidateProfileEditCommand());
        commandMap.put("updateCandidateProfile", new UpdateCandidateProfileCommand());
        commandMap.put("getSubmitRequestForm", new GetSubmitRequestFormCommand());

    }


    public static Command get(String commandName) {
        if (commandName == null || !commandMap.containsKey(commandName)) {
            // log.trace("Command not found, name --> " + commandName);
            return commandMap.get("noCommand");
        }

        return commandMap.get(commandName);


    }
}
