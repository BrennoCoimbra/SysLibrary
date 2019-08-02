package br.com.syslib.core.impl.negocio;

import java.util.List;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.Autor;
import br.com.syslib.dominio.Categoria;
import br.com.syslib.dominio.Editora;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Livro;

public class ValidadorDadosObrigatoriosLivro implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Livro){
			Livro livro = (Livro)entidade;
			
			String titulo = livro.getTitulo();
			String ISBN = livro.getISBN();
			double altura = livro.getAltura();
			double largura = livro.getLargura();
			double peso = livro.getPeso();
			double profundidade = livro.getProfundidade();
			String codBarras = livro.getCodBarras();
			List<Autor> autores = livro.getAutores();
			List<Categoria> categorias = livro.getCategorias();
			Editora editora = livro.getEditora();
			
			if(altura <= 0 || largura <= 0 || peso <= 0 || profundidade <= 0 ||
					autores == null || categorias == null  || editora == null)
				return "Altura, Largura, Peso, Profundidade, Autor, Categoria, Editora são de preenchimento obrigatório!";
			
			if(titulo.trim().equals("") || ISBN.trim().equals("") || codBarras.trim().equals(""))
				return "Código de Barras, Título e ISBN são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um Livro!";
		}
		return null;
	}
}