package web.command;

import entity.AdmissionRequest;
import entity.AdmissionRequestStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitRequestCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        AdmissionRequest admissionRequest =  new AdmissionRequest();
        admissionRequest.setFacultyId(Long.valueOf(request.getParameter("facultyId")));
        admissionRequest.setCandidateId(Long.valueOf(request.getParameter("candidateId")));
        admissionRequest.setRequiredSubject1Grade(Integer.parseInt(request.getParameter("requiredSubject1Grade")));
        admissionRequest.setRequiredSubject2Grade(Integer.parseInt(request.getParameter("requiredSubject2Grade")));
        admissionRequest.setRequiredSubject3Grade(Integer.parseInt(request.getParameter("requiredSubject3Grade")));
        admissionRequest.setAdmissionRequestStatus(AdmissionRequestStatus.NEW);


        daoFactory.getAdmissionRequestDAO().saveAdmissionRequest(admissionRequest);
        return "/controller?command=getCandidateRequestsList";
    }
}
