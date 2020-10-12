<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 11.10.2020
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
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

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title">Admission Requests: </h3>
                        </div>

                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>

                            <th>ID</th>
                            <th>From
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=candidate,ASC">&#8593</a>--%>
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=candidate,DESC">&#8595</a>--%>
                            </th>
                            <th>To the Faculty</th>
                            <th>Status
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=admissionRequestStatus,ASC">&#8593</a>--%>
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=admissionRequestStatus,DESC">&#8595</a>--%>
                            </th>
                            <th>Action</th>


                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="req" items="${requestsList}">
                            <jsp:useBean id="req" type="entity.AdmissionRequest"/>
                            <tr>

                                <td>${req.id}</td>
                                <td>${req.candidate.candidateProfile.firstName} ${req.candidate.candidateProfile.lastName}</td>
                                <td>${req.faculty.name}</td>
                                <td>${req.admissionRequestStatus.name()}</td>
                                <td>
                                    <form
                                            action="/controller" method="post">
                                        <input type="hidden" name="command" value="checkRequestFromFacultyReqList">
                                        <input type="hidden" name="requestId" value="${req.id}">
                                        <button class="btn btn-primary" type="submit">Check request</button>
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


</body>
</html>
