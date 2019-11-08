app.controller('homeController', function($scope) {
	$scope.headingTitle = "Home Page";
});

app.controller('systemController', function($scope, $http) {
	// alert('inside metadataController');
	$scope.headingTitle = "EDAG Source Systems";
	$http.get("/metadata/systems").then(function successCallback(response) {
		$scope.systems = response.data;
		$scope.systems.count = $scope.systems.length;
		// alert($scope.systems.count);
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		alert('error');
	});
});

app.controller('loginInfoController', function($scope, $http) {
	// alert('inside loginInfoController');
	$scope.headingTitle = "Tools - URL Details";
});


app.controller('fileController', function($scope, $http) {
	// alert('inside metadataController');
	$scope.headingTitle = "EDAG File Details";
	$http.get("/metadata/files").then(function successCallback(response) {
		$scope.files = response.data;
		$scope.files.count = $scope.files.length;
		// alert($scope.systems.count);
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		alert('error');
	});
});

app.controller('serverController', function($scope, $http) {
	// alert('inside metadataController');
	$scope.headingTitle = "EDAG Server Details";
	$http.get("/environment/servers").then(function successCallback(response) {
		$scope.servers = response.data;
		$scope.servers.count = $scope.servers.length;
		// alert($scope.systems.count);
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		alert('error');
	});
});

app
		.controller(
				"jobController",
				function($scope, $http, $timeout) {
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
													alert('error');
												});
							}, 500);
				});


//load status
app.controller('loadStatusController', function($scope, $http) {
	$scope.headingTitle = "EDAG Batch Status";
	$http.get("/operation/t1").then(function successCallback(response) {
		$scope.t1 = response.data;
		$scope.t1.count = $scope.t1.length;
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		alert('error');
	});
	$http.get("/operation/t2").then(function successCallback(response) {
		$scope.t2 = response.data;
		$scope.t2.count = $scope.t2.length;
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		alert('error');
	});
	$http.get("/operation/t3").then(function successCallback(response) {
		$scope.t3 = response.data;
		$scope.t3.count = $scope.t3.length;
		// alert(JSON.stringify($scope.systems));
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		alert('error');
	});
});


// added for cluster utilization details

app.controller('clusterController', function($scope, $http) {
	// D3 generic
	var margin = {
		top : 25,
		right : 20,
		bottom : 80,
		left : 80
	}, width = 700 - margin.left - margin.right, height = 450 - margin.top
			- margin.bottom;

	var paddingForText = 3, radius = 6;

	// Parse the date / time
	// var parseDate = d3.timeParse("%d-%b-%y");
	var parseDate = d3.timeParse("%Y-%m-%d");

	// Set the ranges
	var x = d3.scaleTime().range([ 0, width ]);
	var y = d3.scaleLinear().range([ height, 0 ]);

	// We're passing in a function in d3.max to tell it what we're maxing (x
	// value)
	var xScale = d3.scale.linear().domain([ 0, width ]).range(
			[ margin.left, width - margin.right ]); // Set margins for x
	// specific

	// We're passing in a function in d3.max to tell it what we're maxing (y
	// value)
	var yScale = d3.scale.linear().domain([ 0, height ]).range(
			[ margin.top, height - margin.bottom ]); // Set margins for y
	// specific

	// Define the axes
	var xAxis = d3.axisBottom(x).ticks(3);
	// axis.tickArguments([d3.timeMinute.every(15)]);

	var yAxis = d3.axisLeft(y).ticks(10);

	// D3 generic ends

	$scope.headingTitle = "Cluster utilisation details";
	$http.get("/cluster/memory").then(
			function successCallback(response) {
				$scope.memory = response.data;

				var data = $scope.memory;
				// D3 starts here

				// Set the dimensions of the canvas / graph

				// Define the line
				var valueline = d3.line().x(function(d) {
					return x(d.date);
				}).y(function(d) {
					return y(d.value);
				});

				// Adds the svg canvas
				var svg = d3.select("#dataviz").append("svg").attr("width",
						width + margin.left + margin.right).attr("height",
						height + margin.top + margin.bottom).append("g").attr(
						"transform",
						"translate(" + margin.left + "," + margin.top + ")");

				var div = d3.select("#dataviz").append("div").attr("class",
						"tooltip").style("opacity", 0);

				// Get the data
				data.forEach(function(d) {
					d.date = parseDate(d.rundate);
					d.value = +d.value;
				});

				// Scale the range of the data
				x.domain(d3.extent(data, function(d) {
					return d.date;
				}));
				y.domain([ 0, d3.max(data, function(d) {
					return d.value;
				}) ]);

				// Add the valueline path.
				svg.append("path").data([ data ]).attr("class", "line").attr(
						"d", valueline);

				var cir = svg.append("g").selectAll("circle").data(data)
						.enter().append("circle").attr("r", 5);
				cir.attr("cx", function(d) {
					return x(d.date)
				}).attr("cy", function(d) {
					return y(d.value)
				}).attr("fill", "green").attr("stroke", "steelblue").on(
						"mouseover",
						function(d) {

							div.transition().duration(100).style("opacity", 1)
							div.html(
									"<p> date: " + d.rundate
											+ "</p> <p>value: " + d.value
											+ "</p>").style("left",
									d3.mouse(this)[0] +160 + "px").style("top",
									d3.mouse(this)[1]  + "px");

						}).on("mouseout", function(d) {		
			            div.transition().duration(1000).style("opacity", 0.01);	
			        }).on("mousemove", function(d) {
 
						div.transition().duration(30).style("opacity", 1)
						div.html(
								"<p> date: " + d.rundate
										+ "</p> <p>value: " + d.value
										+ "</p>").style("left",
								d3.mouse(this)[0] +160 + "px").style("top",
								d3.mouse(this)[1]  + "px");

					}) ;
				
				
				

				// Add the X Axis
				svg.append("g").attr("class", "x axis").attr("transform",
						"translate(0," + height + ")")
				// .attr("transform", "rotate(-65)")
				.call(xAxis.ticks(d3.timeDay.every(3))).selectAll("text")
						.style("text-anchor", "end");

				// Add the Y Axis
				svg.append("g").attr("class", "y axis").call(yAxis);
				
				 // Add the x Axis
			/*	  svg.append("g")
				      .attr("transform", "translate(0," + height + ")")
				      .call(d3.axisBottom(x));
				      */

				  // text label for the x axis
				  svg.append("text")             
				      .attr("transform",
				            "translate(" + (width/2) + " ," + 
				                           (height + margin.top + 20) + ")")
				      .style("text-anchor", "middle")
				      .text("Date");

				  // Add the y Axis
				  svg.append("g") .call(d3.axisLeft(y));

				  // text label for the y axis
				  svg.append("text")
				      .attr("transform", "rotate(-90)")
				      .attr("y", 0 - margin.left)
				      .attr("x",0 - (height / 2))
				      .attr("dy", "1em")
				      .style("text-anchor", "middle")
				      .text("Value"); 
				
				
				
				
				

				// D3 ends here

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
				alert('error');
			});
	$http.get("/cluster/cpu").then(
			function successCallback(response) {
				$scope.cpu = response.data;

				var data = $scope.cpu;
				// D3 starts here

				// Define the line
				var valueline = d3.line().x(function(d) {
					return x(d.date);
				}).y(function(d) {
					return y(d.value);
				});

				// Adds the svg canvas
				var svg = d3.select("#cpuviz").append("svg").attr("width",
						width + margin.left + margin.right).attr("height",
						height + margin.top + margin.bottom).append("g").attr(
						"transform",
						"translate(" + margin.left + "," + margin.top + ")");
				var div = d3.select("#cpuviz").append("div").attr("class",
						"tooltip").style("opacity", 0);
				// Get the data
				data.forEach(function(d) {
					d.date = parseDate(d.rundate);
					d.value = +d.value;
				});

				// Scale the range of the data
				x.domain(d3.extent(data, function(d) {
					return d.date;
				}));
				y.domain([ 0, d3.max(data, function(d) {
					return d.value;
				}) ]);

				// Add the valueline path.
				svg.append("path").data([ data ]).attr("class", "line").attr(
						"d", valueline);

				var cir = svg.append("g").selectAll("circle").data(data)
						.enter().append("circle").attr("r", 5);

				cir.attr("cx", function(d) {
					return x(d.date)
				}).attr("cy", function(d) {
					return y(d.value)
				}).attr("fill", "green").attr("stroke", "steelblue")
				  .on(
						"mouseover",
						function(d) {

							div.transition().duration(100).style("opacity", 1)
							div.html(
									"<p> date: " + d.rundate
											+ "</p> <p>value: " + d.value
											+ "</p>").style("left",
									d3.mouse(this)[0] +160 + "px").style("top",
									d3.mouse(this)[1] + "px");

						}).on("mouseout", function(d) {		
			            div.transition().duration(1000).style("opacity", 0.01);	
			        }).on("mousemove", function(d) {

						div.transition().duration(30).style("opacity", 1)
						div.html(
								"<p> date: " + d.rundate
										+ "</p> <p>value: " + d.value
										+ "</p>").style("left",
								d3.mouse(this)[0] +160+ "px").style("top",
								d3.mouse(this)[1]  + "px");

					}) ;
				svg.append("g").attr("class", "x axis").attr("transform",
						"translate(0," + height + ")")
				// .call(xAxis)
				.call(xAxis.ticks(d3.timeDay.every(3))).selectAll("text")
						.style('text-anchor', 'end');
						// .attr("dx", "-.8em")
					//	.attr("dy", ".15em").attr("transform", "rotate(-65)");
				// Add the Y Axis
				svg.append("g").attr("class", "y axis").call(yAxis);
				
				
				
				 // text label for the x axis
				  svg.append("text")             
				      .attr("transform",
				            "translate(" + (width/2) + " ," + 
				                           (height + margin.top + 20) + ")")
				      .style("text-anchor", "middle")
				      .text("Date");

				  // Add the y Axis
				  svg.append("g") .call(d3.axisLeft(y));

				  // text label for the y axis
				  svg.append("text")
				      .attr("transform", "rotate(-90)")
				      .attr("y", 0 - margin.left)
				      .attr("x",0 - (height / 2))
				      .attr("dy", "1em")
				      .style("text-anchor", "middle")
				      .text("Value"); 
				
				

				// D3 ends here

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
				alert('error');
			});
	$http.get("/cluster/io").then(
			function successCallback(response) {
				$scope.io = response.data;
				var data = $scope.io;

				// var parseDate = d3.time.format("%d-%b-%y").parse;
				var parseDate = d3.timeParse("%Y-%m-%d");
				

				var area = d3.svg.area().x(function(d) {
					return x(d.date);
				}).y0(height).y1(function(d) {
					return y(d.value);
				});

				var svg = d3.select("#ioviz").append("svg").attr("width",
						width + margin.left + margin.right).attr("height",
						height + margin.top + margin.bottom).append("g").attr(
						"transform",
						"translate(" + margin.left + "," + margin.top + ")");

				
				
				data.forEach(function(d) {
					d.date = parseDate(d.rundate);
					d.value = +d.value;
				});

				x.domain(d3.extent(data, function(d) {
					return d.date;
				}));
				y.domain([ 0, d3.max(data, function(d) {
					return d.value;
				}) ]);

				var div = d3.select("#ioviz").append("div").attr("class",
				"tooltip").style("opacity", 0);
				
				var cir = svg.append("g").selectAll("circle").data(data)
				.enter().append("circle").attr("r", 5);

		cir.attr("cx", function(d) {
			return x(d.date)
		}).attr("cy", function(d) {
			return y(d.value)
		}).attr("fill", "green").attr("stroke", "steelblue")
		  .on(
						"mouseover",
						function(d) {

							div.transition().duration(100).style("opacity", 1)
							div.html(
									"<p> date: " + d.rundate
											+ "</p> <p>value: " + d.value
											+ "</p>").style("left",
									d3.mouse(this)[0] +160 + "px").style("top",
									d3.mouse(this)[1]  + "px");

						}).on("mouseout", function(d) {		
			            div.transition().duration(1000).style("opacity", 0.01);	
			        }).on("mousemove", function(d) {

						div.transition().duration(30).style("opacity", 1)
						div.html(
								"<p> date: " + d.rundate
										+ "</p> <p>value: " + d.value
										+ "</p>").style("left",
								d3.mouse(this)[0] +160 + "px").style("top",
								d3.mouse(this)[1]  + "px");

					}) ;
				svg.append("path").datum(data).attr("class", "area")
						.attr("d", area) ;
				svg.append("g").attr("class", "x axis").attr("transform",
						"translate(0," + height + ")").call(xAxis).append("text");

				svg.append("g").attr("class", "y axis").call(yAxis).append(
						"text");
				
				 // text label for the x axis
				  svg.append("text")             
				      .attr("transform",
				            "translate(" + (width/2) + " ," + 
				                           (height + margin.top + 20) + ")")
				      .style("text-anchor", "middle")
				      .text("Date");

				  // Add the y Axis
				  svg.append("g") .call(d3.axisLeft(y));

				  // text label for the y axis
				  svg.append("text")
				      .attr("transform", "rotate(-90)")
				      .attr("y", 0 - margin.left)
				      .attr("x",0 - (height / 2))
				      .attr("dy", "1em")
				      .style("text-anchor", "middle")
				      .text("Value"); 
				
				

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
				alert('error');
			});
});


app.controller('customerreport', function($scope, $http) {

	$scope.headingTitle = "Customer-Account Report";
	// D3 generic
	
	
	// set the dimensions and margins of the graph
	var margin = {top: 20, right: 20, bottom: 30, left: 40},
	    width = 650 - margin.left - margin.right,
	    height = 400 - margin.top - margin.bottom;

	var paddingForText = 3, radius = 6;


	// set the ranges
	var x = d3.scaleBand()
	          .range([0, width])
	          .padding(0.1);
	var y = d3.scaleLinear()
	          .range([height, 0]);
	          
	
	var parseDate = d3.timeParse("%Y-%m-%d");
	
	// D3 generic ends

	$http.get("/customer/customer").then(
			function successCallback(response) {
				
				$scope.customer = response.data;
				var models = $scope.customer;
			
				  models = models.map(i => {
					  i.model_name = i.date;
						return i;
					});

					var container = d3.select('#custviz'),
					    width = 700,
					    height = 500,
					    margin = {top: 80, right: 20, bottom: 80, left: 80},
					    barPadding = .2,
					    axisTicks = {qty: 5, outerSize: 0, dateFormat: '%m-%d'};

					var svg = container
					   .append("svg")
					   .attr("width", width)
					   .attr("height", height)
					   .append("g")
					   .attr("transform", `translate(${margin.left},${margin.top})`);

					var div = d3.select("#custviz").append("div").attr("class",
					"tooltip").style("opacity", 0);
					var xScale0 = d3.scaleBand().range([0, width - margin.left - margin.right]).padding(barPadding);
					var xScale1 = d3.scaleBand();
					var yScale = d3.scaleLinear().range([height - margin.top - margin.bottom, 0]);

					var xAxis = d3.axisBottom(xScale0).tickSizeOuter(axisTicks.outerSize);
					var yAxis = d3.axisLeft(yScale).ticks(axisTicks.qty).tickSizeOuter(axisTicks.outerSize);

					xScale0.domain(models.map(d => d.model_name));
					xScale1.domain(['field1', 'field2']).range([0, xScale0.bandwidth()]);
					yScale.domain([0, d3.max(models, d => d.valueI > d.valueC ? d.valueI : d.valueC)]);

					var model_name = svg.selectAll(".model_name")
					  .data(models)
					  .enter().append("g")
					  .attr("class", "model_name")
					  .attr("transform", d => `translate(${xScale0(d.model_name)},0)`);

					/* Add field1 bars */
				var bar1 =	model_name.selectAll(".bar.field1")
					  .data(d => [d])
					  .enter()
					  .append("rect")
					  .attr("class", "bar field1")
					  .style("fill","red")
					  .attr("x", d => xScale1('field1'))
					  .attr("y", d => yScale(d.valueI))
					  .attr("width", xScale1.bandwidth())
					  .attr("height", d => {
					    return height - margin.top - margin.bottom - yScale(d.valueI)
					  }) .on('mouseenter', function (s, i) {
						    d3.select(this)
					        .transition()
					        .duration(50)
					        .attr('opacity', 0.6);


		                	
                  })
                   .on('mouseout', function (s, i) {
						    d3.select(this)
					        .transition()
					        .duration(50)
					        .attr('opacity', 1);
					        div.transition().duration(500).style("opacity", 0.01);	
                  }) .on("mousemove", function(d){

                	  div.transition().duration(50).style("opacity", 1)
						div.html(
								"<p> date: " + d.model_name
										+ "</p> <p>value: " + d.valueI
										+ "</p>").style("left",
												d3.event.pageX - 250+ "px").style("top",
														d3.event.pageY - 300 + "px");
            
             });
							
					  
				model_name.selectAll(".text")
				  .data(d => [d])
				  .enter()
				  .append("text")
				  .attr("class","label")
				 .attr("x", d => xScale1('field1'))
					  .attr("y", d => yScale(d.valueI)- 10)
				  .attr("dy", ".75em")
				  .attr("font-size","8px")
				  .text(function(d) { return d.valueI; });   
				
				
				
					/* Add field2 bars */
					model_name.selectAll(".bar.field2")
					  .data(d => [d])
					  .enter()
					  .append("rect")
					  .attr("class", "bar field2")
					 .style("fill","blue")
					  .attr("x", d => xScale1('field2'))
					  .attr("y", d => yScale(d.valueC))
					  .attr("width", xScale1.bandwidth())
					  .attr("height", d => {
					    return height - margin.top - margin.bottom - yScale(d.valueC)
					  })
					  .on('mouseenter', function (s, i) {
						  d3.select(this)
						  .transition()
						  .duration(50)
						  .attr('opacity', 0.6)
						  .attr('x', (a) => xScale(a.model_name) - 5)
						  .attr('width', xScale.bandwidth() + 10)
					 }).on('mouseout', function (s, i) {
						    d3.select(this)
					        .transition()
					        .duration(50)
					        .attr('opacity', 1);
					        div.transition().duration(500).style("opacity", 0.01);	
                  }) .on("mousemove", function(d){

                	  div.transition().duration(50).style("opacity", 1)
						div.html(
								"<p> date: " + d.model_name
										+ "</p> <p>value: " + d.valueC
										+ "</p>").style("left",
												d3.event.pageX - 250+ "px").style("top",
														d3.event.pageY - 300 + "px");
            
                	  
           
            });
		
					model_name.selectAll(".text")
					  .data(d => [d])
					  .enter()
					  .append("text")
					  .attr("class","label")
					 .attr("x", d => xScale1('field2'))
						  .attr("y", d => yScale(d.valueC) - 10)
					  .attr("dy", ".75em")
					  .attr("font-size","8px")
					  .text(function(d) { return d.valueC; }); 
					 
					// Add the X Axis
					svg.append("g")
					   .attr("class", "x axis")
					   .attr("transform", `translate(0,${height - margin.top - margin.bottom})`)
					   .call(xAxis);

					// Add the Y Axis
					svg.append("g")
					   .attr("class", "y axis")
					   .call(yAxis); 
				
				
					
					svg.selectAll(".text")        
					  .data(models)
					  .enter()
					  .append("text")
					  .attr("class","label")
					  .attr("x", (function(d) { return x(d.model_name); }  ))
					  .attr("y", function(d) { return y(d => d.valueI > d.valueC ? d.valueI : d.valueC) - 20; }) ;
					
					
					//Legends
					 var COLORS = ["red", "blue"],
			            LABELS = ["Individual", "Corporate"],VALUES = ["Individual", "Corporate"];
					 var color = d3.scale.ordinal().range(COLORS);
					var legspacing = 25;

		            var legend = svg.selectAll(".legend")
		                .data(VALUES)
		                .enter()
		                .append("g")

		            legend.append("rect")
		                .attr("fill", color)
		                .attr("width", 20)
		                .attr("height", 20)
		                .attr("y", function (d, i) {
		                    return i * legspacing - 60;
		                })
		                .attr("x", 0);

		            legend.append("text")
		                .attr("class", "label")
		                .attr("y", function (d, i) {
		                    return i * legspacing - 46;
		                })
		                .attr("x", 30)
		                .attr("text-anchor", "start")
		                .text(function (d, i) {
		                    return LABELS[i];
		                });
					
					
				
				// D3 ends here

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
				alert('error');
			});
	$http.get("/customer/account").then(
			function successCallback(response) {
				$scope.account = response.data;

				var data = $scope.account;
				//  google.charts.load("visualization", "1", {packages:["corechart", "charteditor"]});
				/* var dateFormat =       $.pivotUtilities.derivers.dateFormat;
			        var sortAs =           $.pivotUtilities.sortAs;
			        var tpl =              $.pivotUtilities.aggregatorTemplates;
                    var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
			        var numberFormat = $.pivotUtilities.numberFormat;
			        var frFormat = numberFormat({thousandsSep:" ", decimalSep:","});
			       // var dataClass = $.pivotUtilities.SubtotalPivotData;
			        var renderers = $.pivotUtilities.renderers;
			        var derivers = $.pivotUtilities.derivers;
			        */
			       
			     $(function(){
			        
			             $("#output").pivotUI(data, {
				                cols: ["type"],
				                rows: ["bizdate"],
			               
				              
			                /* aggregators: {
				                	"Sum": function() { return tpl.sum()(["value"]) },
				                    "Count":      function() { return tpl.count()(["value"]) },
				                    "Average": function() { return tpl.average()(["value"])},
				                    "Minimum": function() { return tpl.min()(["value"])},
				                    "Maximum": function() { return tpl.max()(["value"])}
				                },
				                */

			                 vals: ["value"],
			                 renderers: $.extend(
			                	    	$.pivotUtilities.renderers, 
			                	        $.pivotUtilities.plotly_renderers,
			                	        $.pivotUtilities.export_renderers
			                	    )
			               ,
			             
			                 rendererOptions: {
			                     table: {
			                         clickCallback: function(e, value, filters, pivotData){
			                             var names = [];
			                             pivotData.forEachMatchingRecord(filters,
			                                 function(record){ names.push(record.Name); });
			                             
			                         }
			                     }, plotly: {width: 1000, height: 600} 
			                 }
			             });
			        
			      });
			         


			
		

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
				alert('error');
			});
	
});
