<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href=“css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.WelcomePage"/></title>
</head>
<body>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
${notAuthorised}
${userRegistered}
${greeting}
<pht:info-time locale="${lang}"/>
<pht:hello role="${accessLevel}" login="${login}"/>
<form class="checkbox" action="WelcomePage" method="post">
    <select class="btn btn-primary" name="lang">
        <option value="be_BY"><fmt:message key="label.button.be"/></option>
        <option value="ru_RU"><fmt:message key="label.button.ru"/></option>
        <option value="en_US"><fmt:message key="label.button.en"/></option>
    </select>
    <input type="hidden" name="action" value="SetLocale">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="label.button.language"/> ">
    <br>
</form>
<form action="UploadPage" method="post">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="label.button.UploadPage"/>">
    <input type="hidden" name="action" value="UploadPage">
</form>
<form action="WelcomePage" method="post">
    <input type="hidden" name="action" value="InvalidateSession">
    <input type="submit" class="btn btn-primary" value="<fmt:message key="label.button.Logout"/>">
</form>
</body>
</html>
