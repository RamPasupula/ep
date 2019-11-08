
// added for cluster utilization details
var app = angular.module('app');
app.controller('frrDailyController', function($scope, $rootScope, $stateParams,$state, LoginService, $http) {
	
	

	$http.get("/frr/daily/frrasiaheatmap").then(
		function successCallback(response) {
			

			$scope.t1 = response.data;
	
			var data = response.data;
			if (! jQuery.isEmptyObject(data)) {
			S			     var dateFormat =   $.pivotUtilities.derivers.dateFormat;
		        var sortAs =  $.pivotUtilities.sortAs;
		        var tpl =  $.pivotUtilities.aggregatorTemplates;
	            var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
		        var numberFormat = $.pivotUtilities.numberFormat;
		        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
		         var intFormat = numberFormat({digitsAfterDecimal: 0});
		     
		         var floatFormat = numberFormat({digitsAfterDecimal: 2});
		         var renderers = $.extend($.pivotUtilities.renderers, 
		                 $.pivotUtilities.d3_renderers);
		        var derivers = $.pivotUtilities.derivers;
		     
		       
		     $(function(){
		             $("#frrasiaheatmap").pivotUI(data, {
			                cols: ["hour"], 
			                rows: ["subject_area"],
			                derivedAttributes: {
		                    },
		                    vals: ["percentage"],
		                    aggregatorName: "List Unique Values",
		                    rendererName: "Heatmap",
		                   renderers: renderers,
		                    unusedAttrsVertical:false,
		                    hiddenAttributes:["category","hour","percentage"],
		                    rendererOptions: {
		                     plotly: {width: 700, height: 300},
		                     heatmap: {
		                            colorScaleGenerator: function(values) {
		                                // Plotly happens to come with d3 on board
		                                return Plotly.d3.scale.linear() 
		                                    .domain([ 0,25,50,75, 100])
		                                    .range(["#FC0", "#FF0","#FFC","#0B6","#0C1"])
		                            }
		                        }
		                 }
		             });
		      });
		}

		}, function errorCallback(response) {
			console.log("Unable to perform get request");
		//	alert('error');
		});
	

	$http.get("/frr/daily/frrasiaareamap").then(
			function successCallback(response) {
				

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
			                 $.pivotUtilities.d3_renderers);
			        var derivers = $.pivotUtilities.derivers;
			     
			       
			     $(function(){
			             $("#frrasiaareamap").pivotUI(data, {
				                cols: ["hour"], 
				                rows: ["category"],
				                derivedAttributes: {
			                      //  "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
			                     //   "load date ":   dateFormat("load time", "%y-%m-%d", false)
			                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
			                    },
			                    vals: ["percentage"],
			                   // aggregatorName: "List Unique Values",
			                    rendererName: "Area Chart",
			                    //renderers: renderers,
			                    unusedAttrsVertical:false,
			                    hiddenAttributes:["subject_area"],
			                    rendererOptions: {
			                     plotly: {width: 700, height: 300}
			                    /*
			                     ,heatmap: {
			                            colorScaleGenerator: function(values) {
			                                // Plotly happens to come with d3 on board
			                                return Plotly.d3.scale.linear() 
			                                    .domain([ 0,25,50,75, 100])
			                                    .range(["#FC0", "#FF0","#FFC","#0B6","#0C1"])
			                            }
			                        }
			                     */
			                 }
			             });
			      });
			}

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
			//	alert('error');
			});
	
	
	


function isEmpty(str) {
    return (!str || 0 === str.length);
}

function numberWithCommas(x) {
    x = x.toString();
    var pattern = /(-?\d+)(\d{3})/;
    while (pattern.test(x))
        x = x.replace(pattern, "$1,$2");
    return x;
}

});

