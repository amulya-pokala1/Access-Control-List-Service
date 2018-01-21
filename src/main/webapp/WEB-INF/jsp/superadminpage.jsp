<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>SuperAdminPage</title>

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
<script src="<c:url value="/resources/js/superadminpage.js" />"></script>
</head>

<body ng-app="myApp" ng-controller="adminController">
	<div >
		<div class="modal fade" id="registerAdmin">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Register</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<div>
							<div class="modal-body">
								<form method="post">
									<div class="form-group">
										<label for="text">Admin Name:</label> <input type="text"
											class="form-control" id="admin_name" placeholder="Enter name"
											name="name">
									</div>
									<div class="form-group">
										<label for="email">Admin Email:</label> <input type="eamil"
											class="form-control" id="admin_email"
											placeholder="Enter email" name="email">
									</div>
									<div class="form-group">
										<label for="email">Admin Description:</label> <input
											type="text" class="form-control" id="admin_description"
											placeholder="Enter description" name="desc">
									</div>

									<button class="btn btn-primary" ng-click="addAdmin()">Register</button>

								</form>
							</div>
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
						</div>

					</div>


				</div>

			</div>
		</div>
		<div class="container-fluid">
			<h1>Hello Super Admin</h1>
		</div>

		<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
			<a class="navbar-brand" href="#">A.C.L</a>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#registerAdmin">ADD ADMIN</a></li>
			</ul>
		</nav>
	</div>
	<h1>Admin List</h1>
	<table class="table">
		<thead>
		<tr>
			<th>S.No</th>
			<th>Name</th>
			<th>E-Mail</th>
			<th>Description</th>
		</tr>
		</thead>
		<tbody>
			<tr ng-repeat="admin in adminList">
				<td>{{$index+1}}</td>
				<td>{{admin.adminName}}</td>
				<td>{{admin.mailId}}</td>
				<td>{{admin.description}}</td>
				<td>
					<button ng-click="deleteAdmin(admin.adminId)" class="btn btn-danger">DELETE!</button>
			</tr>
		</tbody>
	</table>
</body>