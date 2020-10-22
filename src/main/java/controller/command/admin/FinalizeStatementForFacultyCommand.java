package controller.command.admin;


import com.sun.xml.internal.bind.v2.TODO;
import model.entity.AdmissionRequest;
import model.entity.Faculty;
import model.entity.StatementElement;
import exception.StatementCreationException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalizeStatementForFacultyCommand implements Command {
    private static final String FILE_NAME = "JasperDesign.jrxml";
    private static final String OUT_FILE = "Reports.pdf";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = "block";
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = null;
        try {
            faculty = daoFactory.getFacultyDAO().findById(facultyId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            daoFactory.getFacultyDAO().changeAdmissionOpenStatus(action, facultyId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            //TODO rework this full class
        }


        List<AdmissionRequest> admissionRequestsListSorted = new GetStatementOfFacultyCommand().getSortedListOfRequestForFaculty(faculty);
        List<StatementElement> statementElementList = getStatementElements(admissionRequestsListSorted);
        try {
            createPdfReport(statementElementList, response);
        } catch (FileNotFoundException | JRException | URISyntaxException e) {
            throw new StatementCreationException("Can not create statement report");
        }
        response.sendRedirect("WEB-INF\\jsp\\admin\\filePage.jsp");


        return "";

    }


    private List<StatementElement> getStatementElements(List<AdmissionRequest> admissionRequests) {
        List<StatementElement> statementElementList = new ArrayList<>();

        for (int i = 0; i < admissionRequests.size(); i++) {
            StatementElement statementElement = new StatementElement();
            statementElement.setFacultyName(admissionRequests.get(i).getFaculty().getNameEn());
            statementElement.setFirstName(admissionRequests.get(i).getCandidate().getCandidateProfile().getFirstName());
            statementElement.setLastName(admissionRequests.get(i).getCandidate().getCandidateProfile().getLastName());
            statementElement.setEmail(admissionRequests.get(i).getCandidate().getCandidateProfile().getEmail());
            statementElement.setGrade(admissionRequests.get(i).getSumOfGrades());
            statementElement.setContactNumber(admissionRequests.get(i).getCandidate().getCandidateProfile().getPhoneNumber());
            statementElement.setStatus((i < admissionRequests.get(i).getFaculty().getBudgetCapacity() ? "Budget" : "Contract"));

            statementElementList.add(statementElement);

        }
        return statementElementList;
    }

    private void createPdfReport(final List<StatementElement> statementElementList, HttpServletResponse response) throws JRException, FileNotFoundException, URISyntaxException {
        File templateFile = null;
        URL resource = getClass().getClassLoader().getResource(FILE_NAME);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        } else {
            templateFile = new File(resource.toURI());
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(templateFile.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(statementElementList);
        Map<String, Object> parameters = new HashMap<>();

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        try {

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=" + "report");


            OutputStream outputSteam = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
