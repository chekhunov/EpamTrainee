<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/tld/usernameDescriptor.tld" prefix="username" %>
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
    <fmt:message key="user.order_page.heading" var="op_heading"/>
    <title>${op_heading}</title>
    <link type="text/css" rel="stylesheet" href="/css/main.css">
</head>
<body>
<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
        ${op_heading}
    </div>

    <c:choose>
    <c:when test="${user.userId==order.userId}">

    <div class="form">
        <fmt:message key="user.order_page.order_details" var="order_details"/>
        <fmt:message key="user.order_page.order_id" var="order_id"/>
        <fmt:message key="user.order_page.req_places" var="req_places"/>
        <fmt:message key="user.order_page.req_class" var="req_class"/>
            ${order_details}: <br>
            ${order_id}: ${order.orderId} <br>
            ${req_places}: ${order.places} <br>
            ${req_class}:
        <c:choose>
            <c:when test="${order.classOfComfort == 1}">
                <fmt:message key="user.order.class.lux" var="lux"/>
                ${lux}
            </c:when>
            <c:otherwise>
                <fmt:message key="user.order.class.economy" var="economy"/>
                ${economy}
            </c:otherwise>
        </c:choose>
        <br>
        <fmt:message key="user.order_page.dates" var="dates"/>
        <fmt:message key="user.order_page.dates.1" var="dates_from"/>
        <fmt:message key="user.order_page.dates.2" var="dates_to"/>
            ${dates}: ${dates_from} ${order.dateIn} ${dates_to} ${order.dateOut} <br>
        <fmt:message key="user.order_page.status" var="status"/>
            ${status}:<tags:status order_status="${order.status}" curr_lang="${locale}"/>
        <br>
        <fmt:message key="user.order_page.view_room_details" var="room_details"/>
        <c:choose>
            <c:when test="${order.status == 'REQUESTED'}">
                <fmt:message key="user.order_page.requested_info" var="requested_info"/>
                ${requested_info}
            </c:when>
            <c:when test="${order.status == 'IN_DISCUSSION'}">
                <fmt:message key="user.order_page.offered_apt_id" var="offered_apt_id"/>
                ${offered_apt_id}: ${apt.number}

                <h4><a href=<c:url value="/room/${apt.aptId}"/>>${room_details}</a></h4>

                <form action="user_order" method="get">
                    <fmt:message key="user.order_page.approve" var="approve_order"/>
                    <input type="submit" value="${approve_order}">
                    <input type="hidden" name="order_id" value="${order.orderId}">
                    <input type="hidden" name="actionName" value="approve">
                </form>
            </c:when>
            <c:when test="${order.status == 'APPROVED'}">
                <fmt:message key="user.order_page.bill_info" var="bill_info"/>
                ${bill_info}<br>
                <fmt:message key="user.order_page.bill.1" var="bill1"/>
                <fmt:message key="user.order_page.bill.2" var="bill2"/>
                <fmt:message key="user.order_page.bill.3" var="bill3"/>
                <fmt:message key="user.order_page.bill.4" var="bill4"/>
                ${bill1} №${apt.number} ${bill2} ${order.dateIn} ${bill3} ${order.dateOut} ${bill4} ${order.cost}$<br>
                <fmt:message key="user.order_page.pay_info" var="pay_info"/>
                ${pay_info}:
                <form action="bill" method="get">
                    <fmt:message key="user.order_page.pay" var="pay"/>
                    <input type="submit" value="${pay}">
                </form>
                <h4><a href=<c:url value="/room/${apt.aptId}"/>>${room_details}</a></h4>
            </c:when>
            <c:when test="${order.status == 'PAID'}">
                <fmt:message key="user.order_page.paid_info" var="paid_info"/>
                ${paid_info}
                <h4><a href=<c:url value="/room/${apt.aptId}"/>>${room_details}</a></h4>

            </c:when>
            <c:when test="${order.status == 'REJECTED'}">
                <fmt:message key="user.order_page.rejected_info" var="rejected_info"/>
                ${rejected_info}
            </c:when>
        </c:choose>
        <c:if test="${not empty error}">
            <fmt:message key="orders.error" var="error_msg"/>
            ${error_msg}
        </c:if>
        <c:if test="${order.status != 'REJECTED' and order.status != 'PAID' and order.status != 'APPROVED'}">
            <form action="edit_order" method="post">
                <fmt:message key="user.order_page.edit" var="edit_order"/>
                <input type="submit" value="${edit_order}">
                <input type="hidden" name="actionName" value="editOrder">
            </form>
        </c:if>

        <c:if test="${order.status != 'REJECTED'}">
            <form action="reject_warning" method="post">
                <fmt:message key="user.order_page.reject" var="reject_order"/>
                <input type="submit" value="${reject_order}">
                <input type="hidden" name="actionName" value="reject">
            </form>
        </c:if>

        <fmt:message key="user.order_page.comments_info" var="comments_info"/>
            <%--${comments_info}--%>

        <form action="user_order" method="post">
            <fmt:message key="user.order_page.leave_comment_info" var="leave_comment_info"/>


            <br>
            <textarea name="comment" rows="4" cols="50"></textarea> <br>
            <input type="hidden" name="actionName" value="send_comment">
            <fmt:message key="user.order_page.leave_comment" var="leave_comment"/>
            <input type="submit" value="${leave_comment}">
        </form>
        <br>

        <br>
        <fmt:message key="user.order_page.comments" var="comments"/>
            ${comments}:
        <br>
        <c:if test="${not empty no_comments}">
            <fmt:message key="user.order_page.no_comments" var="no_comm"/>
            ${no_comm}
        </c:if>
        <c:forEach items="${comment_list}" var="comment">
            <username:usernameTagHandler userId="${comment.userId}" lang="${locale}"/>: ${comment.text} <br>
        </c:forEach>
        <br>
        <div class="paging">
            <c:if test="${currentPage != 1}">
                <fmt:message key="page.previous" var="previous"/>
                <td><a href="user_order?page=${currentPage - 1}">${previous}</a></td>
            </c:if>

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
                                <td><a href="user_order?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>

                <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <fmt:message key="page.next" var="next"/>
                <td><a href="user_order?page=${currentPage + 1}">${next}</a></td>
            </c:if>
            <fmt:message key="ref.my_page" var="my_page"/>
            <h4><a href=<c:url value="/user"/>>${my_page}</a></h4>
        </div>
        </c:when>
        <c:otherwise>
        <div class="warning">
            <fmt:message key="ref.my_page" var="my_page1"/>
            <fmt:message key="auth.user_admin.error" var="auth_admin_err"/>
                ${auth_admin_err}
            <c:if test="${user.accessLevel=='ADMIN'}">
                <h4><a href=<c:url value="/admin"/>>${my_page1}</a></h4>
            </c:if>
            <c:if test="${user.accessLevel=='USER'}">
                <h4><a href=<c:url value="/user"/>>${my_page1}</a></h4>
            </c:if>
        </div>
    </div>
</div>
</c:otherwise>
</c:choose>
<jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
