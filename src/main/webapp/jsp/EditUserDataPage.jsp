<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/pharmacy.css" rel="stylesheet">
    <title><fmt:message key="label.button.EditUserPage"/></title>
</head>
<body>
<div>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
</div>
<h4>
${validationError}
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

                    <input type="hidden" name="action" value="UploadResult">
                    <input class="form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.uploadfile"/>">
                </div>
            </form>
            <form accept-charset="utf-8" action="ClientDetailPage"
                  method="post">
                <c:set var="us" value="${user}"></c:set>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-inbox"></span></span>

                    <input class="form-control" id="email" type="email" name="email" value="${us.email}"
                           placeholder="<fmt:message key="message.enter.email"/>" maxlength="45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>

                    <input class="form-control" id="name" type="text" name="name" value="${us.name}"
                           placeholder="<fmt:message key="message.enter.name"/>"
                           maxlength="45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>

                    <input class="form-control" id="lastname" type="text" name="lastname" value="${us.lastname}"
                           placeholder="<fmt:message key="message.enter.lastname"/>" maxlength="45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span>

                    <input class="form-control" id="phone" type="text" name="phone" value="${us.phone}"
                           placeholder="<fmt:message key="message.enter.phone"/>"
                           maxlength="15">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="postcode" type="text" name="postcode" value="${us.postcode}"
                           placeholder="<fmt:message key="message.enter.postcode"/>" maxlength="10">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="country" type="text" name="country" value="${us.country}"
                           placeholder="<fmt:message key="message.enter.country"/>"
                           maxlength="45">
                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="city" type="text" name="city" value="${us.city}"
                           placeholder="<fmt:message key="message.enter.city"/>"
                           maxlength="45">

                </div>
                <div class="form-group input-group input-group-lg">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span>

                    <input class="form-control" id="address" type="text" name="address" value="${us.address}"
                           placeholder="<fmt:message key="message.enter.address"/>"
                           maxlength="45">
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
