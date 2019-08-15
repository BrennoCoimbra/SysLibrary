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
		Usuario usuario = Logged.getUsuario();
		usuario.getId();
		List<EntidadeDominio> cartao = (List<EntidadeDominio>) request.getAttribute("cartoes");
		StringBuilder sb;
		Resultado resultado = (Resultado) request.getAttribute("resultado");
		%>	
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="../login.jsp">Sign out <span data-feather="log-out"></span></a>
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
                <a class="nav-link active" href="./consultar-cartao.jsp">
                  <span data-feather="credit-card"></span>
                  Consultar Cartão
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
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

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Categorias</span>
              <a class="d-flex align-items-center text-muted" href="#">
               
              </a>
            </h6>
			        			      
            <ul class="nav flex-column mb-2">
            	
			<%
        	List<EntidadeDominio> categorias = new CategoriaDAO().listar();
            for (EntidadeDominio ed : categorias) {
            	Categoria categoria = (Categoria) ed;	
			%>
              
              <li class="nav-item">
              <a class="nav-link" href="index.jsp?idCategoria=<%=categoria.getId()%>"> 
              <span data-feather="book"></span> <%=categoria.getNome()%>
              </a>
              </li>
              <%
			      }
			   %>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div style="text-align: center;">
					<h3>Cartão Credito </h3>           
				   <br>
            	</div>           
       <form action="SalvarCartao" method="post" class="form-horizontal">
		<div class="row">
			<div class="col-lg-5">
			
			</div>
			<div class="col-4">
			<input id="search" name="search" class="form-control" placeholder="Pesquisar" type="text">
			</div>
			<div class="col-0">
			<input type='submit' class='btn btn-primary'  id='operacao' name='operacao' value='VISUALIZAR'/>
			 <a href="./form-cartao.jsp" class="btn btn-primary">NOVO</a>
				
		</div>
			
		</div>
		<!-- /#top -->

		<div id="list" class="row">	
			<div class="table-responsive col-md-12">
				<table class="table table-striped" >
					<thead>
						<tr>

							<th class="text-center">Descrição</th>
							<th class="text-center">Nome</th>
							<th class="text-center">Numero</th>
							<th class="text-center">Validade</th>
							<th class="text-center">CVV</th>
							<th class="text-center">Bandeira</th>
							<th style="text-align: right">Ações</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

		            		<%
		            		
		            		
		            		if (cartao != null) {
			            		for (EntidadeDominio ed : cartao) {
			            			CartaoCredito card = (CartaoCredito) ed;			            			
		            		
		            		%>
						<tr>
							
							<td style="text-align: center; vertical-align: middle;"><%=card.getDescricao() %> </td>
							<td style="text-align: center; vertical-align: middle;"><%=card.getNomeCartao() %> </td>														
							<td style="text-align: center; vertical-align: middle;"><%=card.getNumeroCartao() %> </td>																				
							<td style="text-align: center; vertical-align: middle;"><%=card.getMes() + "/" + card.getAno() %></td>
							<td style="text-align: center; vertical-align: middle;"><%=card.getCodigoSeguranca() %> </td>
							<td style="text-align: center; vertical-align: middle;"><%=card.getBandeiraCartao().getDescricao() %></td>
							
							<!-- Buttons actions -->
							<td style="text-align: right; ">
							<a href="SalvarCartao?operacao=CONSULTAR&cartao_id=<%=card.getId() %>" class="btn btn-warning">Alterar</a> 
							</td>
							
							<td style="text-align: left; ">
							<a href="SalvarCartao?operacao=EXCLUIR&cartao_id=<%=card.getId() %>"class="btn btn-danger">Remover</a>
							</td>				
										
						</tr>
						<%
						}
		            }
		            	%>
						

					</tbody>
				</table>
			</div>
			</div>
			</form>
        </main>		
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
