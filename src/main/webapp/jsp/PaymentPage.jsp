<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/pharmacy.css" rel="stylesheet">
    <title><fmt:message key="label.button.LoginPage"/>
        <hr>
    </title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
${notAuthorised}
${userNotRegistered}
${needRegister}
${needLogin}
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="col-md-4 col-md-offset-4">
<c:set value="${payment}" var="pmt"/>
            <form action="CheckLogin" method=post>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-credit-card"></span></span>

                    <input class="form-control" type="text"  name="payCredit" size="15" maxlength="45"
                           required
                           title="Match email format no more then 45 symbols at least 6x+@3x+.2x(3x)"
                           value="${pmt.orderSum}">
                </div>

                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>

                    <input class="form-control" type="text" name="payDebit" size="15" maxlength="45"
                           pattern="\d+.\d" required value="${pmt.orderSum}">
                </div>
                <input type="hidden" name="action" value="CheckLogin">
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">
                    <input class=" form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.Submit"/>">
                </div>
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">
                    <input class=" form-control btn btn-warning" type="reset"
                           value="<fmt:message key="label.button.Reset"/>">
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

