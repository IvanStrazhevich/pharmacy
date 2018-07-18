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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload Result Page</title>
</head>
<body class="body">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
${result}
<br>


<div>
    <h4>
        <table class="table">
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.name"/></th>
                <th><fmt:message key="label.header.lastname"/></th>
                <th><fmt:message key="label.header.email"/></th>
                <th><fmt:message key="label.header.phone"/></th>
                <th><fmt:message key="label.header.postcode"/></th>
                <th><fmt:message key="label.header.country"/></th>
                <th><fmt:message key="label.header.city"/></th>
                <th><fmt:message key="label.header.address"/></th>
            </tr>
            <c:set var="us" value="${user}"></c:set>
            <%--<c:forEach items="${user}" var="us">
            --%>
            <tr>
                <td>${us.clientId}</td>
                <td>${us.name}</td>
                <td>${us.lastname}</td>
                <td>${us.email}</td>
                <td>${us.phone}</td>
                <td>${us.postcode}</td>
                <td>${us.country}</td>
                <td>${us.city}</td>
                <td>${us.address}</td>
                <td>
                    <form action="EditUserDataPage" method="post">
        <span><input type="submit" class="btn btn-primary"
                     value="<fmt:message key="label.button.EditUserPage"/>"></span>
                        <input type="hidden" name="action" value="EditUserDataPage">
                    </form>
                </td>
            </tr>
            <%--</c:forEach><br>
        --%></table>
    </h4>
</div>
<form action="WelcomePage"
      method="post">
    <input type="hidden" name="action" value="WelcomePage">
    <input type="submit" value="<fmt:message key="label.button.WelcomePage"/>">
</form>
<form action="WelcomePage" method="post">
    <input type="hidden" name="action" value="InvalidateSession">
    <input type="submit" value="<fmt:message key="label.button.Logout"/>">
</form>
</body>
</html>
