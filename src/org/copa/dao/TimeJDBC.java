package org.copa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TimeJDBC {

	public  void abrirConexaoBanco() throws SQLException {
		
        try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/fj21", "admin", "pass");
			System.out.println("Conectado!");
			conexao.close();
		
        } catch (ClassNotFoundException e) {
		}  
	}
}
