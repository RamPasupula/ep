//loginController-js 
var app = angular.module('app');
app.controller('loginController', 
		function($scope, $rootScope, $stateParams,$state,$http, LoginService) {
	console.log("loginController");
	$scope.user = {
			userid : "",
			passwd : "",
			ip : "",
			domain:"SG"
		};
	$rootScope.isAuthenticated = false;
	$rootScope.userId='';
	 $scope.domains = [
	        {model : "SG"},
	        {model : "TH"},
	        {model : "MY"},
	        {model : "CN"}
	        
	    ];
	 
	  $scope.selectedDomain ="SG";
	$rootScope.title = "EdagPortal Login";
					$scope.login = function() {

						var header = "headers: {'Content-Type': 'application/json; charset=UTF-8'}";
						var url = '/userLogin';

						if (null != $scope.selectedDomain) {
							$scope.user.domain = $scope.selectedDomain;
						}

						$http.post(url, angular.toJson($scope.user), header)
								.then(
										function(response) {

										if (!response.data.error) {

												
												$scope.ErrorMessage = "";
												$rootScope.isAuthenticated =true;
												$rootScope.userId=userid;
																							
												
												$scope.user = {
														userid : "",
														passwd : ""
													};
												$state.transitionTo('home');
											} else {
												$scope.ErrorMessage = response.data.errorReason;
											}

										},
										function(response) {
											console.log("Unable to perform post request");

										});
				
					};
	
	$scope.reset = function() {

		$scope.user = {
			userid : "",
			passwd : ""
		};
		$scope.ErrorMessage = "";
	};
});