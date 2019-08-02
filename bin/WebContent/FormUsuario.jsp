<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.dominio.EntidadeDominio"%>
<%@page import="br.com.syslib.dominio.Livro"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>

<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8"/>
<title>Cadastro de Usuarios</title>
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
	Usuario user = (Usuario) request.getAttribute("usuario");
	Endereco end = (Endereco) request.getAttribute("endereco");	
	StringBuilder sb;
	%>
	<main>
	<!-- Barra de navegação e busca -->
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

	<div id="main"class="container-fluid">
		<div class="row">
			 <div class="form-group col-md-2">
		<h4 class="page-header">
			<div class="form-group">
 			<h4 class="page-header">USUARIOS  <span class="glyphicon glyphicon-user"></span> </a></li></h4>
 				<form action="SalvarUsuario" method="post">
 				
 		<input type="hidden" name="idUsuario" value="<% if (user != null) out.print(user.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (user == null) out.print("SALVAR"); else out.print("ALTERAR");%>" />
		
		  <select id="idTpUsuario" name="idTpUsuario" class="form-control">
		  <%
		  TipoUsuario tpUser = new TipoUsuario();
		  List<EntidadeDominio> tpUsers = new TipoUsuarioDAO().consultar(tpUser);
		  for(EntidadeDominio tpU : tpUsers){
			  TipoUsuario tipoUsuario = (TipoUsuario) tpU;  
		  %>		  
		  <option id=<%=tipoUsuario.getId() %> value=<%=tipoUsuario.getId() %> <%if (user != null && user.getTipoUsuario().getId() == tipoUsuario.getId()) out.print("selected"); %>><%=tipoUsuario.getDescricao() %></option>
		  <%
		  }
		  %>
		  </select>
		  
		</div>  
		 <span class="glyphicon glyphicon-"></span> </a></li></h4>
				<label><input type="checkbox" name="isAtivo" value=value=1 <%if (user == null || (user != null && user.getAtivo())) out.print("checked"); %>/>Ativo</label>
				</div>
		 </div>

		


	
			  <!-- area de campos do form -->

			  <div class="container-fluid">
		<h4 class="page-header">Dados Login </a></li></h4>
		
		<div class="row">
			 <div class="form-group col-md-3">
			   <label for="campo2">Login</label>
			   <input type="text" class="form-control" id="login" name="login"
			   value="<%if(user != null) out.print(user.getEmail()); %>">
			 </div>
			 
			 <div class="form-group col-md-2">
			   <label for="campo1">Senha</label>
			   <input type="text" class="form-control" id="senha"  name="senha"
			    value="<%if(user != null) out.print(user.getSenha()); %>">
			 </div>
			 
			 <div class="form-group col-md-2">
			    <label for="campo1">Confirma Senha</label>
			   <input type="text" class="form-control" id="confirmaSenha" name="confirmaSenha"
			    value="<%if(user != null) out.print(user.getConfirmaSenha()); %>">
			 </div>

			 <div class="form-group col-md-5">
			   
			 </div>
			</div>
		</div>
				<div class="container-fluid">
		<h4 class="page-header">Identificação </a></li></h4>

			 <div class="row">
			 	<div class="form-group col-md-3">
			   <label for="campo1">Nome</label>
			   <input type="text" class="form-control" id="nome" name="nome"
			    value="<%if(user != null) out.print(user.getNome()); %>">
			 </div>
			 <div class="form-group col-md-2">
			   <label for="campo3">Data Nascimento</label>
			   <input type="date" class="form-control" id="dtNascimento" name="dtNascimento"
			    value="<%if(user != null) out.print(user.getDt_nascimento()); %>">
			 </div>
			 <div class="form-group col-md-3">
			   <label for="campo1">CPF</label>
			   <input type="text" class="form-control" id="cpf" name="cpf"
			    value="<%if(user != null) out.print(user.getCpf()); %>">
			 </div>
			 <div class="form-group col-md-2">
			   
			 </div>
			 </div>


			 <div class="row">
			 	<div class="form-group col-md-3">
			   <label for="campo1">Telefone</label>
			   <input type="text" class="form-control" id="telefone" name="telefone"
			    value="<%if(user != null) out.print(user.getTelefone()); %>">
			 </div>
			 <div class="form-group col-md-3">
			   <label for="campo3">Celular</label>
			   <input type="text" class="form-control" id="celular" name="celular"
			    value="<%if(user != null) out.print(user.getCelular()); %>">
			 </div>
			 <div class="form-group col-md-1">
			  
			 </div>
			 <div class="form-group col-md-3">
			   
			 </div>
			 <div class="form-group col-md-2">
			   
			 </div>
			 </div>
			</div>

			 		<div class="container-fluid">
		<h4 class="page-header">Endereço</a></li></h4>
			  <div class="row">
			 	<div class="form-group col-md-2">
			   <label for="tpResidencia">Tipo de Residência	</label> 
			<input type="text" class="form-control" id="tpResidencia" name="tpResidencia"
			  value="<%if(end != null) out.print(end.getTpResidencia()); %>">		 
			 </div>
			 <div class="form-group col-md-2">
			   <label for="tpLogradouro">Tipo do Logradouro</label>
				<input type="text" class="form-control" id="tpLogradouro" name="tpLogradouro"
				  value="<%if(end != null) out.print(end.getTpLogradouro()); %>">			
			 </div>
			 <div class="form-group col-md-3">
			   <label for="isbn">Logradouro</label>
					<input id="logradouro" class="form-control" type="text" name="logradouro"
					value="<%if(end != null) out.print(end.getLogradouro()); %>">		
			 </div>
			 <div class="form-group col-md-1">
			  <label for="isbn">Numero</label>
								<input id="numero" class="form-control" type="text" name="numero"
								value="<%if(end != null) out.print(end.getNumero()); %>">
			 </div>
			  <div class="form-group col-md-2">
			   <label for="isbn">CEP</label>
								<input id="cep" class="form-control" type="text" name="cep"
								value="<%if(end != null) out.print(end.getCep()); %>">
			 </div>
			 <div class="form-group col-md-2">
			   <label for="isbn">Bairro</label>
								<input id="bairro" class="form-control" type="text" name="bairro"
								value="<%if(end != null) out.print(end.getBairro()); %>">
			 </div>
			 </div>
			  <div class="row">
			 	<div class="form-group col-md-3">
			   <label for="isbn">Cidade</label>
								<input id="cidade" class="form-control" type="text" name="cidade"
								value="<%if(end != null) out.print(end.getCidade()); %>">
			 </div>
			 <div class="form-group col-md-1">
			  <label for="isbn">Estado</label>
								<input id="estado" class="form-control" type="text" name="estado"
								value="<%if(end != null) out.print(end.getEstado()); %>">
			 </div>
			 <div class="form-group col-md-1">
			  <label for="isbn">País</label>
								<input id="pais" class="form-control" type="text" name="pais"
								value="<%if(end != null) out.print(end.getPais()); %>">
			 </div>
			 <div class="form-group col-md-3">
			   
			 </div>
			 <div class="form-group col-md-2">
			   
			 </div>
			 </div>
			</div>


			  <hr />
			  <div id="actions" class="row">
			    <div class="col-md-12">
			    	<%		
				
			if(user != null) {
				out.print("<input type='submit' id='operacao' name='operacao' value='ALTERAR'/>");
				
				out.print("<input type='submit' id='operacao' name='operacao' value='EXCLUIR'/>");	
			}else{
				out.print("<input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/>");
			}
		%>
		</form>
		<br>
			    </div>
			  </div>
		</div><!-- FIM container fluid-->
		<!-- FIM container fluid--> </main>

	<footer> Todos os direitos reservados - Biblioteca
		Copyright©2019 </footer>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<script type="text/javascript" src="js/wow.min.js"></script>
	<script>
		$(function() {
			new WOW().init();
		});
	</script>
</body>
</html>
