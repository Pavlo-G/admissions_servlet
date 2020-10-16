<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.lang}">
<c:set var="title" value="Faculties" scope="page"/>
<head>
    <title>Admin Workspace</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="/controller?command=adminWorkspace"><fmt:message
            key="navbar.Admission_Board_App"/></a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <li class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">

            <li class="nav-item dropdown my-2">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><fmt:message key="navbar.Change_Language"/>
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">
                            <a class="dropdown-item" href="/controller?command=adminWorkspace&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=adminWorkspace&sessionLocale=uk">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>

            <li class="nav-item ml-4 mr-2">
                <form class="form-inline ">
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=adminWorkspace" role="button">
                        <fmt:message key="navbar.Admin_workspace_faculty"/></a>
                </form>

            </li>
            <li class="nav-item mr-2 ">
                <form class="form-inline">
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=candidatesList" role="button">
                        <fmt:message key="navbar.Admin_workspace_candidates"/></a>
                </form>

            </li>
            <div class="nav-item dropdown my-2 ">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown ml-auto"><a href="" class="dropdown-toggle" data-target="#navbarDropdownPr"
                                                    data-toggle="dropdown"><fmt:message
                            key="navbar.Account"/> ${sessionScope.candidate.username}
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdownPr">
                            <a class="dropdown-item" href="controller?command=candidateProfile"><fmt:message
                                    key="navbar.my_profile"/></a>
                            <a class="dropdown-item" href="controller?command=getCandidateRequestsList"><fmt:message
                                    key="navbar.my_requests"/></a>
                            <a class="dropdown-item" href="controller?command=logout"><fmt:message
                                    key="navbar.logout"/></a>
                        </div>


                    </li>
                </ul>
            </div>

        </ul>
    </li>
</nav>

<br>
<div class="container ml-4 align-content-around ">
    <div class="row">
        <div >
            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-1">
                            <h3 class="panel-title"><fmt:message key="admin.faculties"/></h3>
                        </div>
                        <div class=" pull-right">
                            <form action="controller" method="get">
                                <input type="hidden" name="command" value="createNewFacultyForm">
                                <button type="submit" class="btn btn-primary btn-create"><fmt:message
                                        key="admin.create_new_faculty"/>
                                </button>
                            </form>
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="float-left">
                    </div>
                </div>
                <div>
                    <table class="table table-striped table-bordered table-responsive">
                        <thead>
                        <tr>
                            <th style="width: 10%"><fmt:message key="admin.name"/></th>
                            <th style="width: 5%"><fmt:message key="admin.new_requests"/></th>
                            <th style="width: 5%"><fmt:message key="admin.rejected_requests"/></th>
                            <th style="width: 5%"><fmt:message key="admin.approved_requests"/></th>
                            <th style="width: 5%"><fmt:message key="admin.budget_capacity"/></th>
                            <th style="width: 5%"><fmt:message key="admin.total_capacity"/></th>
                            <th style="width: 13%"><fmt:message key="admin.work_with_requests"/></th>
                            <th style="width: 10%"><fmt:message key="admin.block_unblock_registration"/></th>
                            <th style="width: 14%"><fmt:message key="admin.edit_faculty"/></th>
                            <th style="width: 14%"><fmt:message key="admin.delete_faculty"/></th>
                            <th style="width: 14%"><fmt:message key="admin.statement"/></th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="faculty" items="${facultiesList}">
                            <jsp:useBean id="faculty" type="entity.Faculty"/>
                            <tr>


                                <td>
                                    <c:choose>
                                        <c:when test="${sessionScope.lang eq 'uk'}">
                                            <c:choose>
                                                <c:when test="${empty faculty.nameUk}">
                                                    ${faculty.nameEn}
                                                </c:when>
                                                <c:otherwise>
                                                    ${faculty.nameUk}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            ${faculty.nameEn}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${faculty.numberOfRequestsNew()}</td>
                                <td>${faculty.numberOfRequestsRejected()}</td>
                                <td>${faculty.numberOfRequestsApproved()}</td>
                                <td>${faculty.budgetCapacity}</td>
                                <td>${faculty.totalCapacity}</td>
                                <td>
                                    <form class="form-inline my-2 my-lg-0"
                                          action="/controller" method="post">
                                        <input type="hidden" name="command" value="showRequestsListOfFaculty">
                                        <input type="hidden" name="facultyId" value="${faculty.id}">
                                        <button class="btn btn-primary btn-xs" type="submit"><fmt:message
                                                key="admin.requests"/></button>
                                    </form>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${faculty.admissionOpen==true}">

                                            <form class="form-inline my-2 my-lg-0" action="controller" method="post">
                                                <input type="hidden" name="command"
                                                       value="blockUnblockFacultyRegistration">
                                                <input type="hidden" name="action" value="block">
                                                <input type="hidden" name="facultyId" value="${faculty.id}">
                                                <button class="btn btn-warning btn-xs" type="submit"><fmt:message
                                                        key="admin.block"/></button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form class="form-inline my-2 my-lg-0" action="controller" method="post">
                                                <input type="hidden" name="command"
                                                       value="blockUnblockFacultyRegistration">
                                                <input type="hidden" name="action" value="unblock">
                                                <input type="hidden" name="facultyId" value="${faculty.id}">
                                                <button class="btn btn-success btn-xs" type="submit"><fmt:message
                                                        key="admin.unblock"/></button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>

                                </td>

                                <td>
                                    <form class="form-inline my-2 my-lg-0"
                                          action="controller" method="post">
                                        <input type="hidden" name="command" value="editFacultyForm">
                                        <input type="hidden" name="facultyId" value="${faculty.id}">

                                        <button class="btn btn-primary btn-xs" type="submit"><fmt:message
                                                key="admin.edit_faculty"/></button>

                                    </form>
                                </td>
                                <td class="col-lg-11 col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="controller" method="post">
                                        <input type="hidden" name="command" value="deleteFaculty">
                                        <input type="hidden" name="facultyId" value="${faculty.id}">
                                        <button class="btn btn-danger btn-xs" type="submit"><fmt:message
                                                key="admin.delete_faculty"/></button>
                                    </form>
                                </td>
                                <td class="col-lg-11 col-centered">
                                    <form class="form-inline my-2 my-lg-0"
                                          action="controller" method="post">
                                        <input type="hidden" name="command" value="getStatementOfFaculty">
                                        <input type="hidden" name="facultyId" value="${faculty.id}">
                                        <button class="btn btn-danger btn-xs" type="submit"><fmt:message
                                                key="admin.statement"/></button>
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
</html>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>