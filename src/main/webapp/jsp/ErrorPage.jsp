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
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
</form>
<form action="WelcomePage" method="post">
    <input type="hidden" name="action" value="InvalidateSession">
    <input type="submit" value="<fmt:message key="label.button.Logout"/>">
</form>
</body>
</html>
