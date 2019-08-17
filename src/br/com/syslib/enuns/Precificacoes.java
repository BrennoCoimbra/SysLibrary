package br.com.syslib.enuns;

public enum Precificacoes {
	
	OURO(1,"ouro"),
	PRATA(2,"prata"),
	BRONZE(3,"bronze");
	
	private int codigo;
	private String descricao;
	
	private Precificacoes(int codigo,String descricao) {
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
