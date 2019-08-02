package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.LivroDAO;
import br.com.syslib.dominio.Autor;
import br.com.syslib.dominio.Categoria;
import br.com.syslib.dominio.Editora;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Livro;

public class LivroViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		String operacao = request.getParameter("operacao");
		Livro livro = null;
		String idLivro;

		if (operacao.equals ("SALVAR") || (operacao.equals("ALTERAR"))){
			idLivro = request.getParameter("IdLivro");
			String ativo = request.getParameter("txtAtivo");
			String isbn = request.getParameter("isbn");
			String codigo_ean = request.getParameter("codBarras");
			String titulo = request.getParameter("titulo");
			String livro_ano = request.getParameter("livro_ano");
			String edicao = request.getParameter("edicao");
			String qtde_pgs = request.getParameter("numPgs");
			String[] idsAut = request.getParameterValues("txtAutor");
			String[] idsCat = request.getParameterValues("txtCategoria");
			String ideditora = request.getParameter("txtEditora");
			String peso = request.getParameter("peso");
			String altura = request.getParameter("altura");
			String largura = request.getParameter("largura");
			String profundidade = request.getParameter("prof");
			String sinopse = request.getParameter("txtSinopse");
			String motivo = request.getParameter("txtMotivo");

			livro = new Livro();
			
			livro.setAtivo(ativo.equals("1") ? true : false);
			livro.setISBN(isbn);
			livro.setCodBarras(codigo_ean);
			livro.setTitulo(titulo);
			livro.setAno(livro_ano);
			livro.setNumPaginas((qtde_pgs != null && !qtde_pgs.equals("")) ?(Integer.parseInt(qtde_pgs)): 0);
			livro.setEdicao(edicao);
			livro.setPeso((peso != null && !peso.equals(""))? (Double.parseDouble(peso)):0);
			livro.setAltura((altura != null && !altura.equals(""))? (Double.parseDouble(altura)):0);
			livro.setLargura((largura != null && !largura.equals(""))? (Double.parseDouble(largura)):0);
			livro.setProfundidade((profundidade != null && !profundidade.equals(""))? (Double.parseDouble(profundidade)):0);
			livro.setSinopse(sinopse);
			livro.setMotivo(motivo);
			

			Editora editora = new Editora();
			editora.setId(Integer.parseInt(ideditora));
			livro.setEditora(editora);
			
			List<Autor> autores = new ArrayList<Autor>();
			Autor aut;
			if(idsAut != null && !idsAut.equals("")) {
				for (String id : idsAut) {
					aut = new Autor();
					aut.setId(Integer.parseInt(id));
					autores.add(aut);
				}
				livro.setAutores(autores);
			}
			
			if(idsCat != null && !idsCat.equals("")) {
				List<Categoria> categorias = new ArrayList<Categoria>();
				Categoria cat;
				for (String id : idsCat) {
					cat = new Categoria();
					cat.setId(Integer.parseInt(id));
					categorias.add(cat);
				}
				livro.setCategorias(categorias);
			}

			if (idLivro != null && !idLivro.equals(""))
			livro.setId(Integer.parseInt(idLivro));

		} else if (operacao.equals("EXCLUIR") || operacao.equals("CONSULTAR")) {
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
		
		return livro;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null) {
			request.getSession().setAttribute("resultado", resultado);
			
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Livro cadastrado com sucesso!");
				
				d = request.getRequestDispatcher("FormLivro.jsp");
				
			} 
				else if (operacao.equals("VISUALIZAR")) {
				try {
					String filtro = request.getParameter("search");
					List<EntidadeDominio> livros = new LivroDAO().visualizar(filtro);
					request.setAttribute("livro", livros);
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar livros.");;
				}
				
				d = request.getRequestDispatcher("ConsultarLivro.jsp");
				
			
			} else if (operacao.equals("CONSULTAR")) {
				try {
					int IdLivro = Integer.parseInt(request.getParameter("IdLivro"));
					Livro livro = (Livro) new LivroDAO().getEntidadeDominio(IdLivro);
					request.setAttribute("livro", livro);
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar livros.");;
				}
				
				d = request.getRequestDispatcher("FormLivro.jsp");
				
			}  else if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("FormLivro.jsp");
				
			} else if (operacao.equals("EXCLUIR")) {
				d = request.getRequestDispatcher("ConsultarLivro.jsp");				
			}
		} else {
			request.setAttribute("msg", resultado.getMsg());
			d = request.getRequestDispatcher("Erros.jsp");
		}
		
		if (d != null)
			d.forward(request,response);
	}
}