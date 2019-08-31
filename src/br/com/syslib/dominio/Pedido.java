package br.com.syslib.dominio;

import java.util.ArrayList;
import java.util.List;

public class Pedido extends EntidadeDominio {	
	
     
    private int idPedido;
    private String dtPedido;
    private double valorTotalPedido;
    private double subtotalPedido;
    private int idClientePedido;   
    private int idEndCliPedido;
    private int idClienteCartao1;
    private int idClienteCartao2;
    private double descontoPedido;
    private double valorTroca;
    private double valorFrete;   
    private int idItemPedido;
    private int qtdeItemPedido;
    private int descontoItem;
    private String statusPedido;
    private String codigoPromocionalPedido;
    private String codigoTrocaPedido;    
    private int qtdeCar;
    private List<Cupom> cupom = new ArrayList<>();
    private List<ItemPedido> pedItem = new ArrayList<ItemPedido>() ;
    private List<Livro> pedLivro = new ArrayList<Livro>() ;
    private Estoque pedEstoque = new  Estoque() ;
    
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getDtPedido() {
		return dtPedido;
	}
	public void setDtPedido(String dtPedido) {
		this.dtPedido = dtPedido;
	}
	public double getValorTotalPedido() {
		return valorTotalPedido;
	}
	public void setValorTotalPedido(double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}
	public double getSubtotalPedido() {
		return subtotalPedido;
	}
	public void setSubtotalPedido(double subtotalPedido) {
		this.subtotalPedido = subtotalPedido;
	}
	public int getIdClientePedido() {
		return idClientePedido;
	}
	public void setIdClientePedido(int idClientePedido) {
		this.idClientePedido = idClientePedido;
	}
	public int getIdEndCliPedido() {
		return idEndCliPedido;
	}
	public void setIdEndCliPedido(int idEndCliPedido) {
		this.idEndCliPedido = idEndCliPedido;
	}
	public int getIdClienteCartao1() {
		return idClienteCartao1;
	}
	public void setIdClienteCartao1(int idClienteCartao1) {
		this.idClienteCartao1 = idClienteCartao1;
	}
	public int getIdClienteCartao2() {
		return idClienteCartao2;
	}
	public void setIdClienteCartao2(int idClienteCartao2) {
		this.idClienteCartao2 = idClienteCartao2;
	}
	public double getDescontoPedido() {
		return descontoPedido;
	}
	public void setDescontoPedido(double descontoPedido) {
		this.descontoPedido = descontoPedido;
	}
	public double getValorTroca() {
		return valorTroca;
	}
	public void setValorTroca(double valorTroca) {
		this.valorTroca = valorTroca;
	}
	public double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}
	public int getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(int idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	public int getQtdeItemPedido() {
		return qtdeItemPedido;
	}
	public void setQtdeItemPedido(int qtdeItemPedido) {
		this.qtdeItemPedido = qtdeItemPedido;
	}
	public int getDescontoItem() {
		return descontoItem;
	}
	public void setDescontoItem(int descontoItem) {
		this.descontoItem = descontoItem;
	}
	public String getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}
	public String getCodigoPromocionalPedido() {
		return codigoPromocionalPedido;
	}
	public void setCodigoPromocionalPedido(String codigoPromocionalPedido) {
		this.codigoPromocionalPedido = codigoPromocionalPedido;
	}
	public String getCodigoTrocaPedido() {
		return codigoTrocaPedido;
	}
	public void setCodigoTrocaPedido(String codigoTrocaPedido) {
		this.codigoTrocaPedido = codigoTrocaPedido;
	}
	public int getQtdeCar() {
		return qtdeCar;
	}
	public void setQtdeCar(int qtdeCar) {
		this.qtdeCar = qtdeCar;
	}
	public List<Cupom> getCupom() {
		return cupom;
	}
	public void setCupom(List<Cupom> cupom) {
		this.cupom = cupom;
	}
	public List<ItemPedido> getPedItem() {
		return pedItem;
	}
	public void setPedItem(List<ItemPedido> pedItem) {
		this.pedItem = pedItem;
	}
	public List<Livro> getPedLivro() {
		return pedLivro;
	}
	public void setPedLivro(List<Livro> pedLivro) {
		this.pedLivro = pedLivro;
	}
	public Estoque getPedEstoque() {
		return pedEstoque;
	}
	public void setPedEstoque(Estoque pedEstoque) {
		this.pedEstoque = pedEstoque;
	}
	
    
    
    
}
