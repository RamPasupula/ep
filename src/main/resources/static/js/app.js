//app.js
(function() {
	var app = angular.module('app', [ 'ui.router' ,'ngResource', 'ngMaterial','ui.bootstrap']);
	app.run(function($rootScope, $location, $state, LoginService) {
	
		if (!LoginService.isAuthenticated()) {
			 $rootScope.$broadcast("NotAuthorized");
			$state.transitionTo('login');
		}
		
	});
	app.config([ '$stateProvider', '$urlRouterProvider',
			function($stateProvider, $urlRouterProvider, $rootScope) {
			
				$stateProvider.state('login', {
					url : '/login',
					templateUrl : 'login.html',
					controller : 'loginController'
				}).state('home', {
					url : '/home',
					templateUrl : '/views/home.html',
					controller : 'homeController',
					resolve: { authenticate: authenticate }
				}).state('discoverytools', {
					url : '/discoverytools',
					templateUrl : '/views/toolslogin.html',
					controller : 'loginInfoController',
					resolve: { authenticate: authenticate }
				}).state('systems', {
					url : '/systems',
					templateUrl : '/views/systems.html',
					controller : 'systemController',
					resolve: { authenticate: authenticate }
				}).state('files', {
					url : '/files',
					templateUrl : '/views/files.html',
					controller : 'fileController',
					resolve: { authenticate: authenticate }
				}).state('servers', {
					url : '/servers',
					templateUrl : '/views/servers.html',
					controller : 'serverController',
					resolve: { authenticate: authenticate }
				}).state('jobs', {
					url : '/jobs',
					templateUrl : '/views/jobs.html',
					controller : 'jobController',
					resolve: { authenticate: authenticate }
				}).state('cluster', {
					url : '/cluster',
					templateUrl : '/views/cluster.html',
					controller : 'clusterController',
					resolve: { authenticate: authenticate }
				}).state('customer', {
					url : '/customer',
					templateUrl : '/views/customerreport.html',
					controller : 'customerreport',
					resolve: { authenticate: authenticate }
				}).state('overallbatchstatus', {
					url : '/overallbatchstatus',
					templateUrl : '/views/loadstatus.html',
					controller : 'loadStatusController'
				}).state('loadv1', {
					url : '/loadv1',
					templateUrl : '/views/loadv1.html',
					controller : 'loadV1Controller',
					resolve: { authenticate: authenticate }
				}).state('loadv2', {
					url : '/loadv2',
					templateUrl : '/views/loadv2.html',
					controller : 'loadV1Controller',
					resolve: { authenticate: authenticate }
				}).state('frrdashboard', {
					url : '/frrdashboard',
					templateUrl : '/views/frrdashboard.html',
					controller : 'frrdashboardcontroller',
					resolve: { authenticate: authenticate }
				}).state('edwfrrbatchstatus', {
					url : '/edwfrrbatchstatus',
					templateUrl : '/views/loadFRR.html',
					controller : 'loadFRRController',
					resolve: { authenticate: authenticate }
				}).state('logout', {
					url : '/logout',
					templateUrl : 'login.html',
					controller : 'logoutController'
				})

				;
				/*
				 * .state('metadatasubmenu', { url : '/metadatasubmenu'
				 * 
				 * }).state('clusterSubmenu', { url : '/clusterSubmenu'
				 * }).state('batchsubmenu', { url : '/batchsubmenu' });
				 */

				// $urlRouterProvider.otherwise('/login');
			} ]);
	function authenticate($q, $rootScope, $state, $timeout) {
	      if ($rootScope.isAuthenticated) {
	        // Resolve the promise successfully
	        return $q.when()
	      } else {
	        // The next bit of code is asynchronously tricky.

	        $timeout(function() {
	          // This code runs after the authentication promise has been rejected.
	          // Go to the log-in page
	         // $state.go('login')
	         $state.transitionTo('login');
	        })

	        // Reject the authentication promise to prevent the state from loading
	        return $q.reject()
	      }
	    };
	  
	
	
})();