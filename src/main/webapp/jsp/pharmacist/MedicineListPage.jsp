<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <title><fmt:message key="label.button.MedicineListPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<div>
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
            </tr>
            <%--<c:set var="us" value="${user}"></c:set>
            --%><c:forEach items="${medicines}" var="meds">
            <tr>
                <td>${meds.medicineId}</td>
                <td>${meds.medicineName}</td>
                <td>${meds.description}</td>
                <td>${meds.dosage}</td>
                <td>${meds.recipeRequired}</td>
                <td>${meds.price}</td>
                <td>${meds.available}</td>
                <td>${meds.quantityAtStorage}</td>
                <%--<td><input type="checkbox" name="chosen"></td>--%>
                <td>
                    <form action="EditMedicinePage" method="post">
                        <input type="submit" class="btn btn-primary"
                               value="<fmt:message key="label.button.EditMedicine"/>">
                        <input type="hidden" name="medicineId" value="${meds.medicineId}">
                        <input type="hidden" name="action" value="EditMedicine">
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
