package br.com.syslib.dominio;

import br.com.syslib.enuns.TipoMovimentacaoEstoque;

public class Estoque extends EntidadeDominio {
	
	public int idLivro;
	public TipoMovimentacaoEstoque tpMov;
	public String valor;
	public int qtde;
	public int qtdeTemp;
	
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
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
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
	
	
	
}
