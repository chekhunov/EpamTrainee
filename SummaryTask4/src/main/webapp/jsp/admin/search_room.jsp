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
    <fmt:message key="admin.search_room.heading" var="search_heading"/>
    <title>${search_heading}</title>
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
        ${search_heading}
    </div>
    <c:choose>
        <c:when test="${not empty isAdmin and not empty order_id}">
            <c:choose>
                <c:when test="${not empty result_list}">

                    <fmt:message key="admin.search_room.number" var="room_number"/>
                    <fmt:message key="admin.search_room.places" var="room_places"/>
                    <fmt:message key="admin.search_room.class_comfort" var="room_class"/>
                    <fmt:message key="admin.search_room.cost" var="room_cost"/>
                    <div class="small-table">
                        <table class="rooms">
                            <tr>
                                <th>${room_number}</th>
                                <th>${room_places}</th>
                                <th>${room_class}</th>
                                <th>${room_cost}</th>
                            </tr>
                            <c:forEach items="${result_list}" var="order">
                                <tr>
                                    <td>
                                        <a href=<c:url value="room/${order.aptId}"/>>${order.number}</a>
                                    </td>
                                    <td>${order.places}</td>
                                    <td><c:choose>
                                        <c:when test="${order.classOfComfort == 1}">
                                            <fmt:message key="user.order.class.lux" var="lux"/>
                                            ${lux}
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="user.order.class.economy" var="economy"/>
                                            ${economy}
                                        </c:otherwise>
                                    </c:choose></td>
                                    <td>${order.cost}$</td>
                                    <td>
                                        <form action="admin_order" method="get">
                                            <fmt:message key="admin.search_room.offer" var="offer"/>
                                            <input type="hidden" name="room_id" value="${order.aptId}">
                                            <input type="hidden" name="order_id" value="${order_id}">
                                            <input type="submit" value="${offer}">
                                        </form>

                                    </td>

                                </tr>

                            </c:forEach>
                        </table>
                        <c:choose>
                            <c:when test="${empty error}">
                                <c:if test="${currentPage != 1}">
                                    <fmt:message key="page.previous" var="previous"/>
                                    <td><a href="search_room?page=${currentPage - 1}">${previous}</a></td>
                                </c:if>
                                <div class="small-paging">
                                <%--For displaying Page numbers.
                                The when condition does not display a link for the current page--%>
                                <table border="1" cellpadding="3" cellspacing="5">
                                    <tr>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    <td>${i}</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><a href="search_room?page=${i}">${i}</a></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </table>

                                <%--For displaying Next link --%>
                                <c:if test="${currentPage lt noOfPages}">
                                    <fmt:message key="page.next" var="next"/>
                                    <td><a href="search_room?page=${currentPage + 1}">${next}</a></td>
                                </c:if>
                                    </div>
                            </c:when>

                            <c:otherwise>
                                <fmt:message key="orders.error" var="err_msg"/>
                                ${err_msg}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:when>
                <c:otherwise>
                    <fmt:message key="admin.search_room.no_res" var="no_res"/>
                    ${no_res}
                </c:otherwise>
            </c:choose>
            <div class="paging">
                <fmt:message key="ref.my_page" var="my_page"/>
                <h4><a href=<c:url value="/admin"/>>${my_page}</a></h4>
            </div>

        </c:when>
        <c:otherwise>
            <div class="form">
                <fmt:message key="auth.user_admin.error" var="auth_err"/>
                <fmt:message key="ref.my_page" var="my_page"/>
                    ${auth_err}
                <c:choose>
                    <c:when test="${not empty isAdmin}">
                        <h4><a href=<c:url value="/admin"/>>${my_page}</a></h4>
                    </c:when>
                    <c:otherwise>
                        <h4><a href=<c:url value="/user"/>>${my_page}</a></h4>
                    </c:otherwise>
                </c:choose>

            </div>
        </c:otherwise>
    </c:choose>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
