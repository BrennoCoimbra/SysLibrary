package br.com.syslib.dominio;

import br.com.syslib.enuns.Estados;
import br.com.syslib.enuns.TipoEndereco;
import br.com.syslib.enuns.TipoLogradouro;
import br.com.syslib.enuns.TipoResidencia;

public class Endereco extends EntidadeDominio {
	
	private int idUsuario;
	private String descricao;
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	private String pais;
	private Estados estados;
	private TipoLogradouro tpLogrdo;
	private TipoResidencia tpResid;
	private TipoEndereco tpEnd;
	private Boolean preferencial;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public TipoLogradouro getTpLogrdo() {
		return tpLogrdo;
	}
	public void setTpLogrdo(TipoLogradouro tpLogrdo) {
		this.tpLogrdo = tpLogrdo;
	}
	public TipoResidencia getTpResid() {
		return tpResid;
	}
	public void setTpResid(TipoResidencia tpResid) {
		this.tpResid = tpResid;
	}
	public TipoEndereco getTpEnd() {
		return tpEnd;
	}
	public void setTpEnd(TipoEndereco tpEnd) {
		this.tpEnd = tpEnd;
	}
	public Estados getEstados() {
		return estados;
	}
	public void setEstados(Estados estados) {
		this.estados = estados;
	}
	public Boolean getPreferencial() {
		return preferencial;
	}
	public void setPreferencial(Boolean preferencial) {
		this.preferencial = preferencial;
	}
	
	
	

}
