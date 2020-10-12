<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp"/>

<br>
<div class="container ml-4">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-1">
                            <h3 class="panel-title">Faculties</h3>
                        </div>
                        <div class=" pull-right">
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="createNewFacultyForm">
                                <button type="submit" class="btn btn-primary btn-create">Create New Faculty
                                </button>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="float-left">
                    </div>
                </div>
                <div class="panel-body  row align-items-center">
                    <table class="table table-striped table-bordered table-list">
                        <thead class="col-md-10 col-md-offset-1">
                        <tr>
                            <th style="width: 10%">Name</th>
                            <th style="width: 5%">New Requests</th>
                            <th style="width: 5%">Rejected Requests</th>
                            <th style="width: 5%">Approved Requests</th>
                            <th style="width: 5%">Budget Capacity</th>
                            <th style="width: 5%">Total Capacity</th>
                            <th style="width: 13%">Work With Requests</th>
                            <th style="width: 10%">Block/Unblock Registration</th>
                            <th style="width: 14%">Edit Faculty</th>
                            <th style="width: 14%">Delete Faculty</th>
                            <th style="width: 14%">Statement</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="faculty" items="${facultiesList}">
                            <jsp:useBean id="faculty" type="entity.Faculty"/>
                            <tr>


                                <td>${faculty.name}</td>
                                <td>${faculty.numberOfRequestsNew()}</td>
                                <td>${faculty.numberOfRequestsRejected()}</td>
                                <td>${faculty.numberOfRequestsApproved()}</td>
                                <td>${faculty.budgetCapacity}</td>
                                <td>${faculty.totalCapacity}</td>
                                <td>
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/controller" method="post">
                                        <input type="hidden" name="command" value="showRequestsListOfFaculty" >
                                        <input type="hidden" name="facultyId" value="${faculty.id}">
                                        <button class="btn btn-primary btn-xs" type="submit">Requests</button>
                                    </form>
                                </td>
                                <td>
                                    <c:choose>
                                    <c:when test="${faculty.admissionOpen==true}">

                                        <form class="form-inline my-2 my-lg-0"
                                              action="/admin/block_reg/" method="post">

                                            <button class="btn btn-warning btn-xs" type="submit">Block</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/block_reg/${faculty.id}" method="post">
<%--                                        <input type="hidden" name="id" value="${faculty.id}">--%>
<%--                                        <input type="hidden" name="name" value="${faculty.name}">--%>
<%--                                        <input type="hidden" name="description" value="${faculty.getDescription()}">--%>
<%--                                        <input type="hidden" name="budgetCapacity"--%>
<%--                                               value="${faculty.getBudgetCapacity()}">--%>
<%--                                        <input type="hidden" name="totalCapacity" value="${faculty.getTotalCapacity()}">--%>
<%--                                        <input type="hidden" name="requiredSubject1"--%>
<%--                                               value="${faculty.getRequiredSubject1()}">--%>
<%--                                        <input type="hidden" name="requiredSubject2"--%>
<%--                                               value="${faculty.getRequiredSubject2()}">--%>
<%--                                        <input type="hidden" name="requiredSubject3"--%>
<%--                                               value="${faculty.getRequiredSubject3()}">--%>
<%--                                        <input type="hidden" name="admissionOpen" value="${true?string}">--%>
                                        <button class="btn btn-success btn-xs" type="submit">Unblock</button>
                                    </form>
                                    </c:otherwise>
                                    </c:choose>

                                </td>

                                <td>
                                    <form class="form-inline my-2 my-lg-0"
                                          action="controller" method="post">
                                        <input type="hidden" name="command" value="editFacultyForm">
                                        <input type="hidden" name="facultyId" value="${faculty.id}">

                                        <button class="btn btn-primary btn-xs" type="submit">Edit</button>

                                    </form>
                                </td>
                                <td class="col-lg-11 col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/faculties/delete/${faculty.id}" method="post">
                                        <button class="btn btn-danger btn-xs" type="submit">Delete</button>
                                    </form>
                                </td>
                                <td class="col-lg-11 col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/admin/statement/faculty/${faculty.id}" method="get">
                                        <button class="btn btn-primary btn-xs" type="submit">Statement</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <br>
                </div>
            </div>

        </div>
    </div>
</div>


</body>


</body>
</html>
