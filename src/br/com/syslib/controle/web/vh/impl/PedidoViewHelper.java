package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.Pedido;

public class PedidoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		
		Pedido pedido = new Pedido();
		ItemPedido itemPedido = new ItemPedido();
		
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
			String idItem = request.getParameter("idItemPedido");
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
        HttpSession session = request.getSession(true);
        int cont = 0;
        String operacao = request.getParameter("operacao");
        Pedido pedido = session.getAttribute("pedido") == null ? new Pedido() : (Pedido) session.getAttribute("pedido");
        @SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("livros");
        
        if(operacao.equals("EXCLUIR")) {
        	if(resultado.getMsg().equals("DRIVEOK")){
        		Pedido pedidoBD = (Pedido) entidade.get(0);
        		if(pedido.getPedItem().size() > 0 ) {
        			for(ItemPedido item : pedido.getPedItem()) {
        				
        				if (pedidoBD.getPedItem().get(0).getItemIdLivro() == item.getItemIdLivro()) {
        					pedido.setValorTotalPedido(pedido.getValorTotalPedido() - item.getItemSubTotal());
        					pedido.setSubtotalPedido(pedido.getSubtotalPedido() - item.getItemSubTotal());
        					pedido.setQtdeItemPedido(pedido.getQtdeItemPedido() - pedido.getPedItem().get(cont).getItemQtde());
        					pedido.getPedItem().remove(item);

                            break;
                        }
                        cont++;
                    }

                }
                session.setAttribute("entidadePedido", pedido);
                session.setAttribute("entidadeLivro", livros);
                response.sendRedirect("carrinho.jsp");
            } else {
                request.setAttribute("resultado", resultado);
                d = request.getRequestDispatcher("/carrinho.jsp");
                d.forward(request, response);
            }           
        	
        // verifica se a operacao é do botao de + ou - do carrinho de compras
        } else if(operacao.equals("ADD") || operacao.equals("REMOVE")) {
        	// verifica se obteve sucesso na validacao qtde do carrinho
        	if(resultado.getMsg().equals("DRIVEOK")){
        		Pedido pedidoBD = (Pedido) entidade.get(0);
        		Livro livro = pedidoBD.getPedLivro().get(0);
        		Estoque estoque = pedidoBD.getPedEstoque();
        		ItemPedido itemPedido = new ItemPedido();          
                String idItem = request.getParameter("idItemPedido");
    			String qtdeiTem = request.getParameter("qtdeItem");		
    			
    			itemPedido.setItemIdLivro(Integer.parseInt(idItem));
    			itemPedido.setItemQtde(Integer.parseInt(qtdeiTem));
    			
    			boolean pedTem = false;
    			
        		// verifica se no carrinho de compras ja tem itens
    			if(pedido.getPedItem().size() > 0 ) {
    				for(ItemPedido item : pedido.getPedItem()) {
    					if(estoque.getIdLivro() == item.getItemIdLivro()) {
    						
    					// soma o item existente do carrinho de compras
    						if(operacao.equals("ADD")) {
    						 item.setItemQtde(item.getItemQtde() + 1);
                             item.setItemSubTotal(item.getItemQtde() * livro.getValorVenda());
                             pedido.setValorTotalPedido(pedido.getValorTotalPedido() + livro.getValorVenda());
                             pedido.setSubtotalPedido(pedido.getSubtotalPedido() + livro.getValorVenda());
                             pedido.setQtdeItemPedido(pedido.getQtdeItemPedido() + 1);
                             session.setAttribute("pedido", pedido);
                             session.setAttribute("livros", livros);
                             response.sendRedirect("carrinho.jsp");
                             return;
                             
                          // subtrai o item existente do carrinho de compras     
    					} else if (operacao.equals("REMOVE")) {
    						item.setItemQtde(item.getItemQtde() - 1);
                            item.setItemSubTotal(item.getItemQtde() * livro.getValorVenda());
                            pedido.setValorTotalPedido(pedido.getValorTotalPedido() - livro.getValorVenda());
                            pedido.setSubtotalPedido(pedido.getSubtotalPedido() - livro.getValorVenda());
                            pedido.setQtdeItemPedido(pedido.getQtdeItemPedido() - 1);
                            session.setAttribute("pedido", pedido);
                            session.setAttribute("livros", livros);
                            response.sendRedirect("carrinho.jsp");
                            return;
    					
    					} else {
    						item.setItemQtde(item.getItemQtde() + pedido.getQtdeItemPedido());                           
                            item.setItemSubTotal(item.getItemQtde() * livro.getValorVenda());
                            pedido.setSubtotalPedido(pedido.getSubtotalPedido() + livro.getValorVenda());
                            pedido.setValorTotalPedido(pedido.getValorTotalPedido() + livro.getValorVenda());
                            pedido.setQtdeItemPedido(pedido.getQtdeItemPedido() + 1);
    					}
    						
    					 pedTem = true;	
    					 
    					}
    				}
    			} 
    			
    			// adiciona outro item ao carrinho de compras
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
        	else {
    			request.setAttribute("msg", resultado.getMsg());
    			d = request.getRequestDispatcher("errors.jsp");
    			d.forward(request, response);
    		}
        	
        } else {
                d = request.getRequestDispatcher("index.jsp");
                d.forward(request, response);
        }
		
       
			
		}
	}
	


