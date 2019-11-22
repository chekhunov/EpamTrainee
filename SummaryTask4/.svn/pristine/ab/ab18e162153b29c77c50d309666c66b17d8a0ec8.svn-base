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
    <fmt:message key="order.bill.heading" var="bill_heading"/>
    <title>${bill_heading}</title>
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <fmt:message key="order.bill.title" var="bill_title"/>
        ${bill_title}
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
    </div>
    <div class="warning">
        <c:choose>
            <c:when test="${empty error}">
                <fmt:message key="order.bill.final_cost.1" var="fc1"/>
                <fmt:message key="order.bill.final_cost.2" var="fc2"/>
                <fmt:message key="order.bill.final_cost.3" var="fc3"/>
                <fmt:message key="order.bill.final_cost.4" var="fc4"/>
                <fmt:message key="order.bill.final_cost.5" var="fc5"/>
                <fmt:message key="order.bill.final_cost.6.1" var="fc61"/>
                <fmt:message key="order.bill.final_cost.6.2" var="fc62"/>
                <fmt:message key="order.bill.final_cost.7" var="fc7"/>
                ${fc1} №${room.number},
                <c:choose>
                    <c:when test="${order.classOfComfort == 1}">
                        <fmt:message key="user.order.class.lux" var="lux"/>
                        ${lux}
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="user.order.class.economy" var="economy"/>
                        ${economy}
                    </c:otherwise>
                </c:choose> ${fc2} ${fc3} ${order.dateIn} ${fc4} ${order.dateOut} ${fc5} ${order.places}
                <c:choose>
                    <c:when test="${order.places==1}">
                        ${fc61}
                    </c:when>
                    <c:otherwise>
                        ${fc62}
                    </c:otherwise>
                </c:choose>
                ${fc7}
                ${order.cost}$.
                <br>
                <fmt:message key="order.bill.submit_info" var="bill_submit_info"/>
                ${bill_submit_info}:
                <br>
                <fmt:message key="order.bill.submit" var="bill_submit"/>
                <form action="bill" method="post">
                    <input type="hidden" name="actionName" value="bill">
                    <input type="submit" value="${bill_submit}">
                </form>
                <br>
            </c:when>
            <c:otherwise>
                <fmt:message key="order.bill.error_pay" var="bill_error"/>
                ${bill_error}
            </c:otherwise>
        </c:choose>
        <br>
        <br>
        <fmt:message key="ref.my_page" var="my_page"/>
        <a href=<c:url value=""/>>${my_page}</a>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
