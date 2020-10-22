package controller.command.admin;

import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GetStatementOfFacultyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Long facultyId = Long.valueOf(request.getParameter("facultyId"));

        Faculty faculty = null;
        try {
            faculty = daoFactory.getFacultyDAO().findById(facultyId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        List<AdmissionRequest> admissionRequestsList = getSortedListOfRequestForFaculty(faculty);

        request.setAttribute("admissionRequestsList", admissionRequestsList);
        request.setAttribute("facultyId", facultyId);

        return "/WEB-INF/jsp/admin/statementOfFaculty.jsp";
    }


    protected List<AdmissionRequest> getSortedListOfRequestForFaculty(Faculty faculty) {
        return faculty.getAdmissionRequestList()
                .stream()
                .filter(x -> x.getAdmissionRequestStatus() == AdmissionRequestStatus.APPROVED)
                .sorted(
                        Comparator.comparingInt(AdmissionRequest::getSumOfGrades).reversed()
                                .thenComparing(AdmissionRequest::getCreationDateTime))
                .limit(faculty.getTotalCapacity())
                .collect(Collectors.toList());
    }
}
