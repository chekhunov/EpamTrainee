<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <fmt:message key="main.title" var="main_title"/>
    <title>${main_title}</title>
</head>
<html>
<body>
<div class="main-content">
    <div class="head-main-content">
        <fmt:message key="local.welcome" var="welcome"/>
        <h2>${welcome}</h2>
    </div>
    <div class="main-page">
    <fmt:message key="local.log_reg" var="log_reg"/>
    ${log_reg}
    <br>
    <fmt:message key="local.log_in_ref" var="log_in_ref"/>
    <h4><a href=<c:url value="/authentication"/>>${log_in_ref}</a></h4>
    <fmt:message key="local.reg_ref" var="reg_ref"/>
    <h4><a href=<c:url value="/registration"/>>${reg_ref}</a></h4>
    <br>
    <fmt:message key="local.choose_lang" var="choose_lang"/>
    ${choose_lang}
    <form action="" method="post">
        <fmt:message key="local.local_button.name.ru" var="ru_button"/>
        <input type="hidden" name="loc" value="ru"/>
        <input type="submit" value="${ru_button}"/><br/>
    </form>

    <form action="" method="post">
        <fmt:message key="local.local_button.name.en" var="en_button"/>
        <input type="hidden" name="loc" value="en"/>
        <input type="submit" value="${en_button}"/>
        <br>
    </form>
    <fmt:message key="local.message" var="message"/>
    ${message}
    </div>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
