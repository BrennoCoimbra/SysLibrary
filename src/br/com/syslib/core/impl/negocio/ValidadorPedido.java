package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Pedido;

public class ValidadorPedido implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Pedido) {
		 	Pedido pedido = (Pedido) entidade;
 			
 		
 			
 			if(pedido.getValorTotalPedido() > 0) {
 				if(pedido.getIdClienteCartao1() > 0 && pedido.getIdClienteCartao2() == 0) {
		 			return null;
				
		 		} else if(pedido.getIdClienteCartao1() > 0 && pedido.getValorCartao2() > 0 && pedido.getDescontoPedido() == 0) {
		 			
						if(pedido.getValorCartao1() == 10 || pedido.getValorCartao2() == 10) {	
							if(pedido.getValorCartao1() == 10 && pedido.getValorCartao2() == (pedido.getValorTotalPedido() - 10)){
								return null;
								
							} else if  (pedido.getValorCartao2() == 10 && pedido.getValorCartao1() == (pedido.getValorTotalPedido() - 10)){
							return null;	
							} else {
								return "Valor do cartao 1 ou 2 está incorreto";
							}
							
						}else if (pedido.getValorCartao1() == 10 || pedido.getValorCartao2() == 10 && pedido.getValorCartao1() + pedido.getValorCartao2() == pedido.getValorTotalPedido()) {
							return null;
							
						}else if (pedido.getValorCartao1() + pedido.getValorCartao2() == pedido.getValorTotalPedido()) {
									if(pedido.getValorCartao1() >= 10 || pedido.getValorCartao2() >= 10) {
										return null;
									} else {
										return "Pagamento minimo para 1 cartao deve ser de R$10,00";
									}
							
						}else {
							return "A soma dos valores a ser pago deve ser igual ao valor total do Pedido";
						}
					}else if(pedido.getIdClienteCartao1() > 0 && pedido.getValorCartao2() > 0 && pedido.getDescontoPedido() > 0) {
			 			
							if(pedido.getValorCartao1() == 5 || pedido.getValorCartao2() == 5) {	
								if(pedido.getValorCartao1() == 5 && pedido.getValorCartao2() == (pedido.getValorTotalPedido() - 5)){
									return null;
									
								} else if  (pedido.getValorCartao2() == 5 && pedido.getValorCartao1() == (pedido.getValorTotalPedido() - 5)){
								return null;	
								} else {
									return "Valor do cartao 1 ou 2 está incorreto";
								}
								
							}else if (pedido.getValorCartao1() == 5 || pedido.getValorCartao2() == 5 && pedido.getValorCartao1() + pedido.getValorCartao2() == pedido.getValorTotalPedido()) {
								return null;
								
							}else if (pedido.getValorCartao1() + pedido.getValorCartao2() == pedido.getValorTotalPedido()) {
										if(pedido.getValorCartao1() >= 5 || pedido.getValorCartao2() >= 5) {
											return null;
										} else {
											return "Pagamento minimo para 1 cartao deve ser de R$5,00";
										}
								
							}else {
								return "A soma dos valores a ser pago deve ser igual ao valor total do Pedido";
							}
						} else {
						return "Soma dos valores a ser pago deve ser igual ao valor total o pedido";
					}
						
 				} else if(pedido.getValorTotalPedido() == 0) {
 					return null;
 				} else {
 				return "erro , valor do pedido nao pode ser menor que zero";
 			}

		} else {
			return "Entidade não é pedido";
		}
		
	}

}
