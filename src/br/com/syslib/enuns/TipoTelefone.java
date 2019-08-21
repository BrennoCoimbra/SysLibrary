package br.com.syslib.enuns;

public enum TipoTelefone {
	
	RESIDENCIAL(1,"Residencial"),
	CELULAR(2,"Celular");
	
	private int codigo;
	private String descricao;
	
	private TipoTelefone(int codigo,String descricao) {
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
