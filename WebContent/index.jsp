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
    	//Usuario usuario = Logged.getUsuario();  
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if(usuario != null){
			usuario.getId();
		}
      
      %>

    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="http://localhost:8080/SysLibrary/index.jsp"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
        
        <%if(usuario != null){%>
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
            <a class="nav-link active" href="http://localhost:8080/SysLibrary/index.jsp">
                  <span data-feather="home"></span>
                  Home <span class="sr-only"></span>
                </a>
                </li>
                <%
					if(usuario != null){
          		%>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/profile.jsp">
                  <span data-feather="users"></span>
                  Seu Perfil <span class="sr-only"></span>
                </a>
                 <%} else {%>
			<%} %>
              </li>
               <li class="nav-item">
                <a class="nav-link" href="#">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
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
              <a class="nav-link" href="http://localhost:8080/SysLibrary/index.jsp?idCategoria=<%=categoria.getId()%>"> 
              <span data-feather="book"></span> <%=categoria.getNome()%>
              </a>
              </li>
              <%
			      }
			
			
			
			   %>

      
            </ul>
          </div>
        </nav>

        <!-- Header -->
    <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"> 
    		<div style="text-align: center;">
				<h3>Livros </h3>  <hr>         
            </div>        
    <div class="header bg-gradient-primary pb-8 pt-5 pt-md-8">
      <div class="container-fluid">
        <div class="header-body">			
			<div class="container">
				<div class="row">
					<!------ shopping Demo-3 ---------->
					<div class="container">
						
						<div class="row">
						    <%
						       int i;
						       boolean exibir;
						       String iCategoria = request.getParameter("idCategoria");
					      	   List<EntidadeDominio> livros = new LivroDAO().listarAtivos();
						       for (EntidadeDominio ed : livros) {
						    	   i = 0;
						    	   exibir = false;
						    	   Livro livro = (Livro) ed;
						    	   
						    	   if (iCategoria != null && !iCategoria.trim().equals("")) {
						    		   for (Categoria categoria : livro.getCategorias()) {
						    			   if (Integer.parseInt(iCategoria) == categoria.getId()) {
						    				   exibir = true;
						    			   }
						    		   }   
						    	   } else {
						    		   exibir = true;
						    	   }
						    	   
						    	   if (exibir) {
						    %>
							<div class="col">
								<div class="product-grid3">
									 <div style="text-align: center;">
										<a href="describe.jsp?IdLivro=<%=livro.getId()%>">
											<img class="pic-1" src="./resources/livros/<%=livro.getId()%>.jpg">	
										</a>	
									</div><br>
									 <div style="text-align: center;">
									 <%if(usuario != null){ %>
										<a href="SalvarCarrinho?operacao=SALVAR&idLivro=<%=livro.getId() %>&idUsuario=<%=usuario.getId() %>">Add-<span data-feather="shopping-cart">
										<%} %> 
										</span></a>
									</div>
								</div>
							</div>
							<%
						    	   }
						       }
		
							%>
						</div>
					</div>
<!-- 					<div> -->
<!-- 					<h1>Exemplos JQuery</h1> -->
<!-- 					<a href="paginaPai.jsp">Load jQuery</a> -->
<!-- 					</div> -->
				</div>
			</div>
        </div>
      </div>
    </div>
    </section>
      </div>	  
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
