<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.css" rel="stylesheet">
    <title><fmt:message key="label.button.MedicineListPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h4>
    ${medicineAdded}
    ${needLogin}
    ${medicineDeleted}
    ${validationError}
    ${notAuthorised}
</h4>
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
            <c:set var="rawNumber" value="${rawNumber}"/>
            <c:set var="raw" value="${shift}"/>
            <c:forEach items="${medicines}" var="meds">
                <tr>
                    <td>${raw=raw+1}</td>
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
        <c:if test="${shift!=0}">
            <form class="navbar-form navbar-left" action="MedicineListPage" method="post">
                <input type="hidden" name="action" value="MedicineList"/>
                <input type="hidden" name="rawNumber" value="${rawNumber}">
                <c:choose>
                    <c:when test="${shift-rawNumber>=0}">
                        <input type="hidden" name="shift" value="${shift-rawNumber}">
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="shift" value="${0}">
                    </c:otherwise>
                </c:choose>
                <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                    <input class=" form-control btn btn-info" type="submit"
                           value="<fmt:message key="label.button.previous"/>">
                </div>
            </form>
        </c:if>

        <c:if test="${raw-shift>rawNumber-1}">
            <form class="navbar-form navbar-left" action="MedicineListPage" method="post">
                <input type="hidden" name="action" value="MedicineList"/>
                <input type="hidden" name="shift" value="${raw}"/>
                <select class="btn btn-info" name="rawNumber">
                    <option value="5" <c:if test="${rawNumber==5}"> selected </c:if>>
                        <fmt:message
                                key="label.button.5raws"/></option>
                    <option value="10" <c:if test="${rawNumber==10}"> selected </c:if>>
                        <fmt:message key="label.button.10raws"/></option>
                    <option value="15"<c:if test="${rawNumber==15}"> selected </c:if>>
                        <fmt:message
                                key="label.button.15raws"/></option>
                </select>
                <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                    <input class=" form-control btn btn-info" type="submit"
                           value="<fmt:message key="label.button.next"/>">
                </div>
            </form>
        </c:if>
    </h6>
</div>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
