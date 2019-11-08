
// added for cluster utilization details
var app = angular.module('app');
app.controller('clusterController', function($scope, $rootScope, $stateParams,$state, LoginService, $http) {
	
	

	$scope.headingTitle = "Cluster utilisation details";
	$http.get("/cluster/memory").then(
			function successCallback(response) {
				$scope.memory = response.data;

				var data =rawData;
				// Googlecharts starts here

				// #dataviz
			    google.charts.load('current', {'packages':['annotationchart']});
			      google.charts.setOnLoadCallback(drawChart);

			      function drawChart() {
			    	  
			    	  
			    	  
			        var data = new google.visualization.DataTable();
			        data.addColumn('date', 'Date');
			        data.addColumn('number', 'min');
			  		data.addColumn('string', 'min title');
			  		//  data.addColumn('string', 'min text');
			        data.addColumn('number', 'mean');
			  	    data.addColumn('string', 'mean title');
			   //     data.addColumn('string', 'Gliese text');
			      data.addColumn('number', 'max');
	              data.addColumn('string', 'max title');
			    //            data.addColumn('string', 'MAX text');
			        data.addRows(data);

			        var chart = new google.visualization.AnnotationChart(document.getElementById('dataviz'));

			        var options = {
			          displayAnnotations: true
			        };

			        chart.draw(data, options);
			      }
				

			}, function errorCallback(response) {
				console.log("Unable to perform get request");
			//	alert('error');
			});
	$http.get("/cluster/cpu").then(
			function successCallback(response) {
				$scope.cpu = response.data;

				var data = $scope.cpu;
				// D3 starts here

				// Define the line
				var valueline = d3.svg.line().x(function(d) {
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
			//	alert('error');
			});
	$http.get("/cluster/io").then(
			function successCallback(response) {
				$scope.io = response.data;
				var data = $scope.io;

				// var parseDate = d3.time.format("%d-%b-%y").parse;
				var parseDate =d3.time.format("%Y-%m-%d");
				

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
			//	alert('error');
			});
});

