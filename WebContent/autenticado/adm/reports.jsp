<!DOCTYPE html>
<%@page import="br.com.syslib.enuns.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.util.*"%>
<%@page import="br.com.syslib.core.aplicacao.Resultado"%>
<%@page import="br.com.syslib.core.impl.dao.*"%>

<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="P�gina gerenciar emprestimos e devolucoes.">
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
		Resultado resultado = (Resultado) request.getAttribute("resultado");
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
                <a class="nav-link" href="http://localhost:8080/SysLibrary/autenticado/adm/trocas.jsp">
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
						<h3> Relat�rios </h3>  
						   <hr>
		            </div>           
			       <form action="SalvarGrafico" method="post" class="form-horizontal">
						 <div class="row">
					        <div class="col-12">
					         
					              <div class="row">
					                
									  <div class="col-3">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtDtInicial">Data Inicial</label>
										  <input type="date" id="dtInicial" name="dtInicial" class="form-control form-control-alternative">
										 </div>
									  </div>
									  <div class="col-3">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtDtFinial">Data Final</label>
										  <input type="date" id="dtFinal" name="dtFinal" class="form-control form-control-alternative">
										</div>
									  </div>
									  <div class="col-2">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtTipoPeriodo">Tipo Periodo</label>
										  <select id="tipoPeriodo" name="tipoPeriodo" class="form-control">
											<%
												for (TipoPeriodo tipoPeriodo : TipoPeriodo.values()) {
											%>  
												<option id="<%=tipoPeriodo.getCodigo() %>" value="<%=tipoPeriodo.getCodigo() %>"><%=tipoPeriodo.getDescricao() %></option>
											<%
												} 
											%>
										  </select>
										</div>
									  </div>
									   
									  <div class="col-1">
										<div align="center" class="form-group">
										  <label class="form-control-label" for="txtTipoRelatorio">.</label>
									   	<button type="submit" name="operacao" value="SALVAR" class="btn btn-primary">Gerar </button>
									   	</div>
									   </div>
								</div>									
					                <hr>
					                  
					        <%
					        	Integer exibir = (Integer) request.getAttribute("exibir");
								if (exibir != null && exibir ==  1) {
					        %>
					        <div class="col-12">
					        	<div class ="row">
							        <div class="col-6" >
							            <img class="img-responsive" src="http://localhost:8080/SysLibrary/resources/graphics/GraficoLinha_Qtde.png">
							        </div>
							        
							       
						        </div>	
					       </div>					          					        
					        
					        <div class="col-12">
					        	<div class ="row">
					         		<div class="col-6">    
							            <h1>...</h1>
							        </div>
							       
							     </div>
					        </div>
					        <%
								}
					        %>
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
