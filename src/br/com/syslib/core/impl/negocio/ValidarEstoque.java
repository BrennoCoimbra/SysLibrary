package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;

public class ValidarEstoque implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		  if (entidade instanceof Estoque) {
			  
			 
			  Estoque estoque = (Estoque) entidade;
			  int qtde = estoque.getQtde();
			  Double vlCompra = estoque.getValorCompra();
			  String dataEnt = estoque.getDataEnt();
			  
			  
			  
			  
			  if(qtde == 0 || vlCompra == 0.0 || dataEnt == null) {
				  return "Quantidade, valor de compra e Data de Entrada são dados obrigatórios";
			  }
			  
			  if(qtde <= 0 || vlCompra <= 0.0){
				  return "Quantidade ou Valor de compra não podem ser negativos";
			  }
			  
			  if(dataEnt.trim().equals("")){
				  return "Data de entrada não pode ser nula";
			  }
			  
			
			
		  } else {
			  return "Entidade não é estoque";
		  }
		  
		  return null;
		
	}
}
