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
    </title>
</head>
<body>
<fmt:message key="message.pagenotfound"/>
<p><img src="img/eddie.gif" alt="Sorry, we alredy fix this" width="1000" height="500"></p>
<div>
    <form action="WelcomePage" method="post">
        <input type="hidden" name="action" value="WelcomePage">
        <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
            <input class="form-control btn btn-success" type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
        </div>
    </form>
    <form action="WelcomePage" method="post">
        <input type="hidden" name="action" value="InvalidateSession">
        <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
            <input class="form-control btn btn-danger" type="submit" value="<fmt:message key="label.button.Logout"/>">
        </div>
    </form>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
