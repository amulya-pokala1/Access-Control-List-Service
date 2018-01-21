var app = angular.module('myApp', ['ngRoute']);

app.service('forgotpasswordService', ['$http', function ($http) {
    this.checkDetails = function () {
        var pass = document.getElementById("pwd").value;
        var conpass = document.getElementById("con_pwd").value;
        if (pass.length == 0 || conpass.length == 0 || (pass != conpass)) {
            alert("Check both the passwords")
        }
        else {

        }
    }
}]);

app.controller('forgotpassword', function ($http, $scope, forgotpasswordService) {
    $scope.checkPassword = function () {

        forgotpasswordService.checkDetails().then(function (data) {
        });
    }

}); 