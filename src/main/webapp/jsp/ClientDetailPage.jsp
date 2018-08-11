<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Upload Result Page</title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<div class="container-fluid" style="flex-direction:column; float: left">
    <c:import url="/WEB-INF/LeftSidePage.jsp"/>
</div>
${result}
${notAuthorised}
<br>
<div>
    <h6>
        <table class="table table-striped table-hover table-bordered tableUpdated">
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
                <%--<th></th>--%>
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
                <%--<td>
                    <form action="EditUserDataPage" method="post">
                        <input type="submit" class="btn btn-primary"
                               value="<fmt:message key="label.button.EditUserPage"/>">
                        <input type="hidden" name="action" value="EditUserDataPage">
                    </form>
                </td>--%>
            </tr>
            <%--</c:forEach><br>
        --%></table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
