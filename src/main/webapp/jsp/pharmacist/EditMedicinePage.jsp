<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <meta charset="utf-8">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title><fmt:message key="label.button.EditMedicine"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h6>
    <table class="table table-striped table-hover table-bordered tableUpdated">
        <tr>
            <th><fmt:message key="label.header.id"/></th>
            <th><fmt:message key="label.header.medicineName"/></th>
            <th><fmt:message key="label.header.description"/></th>
            <th><fmt:message key="label.header.dosage"/></th>
            <th><fmt:message key="label.header.recipeRequaered"/></th>
            <th><fmt:message key="label.header.price"/></th>
            <th><fmt:message key="label.header.available"/></th>
            <th><fmt:message key="label.header.quantityAvailable"/></th>
            <%-- <th><fmt:message key="label.header.choose"/></th>--%>
            <th></th>
            <th></th>
        </tr>
        <c:set var="med" value="${medicine}"></c:set>
        <%--<c:forEach items="${medicines}" var="meds">--%>
        <tr>
            <form action="MedicineListPage" accept-charset="utf-8" method="post">
                <td>${med.medicineId}</td>
                <td><input type="text" name="medicineName" value="${med.medicineName}" placeholder="${med.medicineName}"></td>
                <td><input type="text" name="description" value="${med.description}" placeholder="${med.description}"></td>
                <td><input type="text" name="dosage" value="${med.dosage}" placeholder="${med.dosage}"></td>
                <td><input type="text" name="recipeRequired" value="${med.recipeRequired}" placeholder="${med.recipeRequired}"></td>
                <td><input type="text" name="price" value="${med.price}" placeholder="${med.price}"></td>
                <td><input type="text" name="available" value="${med.available}" placeholder="${med.available}"></td>
                <td><input type="text" name="quantityAtStorage" value="${med.quantityAtStorage}" placeholder="${med.quantityAtStorage}"></td>
                <%--<td><input type="checkbox" name="chosen"></td>--%>
                <td>
                    <input type="submit" class="btn btn-success"
                           value="<fmt:message key="label.button.confirm"/>">
                    <input type="hidden" name="medicineId" value="${med.medicineId}">
                    <input type="hidden" name="action" value="SaveMedicine">
            </form>
            </td>
            <td>
                <form action="MedicineListPage" method="post">
                    <input type="submit" class="btn btn-danger"
                           value="<fmt:message key="label.button.delete"/>">
                    <input type="hidden" name="medicineId" value="${med.medicineId}">
                    <input type="hidden" name="action" value="DeleteMedicine">
                </form>
            </td>
        </tr>
        <%--</c:forEach><br>--%>
    </table>
</h6>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
