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
            <a class="nav-link active" href="http://localhost:8080/SysLibrary/autenticado/adm/dashboard.jsp">
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
              <span>Relatorios</span>
              <a class="d-flex align-items-center text-muted" href="#">
               
              </a>
            </h6>
			        			      
            <ul class="nav flex-column mb-2">
			
			
              <li class="nav-item">
           <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/trocas.jsp/reports.html">
              <span data-feather="file-text"></span> 
             Mais Vendidos
              </a>
              </li>
             <li class="nav-item">
           <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/trocas.jsp/log.jsp">
              <span data-feather="file-text"></span> 
              Log
              </a>
              </li>

      
            </ul>
          </div>
        </nav>


        <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		
			<div style="text-align: center;">
					<h3>Status dos Pedidos </h3>
					   
            	</div>  

			
			<div class="table-responsive col-md-12">
				<table class="table table-striped" >
					<thead>
						<tr>
							<th class="text-center">Nº Pedido</th>
							<th class="text-center">Cliente</th>
							<th class="text-center">Endereço</th>
							<th class="text-center">Valor</th>							
							<th class="text-center">Status</th>
							<th style="text-align: center">Ação</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

		            		
						<tr>
							<td style="text-align: center; vertical-align: middle;">00001</td>																				
							<td style="text-align: center; vertical-align: middle;">Joao Paulo </td>
							<td style="text-align: center; vertical-align: middle;">Rua X, 203 - São Paulo-SP </td>
							<td style="text-align: center; vertical-align: middle;">R$99.90</td>																				
							<td style="text-align: center; vertical-align: middle;">EM PROCESSAMENTO</td>
							
							<!-- Buttons actions -->
							<td style="text-align: center; ">
							<a href="#" class="btn btn-warning btn-sm">EM TRÂNSITO</a> 							
							</td>
							
										
						</tr>
						
						<tr>
							<td style="text-align: center; vertical-align: middle;">00002</td>																				
							<td style="text-align: center; vertical-align: middle;">Jose Silva </td>
							<td style="text-align: center; vertical-align: middle;">Rua Y, 302 - Campinas-SP </td>
							<td style="text-align: center; vertical-align: middle;">R$99.90</td>																				
							<td style="text-align: center; vertical-align: middle;">EM TRÂNSITO</td>
							
							<!-- Buttons actions -->
							<td style="text-align: center; ">
							
							<a href="#"class="btn btn-success btn-sm">ENTREGUE</a>
							</td>
							
										
						</tr>
										

					</tbody>
				</table>
			</div>
			 </section>	
		  </div>	  
       

      </div>	  

    <!-- Icons -->    
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
    <script>
    feather.replace()
    </script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
