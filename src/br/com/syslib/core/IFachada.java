package br.com.syslib.core;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.EntidadeDominio;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);

    public Resultado consultar(EntidadeDominio entidade);

    public Resultado alterar(EntidadeDominio entidade); 

    public Resultado excluir(EntidadeDominio entidade);
    
    public Resultado visualizar(EntidadeDominio entidade);
	
	public Resultado buscar(EntidadeDominio entidade);

}
