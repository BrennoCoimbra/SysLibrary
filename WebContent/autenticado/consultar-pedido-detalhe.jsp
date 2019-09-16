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
  
		<%
				
  		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Pedido pedido = (Pedido) request.getAttribute("pedido");
  	    Resultado resultado = (Resultado) request.getAttribute("resultado");
  	  	//Pedido pedCupom = session.getAttribute("cupom") == null ? null : (Pedido) session.getAttribute("cupom");
    	%>	
    	

     <body> 
   
  
		
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
			<div style="text-align: center;">
					<h3>Detalhe Pedido</h3>   
				       
            	</div>  
		<form action="SalvarPedido" method="post">
           <div class="row">

		</div>   
            	<div id="list" class="row">	
			<div class="table-responsive col-12">
				<table class="table" >
					<thead>
					
						<tr class="table-active">
			              <td></td> 
			              <td></td> 
			              <th colspan="2"class="text-center">PRODUTOS</th>           
			              <td></td> 			            			              
			              <td></td> 			           
			            </tr>
			            
						<tr>
							<th></th>
							<th class="text-center">Livro</th>
							<th class="text-center">Qtde</th>
							<th class="text-center">Preço Unit. R$</th>
							<th class="text-center">Total R$</th>
							<th class="text-center">Ação</th>
							
						</tr>
						
					</thead>
					<tbody>
					<%
					if(pedido !=null){
                                                                       
                        	for (ItemPedido i : pedido.getPedItem()) {
                        		int j = i.getItemIdLivro();
                        		if(j == 0){
                        			i.setItemIdLivro(1);
                        		}
                                                           
                                                             
					%>		            				            	
						<tr>
							<td></td>
							<td style="text-align: center; vertical-align: middle;"><%=i.getItemTitLivro() %> </td>
							<td><input style="text-align: center;" readonly type= text value=<%= i.getItemQtde() %> ></td>
							<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f",i.getItemValorUnit()) %> ></td>
							<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", i.getItemSubTotal()) %>> </td>
							
							<%
							EntidadeDominio cupom = new CupomDAO().getEntidadeDominioCupom(pedido.getIdPedido(),i.getItemIdLivro());
							Cupom cp = (Cupom) cupom;
							int itemId;
							if(cupom == null && cp == null){
								 itemId = 0;
							} else {
								itemId = cp.getIdItem();
							}
							if(itemId != i.getItemIdLivro() && pedido.getStatusPedido().equals("ENTREGUE")){ %>
							<!-- Buttons actions -->
							<td style="text-align: center; ">
							<a class="btn btn-danger btn-sm" data-target="#modal-troca<%=pedido.getIdPedido() %>" data-toggle="modal" > TROCAR</a> 
							</td>
							<%} %>
						<% } %>
						
						
                        	
						<tr>            
			              <td></td>     
			              <td></td>
			              <td></td>     
			              <th class="text-center">SubTotal</th>
			              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", pedido.getValorTotalPedido() - pedido.getValorFrete() + pedido.getDescontoPedido()) %>> </td>
			              <td></td>
			            </tr>

                      <tr class="table-active">
			              <td></td>             
			              <td></td> 
			              <th colspan="2"class="text-center">ENDEREÇO</th>
			              <td></td> 
			              <td></td> 
            		</tr>
					
					 <%
				        Cliente cliente = new Cliente();
				        if(cliente != null){
				        	cliente = (Cliente) usuario;
				        	pedido.setIdClientePedido(usuario.getId());
				        }
				       
				        %>
            		<tr>
            		
		              <td></td>             
		              <td style="text-align: center; vertical-align: middle;"><b>Endereço de Entrega: </b></td>
		              <td style="text-align: center; vertical-align: middle;">
		               	<select id="end" name="end" class="form-control">
		                  <%
									EntidadeDominio ends = new EnderecoDAO().getEntidadeDominioEndreco(pedido.getIdEndCliPedido());
									Endereco end = (Endereco) ends;
									boolean first = true;
									pedido.setIdEndCliPedido(end.getId());
									
								%>
								<option id=<%=end.getId() %> value=<%=ends.getId() %>><%=end.getDescricao() %> </option>
								
		                </select>
		              </td>         
              		<td style="text-align: center; vertical-align: middle;"><b>Frete </b></td>              		
              		<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", pedido.getValorFrete()) %>> </td>
            		</tr>
            		
           <tr class="table-active">
              <td></td>             
              <td></td> 
              <th colspan="2"class="text-center">CUPOM</th>
              <td></td> 
              <td></td> 
            </tr>
            
             <tr>
              <td></td>                           
              <td style="text-align: center; vertical-align: middle;"></td> 
              <td></td>                                  	
              <th class="text-center">Cupom</th>
                 <td style="text-align: center; vertical-align: middle;"><input name="valorDesconto" id="valorDesconto" readonly style="text-align: center;" type= text value="<%if(pedido.getDescontoPedido() != 0) { out.print("R$" + String.format("%.2f", pedido.getDescontoPedido()));} ; %>"> </td>
			              <td></td>           
            </tr>
            <tr>
             <td></td>             
              <td></td>     
               <td></td>     
              <th class="text-center"><h4>TOTAL</h4></th>
              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%="R$" + String.format("%.2f", pedido.getValorTotalPedido()) %>> </td>
            </tr>
          
						
					</tbody>
					
				</table>
				
		
				
			</div>
			</div>
			
			<%
			if(pedido.getValorCartao1() > 0 || pedido.getValorCartao2() > 0){
			
			%>
			
			<div class="container-fluid">
			<div class="row">	
			<table class="table"> 	
        	<tbody>
		        <tr class="table-active">
		              <td></td>             
		               <td></td> 
		              <th colspan="2"class="text-center">FORMAS DE PAGAMENTO
		              
		              </th>
		               <td></td> 
		               <td></td> 
		            </tr>
		    </tbody>        
            </table>
            						
			<table class="table table-striped" >
			
			
	          <thead>
	          
				
           		 
	            <tr>
	              <th></th>            
	              <th class="text-center">Descrição</th>
	              <th class="text-center">Número</th>
	              <th class="text-center">Valor(R$)</th>
	             <th></th>
	            </tr>
	
	          </thead>
	           <tbody>
			                
			                <%
			                EntidadeDominio cart1 = new CartaoCreditoDAO().getEntidadeDominioCartaoCredito(pedido.getIdClienteCartao1());
			                CartaoCredito cart;
			                cart = (CartaoCredito) cart1;
			                EntidadeDominio cart2 = new CartaoCreditoDAO().getEntidadeDominioCartaoCredito(pedido.getIdClienteCartao2());
			                if(cart1 != null && cart2 != null){
							%>
			                	<tr>
					             <td><input type="hidden" name="cartId1" id="cartId1" value=<%= pedido.getCartoes().get(0).getId() %>></td>		              					              
					              <td style="text-align: center; vertical-align: middle;"><input readonly name="cartId1" id="cartId1" style="text-align: center;" type= text value= <%= pedido.getCartoes().get(0).getDescricao()%>> </td>
					              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value=  <%= "XXXX." + pedido.getCartoes().get(0).getNumeroCartao().toString().substring(12, 16)  %>> </td>					              
								  <td style="text-align: center; vertical-align: middle;"><input style="text-align: center;" name="cartVl1" id="cartVl1" type= number value= <%=pedido.getValorCartao1() %>> </td>								  
					              <td></td>     
					            </tr>
					            
					            <tr>
					              <td><input type="hidden" name="cartId2" id="cartId2" value=<%= pedido.getCartoes().get(1).getId() %>></td>		              					              
					              <td style="text-align: center; vertical-align: middle;"><input readonly  style="text-align: center;" type= text value= <%= pedido.getCartoes().get(1).getDescricao()%>> </td>
					             <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value=  <%= "XXXX." + pedido.getCartoes().get(1).getNumeroCartao().toString().substring(12, 16)  %>> </td>					              
								  <td style="text-align: center; vertical-align: middle;"><input style="text-align: center;" name="cartVl2" id="cartVl2" type= number value= <%= pedido.getValorCartao2()%>> </td>								  
					              <td></td>     
					            </tr>
							
							
					
							<%} else { %>
				
								<tr>
					             <td><input type="hidden" name="cartId1" id="cartId1" value=<%= pedido.getCartoes().get(0).getId() %>></td>		              					              
					              <td style="text-align: center; vertical-align: middle;"><input readonly name="cartId1" id="cartId1" style="text-align: center;" type= text value= <%= pedido.getCartoes().get(0).getDescricao()%>> </td>
					              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value=  <%= "XXXX." + pedido.getCartoes().get(0).getNumeroCartao().toString().substring(12, 16)  %>> </td>					              
								  <td style="text-align: center; vertical-align: middle;"><input style="text-align: center;" name="cartVl1" id="cartVl1" type= number value= <%=pedido.getValorCartao1() %>> </td>								  
					              <td></td>     
					            </tr>
		             
			
			<% } %>
			<% } %>
			
			</tbody>
        		</table>	
			</div>
			</div>	
		
		<hr>
			
			
			
			
          	 <div class="row">
				<div class="form-group col-md-8">
				</div>
			</div>
			
			</form>

			
			<!-- INICIO MODAL CUPOM -->
		<form action="GerarCupomTroca" method="post">
		<div class="modal fade" role="dialog" id="modal-troca<%=pedido.getIdPedido() %>">
	    <div class="modal-dialog modal-xl">
	         <div class="modal-content">
	             <div class="modal-header">
	             <h4 class="modal-title">Solicitação de Troca</h4>
	                 <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
	                 
	             </div>

	              <%
                            
                            for (ItemPedido item : pedido.getPedItem()) {                            	
    							EntidadeDominio cupom = new CupomDAO().getEntidadeDominioCupom(pedido.getIdPedido());
    							Cupom cp = (Cupom) cupom;
    							int itemId;
    							if(cupom == null && cp == null){
    								 itemId = 0;
    							} else {
    								itemId = cp.getIdItem();
    							}
    							if(itemId != item.getItemIdLivro() &&  pedido.getStatusPedido().equals("ENTREGUE")){
                        %>
        <input type="hidden" name="idUsuario" value="<%if(usuario != null) out.print(usuario.getId()); %>" />      		   				
		<input type="hidden" name="idPedido" value="<%=pedido.getIdPedido() %>" />
		     <div class="container-fluid">					
				<div class="row">
												
					<div class="form-group col-md-7">
						<label for="numpgs">Titulo</label>
						<input type="text" readonly class="form-control" value="<%=item.getItemTitLivro() %>">
						<input type="hidden" name="idTem" value="<%if(item != null) out.print(item.getItemIdLivro()); %>" />
						<input type="hidden" name="itemVl" id="itemVl" value="<%if(item != null) out.print(item.getItemValorUnit()); %>" />
					</div>
					
					<div class="form-group col-md-2">					
						<label for="numpgs">QtdeTroca</label>												
						<input type="number" class="form-control" id="qtdeTroca" name="qtdeTroca">
					</div>
					
					<div class="form-group col-md-3">
					<label for="numpgs">Ação</label>
						<input type='submit' class='btn btn-warning btn-sm'  id='operacao' name='operacao' value='SALVAR'/>
					</div>
                        
                      </div>
                   </div>     
                        
                        
                        <%} }%>

            </div>
        </div>
        </div>
        </form>    	        
		<!-- FIM MODAL CUPOM --
			
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
    <!-- Icons -->    
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
    <script>feather.replace()</script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
