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
import br.com.syslib.core.impl.dao.CupomDAO;
import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Pedido;
import br.com.syslib.dominio.Usuario;
import br.com.syslib.enuns.TipoUsuario;

public class CupomViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Cupom cupom = new Cupom();

		if (operacao.equals("CONSULTAR")) {
			HttpSession session = request.getSession(true);
            Pedido ped = (Pedido) session.getAttribute("pedido");
			String nomeCupom = request.getParameter("idCupom");
			String idUsu = request.getParameter("idUsu");
			try {
				cupom.setValorPedido(ped.getValorTotalPedido());
				cupom.setDescontoPedido(ped.getDescontoPedido());
				cupom.setValorCupom(ped.getValorTroca());
				cupom.setIdCupomCliente(ped.getIdClientePedido());
				cupom.setNomeCupom(nomeCupom);
				cupom.setSubtotal(ped.getSubtotalPedido());
				cupom.setIdCupomCliente(Integer.parseInt(idUsu));
			} catch (Exception e) {
				cupom.setDescontoPedido(0);
				cupom.setDescontoPedido(0);
				cupom.setValorCupom(0);
				cupom.setIdCupomCliente(0);
				cupom.setNomeCupom("");
			}
			
            return cupom;


		} else if (operacao.equals("SALVAR")) {
			String idUsu = request.getParameter("idUsuario");
			String idPed = request.getParameter("idPedido");
			String idItem = request.getParameter("idTem");
			String qtdeTroca = request.getParameter("qtdeTroca");
			
			cupom.setIdPedido(Integer.parseInt(idPed));
			cupom.setIdItem(Integer.parseInt(idItem));
			cupom.setIdCupomCliente(Integer.parseInt(idUsu));
			
			if(!qtdeTroca.equals("")) {
				cupom.setQtdeTroca(Integer.parseInt(qtdeTroca));			
			} else {
				cupom.setQtdeTroca(0);
				
			}
				
			
			
			return cupom;
			
		}else if (operacao.equals("ALTERAR")) {
			String idCupom = request.getParameter("IdCupom");
			String idPed = request.getParameter("IdPed");
			String idItem = request.getParameter("IdLivro");
			String opcao = request.getParameter("Option");
			
			cupom.setIdPedido(Integer.parseInt(idPed));
			cupom.setIdItem(Integer.parseInt(idItem));
			cupom.setId(Integer.parseInt(idCupom));
			cupom.setIdCupom(Integer.parseInt(idCupom));
			
			if(opcao.equals("S")) {
				cupom.setStatusCupom("TROCA AUTORIZADA");
			} else {
				cupom.setStatusCupom("TROCA NÃO AUTORIZADA");
			}
			
			
			return cupom;
					
		
	}else if (operacao.equals("ADD")) {
		String idCupom = request.getParameter("IdCupom");
		String idPed = request.getParameter("IdPed");
		String idItem = request.getParameter("IdLivro");
		String opcao = request.getParameter("Option");
		String qtde = request.getParameter("qtdeItem");
		
		cupom.setIdPedido(Integer.parseInt(idPed));
		cupom.setIdItem(Integer.parseInt(idItem));
		cupom.setId(Integer.parseInt(idCupom));
		cupom.setIdCupom(Integer.parseInt(idCupom));
		cupom.setQtdeTroca(Integer.parseInt(qtde));
		if(opcao.equals("S")) {
			cupom.setEnviarEstoque(true);
		} else {
			cupom.setEnviarEstoque(false);
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
    		EntidadeDominio usu = (EntidadeDominio) session.getAttribute("usuario");

	        
	       if(operacao.equals("CONSULTAR")) {
	    	   if(resultado.getMsg() != null && !"DRIVEOK".equals(resultado.getMsg())) {
	    		   resultado.setEntidades(null);
	                request.setAttribute("resultado", resultado);  
	                request.setAttribute("msg", resultado.getMsg());
	    			d = request.getRequestDispatcher("errors.jsp");

	                 
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
	    	   } else if(operacao.equals("SALVAR")) {
	    		   if(resultado.getMsg() == null) {
	    		   d = request.getRequestDispatcher("consultar-trocas.jsp");
	    		   } else {
	    			   request.setAttribute("msg", resultado.getMsg());	    				
	    				d = request.getRequestDispatcher("errors.jsp");
	    		   }
	    	   }else if (operacao.equals("VISUALIZAR") || operacao.equals("ALTERAR") || operacao.equals("ADD")) {
	           	Usuario user = (Usuario) usu;

	        	if(user.getTipoUsuario().equals(TipoUsuario.ADMIN)) {
	        		try {				
	    				String filter = request.getParameter("search");    			
	    				if(filter == null) {
	    					filter = "";
	    				}
	    				List<EntidadeDominio> cupons = new CupomDAO().visualizar(filter);
	    				request.setAttribute("cupons", cupons);
	    				
	    			} catch (SQLException e) {
	    				resultado.setMsg("Não foi possível listar cupons.");
	    				d = request.getRequestDispatcher("errors.jsp");
	        			d.forward(request, response);
	    			}

	    			d = request.getRequestDispatcher("./trocas.jsp");
	        	} else {
				try {				
					String filter = request.getParameter("search");
					Integer idCliente = usu.getId();
					List<EntidadeDominio> cupons = new CupomDAO().visualizar(filter,idCliente);
					request.setAttribute("cupons", cupons);
					
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar pedidos.");
					d = request.getRequestDispatcher("errors.jsp");
				}

				d = request.getRequestDispatcher("consultar-trocas.jsp");
	        	}
				
			} else if (operacao.equals("BUSCAR")) {
	           	Usuario user = (Usuario) usu;

	        	if(user.getTipoUsuario().equals(TipoUsuario.ADMIN)) {
	        		try {				
	    				String filter = request.getParameter("search");    			
	    				if(filter == null) {
	    					filter = "";
	    				}
	    				List<EntidadeDominio> cupons = new CupomDAO().visualizar(filter);
	    				request.setAttribute("cupons", cupons);
	    				
	    			} catch (SQLException e) {
	    				resultado.setMsg("Não foi possível listar cupons.");
	    				d = request.getRequestDispatcher("errors.jsp");
	        			d.forward(request, response);
	    			}

	    			d = request.getRequestDispatcher("./trocas.jsp");
	        	} else {
				try {				
					String filter = request.getParameter("search");
					Integer idCliente = usu.getId();
					List<EntidadeDominio> cupons = new CupomDAO().visualizar(filter,idCliente);
					request.setAttribute("cupons", cupons);
					
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar pedidos.");
					d = request.getRequestDispatcher("errors.jsp");
				}

				d = request.getRequestDispatcher("consultar-cupons.jsp");
	        	}
				
			} 
	       if (d != null)
				d.forward(request, response);
	       }

	}


