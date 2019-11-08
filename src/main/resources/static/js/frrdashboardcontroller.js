
var app = angular.module('app');
app.controller('frrdashboardcontroller', function($scope, $http) {
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
	
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
              ['Hour','Foundation',' Finance','Products','Risk'],
              [new Date(2019, 09, 03, 0),40,5,17,1],
              [new Date(2019, 09, 03, 1),54,6,24,7],
              [new Date(2019, 09, 03, 2),70,7,32,18],
              [new Date(2019, 09, 03, 3),79,10,41,30],
              [new Date(2019, 09, 03, 4),81,10,42,34],
              [new Date(2019, 09, 03, 5),87,11,44,34],
              [new Date(2019, 09, 03, 6),89,12,46,34],
              [new Date(2019, 09, 03, 7),93,12,51,36],
              [new Date(2019, 09, 03, 8),95,35,63,42],
              [new Date(2019, 09, 03, 9),98,68,75,56],
              [new Date(2019, 09, 03, 10),99,83,80,59],
              [new Date(2019, 09, 03, 11),99,85,81,61],
              [new Date(2019, 09, 03, 12),99,89,86,64],
              [new Date(2019, 09, 03, 13),100,92,90,65],
              [new Date(2019, 09, 03, 14),100,92,98,66],
              [new Date(2019, 09, 03, 15),100,92,98,94],
              [new Date(2019, 09, 03, 16),100,93,98,94],
              [new Date(2019, 09, 03, 19),100,100,100,100],
              [new Date(2019, 09, 03, 20),100,100,100,100],
              [new Date(2019, 09, 03, 22),100,100,100,100],
        ]);

        var options = {
          title: 'Batch progress over the day',
          hAxis: {title: 'Hour of the Day',  titleTextStyle: {color: '#333'}
                      /*,viewWindow: {
                          min: new Date(2019, 09, 03, 00),
                          max: new Date(2019, 09, 04, 00)
                            }
                      */
                      ,gridlines: {count:12}
          },
          vAxis: {title: '% of Completion',  titleTextStyle: {color: '#333'}
          ,viewWindowMode:'explicit',
                 viewWindow: {
                   max:120,
                   min:0
                 }
              }
          //,colors: ['#242222', '#5d6263', '#0f2ba8', '#a80f0f']
          ,legend: { position: 'right', alignment: 'center' }
        };

});

});

