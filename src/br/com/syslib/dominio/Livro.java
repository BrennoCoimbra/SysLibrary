package br.com.syslib.dominio;

import java.util.List;

import br.com.syslib.enuns.Precificacoes;

public class Livro extends EntidadeDominio {
	
	private String Titulo;
	private int idLivro;
	private String Ano;
	private String Edicao;
	private String ISBN;
	private String Sinopse;
	private String CodBarras;
	private String motivo;
	private int NumPaginas;
	private double peso;
	private double Altura;
	private double largura;
	private double profundidade;
	private Boolean ativo;
	private Autor autor;
	private int quantidade;
	private List<Autor> autores;
	private List<Categoria> categorias;
	private Editora editora;
	private Precificacoes preficacao;
	private Estoque estoque = new Estoque();
	private double valorVenda;
	
	
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	public String getAno() {
		return Ano;
	}
	public void setAno(String ano) {
		Ano = ano;
	}
	public String getEdicao() {
		return Edicao;
	}
	public void setEdicao(String edicao) {
		Edicao = edicao;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getSinopse() {
		return Sinopse;
	}
	public void setSinopse(String sinopse) {
		Sinopse = sinopse;
	}
	public String getCodBarras() {
		return CodBarras;
	}
	public void setCodBarras(String codBarras) {
		CodBarras = codBarras;
	}
	public int getNumPaginas() {
		return NumPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		NumPaginas = numPaginas;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return Altura;
	}
	public void setAltura(double altura) {
		Altura = altura;
	}
	public double getLargura() {
		return largura;
	}
	public void setLargura(double largura) {
		this.largura = largura;
	}
	public double getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(double profundidade) {
		this.profundidade = profundidade;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public void addAutor(Autor autor) {
		autores.add(autor);
	}
	
	public void addCategoria(Categoria categoria) {
		categorias.add(categoria);
}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Precificacoes getPreficacao() {
		return preficacao;
	}
	public void setPreficacao(Precificacoes preficacao) {
		this.preficacao = preficacao;
	}
	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	public double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}
	
	
	
}
