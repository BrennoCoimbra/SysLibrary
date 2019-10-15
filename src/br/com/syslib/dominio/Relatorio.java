package br.com.syslib.dominio;

import java.util.Date;

import br.com.syslib.enuns.TipoRelatorio;

public class Relatorio extends EntidadeDominio {
	private String titulo;
	private Date dataInicial;
	private Date dataFinal;
	private Date data;
	private int mes;
	private int ano;
	private double qtde;
	private double valor;
	private TipoRelatorio tipoRelatorio;
	private String analise;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	public double getQtde() {
		return qtde;
	}
	public void setQtde(double qtde) {
		this.qtde = qtde;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public TipoRelatorio getTipoRelatorio() {
		return tipoRelatorio;
	}
	public void setTipoRelatorio(TipoRelatorio tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}
	public String getAnalise() {
		return analise;
	}
	public void setAnalise(String analise) {
		this.analise = analise;
	} 
	
}
