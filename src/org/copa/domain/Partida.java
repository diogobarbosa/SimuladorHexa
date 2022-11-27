package org.copa.domain;

import java.util.ArrayList;
import java.util.List;

public class Partida {

	private String resultado;
	private List<Time> listaTime = new ArrayList<Time>();
	private Fase fase;
	private String penalties;
	
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public List<Time> getListaTime() {
		return listaTime;
	}
	public void setListaTime(List<Time> listaTime) {
		this.listaTime = listaTime;
	}
	public Fase getFase() {
		return fase;
	}
	public void setFase(Fase fase) {
		this.fase = fase;
	}
	public String getPenalties() {
		return penalties;
	}
	public void setPenalties(String penalties) {
		this.penalties = penalties;
	}
	
}
