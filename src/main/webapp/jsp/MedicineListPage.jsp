<%@ page contentType="text/html" pageEncoding="UTF-8" %>
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
<div class="container-fluid" style="flex-direction:column; float: left ">
    <c:import url="/WEB-INF/LeftSidePage.jsp"/>
</div>
${medicineAdded}
${needLogin}
${medicineDeleted}
<div class="table-responsive">
    <h6>
        <table class="table table-striped table-bordered tableUpdated table-responsive">
            <tr>
                <th><fmt:message key="label.header.id"/></th>
                <th><fmt:message key="label.header.medicineName"/></th>
                <th><fmt:message key="label.header.description"/></th>
                <th><fmt:message key="label.header.dosage"/></th>
                <th><fmt:message key="label.header.recipeRequaered"/></th>
                <th><fmt:message key="label.header.price"/></th>
                <th><fmt:message key="label.header.available"/></th>
                <th><fmt:message key="label.header.quantityAvailable"/></th>
                <th><fmt:message key="label.header.choose"/></th>
                <c:if test="${accessLevel!='pharmacist'}">
                <th colspan="3"></th>
                </c:if>
            </tr>
            <c:forEach items="${medicines}" var="meds">
                <tr>
                    <td>${meds.medicineId}</td>
                    <td>${meds.medicineName}</td>
                    <td>${meds.description}</td>
                    <td>${meds.dosage}</td>
                    <td>${meds.recipeRequired}</td>
                    <td>${meds.price}</td>
                    <td>${meds.available}</td>
                    <td>${meds.quantityAtStorage}</td>
                    <c:choose>
                        <c:when test="${sessionScope.accessLevel!='pharmacist'}">
                            <form action="MedicineListPage" method="post">
                                <td>
                                    <input type="number" name="medicineQuantity" min="1"
                                           max="${meds.quantityAtStorage}">
                                </td>
                                <td>
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="label.button.addMedicine"/>">
                                    <input type="hidden" name="medicineId" value="${meds.medicineId}">
                                    <input type="hidden" name="action" value="AddMedicineToOrder">
                                </td>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <form action="EditMedicinePage" method="post">
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="label.button.EditMedicine"/>">
                                    <input type="hidden" name="medicineId" value="${meds.medicineId}">
                                    <input type="hidden" name="action" value="EditMedicine">
                                </form>
                            </td>
                        </c:otherwise>
                    </c:choose>
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
