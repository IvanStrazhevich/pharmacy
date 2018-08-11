<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/pharmacy.css" rel="stylesheet">
    <title><fmt:message key="label.button.LoginPage"/>
        <hr>
    </title>
</head>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h4>
${notAuthorised}
${userNotRegistered}
${needRegister}
${needLogin}
${validationError}
</h4>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="col-md-4 col-md-offset-4">

            <form action="CheckLogin" method=post>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <input class="form-control" type="text" id="login" name="login" size="15" maxlength="45"
                           pattern="\w{1,}@\w{3,}\.\w{2,4}" required
                           title="Match email format"
                           placeholder="<fmt:message key="message.enter.login"/>">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-eye-close"></span></span>
                    <input class="form-control" id="password" type="password" name="password" size="15" maxlength="45"
                           title="Enter at least 6 symbols"
                           pattern="(\w){6,45}" required placeholder="<fmt:message key="message.enter.password"/>">
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
