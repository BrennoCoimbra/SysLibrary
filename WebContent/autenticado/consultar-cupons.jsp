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
		List<EntidadeDominio> cupons = (List<EntidadeDominio>) request.getAttribute("cupons");
		StringBuilder sb;
		Resultado resultado = (Resultado) request.getAttribute("resultado");
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
                <a class="nav-link" href="http://localhost:8080/SysLibrary/carrinho.jsp">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-pedidos.jsp">
                  <span data-feather="shopping-bag"></span>
                  Pedidos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="./consultar-cupons.jsp">
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
			<div style="text-align: center;">
			<h3>Cupons </h3>  
				   <br>
            	</div>           
       <form action="BuscarCupomTroca" method="post" class="form-horizontal">
		<div class="row">
			<div class="col-lg-5">
			
			</div>
			<div class="col-4">
			<input id="search" name="search" class="form-control" placeholder="Pesquisar" type="text">
			</div>
			<div class="col-0">
			<input type='submit' class='btn btn-primary'  id='operacao' name='operacao' value='BUSCAR'/>
			 
				
		</div>
			
		</div>
		<!-- /#top -->

		<div id="list" class="row">	
			<div class="table-responsive col-md-12">
				<table class="table table-striped" >
					<thead>
						<tr>
							<th></th>
							<th class="text-center">ID Cupom</th>
							<th class="text-center">Valor </th>
							<th class="text-center">Data</th>
							<th class="text-center">Status</th>
							<th> </th>
							
						</tr>
					</thead>
					<tbody>

		            		<%
		            		
		            		
		            		if (cupons != null) {
			            		for (EntidadeDominio ed : cupons) {
			            			Cupom cp = (Cupom) ed;			            			
		            		if( cp.getStatusCupom().equals("USADO") || cp.getStatusCupom().equals("TROCA AUTORIZADA")){
		            			if(cp.getStatusCupom().equals("TROCA AUTORIZADA")){
		            				cp.setStatusCupom("ATIVO");
		            			}
		            		%>
						<tr>
							<th></th>
							<td style="text-align: center; vertical-align: middle;"><%=cp.getNomeCupom() %> </td>
							<td style="text-align: center; vertical-align: middle;">R$<%=String.format("%.2f",cp.getValorCupom()) %> </td>								
							<td style="text-align: center; vertical-align: middle;"><%=cp.getDataCupom() %> </td>																				
							<td style="text-align: center; vertical-align: middle;"><%=cp.getStatusCupom()%></td>
							<th> </th>
							
							
									
										
						</tr>
						<%
			            		}
						}
		            }
		            	%>
						

					</tbody>
				</table>
			</div>
			</div>
			</form>
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
