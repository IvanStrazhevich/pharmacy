<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<fmt:setLocale value="${fn:escapeXml(sessionScope.lang)}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Upload Result Page</title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
${fn:escapeXml(result)}
${fn:escapeXml(notAuthorised)}
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
            <tr>
                <td>${fn:escapeXml(us.clientId)}</td>
                <td>${fn:escapeXml(us.name)}</td>
                <td>${fn:escapeXml(us.lastname)}</td>
                <td>${fn:escapeXml(us.email)}</td>
                <td>${fn:escapeXml(us.phone)}</td>
                <td>${fn:escapeXml(us.postcode)}</td>
                <td>${fn:escapeXml(us.country)}</td>
                <td>${fn:escapeXml(us.city)}</td>
                <td>${fn:escapeXml(us.address)}</td>
            </tr>
        </table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
