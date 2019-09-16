package br.com.syslib.core.impl.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.CartaoCreditoDAO;
import br.com.syslib.dominio.CartaoCredito;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Pedido;

public class ValidadorFrmPgto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Pedido) {
		 	Pedido pedido = (Pedido) entidade;
		 	CartaoCredito card = new CartaoCredito();
		 	CartaoCreditoDAO daoCard = new CartaoCreditoDAO();
		 	CartaoCredito cartao = new CartaoCredito();
		 	List<CartaoCredito> cards = pedido.getCartoes();
 			List<CartaoCredito> cts = new ArrayList<CartaoCredito>();

		 	if(pedido.getId() == null) {
		 	
		 	EntidadeDominio est = new CartaoCredito();
		 	if(pedido.getValorTotalPedido() < 0) {
		 		pedido.setValorTotalPedido((-1) * pedido.getValorTotalPedido());
		 	}
		 	
				/*
				 * if(pedido.getValorTroca() > 0 || pedido.getDescontoPedido() > 0) {
				 * pedido.setCuponsValor(pedido.getValorTroca() + pedido.getDescontoPedido());
				 * 
				 * if(pedido.getCuponsValor() > pedido.getValorTotalPedido()) {
				 * pedido.setCuponsValor(pedido.getCuponsValor() -
				 * pedido.getValorTotalPedido()); return null; } else {
				 * pedido.setCuponsValor(pedido.getValorTotalPedido() -
				 * pedido.getCuponsValor()); if(pedido.getCartoes().size() > 0 &&
				 * pedido.getCartoes().size() < 2) {
				 * 
				 * } }
				 * 
				 * }
				 */	 
		 	if(cards != null && pedido.getCuponsValor() == 0) {
		 			if(pedido.getCartoes().size() > 0 && pedido.getCartoes().size() < 2) {			 			
			 			card.setId(cards.get(0).getId());
			 			try {
			 			est = daoCard.getEntidadeDominioCartaoCredito(card.getId());
			 			}catch(SQLException e) {
			 				return "Erro ao buscar cartão";
			 			}
						if (!est.equals(null)) {
								cartao = (CartaoCredito) est;
								if(cartao.getAno() > 19 && cartao.getMes() > 9) {
									pedido.setIdClienteCartao1(cartao.getId());
									pedido.setValorCartao1(pedido.getValorTotalPedido());
									pedido.setCartao(cartao);									
									return "DRIVEOK";
								} else {
									return "Cartão final XXXX-" + cartao.getNumeroCartao().toString().substring(12, 16) + " vencido";
								}
						} else {
							return "erro";
						}
			 		} else if (pedido.getCartoes().size() > 1 && pedido.getCartoes().size() < 3) {
			 			
			 			for(CartaoCredito cartoes : cards) {
			 				try {
			 					est =  daoCard.getEntidadeDominioCartaoCredito(cartoes.getId());
			 				}catch(SQLException e){
			 					return "Erro ao buscar dados dos cartoes";
			 				}
			 				if (!est.equals(null)) {
								cartao = (CartaoCredito) est;
								if(cartao.getAno() > 19 && cartao.getMes() > 9) {
									if(pedido.getIdClienteCartao1() == 0) {
									pedido.setIdClienteCartao1(cartao.getId());
									pedido.setValorCartao1(pedido.getValorTotalPedido() - 10);
									cartao.setValorCartao(pedido.getValorTotalPedido() - 10);
									} else {
										pedido.setIdClienteCartao2(cartao.getId());
										pedido.setValorCartao2(10);
										cartao.setValorCartao(10);
									}
									
								} else {
									return "Cartão final XXXX-" + cartao.getNumeroCartao().toString().substring(12, 16) + " vencido";
								}
								cts.add(cartao);
									
								
						} else {
							return "erro";
						}
			 			}
						
						pedido.setCartoes(cts);
			 			return "DRIVEOK";
			 			
			 		}
			 		else {
			 			return "Não pode usar mais que 2 cartões por compra!";
			 		}
		 		} else if(cards != null && pedido.getCuponsValor() >= 0) {
		 			if(pedido.getCartoes().size() > 0 && pedido.getCartoes().size() < 2) {
		 				card.setId(cards.get(0).getId());
			 			try {
			 			est = daoCard.getEntidadeDominioCartaoCredito(card.getId());
			 			}catch(SQLException e) {
			 				return "Erro ao buscar cartão";
			 			}
			 			if (!est.equals(null)) {
							cartao = (CartaoCredito) est;
							if(cartao.getAno() > 19 && cartao.getMes() > 9) {
								pedido.setIdClienteCartao1(cartao.getId());
								pedido.setValorCartao1(pedido.getValorTotalPedido() - pedido.getDescontoPedido());
								pedido.setValorTotalPedido(pedido.getValorTotalPedido() - pedido.getDescontoPedido());
								pedido.setCartao(cartao);									
								return "DRIVEOK";
							} else {
								return "Cartão final XXXX-" + cartao.getNumeroCartao().toString().substring(12, 16) + " vencido";
							}
					} else {
						return "erro";
					}
		 			} else if (pedido.getCartoes().size() > 1 && pedido.getCartoes().size() < 3) {
			 			if((pedido.getValorTotalPedido()-pedido.getDescontoPedido()) >= 10) {
			 			for(CartaoCredito cartoes : cards) {
			 				try {
			 					est =  daoCard.getEntidadeDominioCartaoCredito(cartoes.getId());
			 				}catch(SQLException e){
			 					return "Erro ao buscar dados dos cartoes";
			 				}
			 				if (!est.equals(null)) {
								cartao = (CartaoCredito) est;
								if(cartao.getAno() > 19 && cartao.getMes() > 9) {
									if(pedido.getIdClienteCartao1() == 0) {
									pedido.setIdClienteCartao1(cartao.getId());
									pedido.setValorCartao1(pedido.getValorTotalPedido() - pedido.getDescontoPedido() - 5);
									cartao.setValorCartao(pedido.getValorTotalPedido() - pedido.getDescontoPedido() - 5);
									pedido.setValorTotalPedido(pedido.getValorTotalPedido() - pedido.getDescontoPedido());
									} else {
										pedido.setIdClienteCartao2(cartao.getId());
										pedido.setValorCartao2(5);
										cartao.setValorCartao(5);
									}
									
								} else {
									return "Cartão final XXXX-" + cartao.getNumeroCartao().toString().substring(12, 16) + " vencido";
								}
								cts.add(cartao);
									
								
						} else {
							return "erro";
						}
			 			}
						
						pedido.setCartoes(cts);
			 			return "DRIVEOK";
		 			} else {
		 				return "Apenas pode escolher um cartao quando o total da compra for zero ou o valor do cartao deve ser no minimo R$5,00";
		 			}
			 		}else {
		 				return "Mesmo a compra estando com valor zero é preciso selecionar pelo menos 1 cartao de credito";
		 			}
		 		}else {
		 			return "Você deve selecionar pelo menos 1 cartão para compra";
		 		}
		 		
		 		
		 	
		 	
		} else {
			return null;
		}
		 	
		} else {
			return "Entidade não é pedido";
		}
		
	}

}
