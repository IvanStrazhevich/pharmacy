<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.ErrorPage"/>
        <hr>
    </title>
</head>
<body class="body">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<fmt:message key="message.smthwentwrong"/>
<hr>
<p><img src="img/eddie1.gif" alt="Sorry, we alredy fix this" width="1000" height="500"></p>
<div class="floating">
    <form action="WelcomePage"
          method="post">
        <input type="hidden" name="action" value="WelcomePage">
        <input class="btn btn-primary" type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
    </form>
    <form action="WelcomePage" method="post">
        <input type="hidden" name="action" value="InvalidateSession">
        <input class="btn btn-danger" type="submit" value="<fmt:message key="label.button.Logout"/>">
    </form>
</div>
</body>
</html>
