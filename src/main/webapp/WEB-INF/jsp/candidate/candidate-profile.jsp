<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10.10.2020
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Candidate Profile</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp" />
<br>
<jsp:useBean id="candidateProfile" type="entity.CandidateProfile" scope="request"/>

<div class="container">
    <div class="row">
        <h2 class="text-danger">Candidate Profile</h2>
        <form class="form-inline ml-auto my-2 my-lg-0">
        <a class="btn btn-primary" href="/controller?command=candidateProfileEdit" role="button">Edit Profile</a>
        </form>

        <table class="table table-bordered success">
            <thead>

            <tr>
                <th>First Name</th>
                <td>${candidateProfile.firstName}</td>
            </tr>
            <tr>
                <th class="info">Last Name</th>
                <td>${candidateProfile.lastName}</td>
            </tr>
            <tr>
                <th class="info">Email</th>
                <td>${candidateProfile.email}</td>
            </tr>

            <tr>
                <th class="info">Address</th>
                <td>${candidateProfile.address}</td>
            </tr>
            <tr>
                <th class="info">City</th>
                <td>${candidateProfile.city}</td>
            </tr>

            <tr>
                <th class="info">Region</th>
                <td>${candidateProfile.region}</td>
            </tr>
            <tr>
                <th class="info">School</th>
                <td>${candidateProfile.school}</td>
            </tr>
            <tr>
                <th class="info">Phone number</th>
                <td>${candidateProfile.phoneNumber}</td>
            </tr>

            </thead>

        </table>
    </div>
</div>
</body>
</html>
