package br.com.syslib.core.impl.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.CupomDAO;
import br.com.syslib.core.impl.dao.EstoqueDAO;
import br.com.syslib.core.impl.dao.PedidoDAO;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Pedido;
import br.com.syslib.enuns.TipoMovimentacaoEstoque;

public class ValidarCancelamentoPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Pedido) {
			Pedido pedido = (Pedido) entidade ;
			Pedido pedidoBD;
			PedidoDAO daoPed = new PedidoDAO();
			CupomDAO daoCupom = new CupomDAO();
			EntidadeDominio ent = new EntidadeDominio();
			EntidadeDominio ent2 = new EntidadeDominio();
			Date dataAtual = new Date();
			Date dataBD = new Date();
			Calendar calendar = Calendar.getInstance();			
			long diferenca;
			Estoque estoque = new Estoque();
			EstoqueDAO estoqueDAO = new EstoqueDAO();
			int i = 0;
			
			if(pedido.getIdPedido() > 0) {
			try {
				ent = daoPed.getEntidadeDoiminio(pedido.getIdPedido());
			} catch (Exception e) {
				return "Erro ao consultar pedido";
			}
			if(!ent.equals(null)) {
				pedidoBD = (Pedido) ent;
				try {
					ent2 = daoCupom.getEntidadeDominioCupom(pedido.getIdPedido());
				} catch (Exception e) {
					return "Erro ao verificar se o pedido está associado com algum cupom de troca";
				}
				if(ent2 == null) {
				//7 dias p/ cancelamento da compra
				calendar.setTime(dataAtual);
				dataBD = pedidoBD.getDtCadastro();
				calendar.setTime(dataBD);
				
				 diferenca = TimeUnit.MILLISECONDS.toDays(dataAtual.getTime() - dataBD.getTime());
				if(diferenca <= 6 && pedidoBD.getStatusPedido().equals("EM PROCESSAMENTO")) {
					//cancelando pedido
					try {
						pedido.setStatusPedido("CANCELADO");
						daoPed.excluir(pedido);
						//return "CANCELOK";
					} catch (Exception e) {
						return "Erro ao cancelar pedido";
					}
					for(ItemPedido it : pedidoBD.getPedItem()) {
						it.setItemQtde(pedidoBD.getPedItem().get(i).getItemQtde());
						it.setItemIdLivro(pedidoBD.getPedItem().get(i).getItemIdLivro());						
						estoque.setQtde(it.getItemQtde());
						estoque.setIdLivro(it.getItemIdLivro());							
						estoque.setTpMov(TipoMovimentacaoEstoque.ENTRADA);
						try {
						estoqueDAO.alterar(estoque);
						}catch(Exception e) {
							return "Erro ao devolver item para estoque";
						}
						i++;
					}
					return "CANCELOK";
				} else {
					return "O pedido não pode ser cancelado pois, passou os 7 dias de prazo do cancelamento ou ja foi entregue";
				}
			} else {
				return "Não é possível cancelar o pedido, pois ja está associoado a uma troca";
			}
			} else {
				return "Busca ao pedido retornou vazia";
			}
		} else {
			return null;
		}
		} else {
			return "Entidade não é pedido!";
		}
	
	}

}
