//homeController.js
var app = angular.module('app');

app.controller('homeController', function($scope, $rootScope, $stateParams,$state, LoginService) {
	console.clear();
	console.log('inside home');
	$scope.headingTitle = "Home Page";
});
