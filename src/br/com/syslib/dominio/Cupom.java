package br.com.syslib.dominio;

import java.util.ArrayList;
import java.util.List;

public class Cupom extends EntidadeDominio {
	
	
	    private double subtotal;
	    private int idCupom;
	    private double valorPedido;
	    private double descontoPedido;    
	    private int idItem;
	    private String nomeCupom;
	    private double valorCupom;
	    private String statusCupom;
	    private String dataCupom;
	    private int idCupomCliente;
	    private int idPedido;
	    private String statusPedido;
	    private List<ItemPedido> itemPedido = new ArrayList<>() ;
	    private List<Cupom> cupom =  new ArrayList<>();
	    
	    
		public double getSubtotal() {
			return subtotal;
		}
		public void setSubtotal(double subtotal) {
			this.subtotal = subtotal;
		}
		public int getIdCupom() {
			return idCupom;
		}
		public void setIdCupom(int idCupom) {
			this.idCupom = idCupom;
		}
		public double getValorPedido() {
			return valorPedido;
		}
		public void setValorPedido(double valorPedido) {
			this.valorPedido = valorPedido;
		}
		public double getDescontoPedido() {
			return descontoPedido;
		}
		public void setDescontoPedido(double descontoPedido) {
			this.descontoPedido = descontoPedido;
		}
		public int getIdItem() {
			return idItem;
		}
		public void setIdItem(int idItem) {
			this.idItem = idItem;
		}
		public String getNomeCupom() {
			return nomeCupom;
		}
		public void setNomeCupom(String nomeCupom) {
			this.nomeCupom = nomeCupom;
		}
		public double getValorCupom() {
			return valorCupom;
		}
		public void setValorCupom(double valorCupom) {
			this.valorCupom = valorCupom;
		}
		public String getStatusCupom() {
			return statusCupom;
		}
		public void setStatusCupom(String statusCupom) {
			this.statusCupom = statusCupom;
		}
		public String getDataCupom() {
			return dataCupom;
		}
		public void setDataCupom(String dataCupom) {
			this.dataCupom = dataCupom;
		}
		public int getIdCupomCliente() {
			return idCupomCliente;
		}
		public void setIdCupomCliente(int idCupomCliente) {
			this.idCupomCliente = idCupomCliente;
		}
		public int getIdPedido() {
			return idPedido;
		}
		public void setIdPedido(int idPedido) {
			this.idPedido = idPedido;
		}
		public String getStatusPedido() {
			return statusPedido;
		}
		public void setStatusPedido(String statusPedido) {
			this.statusPedido = statusPedido;
		}
		public List<ItemPedido> getItemPedido() {
			return itemPedido;
		}
		public void setItemPedido(List<ItemPedido> itemPedido) {
			this.itemPedido = itemPedido;
		}
		public List<Cupom> getCupom() {
			return cupom;
		}
		public void setCupom(List<Cupom> cupom) {
			this.cupom = cupom;
		}
	    
	    
}
