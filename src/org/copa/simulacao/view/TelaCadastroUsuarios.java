package org.copa.simulacao.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.copa.dao.UsuarioDAO;

public class TelaCadastroUsuarios {

	public void construirTelaUsuarios(JFrame framePrincipal) {
		
		
		JFrame frameCadastroUsuario = new JFrame("Cadastro de Usuario");

		frameCadastroUsuario.setSize(380, 300);
		frameCadastroUsuario.setLayout(new GridLayout(1, 2));
		frameCadastroUsuario.setVisible(true);
		frameCadastroUsuario.setLocationRelativeTo(null);
		frameCadastroUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelCadastroUsuário = new JPanel();
		panelCadastroUsuário.setLayout(null);
		panelCadastroUsuário.setBackground(Color.getHSBColor(328, 1.0F, 0.49F));

		JLabel labelNomeUsuario = new JLabel("Nome:");
		labelNomeUsuario.setBounds(20, 20, 60, 30);
		labelNomeUsuario.setFont(new Font("Serif", Font.BOLD, 14));
		
		JTextField txtNomeUsuario = new JTextField();
		txtNomeUsuario.setBounds(80, 20, 220, 30);
		
		JLabel labelEmailUsuario = new JLabel("Email:");
		labelEmailUsuario.setBounds(20, 60, 60, 30);
		labelEmailUsuario.setFont(new Font("Serif", Font.BOLD, 14));
		
		JTextField txtEmailUsuario = new JTextField();
		txtEmailUsuario.setBounds(80, 60, 220, 30);
		
		JLabel labelSenhaUsuario = new JLabel("Senha:");
		labelSenhaUsuario.setBounds(20, 100, 60, 30);
		labelSenhaUsuario.setFont(new Font("Serif", Font.BOLD, 14));

		JPasswordField txtSenhaUsuario = new JPasswordField();
		txtSenhaUsuario.setBounds(80, 100, 220, 30);
		
		
		JButton botaoCadastrar = new JButton("Cadastrar usuário");
		botaoCadastrar.setBounds(100, 160, 160, 30);
		botaoCadastrar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				UsuarioDAO usuarioDAO = new UsuarioDAO();
				try {
					usuarioDAO.cadastrarUsuario(txtNomeUsuario.getText(), txtEmailUsuario.getText(), txtSenhaUsuario.getText());
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso.");
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, tente mais tarde!");
				}
			}
		});

		
		JButton botaoVoltar = new JButton("Voltar");
		botaoVoltar.setBounds(100, 200, 160, 30);
		botaoVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frameCadastroUsuario.removeAll();
				frameCadastroUsuario.validate();
				frameCadastroUsuario.hide();
				
				framePrincipal.validate();
				framePrincipal.show();
				
			}
		});
		
		panelCadastroUsuário.add(labelNomeUsuario);
		panelCadastroUsuário.add(txtNomeUsuario);
		panelCadastroUsuário.add(labelEmailUsuario);
		panelCadastroUsuário.add(txtEmailUsuario);
		panelCadastroUsuário.add(labelSenhaUsuario);
		panelCadastroUsuário.add(txtSenhaUsuario);
		panelCadastroUsuário.add(botaoCadastrar);
		panelCadastroUsuário.add(botaoVoltar);

		frameCadastroUsuario.add(panelCadastroUsuário);
		

	}
}
