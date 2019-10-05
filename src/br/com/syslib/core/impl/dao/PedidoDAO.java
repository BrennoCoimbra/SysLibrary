package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.ItemPedido;
import br.com.syslib.dominio.Pedido;

public class PedidoDAO extends AbstractJdbcDAO {

	public PedidoDAO() {
		super("pedido", "ped_id");
		
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		Pedido pedido = (Pedido) entidade;
		
		try {
			StringBuilder sqlItem = new StringBuilder();
			sqlItem.append("INSERT INTO item_pedido(item_qtde,item_subtotal,item_liv_id,item_titulo,item_valor_unit,ped_item_id,item_status)");
			sqlItem.append("VALUES(?,?,?,?,?,?,?)");
			StringBuilder sqlPedido = new StringBuilder();
			sqlPedido.append("INSERT INTO pedido(ped_cli_id,ped_cli_end_id,ped_valor_frete,ped_cli_card_id1,ped_cli_card_id2,ped_valor_desconto,ped_valor_total,ped_status,ped_dtCadastro)");
			sqlPedido.append("VALUES(?,?,?,?,?,?,?,?,sysdate())");
			
			
			if(!pedido.getCupom().isEmpty()) {
				
			} 
			else {
				pst = connection.prepareStatement(sqlPedido.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, pedido.getIdClientePedido());
	            pst.setInt(2, pedido.getIdEndCliPedido());
	            pst.setDouble(3, pedido.getValorFrete());
	            pst.setInt(4, pedido.getIdClienteCartao1());
	            pst.setInt(5, pedido.getIdClienteCartao2());
	            pst.setDouble(6, pedido.getDescontoPedido());
	            pst.setDouble(7, pedido.getValorTotalPedido());
	            pst.setString(8, pedido.getStatusPedido());
	            pst.execute();
	            final ResultSet rs = pst.getGeneratedKeys();
	            int id = 0;
	            if (rs.next()) {
	                id = rs.getInt(1);
	                pedido.setIdPedido(id);
	            }
	            //ITEM
	            for (ItemPedido i : pedido.getPedItem()) {
	            	pst1 = connection.prepareStatement(sqlItem.toString(), Statement.RETURN_GENERATED_KEYS);
	                pst1.setInt(1, i.getItemQtde());
	                pst1.setDouble(2, i.getItemSubTotal());
	                pst1.setInt(3, i.getItemIdLivro());
	                pst1.setString(4, i.getItemTitLivro());
	                pst1.setDouble(5, i.getItemValorUnit());
	                pst1.setDouble(6, pedido.getIdPedido());
	                pst1.setString(7, "Ativo");
	                pst1.execute();              
	            }
	           
	            
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				pst1.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		String sqlPedido = null;
		
		sqlPedido = "UPDATE pedido SET ped_status = ? where ped_id = ?";
		if(pedido.getId() > 0 && pedido.getValorTotalPedido() == 0.0) {
		try {
			openConnection();			
			pst = connection.prepareStatement(sqlPedido);
			pst.setString(1, pedido.getStatusPedido());
			pst.setInt(2, pedido.getId());
			pst.execute();
			
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();				
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
	}
	 
	@Override
	public void excluir(EntidadeDominio entidade) {
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		String sqlPedido = null;
		
		sqlPedido = "UPDATE pedido SET ped_status = ? where ped_id = ?";
		
		try {
			openConnection();			
			pst = connection.prepareStatement(sqlPedido);
			pst.setString(1, pedido.getStatusPedido());
			pst.setInt(2, pedido.getId());
			pst.execute();
			
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();				
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		
	}
	
	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		
		return false;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		
		String sqlPedido = null;
		String sqlItem = null;
		List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
		
		
			sqlPedido = "select * from pedido where ped_id = ? ";
			sqlItem = "select * from item_pedido where ped_item_id = ?";
		

		
		try {
			openConnection();
			pst = connection.prepareStatement(sqlPedido);
			
			if(pedido.getId() != null) {
				pst.setInt(1, pedido.getId());								
			}
			
		ResultSet rs = pst.executeQuery();
		
		
		while(rs.next()) {
			Pedido ped = new Pedido();
			ped.setDtCadastro(rs.getDate("ped_dtCadastro"));
			ped.setIdClienteCartao1(rs.getInt("ped_cli_card_id1"));
			ped.setIdClienteCartao2(rs.getInt("ped_cli_card_id2"));
			ped.setIdClientePedido(rs.getInt("ped_cli_id"));
			ped.setValorFrete(rs.getDouble("ped_valor_frete"));
			ped.setDescontoPedido(rs.getDouble("ped_valor_desconto"));
			ped.setValorTotalPedido(rs.getDouble("ped_valor_total"));
			ped.setStatusPedido(rs.getString("ped_status"));
			ped.setIdEndCliPedido(rs.getInt("ped_cli_end_id"));
			ped.setId(rs.getInt("ped_id"));
			ped.setIdPedido(rs.getInt("ped_id"));
			
			pst = connection.prepareStatement(sqlItem);
			pst.setInt(1, pedido.getId());
			ResultSet rs1 = pst.executeQuery();
			rs1 = pst.executeQuery();
			
			while (rs1.next()) {
				ItemPedido item = new ItemPedido();
				item.setItemId(rs1.getInt("item_id"));
				item.setItemIdLivro(rs1.getInt("item_liv_id"));
				item.setItemQtde(rs1.getInt("item_qtde"));
				item.setItemSubTotal(rs1.getInt("item_subtotal"));
				item.setItemTitLivro(rs1.getString("item_titulo"));
				item.setItemValorUnit(rs1.getInt("item_valor_unit"));
				item.setItemPedId(rs1.getInt("ped_item_id"));
				item.setItemStatus(rs1.getString("item_status"));
				ped.getPedItem().add(item);
			}
			pedidos.add(ped);
		}
		
		return pedidos;
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EntidadeDominio getEntidadeDoiminio(int idPedido) throws SQLException {
		Pedido ped = new Pedido();
		PreparedStatement pst = null;
		
		String sqlPedido = null;
		String sqlItem = null;
		
		
			sqlPedido = "select * from pedido where ped_id = ? ";
			sqlItem = "select * from item_pedido where ped_item_id = ?";
		

		
		try {
			openConnection();
			pst = connection.prepareStatement(sqlPedido);
			
			if(idPedido != 0) {
				pst.setInt(1, idPedido);								
			}
			
		ResultSet rs = pst.executeQuery();
		
		
		while(rs.next()) {
			
			ped.setDtCadastro(rs.getDate("ped_dtCadastro"));
			ped.setIdClienteCartao1(rs.getInt("ped_cli_card_id1"));
			ped.setIdClienteCartao2(rs.getInt("ped_cli_card_id2"));
			ped.setIdClientePedido(rs.getInt("ped_cli_id"));
			ped.setValorFrete(rs.getDouble("ped_valor_frete"));
			ped.setDescontoPedido(rs.getDouble("ped_valor_desconto"));
			ped.setValorTotalPedido(rs.getDouble("ped_valor_total"));
			ped.setStatusPedido(rs.getString("ped_status"));
			ped.setIdEndCliPedido(rs.getInt("ped_cli_end_id"));
			ped.setId(rs.getInt("ped_id"));
			ped.setIdPedido(rs.getInt("ped_id"));
			
			pst = connection.prepareStatement(sqlItem);
			pst.setInt(1, idPedido);
			ResultSet rs1 = pst.executeQuery();
			rs1 = pst.executeQuery();
			
			while (rs1.next()) {
				ItemPedido item = new ItemPedido();
				item.setItemId(rs1.getInt("item_id"));
				item.setItemIdLivro(rs1.getInt("item_liv_id"));
				item.setItemQtde(rs1.getInt("item_qtde"));
				item.setItemSubTotal(rs1.getInt("item_subtotal"));
				item.setItemTitLivro(rs1.getString("item_titulo"));
				item.setItemValorUnit(rs1.getInt("item_valor_unit"));
				item.setItemPedId(rs1.getInt("ped_item_id"));
				item.setItemStatus(rs1.getString("item_status"));
				ped.getPedItem().add(item);
			}
			
		}
		
		return ped;
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EntidadeDominio> visualizar(String filter, int idCliente)throws SQLException {
		openConnection();
		List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
		Pedido pedido = null;
		
		
		StringBuilder sql = new StringBuilder();
		
		if(!filter.equals("")) {
			sql.append("SELECT * FROM pedido T1 INNER JOIN cliente T2 ON T2.cli_usu_id = T1.ped_cli_id AND T1.ped_cli_id = " + idCliente + "");
			sql.append(" WHERE T2.cli_usu_id = " + idCliente + "");
			sql.append(" AND T1.ped_id LIKE '%" + filter + "%'");
			sql.append(" OR T1.ped_status LIKE '%" + filter + "%'");
			sql.append(" OR T1.ped_dtCadastro LIKE '%" + filter + "%'");
			sql.append(" OR T1.ped_valor_total LIKE '%" + filter + "%'");	
			
		} else {
			sql.append("SELECT * FROM pedido T1 INNER JOIN cliente T2 ON T2.cli_usu_id = T1.ped_cli_id AND T1.ped_cli_id = " + idCliente + "");
		}
		PreparedStatement stmt = connection.prepareStatement(sql.toString());		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			pedido = new Pedido();
			int idPed = rs.getInt("T1.ped_id");
			int idUsu = rs.getInt("T1.ped_cli_id");
			String status = rs.getString("T1.ped_status");
			Double valorTotal = rs.getDouble("T1.ped_valor_total");
			Date dtPedido = rs.getDate("T1.ped_dtCadastro");
			
			pedido.setIdPedido(idPed);
			pedido.setIdClientePedido(idUsu);
			pedido.setStatusPedido(status);
			pedido.setValorTotalPedido(valorTotal);
			pedido.setDtCadastro(dtPedido);						
			pedidos.add(pedido);
		}
		rs.close();
		stmt.close();
		connection.close();
		
		return pedidos;
	}
	
	public List<EntidadeDominio> visualizar(String filter)throws SQLException {
		openConnection();
		List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
		Pedido pedido = null;
		
		
		StringBuilder sql = new StringBuilder();
		
		if(!filter.equals("")) {
			sql.append("SELECT * FROM pedido T1 INNER JOIN cliente T2 ON T2.cli_usu_id = T1.ped_cli_id ");
			sql.append(" WHERE T1.ped_id LIKE '%" + filter + "%'");
			sql.append(" OR T1.ped_status LIKE '%" + filter + "%'");
			sql.append(" OR T1.ped_dtCadastro LIKE '%" + filter + "%'");
			sql.append(" OR T1.ped_valor_total LIKE '%" + filter + "%'");
			sql.append(" ORDER BY T1.ped_dtCadastro,T1.ped_status");
			
		} else {
			sql.append("SELECT * FROM pedido T1 INNER JOIN cliente T2 ON T2.cli_usu_id = T1.ped_cli_id ORDER BY T1.ped_dtCadastro,T1.ped_status");
		}
		PreparedStatement stmt = connection.prepareStatement(sql.toString());		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			pedido = new Pedido();
			int idPed = rs.getInt("T1.ped_id");
			int idUsu = rs.getInt("T1.ped_cli_id");
			String status = rs.getString("T1.ped_status");
			Double valorTotal = rs.getDouble("T1.ped_valor_total");
			Date dtPedido = rs.getDate("T1.ped_dtCadastro");
			int idEnd = rs.getInt("T1.ped_cli_end_id");
			
			pedido.setIdPedido(idPed);
			pedido.setIdClientePedido(idUsu);
			pedido.setStatusPedido(status);
			pedido.setValorTotalPedido(valorTotal);
			pedido.setDtCadastro(dtPedido);
			pedido.setIdEndCliPedido(idEnd);
			pedidos.add(pedido);
		}
		rs.close();
		stmt.close();
		connection.close();
		
		return pedidos;
	}
}
