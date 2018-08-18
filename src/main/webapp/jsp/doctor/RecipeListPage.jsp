<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href=“css/normalize.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.RecipeListPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h4>
    ${recipeDeleted}
</h4>
<div class="table-responsive">
    <h6>
        <table class="table table-striped table-hover table-bordered tableUpdated">
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.medicineName"/></th>
                <th><fmt:message key="label.header.clientLastname"/></th>
                <th><fmt:message key="label.header.clientName"/></th>
                <th><fmt:message key="label.header.quantity"/></th>
                <th><fmt:message key="label.header.dosage"/></th>
                <th><fmt:message key="label.header.validTill"/></th>
                <th><fmt:message key="label.header.approved"/></th>
                <th></th>
            </tr>
            <c:forEach items="${recipes}" var="rcp">
                <tr>
                    <td>${rcp.recipeId}</td>
                    <td>${rcp.medicine.medicineName}</td>
                    <td>${rcp.clientDetail.name}</td>
                    <td>${rcp.clientDetail.lastname}</td>
                    <td>${rcp.medicineQuantity}</td>
                    <td>${rcp.dosage}</td>
                    <td>${rcp.validTill}</td>
                    <td>${rcp.approved}</td>
                    <td>
                        <c:if test="${rcp.approved =='false'}">
                            <form action="RecipeApprovalPage" method="post">
                                <input type="submit" class="btn btn-primary"
                                       value="<fmt:message key="label.button.RecipeApproval"/>">
                                <input type="hidden" name="recipeId" value="${rcp.recipeId}">
                                <input type="hidden" name="action" value="EditRecipe">
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach><br>
        </table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="WEB-INF/js/bootstrap.min.js"></script>
</body>
</html>
