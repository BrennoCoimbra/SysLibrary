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
		CartaoCredito cartao = (CartaoCredito) session.getAttribute("cartao") == null ? null : (CartaoCredito) session.getAttribute("cartao");		
  		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Endereco endereco = (Endereco) request.getAttribute("enderecos");
		Cupom cupom = (Cupom) request.getAttribute("cupom");
	    Pedido pedido = session.getAttribute("pedido") == null ? null : (Pedido) session.getAttribute("pedido");
	    Frete frete = session.getAttribute("frete") == null ? null : (Frete) session.getAttribute("frete");
	    @SuppressWarnings({ "unchecked", "rawtypes" })
  	    ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? null : (ArrayList) session.getAttribute("livros");
	    @SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<EntidadeDominio> cards = session.getAttribute("cartoes") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("cartoes");
  	    Resultado resultado = (Resultado) request.getAttribute("resultado");
    	%>	
    	
  	  <% 
  		if (pedido != null&& pedido.getPedItem()!=null && !pedido.getPedItem().isEmpty()) {%>    
        <body onload="countDown(300)"> 
      <%} else{%>
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
                <a class="nav-link active" href="http://localhost:8080/SysLibrary/carrinho.jsp">
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
					<h3>Pedido</h3>   
				       <div style="text-align: right;"> 
				       <% if(pedido != null) {%>
				          <label>Você tem: <input type="text" class="col-lg-3"  readonly id="btn" name="btn" value=""> minutos para comprar.</label>
				          <% } %>
				       </div>
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
						</tr>
						
					</thead>
					<tbody>
					<%
					if(pedido !=null){
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
							<td><input style="text-align: center;" readonly type= text value=<%= i.getItemQtde() %> ></td>
							<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f",livro.getEstoque().getValorVenda()) %> ></td>
							<td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= "R$" + String.format("%.2f", i.getItemSubTotal()) %>> </td>
							<td></td>
							
						<% } %>
						
						<tr>
			              <td></td>             
			              <td></td>     
			              <td></td>     
			              <th class="text-center">Desconto</th>
			              <td style="text-align: center; vertical-align: middle;"><input name="valorDesconto" id="valorDesconto" readonly style="text-align: center;" type= text value="<%if(pedido.getDescontoPedido() != 0) { out.print("R$" + String.format("%.2f", pedido.getDescontoPedido()));} out.print("R$00.00"); %>"> </td>
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
			            </tr>
			            
			          <%  
                        } %>
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
              <td style="text-align: center; vertical-align: middle;"><input name="valorCupom" id="valorCupom" style="text-align: center;" readonly type= text value="<%if(cupom != null) { out.print(String.format("%.2f",cupom.getValorCupom()));}  out.print("R$00.00"); %>"> </td>              
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
	              <th class="text-center">Valor</th>
	             <th></th>
	            </tr>
	
	          </thead>
	           <tbody>
			                
			                <%
			                if(cards.size() > 1){
							List<EntidadeDominio> cartoes = new CartaoCreditoDAO().getEntidadeDominio(usuario.getId());
							CartaoCredito cartaoTemp = null;
							boolean second = true;
							int i = 0;
							String descricaoPgto = null;

							for (EntidadeDominio ed : cards) {
								CartaoCredito cart = (CartaoCredito) ed;
								
					
							%>
			            	  <tr>
					              <td></td>		              					              
					              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= cart.getDescricao() %>> </td>
					              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value=  <%= "XXXX." + cart.getNumeroCartao().toString().substring(12, 16) %>> </td>					              
								  <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%="R$" + String.format("%.2f", cart.getValorCartao()) %>> </td>								  
					              <td></td>     
					            </tr>
			            	<%
			            				i += 1;
				            			}
							
			            		
				            %>
							
					
			<%} else { %>
				
					<tr>
		              <td></td>		              					              
		              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%= cartao.getDescricao() %>> </td>
		              <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value=  <%= "XXXX." + cartao.getNumeroCartao().toString().substring(12, 16) %>> </td>
					  <td style="text-align: center; vertical-align: middle;"><input readonly style="text-align: center;" type= text value= <%="R$" + String.format("%.2f", pedido.getValorCartao1()) %>> </td>			                            
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
          		<div class="form-group col-md-10">
					<a href="http://localhost:8080/SysLibrary/autenticado/form-pedido-cartao.jsp" class="btn btn-success">Voltar</a>
				</div>
				<%
				if(cliente == null){
				%>
				<div class="form-group col-md-2">
					<a href="http://localhost:8080/SysLibrary/login.jsp" class="btn btn-warning">Concluir Compra</a>
				</div>
				<%} else { %>
           		<div class="form-group col-md-2">	
					 <button type="submit" name="operacao" value="SALVAR" class="btn btn-danger">Concluir Compra </button>
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
    <!-- Icons -->    
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
        setTimeout(function () {
            window.location.reload(1);
        }, 60000); //tempo em milisegundos. Neste caso, o refresh vai acontecer de 5 em 5 segundos.
    });
    </script>	
    <script type="text/javascript">
    
    var seg = 59;
    function countDown(tempo) {
        var btn = document.getElementById('btn');
        // Se o tempo não for zerado
        if ((tempo - 1) >= -1) {
            // Pega a parte inteira dos minutos
            var min = parseInt(tempo / 60);
            // Calcula os segundos restantes
            var seg = tempo % 60;
            // Formata o número menor que dez, ex: 08, 07, ...
            if (min < 10) {
                min = "0" + min;
                min = min.substr(0, 2);
            }
            if (seg <= 9) {
                seg = "0" + seg;
            }
            // Cria a variável para formatar no estilo hora/cronômetro
            horaImprimivel = min + ':' + seg;
            //JQuery pra setar o valor
            btn.value = horaImprimivel;
    // diminui o tempo
            tempo--;
            // Define que a função será executada novamente em 1000ms = 1 segundo
            setTimeout('countDown(' + tempo + ')', 1000);
            // Quando o contador chegar a zero faz esta ação

        } 
    }
    </script>
    <script>
    feather.replace()
    </script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
