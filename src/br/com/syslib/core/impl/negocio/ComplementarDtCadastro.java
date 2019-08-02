package br.com.syslib.core.impl.negocio;

import java.util.Date;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;

public class ComplementarDtCadastro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {		
		if(extracted(entidade) != null){
			Date data = new Date();		
			extracted(entidade).setDtCadastro(data);
		} else {
			return "Entidade: " + extracted(entidade).getClass().getCanonicalName() + " nula!";
		}
		
		return null;
	}

	private EntidadeDominio extracted(EntidadeDominio entidade) {
		return entidade;
	}
}
