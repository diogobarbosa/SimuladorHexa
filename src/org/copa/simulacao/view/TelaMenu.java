package org.copa.simulacao.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.copa.dao.TimeDAO;
import org.copa.domain.Time;

public class TelaMenu {

	public void construirTelaMenu(Boolean isAcessoADM) {
		JFrame framePrincipal = new JFrame("Simulador da Copa do Mundo 2022!");

		JButton botaoSimularCopa = new JButton("Simular Copa do mundo!!!");
		botaoSimularCopa.setBounds(130, 100, 200, 40);

		JButton botaoRealizarCadastroTime = new JButton("Cadastrar times");
		botaoRealizarCadastroTime.setBounds(350, 100, 200, 40);
		
		framePrincipal.add(botaoSimularCopa);
		framePrincipal.add(botaoRealizarCadastroTime);
		if(!isAcessoADM) {
			botaoRealizarCadastroTime.setEnabled(false);
		}
		
		framePrincipal.setSize(720, 300);
		framePrincipal.setLayout(null);
		framePrincipal.setVisible(true);
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.getContentPane().setBackground(Color.getHSBColor(328, 1.0F, 0.49F));
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Constroi a Tela de Simulacao
		botaoSimularCopa.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 
	        	 	TimeDAO timeDAO = new TimeDAO();
	     			List<Time> listaTimes = timeDAO.getListaTimes();
	     			if(listaTimes.size() != 32) {
						JOptionPane.showMessageDialog(null, "Para simular a copa é necessário ter 32 times cadastrados. Quantidade atual: "+listaTimes.size());
						return;
	     			}
	        	 	new TelaSimulacao().construirTelaSimulacao(framePrincipal);
	         }
		});
		
		//Constroi a Tela de Cadastro de times
		botaoRealizarCadastroTime.addActionListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e) {
	        	 	new TelaCadastroTime().construirTelaCadastro(framePrincipal);
	         }
		});
	}
}
