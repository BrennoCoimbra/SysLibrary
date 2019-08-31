package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.LivroDAO;
import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.Frete;
import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.Pedido;

public class PedidoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		
		Pedido pedido = new Pedido();
		ItemPedido itemPedido = new ItemPedido();
		Frete frete = new Frete();
		Cupom cupom = new Cupom();
		Livro livro = new Livro();
		Cliente cliente = new Cliente();
		
		if(operacao.equals("ADD")) {
			// Pegando o item e quantidade unica pelo parametro passado ao clicar em add ao carrinho
			String idItem = request.getParameter("idItemPedido");
			String qtdeiTem = request.getParameter("qtdeItem");
			
			itemPedido.setItemIdLivro(Integer.parseInt(idItem));
			itemPedido.setItemQtde(Integer.parseInt(qtdeiTem));
			
			pedido.getPedItem().add(itemPedido);
			pedido.setQtdeCar(-1);
			
		} else if(operacao.equals("REMOVE") || operacao.equals("EXCLUIR")) {
			// Pegando o item e quantidade unica pelo parametro passado ao clicar em remover do carrinho
			String idItem = request.getParameter("pedItemId");
			String qtdeiTem = request.getParameter("qtdeItem");
			
			itemPedido.setItemIdLivro(Integer.parseInt(idItem));
			itemPedido.setItemQtde(Integer.parseInt(qtdeiTem));
			
			pedido.getPedItem().add(itemPedido);
			pedido.setQtdeCar(1);
		}
		return pedido;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		RequestDispatcher d = null;
        List<EntidadeDominio> entidade = (List<EntidadeDominio>) resultado.getEntidades();
        String operacao = request.getParameter("operacao");
        
        
        if(operacao.equals("ADD")) {
        	if(resultado.getMsg().equals("ADDOK")){
        		
        		
        		Pedido pedidoBD = (Pedido) entidade.get(0);
        		Livro livro = pedidoBD.getPedLivro().get(0);
        		Estoque estoque = pedidoBD.getPedEstoque();
        		ItemPedido itemPedido = new ItemPedido();
        		// Attributes of session
                HttpSession session = request.getSession();                
                String idItem = request.getParameter("idItemPedido");
    			String qtdeiTem = request.getParameter("qtdeItem");		
    			Pedido pedido = session.getAttribute("pedido") == null ? new Pedido() : (Pedido) session.getAttribute("pedido");
                ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("livros");
    			
                
    			itemPedido.setItemIdLivro(Integer.parseInt(idItem));
    			itemPedido.setItemQtde(Integer.parseInt(qtdeiTem));
    			
    			boolean pedTem = false;
        		
    			if(pedido.getPedItem().size() > 0 ) {
    				for(ItemPedido item : pedido.getPedItem()) {
    					if(estoque.getIdLivro() == item.getItemIdLivro()) {
    						 item.setItemQtde(item.getItemQtde() + 1);
                             item.setItemSubTotal(item.getItemQtde() * livro.getValorVenda());
                             pedido.setValorTotalPedido(pedido.getValorTotalPedido() + livro.getValorVenda());
                             pedido.setSubtotalPedido(pedido.getSubtotalPedido() + livro.getValorVenda());
                             pedido.setQtdeItemPedido(pedido.getQtdeItemPedido() + 1);
                             session.setAttribute("pedido", pedido);
                             session.setAttribute("livros", livros);
                             response.sendRedirect("carrinho.jsp");
                             pedTem = true;
                             return;
                             
    					}
    					
    				}
    			} 
    			
    			if(!pedTem){    				
    				itemPedido.setItemSubTotal(itemPedido.getItemQtde() * livro.getValorVenda());
    				pedido.setValorTotalPedido(pedido.getValorTotalPedido() + livro.getValorVenda());
                    pedido.setSubtotalPedido(pedido.getSubtotalPedido() + livro.getValorVenda());
                    itemPedido.setItemIdLivro(estoque.getIdLivro());
                    itemPedido.setItemTitLivro(livro.getTitulo());
                    itemPedido.setItemValorUnit(livro.getValorVenda());
                    pedido.setQtdeItemPedido(pedido.getQtdeItemPedido() + 1);
                    pedido.getPedItem().add(itemPedido);
                }
                session.setAttribute("pedido", pedido);
                session.setAttribute("livros", livros);
                response.sendRedirect("carrinho.jsp");
                return;
    			
        	}
        	 request.setAttribute("resultado", resultado);
             d = request.getRequestDispatcher("/carrinho.jsp");
             d.forward(request, response);
             
        } else {
            d = request.getRequestDispatcher("/index.jsp");
            d.forward(request, response);
        }

	}

}
