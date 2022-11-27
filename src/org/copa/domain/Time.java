package org.copa.domain;

import java.util.Objects;

public class Time implements Comparable<Time>{

	private String nome;
	private Integer saldoGols;
	private Integer pontos;
	private Integer quantidadeVitorias;
	private Integer quantidadeDerrotas;
	private Integer quantidadeEmpates;
	
	public Time(String nome) {
		this.nome = nome;
		this.saldoGols = 0;
		this.pontos = 0;
		this.quantidadeVitorias = 0;
		this.quantidadeDerrotas = 0;
		this.quantidadeEmpates = 0;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getSaldoGols() {
		return saldoGols;
	}
	public void setSaldoGols(Integer saldoGols) {
		this.saldoGols = saldoGols;
	}
	public Integer getPontos() {
		return pontos;
	}
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	public Integer getQuantidadeVitorias() {
		return quantidadeVitorias;
	}
	public void setQuantidadeVitorias(Integer quantidadeVitorias) {
		this.quantidadeVitorias = quantidadeVitorias;
	}
	public Integer getQuantidadeDerrotas() {
		return quantidadeDerrotas;
	}
	public void setQuantidadeDerrotas(Integer quantidadeDerrotas) {
		this.quantidadeDerrotas = quantidadeDerrotas;
	}
	public Integer getQuantidadeEmpates() {
		return quantidadeEmpates;
	}
	public void setQuantidadeEmpates(Integer quantidadeEmpates) {
		this.quantidadeEmpates = quantidadeEmpates;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Time other = (Time) obj;
		return Objects.equals(nome, other.nome);
	}

	@Override
	public int compareTo(Time outroTime) {

		if(this.pontos > outroTime.getPontos()) return 1;
		if(this.pontos < outroTime.getPontos()) return -1;
		
		if(this.saldoGols > outroTime.getSaldoGols()) return 1;
		if(this.saldoGols < outroTime.getSaldoGols()) return -1;

		if(this.quantidadeVitorias > outroTime.getQuantidadeVitorias()) return 1;
		if(this.quantidadeVitorias < outroTime.getQuantidadeVitorias()) return -1;
		
		return 0;

	}

	
}
