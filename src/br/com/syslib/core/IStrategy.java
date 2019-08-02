package br.com.syslib.core;

import br.com.syslib.dominio.EntidadeDominio;

public interface IStrategy {
	
	public String processar(EntidadeDominio entidade);

}
