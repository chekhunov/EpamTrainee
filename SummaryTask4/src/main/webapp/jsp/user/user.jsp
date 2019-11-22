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
    <fmt:message key="user.header" var="user_header"/>
    <title>${user_header}</title>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
        <fmt:message key="user.new_order" var="new_order"/>
        <form action="" method="post">
            <fmt:message key="user.new_order.button" var="new_order_button"/>
            <input type="hidden" name="actionName" value="newOrder">
            <input type="submit" value="${new_order_button}">
            <br>
        </form>
    </div>

    <div class="rooms-table">
         <div style="display: flex; width: 100%;">
            <div style="width: 50%;">

                <fmt:message key="user.my_orders" var="my_orders"/>
                <p>${my_orders}:</p>
         </div>
          </div>







        <c:choose>
        <c:when test="${empty no_result}">
        <br>
        <table class="rooms">
            <tbody>
            <tr>
                <fmt:message key="user.orders.places" var="places"/>
                <th>${places}</th>
                <fmt:message key="user.orders.class" var="class_comf"/>
                <th>${class_comf}</th>
                <fmt:message key="user.order.date_in" var="order_date_in"/>
                <th>${order_date_in}</th>
                <fmt:message key="user.order.date_out" var="order_date_out"/>
                <th>${order_date_out}</th>
                <fmt:message key="user.order.status" var="status"/>
                <th>${status}</th>
                <fmt:message key="user.order.cost" var="cost"/>
                <th>${cost}</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${new_orders_list}" var="order">
                <tr>
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
                    </c:choose>
                    </td>
                    <td>${order.dateIn}</td>
                    <td>${order.dateOut}</td>

                    <td><tags:status order_status="${order.status}" curr_lang="${locale}"/>
                    </td>
                    <td><c:choose>
                        <c:when test="${order.cost != 0}">
                            ${order.cost}$
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <form action="user_order" method="get">
                            <fmt:message key="user.order.details" var="details"/>
                            <input type="hidden" name="order_id" value="${order.orderId}">
                            <input type="submit" value="${details}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${not empty old_orders_list}">
                <tr>
                    <br>
                    <fmt:message key="user.old_orders" var="old_orders"/>
                    <td>${old_orders}</td>
                </tr>
            </c:if>
            <c:forEach items="${old_orders_list}" var="order">
                <tr>
                    <td>${order.places}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.classOfComfort == 1}">
                                ${lux}
                            </c:when>
                            <c:otherwise>
                                ${economy}
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>${order.dateIn}</td>
                    <td>${order.dateOut}</td>

                    <td><tags:status order_status="${order.status}" curr_lang="${locale}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${order.cost != 0}">
                                ${order.cost}$
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="user_order" method="get">
                            <fmt:message key="user.order.details" var="detail"/>
                            <input type="hidden" name="order_id" value="${order.orderId}">
                            <input type="submit" value="${detail}">
                        </form>
                    </td>
                    <td>
                        <form action="remove_warning" method="post">
                            <input type="hidden" name="order_id" value="${order.orderId}">
                            <fmt:message key="user.order.remove" var="remove"/>
                            <input type="submit" value="${remove}">
                            <input type="hidden" name="actionName" value="remove_order">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="paging">

            <c:if test="${currentPage != 1}">
                <fmt:message key="page.previous" var="previous"/>
                <td><a href="?page=${currentPage - 1}">${previous}</a></td>
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
                                <td><a href="?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
                </tbody>
            </table>

                <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <fmt:message key="page.next" var="next"/>
                <td><a href="?page=${currentPage + 1}">${next}</a></td>
            </c:if>

            </c:when>
            <c:otherwise>
                <fmt:message key="user.orders.no_orders" var="no_result"/>
                ${no_result}
            </c:otherwise>
            </c:choose>

            <c:if test="${not empty isAdmin}">
                <fmt:message key="user.user_admin.user_page" var="as_admin"/>
                <h4><a class="paging" href=
                        <c:url value="/admin"/>>${as_admin}</a></h4>
            </c:if>
        </div>
    </div>
    <c:if test="${not empty error}">
        <fmt:message key="orders.error" var="orders_list_error"/>
        ${orders_list_error}
    </c:if>
</div>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
