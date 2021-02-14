
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 11.02.2021
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@ taglib uri="http://java.sun.com/JSP/Page" prefix="jsp" %>--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- To declare your language - read more here: https://www.w3.org/International/questions/qa-html-language-declarations -->

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Search train</title>
</head>
<body>
<h2>Search train</h2>

<h3>Search trains from station:</h3>
<form:form action="/user/trainsByDepartStation" method="get" modelAttribute="station">
    <form:select path="id">
    <c:forEach items="${allStations}" var="station">
        <option value="${station.id}"><c:out value="${station.name}"/></option>
    </c:forEach>
    </form:select>
    <input type="submit" value="Search">
</form:form>

<h3>Search trains between stations:</h3>
<form:form action="/user/trainsBetweenStations" method="get" modelAttribute="stations" >
    Departure station:
    <form:select path="departStation">
        <c:forEach items="${allStations}" var="departStation">
            <option value="${departStation.id}"><c:out value="${departStation.name}"/></option>
        </c:forEach>
    </form:select>

    Arrival station:
        <form:select path="arriveStation">
            <c:forEach items="${allStations}" var="arriveStation">
                <option value="${arriveStation.id}"><c:out value="${arriveStation.name}"/></option>
            </c:forEach>
        </form:select>
    <input type="submit" value="Search">
</form:form>

<c:if test="${trains ne null}">
    <h3>Search result:</h3>

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

        <c:forEach items="${trains}" var="train">
            <tr>
                <td>${train.id}</td>
                <td>${train.number}</td>
                <td>${train.departStation.name}</td>
                <td>${train.departTime}</td>
                <td>${train.arriveStation.name}</td>
                <td>${train.arriveTime}</td>
                <td>${train.price}</td>
                <td>
            <form:form action="/trainPage" method="get" modelAttribute="train">
                <input type="hidden" value="${train.id}" name="train">
                    <input type="submit" value="View train info and book ticket!">
            </form:form>
                </td>
            </tr>

        </c:forEach>
    </table>




</c:if>



</body>
</html>
