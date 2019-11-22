<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="username" uri="/WEB-INF/tld/usernameDescriptor.tld" %>
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
    <fmt:message key="admin.order_page.heading" var="order_page_admin_head"/>
    <title>${order_page_admin_head}</title>
</head>
<body>

<div class="main-content">
    <div class="head-content">
        <tags:language curr_lang="${locale}" curr_uri="${pageContext.request.requestURI}"/>
        <tags:logout userLogin="${user.login}" userName="${user.name}" curr_lang="${locale}"/>
        ${order_page_admin_head}
    </div>

    <fmt:message key="user.order_page.order_details" var="order_details"/>
    <fmt:message key="user.order_page.order_id" var="order_id"/>
    <fmt:message key="admin.order_page.user_id" var="user_id"/>
    <fmt:message key="user.order_page.req_places" var="req_places"/>
    <fmt:message key="user.order_page.req_class" var="req_class"/>
    <fmt:message key="user.order_page.dates" var="dates"/>
    <fmt:message key="user.order_page.dates.1" var="dates_from"/>
    <fmt:message key="user.order_page.dates.2" var="dates_to"/>
    <fmt:message key="user.order_page.status" var="status"/>
    <c:choose>
    <c:when test="${not empty isAdmin}">
    <div class="form">
            ${order_details}: <br>
            ${order_id}: ${order.orderId} <br>
            ${user_id}: ${order.userId} <br>
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
            ${dates}: ${dates_from} ${order.dateIn} ${dates_to} ${order.dateOut} <br>
            ${status}: <tags:status order_status="${order.status}" curr_lang="${locale}"/>
        <br>
        <c:choose>
            <c:when test="${order.status == 'REQUESTED'}">
                <fmt:message key="admin.order_page.requested.find_room" var="find"/>
                <form action="search_room" method="post">
                    <input type="submit" value="${find}">
                    <input type="hidden" name="actionName" value="find_room">
                </form>
            </c:when>
            <c:when test="${order.status == 'IN_DISCUSSION'}">
                <fmt:message key="admin.order_page.in_discussion.find_an_room" var="find_another"/>
                <fmt:message key="admin.order_page.offered_apartment_id" var="offered_apt"/>
                ${offered_apt}: ${order.orderedAptId}
                <form action="search_room" method="post">
                    <input type="submit" value="${find_another}">
                    <input type="hidden" name="actionName" value="find_room">
                </form>
            </c:when>
            <c:when test="${order.status == 'APPROVED'}">
                <fmt:message key="admin.order_page.apartment_id" var="apt_id"/>
                <fmt:message key="admin.order_page.approved.bill" var="bill_info"/>
                ${apt_id}: ${order.orderedAptId}
                <br>
                ${bill_info}
            </c:when>

            <c:when test="${order.status == 'PAID'}">
                <fmt:message key="admin.order.addit_ino" var="add_info"/>
                ${add_info}: ${order.additionalInfo}
                <br>
                <fmt:message key="order.admin.paid.add_info" var="add_order_info"/>
                <fmt:message key="admin.order_page.paid_info" var="paid_info"/>
                ${paid_info}
                <br>
                <br>
                <form action="admin_order" method="post">
                    <textarea name="additional_info" rows="4" cols="50"></textarea>
                    <br>
                    <input type="hidden" name="actionName" value="add_info">
                    <input type="submit" value="${add_order_info}">
                </form>
            </c:when>
            <c:when test="${order.status == 'REJECTED'}">
                <fmt:message key="admin.order_page.rejected_info" var="rejected_info"/>
                ${rejected_info}
            </c:when>

        </c:choose>
        <c:if test="${not empty error}">
            <fmt:message key="orders.error" var="error_msg"/>
            ${error_msg}
        </c:if>
        <c:if test="${order.status != 'REJECTED'}">
            <fmt:message key="user.order_page.reject" var="reject_order"/>
            <form action="reject_warning" method="post">
                <input type="hidden" name="order_id" value="${order.orderId}">
                <input type="submit" value="${reject_order}">
                <input type="hidden" name="actionName" value="reject">
            </form>
            <br>
        </c:if>
        <fmt:message key="ref.my_page" var="my_page"/>


        <form action="admin_order" method="post">
            <fmt:message key="user.order_page.leave_comment_info" var="comment_leave"/>
            <fmt:message key="user.order_page.leave_comment" var="comment_submit"/>
                <%--${comment_leave}: <br>--%>
            <textarea name="comment" rows="4" cols="50"></textarea>
            <input type="hidden" name="actionName" value="send_comment">
            <br>
            <input type="submit" value="${comment_submit}">
        </form>
        <br>

        <fmt:message key="user.order_page.comments" var="comments"/>
                <div class="comments">
            ${comments}:
                </div>
        <c:choose>
        <c:when test="${not empty no_comments}">
            <fmt:message key="user.order_page.no_comments" var="no_comm"/>
            ${no_comm}
        </c:when>
        <c:otherwise>
        <c:forEach items="${comment_list}" var="comment">
            <username:usernameTagHandler userId="${comment.userId}" lang="${locale}"/>: ${comment.text} <br>
        </c:forEach>
    </div>
    <div class="paging">
        <c:if test="${currentPage != 1}">
            <fmt:message key="page.previous" var="previous"/>
            <td><a href="admin_order?page=${currentPage - 1}">${previous}</a></td>
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
                            <td><a href="admin_order?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

            <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages}">
            <fmt:message key="page.next" var="next"/>
            <td><a href="admin_order?page=${currentPage + 1}">${next}</a></td>
        </c:if>
    </div>

    </c:otherwise>
    </c:choose>
    <div class="paging">
        <h4><a href=<c:url value="/admin"/>>${my_page}</a></h4>
    </div>
    </c:when>
    <c:otherwise>
        <fmt:message key="ref.my_page" var="my_page1"/>
        <fmt:message key="auth.user_admin.error" var="auth_admin_err"/>
        ${auth_admin_err}
    <h4><a class="paging" href=<c:url value="/user"/>>${my_page1}</a></h4>
    </c:otherwise>
    </c:choose>
    <jsp:include page="/WEB-INF/fragments/header/footer.jsp"/>
</body>
</html>
