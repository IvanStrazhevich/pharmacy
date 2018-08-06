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
<div class="container-fluid" style="flex-direction:column; float: left">
    <c:import url="/WEB-INF/LeftSidePage.jsp"/>
</div>
<div class="table-responsive">
    <h6>
        <table class="table table-striped table-hover table-bordered tableUpdated table-responsive">

            <c:set var="med" value="${medicine}"></c:set>
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.medicineName"/></th>
                <th><fmt:message key="label.header.description"/></th>
                <th><fmt:message key="label.header.dosage"/></th>
                <th><fmt:message key="label.header.recipeRequaered"/></th>
                <th><fmt:message key="label.header.price"/></th>
                <th><fmt:message key="label.header.available"/></th>
                <th><fmt:message key="label.header.quantityAvailable"/></th>
                <th></th>
                <c:if test="${accessLevel=='pharmacist'&& med.medicineId!=0}">
                    <th colspan="2"></th>
                </c:if>
            </tr>
            <tr>
                <form action="MedicineListPage" accept-charset="utf-8" method="post">
                    <c:if test="${medicine==null}">
                        <td></td>
                    </c:if>
                    <c:if test="${medicine!=null}">
                        <td>${med.medicineId}</td>
                    </c:if>
                    <td><input type="text" name="medicineName" value="${med.medicineName}"
                               placeholder="${med.medicineName}" maxlength="45" size="10"></td>
                    <td><input type="text" name="description" value="${med.description}"
                               placeholder="${med.description}" maxlength="65000" size="15">
                    </td>
                    <td><input type="text" name="dosage" value="${med.dosage}" placeholder="${med.dosage}" maxlength="5"
                               size="5"></td>
                    <td><input type="text" name="recipeRequired" value="${med.recipeRequired}"
                               placeholder="${med.recipeRequired}" maxlength="5" size="5"></td>
                    <td><input type="text" name="price" value="${med.price}" placeholder="${med.price}" maxlength="8"
                               size="10">
                    </td>
                    <td><input type="text" name="available" value="${med.available}" placeholder="${med.available}"
                               maxlength="5" size="5"></td>
                    <td><input type="text" name="quantityAtStorage" value="${med.quantityAtStorage}"
                               placeholder="${med.quantityAtStorage}" maxlength="11" size="5"></td>
                    <td>
                        <input type="submit" class="form-group btn btn-success"
                               value="<fmt:message key="label.button.confirm"/>">
                        <c:if test="${med.medicineId!=null}">
                        <input type="hidden" name="medicineId" value="${med.medicineId}"></c:if>
                        <input type="hidden" name="action" value="SaveMedicine">
                </form>
                </td>

                <c:if test="${accessLevel=='pharmacist'&& med.medicineId!=0}">
                    <td>
                        <form action="MedicineListPage" method="post">
                            <input type="submit" class=" form-control btn btn-danger"
                                   value="<fmt:message key="label.button.delete"/>">
                            <input type="hidden" name="medicineId" value="${med.medicineId}">
                            <input type="hidden" name="action" value="RemoveMedicineFromBase">
                        </form>
                    </td>
                </c:if>

            </tr>
        </table>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
