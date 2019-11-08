
var app = angular.module('app');

app.controller('fileController', function($scope, $rootScope, $stateParams,$state, LoginService, $filter, $http) {
	// alert('inside metadataController');
	$scope.headingTitle = "File Details";
	$scope.showLoader = true;
        $scope.currentPage = 0;
	    $scope.pageSize = 100;
	    $scope.data = [];
	    $scope.q = '';
	
		
		  $scope.pageSizes = [
		        {pages : "50"},
		        {pages : "100"},
		        {pages : "250"},
		        {pages : "500"}
		        
		    ];
		  
	  $http.get("/metadata/files").then(function successCallback(response) {
			$scope.files = response.data;
			$scope.data = response.data;
			$scope.files.count = $scope.files.length;
			$scope.showLoader = false;
			$scope.getData();
		//	 $scope.$watch('currentPage + numPerPage', updateFilteredItems);
		}, function errorCallback(response) {
			console.log("Unable to perform get request");
			$scope.showLoader = false;
		
		});
		

	    
	       $scope.getData = function () {
	        return $filter('filter')($scope.data, $scope.q)
	      };
	      
	      $scope.numberOfPages=function(){
	          return Math.ceil($scope.getData().length/$scope.pageSize);
	      };
	      
	     $scope.$watch('q', function(newValue,oldValue){
	    	  if(oldValue!=newValue){
	          $scope.currentPage = 0;
	        }
	      },true);
	  });


	


app.filter('startFrom', function() {
    return function(input, start) {
        start = +start;
        return input.slice(start);
    }
});


