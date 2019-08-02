<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.dominio.EntidadeDominio"%>
<%@page import="br.com.syslib.dominio.Livro"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>

<html>
<head>
<meta charset="UTF-8" />
<title>Cadastro de Livros</title>
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
	<%
	Livro liv = (Livro) request.getAttribute("livro");
	StringBuilder sb;
	%>
	 <!-- Main content -->
	<!-- Barra de navegacao e busca -->
	<header>

		<nav class="navbar navbar-inverse  navbar-fixed-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="Index.html">:::LIVROS:::</a>
				</div>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" >LIVROS <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="CadastrarLivro.html">CADASTRO</a></li>
							<li><a href="Estoque.html">ESTOQUE</a></li>
						</ul></li>


					<li><a href="#">RELATORIOS</a></li>

				</ul>
				
				
				<ul class="nav navbar-nav navbar-right">
					<li><a href="Bibliotecario.html"> <span
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
	<% 
	Livro livro = (Livro) request.getAttribute("livro");
	%>
 	<!-- CATEGORIAS -->
	<div id="main" class="container-fluid">
	<form action="SalvarLivro" method="post">
		<h4 class="page-header"> LIVROS <span class="glyphicon glyphicon-book"></span> </h4>
		  <button type="submit" class="btn btn-primary">Salvar</button>
		<input type="hidden" name="IdLivro" value="<% if (liv != null) out.print(liv.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (liv == null) out.print("SALVAR"); else out.print("ALTERAR");%>" />
		<label><input type="checkbox" name="isAtivo" value=value=1 <%if (livro == null || (liv != null && liv.getAtivo())) out.print("checked"); %>/>Ativo</label>

			<!-- area de campos do form -->

			<div class="container-fluid">
				<h4 class="page-header"> Identificação </h4>
				<div class="row">
					<div class="form-group col-md-2">
						<label for="campo2">ISBN</label> <input type="text"
							class="form-control" id="isbn" name="isbn"
							value="<%if(liv != null) out.print(liv.getISBN()); %>">
					</div>

					<div class="form-group col-md-2">
						<label for="campo1">Cód. Barras</label> <input type="text"
							class="form-control" id="codBarras" name="codBarras"
							value="<%if(liv != null) out.print(liv.getCodBarras()); %>">
					</div>

					<div class="form-group col-md-4"></div>

					<div class="form-group col-md-4"></div>
				</div>
			</div>
			<div class="container-fluid">
				<h4 class="page-header"> Caracteristicas </h4>

				<div class="row">
					<div class="form-group col-md-3">
						<label for="titulo">Titulo</label> <input type="text"
							class="form-control" id="titulo" name="titulo"
							value="<%if(liv != null) out.print(liv.getTitulo()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="ano">Ano</label> <input type="text"
							class="form-control" id="ano" name="livro_ano"
							value="<%if(liv != null) out.print(liv.getAno()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="edicao">Edição</label> <input type="text"
							class="form-control" id="edicao" name="edicao"
							value="<%if(liv != null) out.print(liv.getEdicao()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="numpgs">Num.Pgs</label> <input type="text"
							class="form-control" id="numPgs" name="numPgs"
							value="<%if(liv != null) out.print(liv.getNumPaginas()); %>">
					</div>
					<div class="form-group col-md-2">
						<label for="campo1">Editora</label> 
						 <select id="txtEditora" name="txtEditora" class="form-control">
							<%
							Editora edits = new Editora();
							List<EntidadeDominio> edit = new EditoraDAO().consultar(edits);
							for (EntidadeDominio ed : edit) {
								Editora editora = (Editora) ed;
							%>
							<option id=<%=editora.getId() %> value=<%=editora.getId() %> <%if (liv != null && liv.getEditora().getId() == editora.getId()) out.print("selected"); %>><%=editora.getNome() %></option>
							<%
								}
							%>
							</select>
					</div>
				</div>


				<div class="row">							
					<div class="form-group col-md-3">
						<label for="campo3">Categoria</label> 
							  <select multiple id="txtCategoria" name="txtCategoria"  class="form-control" data-actions-box="true">
							<%
							Categoria categ = new Categoria();	
							List<EntidadeDominio> categorias = new CategoriaDAO().consultar(categ);
							for (EntidadeDominio cat : categorias) {
								Categoria categoria = (Categoria) cat;
								boolean selected = false;

								
								if(livro != null){
									for (Categoria livroCategoria : livro.getCategorias()) {
										if (livroCategoria.getId() == categoria.getId()) {
											selected = true;
										}										
									}
								}	
								
							%>
							<option id=<%=categoria.getId() %> value=<%=categoria.getId() %> <%if(selected) out.print("selected"); %>><%=categoria.getNome() %></option>
							<%				
							}							
							%>
							
						</select>
					</div>
					
					
					<div class="form-group col-md-3">
						<label for="campo1">Autor</label> 
						 <select multiple id="txtAutor" name="txtAutor" class="form-control" data-actions-box="true">
							<%
							Autor aut = new Autor();
							List<EntidadeDominio> autores = new AutorDAO().consultar(aut);
							for (EntidadeDominio au : autores) {
								Autor autor = (Autor) au;
								boolean selected = false;
								
								if(livro != null){
									for(Autor livroAutor : livro.getAutores()){
										if(livroAutor.getId() == autor.getId()){
										selected= true;																												
										}
										
									}
									
								}
										%>
										<option id=<%=autor.getId() %> value=<%=autor.getId() %> <%if(selected) out.print("selected"); %>><%=autor.getNome() %></option>
										<%
								
									}
								%>
								

							
						</select>
					</div>
					</div>

				<div class="row">
					<div class="form-group col-md-8">
						<label for="campo3">Sinopse</label>
						<textarea id="sinopse" rows="4" cols="40" maxlength="200"
							class="form-control" name="sinopse" style="text-align:left">
							  <%if (livro != null) out.print(livro.getSinopse()); %></textarea>
					</div>
					 


				</div>
			

			<div class="container-fluid">
				<h4 class="page-header"> Dimensões</h4>

				<div class="row">
					<div class="form-group col-md-1">
						<label for="campo1">Altura(cm)</label> <input type="text"
							class="form-control" id="campo1"name="altura"
							value="<%if(liv != null) out.print(liv.getAltura()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="campo3">Largura(cm)</label> <input type="text"
							class="form-control" id="campo3" name="largura"
							value="<%if(liv != null) out.print(liv.getLargura()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="campo3">Peso(kg)</label> <input type="text"
							class="form-control" id="campo3" name="peso"
							value="<%if(liv != null) out.print(liv.getPeso()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="campo1">Profundidade(cm)</label> <input type="text"
							class="form-control" id="campo1" name="prof"
							value="<%if(liv != null) out.print(liv.getProfundidade()); %>">
					</div>
					<div class="form-group col-md-3"></div>

				</div>
			</div>


			<hr />
			<div id="actions" class="row">
				<div class="col-md-12">
				
			<br>
				</div>
			</div>
	</div>
	</form>
		</div>
	
	<!-- FIM container fluid--> 

	<footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</body>
</html>

