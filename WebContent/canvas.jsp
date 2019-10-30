<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
 
<%
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
String dataPoints = null;
 
try{
	Class.forName("com.mysql.jdbc.Driver"); 
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/library?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo", "glivros", "glivros");
	Statement statement = connection.createStatement();
	String xVal, yVal;

	
	ResultSet resultSet = statement.executeQuery("select T1.ped_dtCadastro, T0.item_titulo, SUM(T0.item_qtde)  from item_pedido T0 INNER JOIN pedido T1 ON T1.ped_id = T0.ped_item_id  group by T1.ped_dtCadastro, T0.item_titulo");
	
	while(resultSet.next()){
		xVal = resultSet.getString("T0.item_titulo");
		yVal = resultSet.getString("SUM(T0.item_qtde)");
		
		map = new HashMap<Object,Object>();  map.put("y", Double.parseDouble(yVal)); map.put("label", (xVal)); list.add(map);
		/*
		map = new HashMap<Object,Object>(); map.put("label", "1 Star"); map.put("y", 8889513); list.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "2 Star"); map.put("y", 3168220); list.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "3 Star"); map.put("y", 6611718); list.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "4 Star"); map.put("y", 11331319); list.add(map);
		map = new HashMap<Object,Object>(); map.put("label", "5 Star"); map.put("y", 43476553); list.add(map);
		*/
		dataPoints = gsonObj.toJson(list);
	}
	System.out.println(dataPoints);
	connection.close();
}
catch(SQLException e){
	out.println("<div  style='width: 50%; margin-left: auto; margin-right: auto; margin-top: 200px;'>Could not connect to the database. Please check if you have mySQL Connector installed on the machine - if not, try installing the same.</div>");
	dataPoints = null;
}
%>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
window.onload = function() { 
 
<% if(dataPoints != null) { %>
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	exportEnabled: true,
	title: {
		text: "JSP Column Chart from Database"
	},
	data: [{
		type: "spline", //change type to bar, line, area, pie, etc
		dataPoints: <%out.print(dataPoints);%>
	},
	
	
	]
	
});
chart.render();
<% } %> 
 
}
</script>
</head>
<body>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="http://localhost:8080/SysLibrary/resources/js/canvasjs.min.js"></script>
</body>
</html>              