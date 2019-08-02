package br.com.syslib.dominio;

public class LivroAutor extends EntidadeDominio {
	private Livro livro;
	private Autor autor;
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
