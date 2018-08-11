<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title><fmt:message key="label.button.HeaderPage"/></title>
</head>
<body>
<nav class="navbar" style="background: #5cb85c; padding: 0px; margin: 0px">
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
            <div class="form-group input-group input-group-lg col-md-0 col-md-offset-0 col-xs-12">
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
            <p class="navbar-text btn-success" style="background: #5cb85c">
                <pht:hello accessLevel="${sessionScope.accessLevel}" login="${login}" photo="${photo}"/>
            </p>
        </c:if>
    </div>
</nav>
<nav class="navbar" style="background: #5cb85c; padding: 0px; margin: 0px">
    <div class="container-fluid container-table">
            <form class="navbar-form navbar-left" action="MedicineListPage" method="post">
                <input type="hidden" name="action" value="MedicineList">
                <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                    <input class=" form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.MedicineListPage"/>">
                </div>
            </form>
            <c:if test="${sessionScope.accessLevel=='doctor'}">
                <form class="navbar-form navbar-left" action="RecipeListPage" method="post">
                    <input type="hidden" name="action" value="RecipeList">
                    <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                        <input class=" form-control btn btn-warning" type="submit"
                               value="<fmt:message key="label.button.RecipeListPage"/>">
                    </div>
                </form>
            </c:if>
            <c:if test="${logged!=null}">

                <form class="navbar-form navbar-left" action="EditUserDataPage" method="post">
                    <input type="hidden" name="action" value="EditUserDataPage">
                    <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                        <input class=" form-control btn btn-success" type="submit"
                               value="<fmt:message key="label.button.EditUserPage"/>">
                    </div>
                </form>
                <c:if test="${accessLevel!='pharmacist'}">
                    <form class="navbar-form navbar-left" action="EditOrderPage" method="post">
                        <input type="hidden" name="action" value="EditOrder">
                        <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                            <input class=" form-control btn btn-success" type="submit"
                                   value="<fmt:message key="label.button.EditOrderPage"/>">
                        </div>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.accessLevel=='pharmacist'}">
                <form class="navbar-form navbar-left" action="EditMedicinePage" method="post">
                    <input type="hidden" name="medicineId" value="0">
                    <input type="hidden" name="action" value="EditMedicine">
                    <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                        <input type="submit" class=" form-control btn btn-primary"
                               value="<fmt:message key="label.button.newMedicine"/>">
                    </div>
                </form>
                <form class="navbar-form navbar-left" action="UserListPage" method="post">
                    <input type="hidden" name="action" value="UserList">
                    <div class="form-group input-group input-group-lg col-md-8 col-md-offset-0 col-xs-12">
                        <input class="form-control btn btn-primary" type="submit"
                               value="<fmt:message key="label.button.UserListPage"/>">
                    </div>
                </form>
            </c:if>
    </div>
</nav>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
