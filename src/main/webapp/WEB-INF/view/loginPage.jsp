<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 24.01.2021
  Time: 22:33
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
            <li><a href="<c:url value="/index"/>"><spring:message code="menu.home" text="default"/></a></li>
            <li><a href="<c:url value="/timetable"/>"><spring:message code="menu.search" text="default"/></a></li>
            <c:choose>
                <c:when test="${currentUser.id == 0}">
                    <li><a href="<c:url value="/loginPage"/>"><spring:message code="menu.login" text="default"/></a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="<c:url value="/user/${currentUser.id}"/>"><spring:message code="menu.myprofile" text="default"/></a></li>
                    <li><a href="<c:url value="/tickets/${currentUser.id}"/>"><spring:message code="menu.mytickets" text="default"/></a></li>
                    <%--<li><a href="<c:url value="/booking"/>">Book tickets</a></li>--%>
                    <li><a href="<c:url value="/logout"/>"><spring:message code="menu.logout" text="default"/></a></li>
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

<%--<h4>Input your e-mail and password:</h4><br>--%>
              <b><spring:message code="login.inputMessage" text="default"/></b>
<form:form method="post" action="/login" modelAttribute="logUserDTO">

    <label><spring:message code="login.email" text="default"/></label><form:input path="email" />
    <label><spring:message code="login.password" text="default"/></label><form:password path="password" />

    <input type="submit" value="Log in!"/>


    <%--<h3>or <a href="/register">register</a></h3>--%>
</form:form>
                <br>
<form:form method="get" action="/register" modelAttribute="currentUser"><input type="submit" value="<spring:message code="login.orRegister" text="default"/>"/>
                </form:form>

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
                <h4>Your profile info</h4>
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
