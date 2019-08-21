package br.com.syslib.dominio;

import br.com.syslib.enuns.TipoUsuario;

public class Usuario extends EntidadeDominio {
	
	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;
	private TipoUsuario tipoUsuario;
	private Boolean ativo;	
	private String privacidade;


	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
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
	public String getSenhaRepetida() {
		return confirmaSenha;
	}
	public void setSenhaRepetida(String senhaRepetida) {
		this.confirmaSenha = senhaRepetida;
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
