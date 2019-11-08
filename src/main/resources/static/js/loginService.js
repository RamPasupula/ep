var app = angular.module('app');
  
  app.factory('LoginService', function($rootScope) {
    var admin = 'admin';
    var pass = 'password';
    var isAuthenticated = false;
    var userId='';
    return {
      login : function($rootScope) {
        isAuthenticated =$rootScope.isAuthenticated;
        
        return isAuthenticated;
      },
      isAuthenticated : function() {
    	  isAuthenticated =$rootScope.isAuthenticated;
    	  userId= $rootScope.userId;
        return isAuthenticated;
      }
    };
    
  });