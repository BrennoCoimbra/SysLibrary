package br.com.syslib.controle.web;

import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Pedido;

/**
 * Application Lifecycle Listener implementation class Carrinho
 *
 */
@WebListener
public class ListenerCarrinho implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public ListenerCarrinho() {
       
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	
		HttpSession session = se.getSession();
        
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	HttpSession session = se.getSession();
    	String operacao = "EXCLUIR";
    	
    	//Disponibilizar itens no estoque caso a sessao do usuario tenha expirado no carrinho de compras.
    	if(session.getAttribute("pedido") != null) {
    		Pedido pedido = (Pedido) session.getAttribute("pedido") ; 
    		if(pedido.getPedItem().size() > 0 ) {
    			for(ItemPedido item : pedido.getPedItem()) {
    				
    			}
    		}
    	}
    	
    }
	
}
