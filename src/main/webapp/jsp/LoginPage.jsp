<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.LoginPage"/>
        <hr>
    </title>
</head>
<body class="body">
<hr>
<fmt:message key="label.button.LoginPage"/>
${userNotRegistered}
${needRegister}
${needLogin}
<form action="CheckLogin" method=post>
    <p><strong><fmt:message key="message.enterLogin"/> </strong>
        <input type="text" name="login" size="15">
    <p>
    <p><strong><fmt:message key="message.enterPassword"/> </strong>
        <input type="password" name="password" size="15">
    <p>
    <p>
        <input type="hidden" size="15" name="action" value="CheckLogin">
        <input type="submit" value="<fmt:message key="label.button.Submit"/>">
        <input type="reset" value="<fmt:message key="label.button.Reset"/>">
</form>
<hr>
<form action="RegisterPage"
      method="post">
    <input type="hidden" name="action" value="RegisterPage">
    <input type="submit" value="<fmt:message key="label.button.RegisterPage"/>">
</form>
</body>
</html>
