package br.com.syslib.enuns;

public enum TipoResidencia {
	
	CASA(1,"Casa"),
	APT(2,"Apartamento"),
	FLAT(3,"Flat"),
	KITNET(4,"Kitnet");
	
	private int codigo;
	private String descricao;
	
	private TipoResidencia(int codigo, String descricao) {
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



