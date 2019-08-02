package br.com.syslib.dominio;

import br.com.syslib.enuns.TipoAcesso;

public class ControleAcesso extends EntidadeDominio{
	
	
	private int idAcesso;
	private TipoAcesso tipoAcesso;
	public int getIdAcesso() {
		return idAcesso;
	}
	public void setIdAcesso(int idAcesso) {
		this.idAcesso = idAcesso;
	}
	public TipoAcesso getTipoAcesso() {
		return tipoAcesso;
	}
	public void setTipoAcesso(TipoAcesso tipoAcesso) {
		this.tipoAcesso = tipoAcesso;
	}
	
	
}
