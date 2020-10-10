<%@ page  contentType="text/html; charset=UTF-8"
          pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<c:set var="title" value="Faculties" scope="page" />
<head>
    <title>Admission Board Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
<br>
<jsp:include page="fragments/navbar.jsp" />

<div class="row">

    <div class="container">

        <h3 class="text-center">List of Faculties</h3>
        <hr>
        <div class="container text-left">

        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Total Capacity</th>
                <th>Budget Capacity</th>
                <th>Submit Request</th>



            </tr>
            </thead>
            <tbody>

            <c:forEach var="faculty" items="${facultiesList}">
                <jsp:useBean id="faculty" type="entity.Faculty"/>

                <tr>
                    <td>
                        <c:out value="${faculty.name}" />
                    </td>
                    <td>
                        <c:out value="${faculty.description}" />
                    </td>
                    <td>
                        <c:out value="${faculty.totalCapacity}" />
                    </td>
                    <td>
                        <c:out value="${faculty.budgetCapacity}" />
                    </td>
                    <td><form action="/controller"  >
                        <input type="hidden" name="command" value="submitRequest">
                        <button  class="btn btn-success">Submit Admission Request</button>
                    </form></td>
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>
</body>

</html>