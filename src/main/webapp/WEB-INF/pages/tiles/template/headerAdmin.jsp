<%-- 
    Document   : header
    Created on : Apr 26, 2021, 10:24:50 PM
    Author     : Bojan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="wrapper row1">
    <header id="header" class="hoc clear"> 
      <!-- ################################################################################################ -->
      <div id="logo" class="fl_left">
        <h1><a href="<c:url value = "/admin/home"/>">PUT OKO SVETA</a></h1>
      </div>
      <nav id="mainav" class="fl_right">
        <ul class="clear">
          <li class="active"><a href="<c:url value = "/admin/home"/>">Pocetna</a></li>
<!--          <li><a class="drop" href="#">Pages</a>
            <ul>
              <li><a href="pages/gallery.html">Gallery</a></li>
              <li><a href="pages/full-width.html">Full Width</a></li>
              <li><a href="pages/sidebar-left.jsp">Sidebar Left</a></li>
              <li><a href="<c:url value = "/portal/sidebar"/>">Sidebar Left</a></li>
              <li><a href="pages/sidebar-right.html">Sidebar Right</a></li>
              <li><a href="pages/basic-grid.html">Basic Grid</a></li>
              <li><a href="<c:url value = "/portal/login"/>">Uloguj se</a></li>
            </ul>
          </li>-->
<!--          <li><a class="drop" href="#">Dropdown</a>
            <ul>
              <li><a href="#">Level 2</a></li>
              <li><a class="drop" href="#">Level 2 + Drop</a>
                <ul>
                  <li><a href="#">Level 3</a></li>
                  <li><a href="#">Level 3</a></li>
                  <li><a href="#">Level 3</a></li>
                </ul>
              </li>
              <li><a href="#">Level 2</a></li>
            </ul>
          </li>-->
<li><a style="text-decoration: none;" href="<c:url value = "/admin/addStory"/>">Dodaj pricu!</a></li>
          <li><a style="text-decoration: none;" href="<c:url value = "/admin/comments"/>">Komentari</a></li>
          <c:if test="${not empty user}"><li><a style="text-decoration: none;" href="<c:url value = "/logout"/>">${user}, izloguj se</a></li></c:if>
<c:if test="${empty user}"><li><a style="text-decoration: none;" href="<c:url value = "/login"/>">Uloguj se</a></li></c:if> 
        </ul>
      </nav>
      <!-- ################################################################################################ -->
    </header>
  </div>
