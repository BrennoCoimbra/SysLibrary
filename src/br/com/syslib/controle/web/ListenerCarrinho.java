package br.com.syslib.controle.web;

import java.io.IOException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import br.com.syslib.core.impl.dao.EstoqueDAO;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Pedido;
import br.com.syslib.enuns.TipoMovimentacaoEstoque;

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
    	
    	
    	//Disponibilizar itens no estoque caso a sessao do usuario tenha expirado no carrinho de compras.
    	if(session.getAttribute("pedido") != null) {
    		Pedido pedido = (Pedido) session.getAttribute("pedido") ; 
    		Estoque estoque = new Estoque();
			EstoqueDAO estoqueDAO = new EstoqueDAO();
						
			
    		int i = 0;
    		for(ItemPedido it : pedido.getPedItem()) {
				it.setItemQtde(pedido.getPedItem().get(i).getItemQtde());
				it.setItemIdLivro(pedido.getPedItem().get(i).getItemIdLivro());						
				estoque.setQtde(it.getItemQtde());
				estoque.setIdLivro(it.getItemIdLivro());							
				estoque.setTpMov(TipoMovimentacaoEstoque.ENTRADA);
				try {
				estoqueDAO.alterar(estoque);
				}catch(Exception e) {
					return;
				}
				i++;
			}
    		
    		session.removeAttribute("pedido");
    		session.setAttribute("pedido", pedido);
    		
    	}
    	
    }
	
}
