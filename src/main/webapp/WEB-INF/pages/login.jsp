<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profesore dobrososli</title>
</head>
<style>
    .container {
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto; }
  @media (min-width: 576px) {
    .container {
      max-width: 540px; } }
  @media (min-width: 768px) {
    .container {
      max-width: 720px; } }
  @media (min-width: 992px) {
    .container {
      max-width: 960px; } }
  @media (min-width: 1200px) {
    .container {
      max-width: 1140px; } }
  .row {
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-wrap: wrap;
  flex-wrap: wrap;
  margin-right: -15px;
  margin-left: -15px; }
  .justify-content-center {
  -webkit-box-pack: center !important;
  -ms-flex-pack: center !important;
  justify-content: center !important; }
  .login-wrap {
  position: relative;
  color: rgba(255, 255, 255, 0.9); }
  .login-wrap h3 {
    font-weight: 300;
    color: #fff; }
  .login-wrap .social {
    width: 100%; }
    .login-wrap .social a {
      width: 100%;
      display: block;
      border: 1px solid rgba(255, 255, 255, 0.4);
      color: #000;
      background: #fff; }
      .login-wrap .social a:hover {
        background: #000;
        color: #fff;
        border-color: #000; }
      .form-group {
  position: relative; }
      .form-control {
  background: transparent;
  border: none;
  height: 50px;
  color: white !important;
  border: 1px solid transparent;
  background: rgba(255, 255, 255, 0.08);
  border-radius: 40px;
  padding-left: 20px;
  padding-right: 20px;
  -webkit-transition: 0.3s;
  -o-transition: 0.3s;
  transition: 0.3s; }
  @media (prefers-reduced-motion: reduce) {
    .form-control {
      -webkit-transition: none;
      -o-transition: none;
      transition: none; } }
  .form-control::-webkit-input-placeholder {
    /* Chrome/Opera/Safari */
    color: rgba(255, 255, 255, 0.8) !important; }
  .form-control::-moz-placeholder {
    /* Firefox 19+ */
    color: rgba(255, 255, 255, 0.8) !important; }
  .form-control:-ms-input-placeholder {
    /* IE 10+ */
    color: rgba(255, 255, 255, 0.8) !important; }
  .form-control:-moz-placeholder {
    /* Firefox 18- */
    color: rgba(255, 255, 255, 0.8) !important; }
  .form-control:hover, .form-control:focus {
    background: transparent;
    outline: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    border-color: rgba(255, 255, 255, 0.4); }
  .form-control:focus {
    border-color: rgba(255, 255, 255, 0.4); }
  
  button,
[type="button"],
[type="reset"],
[type="submit"] {
  -webkit-appearance: button; }

button:not(:disabled),
[type="button"]:not(:disabled),
[type="reset"]:not(:disabled),
[type="submit"]:not(:disabled) {
  cursor: pointer; }

button::-moz-focus-inner,
[type="button"]::-moz-focus-inner,
[type="reset"]::-moz-focus-inner,
[type="submit"]::-moz-focus-inner {
  padding: 0;
  border-style: none; }
input[type="submit"].btn-block,
input[type="reset"].btn-block,
input[type="button"].btn-block {
  width: 100%; }
.pl-3,
.px-3 {
  padding-left: 1rem !important; }
.pr-3,
.px-3 {
  padding-right: 1rem !important; }

#footer {
    padding-top: 0;
    margin-top: 0;
}
    </style>
<body>

  <div class="limiter">
      <div class="container-login100" style="height: 500px;
  position: relative; 
  background-image: url(data:image/jpeg;base64,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);
      background-size: cover;">
                    <div class="wrap-login100" style="margin-top: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);">
				<section class="ftco-section">
		<div class="container">
			
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
                                            <h3 class="mb-4 text-center" style="color: #474747;">Ulogujte se...</h3>

				<form th:action="/login" method="post" class="login100-form validate-form">
				

					<div class="form-group" data-validate = "Valid email is required: ex@abc.xyz">
                                            <input  class="form-control" type="text" name="username" class="form__input" placeholder="Korisnicko ime" required>
						
					</div>

                                    <div class="form-group" data-validate = "Password is required" style="margin-top: 10%;">
                                                <input  type="password" class="form-control" name="password" class="form__input" placeholder="Sifra" required>
						
					</div>
					
					<div class="form-group" style="margin-top: 10%;">
                                            <button type="submit" class="btn" style="width: 100%; border-radius: 20px;">
							Prijavi se
						</button>
					</div>

				</form>
                        
		      </div>
				</div>
			</div>
		</div>
	</section>
			</div>
		</div>
	</div>
    


</body>
</html>