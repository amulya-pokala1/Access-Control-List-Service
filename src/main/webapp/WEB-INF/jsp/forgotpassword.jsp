<!DOCTYPE html>
<html lang="en">

<head>
<title>Forgot Password Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.25/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet"
	integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="forgotpassword.js"></script>
</head>

<body ng-app="myApp">
	<div ng-controller="forgotpassword">
		<h2 style="text-align: center; margin-top: 25px;">ACCESS CONTROL
			LIST SERVICE</h2>
		<br> <br> <br>
		<h3 style="text-align: center;">UPDATE PASSWORD</h3>
		<div class="container col-lg-6">

			<form class="form-control"
				action="/access-control-list-service/resetPassword" method="POST">

				<div class="form-group">

					<input type="password" class="form-control" id="pwd"
						placeholder="Enter password" name="pswd" />
				</div>
				<div class="form-group">

					<input type="password" class="form-control" id="con_pwd"
						placeholder="Confirm password" name="confpswd" />
				</div>
				<div class="form-group">

					<button type="submit" class="btn btn-primary"
						ng-click="checkPassword()">Submit</button>
				</div>

			</form>
		</div>
	</div>
</body>

</html>