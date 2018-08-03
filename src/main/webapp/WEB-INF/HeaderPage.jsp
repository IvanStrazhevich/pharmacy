<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="label.button.HeaderPage"/></title>
</head>
<body>
<nav class="navbar nav-tabs" style="background: #5cb85c">
    <div class="container-fluid">
        <form class="navbar-form navbar-left" action="WelcomePage"
              method="post">

            <input type="hidden" name="action" value="WelcomePage">
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input class="form-control btn btn-success" type="submit"
                       value="<fmt:message key="label.button.WelcomePage"/>">
            </div>
        </form>
        <form action="WelcomePage" class="navbar-form navbar-right" method="post">
            <input type="hidden" name="action" value="InvalidateSession">
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input class="form-control btn btn-danger" type="submit"
                       value="<fmt:message key="label.button.Logout"/>">
            </div>
        </form>
        <form class="navbar-form navbar-right" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <input type="hidden" name="lang" value="be_BY"></input>
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="<fmt:message key="label.button.be"/>">
            </div>
        </form>
        <form class="navbar-form navbar-right" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <input type="hidden" name="lang" value="ru_RU"></input>
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="<fmt:message key="label.button.ru"/>">
            </div>
        </form>
        <form class="navbar-form navbar-right" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <input type="hidden" name="lang" value="en_US"></input>
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary" value="<fmt:message key="label.button.en"/>">
            </div>
        </form>

        <%--<form class="navbar-form navbar-left" action="WelcomePage" method="post">
            <input type="hidden" name="action" value="SetLocale">
            <div>
                <select class="form-control btn-primary" name="lang">
                    <option value="be_BY"><fmt:message key="label.button.be"/></option>
                    <option value="ru_RU"><fmt:message key="label.button.ru"/></option>
                    <option value="en_US"><fmt:message key="label.button.en"/></option>
                </select>
            </div>
            <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                <input type="submit" class="form-control btn btn-primary"
                       value="<fmt:message key="label.button.language"/> ">
            </div>
        </form>
--%>

        <c:if test="${logged==null}">
            <form class="navbar-form navbar-right" action="LoginPage" method="post">
                <input type="hidden" name="action" value="LoginPage">
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                    <input type="submit" class="form-control btn btn-success"
                           value="<fmt:message key="label.button.LoginPage"/>">
                </div>
            </form>
            <form class="navbar-form navbar-right" action="RegisterPage" method="post">
                <input type="hidden" name="action" value="RegisterPage">
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                    <input class="form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.RegisterPage"/>">
                </div>
            </form>
        </c:if>
        <c:if test="${logged!=null}">
            <p class="navbar-text btn-success">
                <pht:hello accessLevel="${sessionScope.accessLevel}" login="${login}"/>
            </p>
        </c:if>


    </div>
</nav>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
