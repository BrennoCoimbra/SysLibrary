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

  <body>
  		<%
		Livro liv = (Livro) request.getAttribute("livro");
		Usuario usuario = Logged.getUsuario();
		usuario.getId();
		StringBuilder sb;
		%>	
    	
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#"> Bem - Vindo ! <span data-feather="smile"></span> </a>      
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="./login.jsp">Sign out <span data-feather="log-out"></span></a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
            <li class="nav-item">
            <a class="nav-link" href="./dashboard.jsp">
                  <span data-feather="home"></span>
                  Dashboard <span class="sr-only"></span>
                </a>
                </li>
              <li class="nav-item">
                <a class="nav-link" href="./profile.jsp">
                  <span data-feather="users"></span>
                  Seu Perfil <span class="sr-only"></span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./form-livro.jsp">
                  <span data-feather="book">(current)</span>
                  Cadastrar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./consultar-livro.jsp">
                  <span data-feather="book"></span>
                  Consultar Livro
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="./estoque.jsp">
                  <span data-feather="package"></span>
                  Estoque
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./loan.jsp">
                  <span data-feather="rewind"></span>
                  Empréstimos
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./returns.jsp">
                  <span data-feather=fast-forward></span>
                  Devoluções
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="./reservas.jsp">
                  <span data-feather="calendar"></span>
                  Reservas
                </a>
              </li>
            </ul>

            <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
              <span>Relatorios</span>
              <a class="d-flex align-items-center text-muted" href="#">
               
              </a>
            </h6>
			        			      
            <ul class="nav flex-column mb-2">
			
			
              <li class="nav-item">
           <a class="nav-link" href="./reports.jsp">
              <span data-feather="file-text"></span> 
              Quantidade em Estoque
              </a>
              </li>
             

      
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<div style="text-align: center;">
					<h3>Estoque </h3>   
				       
            	</div>  
            	
           <div class="row">
			<div class="col-lg-5">
			
			</div>
			<div class="col-4">
			
			</div>
			<div class="col-0">
			
				<select id="txtMovimento" name="txtMovimento" class="form-control">
					<%
            		for(TipoMovimentacaoEstoque tpMov : TipoMovimentacaoEstoque.values()){
            	%>
					<option id=<%=tpMov.getDescricao() %> value=<%=tpMov.getDescricao() %>><%=tpMov.getDescricao() %></option>
				
				
				<%
            	}
				%>
				</select>
		</div>
			
		</div>   
            	<div id="list" class="row">	
			<div class="table-responsive col-12">
				<table class="table table-striped" >
					<thead>
						<tr>
						<th></th>
						
							<th class="text-center">Seleção</th>
							<th class="text-center">Livro</th>
							<th>Qtde</th>
							<th>Ações</th>
							<th></th>
						</tr>
					</thead>
					<tbody>


		            		
		            	
						<tr>
							
							<%
		            		int i = 0;
							if (i < 8) {
		            			while (i < 8) {
							%>
							<td></td>
							<td style="text-align: center; vertical-align: middle;">
							 <input type="checkbox" id="chkSelect^<%=i %>" name="chkSelect^<%=i %>">
							</td>
							
							    <td>
			                  <select style="text-align: center;"style="width:350px;" id="txtLivro^<%=i %>" name="txtLivro^<%=i %>" class="form-control">
			                        <option id="#" value="#" selected >Selecione </option>
									<%
										List<EntidadeDominio> livros = new LivroDAO().visualizar();
	
										for (EntidadeDominio edo : livros) {
											Livro livro = (Livro) edo;
									%>
									<option id=<%=livro.getId() %> value=<%=livro.getId() %> ><%=livro.getTitulo() %> </option>
							        <%
										}
									%>
							  </select>
			                </td>
							<td >
							<input style="width:50px;" type="text" id="txtQtde" name="txtQtde" class="form-control form-control-alternative">
							</td>
							
							<!-- Buttons actions -->
							
							<td>
							<a href="./estoque.jsp"class="btn btn-danger btn-sm">Remover</a>
							</td>	
							<td></td>
									
						</tr>
						<%
		            				i += 1;
		            			}
		            		}
		            	%>
					</tbody>
					
				</table>
			</div>
			</div>
			
			<div class=col>
            	<hr>
				<h4 class="page-header"> <input type='submit' class='btn btn-success'  id='operacao' name='operacao' value='SALVAR'/></h4>
            	 </div>    
          	  	<div class="row">
					<div class="form-group col-md-8">
						<label for="campo3"></label>
						
					</div>
					</div>
        </main>		
      </div>	  
    </div>	
    <!-- Icons -->    
	<script src="./resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="./resources/bootstrap/js/feather.min.js"></script>
    <script>
    feather.replace()
    </script>  	
  </body>
  <footer> Todos os direitos reservados - Biblioteca Copyright©2019 </footer>
</html>
