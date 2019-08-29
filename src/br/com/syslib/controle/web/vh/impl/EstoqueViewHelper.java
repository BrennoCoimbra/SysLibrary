package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.FornecedorDAO;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;
import br.com.syslib.dominio.Fornecedor;
import br.com.syslib.dominio.Livro;
import br.com.syslib.enuns.TipoMovimentacaoEstoque;

public class EstoqueViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		Livro livro = null;
		Estoque estoque = null;
		String idLivro;
		
		if (operacao.equals ("SALVAR") || (operacao.equals("ALTERAR"))){
			idLivro = request.getParameter("idLivro");
			String idFornecedor = request.getParameter("fornecedor");
			String qtde = request.getParameter("qtde");
			String valorCompra = request.getParameter("valorCompra");
			String dataEnt = request.getParameter("dataEnt");
			
			
			estoque = new Estoque();
			
			estoque.setIdLivro(Integer.parseInt(idLivro));
			
			if (idFornecedor != null && !idFornecedor.trim().equals(""))
				try {
					estoque.setFornecedor((Fornecedor) new FornecedorDAO().getEntidadeDominio(Integer.parseInt(idFornecedor)));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
			
			
			
			try {
				estoque.setQtde(Integer.parseInt(qtde));
			} catch (Exception e) {
				estoque.setQtde(0);
			}
			
			try {
				estoque.setValorCompra(Double.parseDouble(valorCompra));
			} catch (Exception e) {
				estoque.setValorCompra(0);
			}
			
			estoque.setTpMov(TipoMovimentacaoEstoque.ENTRADA);
			
			estoque.setDataEnt(dataEnt);
			
			
			
		} else if (operacao.equals ("CONSULTAR")){
			idLivro = request.getParameter("IdLivro");
			int id = 0;
			if (idLivro != null && !idLivro.trim().equals("")) {
				id = Integer.parseInt(idLivro);
				livro = new Livro();
				livro.setId(id);
			} else {

				Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
				idLivro = request.getParameter("IdLivro");
				int i = 0;

				if (idLivro != null && !idLivro.trim().equals("")) {
					i = Integer.parseInt(idLivro);
				}

				if (resultado != null) {
					for (EntidadeDominio e : resultado.getEntidades()) {
						if (e != null && e.getId() != null && i > 0 && e.getId() == i) {
							livro = (Livro) e;
						}
					}
				}
			}
		}
		
		return estoque;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null) {
			request.getSession().setAttribute("resultado", resultado);
			
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("estoque.jsp");
			} 

		} else {
			request.setAttribute("msg", resultado.getMsg());
			
			d = request.getRequestDispatcher("errors.jsp");
		}
				
		if (d != null)
			d.forward(request, response);
	}
}