<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<br>

<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel-body">

                <td align="center">

                    <form class="form-inline my-2 my-lg-0"
                          action="controller" method="Post">

                        <input type="hidden" name="command" value="finalizeStatementForFaculty">
                        <input type="hidden" name="facultyId"  value="${facultyId}">
                        <button class="btn btn-danger" type="submit">Finalize statement</button>
                    </form>

                </td>
                <br>
                <table class="table table-striped table-bordered table-list">
                    <thead>
                    <tr>

                        <th style="width: 14.28%">Faculty Name</th>
                        <th style="width: 14.28%">Candidate Name</th>
                        <th style="width: 14.28%">Candidate Email</th>
                        <th style="width: 14.28%">Candidate Contact Number</th>
                        <th style="width: 14.28%">Candidate Grade</th>
                        <th style="width: 14.28%">Budget/Contract</th>

                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="req" items="${admissionRequestsList}">

                        <tr>

                            <td style="width: 14.28%">${req.faculty.getName()}</td>
                            <td style="width: 14.28%">${req.candidate.candidateProfile.firstName} ${statement.candidate.candidateProfile.lastName} </td>
                            <td style="width: 14.28%">${req.candidate.candidateProfile.email}</td>
                            <td style="width: 14.28%">${req.candidate.candidateProfile.phoneNumber}</td>
                            <td style="width: 14.28%">${req.getSumOfGrades()}</td>
                            <td style="width: 14.28%">


                            </td>

                        </tr>
                    </c:forEach>
                    <h2>No active requests to prepare the statement</h2>

                    </tbody>

                </table>


            </div>

        </div>


    </div>
</div>


</body>
</html>
