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
import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Pedido;

public class CupomViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cupom cupom = new Cupom();

		if (operacao.equals("CONSULTAR")) {
			HttpSession session = request.getSession(true);
            Pedido ped = (Pedido) session.getAttribute("entidadePedido");
			String nomeCupom = request.getParameter("idCupom");
			
			try {
				cupom.setValorPedido(ped.getValorTotalPedido());
				cupom.setDescontoPedido(ped.getDescontoPedido());
				cupom.setValorCupom(ped.getValorTroca());
				cupom.setIdCupomCliente(ped.getIdClientePedido());
				cupom.setNomeCupom(nomeCupom);
				cupom.setSubtotal(ped.getSubtotalPedido());
			} catch (Exception e) {
				cupom.setDescontoPedido(0);
				cupom.setDescontoPedido(0);
				cupom.setValorCupom(0);
				cupom.setIdCupomCliente(0);
				cupom.setNomeCupom("");
			}
			
            return cupom;


		}

		return cupom;
	}


	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		 RequestDispatcher d = null;
	        List<EntidadeDominio> entidade = (List<EntidadeDominio>) resultado.getEntidades();
	        String operacao = request.getParameter("operacao");
	        HttpSession session = request.getSession();
            @SuppressWarnings({ "unchecked", "rawtypes" })
			ArrayList<EntidadeDominio> pedidos = session.getAttribute("pedidos") == null ? null : (ArrayList) session.getAttribute("pedidos");            
            Pedido pedido = session.getAttribute("pedido") == null ? new Pedido() : (Pedido) session.getAttribute("pedido");
            
	        
	       if(operacao.equals("CONSULTAR")) {
	    	   if(resultado.getMsg() != null && !"DRIVEOK".equals(resultado.getMsg())) {
	    		   resultado.setEntidades(null);
	                request.setAttribute("resultado", resultado);  
	                request.setAttribute("msg", resultado.getMsg());
	    			d = request.getRequestDispatcher("errors.jsp");
	    			d.forward(request, response);
	                 
	    	   } else {
	    		   Cupom cupom = (Cupom) entidade.get(0);               
	                pedido.setValorTroca(cupom.getValorCupom());
	                pedido.setValorTotalPedido(cupom.getValorPedido());
	                pedido.setSubtotalPedido(cupom.getSubtotal());
	                pedido.setDescontoPedido(cupom.getDescontoPedido());                
	                if (pedidos != null) {
	                    for (EntidadeDominio e : pedidos) {
	                        Pedido p = (Pedido) e;
	                        if (p.getCupom() != null) {
	                            for (Cupom c : p.getCupom()) {
	                                if (cupom.getIdCupom() == c.getIdCupom()) {
	                                    c.setValorCupom(cupom.getValorCupom());
	                                    c.setStatusCupom(cupom.getStatusCupom());
	                                }                                
	                            }
	                        }                        
	                    }
	                }
	                
	                session.setAttribute("pedido", pedido);
	                session.setAttribute("cupom", cupom);
	                response.sendRedirect(response.encodeRedirectURL("form-pedido-cartao.jsp"));                
	            }
	    	   }
	       }

	}


