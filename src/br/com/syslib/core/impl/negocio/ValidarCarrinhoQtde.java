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

public class ValidarCarrinhoQtde implements IStrategy {

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
		EntidadeDominio est1 = new Estoque();
		
		if(pedido.getId() == null) {
		int idLivro = pedido.getPedItem().get(0).getItemIdLivro();
		int qtdeLivro = pedido.getPedItem().get(0).getItemQtde();
		int qtdeCarrinho = pedido.getQtdeCar();

		try {
			livro.setId(idLivro);
			if (qtdeLivro >= 1 && qtdeCarrinho == -1 || qtdeLivro > 1 && qtdeCarrinho == 1) {
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
						if (estoqueBD.getQtde() > 0 || pedido.getQtdeCar() == 1) {
							estoqueCarrinho.setQtde(pedido.getQtdeCar());
							estoqueCarrinho.setIdLivro(estoqueBD.getIdLivro());							
							estoqueCarrinho.setTpMov(TipoMovimentacaoEstoque.SAIDA);
						}
						try {
							estoqueDAO.alterar(estoqueCarrinho);

						} catch (Exception e) {
							return "Erro ao atualizar ao atualizar a quantidade de livro no estoque!";
						}
						if (!est1.equals(null)) {
							estoqueBD = (Estoque) est1;
							Estoque estoques = (Estoque) estoqueDAO.getQtdeEstoque(idLivro);
							@SuppressWarnings({ "unchecked", "rawtypes" })
							List<Livro> livros = (ArrayList) livroDAO.consultar(livro);
							pedido.getPedLivro().addAll(livros);
							pedido.setPedEstoque(estoques);
							return "DRIVEOK";
						} else {
							return "Erro ao add livro no carrinho!";
						}
						
					}
				} else {
					return "Erro ao zerar item!";
				}
			} else {
				return "Qtde do item no carrinho é igual 1, para zerar clique em excluir!";
			}
		} catch (Exception e) {
			return "Erro ao buscar livro!";
		}
		
		} else {
		return null;
		}
		return null;
	}

}
