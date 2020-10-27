package controller.command;

import Service.CandidateService;
import exception.DbProcessingException;
import model.entity.Candidate;
import model.entity.CandidateProfile;
import model.entity.CandidateStatus;
import model.entity.Role;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.validation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistrationCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(RegistrationCommand.class);

    private final CandidateService candidateService;

    public RegistrationCommand(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = (String) request.getSession().getAttribute("lang");
        String errorMessage = null;
        String forward = "WEB-INF\\jsp\\registration.jsp";
        FieldValidator fieldValidator = new FieldValidator();
        PhoneValidator phoneValidator= new PhoneValidator();
        EmailValidator emailValidator= new EmailValidator();
        RegistrationValidator registrationValidator=
                new RegistrationValidator(lang,fieldValidator,emailValidator,phoneValidator);
        Map<String, String> registrationParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));



        Map<String, String> errors = registrationValidator.validateRegistration(registrationParameters);
        if (!errors.isEmpty()) {
            registrationParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/registration.jsp";
        }




        Candidate candidate = new Candidate();
        candidate.setUsername(registrationParameters.get("username"));
        candidate.setPassword(BCrypt.hashpw(registrationParameters.get("password"), BCrypt.gensalt(12)));
        candidate.setRole(Role.USER);
        candidate.setCandidateStatus(CandidateStatus.ACTIVE);


        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setEmail(registrationParameters.get("email"));
        candidateProfile.setFirstName(registrationParameters.get("firstName"));
        candidateProfile.setLastName( registrationParameters.get("lastName"));
        candidateProfile.setAddress(registrationParameters.get("address"));
        candidateProfile.setCity(registrationParameters.get("city"));
        candidateProfile.setRegion(registrationParameters.get("region"));
        candidateProfile.setSchool(registrationParameters.get("school"));
        candidateProfile.setPhoneNumber(registrationParameters.get("phoneNumber"));
        candidateProfile.setCandidate(candidate);

        try {
           candidateService.create(candidate, candidateProfile);
        } catch (DbProcessingException e) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Юзер з Ім'ям " + candidate.getUsername() + " вже існує";
            } else {
                errorMessage = "Username " + candidate.getUsername() + " already exists";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward;

        }

        try {
            forward="";
            response.sendRedirect("/controller?command=loginForm");
        } catch (IOException e) {
            LOG.error("Bad Request!",e);
            errorMessage="Bad request!";
            request.setAttribute("errorMessage", errorMessage);
        }


        return forward;
    }
}
