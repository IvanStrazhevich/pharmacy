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
    <title><fmt:message key="label.button.EditUserAccessLevel"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<div class="table-responsive">
    <h6>
        <table style="background: whitesmoke" class="table table-striped table-hover table-bordered tableUpdated">
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.name"/></th>
                <th><fmt:message key="label.header.lastname"/></th>
                <th><fmt:message key="label.header.email"/></th>
                <th><fmt:message key="label.header.accessLevel"/></th>
                <th></th>
            </tr>
            <c:set value="${user}" var="us"></c:set>
            <tr>
                <td>${fn:escapeXml(us.userId)}</td>
                <td>${fn:escapeXml(us.clientDetail.name)}</td>
                <td>${fn:escapeXml(us.clientDetail.lastname)}</td>
                <td>${fn:escapeXml(us.clientDetail.email)}</td>
                <c:if test="${fn:escapeXml(us.clientDetail.email) != fn:escapeXml(sessionScope.login)}">
                <form action="UserListPage" method="post">
                    <td>
                        <select class="btn btn-primary" name="accessLevel">
                            <option value="client" <c:if test="${fn:escapeXml(us.accessLevel)=='client'}"> selected </c:if>>
                                <fmt:message
                                        key="label.button.accessClient"/></option>
                            <option value="pharmacist" <c:if test="${fn:escapeXml(us.accessLevel)=='pharmacist'}"> selected </c:if>>
                                <fmt:message key="label.button.accessPharmacist"/></option>
                            <option value="doctor"<c:if test="${fn:escapeXml(us.accessLevel)=='doctor'}"> selected </c:if>>
                                <fmt:message
                                        key="label.button.accessDoctor"/></option>
                        </select>
                    </td>
                    <td>
                        <input type="submit" class="btn btn-primary"
                               value="<fmt:message key="label.button.confirm"/>">
                        <input type="hidden" name="action" value="SaveAccessLevel">
                        <input name="userId" type="hidden" value="${us.userId}">
                </form>
                </td>
                </c:if>
            </tr>
            <br>
        </table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
