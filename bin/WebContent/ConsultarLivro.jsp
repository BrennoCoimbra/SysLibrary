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
	background: #333;
	color: #fff;
	padding: 20px 0;
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
			<div class="col-md-3">
				<h2>Listagem Livros</h2>
			</div>


			<div class="col-md-9">
				<a href="./ConsultarLivro.jsp" class="btn btn-primary pull-right h2">BuscarLivro</a> 
				<a href="./FormLivro.jsp" class="btn btn-primary pull-right h2">Novo</a>
			</div>
		</div>
		<!-- /#top -->

		<hr />

	<form action="SalvarLivro" method="post" class="form-horizontal">
		<div id="list" class="row">
				
			<div class="table-responsive col-md-12">
 			<input type="hidden" name="operacao" id="operacao" value="SALVAR"/> 
			
				<table class="table table-striped" cellspacing="0" cellpadding="0">
					<thead>
						<tr>
							
							<th class="text-center">ID</th>
							<th class="text-center">Ativo</th>
							<th class="text-center">ISBN</th>
							<th class="text-center">Cód. Barras</th>
							<th class="text-center">Titulo</th>
							<th class="text-center">Quantidade</th>
							<th class="text-center">Autor</th>
							<th class="text-center">Categoria</th>
							<th class="text-center">Ano</th>
							<th class="text-center">Editora</th>
							<th class="text-center">Sinopse</th>
							<th class="text-center">Altura</th>
							<th class="text-center">Largura</th>
							<th class="text-center">Peso</th>
							<th class="text-center">Profundidade</th>
							<th class="actions"></th>
							<th class="actions"></th>
						</tr>
					</thead>
					<tbody>

		            		<%
		            		
		            		List<EntidadeDominio> livros = new LivroDAO().consultar(new Livro());
		            		if (livros != null) {
		            		for(EntidadeDominio ed : livros){
		            			Livro livro = (Livro)ed;
		            			
		            		
		            		%>
						<tr>						
							<td style="text-align: center; vertical-align: middle;"><%=livro.getId() %></td>
							<td style="text-align: center; vertical-align: middle;"><% if(livro != null && livro.getAtivo()) out.print("sim"); else out.print("não") ; %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getISBN() %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getCodBarras() %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getTitulo() %> </td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getQuantidade() %></td>
							<td style="text-align: center; vertical-align: middle;"><% %></td>
							<td style="text-align: center; vertical-align: middle;"><% %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getAno() %></td>
							<td style="text-align: center; vertical-align: middle;"><% %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getSinopse() %></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getAltura() %>cm</td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getLargura() %>cm</td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getPeso() %>g</td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getProfundidade() %>cm</td>
							<td style="text-align: center; vertical-align: middle;"><a class="btn btn-alert ico-alert" href="SalvarLivro?operacao=CONSULTAR&IdLivro=<%=livro.getId() %>">Alterar</a></td>
							<td style="text-align: center; vertical-align: middle;"><a class="btn btn-alert ico-warning" href="SalvarLivro?operacao=EXCLUIR&IdLivro=<%=livro.getId() %>">Remover</a></td>							
						</tr>
						<%
						}
		            }else{
		            	out.print("nao");
		            }
		      	
		            	%>
						

					</tbody>
				</table>
			</div>
				</div>		
	</form>
			</div>

	</main>
	<footer> Todos os direitos reservados - Biblioteca
		Copyright©2019 </footer>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
</body>
</html>