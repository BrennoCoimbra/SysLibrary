package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.CupomDAO;
import br.com.syslib.core.impl.dao.EstoqueDAO;
import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.enuns.TipoMovimentacaoEstoque;

public class ValidadorTrocaEstoque implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cupom cupom = (Cupom) entidade;
		Cupom cupomBD;
		Cupom cp = new Cupom();
		CupomDAO daoCp = new CupomDAO();
		EntidadeDominio ent = new EntidadeDominio();
		Estoque estoqueCarrinho = new Estoque();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		
		if(cupom.getQtdeTroca() != 0) {
			if(cupom.getEnviarEstoque() == true) {
				cp.setIdCupom(cupom.getIdCupom());
				cp.setQtdeTroca(cupom.getQtdeTroca());
				cp.setEnviarEstoque(cupom.getEnviarEstoque());
				cp.setIdItem(cupom.getIdItem());
				cp.setIdPedido(cupom.getIdPedido());
				try {
					daoCp.alterar(cp);					
				} catch (Exception e) {
					return "Não foi possível completar a operação";
				}
				try {
				 ent = daoCp.getEntidadeDominioCupom(cp.getIdPedido(), cp.getIdItem());
				} catch (Exception e) {
					return "Não foi possível completar a operação";
				}
				if(ent!=null) {
					cupomBD = (Cupom) ent;
					if(cupomBD.getEnviarEstoque() == true && cupomBD.getStatusCupom().equals("TROCA AUTORIZADA")) {
						estoqueCarrinho.setIdLivro(cupomBD.getIdItem());
						estoqueCarrinho.setQtde(cp.getQtdeTroca());
						estoqueCarrinho.setTpMov(TipoMovimentacaoEstoque.ENTRADA);
						
						try {
							estoqueDAO.alterar(estoqueCarrinho);
							
						} catch (Exception e) {
							return "Erro ao entrar em estoque";
						}
						return "TROCAOK";
					} else {
						return "Erro ao entrar em estoque";
					}
				} else {
					return "Erro ao entrar em estoque";
				}
				
			} else {
				cp.setIdCupom(cupom.getIdCupom());
				cp.setQtdeTroca(cupom.getQtdeTroca());
				cp.setEnviarEstoque(cupom.getEnviarEstoque());
				cp.setIdItem(cupom.getIdItem());
				cp.setIdPedido(cupom.getIdPedido());
				try {
					daoCp.alterar(cp);					
				} catch (Exception e) {
					return "Não foi possível completar a operação";
				}
				
			}
		} else {
			return null;
		}
		return "TROCAOK";
	}

}
