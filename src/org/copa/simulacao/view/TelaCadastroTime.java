package org.copa.simulacao.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.copa.dao.TimeDAO;
import org.copa.domain.Time;

public class TelaCadastroTime {

	private JPanel panelTabelaTime;

	public void construirTelaCadastro(JFrame framePrincipal) {

			
		framePrincipal.dispose();
		JFrame frameCadastroTime = new JFrame("Cadastro de Times");

		frameCadastroTime.setSize(900, 600);
		frameCadastroTime.setLayout(new GridLayout(1, 2));
		frameCadastroTime.setVisible(true);
		frameCadastroTime.setLocationRelativeTo(null);
		frameCadastroTime.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelCadastroTime = new JPanel();
		panelCadastroTime.setLayout(null);
		panelCadastroTime.setBackground(Color.getHSBColor(328, 1.0F, 0.49F));

		JTextField txtCadastrarTime = new JTextField();
		txtCadastrarTime.setBounds(20, 20, 220, 30);

		JButton botaoCadastrar = new JButton("Cadastrar time!");
		botaoCadastrar.setBounds(250, 20, 160, 30);
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TimeDAO timeDao = new TimeDAO();
					timeDao.cadastrarTime(txtCadastrarTime.getText());

					panelTabelaTime.removeAll();
					frameCadastroTime.remove(panelTabelaTime);

					criarTabela(framePrincipal, frameCadastroTime);
					frameCadastroTime.validate();

				} catch (SQLException ex) {
					if (ex.getCause() instanceof SQLIntegrityConstraintViolationException) {
						JOptionPane.showMessageDialog(null, "Time já cadastrado!");
					} else {
						JOptionPane.showMessageDialog(null, "Por favor, tente mais tarde!");
					}
				}
			}
		});

		panelCadastroTime.add(txtCadastrarTime);
		panelCadastroTime.add(botaoCadastrar);

		JTextField txtRemoverTime = new JTextField();
		txtRemoverTime.setBounds(20, 60, 220, 30);

		JButton botaoRemover = new JButton("Remover time!");
		botaoRemover.setBounds(250, 60, 160, 30);
		botaoRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					TimeDAO timeDao = new TimeDAO();
					timeDao.removerTime(txtRemoverTime.getText());

					panelTabelaTime.removeAll();
					frameCadastroTime.remove(panelTabelaTime);

					criarTabela(framePrincipal, frameCadastroTime);
					frameCadastroTime.validate();

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Por favor, tente mais tarde!");
				}
			}
		});

		panelCadastroTime.add(txtRemoverTime);
		panelCadastroTime.add(botaoRemover);

		frameCadastroTime.add(panelCadastroTime);
		criarTabela(framePrincipal, frameCadastroTime);

	}
		

	
	public void criarTabela(JFrame framePrincipal, JFrame frameCadastroTime) {
		TimeDAO timeDao = new TimeDAO();
		List<Time> listaTime = timeDao.getListaTimes();
		String[][] nomeTimes = new String[listaTime.size()][1];
		for(int index = 0; index < listaTime.size(); index++) {
			nomeTimes[index][0] = listaTime.get(index).getNome();
		}
		
		String column[]={"TIME"};      
		JTable table =new JTable(nomeTimes,column);   
		JScrollPane scrollTabela =new JScrollPane(table);    
	    scrollTabela.setBounds(10,10,400,400);          

		 
		panelTabelaTime = new JPanel();
		panelTabelaTime.setBackground(Color.getHSBColor(328, 1.0F, 0.49F));
		panelTabelaTime.setLayout(null);
		
		JButton botaoVoltarPrincipal = new JButton("Voltar");
		botaoVoltarPrincipal.setBounds(150, 420, 160, 30);
		botaoVoltarPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameCadastroTime.hide();
				framePrincipal.show();
			}
		});
		
		panelTabelaTime.add(scrollTabela);
		panelTabelaTime.add(botaoVoltarPrincipal);
		frameCadastroTime.add(panelTabelaTime);

	}

}
