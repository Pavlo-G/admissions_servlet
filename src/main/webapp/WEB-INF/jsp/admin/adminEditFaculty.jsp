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
    <title>Create Faculty</title>
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
                            <a class="dropdown-item"
                               href="/controller?command=editFacultyForm
                               &facultyId=<c:choose><c:when test="${not empty facultyId}">${facultyId}</c:when><c:otherwise>${faculty.id}</c:otherwise> </c:choose>
                               &sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item"
                               href="/controller?command=editFacultyForm
                               &facultyId=<c:choose><c:when test="${not empty facultyId}">${facultyId}</c:when><c:otherwise>${faculty.id}</c:otherwise> </c:choose>
                               &sessionLocale=uk">
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

                        </form>
                    </li>
                </ul>
            </div>
        </ul>
    </div>
</nav>


<%--<jsp:useBean id="faculty" scope="request" type="model.entity.Faculty"/>--%>

<br>
<main class="registration-form">
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
                    <div class="card-header"><fmt:message key="faculty.update_faculty_information"/></div>
                    <div class="card-body">

                        <form action="controller" method="POST">
                            <input type="hidden" name="command" value="updateFaculty">
                            <input type="hidden" name="facultyId" value="<c:choose><c:when test="${not empty facultyId}">${facultyId}</c:when><c:otherwise>${faculty.id}</c:otherwise></c:choose>">
                            <div class="form-group row">
                                <label for="nameEn" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.name.en"/>

                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="nameEn"
                                           class="form-control <c:if test="${not empty nameEnError}">is-invalid</c:if>"
                                           name="nameEn"
                                           value="<c:choose><c:when test="${not empty nameEn}">${nameEn}</c:when><c:otherwise>${faculty.nameEn}</c:otherwise> </c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty nameEnError}">
                                            <div class="invalid-feedback">
                                                    ${nameEnError}
                                            </div>
                                        </c:when>
                                    </c:choose>


                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="nameUk" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.name.uk"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="nameUk"
                                           class="form-control <c:if test="${not empty nameUkError}">is-invalid</c:if>"
                                           name="nameUk"
                                           value="<c:choose><c:when test="${not empty nameUk}">${nameUk}</c:when><c:otherwise>${faculty.nameUk}</c:otherwise> </c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty nameUkError}">
                                            <div class="invalid-feedback">
                                                    ${nameUkError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="descriptionEn" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.description.en"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="descriptionEn"
                                           class="form-control <c:if test="${not empty descriptionEnError}">is-invalid</c:if>"
                                           name="descriptionEn"
                                           value="<c:choose><c:when test="${not empty descriptionEn}">${descriptionEn}</c:when><c:otherwise>${faculty.descriptionEn}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty descriptionEnError}">
                                            <div class="invalid-feedback">
                                                    ${descriptionEnError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="descriptionUk" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.description.uk"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="descriptionUk"
                                           class="form-control <c:if test="${not empty descriptionUkError}">is-invalid</c:if>"
                                           name="descriptionUk"
                                           value="<c:choose><c:when test="${not empty descriptionUk}">${descriptionUk}</c:when><c:otherwise>${faculty.descriptionUk}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty descriptionUkError}">
                                            <div class="invalid-feedback">
                                                    ${descriptionUkError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="budgetCapacity" class="col-md-4 col-form-label text-md-right">
                                    <fmt:message key="admin.budget_capacity"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="budgetCapacity"
                                           class="form-control <c:if test="${not empty budgetCapacityError}">is-invalid</c:if>"
                                           name="budgetCapacity"
                                           value="<c:choose><c:when test="${not empty budgetCapacity}">${budgetCapacity}</c:when><c:otherwise>${faculty.budgetCapacity}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty budgetCapacityError}">
                                            <div class="invalid-feedback">
                                                    ${budgetCapacityError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="totalCapacity" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="admin.total_capacity"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="totalCapacity"
                                           class="form-control <c:if test="${not empty totalCapacityError}">is-invalid</c:if>"
                                           name="totalCapacity"
                                           value="<c:choose><c:when test="${not empty totalCapacity}">${totalCapacity}</c:when><c:otherwise>${faculty.totalCapacity}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty totalCapacityError}">
                                            <div class="invalid-feedback">
                                                    ${totalCapacityError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject1En"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.required_subject1_en"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject1En"
                                           class="form-control <c:if test="${not empty requiredSubject1EnError}">is-invalid</c:if>"
                                           name="requiredSubject1En"
                                           value="<c:choose><c:when test="${not empty requiredSubject1En}">${requiredSubject1En}</c:when><c:otherwise>${faculty.requiredSubject1En}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject1EnError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject1EnError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject1Uk"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.required_subject1_uk"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject1Uk"
                                           class="form-control <c:if test="${not empty requiredSubject1UkError}">is-invalid</c:if>"
                                           name="requiredSubject1Uk"
                                           value="<c:choose><c:when test="${not empty requiredSubject1Uk}">${requiredSubject1Uk}</c:when><c:otherwise>${faculty.requiredSubject1Uk}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject1UkError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject1UkError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="requiredSubject2En"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.required_subject2_en"/>
                                </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject2En"
                                           class="form-control <c:if test="${not empty requiredSubject2EnError}">is-invalid</c:if>"
                                           name="requiredSubject2En"
                                           value="<c:choose><c:when test="${not empty requiredSubject2En}">${requiredSubject2En}</c:when><c:otherwise>${faculty.requiredSubject2En}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject2EnError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject2EnError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="requiredSubject2Uk"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.required_subject2_uk"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject2Uk"
                                           class="form-control <c:if test="${not empty requiredSubject2UkError}">is-invalid</c:if>"
                                           name="requiredSubject2Uk"
                                           value="<c:choose><c:when test="${not empty requiredSubject2Uk}">${requiredSubject2Uk}</c:when><c:otherwise>${faculty.requiredSubject2Uk}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject2UkError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject2UkError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="requiredSubject3En"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.required_subject3_en"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject3En"
                                           class="form-control <c:if test="${not empty requiredSubject3EnError}">is-invalid</c:if>"
                                           name="requiredSubject3En"
                                           value="<c:choose><c:when test="${not empty requiredSubject3En}">${requiredSubject3En}</c:when><c:otherwise>${faculty.requiredSubject3En}</c:otherwise></c:choose>"
                                    />
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject3EnError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject3EnError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject3Uk"
                                       class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="faculty.required_subject3_uk"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="requiredSubject3Uk"
                                           class="form-control <c:if test="${not empty requiredSubject3UkError}">is-invalid</c:if>"
                                           name="requiredSubject3Uk"
                                           value="<c:choose><c:when test="${not empty requiredSubject3Uk}">${requiredSubject3Uk}</c:when><c:otherwise>${faculty.requiredSubject3Uk}</c:otherwise></c:choose>"
                                           autofocus/>
                                    <c:choose>
                                        <c:when test="${not empty requiredSubject3UkError}">
                                            <div class="invalid-feedback">
                                                    ${requiredSubject3UkError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="faculty.update"/>
                                </button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>


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
