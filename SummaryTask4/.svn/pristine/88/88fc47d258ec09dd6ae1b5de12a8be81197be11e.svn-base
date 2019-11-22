<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="table.label.rooms" var="rooms"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomId"/>
<fmt:message bundle="${naming}" key="table.label.type" var="type"/>
<fmt:message bundle="${naming}" key="button.label.add" var="add"/>
<fmt:message bundle="${naming}" key="button.label.cancel" var="cancel"/>
<fmt:message bundle="${naming}" key="room.label.apartment" var="apartment"/>
<fmt:message bundle="${naming}" key="room.label.business" var="business"/>
<fmt:message bundle="${naming}" key="room.label.deluxe" var="deluxe"/>
<fmt:message bundle="${naming}" key="room.label.duplex" var="duplex"/>
<fmt:message bundle="${naming}" key="room.label.familyRoom" var="familyRoom"/>
<fmt:message bundle="${naming}" key="room.label.standard" var="standard"/>
<fmt:message bundle="${naming}" key="room.label.president" var="president"/>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/modalStyle.css">
    <script src="${pageContext.request.contextPath}/js/addRoom.js"></script>
</head>
<body>
<div id="addRoom" class="modal">

    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=saveRoom" method="post">
            <label for="addRoomNumber"><b>${roomId}</b></label>
            <input type="text" id="addRoomNumber" name="addRoomNumber"
                   pattern="^([A-Za-z1-9]{1}[A-Za-z0-9]{0,9})$" required>
            <label for="addTypeRoom"><b>${type}</b></label>
            <select id="addTypeRoom" name="addTypeRoom" required>
                <option selected disabled>${type}</option>
                <option value="Apartment">${apartment}</option>
                <option value="Business">${business}</option>
                <option value="Deluxe">${deluxe}</option>
                <option value="Duplex">${duplex}</option>
                <option value="FamilyRoom">${familyRoom}</option>
                <option value="Standard">${standard}</option>
                <option value="President">${president}</option>
            </select>
            <div>
                <input class="prcButton" type="submit" value="${add}"/>
            </div>
        </form>
        <div>
            <button class="cancelButton" onclick="document.getElementById('addRoom').style.display='none'">${cancel}
            </button>
        </div>
    </div>
</div>

<%--<jsp:include page="/WEB-INF/fragments/room/addRoomNotify.jsp"/>--%>
</body>
</html>