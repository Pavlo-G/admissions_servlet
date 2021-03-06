package Service;

import exception.CanNotFindRequestById;
import exception.CanNotMakePDFException;
import exception.DbProcessingException;
import model.DAO.AdmissionRequestDAO;
import model.DAO.DAOFactory;
import model.entity.AdmissionRequest;
import model.entity.AdmissionRequestStatus;
import model.entity.Faculty;
import model.entity.StatementElement;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


public class AdmissionRequestService {
    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);
    private static final String REPORT_TEMPLATE_FILE_NAME = "JasperDesign.jrxml";


    public List<AdmissionRequest> findAll() {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }

    }


    public void changeAdmissionRequestStatus(Long admissionRequestId, AdmissionRequestStatus newAdmissionRequestStatus) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            dao.changeAdmissionRequestStatus(admissionRequestId, newAdmissionRequestStatus);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }


    public AdmissionRequest findById(Long admissionRequestId) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            return dao.findById(admissionRequestId)
                    .orElseThrow(() -> new CanNotFindRequestById("Can not find request by id: " + admissionRequestId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }


    public void create(AdmissionRequest admissionRequest) {

        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            dao.create(admissionRequest);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }


    public List<AdmissionRequest> selectAdmissionRequestsForCandidateWithId(Long id) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            return dao.selectAdmissionRequestsForCandidateWithId(id);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not get requests for candidate");
        }
    }

    public void delete(Long id) {
        try (AdmissionRequestDAO dao = daoFactory.getAdmissionRequestDAO()) {
            dao.delete(id);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not delete request with id:" + id);
        }
    }


    /**
     * Returns list of Admission requests sorted by grade(then sorted by request creation time)
     * and limited by total capacity of the faculty.
     * @param faculty
     * @return List<AdmissionRequest>
     */
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

    /**
     * Converts List of Admission requests to list with Statement elements suitable for pdf report.
     * @param admissionRequests
     * @return List<StatementElement>
     */

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


    /**
     * Returns byte array of prepared PDF report to the controller.
     * @param faculty
     * @return byte[]
     */


    public byte[] finalizeStatement(Faculty faculty) {

        List<StatementElement> statementElementList = getStatementElements(getSortedListOfRequestForFaculty(faculty));

        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = createPdfReport(statementElementList);
        } catch (JRException e) {
            throw new CanNotMakePDFException("Jasper report throws exception");
        } catch (FileNotFoundException e) {
            throw new CanNotMakePDFException("Template not found");
        } catch (URISyntaxException e) {
            throw new CanNotMakePDFException("Wrong template URI");
        }
        return outputStream.toByteArray();
    }


    /**
     * Returns ByteArrayOutputStream of PDF report, converted by Jasper
     * @param statementElementList
     * @return ByteArrayOutputStream
     * @throws JRException
     * @throws FileNotFoundException
     * @throws URISyntaxException
     */

    private ByteArrayOutputStream createPdfReport(final List<StatementElement> statementElementList) throws JRException, FileNotFoundException, URISyntaxException {
        File templateFile;
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
        ByteArrayOutputStream outputSteam = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

        return outputSteam;
    }



}
