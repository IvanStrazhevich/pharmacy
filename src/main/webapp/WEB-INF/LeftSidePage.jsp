<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pharmacyCustomTaglib" prefix="pht" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${lang}" scope="session"/>
<fmt:setBundle basename="message"/>
<html>
<head>
    <link href="css/bootstrap.css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="label.button.LeftSidePage"/></title>
</head>
<body>
<div class="container-fluid container-table">
    <div class="row">
        <div class="col-md-2 col-md-offset-0">
            <form action="MedicineListPage"
                  method="post">
                <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                    <input type="hidden" name="action" value="MedicineList">
                    <input class=" form-control btn btn-success" type="submit"
                           value="<fmt:message key="label.button.MedicineListPage"/>">
                </div>
            </form>
            <c:if test="${sessionScope.accessLevel=='doctor'}">
                <form action="RecipeListPage"
                      method="post">
                    <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                        <input type="hidden" name="action" value="RecipeList">
                        <input class=" form-control btn btn-warning" type="submit"
                               value="<fmt:message key="label.button.RecipeListPage"/>">
                    </div>
                </form>
            </c:if>
            <c:if test="${logged!=null}">

                <form action="EditUserDataPage" method="post">
                    <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                        <input type="hidden" name="action" value="EditUserDataPage">
                        <input class=" form-control btn btn-success" type="submit"
                               value="<fmt:message key="label.button.EditUserPage"/>">
                    </div>
                </form>
                <form action="EditOrderPage" method="post">
                    <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                        <input type="hidden" name="action" value="EditOrder">
                        <input class=" form-control btn btn-success" type="submit"
                               value="<fmt:message key="label.button.EditOrderPage"/>">
                    </div>
                </form>
            </c:if>
            <c:if test="${sessionScope.accessLevel=='pharmacist'}">
                <form action="EditMedicinePage" method="post">
                    <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                        <input type="hidden" name="medicineId" value="0">
                        <input type="hidden" name="action" value="EditMedicine">
                        <input type="submit" class=" form-control btn btn-primary"
                               value="<fmt:message key="label.button.newMedicine"/>">
                    </div>
                </form>
                <form action="UserListPage" method="post">
                    <div class="form-group input-group input-group-lg col-md-4 col-md-offset-0 col-xs-2">
                        <input type="hidden" name="action" value="UserList">
                        <input class="form-control btn btn-primary" type="submit"
                               value="<fmt:message key="label.button.UserListPage"/>">
                    </div>
                </form>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
