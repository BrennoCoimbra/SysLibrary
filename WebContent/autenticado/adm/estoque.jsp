<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.dominio.EntidadeDominio"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>
<%@page import="br.com.syslib.core.util.*"%>
<%@page import="br.com.syslib.enuns.*" %>


<html>
  <head>
    <meta charset="UTF-8">
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
		 Resultado resultado = (Resultado) request.getAttribute("resultado");
		 StringBuilder sb;
		 Livro livro = (Livro) request.getAttribute("livro");
		  %>
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="./login.jsp">Sign out <span data-feather="log-out"></span></a>
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
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/profile.jsp">
                  <span data-feather="users"></span>
                  Seu Perfil <span class="sr-only"></span>
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
                <a class="nav-link active" href="http://localhost:8080/SysLibrary/autenticado/adm/estoque.jsp">
                  <span data-feather="package"></span>
                  Estoque
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


             <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div style="text-align: center;">
					<h3>Estoque </h3>   				      
					<hr>
            </div>  
            <form action="SalvarLivro" method="post" class="form-horizontal">
            <!-- Form para buscar o dados do livro -->	          
           	<div id="list" class="row">	
				<div class="col-lg-6">			
				</div>
				<div class="col-4">
					<input id="search" name="search" class="form-control" placeholder="Digite o ISBN, para buscar o livro." type="text">
				</div>
				
				<div class="col-0">
					<input type='submit' class='btn btn-primary'  id='operacao' name='operacao' value='BUSCAR'/>					
				</div>
				
			</div>			
			<hr>
					            			
			<!-- Form para trazer o dados do livro -->
			
			<div class="container-fluid">
			<h5 class="page-header"> Entrada em Estoque </h5>
			<input type="hidden" id="idLivro=<% if(livro != null) out.print(livro.getIdLivro());%>" name="idLivro" value="<% if(livro != null) out.print(livro.getIdLivro());%>"/>  							
			<div class="row">	
			<div class="form-group col-md-3">
						<label for="campo2">ISBN</label> <input type="text" maxlength="13" readonly
							class="form-control" id="isbn" name="isbn"							
							value="<% if(livro != null) out.print(livro.getISBN()); %>">
					</div>

					<div class="form-group col-md-3">
						<label for="campo1">Nome do livro</label> <input type="text" maxlength="13" readonly
							class="form-control" id="codBarras" name="codBarras"
							value="<% if(livro != null) out.print(livro.getTitulo()); %>">
					</div>	

				<div  class="form-group col-md-2">
						<label for="campo1">Precificação</label> 
						<input type="text" maxlength="13" readonly
							class="form-control" id="codBarras" name="codBarras" readonly
							value="<% if(livro != null) out.print(livro.getPreficacao().getDescricao()); %>">
						 
					</div>
					
			</div>
			</div>
			<!-- Form para salvar o livro em estoque.-->
			</form>
			<div class="container-fluid">
			<form action="SalvarEstoque" method="post" class="form-horizontal">	
			<% if(livro != null) {%>
						
			<div class="row">	
			<input type="hidden" id="idLivro" name="idLivro" value="<%= livro.getIdLivro()%>"/>  
			<div class="form-group col-md-3">
			
			<label for="campo2">Fornecedor</label> 						
						<select id="fornecedor" name="fornecedor" class="form-control">
								<%
									List<EntidadeDominio> fornecedores = new FornecedorDAO().visualizar();

									for (EntidadeDominio ed : fornecedores) {
										Fornecedor fornecedor = (Fornecedor) ed;
								%>
								<option id=<%=fornecedor.getId() %> value=<%=fornecedor.getId() %>><%=fornecedor.getNomeFornecedor() %> </option>								
								<%
									}
								%>
							</select>				
					</div>

					<div class="form-group col-md-3">
						<label for="campo1">Quantidade</label> <input type="text" maxlength="13"
							class="form-control" id="qtde" name="qtde"
							value="">
					</div>
					<div class="form-group col-md-3">
						<label for="campo1">Valor</label> <input type="text" maxlength="13"
							class="form-control" id="valorCompra" name="valorCompra"
							value="">
					</div>	
					</div>	
					<div class=col>            	
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>
            	 </div>    	
			</form>
				<%} %>															
			</div>
			
						
			</div>
			
          	  	<div class="row">
					<div class="form-group col-md-8">
						<label for="campo3"></label>
						
					</div>
					</div>
					
        </main>	
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
