package org.copa;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.copa.dao.UsuarioDAO;
import org.copa.domain.Usuario;
import org.copa.simulacao.view.TelaCadastroUsuarios;
import org.copa.simulacao.view.TelaMenu;

public class Principal {

	public static void main(String[] args) {

		JFrame framePrincipal = new JFrame("Simulador da Copa do Mundo 2022!");

		JButton botaoLoginCopa = new JButton("Login");
		botaoLoginCopa.setBounds(130, 100, 200, 40);
		botaoLoginCopa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (realizarLogin()) {
					framePrincipal.hide();
				}
			}
		});

		JButton botaoCadastroUsuario = new JButton("Cadastro de usuário");
		botaoCadastroUsuario.setBounds(350, 100, 200, 40);
		botaoCadastroUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaCadastroUsuarios().construirTelaUsuarios(framePrincipal);
				framePrincipal.hide();
			}
		});

		framePrincipal.add(botaoLoginCopa);
		framePrincipal.add(botaoCadastroUsuario);

		framePrincipal.getContentPane().setBackground(Color.getHSBColor(328, 1.0F, 0.49F));
		framePrincipal.setSize(720, 300);
		framePrincipal.setLayout(null);
		framePrincipal.setVisible(true);
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private static Boolean realizarLogin() {
		JTextField usuario = new JTextField();
		JTextField senha = new JPasswordField();
		Object[] message = { "Usuário:", usuario, "Senha:", senha };

		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {

			try {
				Optional<Usuario> optionalUsuario = new UsuarioDAO().loginUsuario(usuario.getText(), senha.getText());
				if (optionalUsuario.isPresent()) {
					// Constroi a tela de Menu
					new TelaMenu().construirTelaMenu(optionalUsuario.get().getIsAcessoAdm());
					return Boolean.TRUE;
				} else {
					JOptionPane.showMessageDialog(null, "Não foi possível realizar o login.");
					return Boolean.FALSE;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Falha ao cadastrar usuário!");
			}

		}
		return Boolean.FALSE;
	}
}
