
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>


    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>


<body>
<jsp:include page="/WEB-INF/jsp/fragments/navbar-log-reg.jsp" />
<section>

  <br>

    <form  id="registration" style="width: 200px;" method="post" action="controller" >
        <input type="hidden" name="command" value="registration"/>
        <input type="hidden" name="id" >
        <dl>
            <dt>Username:</dt>
            <dd><input type="text"  name="username" required></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="password"   name="password" required></dd>
        </dl>
        <dl>
            <dt>Email:</dt>
            <dd><input type="email" name="email" required></dd>
        </dl>
        <dl>
            <dt>First Name:</dt>
            <dd><input type="text" name="firstName" required></dd>
        </dl>
        <dl>
            <dt>Last Name:</dt>
            <dd><input type="text" name="lastName" required></dd>
        </dl>

        <dl>
            <dt>Address:</dt>
            <dd><input type="text" name="address" required></dd>
        </dl>
        <dl>
            <dt>City:</dt>
            <dd><input type="text" name="city" required></dd>
        </dl>
        <dl>
            <dt>Region:</dt>
            <dd><input type="text" name="region" required></dd>
        </dl>
        <dl>
            <dt>School:</dt>
            <dd><input type="text" name="school" required></dd>
        </dl>
        <dl>
            <dt>ContactNumber:</dt>
            <dd><input type="text" name="phoneNumber" required></dd>
        </dl>


        <button type="submit">Register</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
