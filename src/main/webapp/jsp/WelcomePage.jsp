<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="label.button.WelcomePage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<nav class="navbar" style="padding: 0px; margin: 0px">
    <div class="container-fluid">
        <form class="navbar-form navbar-left" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <input type="hidden" name="lang" value="be_BY"></input>
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="<fmt:message key="label.button.be"/>">
            </div>
        </form>
        <form class="navbar-form navbar-left" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <input type="hidden" name="lang" value="ru_RU"></input>
            <div class="form-group input-group input-group-lg col-md-0 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="<fmt:message key="label.button.ru"/>">
            </div>
        </form>
        <form class="navbar-form navbar-left" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <input type="hidden" name="lang" value="en_US"></input>
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="<fmt:message key="label.button.en"/>">
            </div>
        </form>
    </div>
</nav>
<h4>
    <c:out value="${notAuthorised}
    ${userRegistered}
    ${greeting}
    ${needRegister}
    ${needLogin}"/>
</h4>
<div style="float: bottom"><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
