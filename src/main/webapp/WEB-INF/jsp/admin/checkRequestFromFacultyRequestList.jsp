
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp" />
<jsp:useBean  id="admissionRequest" type="entity.AdmissionRequest" scope="request"/>
<br>
<div class="container">
    <div class="row">
        <h2 class="text-danger">Request to the ${admissionRequest.faculty.name} </h2>

        <table class="table table-bordered success">
            <thead>
            <tr>
                <th>First Name</th>
                <td>${admissionRequest.candidate.candidateProfile.firstName}</td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td>${admissionRequest.candidate.candidateProfile.lastName}</td>
            </tr>
            <tr>
                <th >Email</th>
                <td>${admissionRequest.candidate.candidateProfile.email}</td>
            </tr>
            <tr>
                <th >Address</th>
                <td>${admissionRequest.candidate.candidateProfile.address}</td>
            </tr>

            <tr>
                <th >City</th>
                <td>${admissionRequest.candidate.candidateProfile.city}</td>
            </tr>
            <tr>
                <th >Region</th>
                <td>${admissionRequest.candidate.candidateProfile.region}</td>
            </tr>
            <tr>
                <th >School</th>
                <td>${admissionRequest.candidate.candidateProfile.school}</td>
            </tr>
            <tr>
                <th >Contact Number</th>
                <td>${admissionRequest.candidate.candidateProfile.phoneNumber}</td>
            </tr>

            <tr>
                <th >Grade for ${request.faculty.getRequiredSubject1()} </th>
                <td>${admissionRequest.getRequiredSubject1Grade()}</td>
            </tr>
            <tr>
                <th >Grade for ${request.faculty.getRequiredSubject2()}</th>
                <td>${admissionRequest.getRequiredSubject2Grade()}</td>
            </tr>
            <tr>
                <th >Grade for ${request.faculty.getRequiredSubject3()}</th>
                <td>${admissionRequest.getRequiredSubject3Grade()}</td>
            </tr>

            </thead>

        </table>

        <div class="container h-100">
            <div class="row h-100 justify-content-center align-items-center">

                <form action="/admin/request_update" method="POST">
                    <input type="hidden" id="id" name="id" value="${admissionRequest.id}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${admissionRequest.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="APPROVED">
                    <button class="btn btn-primary mr-2" type="submit">Approve</button>
                </form>
                <form action="/admin/request_update" method="POST">
                    <input type="hidden" id="id" name="id" value="${admissionRequest.id}">
                    <input type="hidden" id="facultyId" name="facultyId" value="${admissionRequest.faculty.id}">
                    <input type="hidden" id="admissionRequestStatus" name="admissionRequestStatus" value="REJECTED">
                    <button class="btn btn-danger mr-2" type="submit">Reject</button>
                </form>
                <form action="/admin/requests_of_faculty/${admissionRequest.faculty.id}" method="get">
                    <button class="btn btn-warning mr-2" type="submit"> Back</button>
                </form>

            </div>
        </div>
    </div>
</div>


</body>
</html>
