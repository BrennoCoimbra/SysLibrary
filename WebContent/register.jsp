<!DOCTYPE html>
<%@page contentType="text/html" import="java.util.*, java.text.*" pageEncoding="UTF-8"%>
<%@page import="br.com.syslib.enuns.*" %>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="./resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Principal CSS do Bootstrap -->
    <link href="./resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Estilos customizados para esse template -->
    <link href="./resources/bootstrap/css/signin.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <form action="SalvarCliente" method="POST"class="form-signin">
		<input class="form-control" type="hidden" id="operacao" name="operacao" value="SALVAR">
      <img class="mb-4" src="./resources/bootstrap/imgs/library.svg" alt="" width="72" height="72">	  
      <h5 class="h5 mb-4 font-weight-normal">Informe seus dados para cadastro</h5>
	  
	  <label for="nome" class="sr-only">Nome</label>
      <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome completo" required autofocus>
	  
	  <label for="cpf" class="sr-only">CPF</label>
      <input type="text" name="cpf" id="cpf" class="form-control" placeholder="CPF" required autofocus>
      
      <label for="dataNasc" class="sr-only">Data Nascimento</label>
      <input type="date" name="dataNasc" id="dataNasc" class="form-control" placeholder="Data Nascimento" required autofocus>
      
	  <label for="email" class="sr-only">Endereço de email</label>
      <input type="email" name="email" id="email" class="form-control" placeholder="Seu email" required autofocus>
      
	  <label for="senha" class="sr-only">Senha</label>
      <input type="password" name="senha" id="senha" class="form-control" placeholder="Senha" required>
	  
	  <label for="confirmasenha" class="sr-only">Confirma senha</label>
      <input type="password" name="confirmaSenha" id="confirmaSenha" class="form-control" placeholder="Confirma senha" required>
             <label for="tel">Genero</label>
      
      <div class="row">
  	<div class="form-group col-md-12">
		<select id="genero" name="genero" class="form-control">
			<%
			
				for (Genero generos : Genero.values()) {
			%>
			  <option id=<%=generos.getCodigo() %> value=<%=generos.getCodigo() %>><%=generos.getDescricao() %> </option>
			<%
				}
			%>
			</select>
			
			
			
      </div>
      
      </div>
       <label for="tel">Telefone</label>
      <div class="row">
     
      <div class="form-group col-md-6">
       <select id="tpTelefone" name="tpTelefone" class="form-control">
			<%
			
				for (TipoTelefone tpTelefone : TipoTelefone.values()) {
			%>
			  <option id=<%=tpTelefone.getCodigo() %> value=<%=tpTelefone.getCodigo() %>><%=tpTelefone.getDescricao() %> </option>
			<%
				}
			%>
			</select>
			
			
      </div>
      <div class="form-group col-md-6">
      <input type="text" name="ddd" id="ddd" class="form-control" placeholder=DDD required>	 
      </div>
      <div class="form-group col-md-12">
       <input type="tel" name="telefone" id="telefone" class="form-control" placeholder=Numero required>
       </div>
      </div>
	  <div class="checkbox mb-2">
                <input class="custom-control-input" id="privacidade" name="privacidade" type="checkbox">
                <label class="custom-control-label" for="privacidade">
                  <span class="text-muted">Eu aceito a <a href="./privacy.html" target="_black">Politica de Privacidade</a></span>       
                </label>

		</div>

				<button class="btn-primary btn-block" type="submit">Cria Conta</button>

      <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
    </form>
		
	<!-- Icons -->   
	<script src="./resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="./resources/bootstrap/js/feather.min.js"></script>
    <script>
      feather.replace()
    </script>  
	
  </body>
</html>
