var app = angular.module('myApp', []);

app.service('adminService', [ '$http', function($http, $scope) {
	this.addAdmin = function() {
		var name = document.getElementById("admin_name").value;
		var desc = document.getElementById("admin_description").value;
		var mail = document.getElementById("admin_email").value;
		if (name.length == 0 || desc.length == 0) {
			alert("check all the values")
		} else {

			return $http({
				method : "POST",
				url : "/access-control-list-service/api/admin",
				data : {
					"adminName" : name,
					"mailId" : mail,
					"description" : desc
				}
			}).then(function(response) {
				alert("Admin Created")

			}, function(rejection) {
				alert("Admin already exists")
			});
		}
	}

	this.deleteAdmin = function(adminId) {

		return $http({
			method : "DELETE",
			url : "/access-control-list-service/api/admin/" + adminId + "",

		}).then(function mySuccess(response) {
			console.log(response)
		}, function myError(response) {
			alert(response.data)
		});
	}

	this.getAdmins = function() {
		return $http({
			method : "GET",
			url : "/access-control-list-service/api/admins",
		}).then(function(response) {
			return response.data;
		}, function(response) {
			alert(response.data);
		});

	}
} ]);

app.controller('adminController', function($scope, $http, adminService) {
	$scope.adminList;
	$scope.addAdmin = function() {
		adminService.addAdmin().then(function(data) {
			adminService.getAdmins().then(function(data) {
				$scope.adminList = data;
			});
		});
	}

	$scope.deleteAdmin = function(id) {
		adminService.deleteAdmin(id).then(function(data) {
			$scope.adminList = $scope.adminList.filter(function(e) {
				return e.id != id;
			})
		});
	}

	adminService.getAdmins().then(function(data) {
		$scope.adminList = data;
	});
});