package br.com.syslib.dominio;

import br.com.syslib.enuns.TipoTelefone;

public class Telefone extends EntidadeDominio {
	
	   private int idTel;
	   private String tipoTel;
	   private String numTel;
	   private String telDDD;
	   private TipoTelefone tpTelefone;
	   
	public int getIdTel() {
		return idTel;
	}
	public void setIdTel(int idTel) {
		this.idTel = idTel;
	}
	public String getTipoTel() {
		return tipoTel;
	}
	public void setTipoTel(String tipoTel) {
		this.tipoTel = tipoTel;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getTelDDD() {
		return telDDD;
	}
	public void setTelDDD(String telDDD) {
		this.telDDD = telDDD;
	}
	public TipoTelefone getTpTelefone() {
		return tpTelefone;
	}
	public void setTpTelefone(TipoTelefone tpTelefone) {
		this.tpTelefone = tpTelefone;
	}
	   
	   
}
