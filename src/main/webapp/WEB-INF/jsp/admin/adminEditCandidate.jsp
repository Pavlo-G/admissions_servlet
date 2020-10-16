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
                               href="/controller?command=editCandidateForm&candidateId=${candidate.id}&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item"
                               href="/controller?command=editCandidateForm&candidateId=${candidate.id}&sessionLocale=uk">
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
    </div>
</nav>
<br>
<main class="registration-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">Create new faculty</div>
                    <div class="card-body">

                        <form action="controller" method="POST">
                            <input type="hidden" name="command" value="editCandidate">
                            <input type="hidden" name="candidateId" value="${candidate.id}">



                            <div class="form-group row">
                                <label  class="col-md-4 col-form-label text-md-right">Username :</label>
                                <div class="col-md-6 my-2">
                                    ${candidate.username}
                                </div>
                            </div>

                            <div class="form-group row">
                                <label class="col-md-4 col-form-label text-md-right">Email : </label>
                                <div class="col-md-6 my-2">
                                    ${candidate.candidateProfile.email}
                                </div>
                            </div>


                            <div class="form-group row">
                                <label  class="col-md-4 col-form-label text-md-right">Role Of Candidate : </label>
                                <div class="form-group">
                                    <select  class="custom-select" name ="role" required>
                                        <option selected="${candidate.getRole().name()}" value="USER">USER</option>
                                        <option value="ADMIN">ADMIN</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="candidateStatus" class="col-md-4 col-form-label text-md-right">Candidate Status :  </label>
                                <div class="form-group">
                                    <select class="custom-select" id="candidateStatus"  name="candidateStatus" required>
                                        <option value="ACTIVE">ACTIVE</option>
                                        <option value="BLOCKED">BLOCKED</option>
                                    </select>
                                </div>
                            </div>



                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Save
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