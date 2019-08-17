package br.com.syslib.dominio;

/**
 * @author brenno
 *
 */
public class Fornecedor extends EntidadeDominio {
	
	String nomeFornecedor;
	
	/**
	 * @return obtem nome do Fornecedor
	 */
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	/**
	 * @param nomeFornecedor
	 */
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	
	

}
