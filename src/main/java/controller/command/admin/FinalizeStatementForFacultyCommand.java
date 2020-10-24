package controller.command.admin;


import Service.AdmissionRequestService;
import Service.FacultyService;
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

    private final FacultyService facultyService;
    private final AdmissionRequestService admissionRequestService;

    public FinalizeStatementForFacultyCommand(FacultyService facultyService, AdmissionRequestService admissionRequestService) {
        this.admissionRequestService = admissionRequestService;
        this.facultyService = facultyService;
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = "block";
        Long facultyId = Long.valueOf(request.getParameter("facultyId"));
        Faculty faculty = facultyService.findById(facultyId);
        facultyService.changeAdmissionOpenStatus(action, facultyId);

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + "report");

        response.getOutputStream().write(admissionRequestService.finalizeStatement(faculty));

        return "";

    }
}
