package br.com.syslib.enuns;

public enum TipoEndereco {
	
	COB(1,"Cobrança"),
	ENT(2,"Entrega"),
	OUTRO(3,"Outro");
	
	private int codigo;
	private String descricao;
	
	private TipoEndereco(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
		}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
