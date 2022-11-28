package org.copa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.copa.domain.Time;

public class TimeDAO {

	public void cadastrarTime(String nome) throws SQLException {
		String sql = "insert into time(nome) values(?)";
		try (Connection connection = new ConnectionFactory().getConection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	public void removerTime(String nome) throws SQLException {
		String sql = "delete from time where nome = ?";
		try (Connection connection = new ConnectionFactory().getConection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}

	
	public boolean existeTime(String nome) throws SQLException {
		boolean existeUsuario = false;
		
		String sql = "select * from time where nome=?";
		try (Connection connection = new ConnectionFactory().getConection()) {
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				if (resultSet.getString("nome").equalsIgnoreCase(nome)) {
					existeUsuario = true;
				} else {
					existeUsuario = false;
				}
			}
			
			statement.close();
		} catch (Exception e) {
			throw new SQLException(e);
		}
		
		return existeUsuario;
	}

	public List<Time> getListaTimes() {
		try(Connection connection = new ConnectionFactory().getConection()) {
			List<Time> times = new ArrayList<Time>();
			
			PreparedStatement statement = connection.prepareStatement("select * from time");
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Time time = new Time(resultSet.getString("nome"));
				times.add(time);
			}
			
			resultSet.close();
			statement.close();
			return times;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
