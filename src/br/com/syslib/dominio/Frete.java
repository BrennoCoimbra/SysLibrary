package br.com.syslib.dominio;

import br.com.syslib.enuns.Estados;

public class Frete extends EntidadeDominio {
	
	private String peso;
	private String area;
	private Estados estado;
	private double valorFrete;
	private int idEnd;
	private int idUsuario;
	
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
	public double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	public int getIdEnd() {
		return idEnd;
	}
	public void setIdEnd(int idEnd) {
		this.idEnd = idEnd;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	
}
