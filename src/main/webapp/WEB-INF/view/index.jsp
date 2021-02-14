<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 24.01.2021
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>

<!--
Template Name: Wetwest
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
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
        <p><span class="slogan">index page</span></p>
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
                    <li><a href="<c:url value="/tickets"/>">My tickets</a></li>
                    <%--<li><a href="<c:url value="/booking"/>">Book tickets</a></li>--%>
                    <li><a href="<c:url value="/logout"/>">Log out</a></li>
                </c:otherwise>
            </c:choose>
<%--<sec:authorize access="hasRole('ROLE_ADMIN')" url="/admin/adminPage" var="Admin page" >--%>
            <%--<c:set var="adminRole" scope="session" value="ROLE_ADMIN"/>--%>
    <%--<c:if test="${currentUser.role.name() == adminRole">--%>
            <%--<li><a href="<c:url value="/admin/adminPage"/>">Admin page</a></li>--%>
    <%--</c:if>--%>
<%--</sec:authorize>--%>
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
                <%--<h3><a href="#">Keeping Photography In Perspective When Traveling</a></h3>--%>

                <h3 align="center"> <c:out value="Welcome, ${currentUser.firstName} ${currentUser.lastName}!"/></h3>
                <%--<img class="featured-img" src="img/featured-img.png" alt=""/><br><br><br>--%>

                    <%--<p>Here you can find trains timetables to plan you trip. Please log in to book a ticket.</p>--%>

                    <c:set var="adminRole" scope="session" value="ROLE_ADMIN"/>
                    <c:if test="${currentUser.role.name() == adminRole}">
                        <p>
                            You logged as admin. Click on link to access <a href="<c:url value="/admin/adminPage"/>">Admin page</a><br>
                        </p>
                    </c:if>
                    <c:if test="${currentUser.role.name() ne adminRole}">
                        <p>Here you can find trains timetables to plan you trip. Please log in to book a ticket.</p>
                    </c:if>

                    <h3 align="center"> <c:out value="General information"/></h3>
                    <div style="text-align: justify">

                    <p>Ukraine has a developed railway network, its total working mileage amounts to over 22 000 kilometers.
                        Two third of Ukrainian railway lines are heavy worked, equipped with modern management facilities, dispatcher's centralization and automatic block system.</p>
                    <p> Ukrainian railway directly borders and cooperates with railways of Russia, Byelorussia, Moldova, Poland, Romania, Slovakia, Hungary and ensures
                        the work with 40 international railway cross-walks, and also serves 18 Ukrainian seaports of the Black Sea and Sea of Azov basin.</p>
                    <p> The administration of public railway transport is The State Administration of Railway Transport of Ukraine "Ukrzaliznytsia", which was established in december 1991.
                        The management sphere of Ukrzaliznytsia covers the railways of Donetsk, Lviv, Odessa, Pivdenna (Southern), Pivdenno-Zakhidna (Southwestern) and Pridniprovska Railways,
                        and also other enterprises and organizations of integrated industrial-engineering complex, that enables freight and passengers transportation.</p>
                    <p>Ukrzaliznytsia accomplishes centralized management of the transportation process in inland and interstate communication regulates railway industrial and economic activity.</p>
                    </div>


            </div>
        </div>
        <!--CONTENT 1 END-->
        <!--SIDEBAR-->
        <div id="sidebar">
                <c:if test="${currentUser.id == 0}">
                    <h4>You logged as guest</h4>
            <p class="about-me"> Please log in! </p>
                </c:if>
                <c:if test="${currentUser.id ne 0}">
                    <h4><c:out value="${mainmessage}"/> </h4>
                    <p class="about-me">

                    </p>
                </c:if>
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
