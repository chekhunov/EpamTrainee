<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${locale == 'ru'}">
        <fmt:setLocale value="ru"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="local"/>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
    <fmt:message key="registration.header" var="reg_header"/>
    <title>${reg_header}</title>
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        ${reg_header}
    </div>
    <div class="form">
        <form action="registration" method="post">
            <input type="hidden" name="actionName" value="registration"/>
            <fmt:message key="registration.registration_form.name" var="name"/>
            ${name}:
            <input type="text" name="name"/>
            <br>
            <fmt:message key="registration.registration_form.email" var="email"/>
            ${email}:
            <input type="text" name="email" required/>
            <br>
            <fmt:message key="registration.registration_form.login" var="login"/>
            ${login}:
            <input type="text" name="login" required/>
            <br>
            <fmt:message key="registration.registration_form.password" var="password"/>
            ${password}:
            <input type="password" name="password"/>
            <br>
            <fmt:message key="registration.registration_form.submit" var="submit"/>
            <input type="submit" value="${submit}">
            <br>

            <c:if test="${not empty reg_error}">
                <div class="error">
                    <fmt:message key="registration.registration_form.fail" var="reg_fail"/>
                        ${reg_fail}: ${reg_error}
                </div>
            </c:if>
            <br>
            <fmt:message key="registration.registration_form.authentication.log_in" var="log_in"/>
            <fmt:message key="registration.registration_form.authentication.if_registered" var="if_regd"/>
            <a href=<c:url value="/authentication"/>>${log_in}</a> ${if_regd}
        </form>
        <fmt:message key="registration.registration_form.main_page" var="main"/>
        <h4><a href=<c:url value="/"/>>${main}</a></h4>
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
