// added for cluster utilization details
var app = angular.module('app');
app.controller('loadFRRController',
				function($scope, $rootScope, $stateParams,$state, LoginService, $http) {
					var biz_date = "";

					google.load("visualization", "1", {
						packages : [ "corechart", "charteditor" ]
					});
					$scope.headingTitle = "EDW/FRR Batch Status";

					var margin = {
						top : 20,
						right : 160,
						bottom : 60,
						left : 60
					};

					var width = 650 - margin.left - margin.right, height = 400
							- margin.top - margin.bottom;

					/* Data in strings like it would be if imported from a csv */

					var parse = d3.time.format("%Y").parse;

					var dateFormat = $.pivotUtilities.derivers.dateFormat;
					var renderers = $.extend($.pivotUtilities.renderers,
							$.pivotUtilities.d3_renderers);
					var derivers = $.pivotUtilities.derivers;
					var numberFormat = $.pivotUtilities.numberFormat;
					var frFormat = numberFormat({
						thousandsSep : " ",
						decimalSep : ","
					});
					var intFormat = numberFormat({
						digitsAfterDecimal : 0
					});

					var floatFormat = numberFormat({
						digitsAfterDecimal : 2
					});
					/*
					 * 
					 * 
					 * var colors = [ "#2DA2DC", "#2ECC71", "#d25c4d"
					 * ,"#FFA500"]; $http.get("/frr/daily/t20").then( function
					 * successCallback(response) { $scope.frrd20 =
					 * response.data; var data = response.data;
					 * 
					 * 
					 * var svg = d3.select("#frrdaily") .append("svg")
					 * .attr("width", width + margin.left + margin.right)
					 * .attr("height", height + margin.top + margin.bottom)
					 * .append("g") .attr("transform", "translate(" +
					 * margin.left + "," + margin.top + ")");
					 * 
					 *  // Transpose the data into layers var dataset =
					 * d3.layout.stack()(["running","pending","completed","failed"
					 * ].map(function(status) { return data.map(function(d) {
					 * return {x: d.name, y: +d[status]}; }); }));
					 *  // Set x, y and colors var x = d3.scale.ordinal()
					 * .domain(dataset[0].map(function(d) { return d.x; }))
					 * .rangeRoundBands([10, width-10], 0.02);
					 * 
					 * var y = d3.scale.linear() .domain([0, d3.max(dataset,
					 * function(d) { return d3.max(d, function(d) { return d.y0 +
					 * d.y; }); })]) .range([height, 0]);
					 * 
					 *  // Define and draw axes var yAxis = d3.svg.axis()
					 * .scale(y) .orient("left") .ticks(5) .tickSize(-width, 0,
					 * 0) .tickFormat( function(d) { return d } );
					 * 
					 * var xAxis = d3.svg.axis() .scale(x) .orient("bottom")
					 * .tickFormat(function(d) { return d });
					 * 
					 * svg.append("g") .attr("class", "y axis") .call(yAxis);
					 * 
					 * svg.append("g") .attr("class", "x axis")
					 * .attr("transform", "translate(0," + height + ")")
					 * .call(xAxis);
					 * 
					 *  // Create groups for each series, rects for each segment
					 * var groups = svg.selectAll("g.cost") .data(dataset)
					 * .enter().append("g") .attr("class", "cost")
					 * .style("fill", function(d, i) { return colors[i]; });
					 * 
					 * var rect = groups.selectAll("rect") .data(function(d) {
					 * return d; }) .enter() .append("rect") .attr("x",
					 * function(d) { return x(d.x); }) .attr("y", function(d) {
					 * return y(d.y0 + d.y); }) .attr("height", function(d) {
					 * return y(d.y0) - y(d.y0 + d.y); }) .attr("width",
					 * x.rangeBand()) .on("mouseover", function() {
					 * tooltip.style("display", null); }) .on("mouseout",
					 * function() { tooltip.style("display", "none"); })
					 * .on("mousemove", function(d) { var xPosition =
					 * d3.mouse(this)[0] - 15; var yPosition = d3.mouse(this)[1] -
					 * 25; tooltip.attr("transform", "translate(" + xPosition +
					 * "," + yPosition + ")"); tooltip.select("text").text(d.y);
					 * });
					 * 
					 *  // Draw legend var legend = svg.selectAll(".legend")
					 * .data(colors) .enter().append("g") .attr("class",
					 * "legend") .attr("transform", function(d, i) { return
					 * "translate(30," + i * 19 + ")"; });
					 * 
					 * legend.append("rect") .attr("x", width - 18)
					 * .attr("width", 18) .attr("height", 18) .style("fill",
					 * function(d, i) {return colors.slice().reverse()[i];});
					 * 
					 * legend.append("text") .attr("x", width + 5) .attr("y", 9)
					 * .attr("dy", ".35em") .style("text-anchor", "start")
					 * .text(function(d, i) { switch (i) { case 0: return
					 * "Failed"; case 1: return "Completed"; case 2: return
					 * "Pending"; case 3: return "Running"; } });
					 *  // Prep the tooltip bits, initial display is hidden var
					 * tooltip = svg.append("g") .attr("class", "tooltip")
					 * .style("display", "none");
					 * 
					 * tooltip.append("rect") .attr("width", 30) .attr("height",
					 * 20) .attr("fill", "white") .style("opacity", 0.5);
					 * 
					 * tooltip.append("text") .attr("x", 15) .attr("dy",
					 * "1.2em") .style("text-anchor", "middle")
					 * .attr("font-size", "12px") .attr("font-weight", "bold");
					 * 
					 * 
					 * 
					 *  }, function errorCallback(response) {
					 * console.log("Unable to perform get request"); //
					 * alert('error'); }); $http.get("/frr/daily/t30").then(
					 * function successCallback(response) {}, function
					 * errorCallback(response) { console.log("Unable to perform
					 * get request"); // alert('error'); });
					 * $http.get("/frr/monthly/t20").then( function
					 * successCallback(response) { $scope.frrm20 =
					 * response.data; var data = $scope.frrm20;
					 * 
					 * 
					 * 
					 * var svg = d3.select("#frrmonthly") .append("svg")
					 * .attr("width", width + margin.left + margin.right)
					 * .attr("height", height + margin.top + margin.bottom)
					 * .append("g") .attr("transform", "translate(" +
					 * margin.left + "," + margin.top + ")");
					 * 
					 *  // Transpose the data into layers var dataset =
					 * d3.layout.stack()(["running","pending","completed","failed"
					 * ].map(function(status) { return data.map(function(d) {
					 * return {x: d.name, y: +d[status]}; }); }));
					 * 
					 *  // Set x, y and colors var x = d3.scale.ordinal()
					 * .domain(dataset[0].map(function(d) { return d.x; }))
					 * .rangeRoundBands([10, width-10], 0.02);
					 * 
					 * var y = d3.scale.linear() .domain([0, d3.max(dataset,
					 * function(d) { return d3.max(d, function(d) { return d.y0 +
					 * d.y; }); })]) .range([height, 0]);
					 * 
					 * 
					 *  // Define and draw axes var yAxis = d3.svg.axis()
					 * .scale(y) .orient("left") .ticks(5) .tickSize(-width, 0,
					 * 0) .tickFormat( function(d) { return d } );
					 * 
					 * var xAxis = d3.svg.axis() .scale(x) .orient("bottom")
					 * .tickFormat(function(d) { return d });
					 * 
					 * svg.append("g") .attr("class", "y axis") .call(yAxis);
					 * 
					 * svg.append("g") .attr("class", "x axis")
					 * .attr("transform", "translate(0," + height + ")")
					 * .call(xAxis);
					 * 
					 *  // Create groups for each series, rects for each segment
					 * var groups = svg.selectAll("g.tot") .data(dataset)
					 * .enter().append("g") .attr("class", "tot") .style("fill",
					 * function(d, i) { return colors[i]; });
					 * 
					 * var rect = groups.selectAll("rect") .data(function(d) {
					 * return d; }) .enter() .append("rect") .attr("x",
					 * function(d) { return x(d.x); }) .attr("y", function(d) {
					 * return y(d.y0 + d.y); }) .attr("height", function(d) {
					 * return y(d.y0) - y(d.y0 + d.y); }) .attr("width",
					 * x.rangeBand()) .on("mouseover", function(d) { var
					 * xPosition = d3.mouse(this)[0] - 15; var yPosition =
					 * d3.mouse(this)[1] - 25; tooltip.attr("transform",
					 * "translate(" + xPosition + "," + yPosition + ")");
					 * tooltip.select("text").text(d.y); }) .on("mouseout",
					 * function() { tooltip.style("display", "none"); })
					 * .on("mousemove", function(d) { var xPosition =
					 * d3.mouse(this)[0] - 15; var yPosition = d3.mouse(this)[1] -
					 * 25; tooltip.attr("transform", "translate(" + xPosition +
					 * "," + yPosition + ")"); tooltip.select("text").text(d.y);
					 * });
					 * 
					 *  // Draw legend var legend = svg.selectAll(".legend")
					 * .data(colors) .enter().append("g") .attr("class",
					 * "legend") .attr("transform", function(d, i) { return
					 * "translate(30," + i * 19 + ")"; });
					 * 
					 * legend.append("rect") .attr("x", width - 18)
					 * .attr("width", 18) .attr("height", 18) .style("fill",
					 * function(d, i) {return colors.slice().reverse()[i];});
					 * 
					 * legend.append("text") .attr("x", width + 5) .attr("y", 9)
					 * .attr("dy", ".35em") .style("text-anchor", "start")
					 * .text(function(d, i) { switch (i) { case 0: return
					 * "Failed"; case 1: return "Completed"; case 2: return
					 * "Pending"; case 3: return "Running"; } });
					 *  // Prep the tooltip bits, initial display is hidden var
					 * tooltip = svg.append("g") .attr("class", "tooltip")
					 * .style("display", "none");
					 * 
					 * tooltip.append("rect") .attr("width", 30) .attr("height",
					 * 20) .attr("fill", "white") .style("opacity", 0.5);
					 * 
					 * tooltip.append("text") .attr("x", 15) .attr("dy",
					 * "1.2em") .style("text-anchor", "middle")
					 * .attr("font-size", "12px") .attr("font-weight", "bold");
					 *  }, function errorCallback(response) {
					 * console.log("Unable to perform get request"); //
					 * alert('error'); });
					 * 
					 * 
					 * 
					 * $http.get("/frr/monthly/t30").then( function
					 * successCallback(response) {}, function
					 * errorCallback(response) { console.log("Unable to perform
					 * get request");
					 * 
					 * });
					 * 
					 */

					$http.get("/frr/daily2/t20").then(
									function successCallback(response) {

										$scope.t1 = response.data;

										var data = response.data;
										if (!jQuery.isEmptyObject(data)) {
											$scope.bizDate = response.data[0].biz_dt;
										

											biz_date = $scope.bizDate;
											$scope.t1.count = $scope.t1.length;
											var dateFormat = $.pivotUtilities.derivers.dateFormat;
											var sortAs = $.pivotUtilities.sortAs;
											var tpl = $.pivotUtilities.aggregatorTemplates;
											var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
											var numberFormat = $.pivotUtilities.numberFormat;
											var frFormat = numberFormat({
												thousandsSep : " ",
												decimalSep : ","
											});
											var intFormat = numberFormat({
												digitsAfterDecimal : 0
											});

											var floatFormat = numberFormat({
												digitsAfterDecimal : 2
											});
											var renderers = $.extend(
															$.pivotUtilities.renderers,
															$.pivotUtilities.plotly_renderers,
															$.pivotUtilities.export_renderers,
															$.pivotUtilities.d3_renderers);
											var derivers = $.pivotUtilities.derivers;

											$(function() {
												$("#frrdaily2")
														.pivotUI(
																data,
																{
																	cols : [ "country code" ],
																	rows : [ "source system" ],
																	derivedAttributes : {

																		"tim" : dateFormat(
																				"etime",
																				"%H:%M",
																				false)

																	},
																	vals : [ "completed %" ],
																	aggregatorName : "List Unique Values",
																	rendererName : "Heatmap",
																	unusedAttrsVertical : false,
																	hiddenAttributes : [
																			"tier",
																			"total %",
																			"pending",
																			"completed",
																			"total process",
																			"pending %" ],
																	rendererOptions : {
																		rendererName : {

																		},
																		plotly : {
																			width : 700,
																			height : 300
																		},
																		heatmap : {
																			colorScaleGenerator : function(
																					values) {
																				
																				return Plotly.d3.scale
																						.linear()
																						.domain(
																								[
																										0,
																										25,
																										50,
																										75,
																										100 ])
																						.range(
																								[
																										"#FC0",
																										"#FF0",
																										"#FFC",
																										"#0B6",
																										"#0C1" ])
																			}
																		}
																	}
																});

											});
										}

									},
									function errorCallback(response) {
										console
												.log("Unable to perform get request");
										// alert('error');
									});

					$http.get("/frr/daily2/t30")
							.then(
									function successCallback(response) {

										$scope.t1 = response.data;
										$scope.t1.count = $scope.t1.length;
										var data = response.data;
										if (!jQuery.isEmptyObject(data)) {
											var dateFormat = $.pivotUtilities.derivers.dateFormat;
											var sortAs = $.pivotUtilities.sortAs;
											var tpl = $.pivotUtilities.aggregatorTemplates;
											var sumOverSum = $.pivotUtilities.aggregatorTemplates.sumOverSum;
											var numberFormat = $.pivotUtilities.numberFormat;
											var frFormat = numberFormat({
												thousandsSep : " ",
												decimalSep : ","
											});
											var intFormat = numberFormat({
												digitsAfterDecimal : 0
											});

											var floatFormat = numberFormat({
												digitsAfterDecimal : 2
											});
											var renderers = $
													.extend(
															$.pivotUtilities.renderers,
															$.pivotUtilities.d3_renderers);
											var derivers = $.pivotUtilities.derivers;

											$(function() {
												$("#frrdaily2t30")
														.pivotUI(
																data,
																{
																	cols : [ "country code" ],
																	rows : [ "subject area" ],
																	derivedAttributes : {
																	
																	},
																	vals : [ "completed %" ],
																	aggregatorName : "List Unique Values",
																	rendererName : "Heatmap",
																	unusedAttrsVertical : false,
																	hiddenAttributes : [
																			"tier",
																			"total %",
																			"pending",
																			"completed",
																			"total process",
																			"pending %" ],
																	rendererOptions : {
																		plotly : {
																			width : 700,
																			height : 300
																		},
																		heatmap : {
																			colorScaleGenerator : function(
																					values) {
																			
																				return Plotly.d3.scale
																						.linear()
																						.domain(
																								[
																										0,
																										25,
																										50,
																										75,
																										100 ])
																						.range(
																								[
																										"#FC0",
																										"#FF0",
																										"#FFC",
																										"#0B6",
																										"#0C1" ])
																			}
																		}
																	}
																});
											});
										}

									},
									function errorCallback(response) {
										console
												.log("Unable to perform get request");
										// alert('error');
									});

					$http.get("/frr/daily/t")
							.then(
									function successCallback(response) {

										$scope.datadt1 = response.data;
										var datadt = response.data;

										$scope.t14C = 0;
										$scope.t14P = 0;
										$scope.t14T = 0;

										$scope.t15C = 0;
										$scope.t15P = 0;
										$scope.t15T = 0;

										$scope.t20C = 0;
										$scope.t20P = 0;
										$scope.t20T = 0;

										$scope.t30C = 0;
										$scope.t30P = 0;
										$scope.t30T = 0;
										for (var i = 0; i < $scope.datadt1.length; i++) {
											var product = $scope.datadt1[i];

											$scope.t14C += Number(product.t14Completed);
											$scope.t14P += Number(product.t14Pending);
											$scope.t14T += Number(product.t14Total);

											$scope.t15C += Number(product.t15Completed);
											$scope.t15P += Number(product.t15Pending);
											$scope.t15T += Number(product.t15Total);

											$scope.t20C += Number(product.t20Completed);
											$scope.t20P += Number(product.t20Pending);
											$scope.t20T += Number(product.t20Total);

											$scope.t30C += Number(product.t30Completed);
											$scope.t30P += Number(product.t30Pending);
											$scope.t30T += Number(product.t30Total);

										}

										$scope.t14cp = (($scope.t14C / $scope.t14T) * 100)
												.toFixed(0);
										$scope.t14pp = (($scope.t14P / $scope.t14T) * 100)
												.toFixed(0);
										$scope.t15cp = (($scope.t15C / $scope.t15T) * 100)
												.toFixed(0);
										$scope.t15pp = (($scope.t15P / $scope.t15T) * 100)
												.toFixed(0);
										$scope.t20cp = (($scope.t20C / $scope.t20T) * 100)
												.toFixed(0);
										$scope.t20pp = (($scope.t20P / $scope.t20T) * 100)
												.toFixed(0);
										$scope.t30cp = (($scope.t30C / $scope.t30T) * 100)
												.toFixed(0);
										$scope.t30pp = (($scope.t30P / $scope.t30T) * 100)
												.toFixed(0);

										$scope.grandcTotal = numberWithCommas($scope.t14C
												+ $scope.t15C
												+ $scope.t20C
												+ $scope.t30C);
										$scope.grandpTotal = numberWithCommas($scope.t14P
												+ $scope.t15P
												+ $scope.t20P
												+ $scope.t30P);
										$scope.grandTotal = numberWithCommas($scope.t14T
												+ $scope.t15T
												+ $scope.t20T
												+ $scope.t30T);

										$scope.grandcPer = ((($scope.t14C
												+ $scope.t15C + $scope.t20C + $scope.t30C) / ($scope.t14T
												+ $scope.t15T + $scope.t20T + $scope.t30T)) * 100)
												.toFixed(0);
										$scope.grandpPer = ((($scope.t14P
												+ $scope.t15P + $scope.t20P + $scope.t30P) / ($scope.t14T
												+ $scope.t15T + $scope.t20T + $scope.t30T)) * 100)
												.toFixed(0);

										$scope.t14CF = numberWithCommas($scope.t14C);
										$scope.t14PF = numberWithCommas($scope.t14P);
										$scope.t14TF = numberWithCommas($scope.t14T);

										$scope.t15CF = numberWithCommas($scope.t15C);
										$scope.t15PF = numberWithCommas($scope.t15P);
										$scope.t15TF = numberWithCommas($scope.t15T);

										$scope.t20CF = numberWithCommas($scope.t20C);
										$scope.t20PF = numberWithCommas($scope.t20P);
										$scope.t20TF = numberWithCommas($scope.t20T);

										$scope.t30CF = numberWithCommas($scope.t30C);
										$scope.t30PF = numberWithCommas($scope.t30P);
										$scope.t30TF = numberWithCommas($scope.t30T);

									},
									function errorCallback(response) {
										console
												.log("Unable to perform get request");
										// alert('error');
									});

					$http.get("/frr/daily/frrasiaheatmap").then(
							function successCallback(response) {

								$scope.tahm = response.data;

								var data = response.data;
								
								if (!jQuery.isEmptyObject(data)) {
									var lookup = {};
									var items = response.data;
									$scope.dataAs = [];

									for (var item, i = items.length - 1; i >=0  ; i--) {
										
										item = items[i];
										
										var name = item.date;
										if (!(name in lookup)) {
											lookup[name] = 1;
											
											$scope.dataAs.push(name);
										}

									};

									date = $scope.dataAs[0];
									$scope.selectedDate = date;
								
									var newdata = getByDate(data, date);
									
									dataChangeAHM(newdata);

								}

							}, function errorCallback(response) {
								console.log("Unable to perform get request");
								// alert('error');
							});

				
					$http.get("/frr/daily/frrasiaareamap").then(
							function successCallback(response) {

								$scope.taam = response.data;
								var rawData = response.data;
								
							 //	google.charts.setOnLoadCallback(drawChart);
								
								var lookup = {};
								var items = response.data;
								console.log(items.length)
								$scope.dataAs = [];

								for (var item, i = items.length-1; i >=0  ; i--) {
									
									item = items[i];
									
									var name = item.date;
									if (!(name in lookup)) {
										lookup[name] = 1;
									
										$scope.dataAs.push(name);
									}

								};

								date = $scope.dataAs[0];
								$scope.selectedDate = date;
														
								var newtaam = getByDate(rawData, date);
								google.charts.load('current', {
									'packages' : [ 'corechart', 'gauge' ]
								});
								
								google.charts.setOnLoadCallback(function () {
									drawChart(newtaam, 'frrasiaareamap');
								});
							

							}, function errorCallback(response) {
								console.log("Unable to perform get request");
								// alert('error');
							});

					$http.get("/frr/daily/frrwesternheatmap").then(
							function successCallback(response) {

								$scope.twhm = response.data;

								var datawmp = response.data;

								if (!jQuery.isEmptyObject(datawmp)) {

									
									date = $scope.selectedDate;
									
									if (jQuery.isEmptyObject(date)) {
										var lookup = {};
										var dates = [];
										for (var item, i = datawmp.length - 1; i >= 0; i--) {

											item = datawmp[i];

											var name = item.date;
											if (!(name in lookup)) {
												lookup[name] = 1;
												dates.push(name);
											}

										};

										date = dates[0];

									}
									
									var newdata = getByDate(datawmp, date);
									
									if(newdata.length > 0) {
										
										 dataChangeWHM(newdata);
									}

									

								}

							}, function errorCallback(response) {
								console.log("Unable to perform get request");
								// alert('error');
							});

					$http.get("/frr/daily/frrwesternareamap")
							.then(
									function successCallback(response) {

										$scope.twam = response.data;
										var rawDataW = response.data;
										if (!jQuery.isEmptyObject(rawDataW)) {
											date = $scope.selectedDate;
											if (jQuery.isEmptyObject(date)) {
												var lookup = {};
												var dates = [];
												for (var item, i = rawDataW.length - 1; i >= 0; i--) {
													item = rawDataW[i];
													var name = item.date;
													if (!(name in lookup)) {
														lookup[name] = 1;
														dates.push(name);
													}

												};
												date = dates[0];
											}
											var newtwam = getByDate(rawDataW,date);
											
											google.charts.load('current', {
												'packages' : [ 'corechart' ]
											});

											google.charts.setOnLoadCallback(function() {
														drawChart(newtwam,'frrwesternareamap');
													});
										
										}

									},
									function errorCallback(response) {
										console
												.log("Unable to perform get request");
										// alert('error');
									});

					function drawChart(rawData, idd) {

						date = $scope.selectedDate;
						var date = new Date(date);
						date.setDate(date.getDate());
						var day = date.getDate();
						var month = date.getMonth();
						var year = date.getFullYear();

						var data = [];
						var Header = [ 'Hour', 'Foundation', 'Products','Finance', 'Risk' ];
						data.push(Header);

					
						for (var i = rawData.length -1 ; i >=0  ; i--) {
							var temp = [];
							temp.push(new Date(year, month, day,
									rawData[i].hour));
							temp.push(rawData[i].foundation);
							temp.push(rawData[i].products);
							temp.push(rawData[i].finance);
							temp.push(rawData[i].risk);
						   data.push(temp);
						}
						if(rawData.length <=0 ){
							
							var temp = [];
							temp.push(new Date(year, month, day,0));
							temp.push(0);
							temp.push(0);
							temp.push(0);
							temp.push(0);
						   data.push(temp);
							
						}
					
						//	google.charts.setOnLoadCallback(drawChart);
						var data = new google.visualization.arrayToDataTable(data);

						var options = {
							// title: 'Batch progress over
							// the day',
							hAxis : {
								title : 'Hour of the Day',
								titleTextStyle : {
									color : '#333'
								},
								viewWindow : {
									min : new Date(year, month, day, 00),
									max : new Date(year, month, day + 1, 00)
								}

								,
								gridlines : {
									count : 12
								}
							},
							vAxis : {
								title : '% of Completion',
								titleTextStyle : {
									color : '#333'
								},
								viewWindowMode : 'explicit',
								viewWindow : {
									max : 120,
									min : 0
								}
							}
							//,colors: ['#242222', '#5d6263', '#0f2ba8', '#a80f0f']
							,
							legend : {
								position : 'bottom',
								alignment : 'center'
							}
						};

						var chart = new google.visualization.AreaChart(document.getElementById(idd));
						
						chart.draw(data, options);

					}

					function dataChangeWHM(newdata) {

						$("#frrwesternheatmap").pivotUI(
										newdata,
										{
											cols : [ "Hour" ],
											rows : [ "Category", "Subject Area" ],
											derivedAttributes : {},
											vals : [ "per" ],
											aggregatorName : "List Unique Values",
											rendererName : "Heatmap",
											renderers : renderers,
											unusedAttrsVertical : false,

											sorters : {
												Category : $.pivotUtilities
														.sortAs(

														[ "Risk", "Finance",
																"Products",
																"Foundation" ]),

											},

											rendererOptions : {
												plotly : {
													width : 300,
													height : 300
												},
												heatmap : {
													colorScaleGenerator : function(
															values) {

														return Plotly.d3.scale
																.linear()
																.domain(
																		[
																				0,
																				50,
																				100 ])
																.range(
																		[
																				"#FFF",
																				"#FC0",
																				"#0C1" ])
													}
												}
											}
										});

					}
					;

					function dataChangeAHM(newdata) {

						$("#frrasiaheatmap").pivotUI(
										newdata,
										{

											cols : [ "Hour" ],
											rows : [ "Category", "Subject Area" ],
											vals : [ "per" ],
											aggregatorName : "List Unique Values",
											rendererName : "Heatmap",
											renderers : renderers,
											unusedAttrsVertical : false,
											sorters : {
												Category : $.pivotUtilities
														.sortAs(

														[ "Risk", "Finance",
																"Products",
																"Foundation" ]),

											},

											//  hiddenAttributes:["category","hour","percentage"],
											//filter: function(record) {return record["hour"] % 2 == 0},			                    
											rendererOptions : {
												plotly : {
													width : 300,
													height : 300
												},
												heatmap : {
													colorScaleGenerator : function(
															values) {
														// Plotly happens to come with d3 on board
														return Plotly.d3.scale
																.linear()
																.domain(
																		[
																				0,
																				50,
																				100 ])
																.range(
																		[
																				"#FFF",
																				"#FC0",
																				"#0C1" ])
													}
												}
											}
										});

					};

                  $scope.selectedItemChanged = function() {
                	  
                	  dt =  $scope.selectedDate;
						console.log("Date: " + dt);
						
						newdataA = getByDate($scope.tahm, dt);
                        dataChangeAHM(newdataA);
						
                       
						rawDataA = getByDate($scope.taam, dt);
                        drawChart(rawDataA, 'frrasiaareamap');
						
                        rawDataW = getByDate($scope.twam, dt);
						
                        drawChart(rawDataW, 'frrwesternareamap');
						

                       newdataW = getByDate($scope.twhm, dt);
					    if(newdataW.length){
						    dataChangeWHM(newdataW);
                         } else{
                        	 dataChangeWHM(null);
                         }
						
						
					};

					function getByDate(data, date) {
						return data.filter(function(el) {
							return el.date == date;
						});
					};

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
