<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<head>


    <title>Login as Candidate</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>


<body>
<jsp:include page="/WEB-INF/jsp/fragments/navbar-log-reg.jsp" />
<section>

    <br>
    <form class="mx-auto" style="width: 200px;" method="post" id="login_form" action="/controller" >
        <input type="hidden"  name="command" value="login" >
        <dl>
            <dt>Login:</dt>
            <dd><input type="text"  name="username" required></dd>
        </dl>
        <dl>
            <dt>Password:</dt>
            <dd><input type="password"   name="password" required></dd>
        </dl>
        <button type="submit">Login</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
