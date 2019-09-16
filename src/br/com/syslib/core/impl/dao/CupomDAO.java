package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.ItemPedido;

public class CupomDAO extends AbstractJdbcDAO {
	
	public CupomDAO() {
		super("cupom_troca", "cupom_id");
			
		}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		Cupom cupom = (Cupom) entidade;
        openConnection();
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        //PreparedStatement pst2 = null;
        String sqlCupomTroca = null;
       // String sqlPedido = null;
        String sqlCupomItem = null;
        	        
        sqlCupomItem = "Insert into CUPOM_TROCA_ITEM(cpi_id, cpi_item_id) values(?,?)";
        sqlCupomTroca = "Insert into CUPOM_TROCA(cupom_cli_id, cupom_cod, cupom_valor, cupom_status, cupom_pedido_id, cupom_item_subtotal, cupom_qtdetrocar) values(?,?,?,?,?,?,?)";

        try {
            pst = connection.prepareStatement(sqlCupomTroca, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, cupom.getIdCupomCliente());
            pst.setString(2, cupom.getNomeCupom());
            pst.setDouble(3, cupom.getValorCupom());
            pst.setString(4, cupom.getStatusCupom());
            pst.setInt(5, cupom.getIdPedido());
            pst.setDouble(6, cupom.getSubtotal());
            pst.setInt(7, cupom.getQtdeTroca());
            pst.execute();
            final ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
                cupom.setIdCupom(id);
            }
            for (ItemPedido item : cupom.getItemPedido()) {
                if (item.getItemQtde() > 0) {
                    pst1 = connection.prepareStatement(sqlCupomItem);
                    pst1.setInt(1, cupom.getIdCupom());
                    pst1.setInt(2, item.getItemIdLivro());
                    pst1.execute();
                }
            }
//            sqlPedido = "Update PEDIDO set  ped_status=? where ped_id = ?";
//            pst2 = connection.prepareStatement(sqlPedido);
//            pst2.setString(1, cupom.getStatusPedido());
//            pst2.setInt(2, cupom.getIdPedido());
//            pst2.executeUpdate();

            connection.close();
            
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
				//pst2.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		Cupom cupom = (Cupom) entidade;
		openConnection();
		PreparedStatement pst = null;        
        String sqlCupomTroca = null;
        String s = null;
		
        try {
			if(cupom != null && cupom.getEnviarEstoque() != true && cupom.getStatusCupom() != null && cupom.getValorPedido() == 0) {
				sqlCupomTroca = "UPDATE CUPOM_TROCA SET cupom_status = ? WHERE cupom_id = ?";
				pst = connection.prepareStatement(sqlCupomTroca);
				pst.setString(1, cupom.getStatusCupom());
				pst.setInt(2, cupom.getIdCupom());
				pst.execute();
			} else if(cupom.getValorPedido() == 0) {
				sqlCupomTroca = "UPDATE CUPOM_TROCA SET cupom_enviar_estoque = ? WHERE cupom_id = ?";
				pst = connection.prepareStatement(sqlCupomTroca);
				if(cupom.getEnviarEstoque() == true) {
					s = "S";
				} else {
					s = "N";
				}
				pst.setString(1, s);
				pst.setInt(2, cupom.getIdCupom());
				pst.execute();
			} else if(!cupom.getCupom().isEmpty()) {
				for (Cupom c : cupom.getCupom()) {
				sqlCupomTroca = "UPDATE CUPOM_TROCA SET cupom_status = ?, cupom_item_subtotal = ? WHERE cupom_id = ?";
				pst = connection.prepareStatement(sqlCupomTroca);
				pst.setString(1, c.getStatusCupom());
				pst.setDouble(2, c.getSubtotal());
				pst.setInt(3, c.getIdCupom());
				pst.execute();
				}
			} else {
				sqlCupomTroca = "UPDATE CUPOM_TROCA SET cupom_valor = ? WHERE cupom_id = ?";
				pst = connection.prepareStatement(sqlCupomTroca);
				pst.setDouble(1, cupom.getValorCupom());
				pst.setInt(2, cupom.getIdCupom());
				pst.execute();
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
		 openConnection();
	     List<EntidadeDominio> cupons = new ArrayList<EntidadeDominio>();
	     Cupom cup = (Cupom) entidade;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     
	        if (!cup.getCupom().isEmpty()) {
	        	
	        } else { 
	        
	       	StringBuilder sql = new StringBuilder();
			sql.append("Select * from  cupom_troca where cupom_cod = ? and cupom_cli_id = ? ");
			pst = connection.prepareStatement(sql.toString());
            pst.setString(1, cup.getNomeCupom());
            pst.setInt(2, cup.getIdCupomCliente());
            rs = pst.executeQuery();
            while (rs.next()) {
                Cupom cupom = new Cupom();
                cupom.setStatusCupom(rs.getString("cupom_status"));
                cupom.setValorCupom(rs.getDouble("cupom_valor"));
                cupom.setNomeCupom(rs.getString("cupom_cod"));
                cupom.setDataCupom(rs.getString("cupom_data"));
                cupom.setIdPedido(rs.getInt("cupom_pedido_id"));
                cupom.setIdCupom(rs.getInt("cupom_id"));
                cupom.setIdCupomCliente(rs.getInt("cupom_cli_id"));
                cupom.setQtdeTroca(rs.getInt("cupom_qtdetrocar"));
                cupons.add(cupom);
            }
        }

        connection.close();
        return cupons;
    
		
	}
	public List<EntidadeDominio> visualizar(String filter,int idCliente) throws SQLException {
		openConnection();
		List<EntidadeDominio> cupons = new	ArrayList<EntidadeDominio>();
		Cupom cupom = null;
		
		
		
		StringBuilder sql = new StringBuilder();
		
		if (!filter.equals("")) {
			sql.append("SELECT * FROM cupom_troca T1 INNER JOIN cupom_troca_item T3 on T3.cpi_id = T1.cupom_id INNER JOIN cliente T2 on T2.cli_usu_id = T1.cupom_cli_id AND T1.cupom_cli_id = " + idCliente + "");
			sql.append(" WHERE t2.cli_usu_id = " + idCliente + "");
			sql.append(" AND T1.cupom_pedido_id LIKE '%" + filter + "%'");
			sql.append(" OR T3.cpi_item_id LIKE '%" + filter + "%'");
			sql.append(" OR T1.cupom_valor LIKE '%" + filter + "%'");
			sql.append(" OR T1.cupom_status LIKE '%" + filter + "%'");			
			sql.append(" OR T1.cupom_cod LIKE '%" + filter + "%'");
				
		
		} else {
			sql.append("SELECT * FROM cupom_troca T1 INNER JOIN cupom_troca_item T3 on T3.cpi_id = T1.cupom_id INNER JOIN cliente T2 on T2.cli_usu_id = T1.cupom_cli_id AND T1.cupom_cli_id = " + idCliente + "");
		
		}
		PreparedStatement stmt = connection.prepareStatement(sql.toString());		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			cupom = new Cupom();
			int id = rs.getInt("T1.cupom_id");
			int idUsu = rs.getInt("T1.cupom_cli_id");
			String cod = rs.getString("T1.cupom_cod");
			double valor = rs.getDouble("T1.cupom_valor");
			double valorSub = rs.getDouble("T1.cupom_item_subtotal");
			String status = rs.getString("T1.cupom_status");
			int itemid = rs.getInt("T1.cupom_item_id");
			int itemid1 = rs.getInt("T3.cpi_item_id");
			int pedid = rs.getInt("T1.cupom_pedido_id");
			int itemqtde = rs.getInt("T1.cupom_item_qtde");
			Date dtPedido = rs.getDate("T1.cupom_data");
			int qtdeTrocar = rs.getInt("T1.cupom_qtdetrocar");
			String enviarEstoque = rs.getString("T1.cupom_enviar_estoque");
			
			cupom.setId(id);
			cupom.setIdCupomCliente(idUsu);
			cupom.setNomeCupom(cod);
			cupom.setValorCupom(valor);
			cupom.setSubtotal(valorSub);
			cupom.setStatusCupom(status);
			if(enviarEstoque.equals("S") && enviarEstoque != null) {
			cupom.setEnviarEstoque(true);
			} else {
				cupom.setEnviarEstoque(false);
			}
			if(itemid == 0) {
			cupom.setIdItem(itemid1);
			} else {
				cupom.setIdItem(itemid);
			}
			cupom.setIdPedido(pedid);
			cupom.setQtdeTroca(itemqtde);
			
			cupom.setDtCadastro(dtPedido);
			cupom.setQtdeTroca(qtdeTrocar);
			
			cupons.add(cupom);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return cupons;
	}
	
	public List<EntidadeDominio> visualizar(String filter) throws SQLException {
		openConnection();
		List<EntidadeDominio> cupons = new	ArrayList<EntidadeDominio>();
		Cupom cupom = null;
		
		
		
		StringBuilder sql = new StringBuilder();
		
		if (!filter.equals("")) {
			sql.append("SELECT * FROM cupom_troca T1 INNER JOIN cupom_troca_item T3 on T3.cpi_id = T1.cupom_id ");
			sql.append(" WHERE T1.cupom_pedido_id LIKE '%" + filter + "%'");
			sql.append(" OR T3.cpi_item_id LIKE '%" + filter + "%'");
			sql.append(" OR T1.cupom_valor LIKE '%" + filter + "%'");
			sql.append(" OR T1.cupom_status LIKE '%" + filter + "%'");			
			sql.append(" OR T1.cupom_cod LIKE '%" + filter + "%'");
				
		
		} else {
			sql.append("SELECT * FROM cupom_troca T1 INNER JOIN cupom_troca_item T3 on T3.cpi_id = T1.cupom_id ");
		
		}
		PreparedStatement stmt = connection.prepareStatement(sql.toString());		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			cupom = new Cupom();
			int id = rs.getInt("T1.cupom_id");
			int idUsu = rs.getInt("T1.cupom_cli_id");
			String cod = rs.getString("T1.cupom_cod");
			double valor = rs.getDouble("T1.cupom_valor");
			double valorSub = rs.getDouble("T1.cupom_item_subtotal");
			String status = rs.getString("T1.cupom_status");
			int itemid = rs.getInt("T1.cupom_item_id");
			int itemid1 = rs.getInt("T3.cpi_item_id");
			int pedid = rs.getInt("T1.cupom_pedido_id");
			//Date dtPedido = rs.getDate("T1.ped_dtCadastro");
			int qtdeTrocar = rs.getInt("T1.cupom_qtdetrocar");
			String enviarEstoque = rs.getString("T1.cupom_enviar_estoque");
			if(enviarEstoque == null) {
				enviarEstoque = "NULL";
			}
			cupom.setId(id);
			cupom.setIdCupomCliente(idUsu);
			cupom.setNomeCupom(cod);
			cupom.setValorCupom(valor);
			cupom.setSubtotal(valorSub);
			cupom.setStatusCupom(status);
			if(enviarEstoque.equals("S") || enviarEstoque.equals("N") && enviarEstoque != null) {
				cupom.setEnviarEstoque(true);
				} else {
					cupom.setEnviarEstoque(false);
				}
			
			if(itemid == 0) {
			cupom.setIdItem(itemid1);
			} else {
				cupom.setIdItem(itemid);
			}
			cupom.setIdPedido(pedid);
			cupom.setQtdeTroca(qtdeTrocar);

			//cupom.setDtCadastro(dtPedido);
						
			cupons.add(cupom);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return cupons;
	}
	
	public EntidadeDominio getEntidadeDominioCupom(int idPedido) throws SQLException {
		openConnection();
		PreparedStatement pst;
		Cupom cupom = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM cupom_troca T1 INNER JOIN cupom_troca_item T3 on T3.cpi_id = T1.cupom_id ");
		sql.append(" WHERE T1.cupom_pedido_id = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idPedido);
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {			
			cupom = new Cupom();
			int id = rs.getInt("T1.cupom_id");
			int idUsu = rs.getInt("T1.cupom_cli_id");
			String cod = rs.getString("T1.cupom_cod");
			double valor = rs.getDouble("T1.cupom_valor");
			double valorSub = rs.getDouble("T1.cupom_item_subtotal");
			String status = rs.getString("T1.cupom_status");
			int itemid = rs.getInt("T1.cupom_item_id");
			int itemid1 = rs.getInt("T3.cpi_item_id");
			int pedid = rs.getInt("T1.cupom_pedido_id");
			int itemqtde = rs.getInt("T1.cupom_item_qtde");
			
			
			cupom.setId(id);
			cupom.setIdCupomCliente(idUsu);
			cupom.setNomeCupom(cod);
			cupom.setValorCupom(valor);
			cupom.setSubtotal(valorSub);
			cupom.setStatusCupom(status);
			if(itemid == 0) {
			cupom.setIdItem(itemid1);
			} else {
				cupom.setIdItem(itemid);
			}
			cupom.setIdPedido(pedid);
			cupom.setQtdeTroca(itemqtde);
			
			
						

		rs.close();
		pst.close();
		connection.close();
		
		
		}
		return cupom;
	}

	public EntidadeDominio getEntidadeDominioCupom(int idPedido,int idItem) throws SQLException {
		openConnection();
		PreparedStatement pst;
		Cupom cupom = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM cupom_troca T1 INNER JOIN cupom_troca_item T3 on T3.cpi_id = T1.cupom_id ");
		sql.append(" WHERE T1.cupom_pedido_id = ? and T3.cpi_item_id = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idPedido);
		pst.setInt(2, idItem);
		
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {			
			cupom = new Cupom();
			int id = rs.getInt("T1.cupom_id");
			int idUsu = rs.getInt("T1.cupom_cli_id");
			String cod = rs.getString("T1.cupom_cod");
			double valor = rs.getDouble("T1.cupom_valor");
			double valorSub = rs.getDouble("T1.cupom_item_subtotal");
			String status = rs.getString("T1.cupom_status");
			int itemid = rs.getInt("T1.cupom_item_id");
			int itemid1 = rs.getInt("T3.cpi_item_id");
			int pedid = rs.getInt("T1.cupom_pedido_id");
			int itemqtde = rs.getInt("T1.cupom_item_qtde");
			String enviarEstoque = rs.getString("T1.cupom_enviar_estoque");
			
			cupom.setId(id);
			cupom.setIdCupomCliente(idUsu);
			cupom.setNomeCupom(cod);
			cupom.setValorCupom(valor);
			cupom.setSubtotal(valorSub);
			cupom.setStatusCupom(status);
			if(enviarEstoque.equals("S") && enviarEstoque != null) {
				cupom.setEnviarEstoque(true);
				} else {
					cupom.setEnviarEstoque(false);
				}
			
			if(itemid == 0) {
			cupom.setIdItem(itemid1);
			} else {
				cupom.setIdItem(itemid);
			}
			cupom.setIdPedido(pedid);
			cupom.setQtdeTroca(itemqtde);
			
			
						

		rs.close();
		pst.close();
		connection.close();
		
		
		}
		return cupom;
	}

}
