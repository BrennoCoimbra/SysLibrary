<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.dominio.EntidadeDominio"%>
<%@page import="br.com.syslib.dominio.Livro"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.*"%>

<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8" />
<title>Listar de Livros</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width" />
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/animate.css" />
<link href="resources/bootstrap/css/style.css" rel="stylesheet">
<style>
.navbar-header button {
	color: #fff;
}

.fullscreen {
	width: 100%;
}

main {
	padding-top: 50px;
}

footer {
  position: fixed;
  left: 0;
  bottom: 0;
  width: 100%;
  background: #333;
  color: #fff;
  text-align: center;
}

.tab-pane {
	padding: 10px 5px;
}

.fonte-maior {
	font-size: 15px;
}

.pink {
	color: pink;
}

</style>
</head>
<body>

</body>
<body>
		 <%
		 Resultado resultado = (Resultado) request.getAttribute("resultado");
		 StringBuilder sb;
		 List<EntidadeDominio> livros = (List<EntidadeDominio>) request.getAttribute("livro");
		  %>
	<!-- Barra de navegação e busca -->
	<header>

		<nav class="navbar navbar-inverse  navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">:::Lista Livro:::</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">CADASTRO <span class="caret"></span></a>
						<ul class="dropdown-menu">

							<li><a href="CadastroUsuario.html">USUARIO</a></li>
							<li><a href="CadastrarLivro.html">LIVRO</a></li>
							<li><a href="Estoque.html">ESTOQUE</a></li>

						</ul></li>
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">LISTAR <span class="caret"></span></a>
						<ul class="dropdown-menu">

							<li><a href="ListarLivro.html">LIVRO</a></li>
							<li><a href="ListarUsuario.html">USUARIOS</a></li>


						</ul></li>
					<li><a href="Log.html">LOGS</a></li>
					<li><a href="Graficos.html">RELATORIOS</a></li>

				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Administrador.html"> <span
							class="glyphicon glyphicon-home"></span> HOME
					</a></li>
					<li><a href="Perfil.html"> <span
							class="glyphicon glyphicon-user"></span> Perfil
					</a></li>
					<li><a href="Login.html"> <span
							class="glyphicon glyphicon-log-in"></span> Sair
					</a></li>
				</ul>
			</div>
		</nav>

	</header>
	<!-- FIM Barra de navegação e busca -->

	<main>
	<div id="main" class="container-fluid">

		<div id="top" class="row">
			<div class="col-md-12">
				<h4 class="page-header">  </h4>
				
			 
				
				<h1 class="display-2 text-white">Consulta de Livros <span class="glyphicon glyphicon-book"></span></h1>
			
            <p class="text-white mt-0 mb-5">Esse é a consulta de livros. Vocé poderá consultar, deletar e selecionar para alterar as informações de um livro.</p>
                        
			</div>	
			
		</div>
		<hr />
	<form action="SalvarLivro" method="post" class="form-horizontal">
		<div class="row">
			<div class="col-lg-5">
			
			</div>
			<div class="col-lg-5">
			<input id="search" name="search" class="form-control" placeholder="Pesquisar" type="text">
			</div>
			<div class="col-lg-0">
			<input type='submit' class='btn btn-primary'  id='operacao' name='operacao' value='VISUALIZAR'/>
			 <a href="./FormLivro.jsp" class="btn btn-primary">NOVO</a>
				
		</div>
			
		</div>
		<!-- /#top -->

		<div id="list" class="row">	
			<div class="table-responsive col-md-12">
				<table class="table table-striped" >
					<thead>
						<tr>

							<th class="text-center">Titulo</th>
							<th class="text-center">Editora</th>
							<th class="text-center">Autor</th>
							<th class="text-center">Ativo</th>
							<th class="text-center">ISBN</th>
							<th style="text-align: right">Ações</th>
							<th></th>
						</tr>
					</thead>
					<tbody>

		            		<%
		            		
		            		
		            		if (livros != null) {
			            		for (EntidadeDominio ed : livros) {
			            			Livro livro = (Livro) ed;
			            			StringBuilder autores = new StringBuilder();
			        				boolean isSecondOrMore = false;
			        				
			        				for (Autor autor : livro.getAutores()) {
			        					if (isSecondOrMore)
			        						autores.append("/");
			        					autores.append(autor.getNome());
			        					
			        					isSecondOrMore = true;
			        				}
		            			
		            		
		            		%>
						<tr>
							
							<td style="text-align: center; vertical-align: middle;"><%=livro.getTitulo() %> </td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getEditora().getNome() %> </td>
							<td style="text-align: center; vertical-align: middle;"><%=autores.toString() %> </td>																				
							<td style="text-align: center; vertical-align: middle;"><% if(livro != null && livro.getAtivo()) out.print("sim"); else out.print("não") ; %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getISBN() %></td>
							
							<!-- Buttons actions -->
							<td style="text-align: right; ">
							<a href="SalvarLivro?operacao=CONSULTAR&IdLivro=<%=livro.getId() %>" class="btn btn-warning">Alterar</a> 
							</td>
							
							<td style="text-align: left; ">
							<a href="SalvarLivro?operacao=EXCLUIR&IdLivro=<%=livro.getId() %>"class="btn btn-danger">Remover</a>
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
				</div>		

	</main>
	<footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>