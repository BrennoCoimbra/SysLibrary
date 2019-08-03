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
		Livro liv = (Livro) request.getAttribute("livro");
		Usuario usuario = Logged.getUsuario();
		usuario.getId();
		StringBuilder sb;
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
            <a class="nav-link" href="./dashboard.jsp">
                  <span data-feather="home"></span>
                  Dashboard <span class="sr-only"></span>
                </a>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="./profile.jsp">
                  <span data-feather="users"></span>
                  Seu Perfil <span class="sr-only"></span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="./form-livro.jsp">
                  <span data-feather="book">(current)</span>
                  Cadastrar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-livro.jsp">
                  <span data-feather="book"></span>
                  Consultar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./estoque.jsp">
                  <span data-feather="package"></span>
                  Estoque
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./loan.jsp">
                  <span data-feather="rewind"></span>
                  Empréstimos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./returns.jsp">
                  <span data-feather=fast-forward></span>
                  Devoluções
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./reservas.jsp">
                  <span data-feather="calendar"></span>
                  Reservas
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
           <a class="nav-link" href="./reports.jsp">
              <span data-feather="file-text"></span> 
              Quantidade em Estoque
              </a>
              </li>
             

      
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div style="text-align: center;">
					<h3>Livros </h3>           
            	</div>          
          	  	<form action="SalvarLivro" method="post">
		
		<input type="hidden" name="IdLivro" value="<% if (liv != null) out.print(liv.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (liv == null) out.print("SALVAR"); else out.print("ALTERAR");%>" />


			<!-- area de campos do form -->
				<hr/>
			<div class="container-fluid">
				<h5 class="form"> Identificação </h5>
				<hr/>
				<div class="row">
				<div class="form-group col-sm-2">
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
					<textarea rows="4" id="txtMotivo" style="resize:none"  cols="num" rows="num" name="txtMotivo" class="form-control form-control-alternative" <%if (liv == null || (liv != null && !liv.getAtivo())) out.print("readonly"); %>><%if (liv != null) out.print(liv.getMotivo()); else out.print("Novo Livro."); %></textarea>
				</div>
				</div>
			</div>
			
			<div class="container-fluid">
			<div class="row">	
			<div class="form-group col-md-3">
						<label for="campo2">ISBN</label> <input type="text" maxlength="13"
							class="form-control" id="isbn" name="isbn"
							value="<%if(liv != null) out.print(liv.getISBN()); %>">
					</div>

					<div class="form-group col-md-3">
						<label for="campo1">Cód. Barras</label> <input type="text" maxlength="13"
							class="form-control" id="codBarras" name="codBarras"
							value="<%if(liv != null) out.print(liv.getCodBarras()); %>">
					</div>

					</div>
				</div>	
					
			<div class="container-fluid">
				<h5 class="page-header"> Caracteristicas </h5>
				<hr>
				<div class="row">
					<div class="form-group col-md-4">
						<label for="titulo">Titulo</label> <input type="text"
							class="form-control" id="titulo" name="titulo"
							value="<%if(liv != null) out.print(liv.getTitulo()); %>">
					</div>
					<div class="form-group col-md-2">
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
			
			</div>
			<div class="container-fluid">
				<h5 class="page-header"> Dimensões</h5>
					<hr>
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
			
		<div class="row">
					<div class="form-group col-md-8">
						<label for="campo3"></label>
						
					</div>
					</div>
	</form>
        </main>		
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
