<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	 <!-- Custom styles for this template -->
    <script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.4.1.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/highcharts.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/exporting.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/export-data.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/series-label.js"></script>
	
	<script type="text/javascript">
	//aqui seria seus dados que vem da requisição...
	  var graph_data = [{"date":"10-15-2019","name":"As Crônicas De Gelo e Fogo","valor":2.0},{"date":"10-20-2019","name":"O Nevoeiro","valor":2.0},{"date":"10-21-2019","name":"O Conde de Monte Cristo","valor":1.0}]
	  var graph_keys = [], graph_values = [];
	  for (var i in graph_data) {
	  var date = new Date(graph_data[i].date);
	  var day = date.getDate();
	  var monthIndex = date.getMonth();
	  var year = date.getFullYear();
	   graph_keys.push(day + "/" + monthIndex + "/" + year);
	  graph_values.push(graph_data[i].valor);

	}
	  
	 
	$(function () {
	    $('#container').highcharts({
	        chart: {
	        renderTo: 'container',
	        type: 'column'
	        },
	        title: {
	            text: 'Mês atual',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Subtítulo',
	            x: -20
	        },
	        xAxis: {
	            categories: graph_keys,
	        },
	        yAxis: {
	            title: {
	                text: 'Percentagem'
	            },
	            plotLines: [{
	                value: 1,
	                width: 0,
	                color: '#808080'
	            }]
	        },
	       tooltip: {
	                borderWidth: 0,
	                backgroundColor: "rgba(255,255,255,0)",
	                borderRadius: 0,
	                shadow: false,
	                useHTML: true,
	                percentageDecimals: 0,
	                formatter: function () {
	                    // var point = filterLegend(this.point.name);
	                    console.log(this);
	                    
	                    var obj = graph_data[this.series.data.indexOf( this.point )]
	                 return '<div class="tooltip-block">' + this.key + '' + this.x + ':<br> ' + this.y + ' ' + JSON.stringify(graph_data[this.series.data.indexOf( this.point )]) +' (' + Math.round(Highcharts.numberFormat(this.percentage, 2)) + '%)</b>'+
	                 '<br>Turno: ' + obj.turno +'<br>Valor: ' + obj.valor
	                 +'</div>';
	                    
	                    
	                }
	                //pointFormat: '<div style="position:relative; z-index: 99999!important; background: white">{series.name}: <b>{point.percentage:.1f}%</b></div>'
	            },
	        legend: {},
	        series: [{
	            name: 'Tarefas Gerais',
	            data: graph_values,
	             dataLabels: {
	                    enabled: true,
	                    rotation: -90, //-90,
	                    color: '#333',
	                    align: 'right',
	                    format: '{point.y:.0f}', // one decimal 00.0 -> .1f || 00.00 -> .2f || 00.000 -> .3f
	                    y: 10, // 10 pixels down from the top
	                    style: {
	                        fontSize: '12px',
	                        textOutline: false
	                    }
	                }
	        }]
	    });
	});
	
	
	</script>
	
</head>
<body>
	<h1> TESTE GRAFICO</h1> <br>
	
	<div id="container">
	</div>

</body>
</html>