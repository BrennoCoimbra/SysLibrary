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
<title>SysLibrary</title>
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
.textarea {
   resize: none;
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


					<li><a href="./ConsultarLivro.jsp">Consulta Livros</a></li>

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

 	<!-- CATEGORIAS -->
	<div id="main" class="container-fluid">
	<form action="SalvarLivro" method="post">
		
		<input type="hidden" name="IdLivro" value="<% if (liv != null) out.print(liv.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (liv == null) out.print("SALVAR"); else out.print("ALTERAR");%>" />
	
		<div class="container-fluid">
				<h4 class="page-header">  </h4>
			
				<h1 class="display-2 text-white">Livros <span class="glyphicon glyphicon-book"></span></h1>
            <p class="text-white mt-0 mb-5">Esse é o cadastro de livros. Vocé poderá incluir e alterar as informações de um livro.</p>
          
            
           
          		
			</div>
			<!-- area de campos do form -->
				<hr/>
			<div class="container-fluid">
				<h4 class="form"> Identificação </h4>
				<hr/>
				<div class="row">
				<div class="form-group col-md-3">
					<label class="form-control-label" for="txtAtivo">Ativo</label>
					<select id="txtAtivo" name="txtAtivo" class="form-control">
							<%
								if (liv == null || (liv != null && !liv.getAtivo())) {
							%>  
								<option id=0 value=0 selected>Não</option>
							<%
								} else if (liv != null && liv.getAtivo()) {
							%>
								<option id=0 value=0 >Não</option>
								<option id=1 value=1 selected>Sim</option>
							<%	
								}
							%>
							</select>					
					</div>
					<div class="form-group col-md-3">
					<label class="form-control-label" for="txtAtivo">Motivo</label>
					<textarea rows="4" id="txtMotivo" style="resize:none"  cols="num" rows="num" name="txtMotivo" class="form-control form-control-alternative" placeholder="Motivo" <%if (liv == null || (liv != null && !liv.getAtivo())) out.print("readonly"); %>><%if (liv != null) out.print(liv.getMotivo()); else out.print("Novo Livro."); %></textarea>
				</div>
				</div>
			</div>
			<div class="row">	
			<div class="container-fluid">
			<div class="form-group col-md-2">
						<label for="campo2">ISBN</label> <input type="text" maxlength="13"
							class="form-control" id="isbn" name="isbn"
							value="<%if(liv != null) out.print(liv.getISBN()); %>">
					</div>

					<div class="form-group col-md-2">
						<label for="campo1">Cód. Barras</label> <input type="text" maxlength="13"
							class="form-control" id="codBarras" name="codBarras"
							value="<%if(liv != null) out.print(liv.getCodBarras()); %>">
					</div>

					<div class="form-group col-md-4">
					
					</div>
					
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
						<label for="campo4">Categoria</label> 
							  <select multiple id="txtCategoria" name="txtCategoria"  class="form-control" data-actions-box="true">
							<%
							Categoria categ = new Categoria();	
							List<EntidadeDominio> categorias = new CategoriaDAO().consultar(categ);
							for (EntidadeDominio cat : categorias) {
								Categoria categoria = (Categoria) cat;
								boolean selected = false;

								
								if(liv != null){
									for (Categoria livroCategoria : liv.getCategorias()) {
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
								
								if(liv != null){
									for(Autor livroAutor : liv.getAutores()){
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
						<textarea rows="4" style="resize:none"  cols="num" rows="num" id="txtSinopse" name="txtSinopse" class="form-control form-control-alternative" ><%if (liv != null) out.print(liv.getSinopse()); %></textarea>
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
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>				
			
	</div>
		<%-- <%	
				
			if(liv != null) {
				out.print("<input type='submit' id='operacao' name='operacao' value='ALTERAR'/>");
				
				out.print("<input type='submit' id='operacao' name='operacao' value='EXCLUIR'/>");	
			}else{
				out.print("<input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/>");
			}
		%> --%>
		
		
	</form>
		</div>
	
	<!-- FIM container fluid--> 

	<footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</body>
</html>

