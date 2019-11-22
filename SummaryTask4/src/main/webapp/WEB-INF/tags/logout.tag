<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="userLogin" type="java.lang.String"
              required="true" %>
<%@ attribute name="userName" type="java.lang.String" %>
<%@ attribute name="curr_lang" type="java.util.Locale" required="true" %>

<c:choose>
    <c:when test="${curr_lang == 'ru'}">
        <fmt:setLocale value="ru"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="local"/>

<form class="logout" action="<c:url value="/authentication"/>" method="POST">
    ${userName} (${userLogin})
        <fmt:message key="tag.logout" var="logout"/>

    <input type="submit" value="${logout}">
    <input type="hidden" name="actionName" value="logout">
</form>
