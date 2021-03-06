<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.dominio.EntidadeDominio"%>
<%@page import="br.com.syslib.dominio.Livro"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.*"%>


<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="P�gina para buscar livros e acessar informa��es.">
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
		 Resultado resultado = (Resultado) request.getAttribute("resultado");
		 StringBuilder sb;
		 Usuario usuario = (Usuario) session.getAttribute("usuario");

		 List<EntidadeDominio> cupons = (List<EntidadeDominio>) request.getAttribute("cupons");
		 
		  %>
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <%if(usuario !=null){%>
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
            <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/dashboard.jsp">
                  <span data-feather="home"></span>
                  Dashboard <span class="sr-only"></span>
                </a>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/alterar-senha.jsp">
                  <span data-feather="key"></span>
                  Alterar Senha <span class="sr-only"></span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/form-livro.jsp">
                  <span data-feather="book">(current)</span>
                  Cadastrar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/consultar-livro.jsp">
                  <span data-feather="book"></span>
                  Consultar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/estoque.jsp">
                  <span data-feather="package"></span>
                  Estoque
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/consultar-clientes.jsp">
                  <span data-feather="users"></span>
                  Consultar Clientes
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="http://localhost:8080/SysLibrary/autenticado/adm/trocas.jsp">
                  <span data-feather="rewind"></span>
                  Trocas
                </a>
              </li>
              
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>An�lise</span>
              <a class="d-flex align-items-center text-muted" href="#">
               
              </a>
            </h6>
			        			      
            <ul class="nav flex-column mb-2">
			
			
              <li class="nav-item">
           <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/reports.jsp">
              <span data-feather="file-text"></span> 
             Gr�fico Comum 
              </a>
              </li>
              
               <li class="nav-item">
           <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/analise.jsp">
              <span data-feather="file-text"></span> 
             Gr�fico Din�mico
              </a>
              </li>

      
            </ul>
          </div>
        </nav>
        <section role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div style="text-align: center;">
					<h3>Solicita��o de  Troca </h3>           
            	</div>          
          	  	<form action="ConsultarCupomTroca" method="post" class="form-horizontal">
		<div class="row">
			<div class="col-lg-5">
			
			</div>
			<div class="col-4">
			<input id="search" name="search" class="form-control" placeholder="Pesquisar" type="text">
			</div>
			<div class="col-0">
			<input type='submit' class='btn btn-primary'  id='operacao' name='operacao' value='VISUALIZAR'/>
				
		</div>
			
		</div>
		<!-- /#top -->

		<div id="list" class="row">	
			<div class="table-responsive col-md-12">
				<table class="table table-striped" >
					<thead>
						<tr>
							<th class="text-center">N�Compra</th>
							<th class="text-center">Titulo</th>
							<th class="text-center">Qtde</th>
							<th class="text-center">Valor</th>
							<th class="text-center">Status</th>
							<th style="text-align: center">A��o</th>
						</tr>
					</thead>
					<tbody>

						<tr>
						<%
		            		
		            		
		            		if (cupons != null) {
			            		for (EntidadeDominio ed : cupons) {
			            			Cupom cp = (Cupom) ed;			            			
		            		
							%>
							
							<td style="text-align: center; vertical-align: middle;"><%=cp.getIdPedido() %> </td>
							<%
							
							EntidadeDominio livro = new LivroDAO().getEntidadeDominio(cp.getIdItem());
							Livro liv = (Livro) livro;
							%>
							<td style="text-align: center; vertical-align: middle;"><%=liv.getTitulo() %> </td>		
							<td style="text-align: center; vertical-align: middle;"><%=cp.getQtdeTroca() %> </td>							
							<td style="text-align: center; vertical-align: middle;">R$ <%=String.format("%.2f",cp.getValorCupom()) %> </td>																				
							<td style="text-align: center; vertical-align: middle;"><%=cp.getStatusCupom() %></td>
							<!-- Buttons actions -->
							 <td class="text-center">
			                  <div class="dropdown">
			                    <a class="btn btn-sm btn-icon-only" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			                     <span data-feather="more-vertical"></span> 
			                    </a>
			                    
			                    <div class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
			                    <%if(cp.getStatusCupom().equals("EM TROCA")){ %>			                    
			                      <a class="dropdown-item" href="GerarCupomTroca?operacao=ALTERAR&IdCupom=<%=cp.getId()%>&IdPed=<%=cp.getIdPedido()%>&IdLivro=<%=cp.getIdItem()%>&Option=S"><span data-feather="check"></span> Aceitar Troca</a> 																					
								  <a class="dropdown-item" href="GerarCupomTroca?operacao=ALTERAR&IdCupom=<%=cp.getId()%>&IdPed=<%=cp.getIdPedido()%>&IdLivro=<%=cp.getIdItem()%>&Option=N"><span data-feather="x"></span> Recusar Troca</a>							
			                      <%} else if(cp.getStatusCupom().equals("TROCA AUTORIZADA") && cp.getEnviarEstoque() != true){ %>
			                      <a class="dropdown-item" href="ADD?operacao=ADD&IdCupom=<%=cp.getId()%>&IdPed=<%=cp.getIdPedido()%>&qtdeItem=<%=cp.getQtdeTroca()%>&IdLivro=<%=cp.getIdItem()%>&Option=S"><span data-feather="check"></span> Enviar p/ Estoque</a>
								  <a class="dropdown-item" href="ADD?operacao=ADD&IdCupom=<%=cp.getId()%>&IdPed=<%=cp.getIdPedido()%>&qtdeItem=<%=cp.getQtdeTroca()%>&IdLivro=<%=cp.getIdItem()%>&Option=N"><span data-feather="x"></span> Descartar</a>
			                       <%} else {%>
			                       <a class="dropdown-item" href="#"> </a>
			                       <%} %>
			                    </div>
			                  </div>
			                </td>	
						</tr>
							<%}} %>
					</tbody>
				</table>
			</div>
			</div>
			</form>
        </section>		
      </div>	  
    </div>	
    <!-- Icons -->    
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script>feather.replace()</script>  		
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright�2019 </footer>
</html>
