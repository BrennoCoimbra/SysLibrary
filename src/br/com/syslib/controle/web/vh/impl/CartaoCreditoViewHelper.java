package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.CartaoCreditoDAO;
import br.com.syslib.dominio.CartaoCredito;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.enuns.BandeiraCartao;

public class CartaoCreditoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		CartaoCredito cartaoCredito = null; 
		String idCartaoCredito;
		
		if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
			String idUsu = request.getParameter("idUsuario");
			String id = request.getParameter("cartao_id");
			String descricao = request.getParameter("descricao");
			String nomeCartao = request.getParameter("nomeCartao");
			String numeroCartao = request.getParameter("numero");
			String mes = request.getParameter("mes");
			String ano = request.getParameter("ano");
			String cvv = request.getParameter("cvv");
			String bandeira = request.getParameter("bandeira");
			String preferencial = request.getParameter("pref");
			
			if (mes == null || mes.trim().equals(""))
				mes = "0";
			if (ano == null || ano.trim().equals(""))
				ano = "0";
			if (cvv == null || cvv.trim().equals(""))
				cvv = "0";
						
			cartaoCredito = new CartaoCredito();
			cartaoCredito.setPreferencial(preferencial.equals("1") ? true : false);
			cartaoCredito.setIdUsuario(Integer.parseInt(idUsu));
			cartaoCredito.setDescricao(descricao);			
			cartaoCredito.setNomeCartao(nomeCartao);
			cartaoCredito.setNumeroCartao(numeroCartao);
			cartaoCredito.setMes(Integer.parseInt(mes));
			cartaoCredito.setAno(Integer.parseInt(ano));
			cartaoCredito.setCodigoSeguranca(Integer.parseInt(cvv));
			cartaoCredito.setDtCadastro(new Date());
			
			for (BandeiraCartao band : BandeiraCartao.values()) {
				if (bandeira != null && Integer.parseInt(bandeira) == band.getCodigo()) {
					cartaoCredito.setBandeiraCartao(band);
				}
			}
			
			if (id != null && !id.trim().equals("")) {
				cartaoCredito.setId(Integer.parseInt(id));
			}
		} else if(operacao.equals("EXCLUIR") || operacao.equals("CONSULTAR")) {
			idCartaoCredito = request.getParameter("cartao_id");
			int id = 0;
			
			if(idCartaoCredito != null && !idCartaoCredito.trim().equals("")) {
				id = Integer.parseInt(idCartaoCredito);
				
				cartaoCredito = new CartaoCredito();
				cartaoCredito.setId(id);
			}
		} else {
			Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
			idCartaoCredito = request.getParameter("cartao_id");
			int id = 0;
			
			if(idCartaoCredito != null && !idCartaoCredito.trim().equals("")) {
				id = Integer.parseInt(idCartaoCredito);
			}
			
			if (resultado != null) {
				for(EntidadeDominio e : resultado.getEntidades()) {
					if(e != null && e.getId() != null && id > 0 && e.getId() == id){
						cartaoCredito = (CartaoCredito) e;
					}
				}
			}
		}
		return cartaoCredito;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		EntidadeDominio usu = (EntidadeDominio) session.getAttribute("usuario");
		
		if(resultado.getMsg() == null) {
			request.getSession().setAttribute("resultado", resultado);
			
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("form-cartao.jsp");
			} else if (operacao.equals("VISUALIZAR")) {
				try {
					String filter = request.getParameter("search");
					Integer idCliente = usu.getId();
					List<EntidadeDominio> cartoes = new CartaoCreditoDAO().visualizar(filter,idCliente);
					request.setAttribute("cartoes", cartoes);
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar cartões.");
				}
				
				d = request.getRequestDispatcher("consultar-cartao.jsp");
				
			} else if (operacao.equals("CONSULTAR")) {
				try {
					int idCartaoCredito = Integer.parseInt(request.getParameter("cartao_id"));
					CartaoCredito cartaoCredito = (CartaoCredito) new CartaoCreditoDAO().getEntidadeDominioCartaoCredito(idCartaoCredito);
					request.setAttribute("cartoes", cartaoCredito);
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar cartão.");
				}
				
				d = request.getRequestDispatcher("form-cartao.jsp");
			} else if (operacao.equals("EXCLUIR")) {
				d = request.getRequestDispatcher("consultar-cartao.jsp");				
			} else if (operacao.equals("SAIR")){
	            request.getSession().invalidate();
	            d = request.getRequestDispatcher("index.jsp");
	            d.forward(request, response);
		
			}
		} else {
			request.setAttribute("msg", resultado.getMsg());
			
			d = request.getRequestDispatcher("errors.jsp");
		}
				
		if (d != null)
			d.forward(request, response);
	}
}