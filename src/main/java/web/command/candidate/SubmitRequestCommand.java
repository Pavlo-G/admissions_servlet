package web.command.candidate;

import entity.*;
import web.command.Command;
import web.validation.GradeValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SubmitRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang= (String) request.getSession().getAttribute("lang");
        String facultyId=request.getParameter("facultyId");

        String errorMessage=null;
        String forward="/controller?command=getSubmitRequestForm";

        GradeValidator gradeValidator= new GradeValidator();
        String requiredSubject1Grade = request.getParameter("requiredSubject1Grade");
        String requiredSubject2Grade = request.getParameter("requiredSubject2Grade");
        String requiredSubject3Grade = request.getParameter("requiredSubject3Grade");
        if (!gradeValidator.validate(requiredSubject1Grade)||!gradeValidator.validate(requiredSubject2Grade)||!gradeValidator.validate(requiredSubject3Grade)) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Оцінка може бути від 1 до 12";
            } else {
                errorMessage = "Grade should be from 1 to 12";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward+"&facultyId="+facultyId;
        }



        AdmissionRequest admissionRequest = new AdmissionRequest();
        admissionRequest.setFacultyId(Long.valueOf(request.getParameter("facultyId")));
        admissionRequest.setCandidateId(Long.valueOf(request.getParameter("candidateId")));
        admissionRequest.setRequiredSubject1Grade(Integer.parseInt(requiredSubject1Grade));
        admissionRequest.setRequiredSubject2Grade(Integer.parseInt(requiredSubject2Grade));
        admissionRequest.setRequiredSubject3Grade(Integer.parseInt(requiredSubject3Grade));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);


        try {
            daoFactory.getAdmissionRequestDAO().saveAdmissionRequest(admissionRequest);
        } catch (SQLException throwables) {
            if (lang != null
                    && lang.equals("uk")) {
                errorMessage = "Ви вже подали заявку на цей факультет";
            } else {
                errorMessage = "You have  already sent request to this faculty";
            }
            request.setAttribute("errorMessage", errorMessage);
            return forward+"&facultyId="+facultyId;
        }


        return "/controller?command=getCandidateRequestsList";
    }
}
