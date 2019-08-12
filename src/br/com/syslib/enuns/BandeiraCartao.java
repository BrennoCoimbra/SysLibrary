package br.com.syslib.enuns;

public enum BandeiraCartao {
	
	VISA(1, "Visa"),
	MASTERCARD(2, "MasterCard"),
	ELO(3, "Elo"),
	AMERICAN_EXPRESS(4,"Americacan Express"),
	DINNERS_CLUB(5,"Dinners Club"),	
	HIPER(6,"Hiper"),
	HIPERCARD(7,"Hipercard");
	
	private int codigo;
	private String descricao;
	
	private BandeiraCartao(int codigo, String descricao) {
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
