<%--
  Created by IntelliJ IDEA.
  User: Pasha
  Date: 10.10.2020
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="../fragments/navbar.jsp" />
<jsp:useBean id="candidate" type="entity.Candidate" scope="request"/>
<jsp:useBean id="faculty" type="entity.Faculty" scope="request"/>
<br>
<main class="login-form">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8">
<%--                <#if errorMessage??>--%>
<%--                <div class="alert alert-primary" role="alert">--%>
<%--                    ${errorMessage}--%>

<%--                    <form method="get" action="/faculties">--%>
<%--                        <button type="submit" class="btn btn-primary">--%>
<%--                            Go back to faculties list--%>
<%--                        </button>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <#else>--%>
                <div class="card">
                    <div class="card-header">Admission request to ${faculty.name} </div>
                    <div class="card-body">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="submitRequest">

                            <div class="form-group row">
                                <label for="first_name" class="col-md-4 col-form-label text-md-right">First
                                    Name </label>
                                <div class="col-md-6" id="first_name">
                                    ${candidate.candidateProfile.firstName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="last_name" class="col-md-4 col-form-label text-md-right">Last
                                    Name </label>
                                <div class="col-md-6" id="last_name">
                                    ${candidate.candidateProfile.lastName}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right">E-Mail
                                    Address</label>
                                <div class="col-md-6" id="email_address">
                                    ${candidate.candidateProfile.email}
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="address" class="col-md-4 col-form-label text-md-right">Address </label>
                                <div class="col-md-6" id="address">
                                    ${candidate.candidateProfile.address}
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="phone_number" class="col-md-4 col-form-label text-md-right">Contact
                                    Number
                                </label>
                                <div class="col-md-6" id="phone_number">
                                    ${candidate.candidateProfile.phoneNumber}
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject1Grade" class="col-md-4 col-form-label text-md-right">Grade For ${faculty.requiredSubject1} </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject1Grade" min="1" max="12" class="form-control" name="requiredSubject1Grade" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject2Grade" class="col-md-4 col-form-label text-md-right">Grade For ${faculty.requiredSubject2} </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject2Grade" min="1" max="12" class="form-control" name="requiredSubject2Grade" required>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="requiredSubject3Grade"  class="col-md-4 col-form-label text-md-right">Grade For ${faculty.requiredSubject3} </label>
                                <div class="col-md-6">
                                    <input type="number" id="requiredSubject3Grade" min="1" max="12" class="form-control" name="requiredSubject3Grade" required>
                                </div>
                            </div>


                            <input type="hidden" id="candidateId" name="candidateId" value="${candidate.id}"/>
                            <input type="hidden" id="facultyId" name="facultyId" value="${faculty.id}"/>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    Submit Request
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
