<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.UploadPage"/></title>
</head>
<body class="body">
<form action="UploadResultPage"
      enctype="multipart/form-data"
      method="post">
    <fmt:message key="message.choosefile"/>
    <input type="file" name="content"
           value="<fmt:message key="label.button.choosefile"/>">
    <input type="hidden" name="action" value="UploadResultPage">
    <input type="email" name="email" placeholder="<fmt:message key="message.enter.email"/>" maxlength="45">
    <input type="text" name="name" placeholder="<fmt:message key="message.enter.name"/>" maxlength="45">
    <input type="text" name="lasname" placeholder="<fmt:message key="message.enter.lastname"/>" maxlength="45">
    <input type="text" name="phone" placeholder="<fmt:message key="message.enter.phone"/>" maxlength="15">
    <input type="text" name="postcode" placeholder="<fmt:message key="message.enter.postcode"/>" maxlength="10">
    <input type="text" name="country" placeholder="<fmt:message key="message.enter.country"/>" maxlength="45">
    <input type="text" name="city" placeholder="<fmt:message key="message.enter.city"/>" maxlength="45">
    <input type="text" name="address" placeholder="<fmt:message key="message.enter.address"/>" maxlength="45">
    <input type="submit" value="<fmt:message key="label.button.confirm"/>">
</form>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
</form>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="InvalidateSession">
    <input type="submit" value="<fmt:message key="label.button.Logout"/>">
</form>
</body>
</html>
