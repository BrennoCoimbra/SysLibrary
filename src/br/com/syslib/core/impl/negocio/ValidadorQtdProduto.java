package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Livro;

public class ValidadorQtdProduto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Livro){
			Livro c = (Livro)entidade;
			
			if(c.getQuantidade() < 1){
				return "Quantidade deve ser no minimo 1!";
			}
			
		}else{
			return "Quantidade não pode ser validado, pois entidade não é um livro!";
		}
		
		
		return null;
	}

}
