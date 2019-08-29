package br.com.syslib.core.impl.negocio;

import java.sql.SQLException;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.EstoqueDAO;
import br.com.syslib.core.impl.dao.LivroDAO;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.Livro;

public class ValidarValorVenda implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if (entidade instanceof Estoque) {
			  
			  Estoque estoqueBD;
			  Livro livBD;
			  Estoque estoque = (Estoque) entidade;
			  Double vlCompra = estoque.getValorCompra();
			  LivroDAO daoLiv = new LivroDAO();
			  EstoqueDAO dao = new EstoqueDAO();
			  EntidadeDominio est = new Estoque();
			  EntidadeDominio liv = new Livro();
			  
			  try {
				est = dao.verificarUltPrecCompra(entidade);
			} catch (SQLException e) {
				
				return "Erro ao buscar preço";
			}
			  if(!est.equals(null)) {
				  estoqueBD = (Estoque) est;
				  if(estoque.getValorCompra() < estoqueBD.getValorCompra()) {
					  estoque.setValorCompra(estoqueBD.getValorCompra());
				  }
				  try {
					liv = daoLiv.getEntidadeDominio(estoque.getIdLivro());
				} catch (Exception e) {
					return "Erro ao buscar livro";
				}
				  if(!liv.equals(null)) {
					  livBD = (Livro) liv;
					  if(livBD.getPreficacao().getDescricao().equals("ouro")) {
						  // colocar o valor da venda
						  estoque.setValorVenda(estoque.getValorCompra() * 1.20);
					  } else if(livBD.getPreficacao().getDescricao().equals("prata")){
						  estoque.setValorVenda(estoque.getValorCompra() * 1.15);
						  
					  } else if(livBD.getPreficacao().getDescricao().equals("bronze")){
						  estoque.setValorVenda(estoque.getValorCompra() * 1.10);
					  }
					  return null;
				  }
				  
			  }
			
			return null;
		  } 
		return "Entidade não é estoque";
	}

}
