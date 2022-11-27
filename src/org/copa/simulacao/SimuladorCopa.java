package org.copa.simulacao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.copa.domain.Fase;
import org.copa.domain.Grupo;
import org.copa.domain.Partida;
import org.copa.domain.Time;

public class SimuladorCopa {

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./Resources/times.properties");
		props.load(file);
		return props;

	}
	
	
	public List<Time> simularCadastroTimes(){
		
	
		try {
			List<Time> listaTimes = new ArrayList<Time>();
			
			Collection<Object> lista = SimuladorCopa.getProp().values();
			lista.forEach(timeProperties -> {
				Time time = new Time(timeProperties.toString());
				listaTimes.add(time);
			});
			
			Collections.shuffle(listaTimes);
			return listaTimes;
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}


	public void exibirResultadoFaseGrupos(List<Grupo> listaGrupos) {
		listaGrupos.forEach(grupo ->{
			grupo.getListaPartidas().forEach(partida ->{
				System.out.println(
						partida.getListaTime().get(0).getNome()+" ("+partida.getListaTime().get(0).getPontos()+" - "+partida.getListaTime().get(0).getSaldoGols()+")"+
						" x "+
						partida.getListaTime().get(1).getNome()+" ("+partida.getListaTime().get(1).getPontos()+" - "+partida.getListaTime().get(1).getSaldoGols()+")"+
						": "+partida.getResultado());
			});
			
			System.out.println("\n\n");
			System.out.println("Times campeões da fase do grupo "+grupo.getNome()+" :"+ grupo.getListaTimes().get(0).getNome()+ " e " +grupo.getListaTimes().get(1).getNome());
		});
	}


	public Grupo realizarOitavasFinal(List<Grupo> listaGrupos) {
		
		Grupo grupoVendedoresOitavaFinal = new Grupo();
		
		List<Time> listaTimesVencedoresOitavaFinal = new ArrayList<Time>();
		for(Grupo grupo : listaGrupos) {
			
			Partida partida = new Partida();
			partida.getListaTime().add(grupo.getListaTimes().get(0));
			partida.getListaTime().add(grupo.getListaTimes().get(1));
			
			Integer resultado01 = (int) Math.round(Math.random() * 3);
			Integer resultado02 = (int) Math.round(Math.random() * 3);
			partida.setResultado(resultado01+"x"+resultado02);
			
			if(resultado01 > resultado02) {
				listaTimesVencedoresOitavaFinal.add(grupo.getListaTimes().get(0));
			}else
			if(resultado01 < resultado02) {
				listaTimesVencedoresOitavaFinal.add(grupo.getListaTimes().get(1));
			}else {
				
				Integer resultadoPenalty01 = (int) Math.round(Math.random() * 10);
				Integer resultadoPenalty02 = (int) Math.round(Math.random() * 10);
				
				if(resultadoPenalty01 > resultadoPenalty02) {
					listaTimesVencedoresOitavaFinal.add(grupo.getListaTimes().get(0));
				}else
				if(resultadoPenalty01 < resultadoPenalty02) {
					listaTimesVencedoresOitavaFinal.add(grupo.getListaTimes().get(1));
				}else {
					listaTimesVencedoresOitavaFinal.add(grupo.getListaTimes().get((int) Math.round(Math.random() * 1)));
				}
			}
			
			grupoVendedoresOitavaFinal.getListaPartidas().add(partida);
			System.out.println("\n\n");
			System.out.println("Time campeão das Oitavas de final:"+ listaTimesVencedoresOitavaFinal.get(listaTimesVencedoresOitavaFinal.size()-1).getNome());

		}
		
		grupoVendedoresOitavaFinal.setListaTimes(listaTimesVencedoresOitavaFinal);
		return grupoVendedoresOitavaFinal;
	}

	public Grupo realizarProximaEtapa(List<Time> listaTimesVencedoresEtapaAnterior, Fase fase) {
		
		Grupo grupoVendedoresProximaEtapa = new Grupo();
		
		List<Time> listaTimesVencedoresProximaEtapa = new ArrayList<Time>();
		for(int index = 0; index <  listaTimesVencedoresEtapaAnterior.size(); index = index+2) {
			
			Partida partida = new Partida();
			partida.getListaTime().add(listaTimesVencedoresEtapaAnterior.get(index));
			partida.getListaTime().add(listaTimesVencedoresEtapaAnterior.get(index+1));
			
			Integer resultado01 = (int) Math.round(Math.random() * 3);
			Integer resultado02 = (int) Math.round(Math.random() * 3);
			partida.setResultado(resultado01+"x"+resultado02);
			
			if(resultado01 > resultado02) {
				listaTimesVencedoresProximaEtapa.add(listaTimesVencedoresEtapaAnterior.get(index));
			}else
			if(resultado01 < resultado02) {
				listaTimesVencedoresProximaEtapa.add(listaTimesVencedoresEtapaAnterior.get(index+1));
			}else {
				
				Integer resultadoPenalty01 = (int) Math.round(Math.random() * 10);
				Integer resultadoPenalty02 = (int) Math.round(Math.random() * 10);
				
				if(resultadoPenalty01 > resultadoPenalty02) {
					listaTimesVencedoresProximaEtapa.add(listaTimesVencedoresEtapaAnterior.get(index));
				}else
				if(resultadoPenalty01 < resultadoPenalty02) {
					listaTimesVencedoresProximaEtapa.add(listaTimesVencedoresEtapaAnterior.get(index+1));
				}else {
					Integer ramdom = (int) Math.round(Math.random() * 1);
					if(ramdom == 0) {
						listaTimesVencedoresProximaEtapa.add(listaTimesVencedoresEtapaAnterior.get(index));
					}else {
						listaTimesVencedoresProximaEtapa.add(listaTimesVencedoresEtapaAnterior.get(index+1));
					}
				}
			}
			
			grupoVendedoresProximaEtapa.getListaPartidas().add(partida);
			System.out.println("\n\n");
			System.out.println("Time campeão ("+fase.getFase()+"):"+ listaTimesVencedoresProximaEtapa.get(listaTimesVencedoresProximaEtapa.size()-1).getNome());
		}
		
		grupoVendedoresProximaEtapa.setListaTimes(listaTimesVencedoresProximaEtapa);
		return grupoVendedoresProximaEtapa;
	}
	
	

	public void realizarPartidasFaseGrupo(List<Grupo> listaGrupos) {
		
		//Iterar sobre cada grupo para realizar os jogos entre os times dentro do mesmo.
		listaGrupos.forEach(grupo ->{
			
			//Iterando sobre os times dentro do grupo para realizar os jogos entre eles
			for(int index = 0; index < grupo.getListaTimes().size(); index++) {
				
				//Interando novamente dentro do grupo de times, para obter os adversários. Todos jogam contra todos, sem repetir os jogos.
				for(int indexAdversario = index+1; indexAdversario < grupo.getListaTimes().size(); indexAdversario++) {
					
					Time time = grupo.getListaTimes().get(index);
					Time timeAdversario = grupo.getListaTimes().get(indexAdversario);
					
					Partida partida = new Partida();
					partida.getListaTime().add(time);
					partida.getListaTime().add(timeAdversario);
					
					Integer resultado01 = (int) Math.round(Math.random() * 3);
					time.setSaldoGols(time.getSaldoGols()+resultado01);
					
					Integer resultado02 = (int) Math.round(Math.random() * 3);
					timeAdversario.setSaldoGols(timeAdversario.getSaldoGols()+resultado02);

					
					if(resultado01 > resultado02) {
						time.setPontos(time.getPontos()+3); 
						time.setQuantidadeVitorias(time.getQuantidadeVitorias()+1);
						timeAdversario.setQuantidadeDerrotas(timeAdversario.getQuantidadeDerrotas()+1);

					}else
					if(resultado02 > resultado01) {
						timeAdversario.setPontos(timeAdversario.getPontos()+3); 
						timeAdversario.setQuantidadeVitorias(timeAdversario.getQuantidadeVitorias()+1);
						time.setQuantidadeDerrotas(time.getQuantidadeDerrotas()+1);

					}else {
						time.setPontos(time.getPontos()+1); 
						time.setQuantidadeEmpates(time.getQuantidadeEmpates()+1);

						timeAdversario.setPontos(timeAdversario.getPontos()+1); 
						timeAdversario.setQuantidadeEmpates(timeAdversario.getQuantidadeEmpates()+1);
					}
					
					partida.setResultado(resultado01+"x"+resultado02);
					
					grupo.getListaPartidas().add(partida);
				}
				
			}
			
		});
	}


	public void separarOsTimesNosGrupos(List<Time> listaTimes, List<Grupo> listaGrupos) {
		Integer quantidadeTimesPorGrupo = listaTimes.size() / listaGrupos.size();
		
		int indexTime = 0; 
		for(Grupo grupo : listaGrupos) { 
			
			for(int index = 0; index < quantidadeTimesPorGrupo; index++) {
				
				grupo.getListaTimes().add(listaTimes.get(indexTime));
				indexTime++;

			}
		}
	}


	public List<Grupo> criarGrupos() {
		Integer quantidadeGrupos = 8;
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		for(int index = 0; index < quantidadeGrupos; index++) {
			
			String letras =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			
			Grupo grupo = new Grupo();
			grupo.setNome(letras.substring(index, index+1));
			listaGrupos.add(grupo);
			
		}
		
		return listaGrupos;
	}
}
