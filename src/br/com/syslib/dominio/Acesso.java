package br.com.syslib.dominio;

import java.util.List;

public class Acesso extends EntidadeDominio{
	
	private String descricao;
	private List<EntidadeDominio> controlesAcesso;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public List<EntidadeDominio> getControlesAcesso() {
		return controlesAcesso;
	}
	public void setControlesAcesso(List<EntidadeDominio> controlesAcesso) {
		this.controlesAcesso = controlesAcesso;
	}
	
	

}
