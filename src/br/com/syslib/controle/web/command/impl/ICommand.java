package br.com.syslib.controle.web.command.impl;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.EntidadeDominio;

public interface ICommand {
	public Resultado execute(EntidadeDominio entidade);

}
