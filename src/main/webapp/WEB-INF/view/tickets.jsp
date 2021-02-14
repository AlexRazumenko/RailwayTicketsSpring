<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 09.02.2021
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tickets booking page</title>
</head>
<body>

<%--<h2>Tickets booking page</h2>--%>
<c:if test="${allTicketsByUser ne null}">
    <h2>My tickets</h2>

    <table border="1" style="border-collapse: collapse">
        <c:forEach items="${allTicketsByUser}" var="ticket">
            <tr>
                <td>
                    <c:out value="ID: ${ticket.id} Train ${ticket.train.number} Date: ${ticket.departDate}"/><br>
                    <c:out value="Seat: ${ticket.place} Price: ${ticket.train.price}, "/>
                    <c:out value="Departure: ${ticket.train.departStation.name} ${ticket.train.departTime}, "/>
                    <c:out value="Arrival: ${ticket.train.arriveStation.name} ${ticket.train.arriveTime} "/><br>
                    <c:out value="Passenger ${ticket.user.firstName} ${ticket.user.lastName}
                                (email: ${ticket.user.email})"/><br>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>


</body>
</html>
