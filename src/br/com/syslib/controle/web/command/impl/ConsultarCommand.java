package br.com.syslib.controle.web.command.impl;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.EntidadeDominio;

public class ConsultarCommand extends AbstractCommand  {

	@Override
	public Resultado execute(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return fachada.consultar(entidade);
	}


}
