package br.com.syslib.dominio;

import java.util.List;

public class Cliente extends Usuario {
	
	
	private String cpf;	
	private Boolean ativo;
	private Usuario usuario;
	private List<Endereco> enderecos;
	private List<CartaoCredito> cartoes;
	private List<Cupom> cupons;
	private Endereco endereco;
	private String privacidade;
	private Telefone telefone = new Telefone();  
    private String ranking;
    private String statusCliente;
    private String dataNasc;
    private String genero;  


	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getPrivacidade() {
		return privacidade;
	}
	public void setPrivacidade(String privacidade) {
		this.privacidade = privacidade;
	}
	public List<CartaoCredito> getCartoes() {
		return cartoes;
	}
	public void setCartoes(List<CartaoCredito> cartoes) {
		this.cartoes = cartoes;
	}
	public List<Cupom> getCupons() {
		return cupons;
	}
	public void setCupons(List<Cupom> cupons) {
		this.cupons = cupons;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getStatusCliente() {
		return statusCliente;
	}
	public void setStatusCliente(String statusCliente) {
		this.statusCliente = statusCliente;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	

}
