package br.com.syslib.enuns;

public enum TipoRelatorio {
	
	QTDE(1, "Quantidade Vendida"),
	VALOR(2, "Valor Vendido");
	
	private int codigo;
	private String descricao;
	
	private TipoRelatorio(int codigo, String descricao) {
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

