<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 25.01.2021
  Time: 7:18
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://java.sun.com/JSP/Page" prefix="jsp" %>--%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- To declare your language - read more here: https://www.w3.org/International/questions/qa-html-language-declarations -->

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Typo Today</title>
    <meta http-equiv="Content-Type" content="application/json; charset=utf-8"/>
    <%--<link rel="stylesheet" href="css/stylesheet.css" type="text/css"/>--%>
    <style type="text/css">
        <%@ include file="../css/stylesheet.css"%>
    </style>
</head>
<body>
<!--HEADER------------------------------------------------------------>
<div id="header">

    <div id="logo">
        <h1><a href="#">Railway Tickets Booking</a></h1>
        <p><span class="slogan">login page</span></p>
    </div>

    <!--HEADER MENU------------------------------------------------------->

    <div id="nav-bar" align="center">
        <ul id="nav1">
            <li><a href="<c:url value="/index"/>">Home</a></li>
            <li><a href="<c:url value="/timetable"/>">Search trains</a></li>
            <c:choose>
                <c:when test="${currentUser.id == 0}">
                    <li><a href="<c:url value="/loginPage"/>">Log in</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/user/${currentUser.id}"/>">My profile</a></li>
                    <li><a href="<c:url value="/tickets/${currentUser.id}"/>">My tickets</a></li>
                    <%--<li><a href="<c:url value="/booking"/>">Book tickets</a></li>--%>
                    <li><a href="<c:url value="/logout"/>">Log out</a></li>
                </c:otherwise>
            </c:choose>
            <li><a href="#">Contact</a></li>
        </ul>
    </div>
</div>
<!--HEADER END-->
<!--CONTENT-->
<div id="content">
    <!--MAIN-CONTENT-->
    <div id="main-content">
        <!--CONTENT 1 END-->
        <div id="content-1">
            <div class="featured">

                <h2>All Users:</h2>

                <p></p>
                <font color="#ffebcd"> Delete user:</font>
                <c:if test="${allUsers ne null}">
                    <form:form method="post" action="/admin/deleteUser" modelAttribute="deletedUser">
                        <form:select path="id">
                            <c:forEach items="${allUsers.users}" var="user">
                                <form:option value="${user.id}" label="${user.email}: ${user.firstName} ${user.lastName}"/>
                            </c:forEach>
                        </form:select>
                        <input type="submit" value="Delete"/>
                    </form:form>
                    <br>

                </c:if>

                <%--<c:if test="${currentUser.role}"--%>
                <table border="1" style="border-collapse: collapse; color: #181818; font-size: medium">
                    <tr>
                        <td>ID</td>
                        <td>E-mail</td>
                        <td>First Name</td>
                        <td>Last Name</td>
                        <td>Role</td>
                        <%--<td>Tickets</td>--%>
                        <td>User page</td>
                        <td>Set rights</td>
                    </tr>

                    <c:forEach items="${allUsers.users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.email}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role}
                            </td>
                                <%--<td>Tickets</td>--%>
                            <td><a href="<c:url value="/user/${user.id}"/>">View page</a><br></td>
                            <td><a href="<c:url value="/adminRights/${user.id}"/>">Set admin role</a><br>
                                <a href="<c:url value="/userRights/${user.id}"/>">Set user role</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>


        </div>
        <!--SIDEBAR END-->
    </div>
    <!--MAIN CONTENT END-->
    <!--FOOTER-->
    <div id="footer">
        <div id="footer-content">
            <p id="footer-text"> Copyright &copy; 2009 <a href="#">Name Here</a> - All Rights Reserved.<br/>
                Designed by <a href="http://population-2.com/">Population2</a></p>
        </div>
    </div>
    <!--FOOTER END-->
</div>
<!--CONTENT END-->
<!--CUFON-->
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/Gentium_Basic.font.js"></script>
<script type="text/javascript" src="js/Bambi_Bold_400.font.js"></script>
<script type="text/javascript">
    Cufon.replace('h1', {fontFamily: 'Bambi Bold'});
    Cufon.replace('h2', {fontFamily: 'Gentium Basic'});
    Cufon.replace('h3', {fontFamily: 'Gentium Basic'});
</script>
<script type="text/javascript"> Cufon.now(); </script>
<!--CUFON END-->
<div align=center>This template downloaded form <a href='http://all-free-download.com/free-website-templates/'>free
    website templates</a></div>
</body>
</html>
