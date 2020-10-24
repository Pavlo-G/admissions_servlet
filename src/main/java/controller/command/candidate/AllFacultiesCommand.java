package controller.command.candidate;


import Service.FacultyService;
import controller.command.admin.AllCandidatesCommand;
import controller.command.admin.UpdateFacultyCommand;
import model.dto.FacultyListDTO;
import controller.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AllFacultiesCommand implements Command {
    static final Logger LOG = LoggerFactory.getLogger(AllFacultiesCommand.class);
    private final FacultyService facultyService;

    public AllFacultiesCommand(FacultyService facultyService){
        this.facultyService=facultyService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String sortBy;
        String sortDir;
        int currentPage;
        int itemsPerPage;

        if ((sortBy = request.getParameter("sortBy")) == null) {
            sortBy = "name_en";
        }

        if ((sortDir = request.getParameter("sortDir")) == null) {
            sortDir = "ASC";
        }

        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        } else {
            currentPage = 1;
        }
        if (request.getParameter("itemsPerPage") != (null)) {
            itemsPerPage = Integer.parseInt(request.getParameter("itemsPerPage"));
        } else {
            itemsPerPage = 5;
        }


        FacultyListDTO facultyListDTO = null;

            facultyListDTO = facultyService.findAllSorted(sortBy, sortDir, currentPage, itemsPerPage);

        int totalFaculties = facultyListDTO.getCount();

        int totalPages = 0;
        if (totalFaculties % itemsPerPage == 0) {
            totalPages = totalFaculties / itemsPerPage;
        } else {
            totalPages = totalFaculties / itemsPerPage + 1;
        }

        int[] itemsPerPageArray = {5, 10, 15};

        request.setAttribute("facultiesList", facultyListDTO.getFacultyList());
        request.setAttribute("noOfPages", totalPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("itemsPerPage", itemsPerPage);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("sortDir", sortDir);
        request.setAttribute("itemsPerPageArray", itemsPerPageArray);


        return "/WEB-INF/jsp/candidate/faculties.jsp";
    }
}
