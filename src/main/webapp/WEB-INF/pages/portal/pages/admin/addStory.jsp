<%-- 
    Document   : addStory
    Created on : May 11, 2021, 7:48:07 PM
    Author     : Bojan
--%>

<%@page import="portal.dto.Story"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<!--
Template Name: Skaxis
Author: <a href="https://www.os-templates.com/">OS Templates</a>
Author URI: https://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: https://www.os-templates.com/template-terms
-->
<html lang="">
<!-- To declare your language - read more here: https://www.w3.org/International/questions/qa-html-language-declarations -->
<head>
<title>Skaxis | Pages | Sidebar Left</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link href="../layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
<style>
    #wrapper {
        margin-top: 5%;
    overflow: hidden; /* will contain if #first is longer than #second */
}
#first {
    width: 5%;
    float:left; /* add this */
}
#second {
    overflow: hidden; /* if you don't want #second to wrap below #first */
    margin-left: 10%;
}
</style>
</head>
<body id="top">
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->

<!-- End Top Background Image Wrapper -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <div class="sidebar one_quarter first"> 
      <!-- ################################################################################################ -->
      <h6>Navigacija</h6>
      <nav class="sdb_holder">
        <ul>
            <c:forEach var = "country" items="${countries}">
                <li><a href="#">${country}</a>
                    <ul>
                <c:forEach var = "story" items="${price}">
                    <c:if test="${story.drzava == country}">
                        <li><a href="${pageContext.request.contextPath}/portal/new/${story.id}">${story.naslov}</a></li>
                    </c:if>
                </c:forEach>
                        </ul>
                </li>
            </c:forEach>
        </ul>
      </nav>
      <div class="sdb_holder">
        <h6>Kontakt</h6>
        <address>
        Put oko sveta<br>
        Jove Ilica 47<br>
        Vojvode Stepe 209<br>
        Beograd<br>
        11000<br>
        <br>
        Tel: +381 11 123 45 68<br>
        Email: <a href="#">contact@posveta.com</a>
        </address>
      </div>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- ################################################################################################ -->
    <div class="content three_quarter">   
         
      <div id="comments">
          <h1>${message}</h1>
        <h2>Unesite novu uzbudljivu pricu!</h2>
        <form action="UploadServlet" method="post" enctype="multipart/form-data">
            <input type="file" id="file" name="file1" accept="image/*"  multiple="muliple" required/><br>
            <button type="submit" class="btn">Ucitaj slike</button>
           <br><br>
        </form>
        <form:form action="${pageContext.request.contextPath}/admin/addStory" method="POST" modelAttribute="story">
          <div class="one_third first">
            <label for="name">Naslov <span>*</span></label>
            <form:input type="text" name="naslov" id="name" value="" size="22" path="naslov"></form:input>
          </div>
          <div class="one_third">
            <label for="email">Kratak opis <span>*</span></label>
          <form:input type="text" name="kratakOpis" id="email" value="" size="100" path="kratakOpis"></form:input>
          </div>
                    <div id="wrapper" class="one_third">
            <div id="first"><form:radiobutton path="premium" value="true"></form:radiobutton></div>
            <div id="second">Premium prica?</div>
          </div>
          <c:forEach var="imge" items="${list}" varStatus="loop">
          <div class="one_third" style="display: none;">
            <label for="url">Url</label>
          <form:input type="hidden" name="url" id="url" value="${list[loop.index].URL}" size="1000" path="slike[${loop.index}].URL"></form:input>
          </div>
          </c:forEach>
          <div class="block clear">
            <label for="comment">Prica</label>
            <form:textarea name="comment" id="comment" cols="25" rows="10" path="tekst"></form:textarea>
          </div>
            <form:select name="country" id="country" path="drzava" style="margin-bottom:2%;">
        <c:forEach items="${locale}" var="country">
            <option value="${country}">${country}</option>
        </c:forEach>
        </form:select>
          <div>
              <button type="submit" name="submit" class="btn" href="/">Dodaj pricu</button>
          </div>
        </form:form>
      </div>
      <!-- ################################################################################################ -->
    </div>
    <!-- ################################################################################################ -->
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
</body>
</html>