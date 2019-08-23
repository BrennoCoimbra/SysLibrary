package br.com.syslib.dominio;

import br.com.syslib.enuns.TipoUsuario;

public class Usuario extends EntidadeDominio {
	
	private String nome;
	private String email;
	private String senha;
	private String senha1;
	private String senha2;
	private TipoUsuario tipoUsuario;
	private Boolean ativo;	
	private String privacidade;


	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha1() {
		return senha1;
	}
	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getPrivacidade() {
		return privacidade;
	}
	public void setPrivacidade(String privacidade) {
		this.privacidade = privacidade;
	}
	
	
	

}
