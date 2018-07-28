<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <title><fmt:message key="label.button.EditOrderPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
${recipeRequested}
<div class="table-responsive">
    <h6>
        <table class="table table-striped table-bordered tableUpdated table-responsive">
            <c:set value="${order}" var="ord"/>
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.medicineName"/></th>
                <th><fmt:message key="label.header.dosage"/></th>
                <th><fmt:message key="label.header.recipeRequaered"/></th>
                <th><fmt:message key="label.header.recipeId"/></th>
                <th><fmt:message key="label.header.approved"/></th>
                <th><fmt:message key="label.header.orderSummary"/></th>
                <th><fmt:message key="label.header.quantity"/></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${ord.orderHasMedicines}" var="ohm">
                <tr>
                    <td>${ord.orderId}</td>
                    <td>${ohm.medicine.medicineName}</td>
                    <td>${ohm.medicine.dosage}</td>
                    <td>${ohm.medicine.recipeRequired}</td>
                    <td>${ohm.recipeId}</td>
                    <td>${ohm.recipe.approved}</td>
                    <td>${ohm.medicineSum}</td>
                    <form action="EditOrderPage" method="post">
                        <td>
                            <input type="number" name="medicineQuantity" value="${ohm.medicineQuantity}"
                                   min="0" max="${ohm.medicine.quantityAtStorage + ohm.medicineQuantity}"
                                   maxlength="11">
                        </td>
                        <td>
                            <input type="submit" class="btn btn-success"
                                   value="<fmt:message key="label.button.changeQuantity"/>">
                            <input type="hidden" name="medicineId" value="${ohm.medicineId}">
                            <input type="hidden" name="orderId" value="${ord.orderId}">
                            <input type="hidden" name="action" value="ChangeQuantity">
                        </td>
                    </form>
                    <td>
                        <form action="EditOrderPage" method="post">
                            <input type="submit" class="btn btn-danger"
                                   value="<fmt:message key="label.button.delete"/>">
                            <input type="hidden" name="medicineId" value="${ohm.medicineId}">
                            <input type="hidden" name="orderId" value="${ord.orderId}">
                            <input type="hidden" name="action" value="RemoveMedicineFromOrder">
                        </form>
                    </td>
                    <td>
                        <c:if test="${(ohm.medicine.recipeRequired==true
                && ohm.recipe.approved==false)
                || (ohm.medicine.recipeRequired==true
                && ohm.recipe.medicineQuantity < ohm.medicineQuantity)}">
                        <form action="EditOrderPage" method="post">
                            <input type="submit" class="btn btn-warning"
                                   value="<fmt:message key="label.button.demandRecipe"/>">
                            </c:if>
                            <input type="hidden" name="dosage" value="${ohm.medicine.dosage}">
                            <input type="hidden" name="orderId" value="${ord.orderId}">
                            <input type="hidden" name="medicineId" value="${ohm.medicineId}">
                            <input type="hidden" name="medicineQuantity" value="${ohm.medicineQuantity}">
                            <input type="hidden" name="action" value="DemandRecipe">
                    </td>
                    </form>
                </tr>
            </c:forEach>
        </table>
    </h6>
</div>

<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
