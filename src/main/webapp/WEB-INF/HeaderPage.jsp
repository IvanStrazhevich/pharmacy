<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <title><fmt:message key="label.button.HeaderPage"/></title>
</head>
<body>
<div style="display: inline-list-item">
    <span style="float: left">
    <form action="WelcomePage"
          method="post">
        <input type="hidden" name="action" value="WelcomePage">
        <input class="btn btn-success" type="submit"
               value="<fmt:message key="label.button.WelcomePage"/>">
    </form>
</span>
    <span style="float: left">
    <form action="EditUserDataPage" method="post">
        <input type="hidden" name="action" value="EditUserDataPage">
        <input type="submit" class="btn btn-success"
               value="<fmt:message key="label.button.EditUserPage"/>">
    </form>
</span>
    <span style="float: left">
    <form action="MedicineListPage"
          method="post">
        <input type="hidden" name="action" value="MedicineList">
        <input class="btn btn-success" type="submit"
               value="<fmt:message key="label.button.MedicineListPage"/>">
    </form>
</span>
    <span style="float: left">
    <form action="LoginPage"
          method="post">
        <input type="hidden" name="action" value="LoginPage">
        <input type="submit" class="btn btn-success"
               value="<fmt:message key="label.button.LoginPage"/>">
    </form>
</span>
    <span style="float: left">
    <form action="RegisterPage"
          method="post">
        <input type="hidden" name="action" value="RegisterPage">
        <input class="btn btn-success" type="submit"
               value="<fmt:message key="label.button.RegisterPage"/>">
    </form>
    </span>
    <span style="float: left">
        <form action="WelcomePage"
              method="post">
        <input type="hidden" name="action" value="InvalidateSession">
        <input class="btn btn-danger" type="submit" value="<fmt:message key="label.button.Logout"/>">
    </form>
    </span>
    <form action="WelcomePage" method="post">
            <span style="float: left">
            <select class="btn btn-primary" name="lang">
                <option value="be_BY"><fmt:message key="label.button.be"/></option>
                <option value="ru_RU"><fmt:message key="label.button.ru"/></option>
                <option value="en_US"><fmt:message key="label.button.en"/></option>
            </select>
            </span>
        <span>
            <input type="hidden" name="action" value="SetLocale">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="label.button.language"/> ">
        </span>
    </form>
</div>
<br>

<span> <pht:hello accessLevel="${accessLevel}" login="${login}"/> </span>


<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
