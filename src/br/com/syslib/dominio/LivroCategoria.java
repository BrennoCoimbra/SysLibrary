package br.com.syslib.dominio;

public class LivroCategoria extends EntidadeDominio {
	private Livro livro;
	private Categoria categoria;
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
