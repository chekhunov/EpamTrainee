<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <fmt:message key="user_list.title" var="user_list_header"/>
    <title>
        ${user_list_header}
    </title>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
</head>
<body>
<c:choose>
    <c:when test="${not empty isAdmin}">
        <div class="main-content">
            <div class="head-content">
                    ${user_list_header}
                <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
                <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
            </div>
            <c:choose>
                <c:when test="${empty error}">

                    <div class="rooms-table">
                        <table class="rooms">
                            <tbody>
                            <tr>
                                <fmt:message key="registration.registration_form.name" var="name"/>
                                <fmt:message key="admin.order.user_id" var="id_user"/>
                                <fmt:message key="registration.registration_form.login" var="login"/>
                                <fmt:message key="registration.registration_form.email" var="email"/>
                                <fmt:message key="user_list.access_level" var="al"/>

                                <th>${name}</th>
                                <th>${id_user}</th>
                                <th>${login}</th>
                                <th>${email}</th>
                                <th>${al}</th>
                                <th>balance</th>

                            </tr>
                            <c:forEach items="${users_list}" var="user_var" varStatus="loop">
                            <tr>
                                <td>${user_var.name}</td>
                                <td>${user_var.userId}</td>
                                <td>${user_var.login}</td>
                                <td>${user_var.email}</td>
                               <td>${balance[loop.index]}</td>
                                <td>

                                    <fmt:message key="user_list.access_level.user" var="user_al"/>
                                    <fmt:message key="user_list.access_level.admin" var="admin_al"/>
                                    <c:choose>
                                        <c:when test="${user_var.accessLevel == 'ADMIN'}">
                                            ${admin_al}
                                        </c:when>
                                        <c:otherwise>
                                            ${user_al}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:if test="${user_var.accessLevel != 'ADMIN'}">
                                        <form action="users_list" method="post">
                                            <input type="hidden" name="user_id" value="${user_var.userId}">
                                            <fmt:message key="user_list.set_as_admin" var="set_as_admin"/>
                                            <input type="submit" value="${set_as_admin}">
                                            <input type="hidden" name="actionName" value="set_admin">
                                        </form>
                                        <div style="width: 50%; display: flex; justify-content: center; align-items: center;">
                                                        balance: ${balance}
                                        </div>
                                        <div style="width: 50%; display: flex; justify-content: center; align-items: center;">
                                         counter: ${counter}
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div class="paging">
                        <c:if test="${currentPage != 1}">
                            <fmt:message key="page.previous" var="previous"/>
                            <td><a href="users_list?page=${currentPage - 1}">${previous}</a></td>
                        </c:if>

                            <%--For displaying Page numbers.
                            The when condition does not display a link for the current page--%>
                        <table border="1" cellpadding="3" cellspacing="5">
                            <tbody>
                            <tr>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <td>${i}</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a href="users_list?page=${i}">${i}</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tr>
                            </tbody>
                        </table>

                            <%--For displaying Next link --%>
                        <c:if test="${currentPage lt noOfPages}">
                            <fmt:message key="page.next" var="next"/>
                            <td><a href="users_list?page=${currentPage + 1}">${next}</a></td>
                        </c:if>
                    </div>
                </c:when>
                <c:otherwise>
                    <fmt:message key="orders.error" var="users_list_error"/>
                    ${users_list_error}
                </c:otherwise>
            </c:choose>

            <div class="paging">
                <fmt:message key="ref.my_page" var="my_page"/>
                <h4><a href=<c:url value="/admin"/>>${my_page}</a></h4>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="form">
            <fmt:message key="auth.user_admin.error" var="auth_error"/>
                ${auth_error}
            <fmt:message key="ref.my_page" var="my_page"/>
            <h4><a href=<c:url value="/user"/>>${my_page}</a></h4>
        </div>
    </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
