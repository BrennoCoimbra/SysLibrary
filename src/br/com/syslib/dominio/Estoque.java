package br.com.syslib.dominio;

import br.com.syslib.enuns.TipoMovimentacaoEstoque;

public class Estoque extends EntidadeDominio {
	
	public int idLivro;
	public TipoMovimentacaoEstoque tpMov;
	public double valorVenda;
	public double valorCompra;
	public int qtde;
	public int qtdeTemp;
	public Fornecedor fornecedor;
	public String dataEnt;
	
	public int getId_Livro() {
		return idLivro;
	}
	public void setId_Livro(int id_Livro) {
		this.idLivro = id_Livro;
	}
	public TipoMovimentacaoEstoque getTpMov() {
		return tpMov;
	}
	public void setTpMov(TipoMovimentacaoEstoque tpMov) {
		this.tpMov = tpMov;
	}
	public Double getValor() {
		return valorVenda;
	}
	public void setValor(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	public int getQtdeTemp() {
		return qtdeTemp;
	}
	public void setQtdeTemp(int qtdeTemp) {
		this.qtdeTemp = qtdeTemp;
	}
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public String getDataEnt() {
		return dataEnt;
	}
	public void setDataEnt(String dataEnt) {
		this.dataEnt = dataEnt;
	}
	
	
	
}
