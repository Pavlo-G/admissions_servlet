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

            <li class="nav-item dropdown">
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"
                                            data-target="#navbarDropdown"><fmt:message key="navbar.Change_Language"/>
                        <b class="caret"></b></a>
                        <div class="dropdown-menu dropdown-menu-right" id="navbarDropdown">
                            <a class="dropdown-item"
                               href="/controller?command=checkRequestFromFacultyReqList&requestId=${admissionRequest.id}&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item"
                               href="/controller?command=checkRequestFromFacultyReqList&requestId=${admissionRequest.id}&sessionLocale=uk">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>

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
        </ul>
    </div>
</nav>
<jsp:useBean id="admissionRequest" type="entity.AdmissionRequest" scope="request"/>
<br>
<div class="container">
    <div class="row">
        <h2 class="text-danger"><fmt:message key="admin.check_request.request_to_the"/>
            <c:choose>
                <c:when test="${sessionScope.lang eq 'uk'}">
                    <c:choose>
                        <c:when test="${empty admissionRequest.faculty.nameUk}">
                            ${admissionRequest.faculty.nameEn}
                        </c:when>
                        <c:otherwise>
                            ${admissionRequest.faculty.nameUk}
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    ${admissionRequest.faculty.nameEn}
                </c:otherwise>
            </c:choose>
        </h2>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <th><fmt:message key="reg.detail.first_name"/></th>
                <td>${admissionRequest.candidate.candidateProfile.firstName}</td>
            </tr>
            <tr>
                <th><fmt:message key="reg.detail.last_name"/></th>
                <td>${admissionRequest.candidate.candidateProfile.lastName}</td>
            </tr>
            <tr>
                <th><fmt:message key="reg.detail.email"/></th>
                <td>${admissionRequest.candidate.candidateProfile.email}</td>
            </tr>
            <tr>
                <th><fmt:message key="reg.detail.address"/></th>
                <td>${admissionRequest.candidate.candidateProfile.address}</td>
            </tr>

            <tr>
                <th><fmt:message key="reg.detail.city"/></th>
                <td>${admissionRequest.candidate.candidateProfile.city}</td>
            </tr>
            <tr>
                <th><fmt:message key="reg.detail.region"/></th>
                <td>${admissionRequest.candidate.candidateProfile.region}</td>
            </tr>
            <tr>
                <th><fmt:message key="reg.detail.school"/></th>
                <td>${admissionRequest.candidate.candidateProfile.school}</td>
            </tr>
            <tr>
                <th><fmt:message key="reg.detail.phoneNumber"/></th>
                <td>${admissionRequest.candidate.candidateProfile.phoneNumber}</td>
            </tr>

            <tr>
                <th><fmt:message key="candidate.admission.requests.grade.for"/>
                    <c:choose>
                        <c:when test="${sessionScope.lang eq 'uk'}">
                            <c:choose>
                                <c:when test="${empty admissionRequest.faculty.requiredSubject1Uk}">
                                    ${admissionRequest.faculty.requiredSubject1En}
                                </c:when>
                                <c:otherwise>
                                    ${admissionRequest.faculty.requiredSubject1Uk}
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            ${admissionRequest.faculty.requiredSubject1En}
                        </c:otherwise>
                    </c:choose>

                </th>
                <td>${admissionRequest.requiredSubject1Grade}</td>
            </tr>
            <tr>
                <th><fmt:message key="candidate.admission.requests.grade.for"/>
                    <c:choose>
                        <c:when test="${sessionScope.lang eq 'uk'}">
                            <c:choose>
                                <c:when test="${empty admissionRequest.faculty.requiredSubject2Uk}">
                                    ${admissionRequest.faculty.requiredSubject2En}
                                </c:when>
                                <c:otherwise>
                                    ${admissionRequest.faculty.requiredSubject2Uk}
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            ${admissionRequest.faculty.requiredSubject2En}
                        </c:otherwise>
                    </c:choose>


                </th>
                <td>${admissionRequest.requiredSubject2Grade}</td>
            </tr>
            <tr>
                <th><fmt:message key="candidate.admission.requests.grade.for"/>
                    <c:choose>
                        <c:when test="${sessionScope.lang eq 'uk'}">
                            <c:choose>
                                <c:when test="${empty admissionRequest.faculty.requiredSubject3Uk}">
                                    ${admissionRequest.faculty.requiredSubject3En}
                                </c:when>
                                <c:otherwise>
                                    ${admissionRequest.faculty.requiredSubject3Uk}
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            ${admissionRequest.faculty.requiredSubject3En}
                        </c:otherwise>
                    </c:choose>

                </th>
                <td>${admissionRequest.requiredSubject3Grade}</td>
            </tr>

            </thead>

        </table>

        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">

                <form action="controller" method="POST">
                    <input type="hidden" id="command" name="command" value="changeAdmissionRequestStatus">
                    <input type="hidden" id="id" name="id" value="${admissionRequest.id}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${admissionRequest.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="APPROVED">
                    <button class="btn btn-primary mr-2" type="submit"><fmt:message
                            key="admin.check_request.approve"/></button>
                </form>
                <form action="controller" method="POST">
                    <input type="hidden" id="command" name="command" value="changeAdmissionRequestStatus">
                    <input type="hidden" id="id" name="id" value="${admissionRequest.id}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${admissionRequest.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="REJECTED">
                    <button class="btn btn-danger mr-2" type="submit"><fmt:message
                            key="admin.check_request.reject"/></button>
                </form>
                <form>
                    <button class="btn btn-warning mr-2" type="button"><a
                            href="/controller?command=showRequestsListOfFaculty&facultyId=${admissionRequest.faculty.id}"><fmt:message
                            key="admin.check_request.back"/></a></button>
                </form>

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