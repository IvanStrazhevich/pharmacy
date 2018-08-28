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
    <link href="css/pharmacy.css" rel="stylesheet">
    <title><fmt:message key="label.button.RegisterPage"/>
        <hr>
    </title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<h4>
${fn:escapeXml(notAuthorised)}
${fn:escapeXml(userExist)}
${fn:escapeXml(needLogin)}
${fn:escapeXml(needRegister)}
${fn:escapeXml(validationError)}
</h4>
<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="col-md-4 col-md-offset-4">
            <form accept-charset="utf-8" action="WelcomePage" method=post>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>

                    <input class="form-control" type="text" id="login" name="login" size="15" maxlength="45"
                           pattern="\w{1,}@\w{3,}\.\w{2,4}" required
                           title="<fmt:message key="message.loginRule"/>"
                           placeholder="<fmt:message key="message.enter.login"/>">
                </div>

                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-eye-close"></span></span>

                    <input class="form-control" id="password" type="password" name="password" size="15" maxlength="45"
                           title="<fmt:message key="message.passwordRule"/>"
                           pattern="(\w){6,45}" required placeholder="<fmt:message key="message.enter.password"/>">
                </div>
                <input type="hidden" name="action" value="RegisterUser">
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">

                    <input class="form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.Register"/>">
                </div>
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">

                    <input class="form-control btn btn-warning" type="reset"
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
