<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 11.02.2021
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" style="border-collapse: collapse">
    <tr>
        <td>ID:</td>
        <td>Number</td>
        <td>Departure Station</td>
        <td>Departure Time</td>
        <td>Arrival Station</td>
        <td>Arrival Time</td>
        <td>Ticket Price</td>
        <td>Tickets Booking</td>
    </tr>

        <tr>
            <td>${train.id}</td>
            <td>${train.number}</td>
            <td>${train.departStation.name}</td>
            <td>${train.departTime}</td>
            <td>${train.arriveStation.name}</td>
            <td>${train.arriveTime}</td>
            <td>${train.price}</td>
            <td>${date}</td>
        </tr>
</table>

<form:form action="/freeSeats" method="get" modelAttribute="train">
    <input type="hidden" value="${train.id}" name="train">
    <input type="date" value="${departDate}" name="departDate" title="departDate">
    <input type="submit" value= "Search free seats">
    <c:out value="${departDate}"/>
</form:form>

<h2>Choose free seat:</h2>
<c:if test="${errorMesage ne null}">
    <c:out value="${errorMesage}"/></c:if>

<c:if test="${freeSeats ne null}">
    <form:form action="/bookTicket" modelAttribute="newTicket" method="post">
    <c:out value="Ticket price: ${train.price}"/>

        <input type="hidden" name="train" value="${train.id}">
        <input type="hidden" name="departDate" value="${departDate}">
        <%--<input type="hidden" name="trainId" value="${train.id}">--%>
        <%--<input type="hidden" name="departDate" value="${departDate}" >--%>
<form:select path="place">
<c:forEach items="${freeSeats}" var="seat">
<form:option value="${seat}"><c:out value="${seat}"/></</form:option>
</c:forEach>
</form:select>
        <%----%>
    <%--<select name="place">--%>
        <%--<c:forEach items="${freeSeats}" var="seat">--%>
            <%--<option value="${newTicket.place}" name="place" title="${seat}"><c:out value="${seat}"/></option>--%>
        <%--</c:forEach>`--%>
    <%--</select>--%>
        <input type="submit" value="Book ticket">
</form:form>
</c:if>

</body>
</html>