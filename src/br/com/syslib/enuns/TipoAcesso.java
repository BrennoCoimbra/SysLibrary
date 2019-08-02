package br.com.syslib.enuns;

public enum TipoAcesso {
	CADASTRAR(1, "Salvar"),
	ALTERAR(2, "Alterar"),
	EXCLUIR(3, "Excluir"),
	CONSULTAR(4, "Consultar"),
	LIBERAR(5, "Liberar");
	
	private int codigo;
	private String descricao;
	
	private TipoAcesso(int codigo, String descricao) {
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
