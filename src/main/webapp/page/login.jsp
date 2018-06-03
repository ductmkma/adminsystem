<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<html lang="en">
  <head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Gentelella Alela! | </title>

    <!-- Bootstrap -->
    <link type="text/css" href="<c:url value='/resources/vendors/bootstrap/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <!-- Font Awesome -->
    <link type="text/css" href="<c:url value='/resources/vendors/font-awesome/css/font-awesome.min.css'/>" rel="stylesheet">
    <!-- NProgress -->
    <link type="text/css" href="<c:url value='/resources/vendors/nprogress/nprogress.css'/>" rel="stylesheet">
    <!-- Animate.css -->
    <link type="text/css" href="<c:url value='/resources/vendors/animate.css/animate.min.css'/>" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link type="text/css" href="<c:url value='/resources/build/css/custom.min.css'/>" rel="stylesheet">
  </head>
  <style>
  	.error{
  		color:red;
  	}
  </style>
  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="<c:url value='/j_spring_security_login'/>" method="post">
              <h1>Login Form</h1>
              <div>
                <input type="text" name="username" class="form-control" placeholder="Username" required="" />
              </div>
              <div>
                <input type="password" name="password" class="form-control" placeholder="Password" required="" />
              </div>
              <div>
                <input type="checkbox" name="remember-me" class="" placeholder="" required="" /> Remember me
              </div>
              <div class="error">
              	<span>${message}</span>
              </div>
              <div>
                 <button type="submit" class="btn btn-success">Login</button>
                <a class="reset_pass" href="#">Lost your password?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">New to site?
                  <a href="#signup" class="to_register"> Create Account </a>
                </p>
	
                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                  <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                </div>
              </div>
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            </form>
          </section>
        </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
