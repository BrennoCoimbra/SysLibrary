<!DOCTYPE html>
<%@page import="br.com.syslib.dominio.*"%>
<%@page contentType="text/html" import="java.util.*, java.text.*" pageEncoding="UTF-8"%>
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
  <%
  ArrayList<EntidadeDominio> usuarioLogado = session.getAttribute("usuarioLogado") == null ? null : (ArrayList) session.getAttribute("usuarioLogado");
    String pagina = session.getAttribute("pagina") == null ? null : (String) session.getAttribute("pagina");
    String pgSemAut = (String) request.getParameter("pgSemAut");
    %>
  <body class="text-center">
    <form action="/SysLibrary/autenticado/Login" method="POST"class="form-signin">
	<input class="form-control" type="hidden" id="operacao" name="operacao" value="CONSULTAR">
      <img class="mb-4" src="./resources/bootstrap/imgs/library.svg" alt="" width="72" height="72">	  
      <h2 class="h4 mb-4 font-weight-normal">Faça Login</h2>
      <label for="inputEmail" class="sr-only">Endereço de email</label>
      <input type="email" name="email" id="email" class="form-control" placeholder="Seu email" required autofocus>
      <label for="inputPassword" class="sr-only">Senha</label>
      <input type="password" name="senha" id="senha" class="form-control" placeholder="Senha" required>
      <div class="checkbox mb-1">
        <label>
          <input type="checkbox" value="remember-me"> Lembrar de mim
        </label>
      </div>
	  <div style="text-align: left;">
            <span class="pull-center"><a href="#">Esqueceu senha?</a></span><br>
         <span class="pull-center"><a href="./register.jsp">Criar nova conta</a></span>   
         </div>
      <button class="btn-primary btn-block" type="submit">Entrar</button>
      <p class="mt-5 mb-3 text-muted">&copy;2018-2019</p>
    </form>
	
	<!-- Icons -->    
	<script src="./resources/bootstrap/js/jquery-3.3.1.slim.min.js"></script>
	<script src="./resources/bootstrap/js/feather.min.js"></script>
    <script>
      feather.replace()
    </script>  
	
  </body>
</html>
