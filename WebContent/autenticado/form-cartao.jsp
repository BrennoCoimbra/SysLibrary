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
    <link rel="icon" href="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Bootstrap core CSS -->
   <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/dashboard.css" rel="stylesheet">
	
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
		CartaoCredito cartao = (CartaoCredito) request.getAttribute("cartoes");
  		Usuario usuario = (Usuario) session.getAttribute("usuario");
		usuario.getId();
		StringBuilder sb;
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
                <a class="nav-link" href="./profile.jsp">
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
                <a class="nav-link active" href="./form-cartao.jsp">
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
                <a class="nav-link" href="#">
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
			<div style="text-align: center;">
					<h3>Cartão Credito </h3>           
            	</div>          
          	  	<form action="SalvarCartao" method="post">
		
		<input type="hidden" name="cartao_id" value="<% if (cartao != null) out.print(cartao.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (cartao != null) out.print("ALTERAR"); else out.print("SALVAR");%>" />
		

			<!-- area de campos do form -->
				<hr/>
			<div class="container-fluid">
				<h6 class="page-header"> Descrição </h6>
					<div class="row">	
							
				<div class="form-group col-md-3">
					<input type="text" maxlength="13" class="form-control" id="descricao" name="descricao"
							value="<%if(cartao != null) out.print(cartao.getDescricao()); %>">
					</div>
					
					</div>
				</div>	
					
			<div class="container-fluid">
				<h6 class="page-header"> Identificação </h6>

				<div class="row">
				
					<div class="form-group col-md-4">
						<label for="campo1">Nome no Cartão</label> <input type="text"
							class="form-control" id="nomeCartao" name="nomeCartao"
							value="<%if(cartao != null) out.print(cartao.getNomeCartao()); %>">
					</div>
					<div class="form-group col-md-4">
						<label for="titulo">Numero</label> <input type="text" 
							class="form-control" id="numero" name="numero"
							value="<%if(cartao != null) out.print(cartao.getNumeroCartao()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="ano">Mês</label> <input type="text" maxlength="2"
							class="form-control" id="mes" name="mes"
							value="<%if(cartao != null) out.print(cartao.getMes()); %>">
					</div>
					<div class="form-group col-md-1">
						<label for="ano">Ano</label> <input type="text" maxlength="2"
							class="form-control" id="ano" name="ano"
							value="<%if(cartao != null) out.print(cartao.getAno()); %>">
					</div>
					
				</div>
					
					<div class="row">
					
					<div class="form-group col-md-1">
						<label for="edicao">CVV</label> <input type="text" maxlength="3"
							class="form-control" id="cvv" name="cvv"
							value="<%if(cartao != null) out.print(cartao.getCodigoSeguranca()); %>">
					</div>
					<div class="form-group col-md-3">
						<label for="numpgs">Bandeira</label> 
						<select id="bandeira" name="bandeira" class="form-control">
							<%
								for (BandeiraCartao bandeiras : BandeiraCartao.values()) {
							%>
							  <option id=<%=bandeiras.getCodigo() %> value=<%=bandeiras.getCodigo() %> <%if (cartao != null && cartao.getBandeiraCartao().getCodigo() == bandeiras.getCodigo()) out.print("selected"); %>><%=bandeiras.getDescricao() %> </option>
							<%
								}
							%>
							</select>
							
					</div>
					
				
					
				</div>


							<hr />
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>				
			
	</div>
		
	</form>
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
