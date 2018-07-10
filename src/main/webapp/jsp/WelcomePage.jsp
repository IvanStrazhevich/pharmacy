<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib  uri="pharmacyCustomTaglib" prefix="pht" %>

<fmt:setBundle basename="message" var="rb"/>
<html>
<head>
    <title><fmt:message key="label.button.WelcomePage" bundle="${rb}"/></title>
</head>


<style type="text/css">
    TABLE {
        border-collapse: collapse;
    }

    TD, TH {
        padding: 3px;
        border: 1px solid rgba(30, 66, 84, 0.97);
    }

    TH {
        background: #38b3cd; /* Цвет фона */
    }

    body {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #38b3cd;
        color: #616161;
    }
</style>
<body>
<fmt:setLocale value="${lang}" scope="request"/>
<%--<fmt:message key="message.welcomePage" bundle="${rb}"/>--%>
${userRegistered}
${greeting}
<pht:info-time locale="${lang}"/>
<pht:hello role="${accessLevel}" login= "${login}"/>
<form action="UploadPage"
method="get">
    <fmt:message key="label.button.language" bundle="${rb}"/>
<select name="lang" style="background: #38b3cd; color: #616161">
    <option value="be_BY">be</option>
    <option value="ru_RU">ru</option>
    <option value="en_US">en</option>
</select>
   <br>
    <input type="submit" value="<fmt:message key="label.button.UploadPage" bundle="${rb}"/>">
    <input type="hidden" name="action" value="UploadPage">
</form>
</body>
</html>
