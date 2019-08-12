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
		Endereco endereco = (Endereco) request.getAttribute("enderecos");
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
            <a class="nav-link" href="./index.jsp">
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
                <a class="nav-link active" href="./form-endereco.jsp">
                  <span data-feather="file">(current)</span>
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
					<h3>Endereço </h3>           
            	</div>          
          	  	<form action="SalvarEndereco" method="post">
		
		<input type="hidden" name="endereco_id" value="<% if (endereco != null) out.print(endereco.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (endereco != null) out.print("ALTERAR"); else out.print("SALVAR");%>" />
		

			<!-- area de campos do form -->
				<hr/>
			<div class="container-fluid">
				<h6 class="page-header"> Descrição </h6>
					<div class="row">	
							
				<div class="form-group col-md-3">
					<input type="text" maxlength="13" class="form-control" id="descricao" name="descricao"
							value="<%if(endereco != null) out.print(endereco.getDescricao()); %>">
					</div>
					
					</div>
				</div>	
					
			<div class="container-fluid">
				<h6 class="page-header"> Caracteristicas </h6>

				<div class="row">
				
					<div class="form-group col-md-4">
						<label for="campo1">Logradouro</label> <input type="text" maxlength="13"
							class="form-control" id="logradouro" name="logradouro"
							value="<%if(endereco != null) out.print(endereco.getLogradouro()); %>">
					</div>
					<div class="form-group col-md-2">
						<label for="titulo">Numero</label> <input type="number"
							class="form-control" id="numero" name="numero"
							value="<%if(endereco != null) out.print(endereco.getNumero()); %>">
					</div>
					<div class="form-group col-md-3">
						<label for="ano">Bairro</label> <input type="text"
							class="form-control" id="bairro" name="bairro"
							value="<%if(endereco != null) out.print(endereco.getBairro()); %>">
					</div>
					<div class="form-group col-md-2">
						<label for="numpgs">CEP</label> <input type="text"
							class="form-control" id="cep" name="cep"
							value="<%if(endereco != null) out.print(endereco.getCep()); %>">
					</div>
				</div>
					
					<div class="row">
					
					<div class="form-group col-md-3">
						<label for="edicao">Cidade</label> <input type="text"
							class="form-control" id="cidade" name="cidade"
							value="<%if(endereco != null) out.print(endereco.getCidade()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="numpgs">Estado</label> 
						<select id="estado" name="estado" class="form-control">
							<%
								for (Estados estado : Estados.values()) {
							%>
							  <option id=<%=estado.name() %> value=<%=estado.name() %> <%if (endereco != null && endereco.getEstado().equals(estado.name())) out.print("selected"); %>><%=estado.name() %> </option>
							<%
								}
							%>
							</select>
							
					</div>
					
					<div class="form-group col-md-2">
						<label for="numpgs">Pais</label> <input type="text"
							class="form-control" id="pais" name="pais"
							value="<%if(endereco != null) out.print(endereco.getPais()); %>">
					</div>
					
				</div>


							<hr />
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>				
			
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
