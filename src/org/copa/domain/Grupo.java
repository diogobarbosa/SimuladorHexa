package org.copa.domain;

import java.util.ArrayList;
import java.util.List;

public class Grupo {

	private String nome;
	private List<Time> listaTimes;
	private List<Partida> listaPartidas;
	
	public Grupo() {
		this.listaTimes = new ArrayList<Time>();
		this.listaPartidas = new ArrayList<Partida>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Time> getListaTimes() {
		return listaTimes;
	}
	public void setListaTimes(List<Time> listaTimes) {
		this.listaTimes = listaTimes;
	}

	public List<Partida> getListaPartidas() {
		return listaPartidas;
	}

	public void setListaPartidas(List<Partida> listaPartidas) {
		this.listaPartidas = listaPartidas;
	}
	
}
