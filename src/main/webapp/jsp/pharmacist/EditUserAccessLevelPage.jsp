<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href=“css/normalize.css">
    <link rel="stylesheet" type="text/css" href="css/pharmacy.css">
    <title><fmt:message key="label.button.EditUserAccessLevel"/></title>
</head>
<body>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
