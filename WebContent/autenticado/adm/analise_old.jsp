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
		$(document).ready(function(){
			$('.ui.dropdown').dropdown({on:'hover'});
			$('#select').dropdown();
			
			var map = $('#performances').val();
			console.log(performances);
			map = map.replace('{','');
			map = map.replace('}','');
			map = map.split(',');
			
			var performance='[';
			
			 for(var i= 0;i < map.length;i++){
				map[i] = map[i].replace(':',',');
				if(i>0){
					performance = performance + ',';
				}
				performance = performance + '['+map[i]+']';
		    }
			performance= performance+']';
			performance = JSON.parse(performance);
				
			Highcharts.chart('container', {
			    chart: {
			        type: 'spline'
			    },
			    title: {
			        text: 'Quantidade de Venda de Livro'
			    },
			    subtitle: {
			       text: 'Grafico de Linhas'
			    },
			   
			    yAxis: {
			        min: 0,
			        title: {
			            text: 'Quantidade'
			        }
			    },
			    legend: {
			    	layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'middle'
			    },
			    
			    plotOptions: {
			        series: {
			            label: {
			                connectorAllowed: false
			            },
			            pointStart: 2010
			        }
			    },


			    tooltip: {
			        pointFormat: '<b>{point.y:.1f} quantidades vendidas</b>'
			    },
			    series: [{
			    	name: '',
			        data: performance,			        
			        colorByPoint: true
			    }],
			    responsive: {
			        rules: [{
			            condition: {
			                maxWidth: 500
			            },
			            chartOptions: {
			                legend: {
			                    layout: 'horizontal',
			                    align: 'center',
			                    verticalAlign: 'bottom'
			                }
			            }
			        }]
			    }
			    
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


		        <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
					<div style="text-align: center;">
						<h3> Relatórios </h3>  
						   <hr>
		            </div>           
			       <form action="SalvarAnalise" method="post" class="ui form">
						 <div class="row">
					        <div class="col-12">
					         
					              <div class="row">
					                
									  <div class="col-3">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtDtInicial">Data Inicial</label>
										  <input type="date" id="dtInicial" name="dtInicial" class="form-control form-control-alternative">
										 </div>
									  </div>
									  <div class="col-3">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtDtFinial">Data Final</label>
										  <input type="date" id="dtFinal" name="dtFinal" class="form-control form-control-alternative">
										</div>
									  </div>
									  <div class="col-2">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtTipoRelatorio">Tipo Periodo</label>
										  <select id="tipoRelatorio" name="tipoRelatorio" class="form-control">
											<%
												for (TipoRelatorio tipoRelatorio : TipoRelatorio.values()) {
											%>  
												<option id="<%=tipoRelatorio.getCodigo() %>" value="<%=tipoRelatorio.getCodigo() %>"><%=tipoRelatorio.getDescricao() %></option>
											<%
												} 
											%>
										  </select>
										</div>
									  </div>
									  <div class="col-1">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtTipoRelatorio">.</label>
									   	<button type="submit" name="operacao" value="CONSULTAR" class="btn btn-primary">Gerar </button>
									   	</div>
									   </div>
								</div>									
					                <hr>
					                  
					      		<%if(json !=null){ %>
							  <input style="display:none" type="text" id="performances" name="performances" value='<%if(json != null) out.print(json); %>'>
					
							 <div class="field">
								<input style="display:none" type="text" id="destino" name="destino" value="PERFORMANCE"/>
							</div><br>
							
							<div id="container" style="min-width: 300 px; height: 400 px; margin: 0 auto"></div>

					      </div>
					      
					      <div class="col-12">
					          <div class="row">      
									<div class="col-3">
									  .
									</div>
								</div>
						 </div>
						<%} %>						
						
					      </div>
					</form>
        		</section>		
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
