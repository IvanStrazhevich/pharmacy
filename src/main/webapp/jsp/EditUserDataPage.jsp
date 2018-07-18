<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href=“css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.EditUserPage"/></title>
</head>
<body class="body">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<form action="EditUserDataPage"
      enctype="multipart/form-data"
      method="post">
    <fmt:message key="message.choosefile"/>
    <input type="file" name="content"
           value="<fmt:message key="label.button.choosefile"/>">
    <input type="hidden" name="action" value="UploadResult">
    <input class="btn btn-success" type="submit" value="<fmt:message key="label.button.uploadfile"/>">
</form>
    <form action="UploadResultPage"
          method="get">
        <c:set var="us" value="${user}"></c:set>
        <div class="input-group">
            <div class="input-group">
                <label for="email"><fmt:message key="message.enter.email"/></label>
                <div class="form-inline">
                    <input id="email" type="email" name="email" value="${us.email}"
                           placeholder="<fmt:message key="message.enter.email"/>" maxlength="45">
                </div>
            </div>
            <div class="input-group">
                <label for="name"><fmt:message key="message.enter.name"/></label>
                <div class="form-inline">
                    <input id="name" type="text" name="name" value="${us.name}"
                           placeholder="<fmt:message key="message.enter.name"/>"
                           maxlength="45">
                </div>
            </div>
            <div class="input-group">
                <label for="lastname"><fmt:message key="message.enter.lastname"/></label>
                <div class="form-inline">
                    <input id="lastname" type="text" name="lastname" value="${us.lastname}"
                           placeholder="<fmt:message key="message.enter.lastname"/>" maxlength="45">
                </div>
            </div>
            <div class="input-group">
                <label for="phone"><fmt:message key="message.enter.phone"/></label>
                <div class="form-inline">
                    <input id="phone" type="text" name="phone" value="${us.phone}"
                           placeholder="<fmt:message key="message.enter.phone"/>"
                           maxlength="15">
                </div>
            </div>
            <div class="input-group">
                <label for="postcode"><fmt:message key="message.enter.postcode"/></label>
                <div class="form-inline">
                    <input id="postcode" type="text" name="postcode" value="${us.postcode}"
                           placeholder="<fmt:message key="message.enter.postcode"/>" maxlength="10">
                </div>
            </div>
            <div class="input-group">
                <label for="country"><fmt:message key="message.enter.country"/></label>
                <div class="form-inline">
                    <input id="country" type="text" name="country" value="${us.country}"
                           placeholder="<fmt:message key="message.enter.country"/>"
                           maxlength="45">
                </div>
            </div>
            <div class="input-group">
                <label for="city"><fmt:message key="message.enter.city"/></label>
                <div class="form-inline">
                    <input id="city" type="text" name="city" value="${us.city}"
                           placeholder="<fmt:message key="message.enter.city"/>"
                           maxlength="45">
                </div>
            </div>
            <div class="input-group">
                <label for="address"><fmt:message key="message.enter.address"/></label>
                <div class="form-inline">
                    <input id="address" type="text" name="address" value="${us.address}"
                           placeholder="<fmt:message key="message.enter.address"/>"
                           maxlength="45">
                </div>
            </div>
            <input type="hidden" name="action" value="UploadResultPage">
            <input class="btn btn-success" type="submit" value="<fmt:message key="label.button.confirm"/>">
        </div>

    </form>

<div class="glyphicon-menu-left">
    <span class="glyphicon">
        <form action="WelcomePage"
              method="post">
        <input type="hidden" name="action" value="WelcomePage">
        <input class="btn btn-primary" type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
    </form>
    </span>
    <span class="glyphicon">
        <form action="WelcomePage"
              method="post">
        <input type="hidden" name="action" value="InvalidateSession">
        <input class="btn btn-danger" type="submit" value="<fmt:message key="label.button.Logout"/>">
    </form>
    </span>
</div>
</body>
</html>
