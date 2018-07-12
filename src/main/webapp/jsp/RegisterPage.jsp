<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.RegisterPage" bundle="${rb}"/>
        <hr>
    </title>
</head>
<body class="body">
<hr>
<fmt:message key="label.button.RegisterPage" bundle="${rb}"/>
${userExist}
${needLogin}
<form action="RegisterUser" method=post>
    <strong><fmt:message key="message.enterLogin" bundle="${rb}"/> </strong>
    <input type="text" name="login" size="15" maxlength="45"
           placeholder="<fmt:message key="message.enterLogin" bundle="${rb}"/>">
    <strong><fmt:message key="message.enterPassword" bundle="${rb}"/> </strong>
    <input type="password" name="password" size="15" maxlength="45"
           placeholder="<fmt:message key="message.enterPassword" bundle="${rb}"/>">
    <input type="hidden" size="15" name="action" value="RegisterUser">
    <input type="submit" value="<fmt:message key="label.button.Register" bundle="${rb}"/>">
    <input type="reset" value="<fmt:message key="label.button.Reset" bundle="${rb}"/>">
</form>
<hr>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input type="submit" value="<fmt:message key="label.button.WelcomePage" bundle="${rb}"/>">
</form>
</body>
</html>
