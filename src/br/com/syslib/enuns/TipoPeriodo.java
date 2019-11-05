package br.com.syslib.enuns;

public enum TipoPeriodo {
	
	DIA(1, "Por Dia"),
	MES(2, "Por Mês"),
	SEMANAL(3, "Por Semana"),
	ANO(4, "Por Ano");
	
	private int codigo;
	private String descricao;
	
	private TipoPeriodo(int codigo, String descricao) {
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

