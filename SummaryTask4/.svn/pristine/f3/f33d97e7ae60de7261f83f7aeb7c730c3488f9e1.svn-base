<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="table.label.rooms" var="rooms"/>
<fmt:message bundle="${naming}" key="table.label.room" var="room"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomId"/>
<fmt:message bundle="${naming}" key="table.label.checkInDate" var="checkInDate"/>
<fmt:message bundle="${naming}" key="table.label.checkOutDate" var="checkOutDate"/>
<fmt:message bundle="${naming}" key="table.label.typeRoom" var="typeRoom"/>
<fmt:message bundle="${naming}" key="table.label.type" var="type"/>
<fmt:message bundle="${naming}" key="button.label.add" var="add"/>
<fmt:message bundle="${naming}" key="button.label.process" var="process"/>
<fmt:message bundle="${naming}" key="table.label.name" var="name"/>
<fmt:message bundle="${naming}" key="table.label.chooseRoomType" var="chooseRoomType"/>
<fmt:message bundle="${naming}" key="button.label.cancel" var="cancel"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
    <script src="${pageContext.request.contextPath}/js/processOrder.js"></script>
</head>
<body>
<div id="processOrder" class="modal">

    <div class="modal-content animate">
        <form action="${pageContext.request.contextPath}/controller?command=processOrder" method="post">
            <%--<jsp:useBean id="currentInProcessOrder" scope="request" type="java.util.List"/>--%>
            <%--<c:forEach items="${currentInProcessOrder}" var="order">--%>
                <%--<label hidden name="id">${order.id}</label>--%>
                <label for="name"><b>${name}</b></label>
                <input type="text" id="name" name="uname" value="${order.firstName} ${order.lastName}"
                       readonly>

                <label><b>${checkInDate}</b></label>
                <input type="text" name="checkInDate" value="${order.checkInDate}"
                       readonly>

                <label><b>${checkOutDate}</b></label>
                <input type="text" name="checkOutDate" value="${order.checkOutDate}" readonly>

                <label for="typeRoom"><b>${typeRoom}</b></label>
                <input id="typeRoom" type="text" name="typeRoom" value="${order.type}" readonly>

                <label for="chooseRoom"><b>${chooseRoomType}</b></label>
                <div>
                    <select class="chooseRoom" id="chooseRoom" name="room">
                        <option disabled>${room}</option>
                        <option>${room}</option>
                    </select>
                    <button class="prcButton" type="submit">${process}</button>
                </div>
            <%--</c:forEach>--%>
        </form>
        <div>
            <button class="cancelButton" type="submit"
                    onclick="document.getElementById('processOrder').style.display='none'">${cancel}
            </button>
        </div>
    </div>

</div>
</body>
</html>
