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
    <title>Form to submit admission request</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>

<%--<jsp:useBean id="candidate" type="model.entity.Candidate" scope="request"/>--%>
<%--<jsp:useBean id="faculty" type="model.entity.Faculty" scope="request"/>--%>
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

                            <a class="dropdown-item" href="/controller?command=getSubmitRequestForm&sessionLocale=en&facultyId=${faculty.id}">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=getSubmitRequestForm&sessionLocale=uk&facultyId=${faculty.id}">
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
            </c:choose>
    </div>
</nav>



<br>
<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <c:choose>
                    <c:when test="${empty errorMessage}">
                    </c:when>
                    <c:otherwise>

                        <div class="alert alert-primary" role="alert">
                                ${errorMessage}
                        </div>
                    </c:otherwise>
                </c:choose>

                <div class="card">
                    <div class="card-header"><fmt:message key="candidate.admission.requests_to" />
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
                    </div>
                    <div class="card-body">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="submitRequest">

                            <div class="form-group row">
                                <label for="first_name" class="col-md-4 col-form-label text-md-right"><fmt:message key="candidate.admission.requests.first.name" /></label>
                                <div class="col-md-6" id="first_name">
                                    ${candidate.candidateProfile.firstName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="last_name" class="col-md-4 col-form-label text-md-right"><fmt:message key="candidate.admission.requests.last.name" /></label>
                                <div class="col-md-6" id="last_name">
                                    ${candidate.candidateProfile.lastName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><fmt:message key="candidate.admission.requests.email" /></label>
                                <div class="col-md-6" id="email_address">
                                    ${candidate.candidateProfile.email}
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right"><fmt:message key="candidate.admission.requests.address" /> </label>
                                <div class="col-md-6" id="address">
                                    ${candidate.candidateProfile.address}
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="phone_number" class="col-md-4 col-form-label text-md-right"><fmt:message key="candidate.admission.requests.phone_number" />
                                </label>
                                <div class="col-md-6" id="phone_number">
                                    ${candidate.candidateProfile.phoneNumber}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject1Grade" class="col-md-4 col-form-label text-md-right">
                                    <fmt:message key="candidate.admission.requests.grade.for" />

                                    <c:choose>
                                        <c:when test="${sessionScope.lang eq 'uk'}">
                                            <c:choose>
                                                <c:when test="${empty faculty.requiredSubject1Uk}">
                                                    ${faculty.requiredSubject1En}
                                                </c:when>
                                                <c:otherwise>
                                                    ${faculty.requiredSubject1Uk}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            ${faculty.requiredSubject1En}
                                        </c:otherwise>
                                    </c:choose>


                                </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject1Grade"
                                           class="form-control <c:if test="${not empty requiredSubject1GradeError}">is-invalid</c:if>"
                                           name="requiredSubject1Grade"
                                           value = "<c:choose><c:when test="${not empty requiredSubject1Grade}">${requiredSubject1Grade}</c:when></c:choose>">
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject1GradeError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject1GradeError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject2Grade" class="col-md-4 col-form-label text-md-right">
                                    <fmt:message key="candidate.admission.requests.grade.for" />
                                    <c:choose>
                                        <c:when test="${sessionScope.lang eq 'uk'}">
                                            <c:choose>
                                                <c:when test="${empty faculty.requiredSubject2Uk}">
                                                    ${faculty.requiredSubject2En}
                                                </c:when>
                                                <c:otherwise>
                                                    ${faculty.requiredSubject2Uk}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            ${faculty.requiredSubject2En}
                                        </c:otherwise>
                                    </c:choose>

                                </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject2Grade"
                                           class="form-control <c:if test="${not empty requiredSubject2GradeError}">is-invalid</c:if>"
                                           name="requiredSubject2Grade"
                                           value = "<c:choose><c:when test="${not empty requiredSubject2Grade}">${requiredSubject2Grade}</c:when></c:choose>">
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject2GradeError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject2GradeError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject3Grade"  class="col-md-4 col-form-label text-md-right">
                                    <fmt:message key="candidate.admission.requests.grade.for" />
                                    <c:choose>
                                        <c:when test="${sessionScope.lang eq 'uk'}">
                                            <c:choose>
                                                <c:when test="${empty faculty.requiredSubject3Uk}">
                                                    ${faculty.requiredSubject3En}
                                                </c:when>
                                                <c:otherwise>
                                                    ${faculty.requiredSubject3Uk}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            ${faculty.requiredSubject3En}
                                        </c:otherwise>
                                    </c:choose>

                                </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject3Grade"
                                           class="form-control <c:if test="${not empty requiredSubject3GradeError}">is-invalid</c:if>"
                                           name="requiredSubject3Grade"
                                           value = "<c:choose><c:when test="${not empty requiredSubject3Grade}">${requiredSubject3Grade}</c:when></c:choose>">
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject3GradeError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject3GradeError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>


                            <input type="hidden" id="candidateId" name="candidateId" value="<c:choose><c:when test="${not empty candidateId}">${candidateId}</c:when><c:otherwise>${candidate.id}</c:otherwise></c:choose>"/>
                            <input type="hidden" id="facultyId" name="facultyId" value="<c:choose><c:when test="${not empty facultyId}">${facultyId}</c:when><c:otherwise>${faculty.id}</c:otherwise></c:choose>"/>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="faculty.submit_request" />
                                </button>
                            </div>

                        </form>

                </div>
            </div>
        </div>
    </div>
    </div>

</main>
<br>
<br>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"></jsp:include>

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