<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.mySlides {display:none;}
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
      <!-- ################################################################################################ -->
      <h1>${story.naslov}</h1>
      <img class="imgr borderedbox inspace-5" src="${story.slike[1].URL}" alt="">
      <p>${story.kratakOpis}</p>
      <img class="imgl borderedbox inspace-5" src="${story.slike[0].URL}" alt="">
      <p>${story.tekst}</p>   
      <h2 class="w3-center">Galerija</h2>

<div class="w3-content w3-display-container">
  <img class="mySlides" src="${story.slike[2].URL}" style="width:100%">
  <img class="mySlides" src="${story.slike[1].URL}" style="width:100%">
  <img class="mySlides" src="${story.slike[0].URL}" style="width:100%">

  <button class="w3-button w3-black w3-display-left" onclick="plusDivs(-1)">&#10094;</button>
  <button class="w3-button w3-black w3-display-right" onclick="plusDivs(1)">&#10095;</button>
</div>
      <div id="comments">
        <h2>Komentari</h2>
        <ul>
          <c:forEach var="kom" items="${story.komentari}">
              <c:if test = "${kom.prihvacen == 1}">
          <li>
            <article>
              <header>
                <figure class="avatar"><img src="../images/demo/avatar.png" alt=""></figure>
                <address>
                By <a href="#">${kom.username}</a>
                </address>
                <time datetime="${kom.vreme}">${kom.vreme}</time>
              </header>
              <div class="comcont">
                  <p>${kom.komentar}</p>
              </div>
            </article>
          </li>
          </c:if>
          </c:forEach>
        </ul>
        <h2>Napisite komentar</h2>
        <form:form action="${pageContext.request.contextPath}/portal/new/story${story.id}/addComment" method="POST" modelAttribute="comment">
            <c:if test="${empty user}">
            <div class="one_half first">
            <label for="name">Ime <span>*</span></label>
            <form:input type="text" name="name" id="name" value="${user}" size="22" path="username"></form:input>
          </div>
          </c:if>
            <c:if test="${empty user}">
          <div class="one_half">
            <label for="email">Mail <span>*</span></label>
            <input type="email" name="email" id="email" value="" size="22" required>
          </div>
        </c:if>
          <div class="block clear">
            <label for="comment">Komentar</label>
            <form:textarea name="comment" id="comment" cols="25" rows="10" path="komentar"></form:textarea>
          </div>
          <div>
                <button class="btn" type="submit" name="submit" href="/">Dodaj komentar</button>
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
<script>
var slideIndex = 1;
showDivs(slideIndex);

function plusDivs(n) {
  showDivs(slideIndex += n);
}

function showDivs(n) {
  var i;
  var x = document.getElementsByClassName("mySlides");
  if (n > x.length) {slideIndex = 1}
  if (n < 1) {slideIndex = x.length}
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  x[slideIndex-1].style.display = "block";  
}
</script>
</body>
</html>