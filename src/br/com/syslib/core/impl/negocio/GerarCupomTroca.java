package br.com.syslib.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.PedidoDAO;
import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Pedido;

public class GerarCupomTroca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		String numero = null;
        Cupom cupom = (Cupom) entidade;
        Pedido pedidoBD;
        PedidoDAO daoPed = new PedidoDAO();
        
        if(cupom.getQtdeTroca() != 0 && cupom.getQtdeTroca() > 0) {
            try {
                EntidadeDominio ent = new EntidadeDominio();
                ent = daoPed.getEntidadeDoiminio(cupom.getIdPedido());
                if(ent != null) {
                pedidoBD = (Pedido) ent;
                for(ItemPedido it : pedidoBD.getPedItem()) {
                	if(cupom.getIdItem() == it.getItemIdLivro()) {
                		
                		if(cupom.getQtdeTroca() <= it.getItemQtde()) {
                			try {                     
                                numero = String.valueOf(cupom.getIdPedido()) + String.valueOf(cupom.getIdItem());
                                cupom.setNomeCupom(String.valueOf(numero));
                                cupom.setValorCupom(it.getItemValorUnit() * cupom.getQtdeTroca());
                                List<ItemPedido> item =  new ArrayList<ItemPedido>();
                                item.add(it);
                                cupom.setItemPedido(item);
                                cupom.setStatusCupom("EM TROCA");
                                //cupom.setStatusPedido("EM TROCA");               
                                return null;
                        } catch (Exception ex) {
                            return "Erro ao criar cupom";
                        }
                	} else  {
                		return "A quantidade p/ troca tem que ser menor ou igual a compra do item";
                	}
                  } 
                }
               }
            }catch(Exception e){
            	return "Erro ao encontrar o pedido";
            }
        
        
        } else {
        	return "A quantidade p/ troca tem que ser menor ou igual a compra do item";
        }
        return null;
    }
	
}
