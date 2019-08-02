package br.com.syslib.core.util;

import br.com.syslib.dominio.Usuario;

public class Logged {
	private static Usuario usuario;

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		Logged.usuario = usuario;
	}
	
	
}
