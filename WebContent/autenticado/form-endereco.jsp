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
		Endereco endereco = (Endereco) request.getAttribute("enderecos");
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
            <a class="nav-link" href="http://localhost:8080/SysLibrary/index.jsp">
                  <span data-feather="home"></span>
                  Home <span class="sr-only"></span>
                </a>
                </li>
              <li class="nav-item">
                 <a class="nav-link " href="./profile.jsp">
                  <span data-feather="users"></span>
                  Seu Perfil <span class="sr-only"></span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="./form-endereco.jsp">
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
					<h3>Endereço </h3>           
            	</div>          
          	  	<form action="SalvarEndereco" method="post">
		<input type="hidden" name="idUsuario" value="<%if(usuario != null) out.print(usuario.getId()); %>" />      		   		
		<input type="hidden" name="endereco_id" value="<% if (endereco != null) out.print(endereco.getId()); %>" />
		<input type="hidden" name="operacao" value="<% if (endereco != null) out.print("ALTERAR"); else out.print("SALVAR");%>" />
		

			<!-- area de campos do form -->
				<hr/>
			<div class="container-fluid">
					
				<div class="row">	
							
					<div class="form-group col-md-3">
					<label for="numpgs">Descrição</label>
						<input type="text" maxlength="13" class="form-control" id="descricao" name="descricao"
							value="<%if(endereco != null) out.print(endereco.getDescricao()); %>">
					</div>
					
					<div class="form-group col-md-2">
						<label for="numpgs">Tipo Endereço</label> 
						<select id="tpEnd" name="tpEnd" class="form-control">
							<%
								for (TipoEndereco tpEnd : TipoEndereco.values()) {
							%>
							  <option id=<%=tpEnd.getCodigo() %> value=<%=tpEnd.getCodigo() %> <%if (endereco != null && endereco.getTpEnd().getCodigo() == tpEnd.getCodigo()) out.print("selected"); %>><%=tpEnd.getDescricao() %> </option>
							<%
								}
							%>
							</select>
					</div>
					
					<div class="form-group col-sm-2">
					<label class="form-control-label" for="pref">Preferencial</label>
						<select id="pref" name="pref" class="form-control">
							<%
								if (endereco == null) {
							%>  
								<option id=0 value=0 >Não</option>
								<option id=1 value=1 >Sim</option>
							<%
								} else if (endereco != null && endereco.getPreferencial() == true) {
							%>
								<option id=0 value=0 >Não</option>
								<option id=1 value=1 selected>Sim</option>
							<%	
								} else if (endereco != null && endereco.getPreferencial() == false) {
									%>
									
									<option id=0 value=0 selected>Não</option>
									<option id=1 value=1 >Sim</option>
								<%	
									}
								%>
						
							</select> 				
					</div>
					
				</div>
						
			</div>
				
			<div class="container-fluid">
				<h6 class="page-header"> Caracteristicas </h6>
				
				<div class="row">
				
					<div class="form-group col-md-2">
					<label for="numpgs">Tipo Residência</label> 
						<select id="tpRes" name="tpRes" class="form-control">
							<%
								for (TipoResidencia residencias : TipoResidencia.values()) {
							%>
							  <option id=<%=residencias.getCodigo() %> value=<%=residencias.getCodigo() %> <%if (endereco != null && endereco.getTpResid().getCodigo() == residencias.getCodigo()) out.print("selected"); %>><%=residencias.getDescricao() %> </option>
							<%
								}
							%>
							</select>
					</div>
					<div class="form-group col-md-2">
					<label for="numpgs">Tipo Logradouro</label> 
						<select id="tpLog" name="tpLog" class="form-control">
							<%
								for (TipoLogradouro logradouros : TipoLogradouro.values()) {
							%>
							  <option id=<%=logradouros.getCodigo() %> value=<%=logradouros.getCodigo() %> <%if (endereco != null && endereco.getTpLogrdo().getCodigo() == logradouros.getCodigo()) out.print("selected"); %>><%=logradouros.getDescricao() %> </option>
							<%
								}
							%>
							</select>
					</div>
					<div class="form-group col-md-4">
						<label for="campo1">Logradouro</label> <input type="text" maxlength="100"
							class="form-control" id="logradouro" name="logradouro"
							value="<%if(endereco != null) out.print(endereco.getLogradouro()); %>">
					</div>
					<div class="form-group col-md-2">
						<label for="titulo">Numero</label> <input type="number"
							class="form-control" id="numero" name="numero"
							value="<%if(endereco != null) out.print(endereco.getNumero()); %>">
					</div>
				</div>	

				<div class="row">
				
					
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
						<label for="numpgs">Estado</label> 
						<select id="estado" name="estado" class="form-control">
							<%
								for (Estados estados : Estados.values()) {
							%>
							  <option id=<%=estados.getCodigo() %> value=<%=estados.getCodigo() %> <%if (endereco != null && endereco.getEstado().equals(estados.getDescricao())) out.print("selected"); %>><%=estados.getDescricao() %> </option>
							<%
								}
							%>
							</select>
													
					</div>
					
					
					<div class="form-group col-md-3">
						<label for="numpgs">Cidade</label> 
						<input type="text"
							class="form-control" id="cidade" name="cidade"
						 value="<%if(endereco != null) out.print(endereco.getCidade()); %>">
													
					</div>
					
					<div class="form-group col-md-2">
						<label for="numpgs">Pais</label> <input type="text"
							class="form-control" id="pais" name="pais"
							value="<%if(endereco != null) out.print(endereco.getPais()); %>">
					</div>
								
		
				</div>


							<hr>
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>				
			
	</div>
		
	</form>
	
        </section>		
      </div>
      	  
    </div>	
    
    
   
     <!-- JQuery carrega os estados/cidades de um arquivo .json -->
    <script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.4.1.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/js/estados_cidades.js"></script>
	 <!-- Icons -->
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
    <script>
    feather.replace()
    </script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
