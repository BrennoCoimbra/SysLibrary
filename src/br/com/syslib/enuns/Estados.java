package br.com.syslib.enuns;

public enum Estados {
	
	AC(1, "Acre"),
	AL(2, "Alagoas"),
	AP(3, "Amapá"),
	AM(4, "Amazonas"),
	BA(5, "Bahia"),
	CE(6, "Ceará"),
	DF(7, "Distrito Federal"),
	ES(8, "Espírito Santo"),
	GO(9, "Goiás"),
	MA(10, "Maranhão"),
	MT(11, "Mato Grosso"),
	MS(12, "Mato Grosso do Sul"),
	MG(13, "Minas Gerais"),
	PA(14, "Pará"),
	PB(15, "Paraíba"),
	PR(16, "Paraná"),
	PE(17, "Pernambuco"),
	PI(18, "Piauí"),
	RJ(19, "Rio de Janeiro"),
	RN(20, "Rio Grande do Norte"),
	RS(21, "Rio Grande do Sul"),
	RO(22, "Rondônia"),
	RR(23, "Roraima"),
	SC(24, "Santa Catarina"),
	SP(25, "São Paulo"),
	SE(26, "Sergipe"),
	TO(27, "Tocantins");
	
	private int codigo;
	private String descricao;
	
	private Estados(int codigo, String descricao) {
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
