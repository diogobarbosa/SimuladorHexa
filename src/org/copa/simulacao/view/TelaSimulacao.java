package org.copa.simulacao.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.copa.domain.Fase;
import org.copa.domain.Grupo;
import org.copa.domain.Partida;
import org.copa.domain.Time;
import org.copa.simulacao.SimuladorCopa;
import org.copa.utils.Utils;

public class TelaSimulacao {

	public void construirTelaSimulacao(JFrame framePrincipal, JButton botaoSimularCopa) {
		
		botaoSimularCopa.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	           
	        	 SimuladorCopa simuladorCopa = new SimuladorCopa();
	        	 framePrincipal.hide();
	        	 
	     		JFrame frameSimulacao =new JFrame("Simulador da Copa do Mundo 2022!");

	        	//Realiza o cadastro de times (Via properties, enquanto não há banco de dados. Para fins de teste)
	     		List<Time> listaTimes = simuladorCopa.simularCadastroTimes();
	     		
	     		
	     		//Cria os grupos para realização da FASE DE GRUPO.
	     		List<Grupo> listaGrupos = simuladorCopa.criarGrupos();
	     		
	     		
	     		//Embaralha os times, e distribui eles aleatóriamente entre os grupos.
	     		simuladorCopa.separarOsTimesNosGrupos(listaTimes, listaGrupos);
	     		
	     		//Realiza as partidas da fase de grupo
	     		simuladorCopa.realizarPartidasFaseGrupo(listaGrupos);
	     		
	     		
	     		Border blackline = BorderFactory.createLineBorder(Color.black);
	     		
	     		JPanel panelSimulacaoGrupo = new JPanel();
	     		for (Grupo grupo : listaGrupos) {
	     			
	     			 JPanel panelGrupo=new JPanel();  
	     			 panelGrupo.setBounds(0, 0,500,500);    
	     			 panelGrupo.setBackground(Color.cyan); 
	     			 panelGrupo.setBorder(blackline);
	     			 
	     			 JLabel labelNomeGrupo = new JLabel(" Grupo "+grupo.getNome());
	     			 panelGrupo.add(labelNomeGrupo);
	     			 
	     			 for(Partida partida : grupo.getListaPartidas()) {
	     				 
	     				Time time = partida.getListaTime().get(0);
	     				Time timeAdversario = partida.getListaTime().get(1);

	     				 JLabel labelPartida = new JLabel(" "+time.getNome()+" vs "+ timeAdversario.getNome()+ ": "+partida.getResultado());
	     				 panelGrupo.add(labelPartida);
	     			 }

	     			Integer timesPorGrupo = listaGrupos.get(0).getListaTimes().size();
	     			Integer quantidadePartidasFaseGrupos = Utils.fatorial(timesPorGrupo) / (Utils.fatorial(timesPorGrupo-2)*2);

	     			 panelGrupo.setLayout(new GridLayout(quantidadePartidasFaseGrupos+1,1));
	     			 panelSimulacaoGrupo.add(panelGrupo);
	     		}
	             
	     		panelSimulacaoGrupo.setSize(900,800); 
	     		panelSimulacaoGrupo.setLayout(new GridLayout(2,4));  
		            
	    		JButton botaoSimularVoltar = new JButton("Voltar");
	    		botaoSimularVoltar.setBounds(350,550,200, 40);
	    		botaoSimularVoltar.addActionListener(new ActionListener() {
		   	         public void actionPerformed(ActionEvent e) {
		   	        	framePrincipal.show();
		   	        	frameSimulacao.hide();
		   	         }
	    		});
	    		
	    		
	     		//Organiza os times do grupo por quantidade de pontos (E desempate por vitória e saldo de gols).
	     		listaGrupos.forEach(grupo ->{
	     			Collections.sort(grupo.getListaTimes(), Collections.reverseOrder());
	     		});
	     		
	     		//apenas exibição em console
	     		simuladorCopa.exibirResultadoFaseGrupos(listaGrupos);
	     		
	     		Grupo grupoVendedoresOitavaFinal = simuladorCopa.realizarOitavasFinal(listaGrupos);
	     		
	     		
   			JPanel panelSimulacaoOitavaFinal=new JPanel();  
   			panelSimulacaoOitavaFinal.setSize(900,300); 
   			panelSimulacaoOitavaFinal.setLayout(new GridLayout(2,4)); 
	     		
	     		for(Partida partida : grupoVendedoresOitavaFinal.getListaPartidas()) {
	     		
	    			JPanel panelOitavaFinal=new JPanel();  
	    			panelOitavaFinal.setBounds(0, 0,100,100);    
	    			panelOitavaFinal.setBackground(Color.cyan); 
	    			panelOitavaFinal.setLayout(new GridLayout(2,1)); 
	    			panelOitavaFinal.setBorder(blackline);
	    			
	    			Time time = partida.getListaTime().get(0);
    				Time timeAdversario = partida.getListaTime().get(1);
    				JLabel labelOitavasFinal = new JLabel("Oitavas de final");
   				JLabel labelPartidaOitavaFinal = new JLabel(" "+time.getNome()+" vs "+ timeAdversario.getNome()+ ": "+partida.getResultado());
	     			panelOitavaFinal.add(labelOitavasFinal);
	     			panelOitavaFinal.add(labelPartidaOitavaFinal);
	    			panelSimulacaoOitavaFinal.add(panelOitavaFinal);
	     		}
	     		

	     		Grupo grupoVencedoresQuartaFinal = simuladorCopa.realizarProximaEtapa(grupoVendedoresOitavaFinal.getListaTimes(), Fase.QUARTA_FINAL);
	     		
	     		JPanel panelSimulacaoQuartaFinal=new JPanel();  
	     		panelSimulacaoQuartaFinal.setSize(900,300); 
	     		panelSimulacaoQuartaFinal.setLayout(new GridLayout(1,4)); 
	     		
	     		for(Partida partida : grupoVencedoresQuartaFinal.getListaPartidas()) {
	     		
	    			JPanel panelQuartaFinal=new JPanel();  
	    			panelQuartaFinal.setBounds(0, 0,100,100);    
	    			panelQuartaFinal.setBackground(Color.cyan); 
	    			panelQuartaFinal.setLayout(new GridLayout(2,1)); 
	    			panelQuartaFinal.setBorder(blackline);
	    			
	    			Time time = partida.getListaTime().get(0);
    				Time timeAdversario = partida.getListaTime().get(1);
    				JLabel labelQuartaFinal = new JLabel("Quartas de final");
   				JLabel labelPartidaQuartaFinal = new JLabel(" "+time.getNome()+" vs "+ timeAdversario.getNome()+ ": "+partida.getResultado());
   				panelQuartaFinal.add(labelQuartaFinal);
   				panelQuartaFinal.add(labelPartidaQuartaFinal);
	     			panelSimulacaoQuartaFinal.add(panelQuartaFinal);
	     		}
	     		
	     		
	     		Grupo grupoVencedoresSemiFinal = simuladorCopa.realizarProximaEtapa(grupoVencedoresQuartaFinal.getListaTimes(), Fase.SEMI_FINAL);
	     		
	     		JPanel panelSimulacaoSemiFinal=new JPanel();  
	     		panelSimulacaoSemiFinal.setSize(900,300); 
	     		panelSimulacaoSemiFinal.setLayout(new GridLayout(1,2)); 
	     		
	     		for(Partida partida : grupoVencedoresSemiFinal.getListaPartidas()) {
	     		
	    			JPanel panelSemiFinal=new JPanel();  
	    			panelSemiFinal.setBounds(0, 0,100,100);    
	    			panelSemiFinal.setBackground(Color.cyan); 
	    			panelSemiFinal.setLayout(new GridLayout(2,1)); 
	    			panelSemiFinal.setBorder(blackline);
	    			
	    			Time time = partida.getListaTime().get(0);
    				Time timeAdversario = partida.getListaTime().get(1);
    				JLabel labelSemiFinal = new JLabel("Semi final");
   				JLabel labelPartidaQuartaFinal = new JLabel(" "+time.getNome()+" vs "+ timeAdversario.getNome()+ ": "+partida.getResultado());
   				panelSemiFinal.add(labelSemiFinal);
   				panelSemiFinal.add(labelPartidaQuartaFinal);
   				panelSimulacaoSemiFinal.add(panelSemiFinal);
	     		}
	     		
	     		
	     		Grupo vencedorFinal = simuladorCopa.realizarProximaEtapa(grupoVencedoresSemiFinal.getListaTimes(), Fase.FINAL);
	     		
	     		JPanel panelSimulacaoFinal=new JPanel();  
	     		panelSimulacaoFinal.setLayout(new GridLayout(1,1)); 
	     		panelSimulacaoFinal.setSize(900,300); 
	     		
	     		JPanel panelFinal=new JPanel(); 
	     		panelFinal.setLayout(new GridLayout(2,1)); 
	     		panelFinal.setBounds(0, 0,100,100);    
	     		panelFinal.setBackground(Color.cyan); 
	     		panelFinal.setBorder(blackline);
   			
   			Time time = vencedorFinal.getListaPartidas().get(0).getListaTime().get(0);
				Time timeAdversario = vencedorFinal.getListaPartidas().get(0).getListaTime().get(1);
				JLabel labelFinal = new JLabel("Final da Copa do mundo!");
				JLabel labelPartidaFinal = new JLabel(" "+time.getNome()+" vs "+ timeAdversario.getNome()+ ": "+vencedorFinal.getListaPartidas().get(0).getResultado());
				panelFinal.add(labelFinal);
				panelFinal.add(labelPartidaFinal);
				panelSimulacaoFinal.add(panelFinal);
				
				
	     		JPanel panelContainerMataAMata=new JPanel();  
	     		panelContainerMataAMata.setSize(900,300); 
	     		panelContainerMataAMata.setLayout(new GridLayout(5,1)); 
	     		panelContainerMataAMata.add(panelSimulacaoOitavaFinal);
	     		panelContainerMataAMata.add(panelSimulacaoQuartaFinal);
	     		panelContainerMataAMata.add(panelSimulacaoSemiFinal);
	     		panelContainerMataAMata.add(panelSimulacaoFinal);
	     		panelContainerMataAMata.add(botaoSimularVoltar);


	     		frameSimulacao.add(panelSimulacaoGrupo);
	     		frameSimulacao.add(panelContainerMataAMata);
	     		
	     		frameSimulacao.setSize(900,1200); 
	     		frameSimulacao.setLayout(new GridLayout(2,1));
	     		frameSimulacao.setVisible(true);  
	     		frameSimulacao.setLocationRelativeTo(null); 
	     		frameSimulacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	        	 
	         }
	      });
	}
}
