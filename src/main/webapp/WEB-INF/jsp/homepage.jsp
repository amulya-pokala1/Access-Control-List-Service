<!DOCTYPE html>
<html lang="en">

<head>
<title>HomePage</title>
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
<script src="/access-control-list-service/resources/js/adminpage.js"></script>
<link rel="stylesheet"
	href="/access-control-list-service/resources/css/homepage.css" />
</head>

<body>

	<!-- this displays a from for superadmin -->
	<div class="modal fade" id="validateSuperAdmin">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">


					<div class="modal-header">
						<h4 class="modal-title">Login</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>


					<div class="modal-body">
						<form action="/access-control-list-service/superAdmin"
							method="POST" onsubmit="validateSuperAdminLogin()">
							<div class="form-group">
								<label for="email">Email:</label> <input type="email"
									class="form-control" id="superadmin_email"
									placeholder="Enter email" name="email">
							</div>
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password"
									class="form-control" id="superadmin_pwd"
									placeholder="Enter password" name="pswd">
							</div>
							<div class="form-group">
								<label for="pwd">Secret Key:</label> <input type="password"
									class="form-control" id="superadmin_superkey"
									placeholder="Enter Secret key" name="pswd">
							</div>
							<div class="form-check">
								<label class="form-check-label"> <input
									class="form-check-input" type="checkbox" name="remember">
									Remember me
								</label>
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>


					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
	</div>

	<!-- this displays a from for admin -->
	<div class="modal fade" id="validateAdmin">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">


					<div class="modal-header">
						<h4 class="modal-title">Login</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>


					<div class="modal-body">
						<form action="/access-control-list-service/login1" method="POST">
							<div class="form-group">
								<label for="email">Email:</label> <input type="email"
									class="form-control" id="admin_email" placeholder="Enter email"
									name="email">
							</div>
							<div class="form-group">
								<label for="pwd">Password:</label> <input type="password"
									class="form-control" id="admin_pwd"
									placeholder="Enter password" name="pswd"> <br> <a
									href="forgotpassword.html">Forgot Password</a>
							</div>

							<button class="btn btn-primary">SUBMIT</button>
						</form>
					</div>


					<div class="modal-footer">
						<button class="btn btn-secondary" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
	</div>




	<div class="container">
		<div class="row">
			&emsp; &emsp;
			<div class="col-sm-3">
				<div class="card mt-4" style="width: 200px">
					<img class="card-img-top" src="superadmin.jpg" alt="Card image"
						style="width: 100%; height: 115px">
					<div class="card-body">
						<h4 class="card-title">Super Admin</h4>
						<a href="#" class="btn btn-primary" data-toggle="modal"
							data-target="#validateSuperAdmin">Login</a>
					</div>
				</div>
			</div>
			&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;&emsp; &emsp;
			<div class="col-sm-3">
				<div class="card mt-4" style="width: 200px">
					<img class="card-img-top" src="admin.jpg" alt="Card image"
						style="width: 100%">
					<div class="card-body">
						<h4 class="card-title">Admin</h4>
						<a href="#" class="btn btn-primary" data-toggle="modal"
							data-target="#validateAdmin">Login</a>
					</div>
				</div>
			</div>

		</div>

	</div>
</body>


</html>