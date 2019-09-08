package br.com.syslib.enuns;

public enum CupomPromocional {
	
	C1(1,"C1",5),
	C2(2,"C2",10),
	C3(3,"C3",15);
	
	private int codigo;
	private String descricao;
	private int porcDesc;
	
	private CupomPromocional(int codigo, String descricao, int porcDesc) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.porcDesc = porcDesc;
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

	public int getPorcDesc() {
		return porcDesc;
	}

	public void setPorcDesc(int porcDesc) {
		this.porcDesc = porcDesc;
	}
	
	

}
