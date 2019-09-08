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
import br.com.syslib.dominio.CartaoCredito;
import br.com.syslib.dominio.Cupom;
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
		} else if(operacao.equals("CUPOMDESCONTO")) { 
			HttpSession session = request.getSession(true);
            Pedido pedi = (Pedido) session.getAttribute("pedido");
            String codigoDesconto = request.getParameter("cupomDesc");
           
          
                      
            if (pedi.getDescontoPedido() > 0) {
                pedido.setDescontoPedido(pedi.getDescontoPedido());
            } else {
            	pedido.setDescontoPedido(0);            	
            }
            pedido.setValorTotalPedido(pedi.getValorTotalPedido() - pedi.getDescontoPedido());
            pedido.setSubtotalPedido(pedi.getSubtotalPedido());
            pedido.setCodigoPromocionalPedido(codigoDesconto);
            return pedido;
            
		}  else if(operacao.equals("FRMPGTO")){
			HttpSession session = request.getSession(true);
            Pedido pedi = (Pedido) session.getAttribute("pedido");
            Cupom cp =  session.getAttribute("cupom") == null ? new Cupom() : (Cupom) session.getAttribute("cupom");
            String[] idsCartoes = request.getParameterValues("chkSelect");
            
           List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
           CartaoCredito card;
           if(idsCartoes != null && !idsCartoes.equals("") ) {
        	   for(String id : idsCartoes) {
        		   card = new CartaoCredito();
        		   card.setId(Integer.parseInt(id));
        		   cartoes.add(card);
        	   }
        	   pedido.setCartoes(cartoes);
           }
           pedido.setValorTotalPedido(pedi.getValorTotalPedido() - pedi.getDescontoPedido());
           pedido.setSubtotalPedido(pedi.getSubtotalPedido());                     
           pedido.setValorFrete(pedi.getValorFrete());
           if(cp.equals(null)) {
        	   cp.setValorCupom(0);
        	   
           }
           pedido.setCuponsValor(pedi.getDescontoPedido() + cp.getValorCupom());
           
            return pedido;
            
		} else if(operacao.equals("SALVAR")){
			HttpSession session = request.getSession(true);
            pedido = (Pedido) session.getAttribute("pedido");
            pedido.setStatusPedido("EM PROCESSAMENTO");
 
            return pedido;
            
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
		CartaoCredito cartao = session.getAttribute("cartao") == null ? new CartaoCredito() : (CartaoCredito) session.getAttribute("cartao");

        @SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("livros");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<EntidadeDominio> cartoes = session.getAttribute("cartoes") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("cartoes");
        
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
        	
        } else if ("CUPOMDESCONTO".equals(operacao)) {


            if (!"DRIVEOK".equals(resultado.getMsg())) {
                request.setAttribute("msg", resultado.getMsg());
                d = request.getRequestDispatcher("errors.jsp");
                d.forward(request, response);
            } else {
                Pedido ped = (Pedido) entidade.get(0);
                pedido.setDescontoPedido(ped.getDescontoPedido());
                pedido.setSubtotalPedido(ped.getSubtotalPedido());
                pedido.setValorTotalPedido(ped.getValorTotalPedido());
                pedido.setCodigoPromocionalPedido(ped.getCodigoPromocionalPedido());          
                session.setAttribute("pedido", pedido);
                response.sendRedirect("carrinho.jsp");
            }
        }  else if ("FRMPGTO".equals(operacao)) {


            if (!"DRIVEOK".equals(resultado.getMsg())) {
                request.setAttribute("msg", resultado.getMsg());
                d = request.getRequestDispatcher("errors.jsp");
                d.forward(request, response);
            } else {
                Pedido ped = (Pedido) entidade.get(0);
             // somente se escolheu a forma de pgto com um cartao
                if(ped.getIdClienteCartao1() > 0 && ped.getIdClienteCartao2() == 0) {
                pedido.setDescontoPedido(ped.getDescontoPedido());
                pedido.setSubtotalPedido(ped.getSubtotalPedido());
                pedido.setValorTotalPedido(ped.getValorTotalPedido());
                pedido.setCodigoPromocionalPedido(ped.getCodigoPromocionalPedido());
                pedido.setValorCartao1(ped.getValorCartao1());
                pedido.setIdClienteCartao1(ped.getIdClienteCartao1());
                cartao.setDescricao(ped.getCartao().getDescricao());
                cartao.setNumeroCartao(ped.getCartao().getNumeroCartao());
                cartao.setId(ped.getCartao().getId());
                session.setAttribute("pedido", pedido);
                session.setAttribute("cartao", cartao);
                response.sendRedirect("form-pedido-fim.jsp");
                
                // somente se escolheu a forma de pgto com dois cartoes
                } else if(ped.getIdClienteCartao1() > 0 && ped.getIdClienteCartao2() > 0) {
                	
                         pedido.setDescontoPedido(ped.getDescontoPedido());
                         pedido.setSubtotalPedido(ped.getSubtotalPedido());
                         pedido.setValorTotalPedido(ped.getValorTotalPedido());
                         pedido.setCodigoPromocionalPedido(ped.getCodigoPromocionalPedido());
                         pedido.setValorCartao1(ped.getValorCartao1());
                         pedido.setValorCartao2(ped.getValorCartao2());
                         pedido.setIdClienteCartao1(ped.getIdClienteCartao1());
                         pedido.setIdClienteCartao2(ped.getIdClienteCartao2());                         
                         pedido.setCartoes(ped.getCartoes());
                         cartoes.addAll(ped.getCartoes());                         
                         session.setAttribute("pedido", pedido);
                         session.setAttribute("cartoes", cartoes);
                         response.sendRedirect("form-pedido-pagamento.jsp");
                }
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
	


