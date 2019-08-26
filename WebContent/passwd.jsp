<!DOCTYPE html>
<%@page import="br.com.syslib.dominio.*"%>
<%@page import="br.com.syslib.core.aplicacao.*"%>

<%@page contentType="text/html" import="java.util.*, java.text.*" pageEncoding="UTF-8"%>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library_icon.ico">

    <title>SysLibrary</title>

    <!-- Principal CSS do Bootstrap -->
    <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Estilos customizados para esse template -->
    <link href="http://localhost:8080/SysLibrary/resources/bootstrap/css/signin.css" rel="stylesheet">
  </head>
  
  <body class="text-center">
  <%
            Resultado resultado = (Resultado) request.getAttribute("resultado");
        %> 
    <form action="SalvarCliente" method="POST"class="form-signin">
    <input type="hidden" id="url" name="url" value="PASSWD">
	<input class="form-control" type="hidden" id="operacao" name="operacao" value="CONSULTAR">
      <img class="mb-4" src="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library.svg" alt="" width="72" height="72">	  
      <h4 class="h4 mb-4 font-weight-normal">Insira seu e-mail e 4 ultimos digitos do CPF</h4>
      <label for="inputEmail" class="sr-only">Endereço de email</label>
      <input type="email" name="email" id="email" class="form-control" placeholder="Seu email" >
      <label for="inputPassword" class="sr-only">CPF</label>
      <input type="password" name="cpf" id="cpf" class="form-control" placeholder="CPF" >
      <div class="checkbox mb-1">
      
      </div>
	  <div style="text-align: left;">
           </div>
      <button class="btn-primary btn-block" type="submit">Recuperar</button>
      <p class="mt-5 mb-3 text-muted">&copy;2018-2019</p>
    </form>	
	<!-- Icons -->    
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="http://localhost:8080/SysLibrary/resources/bootstrap/js/feather.min.js"></script>
    <script>
      feather.replace()
    </script>  
	
  </body>
</html>
