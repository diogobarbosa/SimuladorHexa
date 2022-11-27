package org.copa;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.copa.simulacao.view.TelaSimulacao;

public class Principal {

	public static void main(String[] args) {
		
		
		JFrame framePrincipal =new JFrame("Simulador da Copa do Mundo 2022!");
		
		JButton botaoSimularCopa = new JButton("Simular Copa do mundo!!!");
		botaoSimularCopa.setBounds(130,100,200, 40);

		JButton botaoRealizarCadastroTime = new JButton("Cadastrar times");
		botaoRealizarCadastroTime.setBounds(350,100,200, 40);
		
		framePrincipal.add(botaoSimularCopa);
		framePrincipal.add(botaoRealizarCadastroTime);
    
		framePrincipal.setSize(720,300); 
		framePrincipal.setLayout(null);
		framePrincipal.setVisible(true);  
		framePrincipal.setLocationRelativeTo(null); 
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		
		//Constroi a Tela de Simulacao
		new TelaSimulacao().construirTelaSimulacao(framePrincipal, botaoSimularCopa);
		
		
		//Constroi a Tela de Cadastro
	}
}
