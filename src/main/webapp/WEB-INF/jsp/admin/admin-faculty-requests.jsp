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
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">

            <li class="nav-item dropdown my-2">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><fmt:message key="navbar.Change_Language"/>
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">
                            <a class="dropdown-item" href="/controller?command=showRequestsListOfFaculty&facultyId=${faculty.id}&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=showRequestsListOfFaculty&facultyId=${faculty.id}&sessionLocale=uk">
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
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=allCandidates" role="button">
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

                        </form>
                    </li>
                </ul>
            </div>
        </ul>
    </div>
</nav>
<br>




<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h3 class="panel-title"><fmt:message key="admin.requests_of_faculty"/> </h3>
                        </div>

                    </div>
                </div>
                <div class="panel-body">
                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>

                            <th><fmt:message key="admin.requests_of_faculty.id"/> </th>
                            <th><fmt:message key="admin.requests_of_faculty.from"/>
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=candidate,ASC">&#8593</a>--%>
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=candidate,DESC">&#8595</a>--%>
                            </th>
                            <th><fmt:message key="admin.requests_of_faculty.to_the_faculty"/></th>
                            <th>Status
                                <fmt:message key="admin.requests_of_faculty.status"/>
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=admissionRequestStatus,ASC">&#8593</a>--%>
                                <%--                                <a href="${springMacroRequestContext.requestUri}?page=${page.getNumber()}&size=${page.size}&sort=admissionRequestStatus,DESC">&#8595</a>--%>
                            </th>
                            <th><fmt:message key="admin.requests_of_faculty.action"/></th>


                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="faculty" type="entity.Faculty" scope="request"/>
                        <c:forEach var="req" items="${faculty.admissionRequestList}">

                            <tr>

                                <td>${req.id}</td>
                                <td>${req.candidate.candidateProfile.firstName} ${req.candidate.candidateProfile.lastName}</td>
                                <td>    <c:choose>
                                    <c:when test="${sessionScope.lang eq 'uk'}">
                                        <c:choose>
                                            <c:when test="${empty req.faculty.nameUk}">
                                                ${req.faculty.nameEn}
                                            </c:when>
                                            <c:otherwise>
                                                ${req.faculty.nameUk}
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        ${req.faculty.nameEn}
                                    </c:otherwise>
                                </c:choose>


                                </td>
                                <td>${req.admissionRequestStatus.name()}</td>
                                <td>
                                    <form
                                            action="/controller" method="post">
                                        <input type="hidden" name="command" value="checkRequestFromFacultyReqList">
                                        <input type="hidden" name="requestId" value="${req.id}">
                                        <button class="btn btn-primary" type="submit"><fmt:message key="admin.requests_of_faculty.check_request"/></button>
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