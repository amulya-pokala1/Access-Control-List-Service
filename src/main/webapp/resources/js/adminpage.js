var app = angular.module('myApp', ['ngRoute']);

app.service('service', ['$http', function ($http, $scope, $root) {
	
	this.getAllPermissions = function(){
		return $http({
			method: "GET",
			url: "/access-control-list-service/api/permissions",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});
	}
	
	this.addPermissionToUser = function(permissionId,userId){
		return $http({
			method: "PUT",
			url: "/access-control-list-service/api/user/"+userId+"/permission/"+permissionId

		}).then(function mySuccess(response) {
			return response;
		}, function myError(response) {
			console.log(response)
		});
	}
	
	this.removePermissionFromUser = function(permissionId,userId){
		return $http({
			method: "DELETE",
			url: "/access-control-list-service/api/user/"+userId+"/permission/"+permissionId

		}).then(function mySuccess(response) {
			return response;
		}, function myError(response) {
			console.log(response)
		});
	}

	
	this.getUserPermissions = function(userId){
		return $http({
			method: "GET",
			url: "/access-control-list-service/api/user/"+userId+"/permissions",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});
	}
	this.getPermissionsExceptUser = function(userId){
		return $http({
			method: "GET",
			url: "/access-control-list-service/api/user/"+userId+"/exceptPermissions",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});
	}
	
	this.removePermissionFromGroup = function(permissionId,groupId){
		return $http({
			method: "DELETE",
			url: "/access-control-list-service/api/group/"+groupId+"/permission/"+permissionId

		}).then(function mySuccess(response) {
			console.log('res');
			console.log(response);
			return response;
		}, function myError(response) {
			console.log(response)
		});
	}
	
	this.addPermissionToGroup = function(permissionId,groupId){
		return $http({
			method: "PUT",
			url: "/access-control-list-service/api/group/"+groupId+"/permission/"+permissionId

		}).then(function mySuccess(response) {
			return response;
		}, function myError(response) {
			console.log(response)
		});
	}
	this.getGroupPermissions = function(groupId) {
		return $http({
			method: "GET",
			url: "/access-control-list-service/api/group/"+groupId+"/permission",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});
	}
	
	this.getPermissionsExceptGroup = function(groupId) {
		return $http({
			method: "GET",
			url: "/access-control-list-service/api/group/"+groupId+"/exceptPermission",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});
	}

	this.getUsersNotInGroup = function(groupId) {
		return $http({
			method: "GET",
			url: "/access-control-list-service/api/group/"+groupId+"/usersExceptInGroup",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});
	}
	
	this.getDetails = function () {

		return $http({
			url: '/access-control-list-service/api/userList',
			method: "GET",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.data;
		});
	}

	this.getGroups = function () {

		return $http({
			method: "GET",
			url: "/access-control-list-service/api/groups",
		}).then(function (response) {
			return response.data;
		}, function (response) {
			return response.statusText;
		});

	}

	this.deleteFromGroup = function (userId, groupId) {
		return $http({
			method: "DELETE",
			url: "/access-control-list-service/api/group/" +
				groupId +
				"/user/" +
				userId + "",

		}).then(function mySuccess(response) {}, function myError(response) {
			console.log(response)
		});
	}

	this.deleteGroup = function (groupId) {
		return $http({
			method: "DELETE",
			url: "/access-control-list-service/api/group/" +
				groupId + "",

		}).then(function mySuccess(response) {
			alert("Group Deleted");
		}, function myError(response) {
			console.log(response)
		});
	}

	this.deleteUser = function (userId) {
		return $http({
			method: "DELETE",
			url: "/access-control-list-service/api/user/" +
				userId + "",

		}).then(function mySuccess(response) {
			alert("User Deleted!");
		}, function myError(response) {
			alert(response.data);
		});
	}

	this.addGroupsData = function (name,desc) {

		if (name.length == 0 || desc.length == 0) {
			alert("check all the values")
		} else {

			return $http({
				method: "POST",
				url: "/access-control-list-service/api/group",
				data: {
					"groupName": name,
					"groupDescription": desc
				}

			}).then(function (response) {
				alert("Group Created");
			}, function (rejection) {
				alert("Group already exists")
			});
		}
	}
	this.adddata = function () {
		var name = document.getElementById("user_name").value;
		var email = document
			.getElementById("user_email").value;

		if (name.length == 0 || email.length == 0) {
			alert("check all the values")
		} else {

			return $http({
				method: "POST",
				url: "/access-control-list-service/api/user",
				data: {
					"userName": name,
					"mailId": email
				}

			}).then(function (response) {
				alert("User Created");

			}, function (rejection) {
				alert(rejection.data);
			});
		}
	}

	this.addPermission = function () {
		var name = document
			.getElementById("permission_name").value;
		var desc = document
			.getElementById("permission_description").value;

		if (name.length == 0 || desc.length == 0) {
			alert("check all the values")
		} else {
			return $http({
				method: "POST",
				url: "/access-control-list-service/api/permission",
				data: {
					"permissionName": name,
					"permissionDescription": desc,
				}

			}).then(function (response) {
				alert("Permission Created")

			}, function (rejection) {
				alert(rejection.data);
			});
		}
	}

}])

app.controller('userList', function ($scope,$rootScope, $http, service, $location,
	$routeParams) {
	service.getDetails().then(function (data) {
		$rootScope.userList = data;
	});
});

app.controller('groups', function ($scope, $rootScope, $http, service, $window, 
	$location, $routeParams) {
	$rootScope.groups;
	$scope.flag;
	$scope.selectedGroup;
	$rootScope.selectedGroupId
	$scope.flag = false;
	$scope.listUsersFromGroup;
	$scope.groupName;
	$scope.groupDesc;
	$rootScope.selectedGroupForPermission;
	$rootScope.notInGroupUserList;
	$rootScope.groupPermissions;
	$rootScope.permissionsExceptGroup;
	$rootScope.userPermissions;
	$rootScope.permissionsExceptUser;
	$rootScope.allPermissionList;
	
	service.getGroups().then(function (data) {
		$rootScope.groups = data;
	});

	$scope.selectGroupForPermissions = function(groupId){
		$rootScope.selectedGroupForPermission = groupId;
		service.getGroupPermissions(groupId).then(function(data){
			$rootScope.groupPermissions = data;
		})
		service.getPermissionsExceptGroup(groupId).then(function(data){
			$rootScope.permissionsExceptGroup = data;
		})
	}
	$scope.storeGroupId = function (id) {
		$rootScope.selectedGroupId = id;
		service.getUsersNotInGroup(id).then(function(data){
			$rootScope.notInGroupUserList = data;
		});
	}

	$scope.deleteGroup = function (groupId) {
		service.deleteGroup(groupId).then(function (data) {
			$rootScope.groups = $rootScope.groups.filter(function(e){
    			return e.groupId != groupId;
    		})
		});
	}

	$scope.deleteUserFromGroup = function (userId, groupId) {
		service.deleteFromGroup(userId, groupId).then(function (data) {
			$scope.groupUserList = $scope.groupUserList.filter(function(e){
    			return e.userId != userId;
			})
		})
	}

	$scope.addGroupData = function () {
		console.log("here");
		service.addGroupsData($scope.groupName,$scope.groupDesc).then(function (data) {
			$('.close').click();
			$window.location.reload();
		})
	}

	$scope.addUserToGroup = function (id) {
		$http({
			method: "PUT",
			url: "/access-control-list-service/api/group/" +
				$rootScope.selectedGroupId + "/user/" + id + "",
		}).then(function mySuccess(response) {
			alert("User added to group!");
			$('.close').click();
		}, function myError(response) {
			alert(response.data);
		});
	}

	$scope.listUsersFromGroup = function (id) {

		$scope.selectedGroup = id;
		$scope.flag = true;
		$http({
			method: "GET",
			url: "/access-control-list-service/api/group/" +
				$scope.selectedGroup + "/users",

		}).then(function mySuccess(response) {
			console.log(response)
			$scope.groupUserList = response.data;
		}, function myError(response) {
			alert("error occured!")
		});
	}

});

app.controller('users', function ($scope,$rootScope, $http, service, $location,
	$routeParams, $route) {
	$scope.createUser = function () {
		service.adddata().then(function (data) {
			$('.close').click();
			service.getDetails().then(function (data) {
				$rootScope.userList = data;
			});
		});
	}
	$scope.deleteUser = function (id) {
		service.deleteUser(id).then(function (data) {
			$rootScope.userList = $rootScope.userList.filter(function(e){
    			return e.userId != id;
    		})
		})
		service.getDetails().then(function (data) {
			$rootScope.userList = data;
		});
	}
	$scope.editUser = function(userId){
		console.log(userId);
		$rootScope.selectedUser = userId;
		$('.close').click();
		$('#userPermissions').modal('show');
		service.getPermissionsExceptUser(userId).then(function(data){
			$rootScope.permissionsExceptUser = data;
		});
		service.getUserPermissions(userId).then(function(data){
			$rootScope.userPermissions = data;
		});
	}
});

app.controller('permissions', function ($scope,$rootScope, $http, service, $location){
	$scope.addPermission = function () {
		service.addPermission().then(function (data) {
			$('.close').click();
			service.getAllPermissions().then(function(data){
				$rootScope.allPermissionList = data;
			});
		});
	};
	$scope.addPermissionToUser = function(permissionId) {
		service.addPermissionToUser(permissionId,$rootScope.selectedUser).then(function(data){
			if(data.status == 200){
				var temp = $rootScope.permissionsExceptUser.filter(function(e){
        			return e.permissionId == permissionId;
        		});
				$rootScope.userPermissions.push(temp[0]);
				$rootScope.permissionsExceptUser = $rootScope.permissionsExceptUser.filter(function(e){
        			return e.permissionId != permissionId;
        		});
			}
		});
	}
	
	$scope.removePermissionFromUser= function(permissionId) {
		service.removePermissionFromUser(permissionId,$rootScope.selectedUser).then(function(data){
			if(data.status == 200){
				var temp = $rootScope.userPermissions.filter(function(e){
        			return e.permissionId == permissionId;
        		});
				$rootScope.permissionsExceptUser.push(temp[0]);
				$rootScope.userPermissions = $rootScope.userPermissions.filter(function(e){
        			return e.permissionId != permissionId;
        		});
			}
		});
	}
	
	$scope.addPermissionToGroup = function(id){
		service.addPermissionToGroup(id,$rootScope.selectedGroupForPermission).then(function(response){
			if(response.status == 200) {
				var temp = $rootScope.permissionsExceptGroup.filter(function(e){
        			return e.permissionId == id;
        		});
				console.log(temp[0]);
				$rootScope.groupPermissions.push(temp[0]);
				$rootScope.permissionsExceptGroup = $rootScope.permissionsExceptGroup.filter(function(e){
        			return e.permissionId != id;
        		});;
			}
		});
	}
	$scope.removePermissionFromGroup = function(id) {
		service.removePermissionFromGroup(id,$rootScope.selectedGroupForPermission).then(function(response){
			if(response.status == 200) {
				var temp = $rootScope.groupPermissions.filter(function(e){
        			return e.permissionId == id;
        		});
				console.log(temp[0]);
				$rootScope.permissionsExceptGroup.push(temp[0]);
				$rootScope.groupPermissions = $rootScope.groupPermissions.filter(function(e){
        			return e.permissionId != id;
        		});;
			}
		});
	}
});

app.controller('allPermissions', function ($scope,$rootScope, $http, service, $location){

	service.getAllPermissions().then(function(data){
		$rootScope.allPermissionList = data;
	});
});
app.directive('bsTooltip', function(){
    return {
        restrict: 'A',
        link: function(scope, element, attrs){
            $(element).hover(function(){
                // on mouseenter
                $(element).tooltip('show');
            }, function(){
                // on mouseleave
                $(element).tooltip('hide');
            });
        }
    };
});