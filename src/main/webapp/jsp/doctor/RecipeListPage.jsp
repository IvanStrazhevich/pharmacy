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
    <title><fmt:message key="label.button.RecipeListPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
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
                <th></th>
            </tr>
            <%--<c:set var="us" value="${user}"></c:set>
            --%><c:forEach items="${recipes}" var="rcp">

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
                        <input type="submit" class="btn btn-primary"
                               value="<fmt:message key="label.button.EditUserPage"/>">
                        <input type="hidden" name="action" value="EditUserDataPage">
                    </form>
                </td>
            </tr>
            </c:forEach><br>
        </table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
