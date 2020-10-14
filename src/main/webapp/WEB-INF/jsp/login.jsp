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
    <title>Admission Board Application Login</title>
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

                            <a class="dropdown-item" href="/controller?command=loginForm&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=loginForm&sessionLocale=uk">
                                <fmt:message key="navbar.Ukrainian"/></a>
                        </div>

                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>


<br>
<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="login.user_login"/></div>
                    <div class="card-body">


                        <form class="mx-auto"  method="post" id="login_form" action="/controller">
                            <input type="hidden" name="command" value="login">

                            <div class="form-group row">
                                <label for="username" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="login.username"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="username" class="form-control" name="username"  placeholder="<fmt:message key="login.username"/>" required autofocus>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right"><fmt:message
                                        key="login.password"/></label>
                                <div class="col-md-6">
                                    <input type="password" id="password" class="form-control" name="password"
                                           placeholder="<fmt:message key="login.password"/>" required>
                                </div>
                            </div>


                            <div class="col-md-6 offset-md-6">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="login.login"/>
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