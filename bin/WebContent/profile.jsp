<!DOCTYPE html>
<%@page import="br.com.syslib.enuns.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.util.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>
<%@page contentType="text/html" import="java.util.*, java.text.*" pageEncoding="UTF-8"%>

<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Página para buscar livros e acessar informações.">
    <meta name="author" content="Brenno Coimbra">
    <link rel="icon" href="./resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Bootstrap core CSS -->
   <link href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="./resources/bootstrap/css/dashboard.css" rel="stylesheet">
	
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
              <a class="nav-link" href="./index.jsp">
                  <span data-feather="home"></span>
                  Home <span class="sr-only"></span>
                </a>
                </li>
                 <li class="nav-item">                
                <a class="nav-link active" href="./profile.jsp">
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
                <a class="nav-link" href="#">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Empréstimos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="file-text"></span>
                  Devoluções
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
              <a class="nav-link" href="Index_Aluno.jsp?cat_id=<%=categoria.getId()%>"> 
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
					<h3>Perfil </h3>
					<hr>   
            	</div>          
          <form action="SalvarUsuario" method="POST">
		<div class="container-fluid">
		<div class="row">	
      		   <input type="hidden" name="idUsuario" value="<%=usuario.getId() %>" />
               <input class="form-control" type="hidden" id="operacao" name="operacao" value="ALTERAR">
      	<div class="form-group col-md-3">
      		<label for="nome">Nome</label>
       	 	<input type="text" value="<%=usuario.getNome() %>"  class="form-control input-lg" id="nome" name="nome" placeholder="Nome">
      	</div>
      	
      		<div class="form-group col-md-3">
      	<label class="form-control-label" for="cpf">CPF</label>
       	 	<input readonly type="text" value="<%=usuario.getCpf() %>" class="form-control input-lg" id="cpf" name="cpf" placeholder="CPF">
      	</div>
      	
      		<div class="form-group col-md-3">
      	<label class="form-control-label" for="email">Email</label>
       	 	<input readonly type="email" value="<%=usuario.getEmail() %>"class="form-control input-lg" id="email" name="email" placeholder="Email">
      	</div>
      	    </div>
      	
						<hr />
				<h4 class="page-header"> <input type="submit" value="SALVAR" class="btn btn-success"></></h4>
			
         </div>
      </form>
    </div>
        </main>		
      </div>	  
    <!-- Icons -->    
	<script src="./resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="./resources/bootstrap/js/feather.min.js"></script>
    <script>
    feather.replace()
    </script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
