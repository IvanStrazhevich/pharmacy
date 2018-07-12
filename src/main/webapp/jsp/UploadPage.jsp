<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message" var="rb"/>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.UploadPage" bundle="${rb}"/></title>
</head>
<body class="body">
<form action="UploadResultPage"
      enctype="multipart/form-data"
      method="post">
    <fmt:message key="message.choosefile" bundle="${rb}"/>
    <input type="file" name="content"
           value="<fmt:message key="label.button.choosefile" bundle="${rb}"/>">
    <input type="hidden" name="action" value="UploadResultPage">
    <input type="submit" value="<fmt:message key="label.button.uploadfile" bundle="${rb}"/>">
</form>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input type="submit" value="<fmt:message key="label.button.WelcomePage" bundle="${rb}"/>">
</form>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="InvalidateSession">
    <input type="submit" value="<fmt:message key="label.button.Logout" bundle="${rb}"/>">
</form>
</body>
</html>
