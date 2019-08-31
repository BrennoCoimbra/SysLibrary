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
    <link rel="icon" href="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Bootstrap core CSS -->
   <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/dashboard.css" rel="stylesheet">
    <script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.4.1.min.js"></script>
  	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/bootstrap.min.js"></script>
	
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
  		Usuario usuario = (Usuario) session.getAttribute("usuario");  		
    	%>	
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="http://localhost:8080/SysLibrary/index.jsp"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
        
        <%if(usuario != null){%>
        <a id="signOut" class="nav-link" href="/SysLibrary/SairSys?operacao=SAIR">Sign out <span data-feather="log-out"></span></a>
        	
		<%  } else { %>
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
            <a class="nav-link" href="http://localhost:8080/SysLibrary/index.jsp">
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
                <a class="nav-link" href="./form-cartao.jsp">
                  <span data-feather="credit-card"></span>
                  Cadastrar Cartão
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-cartao.jsp">
                  <span data-feather="credit-card"></span>
                  Consultar Cartão
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/carrinho.jsp">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-pedidos.jsp">
                  <span data-feather="shopping-bag"></span>
                  Pedidos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-cupons.jsp">
                  <span data-feather="file-text"></span>
                  Cupons
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-trocas.jsp">
                  <span data-feather="code"></span>
                  Trocas
                </a>
              </li>
            </ul>

            
          </div>
        </nav>
		
        <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        
     
        <%
        Cliente cliente = new Cliente();
        if(cliente != null){
        	cliente = (Cliente) usuario;
        }
        
        %>
        
			<div style="text-align: center;">
					<h3>Perfil </h3>
					<hr>   
            	</div>          
          <form action="SalvarCliente" method="POST">
		<div class="container-fluid">
		<div class="row">	
	      	<input type="hidden" name="idUsuario" value="<%if(cliente != null) out.print(usuario.getId()); %>" /> 
	      	<input type="hidden" id="tpUsu" name="tpUsu" value="<%if(usuario != null) out.print(usuario.getTipoUsuario()); %>" />   		   
			<input type="hidden" name="operacao" value="<% if (cliente == null) out.print("SALVAR"); else out.print("ALTERAR");%>" />
              
      	<div class="form-group col-md-3">
      		<label for="nome">Nome</label>
       	 	<input type="text" value="<%if(cliente != null) out.print(cliente.getNome()); %>"  class="form-control input-lg" id="nome" name="nome" placeholder="Nome">
      	</div>
      	
      		<div class="form-group col-md-3">
      	<label class="form-control-label" for="cpf">CPF</label>
       	 	<input readonly type="text" value="<%if(cliente != null) out.print(cliente.getCpf()); %>" class="form-control input-lg" id="cpf" name="cpf" placeholder="CPF">
      	</div>
      	
      		<div class="form-group col-md-3">
      	<label class="form-control-label" for="email">Email</label>
       	 	<input type="email" value="<%if(cliente != null) out.print(cliente.getEmail()); %>"class="form-control input-lg" id="email" name="email" placeholder="Email" required>
      	</div>
      	    </div>
      	    <div class="row">
      	    <div class="form-group col-md-3">
      	<label class="form-control-label" for="dataNasc">Data Nascimento</label>
       	 	<input  type="date" value="<%if(cliente != null) out.print(cliente.getDataNasc()); %>" class="form-control input-lg" id="dataNasc" name="dataNasc">
      	</div>
      	<div class="form-group col-md-3">
      	<label class="form-control-label" for="genero">Gênero</label>
		<select id="genero" name="genero" class="form-control">
			<%
			
				for (Genero generos : Genero.values()) {
			%>
			   <option id=<%=generos.getCodigo() %> value=<%=generos.getCodigo() %> <%if (cliente != null && cliente.getGenero().getCodigo() == generos.getCodigo()) out.print("selected"); %>><%=generos.getDescricao() %> </option>
			<%
				}
			%>
			</select>	
      		</div>
      	    </div>
      	    <div class="row">
      	    <div class="form-group col-md-3">
      	    <label class="form-control-label" for="telefone">Tipo Telefone</label>
		       <select id="tpTelefone" name="tpTelefone" class="form-control">
					<%
					
						for (TipoTelefone tpTelefone : TipoTelefone.values()) {
					%>
					  <option id=<%=tpTelefone.getCodigo() %> value=<%=tpTelefone.getCodigo() %> <%if (cliente != null && cliente.getTelefone().getTpTelefone().getCodigo() == tpTelefone.getCodigo()) out.print("selected"); %>><%=tpTelefone.getDescricao() %> </option>
					<%
						}
					%>
					</select>
					
					
		      </div>
		      <div class="form-group col-md-1">
		      <label class="form-control-label" for="DDD">DDD</label>
		      <input type="text" name="ddd" id="ddd" class="form-control" placeholder=DDD
		      value="<%if(cliente != null) out.print(cliente.getTelefone().getTelDDD()); %>">	 
		      </div>
		      <div class="form-group col-md-3">
		      <label class="form-control-label" for="dataNasc">Telefone</label>
		       <input type="tel" name="telefone" id="telefone" class="form-control" placeholder=Numero 
		       value="<%if(cliente != null) out.print(cliente.getTelefone().getNumTel()); %>">
		       </div>
		      </div>
		      	          	
						<hr />
			 <div class="row">
      	    	<div class="form-group col-md-1">			
					 <input type="submit" value="SALVAR" class="btn btn-success">
				</div>
<!-- 				<div class="form-group col-md-1">			 -->
<%-- 					<a href="SalvarCliente?operacao=EXCLUIR&IdCliente=<%=cliente.getId() %>&tpUsu=<%=usuario.getTipoUsuario() %>" class="btn btn-danger btn-sm">Inativar</a> --%>
<!-- 				</div> -->
				 <div class="form-group col-md-1">	
					<a class="btn btn-primary" data-toggle="collapse" href="#collapseNovaSenha" role="button" aria-expanded="false" aria-controls="collapseNovaSenha">Troca Senha</a>
				  </div>
				  
			 </div>
         </div>
      </form>
	      <div class="collapse" id="collapseNovaSenha">				          
				<div class="container-fluid">
					
				<form action="AlterarSenha" method="POST"> 				  
				<input type="hidden" name="idUsuario" value="<%if(cliente != null) out.print(usuario.getId()); %>" />    		   
				<input type="hidden" name="operacao" value="<% if (cliente == null) out.print("SALVAR"); else out.print("ALTERAR");%>" />
					<div style="text-align: center;">
							<h3>Nova Senha </h3>
							<hr>  
							
		            	</div>  
					<div class="row">	
						<div class="form-group col-md-3">
			      		
			       	 	<input type="password" value=""  class="form-control input-lg" id="senhaAntiga" name="senhaAntiga" placeholder="Senha Antiga">
			      	</div>
		      	
		      		<div class="form-group col-md-3">
				      	
				       	 	<input type="password" value="" class="form-control input-lg" id="novaSenha" name="novaSenha" placeholder="Nova Senha">
				     </div>
		      	
		      		<div class="form-group col-md-3">
				      	
				       	 	<input type="password" value=""class="form-control input-lg" id="confNovaSenha" name="confNovaSenha" placeholder="Confirma Nova Senha">
				     </div>
					
					
					
						<div class="form-group col-md-3">
						<h4 class="page-header"> <input type="submit" value="SALVAR" class="btn btn-success btn-sm"></h4>
						</div>
					</div>
					</form>
					<div class="row">	
						<div class="form-group col-md-3">
						</div>
					</div>
				
				</div>
		 </div>
       </section>	
    </div>
       	
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
