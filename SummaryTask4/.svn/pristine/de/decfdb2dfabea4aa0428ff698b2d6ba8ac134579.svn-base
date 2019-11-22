<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>

<fmt:message bundle="${naming}" key="mainHeader.label.profile" var="profile"/>
<fmt:message bundle="${naming}" key="admin.label.orders" var="orders"/>
<fmt:message bundle="${naming}" key="user.label.balance" var="balance"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/verticalMenuStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<div class="vertical-menu">
    <div class="buttons">
        <a href="controller?command=showProfile">${profile}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showOrders">${orders}</a>
    </div>
    <div class="buttons">
        <a href="controller?command=showBalance">${balance}</a>
    </div>
</div>
</body>
</html>




