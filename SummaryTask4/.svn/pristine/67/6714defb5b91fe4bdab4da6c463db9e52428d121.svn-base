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
    <fmt:message key="order.warning_header" var="reject_header"/>
    <title>${reject_header}</title>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="main-content">
    <div class="head-content">
        ${reject_header}
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
    </div>
    <div class="warning">
        <c:choose>
            <c:when test="${not empty order_id}">
                <form action="reject_warning" method="post">
                    <fmt:message key="order.reject.confirm_info" var="confirm_info"/>
                    <fmt:message key="order.remove.confirm" var="confirm_reject"/>
                        ${confirm_info}:
                    <input type="submit" value="${confirm_reject}">
                    <input type="hidden" name="actionName" value="confirmed">
                        <%--<input type="hidden" name="order_id" value="${order_id}">--%>

                </form>
                <c:if test="${not empty error}">
                    <fmt:message key="order.reject.error_msg" var="reject_error"/>
                    ${reject_error}
                </c:if>
            </c:when>
            <c:otherwise>
                <fmt:message key="auth.user_admin.error" var="auth_error"/>
                ${auth_error}
            </c:otherwise>
        </c:choose>
        <fmt:message key="order.remove.back" var="back"/>
        <c:choose>
            <c:when test="${not empty isAdmin}">
                <h4><a href=<c:url value="/admin"/>>${back}</a></h4>
            </c:when>
            <c:otherwise>
                <h4><a href=<c:url value="/user"/>>${back}</a></h4>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
