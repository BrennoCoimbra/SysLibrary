package br.com.syslib.dominio;

import br.com.syslib.enuns.Estados;

public class Frete extends EntidadeDominio {
	
	private String peso;
	private String area;
	private Estados estado;
	private String valorFrete;
	
	
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Estados getEstado() {
		return estado;
	}
	public void setEstado(Estados estado) {
		this.estado = estado;
	}
	public String getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(String valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	
}
