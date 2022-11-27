package org.copa.domain;

public enum Fase {

	FASE_DE_GRUPOS ("Fase de grupos"),
	OITAVA_FINAL("Oitava de finais"),
	QUARTA_FINAL("Quarta de finais"),
	SEMI_FINAL("Semi final"),
	FINAL("Final");
	
	private String fase;
	
	private Fase(String fase) {
		this.fase = fase;
	}
	
	public String getFase() {
		return fase;
	}
}
