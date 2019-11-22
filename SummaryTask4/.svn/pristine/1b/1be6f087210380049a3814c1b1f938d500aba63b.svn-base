<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<c:choose>
    <c:when test="${locale == 'ru'}">
        <fmt:setLocale value="ru"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="local"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
    <fmt:message key="room.heading" var="room_heading"/>
    <title>${room_heading}</title>
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
        <fmt:message key="room.heading.number" var="heading_number"/>
        ${heading_number} №${room.number}
    </div>
    <div  class="form">
    <c:choose>
        <c:when test="${empty error}">
            <fmt:message key="room.living_places" var="living_places"/>
            <fmt:message key="room.class_comfort" var="class_comfort"/>
            <fmt:message key="room.cost_day" var="cost_day"/>
            <fmt:message key="room.description" var="descr"/>
            <form action="/admin_order" method="get">
                    ${living_places}: ${room.places}
                <br>
                    ${class_comfort}: ${room.classOfComfort}
                <br>
                    ${cost_day}: ${room.cost}
                <br>
                    ${descr}: ${description}
                <fmt:message key="room.back_to_order" var="back_to_order"/>
                <c:choose>
                    <c:when test="${not empty isAdmin}">
                        <fmt:message key="admin.search_room.offer" var="offer"/>
                        <br>
                        <input type="submit" value="${offer}">
                        <input type="hidden" name="room_id" value="${room.aptId}">
                        <input type="hidden" name="order_id" value="${order.orderId}">
                        <h4><a class="paging" href=<c:url
                                value="/admin_order?order_id=${order_id}"/>>${back_to_order}</a></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><a class="paging" href=<c:url
                                value="/user_order?order_id=${order_id}"/>>${back_to_order}</a></h4>
                    </c:otherwise>
                </c:choose>
            </form>
        </c:when>
        <c:otherwise>
            <fmt:message key="orders.error" var="err_msg"/>
            ${err_msg}
        </c:otherwise>
    </c:choose>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
