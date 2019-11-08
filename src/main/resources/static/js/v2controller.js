

// added for cluster utilization details
var app = angular.module('app');
app.controller('loadV1Controller', function($scope, $rootScope, $stateParams,$state, LoginService, $http) {

	$scope.headingTitle = "EDAG DL V1 Summary";

	n =  new Date();
	y = n.getFullYear();
	m1 = n.getMonth() + 1;
	d = n.getDate()-1;
	$scope.today = y + "-" + m1 + "-" + d;
	
	$http.get("/load/v1").then(
			function successCallback(response) {
				var root = response.data;
				var content = document.getElementById('v1viz');
			var m = [120, 80, 20, 120],
				  w = 1400 - m[1] - m[3],
				  h = 5000 - m[0] - m[2],
				  i = 0;

				var tree = d3.layout.tree().size([h, w]);

				var diagonal = d3.svg.diagonal()
				  .projection(function(d) {
				    return [d.y, d.x];
				  });

				var vis = d3.select("#v1viz").append("svg:svg")
				.attr("width", w + m[1] + m[3])
				.attr("height", h + m[0] + m[2])
				.append("svg:g")
				.attr("transform", "translate(" + m[3] + "," + m[0] + ")");

				// root initialized above with the html
				root.x0 = h / 2;
				root.y0 = 0;

				function toggleAll(d) {
				  if (d.children) {
				    d.children.forEach(toggleAll);
				    toggle(d);
				  }
				}

				// Initialize the display to show a few nodes.
			   root.children.forEach(toggleAll);
				
		       toggle(root.children[0]);
				/*
				toggle(root.children[1]);
				toggle(root.children[1].children[2]);
				toggle(root.children[9]);
				toggle(root.children[9].children[0]);
				*/
				update(root);


				function update(source) {
				  var duration = d3.event && d3.event.altKey ? 5000 : 500;

				  // compute the new height
				  var levelWidth = [1];
				  var childCount = function(level, n) {

				    if (n.children && n.children.length > 0) {
				      if (levelWidth.length <= level + 1) levelWidth.push(0);

				      levelWidth[level + 1] += n.children.length;
				      n.children.forEach(function(d) {
				        childCount(level + 1, d);
				      });
				    }
				  };
				  childCount(0, root);
				  var newHeight = d3.max(levelWidth) * 20; // 20 pixels per line  
				  tree = tree.size([newHeight, w]);

				  // Compute the new tree layout.
				  var nodes = tree.nodes(root).reverse();

				  // Normalize for fixed-depth.
				  nodes.forEach(function(d) {
				    d.y = d.depth * 180;
				  });

				  // Update the nodes…
				  var node = vis.selectAll("g.node")
				    .data(nodes, function(d) {
				      return d.id || (d.id = ++i);
				    });

				  // Enter any new nodes at the parent's previous position.
				  var nodeEnter = node.enter().append("svg:g")
				    .attr("class", "node")
				    .attr("transform", function(d) {
				      return "translate(" + source.y0 + "," + source.x0 + ")";
				    })
				    .on("click", function(d) {
				      toggle(d);
				      update(d);
				    });

				  nodeEnter.append("svg:circle")
				    .attr("r", 1e-6)
				    .style("fill", 
				    function(d) {
				    //  return d.children || d._children ?  "green" :d.isCompleted;
				    	return d.isCompleted;
				    });

				  nodeEnter.append("svg:text")
				    .attr("x", function(d) {
				      return d.children || d._children ? -10 : 10;
				    })
				    .attr("dy", ".35em")
				    .attr("text-anchor", function(d) {
				      return d.children || d._children ? "end" : "start";
				    })
				    .text(function(d) {
				      return d.name;
				    })
				    .style("fill-opacity", 1e-6);

				  // Transition nodes to their new position.
				  var nodeUpdate = node.transition()
				    .duration(duration)
				    .attr("transform", function(d) {
				      return "translate(" + d.y + "," + d.x + ")";
				    });

				  nodeUpdate.select("circle")
				    .attr("r", 5);
				 //   .style("fill",
				 //   		function(d) {
				//      return d._children ?  d.status : "green";
				//       });
				 // function(d) { 
		                 /*return color according to the current data's status value    */
		                 /*it is > 1 than red else if < 0 than orange else(if 0)than red */   
		          //        return d.status;
		         //     }) ;
				  nodeUpdate.select("text")
				    .style("fill-opacity", 1);

				  // Transition exiting nodes to the parent's new position.
				  var nodeExit = node.exit().transition()
				    .duration(duration)
				    .attr("transform", function(d) {
				      return "translate(" + source.y + "," + source.x + ")";
				    })
				    .remove();

				  nodeExit.select("circle")
				    .attr("r", 1e-6);

				  nodeExit.select("text")
				    .style("fill-opacity", 1e-6);

				  // Update the links…
				  var link = vis.selectAll("path.link")
				    .data(tree.links(nodes), function(d) {
				      return d.target.id;
				    });
				  // .style("stroke",
				  //  		function(d) {
				   //   return d.target.isCompleted;
				 //     });
				    ;

				  // Enter any new links at the parent's previous position.
				  link.enter().insert("svg:path", "g")
				    .attr("class", "link")
				    .attr("d", function(d) {
				      var o = {
				        x: source.x0,
				        y: source.y0
				      };
				      return diagonal({
				        source: o,
				        target: o
				      });
				    })
				    .transition()
				    .duration(duration)
				    .attr("d", diagonal);
			    //  .style("stroke",
				//	function(d) {
			    //	   return d.target.isCompleted;
				 //    });
				    
				  // Transition links to their new position.
				  link.transition()
				    .duration(duration)
				    .attr("d", diagonal)
				   .style("stroke",
				  		function(d) {
				   return  d.target.isCompleted;
				       });
				    ;
				 

				  // Transition exiting nodes to the parent's new position.
				  link.exit().transition()
				    .duration(duration)
				    .attr("d", function(d) {
				      var o = {
				        x: source.x,
				        y: source.y
				      };
				      return diagonal({
				        source: o,
				        target: o
				      });
				    })
				    .remove();

				  // Stash the old positions for transition.
				  nodes.forEach(function(d) {
				    d.x0 = d.x;
				    d.y0 = d.y;
				  });
				  
				   node.append("svg:title").text(function(d , i ) {
					   
					 var  arrivalDate = Boolean(d.arrivalTime) ? "Arrival Time: "+ d.arrivalTime +"\n" : "" ;
					    return Boolean(d.children) ? d.date : Boolean(d._children) ? "Count: " + d._children.length : arrivalDate + "Load Time: " + d.date  +"\n" +"File Name: " + d.fileName +"\n" + "Record Count: " + d.recordsCount  +"\n"  + "File Size: " + d.fileSize +"\n"  ;
					});
					
				  window.setTimeout(function() {
				    var max = d3.max(d3.selectAll(".node")[0], function(g) {
				      return d3.transform(d3.select(g).attr("transform")).translate[1];
				    });
				    d3.select("svg").attr("height", max + 1000)
				  
				  }, 1000)
				};

				// Toggle children.
				function toggle(d) {
				  if (d.children) {
				    d._children = d.children;
				    d.children = null;
				  } else {
				    d.children = d._children;
				    d._children = null;
				  }
				}
				
			}, function errorCallback(response) {
				console.log("Unable to perform get request v2Controller");
			//	alert('error');
			});
	$http.get("/load/v2").then(function successCallback(response) {
		
	}, function errorCallback(response) {
		console.log("Unable to perform get request");
		//alert('error');
	});
	
	
});

