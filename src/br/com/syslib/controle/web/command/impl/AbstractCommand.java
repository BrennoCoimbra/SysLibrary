package br.com.syslib.controle.web.command.impl;

import br.com.syslib.core.IFachada;
import br.com.syslib.core.impl.controle.Fachada;

public abstract class AbstractCommand implements ICommand{
	
	protected IFachada fachada = new Fachada();

}
