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
            <p>Neverovatne</p>
            <h3 class="heading">Premijerne price sa putovanja</h3>
            <p>Na samo jedan klik od Vas</p>
            <footer><a class="btn" href="#">Dozivite avanturu</a></footer>
          </article>
        </li>
        <li>
          <article>
            <p>Najbolje</p>
            <h3 class="heading">Ponude aranzmana</h3>
            <p>Neka leto 2020. pocne odmah</p>
            <footer>
              <form class="group" method="post" action="#">
                <fieldset>
                  <legend>Sign-Up:</legend>
                  <input type="email" value="" placeholder="Email&hellip;">
                  <button class="fa fa-sign-in" type="submit" title="Submit"><em>Submit</em></button>
                </fieldset>
              </form>
            </footer>
          </article>
        </li>
        <li>
          <article>
            <p>Dozivite</p>
            <h3 class="heading">Svet posle pandemije</h3>
            <p>Turisticki aranzmani na jedan klik od Vas</p>
            <footer><a class="btn inverse" href="#">Klik</a></footer>
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
        <li class="one_third first"><a href="#"><img src="https://www.planetware.com/photos-large/PHI/philippines-luzon-saud-beach.jpg" alt=""></a></li>
        <li class="one_third"><a href="#"><img src="https://www.telegraph.co.uk/content/dam/Travel/Destinations/Africa/Mauritius/Mauritius---Beaches---Tropical-beach.jpg" alt=""></a></li>
        <li class="one_third"><a href="#"><img src="https://www.sattravel.rs/images/smestaj/galerija/sat-travel-cuba%20(6).jpg" alt=""></a></li>
      </ul>
    </figure>
    <p class="center"><a class="btn" href="#">Zapocni avanturu</a></p>
    <!-- ################################################################################################ -->
  </div>
</div>
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="hoc container clear"> 
      <h3>${message}</h3>
    <!-- main body -->
    <!-- ################################################################################################ -->
    <article class="group btmspace-80">
      <div class="two_third first"><img class="borderedbox inspace-10" src="${news[fn:length(news)-1].slike[0].URL}" alt=""></div>
      <div class="one_third">
        <h6 class="heading">${news[fn:length(news)-1].naslov}</h6>
        <ul class="nospace meta">
          <li><i class="fa fa-user"></i> <a href="#">User</a></li>
          <li><i class="fa fa-tag"></i> <a href="#">Tag Name</a></li>
        </ul>
        <p>${news[fn:length(news)-1].kratakOpis}</p>
        <footer class="nospace"><a class="btn" href="<c:url value = "/portal/new/${news[fn:length(news)-1].id}"/>">Cela prica &raquo;</a></footer>
      </div>
    </article>
    <!-- ################################################################################################ -->
    <hr class="btmspace-80">
    <!-- ################################################################################################ -->
    <ul class="nospace group overview">
        <c:forEach var="new1" items="${news}" varStatus="loop">
            <c:if test="${!loop.last}">
      <li class="one_third">
        <article><a href="#"><img src="${new1.slike[0].URL}" alt=""></a>
          <h6 class="heading">${new1.naslov}</h6>
          <ul class="nospace meta">
            <li><i class="fa fa-user"></i> <a href="#">Admin</a></li>
            <li><i class="fa fa-tag"></i> <a href="#">Tag Name</a></li>
          </ul>
          <p>${new1.kratakOpis}</p>
          <footer class="nospace">
              <a class="btn" href="<c:url value = "/portal/new/${new1.id}"/>">Cela prica &raquo;
              </a>
                 <c:if test="${!premium && new1.premium}"><img style="width: 40px; height: 40px; margin-top: 30px;" src="https://store-images.s-microsoft.com/image/apps.3543.13685793528492109.819f1a0a-5c6d-4efb-b2b6-5fd3aed09f5e.dcf3b1f1-69f0-41b1-96d7-56e864885e38?mode=scale&q=90&h=300&w=300"></c:if>
          </footer>
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
