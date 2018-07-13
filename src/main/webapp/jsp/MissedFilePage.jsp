<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.ErrorPage" bundle="${rb}"/></title>
</head>
<body class="body">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<fmt:message key="message.forgotfile" bundle="${rb}"/>
<hr>
<form action="UploadPage"
      method="post">
    <input type="submit" value="<fmt:message key="label.button.UploadPage" bundle="${rb}"/>">
    <input type="hidden" name="action" value="UploadPage">
</form>
<fmt:message key="message.youcanswitch" bundle="${rb}"/>
<hr>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input type="submit" value="<fmt:message key="label.button.WelcomePage" bundle="${rb}"/>">
</form>
<form action="WelcomePage" method="post">
    <input type="hidden" name="action" value="InvalidateSession">
    <input type="submit" name="action" value="<fmt:message key="label.button.Logout" bundle="${rb}"/>">
</form>
</body>
</html>
