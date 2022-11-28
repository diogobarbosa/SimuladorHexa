package org.copa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/copa", "root", "123456");
			return conexao;
        } catch (Exception e) {
        	throw new SQLException(e);
		}  																				
	}
	
}
