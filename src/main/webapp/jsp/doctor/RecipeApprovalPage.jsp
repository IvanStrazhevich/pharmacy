<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href=“css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.RecipeApproval"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<div class="container-fluid" style="flex-direction:column; float: left">
    <c:import url="/WEB-INF/LeftSidePage.jsp"/>
</div>

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
            <c:set value="${recipe}" var="rcp"/>
            <tr>
                <form action="RecipeListPage" method="post">
                    <td>${rcp.recipeId}</td>
                    <td>${rcp.medicine.medicineName}</td>
                    <td>${rcp.clientDetail.name}</td>
                    <td>${rcp.clientDetail.lastname}</td>
                    <td><input type="number" value="${rcp.medicineQuantity}" min="0" max="${rcp.medicineQuantity}"
                               name="medicineQuantity"></td>
                    <td><input type="number" value="${rcp.dosage}" min="0" max="${rcp.dosage}" name="dosage"></td>
                    <td><input type="datetime-local" name="validTill" placeholder="yyyy-mm-dd hh:mm:ss" maxlength="19"
                               pattern="\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}" minlength="19"></td>
                    <td>
                        <select class="btn btn-primary" name="approved">
                            <option value="true" <c:if test="${rcp.approved=='true'}"> selected </c:if>>
                                <fmt:message key="label.button.approved"/></option>
                            <option value="false" <c:if test="${rcp.approved=='false'}"> selected </c:if>>
                                <fmt:message key="label.button.notApproved"/></option>
                        </select>
                    </td>
                    <td>
                        <input type="submit" class="btn btn-primary"
                               value="<fmt:message key="label.button.approve"/>">
                        <input type="hidden" name="medicineId" value="${rcp.medicineId}">
                        <input type="hidden" name="recipeId" value="${rcp.recipeId}">
                        <input type="hidden" name="action" value="ApproveRecipe">
                    </td>
                </form>
                <form action="RecipeListPage" method="post">
                    <td><input type="submit" class="btn btn-danger"
                               value="<fmt:message key="label.button.delete"/>">
                        <input type="hidden" name="recipeId" value="${rcp.recipeId}">
                        <input type="hidden" name="action" value="DeleteRecipe">
                    </td>
                </form>
            </tr>
        </table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
