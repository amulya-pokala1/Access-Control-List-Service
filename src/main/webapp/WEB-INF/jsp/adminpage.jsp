<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Admin Page</title>

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



</head>

<body ng-app="myApp">
	<div class="modal fade" id="listAllPermissions">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">All Permissions</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div ng-controller="allPermissions">
						<div class="modal-body row">
							<table class="table">
								<thead>
									<tr>
										<th>S.No</th>
										<th>Permission Name</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="permission in allPermissionList"
										title="{{permission.permissionDescription}}" bsTooltip>
										<td>{{$index+1}}</td>
										<td>{{permission.permissionName}}</td>
									</tr>
								</tbody>
							</table>
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
	<div ng-controller="groups">
		<div class="modal fade" id="userPermissions">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">User Permissions</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div ng-controller="userList">
							<div class="modal-body row">
								<div class="col-lg-6">
									User Permissions
									<table class="table">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Permission</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="permission in userPermissions"
												title="{{permission.permissionDescription}}" bsTooltip>
												<td>{{$index+1}}</td>
												<td>{{permission.permissionName}}</td>
												<td ng-controller="permissions">
													<button class="btn btn-danger"
														ng-click="removePermissionFromUser(permission.permissionId)">Remove
														Permission</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-lg-6">
									All Permissions
									<table class="table">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Permission</th>
											</tr>
										</thead>
										<tbody>
											<tr ng-repeat="permission in permissionsExceptUser"
												title="{{permission.permissionDescription}}" bsTooltip>
												<td>{{$index+1}}</td>
												<td>{{permission.permissionName}}</td>
												<td ng-controller="permissions">
													<button class="btn btn-success"
														ng-click="addPermissionToUser(permission.permissionId)">Add
														Permission</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
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
	<div class="modal fade" id="groupPermissions">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Group Permissions</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div ng-controller="userList">
						<div class="modal-body row">
							<div class="col-lg-6">
								Group Permissions
								<table class="table">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Permission</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="permission in groupPermissions"
											title="{{permission.permissionDescription}}" bsTooltip>
											<td>{{$index+1}}</td>
											<td>{{permission.permissionName}}</td>
											<td ng-controller="permissions">
												<button class="btn btn-danger"
													ng-click="removePermissionFromGroup(permission.permissionId)">Remove
													Permission</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-lg-6">
								All Permissions
								<table class="table">
									<thead>
										<tr>
											<th>S.No</th>
											<th>Permission</th>
										</tr>
									</thead>
									<tbody>
										<tr ng-repeat="permission in permissionsExceptGroup"
											title="{{permission.permissionDescription}}" bsTooltip>
											<td>{{$index+1}}</td>
											<td>{{permission.permissionName}}</td>
											<td ng-controller="permissions">
												<button class="btn btn-success"
													ng-click="addPermissionToGroup(permission.permissionId)">Add
													Permission</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
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
	<div class="modal fade" id="listAllUsers">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-lm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Users List</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div ng-controller="users">
						<div class="modal-body">
							<table class="table">
								<thead>
									<tr>
										<th>Name</th>
										<th>Email</th>

										<th></th>
										<th></th>
									</tr>
								</thead>
								<tbody ng-repeat="user in userList">
									<tr>
										<td>{{user.userName}}</td>
										<td>{{user.mailId}}</td>

										<td data-toggle="tooltip " title="Edit User"><i
											class="fa fa-pencil-square-o " aria-hidden="true "
											ng-click="editUser(user.userId)"></i></td>

										<td data-toggle="tooltip " title="Delete User "><i
											class="fa fa-trash " aria-hidden="true "
											ng-click="deleteUser(user.userId)"></i></td>
									</tr>
								</tbody>
							</table>

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
	<div class="modal fade" id="registerGroup" ng-controller="groups">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">


					<div class="modal-header">
						<h4 class="modal-title">Create Group!</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>


					<div class="modal-body">
						<form>
							<div class="form-group">
								<label for="text">Group Name:</label> <input type="text"
									class="form-control" ng-model="groupName" id="group_name"
									placeholder="Enter name" name="name">
							</div>

							<div class="form-group">
								<label for="description">Description:</label> <input
									type="textarea" class="form-control" ng-model="groupDesc"
									id="group_description" placeholder="Enter Description"
									name="description">
							</div>

							<button type="submit" class="btn btn-primary"
								ng-click=addGroupData()>Submit</button>
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
	<div class="modal fade" id="addUserToGroup">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-lm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Add Users to group</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div ng-controller="userList">
						<div class="modal-body">
							<form>
								<table class="table">
									<thead>
										<tr>
											<th>Name</th>
											<th>Email</th>
											<th></th>

										</tr>

									</thead>
									<tbody ng-repeat="user in notInGroupUserList">
										<tr>
											<td>{{user.userName}}</td>
											<td>{{user.mailId}}</td>
											<td>
												<div ng-controller="groups">
													<button class="btn btn-success"
														ng-click="addUserToGroup(user.userId)">Add User</button>
												</div>
											</td>
										</tr>
									</tbody>
								</table>

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
	<div class="modal fade" id="registerUser">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">CREATE USER</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<div>
						<div class="modal-body" ng-controller="users">
							<form ng-submit="createUser()">
								<div class="form-group">
									<label for="text">User Name:</label> <input type="text"
										class="form-control" ng-model="userName" id="user_name"
										placeholder="Enter name" name="name">
								</div>
								<div class="form-group">
									<label for="email">User Email:</label> <input type="email"
										class="form-control" ng-model="email" id="user_email"
										placeholder="Enter email" name="email">
								</div>
								<div>
									<input type="submit" class="btn btn-primary" />
								</div>
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
	<div ng-controller="permissions">
		<div class="modal fade" id="addPermissions">
			<div class="vertical-alignment-helper">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">Add Permissions</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label for="text">Permission Name:</label> <input type="text"
											class="form-control" ng-model="permissionName"
											id="permission_name" placeholder="Enter name" name="name" />
									</div>
									<div class="form-group">
										<label for="text">Permission Description:</label> <input
											type="text" class="form-control" ng-model="permissionDesc"
											id="permission_description"
											placeholder="Write a short description" name="email" />
									</div>

									<button class="btn btn-primary" ng-click="addPermission()">Add
										Permission</button>

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
	</div>

	<div class="container-fluid">
		<h1>Hello ${name}</h1>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
		<a class="navbar-brand" href="#">A.C.L</a>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#registerUser">CREATE USER</a></li>
			&emsp; &emsp;

			<li class="nav-item"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#registerGroup">CREATE GROUP</a></li>
			&emsp; &emsp;
			<li class="nav-item"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#addPermissions">CREATE
					PERMISSION</a></li> &emsp; &emsp;
			<li class="nav-item"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#listAllUsers">LIST ALL USERS</a></li>
			&emsp; &emsp;
			<li class="nav-item"><a class="nav-link" href="#"
				data-toggle="modal" data-target="#listAllPermissions">LIST ALL
					PERMISSIONS</a></li> &emsp; &emsp;
			<li class="nav-item"><a class="nav-link"
				href="/access-control-list-service/logout">LOG OUT!</a></li>
		</ul>
	</nav>
	<br>
	<br>
	<div ng-controller="groups">
		<div class="row">
			<div class="col-lg-6">
				<div class="table-responsive ">
					<table class="table ">
						<thead>
							<tr>
								<th>S. No</th>
								<th>Name</th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="group in groups">

								<td title="{{group.groupDescription}}" bsTooltip>{{$index+1}}</td>
								<td title="{{group.groupDescription}}" bsTooltip>{{group.groupName}}</td>
								<td data-toggle="tooltip " title="Add User to Group "><i
									class="fa fa-plus-square" aria-hidden="true"
									data-toggle="modal" data-target="#addUserToGroup"
									ng-click="storeGroupId(group.groupId)"></i></td>
								<td data-toggle="tooltip " title="Edit Permissions"><i
									class="fa fa-pencil-square-o " aria-hidden="true"
									data-toggle="modal" data-target="#groupPermissions"
									ng-click="selectGroupForPermissions(group.groupId)"></i></td>

								<td data-toggle="tooltip " title="Delete Group "><i
									class="fa fa-trash " aria-hidden="true "
									ng-click="deleteGroup(group.groupId)"></i></td>
								<td data-toggle="tooltip " title="List Users in group"><i
									class="fa fa-user-circle " aria-hidden="true "
									ng-click="listUsersFromGroup(group.groupId)"></i></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="col-lg-6">
				<table class="table " id="groupUsersTable" ng-show="flag">
					<thead>
						<tr>
							<th>S. No</th>
							<th>Name</th>
							<th>Email</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<tr ng-repeat="user in groupUserList">
							<td>{{$index+1}}</td>
							<td>{{user.userName}}</td>
							<td>{{user.mailId}}</td>

							<td data-toggle="tooltip " title="Remove User From Group ">
								<i class="fa fa-trash " aria-hidden="true "
								ng-click="deleteUserFromGroup(user.userId,selectedGroup)"></i>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>