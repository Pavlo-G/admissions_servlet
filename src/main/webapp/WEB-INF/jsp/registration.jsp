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
    <title>Admission Board Application Registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="/"><fmt:message
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

                            <a class="dropdown-item" href="/controller?command=registrationForm&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=registrationForm&sessionLocale=uk">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>


<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="reg.detail.please_fill_your_profile"/></div>
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${empty errorMessage}">
                            </c:when>
                            <c:otherwise>

                                <div class="alert alert-primary" role="alert">
                                        ${errorMessage}
                                </div>
                            </c:otherwise>
                        </c:choose>

                        <form id="registration" method="post" action="controller">
                            <input type="hidden" name="command" value="registration"/>


                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="login.username"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="username"
                                           class="form-control <c:if test="${not empty usernameError}">is-invalid</c:if>"
                                           name="username"
                                           placeholder="<fmt:message key="login.username"/>"
                                           value="<c:choose><c:when test="${not empty username}">${username}</c:when></c:choose>"/>

                                    <c:choose>
                                        <c:when test="${not empty usernameError}">
                                            <div class="invalid-feedback">
                                                    ${usernameError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="login.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password"
                                           class="form-control <c:if test="${not empty passwordError}">is-invalid</c:if>"
                                           name="password"
                                           placeholder="<fmt:message key="login.password"/>"
                                           />

                                    <c:choose>
                                        <c:when test="${not empty passwordError}">
                                            <div class="invalid-feedback">
                                                    ${passwordError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="firstName" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.first_name"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="firstName"
                                           class="form-control <c:if test="${not empty firstNameError}">is-invalid</c:if>"
                                           name="firstName"
                                           placeholder="<fmt:message key="reg.detail.first_name"/> "
                                           value="<c:choose><c:when test="${not empty firstName}">${firstName}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty firstNameError}">
                                            <div class="invalid-feedback">
                                                    ${firstNameError}
                                            </div>
                                        </c:when>
                                    </c:choose>

                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="lastName" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.last_name"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="lastName"
                                           class="form-control <c:if test="${not empty lastNameError}">is-invalid</c:if>"
                                           name="lastName"
                                           placeholder="<fmt:message key="reg.detail.last_name"/>"
                                           value="<c:choose><c:when test="${not empty lastName}">${lastName}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty lastNameError}">
                                            <div class="invalid-feedback">
                                                    ${lastNameError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.email"/></label>
                                <div class="col-md-6">
                                    <input type="email"
                                           id="email_address"
                                           class="form-control  <c:if test="${not empty emailError}">is-invalid</c:if>"
                                           name="email"
                                           placeholder="<fmt:message key="reg.detail.email"/>"
                                           value="<c:choose><c:when test="${not empty email}">${email}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty emailError}">
                                            <div class="invalid-feedback">
                                                    ${emailError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.address"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="address"
                                           class="form-control  <c:if test="${not empty addressError}">is-invalid</c:if>"
                                           name="address"
                                           placeholder="<fmt:message key="reg.detail.address"/>"
                                           value="<c:choose><c:when test="${not empty address}">${address}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty addressError}">
                                            <div class="invalid-feedback">
                                                    ${addressError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="city" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.city"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="city"
                                           class="form-control <c:if test="${not empty cityError}">is-invalid</c:if>"
                                           name="city"
                                           placeholder="<fmt:message key="reg.detail.city"/>"
                                           value="<c:choose><c:when test="${not empty city}">${city}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty cityError}">
                                            <div class="invalid-feedback">
                                                    ${cityError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="region" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.region"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="region"
                                           class="form-control <c:if test="${not empty regionError}">is-invalid</c:if>"
                                           name="region"
                                           placeholder="<fmt:message key="reg.detail.region"/>"
                                           value="<c:choose><c:when test="${not empty region}">${region}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty regionError}">
                                            <div class="invalid-feedback">
                                                    ${regionError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="school" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.school"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="school"
                                           class="form-control <c:if test="${not empty schoolError}">is-invalid</c:if>"
                                           name="school"
                                           placeholder="<fmt:message key="reg.detail.school"/>"
                                           value="<c:choose><c:when test="${not empty school}">${school}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty schoolError}">
                                            <div class="invalid-feedback">
                                                    ${schoolError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="phoneNumber" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="reg.detail.phoneNumber"/> </label>
                                <div class="col-md-6">
                                    <input type="text"
                                           id="phoneNumber"
                                           class="form-control <c:if test="${not empty phoneNumberError}">is-invalid</c:if>"
                                           name="phoneNumber"
                                           placeholder="xxx-xxx-xxx"
                                           value="<c:choose><c:when test="${not empty phoneNumber}">${phoneNumber}</c:when></c:choose>"/>
                                    <c:choose>
                                        <c:when test="${not empty phoneNumberError}">
                                            <div class="invalid-feedback">
                                                    ${phoneNumberError}
                                            </div>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="login.registration"/>
                                </button>
                                <a href="/" class="btn btn-link"> <fmt:message key="login.back"/></a>
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