package Service;

import controller.command.admin.GetStatementOfFacultyCommand;
import exception.CanNotFindRequestById;
import exception.DbProcessingException;
import exception.StatementCreationException;
import model.DAO.DAOFactory;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;
import model.entity.StatementElement;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class AdmissionRequestService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
    private static final String REPORT_TEMPLATE_FILE_NAME = "JasperDesign.jrxml";


    public void changeAdmissionRequestStatus(Long admissionRequestId, AdmissionRequestStatus newAdmissionRequestStatus) {
    }

    public AdmissionRequest findById(Long admissionRequestId) {
        try {
            return daoFactory.getAdmissionRequestDAO().findAdmissionRequest(admissionRequestId)
                    .orElseThrow(() -> new CanNotFindRequestById("Can not find request by id: " + admissionRequestId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
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

    public void finalizeStatement(Faculty faculty, HttpServletResponse response) {
        List<AdmissionRequest> admissionRequestsListSorted = getSortedListOfRequestForFaculty(faculty);
        List<StatementElement> statementElementList = getStatementElements(admissionRequestsListSorted);
        try {
            createPdfReport(statementElementList, response);
        } catch (FileNotFoundException | JRException | URISyntaxException e) {
            throw new StatementCreationException("Can not create statement report");
        }
    }

    public List<AdmissionRequest> getSortedListOfRequestForFaculty(Faculty faculty) {
        return faculty.getAdmissionRequestList()
                .stream()
                .filter(x -> x.getAdmissionRequestStatus() == AdmissionRequestStatus.APPROVED)
                .sorted(
                        Comparator.comparingInt(AdmissionRequest::getSumOfGrades).reversed()
                                .thenComparing(AdmissionRequest::getCreationDateTime))
                .limit(faculty.getTotalCapacity())
                .collect(Collectors.toList());
    }


    private void createPdfReport(final List<StatementElement> statementElementList, HttpServletResponse response) throws JRException, FileNotFoundException, URISyntaxException {
        File templateFile = null;
        URL resource = getClass().getClassLoader().getResource(REPORT_TEMPLATE_FILE_NAME);
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
