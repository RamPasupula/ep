//load status

var app = angular.module('app');
app.controller('loadStatusController', function($scope, $rootScope, $stateParams,$state, LoginService, $http) {
	$scope.headingTitle = "Overall Batch Status";
	
	
	
	n =  new Date();
	y = n.getFullYear();
	m = n.getMonth() + 1;
	d = n.getDate();
	$scope.today = y + "-" + m + "-" + d;
	$http.get("/operation/t1").then(function successCallback(response) {
		$scope.t1 = response.data;
		$scope.t1.count = $scope.t1.length;

     

		var data = response.data;
		     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	         var intFormat = numberFormat({digitsAfterDecimal: 0});
	     
	         var floatFormat = numberFormat({digitsAfterDecimal: 2});
	       // var dataClass = $.pivotUtilities.SubtotalPivotData;
	        var renderers = $.pivotUtilities.renderers;
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#outputt1").pivotUI(data, {
	            	
		                cols: ["biz date"],
		                rows: ["region","country code"],
	               
		                derivedAttributes: {
	                        "load time (HH.MM)": dateFormat("load time", "%H.%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                        //,"arrival time1": frFormat( "arrival time"),
	                       // "file size1": frFormat( "file size")
	                    },
	                    
	                 vals: ["load time (HH.MM)"],
	                /* 
	                 sorters: {
	                        "load day": sortAs(["Sun","Mon","Tue","Wed", "Thu","Fri","Sat"])
	                 },	*/  	                 
	                 renderers:   $.extend(
	                	    	$.pivotUtilities.renderers, 
	                	        $.pivotUtilities.plotly_renderers,
	                	        $.pivotUtilities.export_renderers,
	                	        $.pivotUtilities.d3_renderers
	                	    ) 
	               ,
	               aggregatorName: "Count",
	               rendererName: "Table",
	               unusedAttrsVertical:false,
	           rendererOptions: {
	        	   Heatmap: {
	                         clickCallback: function(e, value, filters, pivotData){
	                        
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     }, plotly: {width: 900, height: 650}

	                 }
	             });
	        
	      });
	         
	
		
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});
	
	$http.get("/operation/frrt1").then(function successCallback(response) {
		$scope.t1 = response.data;
		$scope.t1.count = $scope.t1.length;

     

		var data = response.data;
		    var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	        var intFormat = numberFormat({digitsAfterDecimal: 0});
	        var floatFormat = numberFormat({digitsAfterDecimal: 2});
	       // var dataClass = $.pivotUtilities.SubtotalPivotData;
	        var renderers = $.pivotUtilities.renderers;
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#frroutputt1").pivotUI(data, {
	            	
		                cols: ["biz date" ],
		                rows: ["region","country code"],
	               
		                derivedAttributes: {
	                        "load time (HH.MM)": dateFormat("load time", "%H.%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                    
	                      //, "records count":  numberFormat("records count"),
	                      // "file size":  numberFormat("records count")
	                    },
	                    
	                 vals: ["load time (HH.MM)"],
	                 
	                	                 
	                 renderers: $.extend(
	                	    	$.pivotUtilities.renderers, 
	                	        $.pivotUtilities.plotly_renderers,
	                	        $.pivotUtilities.export_renderers
	                	    )
	               ,
	               aggregatorName: "Count",
	               rendererName: "Table",
	               unusedAttrsVertical:false,
	           rendererOptions: {
	        	   Heatmap: {
	                         clickCallback: function(e, value, filters, pivotData){
	                        
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     }, plotly: {width: 900, height: 650}

	                 }
	             });
	        
	      });
	         
	
		
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});
	
	
	$http.get("/operation/t14").then(function successCallback(response) {
		$scope.t14 = response.data;
		$scope.t14.count = $scope.t14.length;

     

		var data = $scope.t14;
		 var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
         var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	        var intFormat = numberFormat({digitsAfterDecimal: 0});
	        var floatFormat = numberFormat({digitsAfterDecimal: 2});
	       // var dataClass = $.pivotUtilities.SubtotalPivotData;
	        var renderers = $.pivotUtilities.renderers;
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#outputT14").pivotUI(data, {
	            	  cols: ["biz date" ],
	            	  rows: ["region","country code"],
	               

		                derivedAttributes: {
	                        "load time (HH.MM)": dateFormat("load time", "%H.%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                   
	                    },
	                    
	                 vals: ["load time (HH.MM)"],
	                 
	              	                 
	                 renderers: $.extend(
	                	    	$.pivotUtilities.renderers, 
	                	        $.pivotUtilities.plotly_renderers,
	                	        $.pivotUtilities.export_renderers
	                	    )
	               ,
	               aggregatorName: "Count",
	               rendererName: "Table",
	               unusedAttrsVertical:false,
	           rendererOptions: {
	        	   Table: {
	                         clickCallback: function(e, value, filters, pivotData){
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     }, plotly: {width: 900, height: 650}

	                 }
	             });
	        
	      });
	         
	
		
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
	//	alert('error');
	});
	$http.get("/operation/t15").then(function successCallback(response) {
		$scope.t15 = response.data;
		$scope.t15.count = $scope.t15.length;

		var data = $scope.t15;
		     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var monthAndDayDeriver = $.pivotUtilities.derivers.dateFormat("Name of Attribute", "%m/%d")
	         var renderers = $.pivotUtilities.renderers;
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#outputT15").pivotUI(data, {
	            	  cols: ["biz date" ],
	            	  rows: ["region","country code"],
	               
		                derivedAttributes: {
	                        "load time (HH.MM)": dateFormat("load time", "%H.%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                    },
	                    
	                 vals: ["load time (HH.MM)"],
	                /* sorters: {
	                        "load day": sortAs(["Sun","Mon","Tue","Wed", "Thu","Fri","Sat"])
	                 },	  */	                 
	                 renderers: $.extend(
	                	    	$.pivotUtilities.renderers, 
	                	        $.pivotUtilities.plotly_renderers,
	                	        $.pivotUtilities.export_renderers
	                	    )
	               ,
	               aggregatorName: "Count",
	               rendererName: "Table",
	               unusedAttrsVertical:false,
	           rendererOptions: {
	        	   Table: {
	                         clickCallback: function(e, value, filters, pivotData){
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     }, plotly: {width: 900, height: 650}

	                 }
	             });
	        
	      });
	         
	
		
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
	//	alert('error');
	});
	
	$http.get("/operation/t2").then(function successCallback(response) {
		$scope.t2 = response.data;
		$scope.t2.count = $scope.t2.length;
        var data = $scope.t2;
		     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var monthAndDayDeriver = $.pivotUtilities.derivers.dateFormat("Name of Attribute", "%m/%d")
	         var renderers = $.pivotUtilities.renderers;
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#outputT2").pivotUI(data, {
	            	  cols: ["biz date" ],
	            	  rows: ["region","country code"],
	               
		                derivedAttributes: {
	                        "load time (HH.MM)": dateFormat("load time", "%H.%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                    },
	                    
	                 vals: ["load time (HH.MM)"],
	                  	                 
	                 renderers: $.extend(
	                	    	$.pivotUtilities.renderers, 
	                	        $.pivotUtilities.plotly_renderers,
	                	        $.pivotUtilities.export_renderers
	                	    )
	               ,
	               aggregatorName: "Count",
	               rendererName: "Table",
	               unusedAttrsVertical:false,
	           rendererOptions: {
	        	   Table: {
	                         clickCallback: function(e, value, filters, pivotData){
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     }, plotly: {width: 900, height: 650}

	                 }
	             });
	        
	      });
	         
	
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
	//	alert('error');
	});
	$http.get("/operation/t3").then(function successCallback(response) {
		$scope.t3 = response.data;
		$scope.t3.count = $scope.t3.length;

     

		var data = $scope.t3;
		     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	         var intFormat = numberFormat({digitsAfterDecimal: 0});
	         var monthAndDayDeriver = $.pivotUtilities.derivers.dateFormat("Name of Attribute", "%m/%d")
	         var floatFormat = numberFormat({digitsAfterDecimal: 2});
	       // var dataClass = $.pivotUtilities.SubtotalPivotData;
	        var renderers = $.pivotUtilities.renderers;
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#outputT3").pivotUI(data, {
	            	  cols: ["biz date" ],
	            	  rows: ["region","country code"],
	               
		                derivedAttributes: {
	                        "load time (HH.MM)": dateFormat("load time", "%H.%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                    },
	                    
	                 vals: ["load time (HH.MM)"],
	                	                 
	                 renderers: $.extend(
	                	    	$.pivotUtilities.renderers, 
	                	        $.pivotUtilities.plotly_renderers,
	                	        $.pivotUtilities.export_renderers
	                	    )
	               ,
	               aggregatorName: "Count",
	               rendererName: "Table",
	               unusedAttrsVertical:false,
	           rendererOptions: {
	        	   Table: {
	                         clickCallback: function(e, value, filters, pivotData){
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     }, plotly: {width: 900, height: 650}

	                 }
	             });
	        
	      });
	         

	
		
		
		
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
	//	alert('error');
	});
	
	
	$http.get("/operation/t1summary").then(function successCallback(response) {
		$scope.t1 = response.data;
		$scope.t1.count = $scope.t1.length;
		var data = response.data;
		if (! jQuery.isEmptyObject(data)) {
		     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	         var intFormat = numberFormat({digitsAfterDecimal: 0});
	     
	         var floatFormat = numberFormat({digitsAfterDecimal: 2});
	         var renderers = $.extend($.pivotUtilities.renderers,
	                 $.pivotUtilities.gchart_renderers);
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#T1Summary").pivotUI(data, {
	                      cols: ["load time (HH:MM)" ],
		        //        rows: ["region","country code"],
	            	 
	                 //  	    cols: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
           
		                derivedAttributes: {
	                        "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
	                    },
	                  
	                 vals: ["file name"],
	                 
	              
                  aggregatorName: "Count",
                  rendererName: "Area Chart",
                  renderers: $.pivotUtilities.d3_renderers,
                  unusedAttrsVertical:false,
	           rendererOptions: {
	        	   rendererName: {
	                         clickCallback: function(e, value, filters, pivotData){
	                        
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     },   plotly: {width: 1000, height: 300}

	                 }
	             });
	        
	      });
	}
	         
}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});

	$http.get("/operation/frrt1summary").then(function successCallback(response) {
		$scope.t1 = response.data;
		$scope.t1.count = $scope.t1.length;
		var data = response.data;
		if (! jQuery.isEmptyObject(data)) {
		     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
            var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	         var intFormat = numberFormat({digitsAfterDecimal: 0});
	     
	         var floatFormat = numberFormat({digitsAfterDecimal: 2});
	         var renderers = $.extend($.pivotUtilities.renderers,
	                 $.pivotUtilities.gchart_renderers);
	        var derivers = $.pivotUtilities.derivers;
	      
	       
	     $(function(){
	        
	             $("#frrT1Summary").pivotUI(data, {
	                      cols: ["load time (HH:MM)" ],
		        //        rows: ["region","country code"],
	            	 
	                 //  	    cols: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
           
		                derivedAttributes: {
	                        "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
	                    },
	                  
	                 vals: ["file name"],
	                 
	              
                  aggregatorName: "Count",
                  rendererName: "Area Chart",
                  renderers: $.pivotUtilities.d3_renderers,
                  unusedAttrsVertical:false,
	           rendererOptions: {
	        	   rendererName: {
	                         clickCallback: function(e, value, filters, pivotData){
	                        
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     },   plotly: {width: 1000, height: 300}

	                 }
	             });
	        
	      });
	}
	         
}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});
$http.get("/operation/t14summary").then(function successCallback(response) {
	 var data = response.data;
	

 if (! jQuery.isEmptyObject(data)) {
	 var dateFormat =   $.pivotUtilities.derivers.dateFormat;
     var sortAs =  $.pivotUtilities.sortAs;
   var derivers = $.pivotUtilities.derivers;
   
       
     $(function(){
        
             $("#T14Summary").pivotUI(data, {
                 cols: ["load time (HH:MM)" ],
 		                   derivedAttributes: {
 	                        "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
 	                       "load date ":   dateFormat("load time", "%y-%m-%d", false)
 	                    },
 	                  
 	                   vals: ["process name"],
 	              
                   aggregatorName: "Count",
                   rendererName: "Area Chart",
                   unusedAttrsVertical:false,
                   renderers: $.pivotUtilities.d3_renderers,
 	           rendererOptions: {
 	        	   rendererName: {
 	                         clickCallback: function(e, value, filters, pivotData){
 	                        
 	                             var names = [];
 	                             pivotData.forEachMatchingRecord(filters,
 	                                 function(record){ names.push(record.Name); });
 	                             
 	                         }
 	                     },   plotly: {width: 1000, height: 300}

 	                 }
 	             });
        
      });
         
 }
}, function errorCallback(response) {
	console.log("Unable to perform get request");
	//alert('error');
});


$http.get("/operation/t15summary").then(function successCallback(response) {
	

	 var data = response.data;
	

if (! jQuery.isEmptyObject(data)) {
	 var dateFormat =   $.pivotUtilities.derivers.dateFormat;
     var sortAs =  $.pivotUtilities.sortAs;
   
     var derivers = $.pivotUtilities.derivers;
   
      
    $(function(){
       
            $("#T15Summary").pivotUI(data, {
	                      cols: ["load time (HH:MM)" ],
		        //        rows: ["region","country code"],
	            	 
	                 //  	    cols: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
           
		                derivedAttributes: {
	                        "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
	                        "load date ":   dateFormat("load time", "%y-%m-%d", false)
	                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
	                    },
	                  
	                    vals: ["process name"],
	                 
	              	                 
	              renderers: $.pivotUtilities.c3_renderers,
                  aggregatorName: "Count",
                  rendererName: "Area Chart",
                  unusedAttrsVertical:false,
                	           rendererOptions: {
	        	   rendererName: {
	                         clickCallback: function(e, value, filters, pivotData){
	                        
	                             var names = [];
	                             pivotData.forEachMatchingRecord(filters,
	                                 function(record){ names.push(record.Name); });
	                             
	                         }
	                     },   plotly: {width: 1000, height: 300}

	                 }
	             });
       
     });
        
}

	
	
}, function errorCallback(response) {
	console.log("Unable to perform get request");
	//alert('error');
});



$http.get("/operation/t2summary").then(function successCallback(response) {

   var data =  response.data;
	  if (! jQuery.isEmptyObject(data)) {
		  var dateFormat =   $.pivotUtilities.derivers.dateFormat;
	        var sortAs =  $.pivotUtilities.sortAs;
	        var tpl =  $.pivotUtilities.aggregatorTemplates;
          var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
	        var numberFormat = $.pivotUtilities.numberFormat;
	        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
	         var intFormat = numberFormat({digitsAfterDecimal: 0});
	     
	         var floatFormat = numberFormat({digitsAfterDecimal: 2});
	    
	        var derivers = $.pivotUtilities.derivers;
	      
      
       
     $(function(){
    	 
    	    $("#T2Summary").pivotUI(data, {
            	 
            	 
            	 cols: ["load time (HH:MM)" ],
                       //        rows: ["region","country code"],
 	            	 
 	                 //  	    cols: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
            
 		                derivedAttributes: {
 	                        "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
 	                       "load date ":   dateFormat("load time", "%y-%m-%d", false)
 	                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
 	                    },
 	                  
 	                   vals: ["process name"],
 	                 
 	              	                 
 	            
                   aggregatorName: "Count",
                   rendererName: "Area Chart",
                   unusedAttrsVertical:false,
                   renderers: $.pivotUtilities.c3_renderers,
                   
 	           rendererOptions: {
 	        	   rendererName: {
 	                         clickCallback: function(e, value, filters, pivotData){
 	                        
 	                             var names = [];
 	                             pivotData.forEachMatchingRecord(filters,
 	                                 function(record){ names.push(record.Name); 
 	                             console.log(record.Name)  ;  
 	                             });
 	                             
 	                         }
 	                     },   plotly: {width: 1000, height: 300}

 	                 }
 	            
 	             });
        
      });
     
	  }
         
}, function errorCallback(response) {
	console.log("Unable to perform get request");
	//alert('error');
});

$http.get("/operation/t3summary").then(function successCallback(response) {
	

     var data = response.data;
  if (! jQuery.isEmptyObject(data)) {
	  var dateFormat =   $.pivotUtilities.derivers.dateFormat;
      var sortAs =  $.pivotUtilities.sortAs;
      var tpl =  $.pivotUtilities.aggregatorTemplates;
      var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
      var numberFormat = $.pivotUtilities.numberFormat;
      var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
       var intFormat = numberFormat({digitsAfterDecimal: 0});
   
       var floatFormat = numberFormat({digitsAfterDecimal: 2});
     
      var derivers = $.pivotUtilities.derivers;
    
     $(function(){
        
    	$("#T3Summary").pivotUI(data, {
                 cols: ["load time (HH:MM)"],
 		        //        rows: ["region","country code"],
 	            	 
 	                 //  	    cols: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
            
 		                derivedAttributes: {
 	                        "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
 	                       "load date ":   dateFormat("load time", "%y-%m-%d", false)
 	                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
 	                    },
 	                   unusedOrientationCutoff :"always horizontal",
 	                 
 	                  vals: ["process name"],
 	                aggregatorName: "Count",
                   rendererName: "Area Chart",
                   renderers: $.pivotUtilities.c3_renderers,
                   unusedAttrsVertical:false,
 	           rendererOptions: {
 	        	   rendererName: {
 	                         clickCallback: function(e, value, filters, pivotData){
 	                        
 	                             var names = [];
 	                             pivotData.forEachMatchingRecord(filters,
 	                                 function(record){ names.push(record.Name); });
 	                             
 	                         }
 	                     },   plotly: {width: 1000, height: 300}

 	                 }
 	             });
        
      });
     
  }
         
}, function errorCallback(response) {
	console.log("Unable to perform get request");
	//alert('error');
});

});