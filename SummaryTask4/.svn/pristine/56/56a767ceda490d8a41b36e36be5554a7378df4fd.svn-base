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
    <fmt:message key="admin.heading_admin" var="admin_header"/>
    <title>${admin_header}</title>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
</head>
<body>
<c:choose>
    <c:when test="${not empty isAdmin}">
        <div class="main-content">
            <div class="head-content">
                <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
                <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
                <form action="users_list" method="post">
                        ${admin_header}
                    <input type="hidden" name="actionName" value="getUsers">
                    <fmt:message key="admin.users" var="users_list"/>
                    <input type="submit" value="${users_list}">
                </form>
                <br>
                <table>
                    <tr>
                        <td>
                            <form action="" method="post">
                                <input type="hidden" name="actionName" value="all">
                                <fmt:message key="admin.orders.heading.all" var="all_orders"/>
                                <input type="submit" value="${all_orders}">
                            </form>
                        </td>
                        <td>
                            <form action="" method="post">
                                <input type="hidden" name="actionName" value="requested">
                                <fmt:message key="admin.orders.heading.requested" var="requested_orders"/>
                                <input type="submit" value="${requested_orders}">
                            </form>
                        </td>
                        <td>
                            <form action="" method="post">
                                <input type="hidden" name="actionName" value="in_discussion">
                                <fmt:message key="admin.orders.heading.in_discussion" var="in_discussion_orders"/>
                                <input type="submit" value="${in_discussion_orders}">
                            </form>
                        </td>
                        <td>
                            <form action="" method="post">
                                <input type="hidden" name="actionName" value="approved">
                                <fmt:message key="admin.orders.heading.approved" var="approved_orders"/>
                                <input type="submit" value="${approved_orders}">
                            </form>
                        </td>
                        <td>
                            <form action="" method="post">
                                <input type="hidden" name="actionName" value="paid">
                                <fmt:message key="admin.orders.heading.paid" var="paid_orders"/>
                                <input type="submit" value="${paid_orders}">
                            </form>
                        </td>
                        <td>
                            <form action="" method="post">
                                <input type="hidden" name="actionName" value="rejected">
                                <fmt:message key="admin.orders.heading.rejected" var="rejected_orders"/>
                                <input type="submit" value="${rejected_orders}">
                            </form>
                        </td>
                        <td>

                        </td>
                    </tr>
                </table>
            </div>
            <div class="rooms-table">
                <h4>${heading}</h4>
                <c:choose>
                <c:when test="${empty no_result}">
                <br>
                <table class="rooms">
                    <tbody>
                    <tr>
                        <th>
                            <fmt:message key="admin.order.user_id" var="user_id"/>
                                ${user_id}
                        </th>
                        <th>
                            <fmt:message key="user.orders.places" var="places"/>
                        </th>
                        <th>
                            <fmt:message key="user.orders.class" var="class_comfort"/>
                                ${class_comfort}
                        </th>
                        <th>
                            <fmt:message key="user.order.date_in" var="date_in"/>
                                ${date_in}
                        </th>
                        <th>
                            <fmt:message key="user.order.date_out" var="date_out"/>
                                ${date_out}
                        </th>
                        <th>
                            <fmt:message key="admin.order.apartment_id" var="apartment_id"/>
                                ${apartment_id}
                        </th>
                        <th>
                            <fmt:message key="admin.order.addit_ino" var="add_info"/>
                                ${add_info}
                        </th>
                        <th>
                            <fmt:message key="user.order.status" var="status"/>
                                ${status}
                        </th>
                        <th>
                            <fmt:message key="user.order.cost" var="cost"/>
                                ${cost}
                        </th>

                    </tr>
                    <c:forEach items="${orders_list}" var="order">
                        <tr>
                            <td>${order.userId}</td>
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
                            <td>${order.orderedAptId}</td>
                            <td>${order.additionalInfo}</td>
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
                                <form action="admin_order" method="get">
                                    <input type="hidden" name="order_id" value="${order.orderId}">
                                    <fmt:message key="user.order.details" var="details"/>
                                    <input type="submit" value="${details}">
                                </form>
                            </td>

                            <c:if test="${order.status=='REJECTED'}">
                                <td>
                                    <form action="remove_warning" method="post">
                                        <input type="hidden" name="order_id" value="${order.orderId}">
                                        <fmt:message key="user.order.remove" var="remove"/>
                                        <input type="submit" value="${remove}">
                                        <input type="hidden" name="actionName" value="remove_order">
                                    </form>
                                </td>
                            </c:if>

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
                        <fmt:message key="admin.orders.no_orders" var="no_orders"/>
                    </c:otherwise>
                    </c:choose>
                    <c:if test="${not empty error}">
                        <fmt:message key="orders.error" var="orders_search_error"/>
                        ${orders_search_error}
                    </c:if>
                    <fmt:message key="ref.my_user_page" var="my_user_page"/>
                    <h4><a href=<c:url value="/user"/>>${my_user_page}</a></h4>

                </div>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <fmt:message key="auth.user_admin.error" var="auth_error"/>
        ${auth_error}
        <fmt:message key="ref.my_page" var="my_page"/>
        <h4><a class="paging" href=<c:url value="/user"/>>${my_page}</a></h4>
    </c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
