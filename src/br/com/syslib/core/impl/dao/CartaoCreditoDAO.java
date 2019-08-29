package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.syslib.dominio.CartaoCredito;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.enuns.BandeiraCartao;

public class CartaoCreditoDAO extends AbstractJdbcDAO {
	
	public CartaoCreditoDAO() {
		super("cartaocredito","cartao_id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		StringBuilder sql;
		PreparedStatement pst = null;
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;

		try {
			connection.setAutoCommit(false);

			sql = new StringBuilder();
			sql.append("INSERT INTO cartaocredito (cartao_desc, cartao_idUsuario, cartao_nome, cartao_numero, " +
					"cartao_mes, cartao_ano, cartao_codigoSeg, cartao_bandeira,cartao_pref, dtCadastro) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, cartaoCredito.getDescricao());
			pst.setInt(2, cartaoCredito.getIdUsuario());
			pst.setString(3, cartaoCredito.getNomeCartao());
			pst.setString(4, cartaoCredito.getNumeroCartao());
			pst.setInt(5, cartaoCredito.getMes());
			pst.setInt(6, cartaoCredito.getAno());
			pst.setInt(7, cartaoCredito.getCodigoSeguranca());
			pst.setString(8, cartaoCredito.getBandeiraCartao().getDescricao());
			pst.setBoolean(9, cartaoCredito.getPreferencial());
			
			pst.executeUpdate();
		
			connection.commit();
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
	public void alterar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		StringBuilder sql;
		PreparedStatement pst = null;
		
		CartaoCredito cartaoCredito = (CartaoCredito) entidade;
		
		try {
			connection.setAutoCommit(false);
			
			sql = new StringBuilder();
			sql.append("UPDATE cartaocredito SET cartao_desc = ?, cartao_idUsuario = ?, cartao_nome = ?, cartao_numero = ?, " + 
					"cartao_mes = ?, cartao_ano = ?, cartao_codigoSeg = ?, cartao_bandeira = ?, cartao_pref = ?, dtCadastro = sysdate() WHERE cartao_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, cartaoCredito.getDescricao());
			pst.setInt(2, cartaoCredito.getIdUsuario());
			pst.setString(3, cartaoCredito.getNomeCartao());
			pst.setString(4, cartaoCredito.getNumeroCartao());
			pst.setInt(5, cartaoCredito.getMes());
			pst.setInt(6, cartaoCredito.getAno());
			pst.setInt(7, cartaoCredito.getCodigoSeguranca());
			pst.setString(8, cartaoCredito.getBandeiraCartao().getDescricao());
			pst.setBoolean(9, cartaoCredito.getPreferencial());
			pst.setInt(10, cartaoCredito.getId());
			
			pst.executeUpdate();
			
			connection.commit();
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
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst;
		List<EntidadeDominio> cartoes = new	ArrayList<EntidadeDominio>();
		
		CartaoCredito cartao = (CartaoCredito) entidade;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM cartaocredito");
		sql.append(" WHERE cartao_idUsuario = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, cartao.getIdUsuario());
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {			
			int id = rs.getInt("cartao_id");
			int idUsu = rs.getInt("cartao_idUsuario");
			String descricao = rs.getString("cartao_desc");
			String nome = rs.getString("cartao_nome");
			String numero = rs.getString("cartao_numero");
			int mes = rs.getInt("cartao_mes");
			int ano = rs.getInt("cartao_ano");
			int cvv = rs.getInt("cartao_codigoSeg");
			String bandeira = rs.getString("cartao_bandeira");
			Date dtCadastro = rs.getDate("dtCadastro");
			Boolean pref = rs.getBoolean("cartao_pref");
			cartao = new CartaoCredito();
			cartao.setId(id);
			cartao.setIdUsuario(idUsu);
			cartao.setDescricao(descricao);
			cartao.setPreferencial(pref);
			cartao.setNomeCartao(nome);
			cartao.setNumeroCartao(numero);
			cartao.setMes(mes);
			cartao.setAno(ano);
			cartao.setCodigoSeguranca(cvv);
			for(BandeiraCartao bd : BandeiraCartao.values()) {
				if(bd.getDescricao().equals(bandeira)) {
					cartao.setBandeiraCartao(bd);
				}
			}
			cartao.setDtCadastro(dtCadastro);
						
			cartoes.add(cartao);
		}


		rs.close();
		pst.close();
		connection.close();
		
		return cartoes;
	}
	
	public List<EntidadeDominio> getEntidadeDominio(int idUsuario) throws SQLException {
		openConnection();
		PreparedStatement pst;
		List<EntidadeDominio> cartoes = new	ArrayList<EntidadeDominio>();
		
		CartaoCredito cartao = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM cartaocredito");
		sql.append(" WHERE cartao_idUsuario = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idUsuario);
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {			
			int id = rs.getInt("cartao_id");
			int idUsu = rs.getInt("cartao_idUsuario");
			String descricao = rs.getString("cartao_desc");
			String nome = rs.getString("cartao_nome");
			String numero = rs.getString("cartao_numero");
			int mes = rs.getInt("cartao_mes");
			int ano = rs.getInt("cartao_ano");
			int cvv = rs.getInt("cartao_codigoSeg");
			String bandeira = rs.getString("cartao_bandeira");
			Date dtCadastro = rs.getDate("dtCadastro");
			Boolean pref = rs.getBoolean("end_pref");
			cartao = new CartaoCredito();
			cartao.setId(id);
			cartao.setIdUsuario(idUsu);
			cartao.setDescricao(descricao);
			cartao.setPreferencial(pref);
			cartao.setNomeCartao(nome);
			cartao.setNumeroCartao(numero);
			cartao.setMes(mes);
			cartao.setAno(ano);
			cartao.setCodigoSeguranca(cvv);
			for(BandeiraCartao bd : BandeiraCartao.values()) {
				if(bd.getDescricao().equals(bandeira)) {
					cartao.setBandeiraCartao(bd);
				}
			}
			cartao.setDtCadastro(dtCadastro);
						
			cartoes.add(cartao);
		}


		rs.close();
		pst.close();
		connection.close();
		
		return cartoes;
	}

	public List<EntidadeDominio> visualizar(String filter,int idCliente) throws SQLException {
		openConnection();
		List<EntidadeDominio> cartoes = new	ArrayList<EntidadeDominio>();
		CartaoCredito cartao = null;
		
		StringBuilder sql = new StringBuilder();
		
		if (!filter.equals("")) {
			sql.append("SELECT * FROM cartaocredito T1 INNER JOIN cliente T2 on T2.cli_usu_id = T1.cartao_idUsuario AND T1.cartao_idUsuario = " + idCliente + "");
			sql.append(" WHERE t2.cli_usu_id = " + idCliente + "");
			sql.append(" AND T1.cartao_desc LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_nome LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_numero LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_mes LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_ano LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_codigoSeg LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_bandeira LIKE '%" + filter + "%'");
			sql.append(" OR T1.cartao_pref LIKE '%" + filter + "%'");
		} else {
			sql.append("SELECT * FROM cartaocredito T1 INNER JOIN cliente T2 on T2.cli_usu_id = T1.cartao_idUsuario WHERE t2.cli_usu_id = " + idCliente + "");
		}
		
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("cartao_id");
			int idUsu = rs.getInt("cartao_idUsuario");
			String descricao = rs.getString("cartao_desc");
			String nome = rs.getString("cartao_nome");
			String numero = rs.getString("cartao_numero");
			int mes = rs.getInt("cartao_mes");
			int ano = rs.getInt("cartao_ano");
			int cvv = rs.getInt("cartao_codigoSeg");
			String bandeira = rs.getString("cartao_bandeira");
			Date dtCadastro = rs.getDate("dtCadastro");
			
			cartao = new CartaoCredito();
			cartao.setId(id);
			cartao.setIdUsuario(idUsu);
			cartao.setDescricao(descricao);
			cartao.setNomeCartao(nome);
			cartao.setNumeroCartao(numero);
			cartao.setMes(mes);
			cartao.setAno(ano);
			cartao.setCodigoSeguranca(cvv);
			for(BandeiraCartao bd : BandeiraCartao.values()) {
				if(bd.getDescricao().contentEquals(bandeira)) {
					cartao.setBandeiraCartao(bd);
				}
			}
			cartao.setDtCadastro(dtCadastro);
						
			cartoes.add(cartao);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return cartoes;
	}
	
	
	
	public EntidadeDominio getEntidadeDominioCartaoCredito(int idCartao) throws SQLException {
		openConnection();
		PreparedStatement pst;
		CartaoCredito cartoes = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM cartaocredito");
		sql.append(" WHERE cartao_id = ?");
	
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idCartao);
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {
			int id = rs.getInt("cartao_id");
			int idUsu = rs.getInt("cartao_idUsuario");
			String descricao = rs.getString("cartao_desc");
			String nome = rs.getString("cartao_nome");
			String numero = rs.getString("cartao_numero");
			int mes = rs.getInt("cartao_mes");
			int ano = rs.getInt("cartao_ano");
			int cvv = rs.getInt("cartao_codigoSeg");
			String bandeira = rs.getString("cartao_bandeira");
			Date dtCadastro = rs.getDate("dtCadastro");
			Boolean pref = rs.getBoolean("cartao_pref");
			
			cartoes = new CartaoCredito();
			cartoes.setId(id);
			cartoes.setIdUsuario(idUsu);
			cartoes.setDescricao(descricao);
			cartoes.setNomeCartao(nome);
			cartoes.setNumeroCartao(numero);
			cartoes.setPreferencial(pref);
			cartoes.setMes(mes);
			cartoes.setAno(ano);
			cartoes.setCodigoSeguranca(cvv);
			for(BandeiraCartao bd : BandeiraCartao.values()) {
				if(bd.getDescricao().equals(bandeira)) {
					cartoes.setBandeiraCartao(bd);
				}
			}
			cartoes.setDtCadastro(dtCadastro);
						
		}

		rs.close();
		pst.close();
		connection.close();
		
		return cartoes;
		
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
