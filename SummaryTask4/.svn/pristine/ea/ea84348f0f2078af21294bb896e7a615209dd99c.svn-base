<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
    <fmt:message key="login.header" var="log_header"/>
    <title>${log_header}</title>
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        ${log_header}
    </div>
    <div class="form">
        <form action="" method="post">
            <input type="hidden" name="actionName" value="authentication"/>
            <fmt:message key="login.login_form.login" var="login"/>
            ${login}:
            <input type="text" name="login" required/>
            <br>
            <fmt:message key="login.login_form.password" var="password"/>
            ${password}:
            <input type="password" name="password" required/>
            <br>
            <fmt:message key="login.login_form.submit" var="submit"/>
            <input type="submit" value="${submit}"/>

            <c:if test="${not empty auth_error}">
                <fmt:message key="login.login_form.fail" var="fail"/>
                <div class="error">
                        ${fail}: ${auth_error}
                </div>
            </c:if>
            <fmt:message key="login.login_form.reg_ref" var="reg_ref"/>
            <h4><a href=<c:url value="/registration"/>>${reg_ref}</a></h4>

        </form>
        <fmt:message key="registration.registration_form.main_page" var="main"/>
        <h4><a href=<c:url value="/"/>>${main}</a></h4>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
