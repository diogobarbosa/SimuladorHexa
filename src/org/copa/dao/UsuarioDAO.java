package org.copa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.copa.domain.Usuario;

public class UsuarioDAO {
	
	public void cadastrarUsuario(String nome, String email, String senha) throws SQLException {
		String sql = "insert into usuario(nome, email, senha, acesso) values(?,?,?,?)";
		try (Connection connection = new ConnectionFactory().getConection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setString(2, email);
			statement.setString(3, senha);
			statement.setInt(4, 0);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	public Optional<Usuario> loginUsuario(String nome, String senha) throws SQLException {
		
		
		String sql = "select * from usuario where nome=?";
		try (Connection connection = new ConnectionFactory().getConection()) {
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				String nomeUsuario = resultSet.getString("nome");
				String emailUsuario = resultSet.getString("email");
				String senhaUsuario = resultSet.getString("senha");
				int acesso = resultSet.getInt("acesso");
				
				if (nomeUsuario.equalsIgnoreCase(nome) && senhaUsuario.equals(senha)) {
					Usuario usuario = new Usuario();
					usuario.setNome(nomeUsuario);
					usuario.setEmail(emailUsuario);
					usuario.setSenha(senhaUsuario);
					if(acesso == 0) {
						usuario.setIsAcessoAdm(false);
					}else if(acesso == 1) {
						usuario.setIsAcessoAdm(true);
					}
					
					return Optional.ofNullable(usuario);
				}
			}
			statement.close();
		} catch (Exception e) {
			throw new SQLException(e);
		}
		
		return Optional.empty();
	}
	
}
