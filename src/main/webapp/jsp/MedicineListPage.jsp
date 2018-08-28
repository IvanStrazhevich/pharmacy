<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${fn:escapeXml(sessionScope.lang)}" scope="session"/>
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
    ${fn:escapeXml(medicineAdded)}
    ${fn:escapeXml(needLogin)}
    ${fn:escapeXml(medicineDeleted)}
    ${fn:escapeXml(notAuthorised)}
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
                <c:if test="${fn:escapeXml(accessLevel)!='pharmacist'}">
                    <th colspan="3"></th>
                </c:if>
            </tr>
            <c:set var="rawNumber" value="${fn:escapeXml(rawNumber)}"/>
            <c:set var="raw" value="${fn:escapeXml(shift)}"/>
            <c:forEach items="${medicines}" var="meds">
                <tr>
                    <td>${fn:escapeXml(raw=raw+1)}</td>
                    <td>${fn:escapeXml(meds.medicineName)}</td>
                    <td>${fn:escapeXml(meds.description)}</td>
                    <td>${fn:escapeXml(meds.dosage)}</td>
                    <td>${fn:escapeXml(meds.recipeRequired)}</td>
                    <td>${fn:escapeXml(meds.price)}</td>
                    <td>${fn:escapeXml(meds.available)}</td>
                    <td>${fn:escapeXml(meds.quantityAtStorage)}</td>
                    <c:choose>
                        <c:when test="${sessionScope.accessLevel!='pharmacist'}">
                            <c:if test="${meds.available}">
                                <form action="MedicineListPage" method="post">
                                    <td>
                                        <input type="number" name="medicineQuantity" min="1"
                                               max="${fn:escapeXml(meds.quantityAtStorage)}">
                                    </td>
                                    <td>
                                        <input type="submit" class="btn btn-primary"
                                               value="<fmt:message key="label.button.addMedicine"/>">
                                        <input type="hidden" name="medicineId" value="${fn:escapeXml(meds.medicineId)}">
                                        <input type="hidden" name="shift" value="${fn:escapeXml(shift)}">
                                        <input type="hidden" name="rawNumber" value="${fn:escapeXml(rawNumber)}">
                                        <input type="hidden" name="action" value="AddMedicineToOrder">
                                    </td>
                                </form>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <form action="EditMedicinePage" method="post">
                                    <input type="submit" class="btn btn-primary"
                                           value="<fmt:message key="label.button.EditMedicine"/>">
                                    <input type="hidden" name="medicineId" value="${fn:escapeXml(meds.medicineId)}">
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
                <input type="hidden" name="rawNumber" value="${fn:escapeXml(rawNumber)}">
                <c:choose>
                    <c:when test="${fn:escapeXml(shift-rawNumber>=0)}">
                        <input type="hidden" name="shift" value="${fn:escapeXml(shift-rawNumber)}">
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
                <input type="hidden" name="shift" value="${fn:escapeXml(raw)}"/>
                <select class="btn btn-info" name="rawNumber">
                    <option value="5" <c:if test="${fn:escapeXml(rawNumber==5)}"> selected </c:if>>
                        <fmt:message
                                key="label.button.5raws"/></option>
                    <option value="10" <c:if test="${fn:escapeXml(rawNumber==10)}"> selected </c:if>>
                        <fmt:message key="label.button.10raws"/></option>
                    <option value="15"<c:if test="${fn:escapeXml(rawNumber==15)}"> selected </c:if>>
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
