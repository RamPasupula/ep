var app = angular.module('app');

app.controller('systemController', function($scope, $rootScope, $stateParams,$state, LoginService,$http) {
	// alert('inside metadataController');
	$scope.headingTitle = "Source Systems";
	$http.get("/metadata/systems").then(function successCallback(response) {
		$scope.systems = response.data;
		$scope.systems.count = $scope.systems.length;
		// alert($scope.systems.count);
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});
});

app.controller('loginInfoController', function($scope, $rootScope, $stateParams,$state, LoginService) {
	$scope.headingTitle = "Discovery Tools - URL Details";
});




app.controller('serverController', function($scope, $rootScope, $stateParams,$state, LoginService,$http) {
	// alert('inside metadataController');
	$scope.headingTitle = "Server Details";
	$http.get("/environment/servers").then(function successCallback(response) {
		$scope.servers = response.data;
		$scope.servers.count = $scope.servers.length;
		// alert($scope.systems.count);
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});
});

app.controller(
				"jobController",
				function($scope, $rootScope, $stateParams,$state, LoginService, $http, $timeout) {
					$scope.headingTitle = "EDAG Job Statistics";

					$timeout(
							function() {
								// T11
								var t11CountryChart = dc.rowChart(
										'#t11-country-chart', 't11');
								var t11SystemChart = dc.rowChart(
										'#t11-system-chart', 't11');
								var t11DataCount = dc.dataCount(
										'#t11-data-count', 't11');
								var t11DataTable = dc.dataTable(
										'#t11-data-table', 't11');

								$scope.resetT11CountryChart = function() {
									t11CountryChart.filterAll('t11');
									dc.redrawAll('t11');
								}

								$scope.resetT11SystemChart = function() {
									t11SystemChart.filterAll('t11');
									dc.redrawAll('t11');
								}

								$scope.resetT11All = function() {
									dc.filterAll('t11');
									dc.renderAll('t11');
								}

								$http
										.get("/batch/dlJobs")
										.then(
												function successCallback(
														response) {
													var dlJobs = response.data;

													var t11Jobs = crossfilter(dlJobs);

													var t11All = t11Jobs
															.groupAll();

													var t11Country = t11Jobs
															.dimension(function(
																	d) {
																return d.LOAD_CTRY;
															});
													var t11CountByCountry = t11Country
															.group();
													var t11CountryChartHeight = t11CountByCountry
															.all().length * 30;

													var t11System = t11Jobs
															.dimension(function(
																	d) {
																return d.APP_CODE;
															});
													var t11CountBySystem = t11System
															.group();
													var t11SystemChartHeight = t11CountBySystem
															.all().length * 30;

													t11CountryChart
															.dimension(
																	t11Country)
															.group(
																	t11CountByCountry)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t11CountryChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t11SystemChart
															.dimension(
																	t11System)
															.group(
																	t11CountBySystem)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t11SystemChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t11DataCount.crossfilter(
															t11Jobs).groupAll(
															t11All);

													t11DataTable
															.dimension(
																	t11Country)
															.showSections(false)
															.columns(
																	[
																			'JOB_NAME',
																			'NODE_ID',
																			'JOB_STATUS',
																			'START_DATE',
																			'END_DATE',
																			'ELAPSED_TIME',
																			'APP_CODE',
																			'LOAD_CTRY',
																			'ODATE' ])
															.sortBy(
																	function(d) {
																		return d.END_DATE;
																	})
															.order(d3.ascending);

													dc.renderAll('t11');
												},
												function errorCallback(response) {
													console
															.log("Unable to perform get request");
													alert('error');
												});

								// T14
								var t14CountryChart = dc.rowChart(
										'#t14-country-chart', 't14');
								var t14SystemChart = dc.rowChart(
										'#t14-system-chart', 't14');
								var t14DataCount = dc.dataCount(
										'#t14-data-count', 't14');
								var t14DataTable = dc.dataTable(
										'#t14-data-table', 't14');

								$scope.resetT14CountryChart = function() {
									t14CountryChart.filterAll('t14');
									dc.redrawAll('t14');
								}

								$scope.resetT14SystemChart = function() {
									t14SystemChart.filterAll('t14');
									dc.redrawAll('t14');
								}

								$scope.resetT14All = function() {
									dc.filterAll('t14');
									dc.renderAll('t14');
								}

								// T15
								var t15CountryChart = dc.rowChart(
										'#t15-country-chart', 't15');
								var t15SystemChart = dc.rowChart(
										'#t15-system-chart', 't15');
								var t15DataCount = dc.dataCount(
										'#t15-data-count', 't15');
								var t15DataTable = dc.dataTable(
										'#t15-data-table', 't15');

								$scope.resett15CountryChart = function() {
									t15CountryChart.filterAll('t15');
									dc.redrawAll('t15');
								}

								$scope.resett15SystemChart = function() {
									t15SystemChart.filterAll('t15');
									dc.redrawAll('t15');
								}

								$scope.resett15All = function() {
									dc.filterAll('t15');
									dc.renderAll('t15');
								}

								// T20
								var t20CountryChart = dc.rowChart(
										'#t20-country-chart', 't20');
								var t20SystemChart = dc.rowChart(
										'#t20-system-chart', 't20');
								var t20DataCount = dc.dataCount(
										'#t20-data-count', 't20');
								var t20DataTable = dc.dataTable(
										'#t20-data-table', 't20');

								$scope.resett20CountryChart = function() {
									t20CountryChart.filterAll('t20');
									dc.redrawAll('t20');
								}

								$scope.resett20SystemChart = function() {
									t20SystemChart.filterAll('t20');
									dc.redrawAll('t20');
								}

								$scope.resett20All = function() {
									dc.filterAll('t20');
									dc.renderAll('t20');
								}

								// T30
								var t30CountryChart = dc.rowChart(
										'#t30-country-chart', 't30');
								var t30SystemChart = dc.rowChart(
										'#t30-system-chart', 't30');
								var t30DataCount = dc.dataCount(
										'#t30-data-count', 't30');
								var t30DataTable = dc.dataTable(
										'#t30-data-table', 't30');

								$scope.resett30CountryChart = function() {
									t30CountryChart.filterAll('t30');
									dc.redrawAll('t30');
								}

								$scope.resett30SystemChart = function() {
									t30SystemChart.filterAll('t30');
									dc.redrawAll('t30');
								}

								$scope.resett30All = function() {
									dc.filterAll('t30');
									dc.renderAll('t30');
								}

								$http
										.get("/batch/edwJobs")
										.then(
												function successCallback(
														response) {
													var edwJobs = response.data;

													// T14
													var t14Jobs = crossfilter(edwJobs
															.filter(function(n) {
																return n.TIER == "T14";
															}));

													var t14All = t14Jobs
															.groupAll();

													var t14Country = t14Jobs
															.dimension(function(
																	d) {
																return d.SITE_ID;
															});
													var t14CountByCountry = t14Country
															.group();
													var t14CountryChartHeight = t14CountByCountry
															.all().length * 30;

													var t14System = t14Jobs
															.dimension(function(
																	d) {
																return d.SRC_SYS_CD;
															});
													var t14CountBySystem = t14System
															.group();
													var t14SystemChartHeight = t14CountBySystem
															.all().length * 30;

													t14CountryChart
															.dimension(
																	t14Country)
															.group(
																	t14CountByCountry)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t14CountryChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t14SystemChart
															.dimension(
																	t14System)
															.group(
																	t14CountBySystem)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t14SystemChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t14DataCount.crossfilter(
															t14Jobs).groupAll(
															t14All);

													t14DataTable
															.dimension(
																	t14Country)
															.showSections(false)
															.columns(
																	[
																			'PROCESS_NAME',
																			'SITE_ID',
																			'SRC_SYS_CD',
																			'FREQ',
																			'BIZ_DT',
																			'JOB_START_DATETIME',
																			'JOB_END_DATETIME',
																			'PROCESS_FLG' ])
															.sortBy(
																	function(d) {
																		return d.JOB_END_DATETIME;
																	})
															.order(d3.ascending);

													dc.renderAll('t14');

													// T15
													var t15Jobs = crossfilter(edwJobs
															.filter(function(n) {
																return n.TIER == "T15";
															}));

													var t15All = t15Jobs
															.groupAll();

													var t15Country = t15Jobs
															.dimension(function(
																	d) {
																return d.SITE_ID;
															});
													var t15CountByCountry = t15Country
															.group();
													var t15CountryChartHeight = t15CountByCountry
															.all().length * 30;

													var t15System = t15Jobs
															.dimension(function(
																	d) {
																return d.SRC_SYS_CD;
															});
													var t15CountBySystem = t15System
															.group();
													var t15SystemChartHeight = t15CountBySystem
															.all().length * 30;

													t15CountryChart
															.dimension(
																	t15Country)
															.group(
																	t15CountByCountry)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t15CountryChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t15SystemChart
															.dimension(
																	t15System)
															.group(
																	t15CountBySystem)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t15SystemChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t15DataCount.crossfilter(
															t15Jobs).groupAll(
															t15All);

													t15DataTable
															.dimension(
																	t15Country)
															.showSections(false)
															.columns(
																	[
																			'PROCESS_NAME',
																			'SITE_ID',
																			'SRC_SYS_CD',
																			'FREQ',
																			'BIZ_DT',
																			'JOB_START_DATETIME',
																			'JOB_END_DATETIME',
																			'PROCESS_FLG' ])
															.sortBy(
																	function(d) {
																		return d.JOB_END_DATETIME;
																	})
															.order(d3.ascending);

													dc.renderAll('t15');

													// T20
													var t20Jobs = crossfilter(edwJobs
															.filter(function(n) {
																return n.TIER == "T20";
															}));

													var t20All = t20Jobs
															.groupAll();

													var t20Country = t20Jobs
															.dimension(function(
																	d) {
																return d.SITE_ID;
															});
													var t20CountByCountry = t20Country
															.group();
													var t20CountryChartHeight = t20CountByCountry
															.all().length * 30;

													var t20System = t20Jobs
															.dimension(function(
																	d) {
																return d.SRC_SYS_CD;
															});
													var t20CountBySystem = t20System
															.group();
													var t20SystemChartHeight = t20CountBySystem
															.all().length * 30;

													t20CountryChart
															.dimension(
																	t20Country)
															.group(
																	t20CountByCountry)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t20CountryChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t20SystemChart
															.dimension(
																	t20System)
															.group(
																	t20CountBySystem)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t20SystemChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t20DataCount.crossfilter(
															t20Jobs).groupAll(
															t20All);

													t20DataTable
															.dimension(
																	t20Country)
															.showSections(false)
															.columns(
																	[
																			'PROCESS_NAME',
																			'SITE_ID',
																			'SRC_SYS_CD',
																			'FREQ',
																			'BIZ_DT',
																			'JOB_START_DATETIME',
																			'JOB_END_DATETIME',
																			'PROCESS_FLG' ])
															.sortBy(
																	function(d) {
																		return d.JOB_END_DATETIME;
																	})
															.order(d3.ascending);

													dc.renderAll('t20');

													// T30
													var t30Jobs = crossfilter(edwJobs
															.filter(function(n) {
																return n.TIER == "T30";
															}));

													var t30All = t30Jobs
															.groupAll();

													var t30Country = t30Jobs
															.dimension(function(
																	d) {
																return d.SITE_ID;
															});
													var t30CountByCountry = t30Country
															.group();
													var t30CountryChartHeight = t30CountByCountry
															.all().length * 30;

													var t30System = t30Jobs
															.dimension(function(
																	d) {
																return d.SRC_SYS_CD;
															});
													var t30CountBySystem = t30System
															.group();
													var t30SystemChartHeight = t30CountBySystem
															.all().length * 30;

													t30CountryChart
															.dimension(
																	t30Country)
															.group(
																	t30CountByCountry)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t30CountryChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t30SystemChart
															.dimension(
																	t30System)
															.group(
																	t30CountBySystem)
															.label(function(d) {
																return d.key;
															})
															.title(function(d) {
																return d.value;
															})
															.width(1100)
															.height(
																	t30SystemChartHeight)
															.elasticX(true)
															.xAxis().ticks(4);

													t30DataCount.crossfilter(
															t30Jobs).groupAll(
															t30All);

													t30DataTable
															.dimension(
																	t30Country)
															.showSections(false)
															.columns(
																	[
																			'PROCESS_NAME',
																			'SITE_ID',
																			'SRC_SYS_CD',
																			'FREQ',
																			'BIZ_DT',
																			'JOB_START_DATETIME',
																			'JOB_END_DATETIME',
																			'PROCESS_FLG' ])
															.sortBy(
																	function(d) {
																		return d.JOB_END_DATETIME;
																	})
															.order(d3.ascending);

													dc.renderAll('t30');
												},
												function errorCallback(response) {
													console
															.log("Unable to perform get request");
												//	alert('error');
												});
							}, 500);
				});






app.controller('customerreport', function($scope, $rootScope, $stateParams,$state, LoginService, $http) {

	$scope.headingTitle = "Customer-Account Report";

	          
 

	$http.get("/customer/customer").then(
			function successCallback(response) {
				
				$scope.customer = response.data;
				var models = $scope.customer;
			

				$scope.account = response.data;

				var data = $scope.account;
				     var dateFormat =       $.pivotUtilities.derivers.dateFormat;
			        var sortAs =           $.pivotUtilities.sortAs;
			        var tpl =              $.pivotUtilities.aggregatorTemplates;
                    var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
			        var numberFormat = $.pivotUtilities.numberFormat;
			        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
			        
			        var intFormat = numberFormat({digitsAfterDecimal: 0});
			       // var dataClass = $.pivotUtilities.SubtotalPivotData;
			        var renderers = $.pivotUtilities.renderers;
			        var derivers = $.pivotUtilities.derivers;
			      
			       
			     $(function(){
			        
			             $("#custviz").pivotUI(data, {
				                cols: ["biz date"],
				                rows: ["country code","customer type"],
			               
				         
				                
			                 vals: ["customers"],
			                 renderers: $.extend(
			                	    	$.pivotUtilities.renderers, 
			                	        $.pivotUtilities.plotly_renderers,
			                	        $.pivotUtilities.export_renderers
			                	    )
			               ,
		 	                aggregators: {
			                	"Sum": function() { return tpl.sum(intFormat)(["customers"]) },
			                    "Count":      function() { return tpl.count()(["customers"]) },
			                    "Average": function() { return tpl.average()(["customers"])},
			                    "Minimum": function() { return tpl.min()(["customers"])},
			                    "Maximum": function() { return tpl.max()(["customers"])}
			                },
			                 rendererOptions: {
			                     table: {
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
				
			});
	      $http.get("/customer/account").then(
			function successCallback(response) {
				$scope.account = response.data;

				var data = $scope.account;
				//  google.charts.load("visualization", "1", {packages:["corechart", "charteditor"]});
				    var dateFormat =       $.pivotUtilities.derivers.dateFormat;
			        var sortAs =           $.pivotUtilities.sortAs;
			        var tpl =              $.pivotUtilities.aggregatorTemplates;
                   // var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
                  //  var monthAndDayDeriver = $.pivotUtilities.derivers.dateFormat("Name of Attribute", "%m/%d")
			      var numberFormat = $.pivotUtilities.numberFormat;
			        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
			        var sum = $.pivotUtilities.aggregatorTemplates.sum;
			 
			     //  var intFormat = numberFormat({ digitsAfterDecimal:0})
			      //  var fractionOfRowSum = $.pivotUtilities.aggregatorTemplates.fractionOf(sum(), 'row', intFormat)

			        var intFormat = numberFormat({digitsAfterDecimal: 0});
				       
			        var renderers = $.pivotUtilities.renderers;
			        var derivers = $.pivotUtilities.derivers;
			   
			       
			     $(function(){
			        
			    	   
			             $("#output").pivotUI(data, {
			            	 cols: ["biz date"],
				                rows: ["country code", "account type"],
				                
			                 vals: ["accounts"],
			                 renderers: $.extend(
			                	    	$.pivotUtilities.renderers, 
			                	        $.pivotUtilities.plotly_renderers,
			                	        $.pivotUtilities.export_renderers,
			                	        $.pivotUtilities.c3_renderers
			                	    )
			               ,
			               aggregators: {
			                	"Sum": function() { return tpl.sum(intFormat)(["accounts"]) },
			                    "Count":      function() { return tpl.count()(["accounts"]) },
			                    "Average": function() { return tpl.average()(["accounts"])},
			                    "Minimum": function() { return tpl.min()(["accounts"])},
			                    "Maximum": function() { return tpl.max()(["accounts"])}
			                },
			                 rendererOptions: {
			                     table: {
			                         clickCallback: function(e, value, filters, pivotData){
			                             var names = [];
			                             pivotData.forEachMatchingRecord(filters,
			                                 function(record){ names.push(record.Name); });
			                             
			                         }
			                     }, plotly: {width: 900, height: 650} 
			                 },  colorScaleGenerator: function(values) {
		                                 return Plotly.d3.scale.linear()
		                                    .domain([-35, 0, 35])
		                                    .range(["#77F", "#FFF", "#F77"])
		                            }
		                        
			             });
			        
			      });
			         


			
		

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
				
			});
	
});
