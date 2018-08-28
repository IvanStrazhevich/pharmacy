<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<fmt:setLocale value="${fn:escapeXml(sessionScope.lang)}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="label.button.LoginPage"/>
        <hr>
    </title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h4>
    ${fn:escapeXml(notAuthorised)}
    ${fn:escapeXml(userNotRegistered)}
    ${fn:escapeXml(needRegister)}
    ${fn:escapeXml(needLogin)}
    ${fn:escapeXml(validationError)}
</h4>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="col-md-4 col-md-offset-4">
            <c:set value="${payment}" var="pmt"/>
            <c:set value="${account}" var="acc"/>
            <form action="EditOrderPage" method=post>

                <label for="credit"> <fmt:message key="label.creditpayment"/></label>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></span>
                    <input id="credit" class="form-control" type="number" name="accountCredit" size="11" maxlength="11"
                           required pattern="\d+(\.\d+)?" min="${fn:escapeXml(acc.accountCredit)}" max="${fn:escapeXml(acc.accountCredit)}"
                           title="Enter digits" placeholder="${fn:escapeXml(acc.accountCredit)}"
                           value="${fn:escapeXml(acc.accountCredit)}">
                </div>

                <label for="debit"> <fmt:message key="label.debitpayment"/></label>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>
                    <input id="debit" class="form-control" type="number" name="accountDebit" size="11" maxlength="11"
                           required pattern="\d+(\.\d+)?" min="${fn:escapeXml(acc.accountDebit)}" max="${fn:escapeXml(acc.accountDebit)}"
                           title="Enter digits" placeholder="${fn:escapeXml(acc.accountDebit)}" value="${fn:escapeXml(acc.accountDebit)}">
                </div>
                <input type="hidden" name="action" value="MakePayment">
                <input type="hidden" name="paymentId" value="${fn:escapeXml(pmt.paymentId)}">
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">
                    <input class=" form-control btn btn-info" type="submit"
                           value="<fmt:message key="label.button.payOrder"/>">
                </div>
            </form>
        </div>
    </div>
</div>

<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>

