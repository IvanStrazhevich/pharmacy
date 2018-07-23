<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css" rel="stylesheet">
    <title><fmt:message key="label.button.UserListPage"/></title>
</head>
<body>
<c:import url="/WEB-INF/HeaderPage.jsp"/>
<div>
    <h6>
        <table style="display: inline">
            <tr>
                <td>
                    <table>
                        <tr>
                            <td>
                                <table class="table table-striped table-hover table-bordered tableUpdated">
                                    <tr style="alignment: inherit">
                                        <th><fmt:message key="label.header.name"/></th>
                                        <th><fmt:message key="label.header.lastname"/></th>
                                    </tr>
                                    <c:forEach items="${clientDetails}" var="dt">
                                        <tr>
                                            <td>${dt.name}</td>
                                            <td>${dt.lastname}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>

                <td>
                    <table>
                        <tr>
                            <td>
                                <table class="table table-striped table-hover table-bordered tableUpdated">
                                    <tr style="alignment: inherit">
                                        <th><fmt:message key="label.header.id"/></th>
                                        <th><fmt:message key="label.header.accessLevel"/></th>
                                        <th></th>
                                    </tr>
                                    <c:forEach items="${users}" var="us">
                                        <tr>
                                            <td>${us.userId}</td>
                                            <td>${us.accessLevel}</td>
                                            <td>
                                                <form action="EditAccessLevelPage" method="post">
                                                    <input type="submit" class="btn btn-primary"
                                                           value="<fmt:message key="label.button.EditUserAccessLevel"/>">
                                                    <input type="hidden" name="action" value="EditAccessLevel">
                                                    <input type="hidden" value="${us.userId}" name="userId">
                                                </form>
                                            </td>

                                        </tr>
                                    </c:forEach><br>

                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </h6>
</div>
<div><c:import url="/WEB-INF/FooterPage.jsp"/></div>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
