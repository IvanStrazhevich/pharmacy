<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${fn:escapeXml(sessionScope.lang)}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="label.button.UserListPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<div class="table-responsive">
    <h6>
        <table class="table table-hover table-bordered tableUpdated">
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.accessLevel"/></th>
                <th><fmt:message key="label.header.name"/></th>
                <th><fmt:message key="label.header.lastname"/></th>
                <th><fmt:message key="label.header.email"/></th>
                <th></th>
            </tr>
            <c:forEach items="${users}" var="us">
                <tr>
                    <td>${fn:escapeXml(us.userId)}</td>
                    <td>${fn:escapeXml(us.accessLevel)}</td>
                    <td>${fn:escapeXml(us.clientDetail.name)}</td>
                    <td>${fn:escapeXml(us.clientDetail.lastname)}</td>
                    <td>${fn:escapeXml(us.clientDetail.email)}</td>
                    <c:if test="${fn:escapeXml(us.clientDetail.email) != fn:escapeXml(sessionScope.login)}">
                    <td>
                        <form action="EditUserAccessLevelPage" method="post">
                            <input type="submit" class="btn btn-primary"
                                   value="<fmt:message key="label.button.EditUserAccessLevel"/>">
                            <input type="hidden" name="action" value="EditAccessLevel">
                            <input type="hidden" value="${fn:escapeXml(us.userId)}" name="userId">
                        </form>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </h6>
</div>
<div><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
