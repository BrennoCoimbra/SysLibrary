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
    <meta name="description" content="PÃ¡gina para buscar livros e acessar informaÃ§Ãµes.">
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
  
		<%
  		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		
	    Pedido pedido = session.getAttribute("pedido") == null ? new Pedido() : (Pedido) session.getAttribute("pedido");
	    @SuppressWarnings({ "unchecked", "rawtypes" })
  	    ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? null : (ArrayList) session.getAttribute("livros");
  	    Resultado resultado = (Resultado) request.getAttribute("resultado");
    	%>	
    	
  	  <% 
  		if (pedido != null&& pedido.getPedItem()!=null && !pedido.getPedItem().isEmpty()) {
  			
  		
  		%>    
        <body onload="countDown(300)"> 
      <%} else{
    	  session.removeAttribute("pedido");
      %>
            <body> 
      <%}%>
   
  
		
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
                <%
					if(usuario != null){
          		%>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/profile.jsp">
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
              <%if (pedido != null) {%>
              <li class="nav-item">
                <a class="nav-link active" href="http://localhost:8080/SysLibrary/carrinho.jsp">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
                </a>
              </li>
              <%} %>
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
                 <%} else {%>
		
           
               <li class="nav-item">
                <a class="nav-link active" href="http://localhost:8080/SysLibrary/carrinho.jsp">
                  <span data-feather="shopping-cart"></span>
                  Carrinho
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
              <a class="nav-link" href="http://localhost:8080/SysLibrary/index.jsp?idCategoria=<%=categoria.getId()%>"> 
              <span data-feather="book"></span> <%=categoria.getNome()%>
              </a>
              </li>
              <%
			      }
			
			
			
			   %>

      	<%} %>
            </ul>
          </div>
        </nav>
        <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div style="text-align: center;">
					<h3>Carrinho de Compras</h3> 
					 <div style="text-align: left;">
					 <form action="ValidarCupomPromocional"> 
				      <label>CUPOM PROMOCIONAL: <input type="text" class="col-lg-3" id="cupomDesc" name="cupomDesc" value="">
				      <button id="btnCupomdesconto"  <%= pedido== null|| pedido.getPedItem()==null || pedido.getPedItem().isEmpty()? "disabled": "" %> type="submit" name="operacao" value="CUPOMDESCONTO">Aplicar</button>
				       </label>	
				       </form>			      				      
				       </div>  
				       <div style="text-align: right;"> 
				       <% if(pedido != null) {%>
				          <label>Você tem: <input type="text" class="col-lg-3"  readonly id="btn" name="btn" value=""> minutos para comprar.</label>
				          <% } %>
				       </div>
            	</div>  
		<form action="SalvarCarrinho" method="post">
           <div class="row">

		</div>   
            	<div id="list" class="row">	
			<div class="table-responsive col-12">
				<table class="table table-striped" >
					<thead>
						<tr>
							<th></th>
							<th class="text-center">Livro</th>
							<th class="text-center">Qtde</th>
							<th class="text-center">Preço Unit. R$</th>
							<th class="text-center">Total R$</th>
							<th class="text-center">Ações</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
					<%
					if(!pedido.getPedItem().isEmpty() || pedido != null){
                        if (livros != null) {                                               
                        	for (ItemPedido i : pedido.getPedItem()) {
                        		int j = i.getItemIdLivro();
                        		if(j == 0){
                        			i.setItemIdLivro(1);
                        		}
                                Livro livro = (Livro) livros.get((i.getItemIdLivro()-1));                            
                                                             
					%>		            				            	
						<tr>
							<td></td>
							<td style="text-align: center; vertical-align: middle;"><%=livro.getTitulo() %> </td>
							<td>
							 <a href="SalvarCarrinho?operacao=ADD&idItemPedido=<%= livro.getId() %>&qtdeItem=1">
							<span data-feather="plus-circle"></span>
							</a>
							<input style="text-align: center;" readonly type= text value=<%= i.getItemQtde() %> > 
							<a href="SalvarCarrinho?operacao=REMOVE&idItemPedido=<%= livro.getId() %>&qtdeItem=<%= i.getItemQtde()%>">
							<span data-feather="minus-circle"></span>
							</a>
							</td>
							<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f",livro.getEstoque().getValorVenda()) %> ></td>
							<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", i.getItemSubTotal()) %>> </td>
							<!-- Buttons actions -->
							<td style="text-align: center; "><a href="SalvarCarrinho?operacao=EXCLUIR&idItemPedido=<%= livro.getId() %>&qtdeItem=<%=i.getItemQtde()%>"><span data-feather="trash-2"></span></a></td>														
							<td></td>

						<% } %>
                        	
						
			            <tr>
			              <td></td>             
			              <td></td>     
			              <td></td>     
			              <th class="text-center">Desconto</th>
			              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", pedido.getDescontoPedido()) %>> </td>
			              <td></td>
			              <td></td>
			            </tr>
			            <tr>
			              <td></td>             
			              <td></td>     
			              <td></td>     
			              <th class="text-center">SubTotal</th>
			              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", pedido.getSubtotalPedido()) %>> </td>
			              <td></td>
			              <td></td>
			            </tr>
			            
			          
			            
			          <% } %>
						
					</tbody>
					
				</table>
				
			</div>
			</div>
			
			<div class="row">
          		<div class="form-group col-md-10">
					<a href="http://localhost:8080/SysLibrary/index.jsp" class="btn btn-success">Voltar</a>
				</div>
				<%
				if(usuario == null){
				%>
				<div class="form-group col-md-2">
					<a href="http://localhost:8080/SysLibrary/login.jsp" class="btn btn-success">Seguir Compra</a>
				</div>
				<%} else { %>
           		<div class="form-group col-md-2">
					<a href="http://localhost:8080/SysLibrary/autenticado/form-pedido-endereco.jsp" class="btn btn-success">Seguir Compra</a>
				</div>
				<%}
				
				%>
          	</div>
          	
          	 <div class="row">
				<div class="form-group col-md-8">
				</div>
			</div>
			
			</form>
			<% } %>
				<% if(request.getAttribute("msg") != null) { %>
	 			<div style="text-align: center" class="alert alert-danger">
				<br>
				<h4>${msg}</h4>
				</div>
				<%} else { } %>
						
        </section>		
      </div>	  
    </div>	
        
	<script src="./resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="./resources/bootstrap/js/feather.min.js"></script>
    <script src="./resources/js/contador.js"></script>	
    <script src="./resources/js/refresh.js"></script>
    <!-- Icons -->
    <script>
    feather.replace()
    </script>  	
    
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
