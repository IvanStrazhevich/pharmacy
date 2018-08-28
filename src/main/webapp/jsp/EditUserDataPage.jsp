<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<fmt:setLocale value="${fn:escapeXml(sessionScope.lang)}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/pharmacy.css" rel="stylesheet">
    <title><fmt:message key="label.button.EditUserPage"/></title>
</head>
<body>
<div>
    <c:import url="/WEB-INF/HeaderPage.jsp"/>
</div>
<h4>
    ${fn:escapeXml(validationError)}
</h4>
<div class="container-fluid container-table">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form action="EditUserDataPage"
                  enctype="multipart/form-data"
                  method="post">
                <fmt:message key="message.choosefile"/>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-camera"></span></span>

                    <input class="form-control" type="file" name="content"
                           value="<fmt:message key="label.button.choosefile"/>">
                </div>
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-upload"></span></span>

                    <input type="hidden" name="action" value="UploadPhoto">
                    <input class="form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.uploadfile"/>">
                </div>
            </form>
            <form accept-charset="utf-8" action="ClientDetailPage"
                  method="post">
                <c:set var="us" value="${user}"></c:set>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-inbox"></span></span>

                    <input class="form-control" id="email" type="email" name="email"
                    <c:choose>
                    <c:when test="${fn:escapeXml(us.email)!=null}"> value="${fn:escapeXml(us.email)}" </c:when>
                    <c:otherwise> value="${fn:escapeXml(sessionScope.login)}"</c:otherwise>
                    </c:choose>
                           pattern="\w{1,}@\w{3,}\.\w{2,4}" title="match email format"
                           required placeholder="<fmt:message key="message.enter.email"/>" maxlength="45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>

                    <input class="form-control" id="name" type="text" name="name" value="${fn:escapeXml(us.name)}"
                           pattern=".{1,45}" required placeholder="<fmt:message key="message.enter.name"/>"
                           maxlength="45" title="Any symbols, up to 45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>

                    <input class="form-control" id="lastname" type="text" name="lastname" value="${fn:escapeXml(us.lastname)}"
                           pattern=".{1,45}" required title="Any symbols, up to 45"
                           placeholder="<fmt:message key="message.enter.lastname"/>" maxlength="45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>

                    <input class="form-control" id="phone" type="text" name="phone" value="${fn:escapeXml(us.phone)}"
                           placeholder="<fmt:message key="message.enter.phone"/>"
                           pattern="\+?\d{7,15}"
                           title="+ contry code, operator/city code, phone number. 7 to 15 digit symbos"
                           required maxlength="15">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="postcode" type="text" name="postcode" value="${fn:escapeXml(us.postcode)}"
                           pattern="(\w-?){1,10}" title="Digits, letters, '-' symbols, up to 10" required
                           placeholder="<fmt:message key="message.enter.postcode"/>" maxlength="10">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="country" type="text" name="country" value="${fn:escapeXml(us.country)}"
                           pattern=".{1,45}" required placeholder="<fmt:message key="message.enter.country"/>"
                           maxlength="45" title="Any symbols, up to 45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="city" type="text" name="city" value="${fn:escapeXml(us.city)}"
                           pattern=".{1,45}" required placeholder="<fmt:message key="message.enter.city"/>"
                           maxlength="45" title="Any symbols, up to 45">

                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="address" type="text" name="address" value="${fn:escapeXml(us.address)}"
                           pattern=".{1,45}" required placeholder="<fmt:message key="message.enter.address"/>"
                           maxlength="45" title="Any symbols, up to 45">
                </div>
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-check"></span></span>

                    <input type="hidden" name="action" value="SaveClientDetail">
                    <input class="form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.confirm"/>"/>
                </div>

            </form>
            <form action="WelcomePage" method="post">
                <div class="form-group input-group input-group-lg col-md-8 col-md-offset-2 col-xs-12">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input type="hidden" name="action" value="WelcomePage">
                    <input class="form-control btn btn-primary" type="submit"
                           value="<fmt:message key="label.button.WelcomePage"/>"/>
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
