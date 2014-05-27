'use strict';

angular.module('sipUniversity')
	.constant('authenticationUrl', 'http://localhost:8080/sip/users/login')
	.constant('userDataUrl', 'http://localhost:8080/sip/rest/home')
	.constant('USER_ROLES', {
		all: '*',
		admin: 'ROLE_ADMIN',
		user: 'ROLE_USER'
	})
	.controller('sipUniversityController', function ($scope, $http, $location, authenticationUrl, userDataUrl) {
		$scope.data = {};
		$scope.authenticate = function (user, pass) {
			var authData = 'j_username=	' + user + '&j_password=' + pass;
			$http.post(authenticationUrl, authData, {
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				withCredentials: true
			})
			.success(function () {
				$http.get(userDataUrl)
					.success(function (data) {
						$location.path('/home');
					})
					.error(function (error, status) {						
						$scope.data.error = {errorMessage: error, status: status};
						$location.path('/home');
					});				
			})
			.error(function (error, status) {
				if (!error || error.length === 0 || !status || status === 0) {
					error = 'unexpected internal error';
					status = -1;
				}
				console.log('Error authenticating user: ' + error + '(' + status + ')');
				$scope.authenticationError = {error: error, status: status};
			});
		};
	});

