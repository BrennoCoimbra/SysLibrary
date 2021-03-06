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
    <form action="/SysLibrary/autenticado/Login" method="POST"class="form-signin">
    <input type="hidden" id="url" name="url" value="<%=request.getParameter("url")%>">
	<input class="form-control" type="hidden" id="operacao" name="operacao" value="CONSULTAR">
	<% if(request.getAttribute("msg") != null) { %>
	 <div class="alert alert-danger">
		<br>
		<h4>${msg}</h4>
		</div>
		<%} else { } %>
      <img class="mb-4" src="http://localhost:8080/SysLibrary/resources/bootstrap/imgs/library.svg" alt="" width="72" height="72">	  
      <h2 class="h4 mb-4 font-weight-normal">Faça Login</h2>
      <label for="inputEmail" class="sr-only">Endereço de email</label>
      <input type="email" name="email" id="email" class="form-control" placeholder="Seu email" >
      <label for="inputPassword" class="sr-only">Senha</label>
      <input type="password" name="senha" id="senha" class="form-control" placeholder="Senha" >
      <div class="checkbox mb-1">
        <label>
          <input type="checkbox" value="remember-me"> Lembrar de mim
        </label>
      </div>
	  <div style="text-align: left;">
<!--             <span class="pull-center"><a href="http://localhost:8080/SysLibrary/passwd.jsp">Esqueceu senha?</a></span><br> -->
         <span class="pull-center"><a href="http://localhost:8080/SysLibrary/register.jsp">Criar nova conta</a></span>   
         </div>
      <button class="btn-primary btn-block" type="submit">Entrar</button>
    
		
		
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
