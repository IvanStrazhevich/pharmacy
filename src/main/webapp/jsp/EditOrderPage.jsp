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

<table>
    <c:set value="${order}" var="ord"></c:set>
    <tr>

        <td>
            <table class="table table-striped table-bordered tableUpdated table-responsive">
                <tr>
                    <th><fmt:message key="label.header.id"/></th>
                    <th><fmt:message key="label.header.medicineName"/></th>
                    <th><fmt:message key="label.header.dosage"/></th>
                    <th><fmt:message key="label.header.recipeRequaered"/></th>
                    <th></th>
                </tr>
                <c:forEach items="${ord.medicines}" var="med">
                    <tr>

                        <form action="EditOrderPage" method="post">
                            <td>${ord.orderId}</td>
                            <td>${med.medicineName}</td>
                            <td>${med.dosage}</td>
                            <td>${med.recipeRequired}</td>
                            <td>
                                <c:if test="${med.recipeRequired==true}"><input type="submit" class="btn btn-warning"
                                       value="<fmt:message key="label.button.demandRecipe"/>"></c:if>
                                <input type="hidden" name="dosage" value="${med.dosage}">
                                <input type="hidden" name="orderId" value="${ord.orderId}">
                                <input type="hidden" name="medicineId" value="${med.medicineId}">
                                <input type="hidden" name="action" value="DemandRecipe">
                            </td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            <table class="table table-striped table-bordered tableUpdated table-responsive">
                <tr>
                    <th><fmt:message key="label.header.quantity"/></th>
                    <th></th>
                    <th><fmt:message key="label.header.orderSummary"/></th>
                    <th><fmt:message key="label.header.recipeId"/></th>
                    <th></th>
                </tr>
                <c:forEach items="${ord.orderHasMedicines}" var="ohm">
                    <tr>
                        <form action="EditOrderPage" method="post">
                            <td><input type="number" name="medicineQuantity" value="${ohm.medicineQuantity}"
                                       min="0" max="10" maxlength="11"></td>
                            <td>
                                <input type="submit" class="btn btn-success"
                                       value="<fmt:message key="label.button.changeQuantity"/>">
                                <input type="hidden" name="medicineId" value="${ohm.medicineId}">
                                <input type="hidden" name="orderId" value="${ord.orderId}">
                                <input type="hidden" name="action" value="ChangeQuantity">
                            </td>
                        </form>
                            <td>${ohm.medicineSum}</td>
                            <td>${ohm.recipeId}</td>

                        <td>
                            <form action="EditOrderPage" method="post">
                                <input type="submit" class="btn btn-danger"
                                       value="<fmt:message key="label.button.delete"/>">
                                <input type="hidden" name="medicineId" value="${ohm.medicineId}">
                                <input type="hidden" name="orderId" value="${ord.orderId}">
                                <input type="hidden" name="action" value="RemoveMedicineFromOrder">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        </td>
    </tr>
    <br>
</table>

<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
