package br.com.syslib.core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection getConnection()
			throws ClassNotFoundException, 
			SQLException{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/library_test?useSSL=false&useTimezone=true&serverTimezone=America/Sao_Paulo";
			String user = "glivros";
			String paswd = "glivros";
			Class.forName( driver );
			Connection conn = DriverManager.getConnection(url, user, paswd);
			
			return conn;
		}
	

}
