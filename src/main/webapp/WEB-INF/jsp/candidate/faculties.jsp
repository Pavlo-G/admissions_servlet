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
    <title>Admission Board Application</title>
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

                            <a class="dropdown-item" href="/controller?command=facultiesList&sessionLocale=en">
                                <fmt:message key="navbar.English"/></a>
                            <a class="dropdown-item" href="/controller?command=facultiesList&sessionLocale=uk">
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



<div class="row">

    <div class="container">

        <h3 class="text-center"><fmt:message key="faculty.list_of_faculties"/></h3>
        <hr>


        <%--    For displaying Items per page--%>
        <div class="mt-3">
            <ul class="pagination">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message key="faculty.items_per_page"/></a>
                </li>


                <c:forEach var="ar" items="${itemsPerPageArray}">
                    <c:choose>
                        <c:when test="${itemsPerPage eq ar}">
                            <li class="page-item active">
                                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">${ar}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${ar}&sortBy=${sortBy}&sortDir=${sortDir}"
                                   tabindex="-1"
                                   aria-disabled="true">${ar}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><fmt:message key="faculty.name"/>
                    <a href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${itemsPerPage}&sortBy=name&sortDir=ASC">&#8593</a>
                    <a href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${itemsPerPage}&sortBy=name&sortDir=DESC">&#8595</a>
                </th>
                <th><fmt:message key="faculty.description"/></th>
                <th><fmt:message key="faculty.total_capacity"/>
                    <a href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${itemsPerPage}&sortBy=total_capacity&sortDir=ASC">&#8593</a>
                    <a href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${itemsPerPage}&sortBy=total_capacity&sortDir=DESC">&#8595</a>
                </th>
                <th><fmt:message key="faculty.budget_capacity"/>
                    <a href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${itemsPerPage}&sortBy=budget_capacity&sortDir=ASC">&#8593</a>
                    <a href="/controller?command=facultiesList&page=${currentPage}&itemsPerPage=${itemsPerPage}&sortBy=budget_capacity&sortDir=DESC">&#8595</a>
                </th>
                <th><fmt:message key="faculty.submit_request"/></th>


            </tr>
            </thead>
            <tbody>

            <c:forEach var="faculty" items="${facultiesList}">
                <jsp:useBean id="faculty" type="entity.Faculty"/>

                <tr>
                    <td>
                        <c:out value="${faculty.name}"/>

                    </td>
                    <td>
                        <c:out value="${faculty.description}"/>
                    </td>
                    <td>
                        <c:out value="${faculty.totalCapacity}"/>
                    </td>
                    <td>
                        <c:out value="${faculty.budgetCapacity}"/>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${faculty.admissionOpen==true}">
                                <form action="/controller">
                                    <input type="hidden" name="command" value="getSubmitRequestForm">
                                    <input type="hidden" name="facultyId" value="${faculty.id}">
                                    <input type="hidden" name="facultyName" value="${faculty.name}">
                                    <button class="btn btn-success"><fmt:message key="faculty.submit_request"/></button>
                                </form>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>
        </table>




        <div class="mt-3">
            <ul class="pagination ">
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message key="faculty.page"/></a>
                </li>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <li class="page-item disabled">
                                <a class="page-link"
                                   href="#">${i}</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link"
                                   href="/controller?command=facultiesList&page=${i}&sortBy=${sortBy}&sortDir=${sortDir}&itemsPerPage=${itemsPerPage}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
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