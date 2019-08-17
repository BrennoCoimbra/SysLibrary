<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="./resources/bootstrap/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<h1>Pagina PAI load jQuery</h1>
<input type="button" value="Carregar pagina" onclick="carregar();"><!-- local de carregamento da pagina filha -->
<div id="mostrarPaginaFilha">

</div>
</body>

<script type="text/javascript">
	function carregar(){
		 $("#mostrarPaginaFilha").load('paginaFilha.jsp');// load pagina em jQuery
	}

</script>
</html>