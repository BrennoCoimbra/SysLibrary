package br.com.syslib.enuns;

public enum TipoMovimentacaoEstoque {
	
	ENTRADA(1, "Entrada"),
	SAIDA(2, "Saida"),
	EMPRESTIMO(3, "Emprestimo"),
	DEVOLUCAO(4, "Devolucao");
	
	private int codigo;
	private String descricao;
	
	private TipoMovimentacaoEstoque(int codigo, String descricao) {
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
