<%-- 
    Document   : home
    Created on : May 18, 2021, 1:11:29 AM
    Author     : Bojan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
body {
  font-family: Arial;
  font-size: 17px;
  padding: 8px;
}

* {
  box-sizing: border-box;
}

.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-25,
.col-50,
.col-75 {
  padding: 0 16px;
}

.container {
  background-color: #f2f2f2;
  padding: 5px 20px 15px 20px;
  border: 1px solid lightgrey;
  border-radius: 3px;
}

input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

label {
  margin-bottom: 10px;
  display: block;
}

.icon-container {
  margin-bottom: 20px;
  padding: 7px 0;
  font-size: 24px;
}

.btn {
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

a {
  color: #2196F3;
}

hr {
  border: 1px solid lightgrey;
}

span.price {
  float: right;
  color: grey;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
  .col-25 {
    margin-bottom: 20px;
  }
}

    </style>
</head>
<body>
    <div class="row" style="margin-top: 2%;">
        <h3>${message}</h3>
    <div class="col-75">
        <div class="container">
            <form:form method="post"  action="/Portal/portal/pay" modelAttribute="order">
                <div class="col-50">
                    <h3>Uplata za premium nalog.</h3>
                    <label>Kartice koje prihvatamo:</label>
                    <div class="icon-container">
                        <i class="fa fa-cc-visa" style="color:navy;"></i>
                        <i class="fa fa-cc-amex" style="color:blue;"></i>
                        <i class="fa fa-cc-mastercard" style="color:red;"></i>
                        <i class="fa fa-cc-discover" style="color:orange;"></i>
                    </div>
                    <label for="price">Iznos: 10 USD</label>
                    <%--<form:input type="text" id="price" disabled="disabled" name="price" value="10" path="price" />--%>
                    <!--<label for="currency">Currency</label>-->
                    <%--<form:input type="text" id="currency" name="currency" placeholder="Enter Currency" path="currency"/>--%>
                    <!--<label for="method">Payment Method</label>-->
                    <%--<form:input type="text" id="method" name="method" placeholder="Payment Method" path="method"/>--%>
                    <!--<label for="intent">Intent</label>-->
                    <%--<form:input type="text" id="intent" name="intent" value="sale" path="intent"/>--%>
                    <!--<label for="description">Payment Description</label>-->
                    <%--<form:input type="text" id="description" name="description" placeholder="Payment Description" path="description"/>--%>

                </div>

                <input type="submit" value="Bicete preusmereni na PayPal portal" class="btn">
            </form:form>
        </div>
    </div>
</div>

</body>
</html>

