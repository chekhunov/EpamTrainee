<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="table.label.rooms" var="rooms"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomId"/>
<fmt:message bundle="${naming}" key="table.label.type" var="type"/>
<fmt:message bundle="${naming}" key="button.label.add" var="add"/>
<fmt:message bundle="${naming}" key="table.label.endDate" var="endDate"/>
<fmt:message bundle="${naming}" key="table.label.startDate" var="startDate"/>
<fmt:message bundle="${naming}" key="table.label.enterCost" var="enterCost"/>
<fmt:message bundle="${naming}" key="button.label.cancel" var="cancel"/>
<fmt:message bundle="${naming}" key="table.label.cost" var="cost"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
    <script src="${pageContext.request.contextPath}/js/addRoomPrice.js"></script>
</head>
<body>
<div id="addRoomPrice" class="modal">

    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=addRoomPrice&limit=${requestScope.roomLimit}&roomPage=${requestScope.roomPage}"
              method="post">
            <label for="roomId"><b>${roomId}</b></label>
            <input id="roomIdHidden" name="roomId" type="hidden" value=""
                   pattern="^([1-9]{1}[0-9]{0,10})$">
            <input id="roomId" name="roomId" type="text" disabled value=""
                   pattern="^([1-9]{1}[0-9]{0,10})$">
            <label for="startDate"><b>${startDate}</b></label>
            <input id="startDate" type="date" name="startDate" required>

            <label for="endDate"><b>${endDate}</b></label>
            <input id="endDate" type="date" name="endDate" placeholder="dd" required>
            <label for="cost"><b>${cost}</b></label>
            <input id="cost" type="text" name="cost" placeholder="${enterCost}"
                   pattern="^([1-9]{1}[0-9]{0,8}\.?[0-9]{0,2})$" required>

            <div>
                <input class="prcButton" type="submit" value="${add}"/>
            </div>
        </form>
        <div>
            <button class="cancelButton"
                    onclick="document.getElementById('addRoomPrice').style.display='none'">${cancel}
            </button>
        </div>
    </div>

</div>
</body>
</html>