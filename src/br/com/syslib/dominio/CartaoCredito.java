package br.com.syslib.dominio;

import br.com.syslib.enuns.BandeiraCartao;

public class CartaoCredito extends FormaPagamento {
	
	private String descricao;
	private int idFormaPgto;
	private int idUsuario;
	private String nomeCartao;
	private String numeroCartao;
	private int mes;
	private int ano;
	private int codigoSeguranca;
	private BandeiraCartao bandeiraCartao;
	private int idCliente;
    private String preferencialCartao;
    private String statusCartao;
    private Boolean preferencial;
	private double valorCartao;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getIdFormaPgto() {
		return idFormaPgto;
	}
	public void setIdFormaPgto(int idFormaPgto) {
		this.idFormaPgto = idFormaPgto;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeCartao() {
		return nomeCartao;
	}
	public void setNomeCartao(String nomeCartao) {
		this.nomeCartao = nomeCartao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getCodigoSeguranca() {
		return codigoSeguranca;
	}
	public void setCodigoSeguranca(int codigoSeguranca) {
		this.codigoSeguranca = codigoSeguranca;
	}
	public BandeiraCartao getBandeiraCartao() {
		return bandeiraCartao;
	}
	public void setBandeiraCartao(BandeiraCartao bandeiraCartao) {
		this.bandeiraCartao = bandeiraCartao;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getPreferencialCartao() {
		return preferencialCartao;
	}
	public void setPreferencialCartao(String preferencialCartao) {
		this.preferencialCartao = preferencialCartao;
	}
	public String getStatusCartao() {
		return statusCartao;
	}
	public void setStatusCartao(String statusCartao) {
		this.statusCartao = statusCartao;
	}
	public Boolean getPreferencial() {
		return preferencial;
	}
	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}
	public double getValorCartao() {
		return valorCartao;
	}
	public void setValorCartao(double valorCartao) {
		this.valorCartao = valorCartao;
	}
	
	
}
