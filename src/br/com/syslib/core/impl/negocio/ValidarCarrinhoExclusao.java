package br.com.syslib.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.EstoqueDAO;
import br.com.syslib.core.impl.dao.LivroDAO;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.Pedido;
import br.com.syslib.enuns.TipoMovimentacaoEstoque;

public class ValidarCarrinhoExclusao implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido pedido = (Pedido) entidade;
		Livro livro = new Livro();
		LivroDAO livroDAO = new LivroDAO();
		Estoque estoqueCarrinho = new Estoque();
		List<EntidadeDominio> livroBD = new ArrayList<>();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		Estoque estoqueBD;
		EntidadeDominio est = new Estoque();

		if(pedido.getIdPedido() == 0) {
		int idLivro = pedido.getPedItem().get(0).getItemIdLivro();
		int qtdeLivro = pedido.getPedItem().get(0).getItemQtde();
		//int qtdeCarrinho = pedido.getQtdeCar();
		
		try {
			livro.setId(idLivro);
			livroBD = livroDAO.consultar(livro);
			if (!livroBD.isEmpty()) {
				livro = (Livro) livroBD.get(0);
				try {
					est = estoqueDAO.getQtdeEstoque(idLivro);
				} catch (Exception e) {
					return "Erro ao buscar quantidade de livro!";
				}
				if (!est.equals(null)) {
						estoqueBD = (Estoque) est;	
						estoqueCarrinho.setQtde(qtdeLivro);
						estoqueCarrinho.setIdLivro(estoqueBD.getIdLivro());							
						estoqueCarrinho.setTpMov(TipoMovimentacaoEstoque.ENTRADA);
					
					try {
						estoqueDAO.alterar(estoqueCarrinho);
						return "DRIVEOK";
					} catch (Exception e) {
						return "Erro ao atualizar ao atualizar a quantidade de livro no estoque!";
					}
					
					}
				} else {
				return "Erro ao excluir item!";
			}
		} catch (Exception e) {
			return "Erro ao buscar livro!";
		}

		return "Erro ao excluir item!";
	} else {
		return null;
	}
	}

}
