package br.com.syslib.enuns;


public enum TipoUsuario {
	
	CUSTOMER(1, "customer"),
	EMPLOYEE(2, "employee"),
	ADMIN(3,"admin");
	
	private TipoUsuario(int codigo, String descriao) {
		this.codigo = codigo;
		this.descriao = descriao;
	}
	
	private int codigo;
	private String descriao;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDescriao() {
		return descriao;
	}
	public void setDescriao(String descriao) {
		this.descriao = descriao;
	}
	
	public static TipoUsuario getTipoUsuario(int codigoTipoUsuario) {
		for (TipoUsuario tp : TipoUsuario.values()) {
			if (tp.getCodigo() == codigoTipoUsuario)
				return tp;
		}
		return null;
	}
	

}
