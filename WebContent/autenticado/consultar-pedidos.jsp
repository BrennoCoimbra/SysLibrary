<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.dominio.EntidadeDominio"%>
<%@page import="br.com.syslib.dominio.Livro"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>
<%@page import="br.com.syslib.core.util.*"%>
<%@page contentType="text/html" import="java.util.*, java.text.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Página para buscar livros e acessar informações.">
    <meta name="author" content="Brenno Coimbra">
    <link rel="icon" href="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Bootstrap core CSS -->
   <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/dashboard.css" rel="stylesheet">
    <script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.4.1.min.js"></script>
  	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/bootstrap.min.js"></script>
	
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
		usuario.getId();		
		List<EntidadeDominio> pedidos = (List<EntidadeDominio>) request.getAttribute("pedidos");
		StringBuilder sb;
		
		Resultado resultado = (Resultado) request.getAttribute("resultado");
		int idPed = 0;
		%>	
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <%if(usuario != null){%>
        <a id="signOut" class="nav-link" href="/SysLibrary/SairSys?operacao=SAIR">Sign out <span data-feather="log-out"></span></a>
        	
		<% } else { %>
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
            <a class="nav-link" href="http://localhost:8080/SysLibrary/index.jsp">
                  <span data-feather="home"></span>
                  Home <span class="sr-only"></span>
                </a>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="./profile.jsp">
                  <span data-feather="users"></span>
                  Seu Perfil <span class="sr-only"></span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./form-endereco.jsp">
                  <span data-feather="file"></span>
                  Cadastrar Endereço
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-endereco.jsp">
                  <span data-feather="file"></span>
                  Consultar Endereço
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./form-cartao.jsp">
                  <span data-feather="credit-card"></span>
                  Cadastrar Cartão
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-cartao.jsp">
                  <span data-feather="credit-card"></span>
                  Consultar Cartão
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link " href="http://localhost:8080/SysLibrary/carrinho.jsp">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="./consultar-pedidos.jsp">
                  <span data-feather="shopping-bag"></span>
                  Pedidos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-cupons.jsp">
                  <span data-feather="file-text"></span>
                  Cupons
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-trocas.jsp">
                  <span data-feather="code"></span>
                  Trocas
                </a>
              </li>
            </ul>

           
          </div>
        </nav>

        <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        <form action="SalvarPedido" method="post" class="form-horizontal">
			<div style="text-align: center;">
			<h3>Minhas Compras </h3>  
				   <br>
            	</div>           
       
		<div class="row">
			<div class="col-lg-5">
			
			</div>
			<div class="col-4">
			<input id="search" name="search" class="form-control" placeholder="Pesquisar" type="text">
			</div>
			<div class="col-0">
			<input type='submit' class='btn btn-primary'  id='operacao' name='operacao' value='VISUALIZAR'/>
			 
				
		</div>
			
		</div>
		<!-- /#top -->

				<table class="table table-striped" >
					<thead>
						<tr>

							<th class="text-center">Nº Pedido</th>
							<th class="text-center">Data Pedido</th>
							<th class="text-center">Valor Total</th>
							<th class="text-center">Situação</th>
							<th style="text-align: center">Ações</th>
							
						</tr>
					</thead>
					<tbody>

		            		<%
		            		
		            		
		            		if (pedidos != null) {
			            		for (EntidadeDominio ed : pedidos) {
			            			Pedido ped = (Pedido) ed;	
			            			
		            		
		            		%>
						<tr>
							
							<td style="text-align: center; vertical-align: middle;"><%=ped.getIdPedido() %> </td>
							<td style="text-align: center; vertical-align: middle;"><%=ConverteDate.converteDateString(ped.getDtCadastro())%> </td>							
							<td style="text-align: center; vertical-align: middle;"><%= "R$" + String.format("%.2f",ped.getValorTotalPedido()) %> </td>																				
							<td style="text-align: center; vertical-align: middle;"><%=ped.getStatusPedido() %></td>
							
						
							<!-- Buttons actions -->
							 <td class="text-center">
			                  <div class="dropdown">
			                    <a class="btn btn-sm btn-icon-only" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                     <span data-feather="more-vertical"></span> 
			                    </a>
			                    
			                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
			                    <%if(!ped.getStatusPedido().equals("CANCELADO")) { %>
			                      <a class="dropdown-item" href="SalvarPedido?operacao=CONSULTAR&idPedido=<%=ped.getIdPedido() %>" class="btn btn-warning btn-sm">Detalhes</a>
			                      <a class="dropdown-item" href="SalvarPedido?operacao=CANCEL&idPedido=<%=ped.getIdPedido() %>" class="btn btn-danger btn-sm">Cancelar</a>			                     
			                       <%} else {%>
			                       <a class="dropdown-item" href="SalvarPedido?operacao=CONSULTAR&idPedido=<%=ped.getIdPedido() %>" class="btn btn-warning btn-sm">Detalhes</a>
			                       <%} %>
			                    </div>
			                  </div>
			                </td>
							
							
						<%}} %>
						</tr>
		</tbody>
		
				
			</table>	
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
