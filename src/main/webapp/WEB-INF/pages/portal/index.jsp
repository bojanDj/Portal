<%-- 
    Document   : index
    Created on : Apr 26, 2021, 9:34:41 PM
    Author     : Bojan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="layout/styles/layout.css" rel="stylesheet" type="text/css" media="all">
    </head>
    <body id="top">
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- Top Background Image Wrapper -->
<div class="bgded overlay light" style="background-image:url('https://infrastructuremagazine.com.au/wp-content/uploads/2019/11/shutterstock_448591534-e1573696537345.jpg');"> 
  <!-- ################################################################################################ -->
  
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <!-- ################################################################################################ -->
  <div id="pageintro" class="hoc clear"> 
    <!-- ################################################################################################ -->
    <div class="flexslider basicslider">
      <ul class="slides">
        <li>
            <article>
            <p>Condimentum</p>
            <h3 class="heading">Ligula at fermentum</h3>
            <p>Sodales sapien donec porttitor dolor nec elit</p>
            <footer><a class="btn" href="#">Luctus suscipit in</a></footer>
          </article>
        </li>
        <li>
          <article>
            <p>Laoreet</p>
            <h3 class="heading">Iaculis interdum</h3>
            <p>Nulla scelerisque posuere convallis</p>
            <footer>
              <form class="group" method="post" action="#">
                <fieldset>
                  <legend>Sign-Up:</legend>
                  <input type="email" value="" placeholder="Email Here&hellip;">
                  <button class="fa fa-sign-in" type="submit" title="Submit"><em>Submit</em></button>
                </fieldset>
              </form>
            </footer>
          </article>
        </li>
        <li>
          <article>
            <p>Phasellus</p>
            <h3 class="heading">Bibendum blandit</h3>
            <p>Lacus non tincidunt class aptent taciti sociosqu</p>
            <footer><a class="btn inverse" href="#">Litora torquent</a></footer>
          </article>
        </li>
      </ul>
    </div>
    <!-- ################################################################################################ -->
  </div>
  <!-- ################################################################################################ -->
</div>
<!-- End Top Background Image Wrapper -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper">
  <div class="hoc clear"> 
    <!-- ################################################################################################ -->
    <figure id="introblocks">
      <ul class="nospace group">
        <li class="one_third first"><a href="#"><img src="https://www.jungletribe.rs/files/images/DALEKE%20DESTINACIJE/Azija/Maldivi/Maldivi%20cover%20photo%20i%20baner/maldives-1993704_1280.jpg" alt=""></a></li>
        <li class="one_third"><a href="#"><img src="https://soleazur.rs/uploads/0000/1/2021/02/25/kuba-kategorija.jpg" alt=""></a></li>
        <li class="one_third"><a href="#"><img src="https://www.sattravel.rs/images/smestaj/galerija/sat-travel-cuba%20(6).jpg" alt=""></a></li>
      </ul>
    </figure>
    <p class="center"><a class="btn" href="#">Per conubia nostra</a></p>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
    <!-- main body -->
    <!-- ################################################################################################ -->
    <article class="group btmspace-80">
      <div class="two_third first"><img class="borderedbox inspace-10" src="${news[fn:length(news)-1].url}" alt=""></div>
      <div class="one_third">
        <h6 class="heading">${news[fn:length(news)-1].naslov}</h6>
        <ul class="nospace meta">
          <li><i class="fa fa-user"></i> <a href="#">User</a></li>
          <li><i class="fa fa-tag"></i> <a href="#">Tag Name</a></li>
        </ul>
        <p>${news[fn:length(news)-1].kratakOpis}</p>
        <footer class="nospace"><a class="btn" href="<c:url value = "/portal/new/${news[fn:length(news)-1].id}"/>">Full Story &raquo;</a></footer>
      </div>
    </article>
    <!-- ################################################################################################ -->
    <hr class="btmspace-80">
    <!-- ################################################################################################ -->
    <ul class="nospace group overview">
        <c:forEach var="new1" items="${news}" varStatus="loop">
            <c:if test="${!loop.last}">
      <li class="one_third">
        <article><a href="#"><img src="${new1.url}" alt=""></a>
          <h6 class="heading">${new1.naslov}</h6>
          <ul class="nospace meta">
            <li><i class="fa fa-user"></i> <a href="#">Admin</a></li>
            <li><i class="fa fa-tag"></i> <a href="#">Tag Name</a></li>
          </ul>
          <p>${new1.kratakOpis}</p>
          <footer class="nospace"><a class="btn" href="<c:url value = "/portal/new/${new1.id}"/>">Full Story &raquo;</a></footer>
        </article>
      </li>
      </c:if>
    </c:forEach>
    </ul>
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