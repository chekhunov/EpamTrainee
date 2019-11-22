<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="naming" var="naming"/>
<fmt:message bundle="${naming}" key="table.label.rooms" var="rooms"/>
<fmt:message bundle="${naming}" key="table.label.roomNumber" var="roomId"/>
<fmt:message bundle="${naming}" key="table.label.type" var="type"/>
<fmt:message bundle="${naming}" key="button.label.add" var="add"/>
<fmt:message bundle="${naming}" key="button.label.edit" var="edit"/>
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
    <script src="${pageContext.request.contextPath}/js/edit.js"></script>
    <script src="${pageContext.request.contextPath}/js/editRoom.js"></script>
</head>
<body>

<div id="editRoom" class="modal">


    <div class="modal-content animate">
        <form action="${pageContext.servletContext.contextPath}/controller?command=saveRoom"
              method="post">
            <input type="hidden" id="roomId" name="roomId"
                   pattern="^([1-9]{1}[0-9]{0,10})$" value="">

            <label for="editRoomNumber"><b>${roomId}</b></label>
            <input type="text" id="editRoomNumber" name="editRoomNumber" value=""
                   pattern="^([A-Za-z1-9]{1}[A-Za-z0-9]{0,9})$" required>

            <label for="editTypeRoom"><b>${type}</b></label>
            <select id="editTypeRoom" name="editTypeRoom" value="" required>
                <option disabled>${type}</option>
                <option value="APARTMENT">${apartment}</option>
                <option value="BUSINESS">${business}</option>
                <option value="DELUXE">${deluxe}</option>
                <option value="DUPLEX">${duplex}</option>
                <option value="FAMILYROOM">${familyRoom}</option>
                <option value="STANDARD">${standard}</option>
                <option value="PRESIDENT">${president}</option>
            </select>
            <div>
                <input class="edtButton" type="submit" value="${edit}"/>
            </div>
        </form>
        <button class="cancelButton" onclick="document.getElementById('editRoom').style.display='none'">${cancel}
        </button>
    </div>
</div>

</div>
</body>
