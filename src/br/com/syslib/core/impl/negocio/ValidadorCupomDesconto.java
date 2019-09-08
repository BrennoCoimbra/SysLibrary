package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Pedido;
import br.com.syslib.enuns.CupomPromocional;

public class ValidadorCupomDesconto implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Pedido pedido = (Pedido) entidade;
		String cupom = pedido.getCodigoPromocionalPedido();
		String cpm = null;
		int cpm2 = 0;
		
		if(!cupom.equals("")) {
		
		if(pedido.getIdPedido() == 0 &&  cupom != null) {
			 for (CupomPromocional cp : CupomPromocional.values()) {
				 if(cupom.equals(cp.getDescricao())) {
				    cpm = cp.getDescricao();
				    cpm2 = cp.getPorcDesc();
				 } 				 
			 }										
			 
			 if(cpm != null && cpm2 != 0) {
						if(pedido.getDescontoPedido() == 0) {
							pedido.setDescontoPedido(pedido.getSubtotalPedido() * cpm2 / 100);
							pedido.setSubtotalPedido(pedido.getSubtotalPedido() - pedido.getDescontoPedido());
							pedido.setValorTotalPedido(pedido.getValorTotalPedido() - pedido.getDescontoPedido());
							return "DRIVEOK";
						} else {
							return "Ja possui cupom de desconto para compra! Invalido!";
						}
					
			 } else {
				 return "Não existe este cupom!";
			 }
				
	            
		} else {
			return "Digite um codigo de cupom!";
		}
		
		} else {
			return "Digite um codigo de cupom!";
		}
	}

}
