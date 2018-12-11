<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Allerta+Stencil">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="CSS/ForgetPassword.css">

</head>
<body>

<section class="bgimg w3-display-container w3-text-black "> 
  <br><br><br>
  <section class="container">
    <section class="container-header" style="padding:35px 50px;">
      <h4><span class="glyphicon glyphicon-lock"></span> Forgot Password</h4>
    </section>
		
		<section class="text-center" style="padding:50px 0">
			<section class="login-form-1">
				<form id="forgot-password-form" class="text-left" action="SendMailController" method="post">
					
					<section class="login-form-main-message"></section>

					<section class="main-login-form">
						<section class="login-group">
        			<section class="form-group">
          			<label for="fp_email"><span class="glyphicon glyphicon-envelope"></span> Email ID</label>
          			<input type="email" class="form-control" id="fp_email" name="fp_email" required="" placeholder="Enter Email ID">
        			</section>
        

						
						</section>
						<button type="submit" class="btn btn-basic btn-block"><span class="glyphicon glyphicon-ok"></span> Submit</button>
					</section>
				</form>
			</section>
		</section>

  
      
	</section> 
</section>



</body>
</html>
