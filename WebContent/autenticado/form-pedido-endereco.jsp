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
		Endereco endereco = (Endereco) request.getAttribute("enderecos");
	    Pedido pedido = session.getAttribute("pedido") == null ? null : (Pedido) session.getAttribute("pedido");
	    @SuppressWarnings({ "unchecked", "rawtypes" })
  	    ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? null : (ArrayList) session.getAttribute("livros");
  	    Resultado resultado = (Resultado) request.getAttribute("resultado");
    	%>	
    	
  	  <% 
  		if (pedido != null&& pedido.getPedItem()!=null && !pedido.getPedItem().isEmpty()) {%>    
        <body onload="countDown(60)"> 
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
        
        <section role="main" class="col-md">
			<div style="text-align: center;">
					<h3>Pedido</h3>   
				       <div style="text-align: right;"> 
				       <% if(pedido != null) {%>
				          <label>Você tem: <input type="text" class="col-lg-3"  readonly id="btn" name="btn" value=""> minutos para comprar.</label>
				          <% } %>
				       </div>
            	</div>  
		<form action="CalcularFrete" method="get">
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
									List<EntidadeDominio> enderecos = new EnderecoDAO().getEntidadeDominio(usuario.getId());
									Endereco endTemp = null;
									boolean first = true;

									for (EntidadeDominio ed : enderecos) {
										Endereco ends = (Endereco) ed;
										if (first) {
											endTemp = endereco;
											first = false;
										}
								%>
								<option id=<%=ends.getId() %> value=<%=ends.getId() %>><%=ends.getDescricao() %> </option>
								<%
									}
								%>
		                </select>
		              </td>         
              		<td style="text-align: center; vertical-align: middle;"><b>OU </b></td>
              		<td style="text-align: center; vertical-align: middle;">
				  	<a class="btn btn-primary btn-sm" data-target="#modal-mensagem" data-toggle="modal" > NOVO</a>
					</td>
            		</tr>
            		
						
					</tbody>
					
				</table>
				
		
				
			</div>
			</div>
			
			<hr>
			
			<div class="row">
          		<div class="form-group col-md-10">
					<a href="http://localhost:8080/SysLibrary/carrinho.jsp" class="btn btn-success">Voltar</a>
				</div>
				<%
				if(cliente == null){
				%>
				<div class="form-group col-md-2">
					<a href="http://localhost:8080/SysLibrary/login.jsp" class="btn btn-warning">Seguir p/Pagamento</a>
				</div>
				<%} else { %>
           		<div class="form-group col-md-2">
					
					 <button type="submit" name="operacao" value="SALVAR" class="btn btn-warning">Seguir p/Pagamento </button>
				</div>
				<%}
				
				%>
          	</div>
          	
          	 <div class="row">
				<div class="form-group col-md-8">
				</div>
			</div>
			
			</form>
			
			 <!--  INICIO DO MODAL DE ENDEREÇO -->
			
			<form action="SalvarEndereco" method="post">
					
        <div class="modal fade" id="modal-mensagem">
		    <div class="modal-dialog modal-lg">
		         <div class="modal-content">
		             <div class="modal-header">
		             <h4 class="modal-title">Novo Endereço</h4>
		                 <button type="button" class="close" data-dismiss="modal"><span>×</span></button>
		     		</div>	
		     		
		<input type="hidden" name="idUsuario" value="<%if(usuario != null) out.print(usuario.getId()); %>" />      		   				
		<input type="hidden" name="operacao" value="<% if (endereco == null) out.print("SALVAR");%>" />
		     <div class="container-fluid">					
				<div class="row">								
					<div class="form-group col-md-5">
					<label for="numpgs">Descrição</label>
						<input type="text" maxlength="13" class="form-control" id="descricao" name="descricao"
							value="<%if(endereco != null) out.print(endereco.getDescricao()); %>">
					</div>
					
					<div class="form-group col-md-4">
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
					
					<div class="form-group col-sm-4">
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
				
					<div class="form-group col-md-4">
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
					<div class="form-group col-md-4">
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
					<div class="form-group col-md-6">
						<label for="campo1">Logradouro</label> <input type="text" maxlength="100"
							class="form-control" id="logradouro" name="logradouro"
							value="<%if(endereco != null) out.print(endereco.getLogradouro()); %>">
					</div>
					<div class="form-group col-md-4">
						<label for="titulo">Numero</label> <input type="number"
							class="form-control" id="numero" name="numero"
							value="<%if(endereco != null) out.print(endereco.getNumero()); %>">
					</div>
				</div>	

				<div class="row">
				
					
					<div class="form-group col-md-5">
						<label for="ano">Bairro</label> <input type="text"
							class="form-control" id="bairro" name="bairro"
							value="<%if(endereco != null) out.print(endereco.getBairro()); %>">
					</div>
					<div class="form-group col-md-4">
						<label for="numpgs">CEP</label> <input type="text"
							class="form-control" id="cep" name="cep"
							value="<%if(endereco != null) out.print(endereco.getCep()); %>">
					</div>
				</div>
					
					<div class="row">
					
					
					<div class="form-group col-md-5">
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
					
					
					<div class="form-group col-md-5">
						<label for="numpgs">Cidade</label> 
						<input type="text"
							class="form-control" id="cidade" name="cidade"
						 value="<%if(endereco != null) out.print(endereco.getCidade()); %>">
													
					</div>
					
					<div class="form-group col-md-4">
						<label for="numpgs">Pais</label> <input type="text"
							class="form-control" id="pais" name="pais"
							value="<%if(endereco != null) out.print(endereco.getPais()); %>">
					</div>
								
		
				</div>


							<hr>
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>				
			
		</div>  
		          </div>
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
