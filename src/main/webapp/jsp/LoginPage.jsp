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
    <title><fmt:message key="label.button.LoginPage"/>
        <hr>
    </title>
</head>
<body class="body">
${notAuthorised}
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<hr>
<fmt:message key="label.button.LoginPage"/>
${userNotRegistered}
${needRegister}
${needLogin}
<form action="CheckLogin" method=post>


    <div class="input-group">
        <label for="login"><fmt:message key="message.enterLogin"/></label>
        <div class="form-inline">
            <input type="text" id="login" name="login" size="45" maxlength="45" pattern="\w{6,}@\w{3,}\.\w{2,4}" required title="Match email format no more then 45 symbols at least 6x+@3x+.2x(3x)">
        </div>
    </div>
    <div class="input-group">
        <label for="password"><fmt:message key="message.enterPassword"/></label>
        <div class="form-inline">
            <input id="password" type="password" name="password" size="45" maxlength="45">
        </div>
    </div>
    <input type="hidden" name="action" value="CheckLogin">
    <input class="btn btn-success" type="submit" value="<fmt:message key="label.button.Submit"/>">
    <input class="btn btn-warning" type="reset" value="<fmt:message key="label.button.Reset"/>">

</form>
<hr>
<form action="RegisterPage"
      method="post">
    <input type="hidden" name="action" value="RegisterPage">
    <input class="btn bg-info" type="submit" value="<fmt:message key="label.button.RegisterPage"/>">
</form>
</body>
</html>
