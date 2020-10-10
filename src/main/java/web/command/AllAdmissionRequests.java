package web.command;

import dto.AdmissionRequestDTO;
import entity.AdmissionRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllAdmissionRequests  implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //  log.debug("Command starts");
        // get menu items list
        List<AdmissionRequestDTO> admissionRequests = daoFactory.getAdmissionRequestDAO().selectAdmissionRequests();
        // log.trace("Found in DB: menuItemsList --> " + menuItems);

        // sort menu by category
//        Collections.sort(menuItems, new Comparator<MenuItem>() {
//            public int compare(MenuItem o1, MenuItem o2) {
//                return (int)(o1.getCategoryId() - o2.getCategoryId());
//            }
//        });

        // put menu items list to the request
        request.setAttribute("admissionRequests", admissionRequests);
        //  log.trace("Set the request attribute: menuItems --> " + menuItems);

        //   log.debug("Command finished");
        return "/WEB-INF/jsp/admissionRequests.jsp";
    }
}
