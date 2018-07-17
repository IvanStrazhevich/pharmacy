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
    <title><fmt:message key="label.button.RegisterPage"/>
        <hr>
    </title>
</head>
<body class="body">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<hr>
<fmt:message key="label.button.RegisterPage"/>
${userExist}
${needLogin}
<form action="RegisterUser" method=post>
    <div class="input-group">
        <label for="login"><fmt:message key="message.enterLogin"/></label>
        <div class="form-inline">
            <input type="text" id="login" name="login" size="45"
                   placeholder="<fmt:message key="message.enterLogin"/>">
        </div>
    </div>
    <div class=" input-group">
        <label for="password"><fmt:message key="message.enterPassword"/></label>
        <div class="form-inline">
            <input id="password" type="password" name="password" size="45"
                   placeholder="<fmt:message key="message.enterPassword"/>">
        </div>
    </div>
    <input type="hidden" size="15" name="action" value="RegisterUser">
    <input class="btn bg-success" type="submit" value="<fmt:message key="label.button.Register"/>">
    <input class="btn btn-warning" type="reset" value="<fmt:message key="label.button.Reset"/>">
</form>
<hr>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input class="btn btn-primary" type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
</form>
</body>
</html>
