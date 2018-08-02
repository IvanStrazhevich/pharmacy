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
            <div class="form-group">
                <input type="hidden" name="action" value="WelcomePage">
                <input class="btn btn-success" type="submit"
                       value="<fmt:message key="label.button.WelcomePage"/>">
            </div>
        </form>
        <c:if test="${logged!=null}">
            <form class="navbar-form navbar-left" action="EditUserDataPage" method="post">
                <div class="form-group">
                    <input type="hidden" name="action" value="EditUserDataPage">
                    <input type="submit" class="btn btn-success"
                           value="<fmt:message key="label.button.EditUserPage"/>">
                </div>
            </form>
            <form class="navbar-form navbar-left" action="EditOrderPage" method="post">
                <div class="form-group">
                    <input type="hidden" name="action" value="EditOrder">
                    <input type="submit" class="btn btn-primary"
                           value="<fmt:message key="label.button.EditOrderPage"/>">
                </div>
            </form>
        </c:if>

        <form class="navbar-form navbar-left" action="MedicineListPage"
              method="post">
            <div class="form-group">

                <input type="hidden" name="action" value="MedicineList">
                <input class="btn btn-success" type="submit"
                       value="<fmt:message key="label.button.MedicineListPage"/>">
            </div>
        </form>
        <c:if test="${sessionScope.accessLevel=='doctor'}">
            <form class="navbar-form navbar-left" action="RecipeApprovalPage" method="post">
                <div class="form-group">
                    <input type="hidden" name="action" value="EditRecipe">
                    <input type="submit" class="btn btn-primary"
                           value="<fmt:message key="label.button.RecipeApproval"/>">
                </div>
            </form>
            <form class="navbar-form navbar-left" action="RecipeListPage"
                  method="post">
                <div class="form-group">
                    <input type="hidden" name="action" value="RecipeList">
                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="label.button.RecipeListPage"/>">
                </div>
            </form>
        </c:if>
        <form class="navbar-form navbar-left" action="WelcomePage" method="post">
            <div class="form-group">
                <select class="btn-primary" name="lang">
                    <option value="be_BY"><fmt:message key="label.button.be"/></option>
                    <option value="ru_RU"><fmt:message key="label.button.ru"/></option>
                    <option value="en_US"><fmt:message key="label.button.en"/></option>
                </select>
            </div>
            <div class="form-group">
                <input type="hidden" name="action" value="SetLocale">
                <input type="submit" class="btn btn-primary" value="<fmt:message key="label.button.language"/> ">
            </div>
        </form>
        <form action="WelcomePage" class="navbar-form navbar-right"
              method="post">
            <div class="form-group">
                <input type="hidden" name="action" value="InvalidateSession">
                <input class="btn btn-danger" type="submit" value="<fmt:message key="label.button.Logout"/>">
            </div>
        </form>
        <c:if test="${logged==null}">
            <form class="navbar-form navbar-right" action="LoginPage"
                  method="post">
                <div class="form-group">
                    <input type="hidden" name="action" value="LoginPage">
                    <input type="submit" class="btn btn-success"
                           value="<fmt:message key="label.button.LoginPage"/>">
                </div>
            </form>
            <form class="navbar-form navbar-right" action="RegisterPage"
                  method="post">
                <div class="form-group">
                    <input type="hidden" name="action" value="RegisterPage">
                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="label.button.RegisterPage"/>">
                </div>
            </form>
        </c:if>
        <p class="navbar-text ">
            <c:if test="${logged!=null}">
                <pht:hello accessLevel="${sessionScope.accessLevel}" login="${login}"/>
            </c:if>
        </p>

    </div>
</nav>

<ul class="nav navbar-left container-fluid" style="background: #5cb85c; padding: 10px; height: auto" >
    <li>
        <form action="MedicineListPage"
              method="post">
                <input type="hidden" name="action" value="MedicineList">
                <input class="btn btn-success" type="submit"
                       value="<fmt:message key="label.button.MedicineListPage"/>">
        </form>
    </li>
    <c:if test="${sessionScope.accessLevel=='doctor'}">
        <li>
            <form action="RecipeApprovalPage" method="post">
                    <input type="hidden" name="action" value="EditRecipe">
                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="label.button.RecipeApproval"/>">
            </form>
        </li>
        <li>
            <form action="RecipeListPage"
                  method="post">
                    <input type="hidden" name="action" value="RecipeList">
                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="label.button.RecipeListPage"/>">
            </form>
        </li>
    </c:if>

    <c:if test="${logged!=null}">
        <li>
            <form action="EditUserDataPage" method="post">
                    <input type="hidden" name="action" value="EditUserDataPage">
                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="label.button.EditUserPage"/>">
            </form>
        </li>
        <li>
            <form action="EditOrderPage" method="post">
                    <input type="hidden" name="action" value="EditOrder">
                    <input class="btn btn-success" type="submit"
                           value="<fmt:message key="label.button.EditOrderPage"/>">
            </form>
        </li>
    </c:if>
</ul>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
