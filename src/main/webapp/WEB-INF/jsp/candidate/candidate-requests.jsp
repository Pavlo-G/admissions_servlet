<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 09.10.2020
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h2 class="text-danger">Admission Requests </h2>
                        </div>
                    </div>
                </div>
                <div class="panel-body">

                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>

                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>To the Faculty</th>
                            <th>Status</th>
                            <th>Creation date and time</th>


                            <th align="center">Delete</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="req" items="${requestsList}">

                            <tr>

                                <td>${req.id}</td>
                                <td>${req.candidate.candidateProfile.firstName}</td>
                                <td>${req.candidate.candidateProfile.lastName}</td>
                                <td>${req.faculty.name}</td>
                                <td>${req.admissionRequestStatus.name()}</td>
                                <td>
                                        ${req.creationDateTime}
                                </td>

                                <td align="center">
                                    <form
                                            action="controller" method="post">
                                        <input type="hidden" name="command" value="deleteAdmissionRequest">
                                        <input type="hidden" name="admissionRequestId" value="${req.id}">
                                        <button class="btn btn-primary" type="submit">Delete request</button>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div>

        </div>
    </div>
</div>
</body>
</html>
