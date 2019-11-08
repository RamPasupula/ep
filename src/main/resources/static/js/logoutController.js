//logoutController-js 
var app = angular.module('app');
app.controller('logoutController', 
		function($scope, $rootScope, $stateParams,
		$state, $http, LoginService) {
	console.log("logoutController");
	$scope.user = {
		userid : "",
		passwd : "",
		ip : "",
		domain : "SG"
	};
	$scope.ErrorMessage = "";
	$rootScope.isAuthenticated = false;
	$scope.domains = [ {
		model : "SG"
	}, {
		model : "TH"
	}, {
		model : "MY"
	}, {
		model : "CN"
	}

	];

	$scope.selectedDomain = "SG";

	$scope.logout = function() {

		$scope.user = {
			userid : "",
			passwd : ""
		};
		$scope.ErrorMessage = "";
		$rootScope.isAuthenticated= false;

		$state.transitionTo('login');

	};

});