
// added for cluster utilization details

var app = angular.module('app');
app.controller('loadFRRController', function($scope, $http) {
	
	

	$scope.headingTitle = "EDW/FRR Batch Status";
	
	
	var margin = {top: 20, right: 160, bottom: 60, left: 60};

	var width = 650 - margin.left - margin.right,
	    height = 400 - margin.top - margin.bottom;




	/* Data in strings like it would be if imported from a csv */


	var parse = d3.time.format("%Y").parse;



	var colors = [ "#2DA2DC", "#2ECC71", "#d25c4d" ,"#FFA500"];
	$http.get("/frr/daily/t20").then(
			function successCallback(response) {
				$scope.frrd20 = response.data;
				var data =  response.data;
				
		
				var svg = d3.select("#frrdaily")
				  .append("svg")
				  .attr("width", width + margin.left + margin.right)
				  .attr("height", height + margin.top + margin.bottom)
				  .append("g")
				  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
				

				// Transpose the data into layers
				var dataset = d3.layout.stack()(["running","pending","completed","failed" ].map(function(status) {
					  return data.map(function(d) {
					    return {x: d.name, y: +d[status]};
					  });
					}));

				// Set x, y and colors
				var x = d3.scale.ordinal()
				  .domain(dataset[0].map(function(d) { return d.x; }))
				  .rangeRoundBands([10, width-10], 0.02);

				var y = d3.scale.linear()
				  .domain([0, d3.max(dataset, function(d) {  return d3.max(d, function(d) { return d.y0 + d.y; });  })])
				  .range([height, 0]);

			
				// Define and draw axes
				var yAxis = d3.svg.axis()
				  .scale(y)
				  .orient("left")
				  .ticks(5)
				  .tickSize(-width, 0, 0)
				  .tickFormat( function(d) { return d } );

				var xAxis = d3.svg.axis()
				  .scale(x)
				  .orient("bottom")
				  .tickFormat(function(d) { return d });

				svg.append("g")
				  .attr("class", "y axis")
				  .call(yAxis);

				svg.append("g")
				  .attr("class", "x axis")
				  .attr("transform", "translate(0," + height + ")")
				  .call(xAxis);


				// Create groups for each series, rects for each segment 
				var groups = svg.selectAll("g.cost")
				  .data(dataset)
				  .enter().append("g")
				  .attr("class", "cost")
				  .style("fill", function(d, i) { return colors[i]; });

				var rect = groups.selectAll("rect")
				  .data(function(d) { return d; })
				  .enter()
				  .append("rect")
				  .attr("x", function(d) { return x(d.x); })
				  .attr("y", function(d) { return y(d.y0 + d.y); })
				  .attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); })
				  .attr("width", x.rangeBand())
				  .on("mouseover", function() { tooltip.style("display", null); })
				  .on("mouseout", function() { tooltip.style("display", "none"); })
				  .on("mousemove", function(d) {
				    var xPosition = d3.mouse(this)[0] - 15;
				    var yPosition = d3.mouse(this)[1] - 25;
				    tooltip.attr("transform", "translate(" + xPosition + "," + yPosition + ")");
				    tooltip.select("text").text(d.y);
				  });


				// Draw legend
				var legend = svg.selectAll(".legend")
				  .data(colors)
				  .enter().append("g")
				  .attr("class", "legend")
				  .attr("transform", function(d, i) { return "translate(30," + i * 19 + ")"; });
				 
				legend.append("rect")
				  .attr("x", width - 18)
				  .attr("width", 18)
				  .attr("height", 18)
				  .style("fill", function(d, i) {return colors.slice().reverse()[i];});
				 
				legend.append("text")
				  .attr("x", width + 5)
				  .attr("y", 9)
				  .attr("dy", ".35em")
				  .style("text-anchor", "start")
				  .text(function(d, i) { 
				    switch (i) {
				      case 0: return "Failed";
				      case 1: return "Completed";
				      case 2: return "Pending";
				      case 3: return "Running";
				    }
				  });

				// Prep the tooltip bits, initial display is hidden
				var tooltip = svg.append("g")
				  .attr("class", "tooltip")
				  .style("display", "none");
				    
				tooltip.append("rect")
				  .attr("width", 30)
				  .attr("height", 20)
				  .attr("fill", "white")
				  .style("opacity", 0.5);

				tooltip.append("text")
				  .attr("x", 15)
				  .attr("dy", "1.2em")
				  .style("text-anchor", "middle")
				  .attr("font-size", "12px")
				  .attr("font-weight", "bold");

				
				
				
			}, function errorCallback(response) {
				console.log("Unable to perform get request");
			//	alert('error');
			});
	$http.get("/frr/daily/t30").then(
			function successCallback(response) {}, function errorCallback(response) {
				console.log("Unable to perform get request");
			//	alert('error');
			});
	$http.get("/frr/monthly/t20").then(
			function successCallback(response) {
				$scope.frrm20 = response.data;
				var data =  $scope.frrm20;
				
			

				var svg = d3.select("#frrmonthly")
				  .append("svg")
				  .attr("width", width + margin.left + margin.right)
				  .attr("height", height + margin.top + margin.bottom)
				  .append("g")
				  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

			
				// Transpose the data into layers
				var dataset = d3.layout.stack()(["running","pending","completed","failed" ].map(function(status) {
					  return data.map(function(d) {
					    return {x: d.name, y: +d[status]};
					  });
					}));
			

				// Set x, y and colors
				var x = d3.scale.ordinal()
				  .domain(dataset[0].map(function(d) { return d.x; }))
				  .rangeRoundBands([10, width-10], 0.02);

				var y = d3.scale.linear()
				  .domain([0, d3.max(dataset, function(d) {  return d3.max(d, function(d) { return d.y0 + d.y; });  })])
				  .range([height, 0]);



				// Define and draw axes
				var yAxis = d3.svg.axis()
				  .scale(y)
				  .orient("left")
				  .ticks(5)
				  .tickSize(-width, 0, 0)
				  .tickFormat( function(d) { return d } );

				var xAxis = d3.svg.axis()
				  .scale(x)
				  .orient("bottom")
				  .tickFormat(function(d) { return d });

				svg.append("g")
				  .attr("class", "y axis")
				  .call(yAxis);

				svg.append("g")
				  .attr("class", "x axis")
				  .attr("transform", "translate(0," + height + ")")
				  .call(xAxis);


				// Create groups for each series, rects for each segment 
				var groups = svg.selectAll("g.tot")
				  .data(dataset)
				  .enter().append("g")
				  .attr("class", "tot")
				  .style("fill", function(d, i) { return colors[i]; });

				var rect = groups.selectAll("rect")
				  .data(function(d) { return d; })
				  .enter()
				  .append("rect")
				  .attr("x", function(d) { return x(d.x); })
				  .attr("y", function(d) { return y(d.y0 + d.y); })
				  .attr("height", function(d) { return y(d.y0) - y(d.y0 + d.y); })
				  .attr("width", x.rangeBand())
				  .on("mouseover", function(d) {
					    var xPosition = d3.mouse(this)[0] - 15;
					    var yPosition = d3.mouse(this)[1] - 25;
					    tooltip.attr("transform", "translate(" + xPosition + "," + yPosition + ")");
					    tooltip.select("text").text(d.y);
					  })
				  .on("mouseout", function() { tooltip.style("display", "none"); })
				  .on("mousemove", function(d) {
				    var xPosition = d3.mouse(this)[0] - 15;
				    var yPosition = d3.mouse(this)[1] - 25;
				    tooltip.attr("transform", "translate(" + xPosition + "," + yPosition + ")");
				    tooltip.select("text").text(d.y);
				  });


				// Draw legend
				var legend = svg.selectAll(".legend")
				  .data(colors)
				  .enter().append("g")
				  .attr("class", "legend")
				  .attr("transform", function(d, i) { return "translate(30," + i * 19 + ")"; });
				 
				legend.append("rect")
				  .attr("x", width - 18)
				  .attr("width", 18)
				  .attr("height", 18)
				  .style("fill", function(d, i) {return colors.slice().reverse()[i];});
				 
				legend.append("text")
				  .attr("x", width + 5)
				  .attr("y", 9)
				  .attr("dy", ".35em")
				  .style("text-anchor", "start")
				  .text(function(d, i) { 
				    switch (i) {
				      case 0: return "Failed";
				      case 1: return "Completed";
				      case 2: return "Pending";
				      case 3: return "Running";
				    }
				  });

				// Prep the tooltip bits, initial display is hidden
				var tooltip = svg.append("g")
				  .attr("class", "tooltip")
				  .style("display", "none");
				    
				tooltip.append("rect")
				  .attr("width", 30)
				  .attr("height", 20)
				  .attr("fill", "white")
				  .style("opacity", 0.5);

				tooltip.append("text")
				  .attr("x", 15)
				  .attr("dy", "1.2em")
				  .style("text-anchor", "middle")
				  .attr("font-size", "12px")
				  .attr("font-weight", "bold");

				}, function errorCallback(response) {
				console.log("Unable to perform get request");
			//	alert('error');
			});
	
	
	
	$http.get("/frr/monthly/t30").then(
			function successCallback(response) {}, function errorCallback(response) {
				console.log("Unable to perform get request");
		
			});
	

	$http.get("/frr/daily2/t20").then(
			function successCallback(response) {
				

				$scope.t1 = response.data;
				$scope.bizDate =response.data[0].biz_dt; 
				
			
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
			         var renderers = $.extend($.pivotUtilities.renderers, $.pivotUtilities.plotly_renderers,$.pivotUtilities.export_renderers,
			                 $.pivotUtilities.gchart_renderers);
			        var derivers = $.pivotUtilities.derivers;
			   
			     
			       
			     $(function(){
			             $("#frrdaily2").pivotUI(data, {
				                cols: ["country code"], 
				                rows: ["source system"],
				                derivedAttributes: {
				                	
				                	
				             	"tim": dateFormat("etime", "%H:%M", false) , 
				                	
			                      
			                     //   "load date ":   dateFormat("load time", "%y-%m-%d", false)
			                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
			                    },
			                    vals: ["tim"],
			                    aggregatorName: "List Unique Values",
			                   
			                    rendererName: "Heatmap",
			                    //renderers: renderers,
			                    unusedAttrsVertical:false,
			                //    hiddenAttributes:["tier","total %","pending","completed","total process","pending %"],
			                    rendererOptions: {
			                    	rendererName: {
			                    		
			                     },   plotly: {width: 700, height: 300},
			                     heatmap: {
			                            colorScaleGenerator: function(values) {
			                                // Plotly happens to come with d3 on board
			                                return Plotly.d3.time.scale()
			                                    .domain([ 0, 100])
			                                    .range(["#FC0","#0C6"])
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


	$http.get("/frr/daily2/t30").then(
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
		         var renderers = $.extend($.pivotUtilities.renderers, $.pivotUtilities.plotly_renderers,$.pivotUtilities.export_renderers,
		                 $.pivotUtilities.gchart_renderers);
		        var derivers = $.pivotUtilities.derivers;
		     
		       
		     $(function(){
		             $("#frrdaily2t30").pivotUI(data, {
			                cols: ["country code"], 
			                rows: ["source system"],
			                derivedAttributes: {
		                      //  "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
		                     //   "load date ":   dateFormat("load time", "%y-%m-%d", false)
		                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
		                    },
		                    vals: ["completed %"],
		                    aggregatorName: "List Unique Values",
		                    rendererName: "Heatmap",
		                    //renderers: renderers,
		                    unusedAttrsVertical:false,
		                    hiddenAttributes:["tier","total %","pending","completed","total process","pending %"],
		                    rendererOptions: {
		                     plotly: {width: 700, height: 300},
		                     heatmap: {
		                            colorScaleGenerator: function(values) {
		                                // Plotly happens to come with d3 on board
		                                return Plotly.d3.scale.linear()
		                                    .domain([ 0, 100])
		                                    .range(["#FC0", "#0C6"])
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
	});


function isEmpty(str) {
    return (!str || 0 === str.length);
}
	/*
	
	$http.get("/frr/daily2/t20").then(
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
			         var renderers = $.extend($.pivotUtilities.renderers, $.pivotUtilities.plotly_renderers,$.pivotUtilities.export_renderers,
			                 $.pivotUtilities.gchart_renderers);
			        var derivers = $.pivotUtilities.derivers;
			     
			       
			     $(function(){
			        
			             $("#frrdaily2").pivot(data, {
				                cols: ["tier", "country code"], rows: ["source system"],
				                derivedAttributes: {
			                      //  "load time (HH:MM)": dateFormat("load time", "%H:%M", false),
			                     //   "load date ":   dateFormat("load time", "%y-%m-%d", false)
			                        //   , "load labels ":   dateFormat("time", "%H:%M", false)
			                    },
			                  
			                 vals: ["completed %"],
			                 //aggregator: $.pivotUtilities.aggregators["List Unique Values"](),
			                 aggregatorName: "List Unique Values",
			                 renderer: $.pivotUtilities.plotly_renderers["Heatmap"],
			                 rendererName: "Heatmap",
			                 
			            //aggregatorName: "List Unique Values",
			            //aggregatorTemplates.listUnique
		                //rendererName: "Heatmap",
		                // renderers: renderers,
		                  unusedAttrsVertical:false,
		                  hiddenAttributes:["total %","pending","completed","total process","pending %"],
			           rendererOptions: {
			        	   rendererName: {
			                         clickCallback: function(e, value, filters, pivotData){
			                        
			                             var names = [];
			                             pivotData.forEachMatchingRecord(filters,
			                                 function(record){ names.push(record.Name); });
			                             
			                         }
			                     },   plotly: {width: 1000, height: 300},
			                     
			                     heatmap: {
			                            colorScaleGenerator: function(values) {
			                                // Plotly happens to come with d3 on board
			                                return Plotly.d3.scale.linear()
			                                    .domain([-35, 0, 35])
			                                    .range(["#DDD", "#DDF", "#0C6"])
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
	
			});

	*/