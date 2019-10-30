<!DOCTYPE html>
<%@page import="br.com.syslib.enuns.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.util.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Página gerenciar emprestimos e devolucoes.">
    <meta name="author" content="Brenno Coimbra">
    <link rel="icon" href="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Bootstrap core CSS -->
   <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

       <!-- Custom styles for this template -->
    <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/dashboard.css" rel="stylesheet">
    <script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.4.1.min.js"></script>
  	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/highcharts.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/exporting.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/export-data.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/series-label.js"></script>
	
	<script>
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
	
	
	<style>
	footer {
	  position: fixed;	  
	  bottom: 0;
	  width: 100%;
	  background: #fff;
	  color: #333;
	  text-align: center;
	}
	</style>
  </head>

  <body>
  		<%		
  		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Resultado resultado = (Resultado) request.getAttribute("resultado");
		String json = (String)request.getAttribute("json");
      %>
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <%if(usuario !=null){%>
        <a id="signOut" class="nav-link" href="/SysLibrary/SairSys?operacao=SAIR">Sign out <span data-feather="log-out"></span></a>	
		<%  } else {%>
        <a id="signOut" class="nav-link" href="./login.jsp">Login <span data-feather="log-in"></span></a>
        <%} %>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
            <li class="nav-item">
            <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/dashboard.jsp">
                  <span data-feather="home"></span>
                  Dashboard <span class="sr-only"></span>
                </a>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/alterar-senha.jsp">
                  <span data-feather="key"></span>
                  Alterar Senha <span class="sr-only"></span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/form-livro.jsp">
                  <span data-feather="book">(current)</span>
                  Cadastrar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/consultar-livro.jsp">
                  <span data-feather="book"></span>
                  Consultar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/estoque.jsp">
                  <span data-feather="package"></span>
                  Estoque
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/consultar-clientes.jsp">
                  <span data-feather="users"></span>
                  Consultar Clientes
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/trocas.jsp">
                  <span data-feather="rewind"></span>
                  Trocas
                </a>
              </li>
              
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Analise</span>
              <a class="d-flex align-items-center text-muted" href="#">
               
              </a>
            </h6>
			        			      
            <ul class="nav flex-column mb-2">
			
			
              <li class="nav-item">
           <a class="nav-link active" href="http://localhost:8080/SysLibrary/autenticado/adm/reports.jsp">
              <span data-feather="file-text"></span> 
            	Relatórios
              </a>
              </li>
            
            </ul>
          </div>
        </nav>


		       
					<div style="text-align: center;">
						<h3> Relatórios </h3>  
						   <hr>
		            </div>           
			       
							 <div class="field">
								<input style="display:none" type="text" id="destino" name="destino" value="PERFORMANCE"/>
							</div><br>
							
							<div id="container" style="min-width: 300 px; height: 400 px; margin: 0 auto"></div>

					      </div>
					      
					     			
						
			
        	</div>	  
     	  

    <!-- Icons -->    
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script>feather.replace()</script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
