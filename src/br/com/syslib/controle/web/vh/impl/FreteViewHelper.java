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
import br.com.syslib.dominio.Frete;
import br.com.syslib.dominio.Pedido;

public class FreteViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Frete frete = null;

		if (operacao.equals("SALVAR")) {
			String endId = request.getParameter("end");

			frete = new Frete();
			frete.setIdEnd(Integer.parseInt(endId));

		}

		return frete;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher d = null;
		List<EntidadeDominio> entidade = (List<EntidadeDominio>) resultado.getEntidades();
		HttpSession session = request.getSession(true);
		String operacao = request.getParameter("operacao");
		Pedido pedido = session.getAttribute("pedido") == null ? new Pedido() : (Pedido) session.getAttribute("pedido");
		Frete frete = session.getAttribute("frete") == null ? new Frete() : (Frete) session.getAttribute("Frete");
		@SuppressWarnings({ "unchecked", "rawtypes" })
		ArrayList<EntidadeDominio> livros = session.getAttribute("livros") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("livros");

		if (operacao.equals("SALVAR")) {
			if(resultado.getMsg().equals("FRETEOK")) {				
				Frete freteBD = (Frete) entidade.get(0);
				int idfrete = freteBD.getIdEnd();
				pedido.setValorFrete(freteBD.getValorFrete());
				pedido.setValorTotalPedido(freteBD.getValorFrete() + pedido.getSubtotalPedido());
				pedido.setIdEndCliPedido(idfrete);			
				session.setAttribute("frete", frete);				
				session.setAttribute("pedido", pedido);
				session.setAttribute("livros", livros);
				response.sendRedirect("form-pedido-cartao.jsp");
				return;
				
			} else {
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
