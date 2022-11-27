 package org.copa.domain;

public class Usuario {

	private String nome;
	private String senha;
	private String email;
	
	private Boolean isAcessoAdm;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAcessoAdm() {
		return isAcessoAdm;
	}

	public void setIsAcessoAdm(Boolean isAcessoAdm) {
		this.isAcessoAdm = isAcessoAdm;
	}
	
}
