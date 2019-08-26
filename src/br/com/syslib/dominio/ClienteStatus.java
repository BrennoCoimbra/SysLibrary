package br.com.syslib.dominio;

public class ClienteStatus extends EntidadeDominio {
	
	private String descricao;
	private Boolean status;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
