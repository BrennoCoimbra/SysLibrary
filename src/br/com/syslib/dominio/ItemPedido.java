package br.com.syslib.dominio;

import java.util.ArrayList;
import java.util.List;

public class ItemPedido extends EntidadeDominio {
	
	
	private int itemId;
	private int itemPedId;
	private int itemQtde;
	private double itemSubTotal;
	private int itemIdLivro;
	private String itemTitLivro;
	private String itemStatus;
	private double itemValorUnit;
	private List<Livro> livros = new ArrayList<>();
	
	public ItemPedido() {
		
	}
	
	public ItemPedido(int itemId, int itemQtde) {
        this.itemId = itemId;
        this.itemQtde = itemQtde;

    }
	
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemPedId() {
		return itemPedId;
	}
	public void setItemPedId(int itemPedId) {
		this.itemPedId = itemPedId;
	}
	public int getItemQtde() {
		return itemQtde;
	}
	public void setItemQtde(int itemQtde) {
		this.itemQtde = itemQtde;
	}
	public double getItemSubTotal() {
		return itemSubTotal;
	}
	public void setItemSubTotal(double itemSubTotal) {
		this.itemSubTotal = itemSubTotal;
	}
	public int getItemIdLivro() {
		return itemIdLivro;
	}
	public void setItemIdLivro(int itemIdLivro) {
		this.itemIdLivro = itemIdLivro;
	}
	public String getItemTitLivro() {
		return itemTitLivro;
	}
	public void setItemTitLivro(String itemTitLivro) {
		this.itemTitLivro = itemTitLivro;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public double getItemValorUnit() {
		return itemValorUnit;
	}
	public void setItemValorUnit(double itemValorUnit) {
		this.itemValorUnit = itemValorUnit;
	}
	
	

}
