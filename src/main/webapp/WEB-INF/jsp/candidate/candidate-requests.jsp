<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Requests of Candidate</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <c:choose>
        <c:when test="${sessionScope.candidate.role=='USER'}">
            <a class="navbar-brand" href="/controller?command=facultiesList"><fmt:message
                    key="navbar.Admission_Board_App"/></a>
        </c:when>
        <c:otherwise>
            <a class="navbar-brand" href="/controller?command=adminWorkspace"><fmt:message
                    key="navbar.Admission_Board_App"/></a>
        </c:otherwise>
    </c:choose>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav">

            <li class="nav-item dropdown">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><fmt:message key="navbar.Change_Language"/>
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">
                            <a class="dropdown-item" href="/controller?command=getCandidateRequestsList&sessionLocale=en&facultyId=${faculty.id}">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=getCandidateRequestsList&sessionLocale=uk&facultyId=${faculty.id}">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>


            <c:choose>
            <c:when test="${sessionScope.candidate!=null}">
            <div class="nav-item dropdown ">
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

            </c:when>
            <c:otherwise>
            <div>

                <form class="form-inline my-2 mr-2 my-lg-0">
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=loginForm" role="button">
                        <fmt:message key="navbar.login"/></a>
                </form>
            </div>
            <div>

                <form class="form-inline my-2 my-lg-0">
                    <a class="btn btn-primary my-2 my-sm-0" href="/controller?command=registrationForm" role="button">
                        <fmt:message key="navbar.registration"/></a>
                </form>

            </div>

            </c:otherwise>
            </c:choose>
    </div>
</nav>






<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">

            <div class="panel panel-default panel-table">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col col-xs-6">
                            <h2 class="text-danger"><fmt:message key="candidate.admission.requests"/> </h2>
                        </div>
                    </div>
                </div>
                <div class="panel-body">

                    <table class="table table-striped table-bordered table-list">
                        <thead>
                        <tr>

                            <th><fmt:message key="candidate.admission.requests.id"/></th>
                            <th><fmt:message key="candidate.admission.requests.first.name"/></th>
                            <th><fmt:message key="candidate.admission.requests.last.name"/></th>
                            <th><fmt:message key="candidate.admission.requests.to.the.faculty"/></th>
                            <th><fmt:message key="candidate.admission.requests.status"/></th>
                            <th><fmt:message key="candidate.admission.requests.datetime"/></th>


                            <th align="center"><fmt:message key="candidate.admission.requests.delete"/></th>
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
                                        <button class="btn btn-primary" type="submit"><fmt:message key="candidate.admission.requests.delete"/></button>
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