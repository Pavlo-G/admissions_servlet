package controller.command.candidate;

import Service.AdmissionRequestService;
import Service.FacultyService;
import exception.DbProcessingException;
import model.dto.AdmissionRequestDTO;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.validation.AdmissionRequestValidator;
import utils.validation.GradeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

public class SubmitRequestCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(SubmitRequestCommand.class);
    private final AdmissionRequestService admissionRequestService;
    private final FacultyService facultyService;

    public SubmitRequestCommand(AdmissionRequestService admissionRequestService,FacultyService facultyService) {
        this.admissionRequestService = admissionRequestService;
        this.facultyService=facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String lang = (String) request.getSession().getAttribute("lang");
        String facultyId = request.getParameter("facultyId");
        String errorMessage = null;


        Map<String, String> admissionRequestParameters = request.getParameterMap().entrySet().stream()
                .filter(entry -> !("command".equals(entry.getKey())))
                .filter(entry -> !("facultyId".equals(entry.getKey())))
                .filter(entry -> !("candidateId".equals(entry.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, stringEntry -> request.getParameter(stringEntry.getKey())));

        AdmissionRequestValidator admissionRequestValidator = new AdmissionRequestValidator(lang);
        Map<String, String> errors = admissionRequestValidator.validateAdmissionRequest(admissionRequestParameters);

        if (!errors.isEmpty()) {
            request.setAttribute("faculty", facultyService.findById(Long.valueOf(facultyId)));
            request.setAttribute("candidate",request.getSession().getAttribute("candidate"));
            admissionRequestParameters.entrySet().stream().forEach(c -> request.setAttribute(c.getKey(), c.getValue()));
            errors.entrySet().stream().forEach(entity -> request.setAttribute(entity.getKey(), entity.getValue()));
            return "WEB-INF/jsp/candidate/candidate-submit-request-form.jsp";

        }


        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setFacultyId(Long.valueOf(request.getParameter("facultyId")));
        admissionRequest.setCandidateId(Long.valueOf(request.getParameter("candidateId")));
        admissionRequest.setRequiredSubject1Grade(Integer.parseInt(admissionRequestParameters.get("requiredSubject1Grade")));
        admissionRequest.setRequiredSubject2Grade(Integer.parseInt(admissionRequestParameters.get("requiredSubject2Grade")));
        admissionRequest.setRequiredSubject3Grade(Integer.parseInt(admissionRequestParameters.get("requiredSubject3Grade")));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);


        try {
            admissionRequestService.create(admissionRequest);
        } catch (DbProcessingException e) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Ви вже подали заявку на цей факультет";
            } else {
                errorMessage = "You have  already sent request to this faculty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return "/controller?command=getSubmitRequestForm&facultyId=" + facultyId;
        }


        response.sendRedirect("/controller?command=getCandidateRequestsList");

        return "";
    }
}
