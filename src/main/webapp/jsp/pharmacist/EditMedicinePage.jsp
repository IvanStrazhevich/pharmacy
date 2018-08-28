<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
    <title><fmt:message key="label.button.EditMedicine"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h4>
    ${fn:escapeXml(validationError)}
</h4>
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
                <c:if test="${fn:escapeXml(accessLevel)=='pharmacist'&& fn:escapeXml(med.medicineId)!=0}">
                    <th colspan="3"></th>
                </c:if>
            </tr>
            <tr>
                <form action="MedicineListPage" accept-charset="utf-8" method="post">
                    <c:if test="${fn:escapeXml(medicine)==null}">
                        <td></td>
                    </c:if>
                    <c:if test="${fn:escapeXml(medicine)!=null}">
                        <td>${fn:escapeXml(med.medicineId)}</td>
                    </c:if>
                    <td><input type="text" name="medicineName" value="${fn:escapeXml(med.medicineName)}" required
                               pattern=".{1,45}" placeholder="${fn:escapeXml(med.medicineName)}" maxlength="45" size="10"></td>
                    <td><input type="text" name="description" value="${fn:escapeXml(med.description)}" required
                               pattern=".{1,65535}" placeholder="${fn:escapeXml(med.description)}" maxlength="65535" size="15">
                    </td>
                    <td><input type="text" name="dosage" value="${fn:escapeXml(med.dosage)}" placeholder="${fn:escapeXml(med.dosage)}" maxlength="5"
                               pattern="\d{1,6}(\.\d{0,2})?" size="5" required></td>
                    <td><input type="text" name="recipeRequired" value="${fn:escapeXml(med.recipeRequired)}" required
                               pattern="\w{1,5}" placeholder="${fn:escapeXml(med.recipeRequired)}" maxlength="5" size="5"></td>
                    <td><input type="text" name="price" value="${fn:escapeXml(med.price)}" placeholder="${fn:escapeXml(med.price)}" maxlength="8"
                               pattern="\d{1,6}(\.\d{0,2})?" size="10" required>
                    </td>
                    <td><input type="text" name="available" value="${fn:escapeXml(med.available)}" placeholder="${fn:escapeXml(med.available)}"
                               pattern="\w{1,5}" maxlength="5" size="5" required></td>
                    <td><input type="number" name="quantityAtStorage" value="${fn:escapeXml(med.quantityAtStorage)}" required
                               pattern="\d{1,11}" placeholder="${fn:escapeXml(med.quantityAtStorage)}" maxlength="11" size="5"></td>
                    <td>
                        <input type="submit" class="form-group btn btn-success"
                               value="<fmt:message key="label.button.confirm"/>">
                        <c:if test="${fn:escapeXml(med.medicineId)!=null}">
                        <input type="hidden" name="shift" value="0"/>
                        <input type="hidden" name="rawNumber" value="5">
                        <input type="hidden" name="medicineId" value="${fn:escapeXml(med.medicineId)}"></c:if>
                        <input type="hidden" name="action" value="SaveMedicine">
                </form>
                </td>

                <c:if test="${fn:escapeXml(accessLevel)=='pharmacist'&& fn:escapeXml(med.medicineId)!=0}">
                    <%-- Temporary removed dew to bug fixing process
                    <td>
                        <form action="MedicineListPage" method="post">
                            <input type="submit" class=" form-control btn btn-danger"
                                   value="<fmt:message key="label.button.delete"/>">
                            <input type="hidden" name="shift" value="0"/>
                            <input type="hidden" name="rawNumber" value="5">
                            <input type="hidden" name="medicineId" value="${med.medicineId}">
                            <input type="hidden" name="action" value="RemoveMedicineFromBase">
                        </form>
                    </td>--%>
                    <td>
                        <form action="MedicineListPage" method="post">
                            <input type="submit" class=" form-control btn btn-warning"
                                   value="<fmt:message key="label.button.remove"/>">
                            <input type="hidden" name="shift" value="0"/>
                            <input type="hidden" name="rawNumber" value="5">
                            <input type="hidden" name="medicineId" value="${fn:escapeXml(med.medicineId)}">
                            <input type="hidden" name="action" value="RemoveFromAvailable">
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
