package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.enuns.Estados;
import br.com.syslib.enuns.TipoEndereco;
import br.com.syslib.enuns.TipoLogradouro;
import br.com.syslib.enuns.TipoResidencia;

public class EnderecoDAO extends AbstractJdbcDAO {

	public EnderecoDAO() {
		super("endereco", "end_id");
		// TODO Auto-generated constructor stub
	}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;
		
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO endereco (end_us_id, end_descricao, end_tpRes, end_tpLog, end_tpEnd, end_logrd, end_num, end_cep, end_bairro, end_cidade, end_estado, end_pais,end_pref, dtCadastro) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, endereco.getIdUsuario());
			pst.setString(2, endereco.getDescricao());
			pst.setString(3, endereco.getTpResid().getDescricao());
			pst.setString(4, endereco.getTpLogrdo().getDescricao());
			pst.setString(5, endereco.getTpEnd().getDescricao());			
			pst.setString(6, endereco.getLogradouro());
			pst.setInt(7, endereco.getNumero());
			pst.setString(8, endereco.getCep());
			pst.setString(9, endereco.getBairro());
			pst.setString(10, endereco.getCidade());
			pst.setString(11, endereco.getEstados().getDescricao());
			pst.setString(12, endereco.getPais());
			pst.setBoolean(13, endereco.getPreferencial());
			
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
		
		Endereco endereco = (Endereco) entidade;
		
		try {
			connection.setAutoCommit(false);
			
			sql = new StringBuilder();
			sql.append("UPDATE endereco SET end_descricao = ?, end_tpRes = ?, end_tpLog = ?, end_tpEnd = ?, end_logrd = ?, end_num = ?, end_bairro = ?, end_cidade = ?, " +
					"end_estado = ?, end_cep = ?, end_pais = ?, end_pref = ?, dtCadastro = sysdate() WHERE end_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, endereco.getDescricao());
			pst.setString(2, endereco.getTpResid().getDescricao());
			pst.setString(3, endereco.getTpLogrdo().getDescricao());
			pst.setString(4, endereco.getTpEnd().getDescricao());
			pst.setString(5, endereco.getLogradouro());
			pst.setInt(6, endereco.getNumero());
			pst.setString(7, endereco.getBairro());
			pst.setString(8, endereco.getCidade());
			pst.setString(9, endereco.getEstados().getDescricao());
			pst.setString(10, endereco.getCep());
			pst.setString(11, endereco.getPais());
			pst.setBoolean(12, endereco.getPreferencial());
			pst.setInt(13, endereco.getId());
			
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
		List<EntidadeDominio> enderecos = new	ArrayList<EntidadeDominio>();
		
		Endereco endereco = (Endereco) entidade;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM endereco");
		sql.append(" WHERE end_us_id = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, endereco.getIdUsuario());
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {			
			int id = rs.getInt("end_id");
			int idUsuario = rs.getInt("end_us_id");
			String descricao = rs.getString("end_descricao");
			String logradouro = rs.getString("end_logrd");
			int numero = rs.getInt("end_num");
			String residencia = rs.getString("end_num");
			String tpLog = rs.getString("end_num");
			String tpEnd = rs.getString("end_num");
			String bairro = rs.getString("end_bairro");
			String cidade = rs.getString("end_cidade");
			String estado = rs.getString("end_estado");
			String cep = rs.getString("end_cep");
			String pais = rs.getString("end_pais");
			Date dtCadastro = rs.getDate("dtCadastro");
			Boolean pref = rs.getBoolean("end_pref");
			
			endereco = new Endereco();
			endereco.setId(id);
			endereco.setIdUsuario(idUsuario);
			endereco.setDescricao(descricao);
			
			for(TipoResidencia res : TipoResidencia.values()) {
				if(res.getDescricao().contentEquals(residencia)) {
					endereco.setTpResid(res);
				}
			}
			for(TipoEndereco end : TipoEndereco.values()) {
				if(end.getDescricao().contentEquals(tpEnd)) {
					endereco.setTpEnd(end);
				}
			}
			for(TipoLogradouro log : TipoLogradouro.values()) {
				if(log.getDescricao().contentEquals(tpLog)) {
					endereco.setTpLogrdo(log);
				}
			}
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setPreferencial(pref);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
			endereco.setCep(cep);
			for(Estados est : Estados.values()) {
				if(est.getDescricao().contentEquals(estado)) {
					endereco.setEstados(est);
				}
			}
			endereco.setPais(pais);
			endereco.setDtCadastro(dtCadastro);
						
			enderecos.add(endereco);
		}

		rs.close();
		pst.close();
		connection.close();
		
		return enderecos;
	}
	
	public List<EntidadeDominio> getEntidadeDominio(int idUsuario) throws SQLException {
		openConnection();
		PreparedStatement pst;
		List<EntidadeDominio> enderecos = new	ArrayList<EntidadeDominio>();
		
		Endereco endereco = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM endereco");
		sql.append(" WHERE end_us_id = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idUsuario);
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {			
			int id = rs.getInt("end_id");
			int idUsu = rs.getInt("end_us_id");
			String descricao = rs.getString("end_descricao");
			String logradouro = rs.getString("end_logrd");
			int numero = rs.getInt("end_num");
			String bairro = rs.getString("end_bairro");
			String cidade = rs.getString("end_cidade");
			String estado = rs.getString("end_estado");
			String cep = rs.getString("end_cep");
			String pais = rs.getString("end_pais");
			Date dtCadastro = rs.getDate("dtCadastro");
			
			endereco = new Endereco();
			endereco.setId(id);
			endereco.setIdUsuario(idUsu);
			endereco.setDescricao(descricao);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			for(Estados est : Estados.values()) {
				if(est.getDescricao().contentEquals(estado)) {
					endereco.setEstados(est);
				}
			}
			endereco.setCep(cep);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setDtCadastro(dtCadastro);
						
			enderecos.add(endereco);
		}


		rs.close();
		pst.close();
		connection.close();
		
		return enderecos;
	}
	
	public EntidadeDominio getEntidadeDominioEndreco(int idEndreco) throws SQLException {
		openConnection();
		PreparedStatement pst;
		Endereco endereco = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM endereco");
		sql.append(" WHERE end_id = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idEndreco);
		
		ResultSet rs = pst.executeQuery();
		
		if (rs.next()) {			
			int id = rs.getInt("end_id");
			int idUsu = rs.getInt("end_us_id");
			String descricao = rs.getString("end_descricao");
			String logradouro = rs.getString("end_logrd");
			int numero = rs.getInt("end_num");
			String bairro = rs.getString("end_bairro");
			String cidade = rs.getString("end_cidade");
			String estado = rs.getString("end_estado");
			String cep = rs.getString("end_cep");
			String pais = rs.getString("end_pais");
			Date dtCadastro = rs.getDate("dtCadastro");
			String residencia = rs.getString("end_tpRes");
			String tpLog = rs.getString("end_tpLog");
			String tpEnd = rs.getString("end_tpEnd");
			Boolean pref = rs.getBoolean("end_pref");
			
			endereco = new Endereco();
			endereco.setId(id);
			endereco.setIdUsuario(idUsu);
			endereco.setDescricao(descricao);
			endereco.setPreferencial(pref);
			for(TipoResidencia res : TipoResidencia.values()) {
				if(res.getDescricao().contentEquals(residencia)) {
					endereco.setTpResid(res);
				}
			}
			for(TipoEndereco end : TipoEndereco.values()) {
				if(end.getDescricao().contentEquals(tpEnd)) {
					endereco.setTpEnd(end);
				}
			}
			for(TipoLogradouro log : TipoLogradouro.values()) {
				if(log.getDescricao().contentEquals(tpLog)) {
					endereco.setTpLogrdo(log);
				}
			}
			
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			for(Estados est : Estados.values()) {
				if(est.getDescricao().contentEquals(estado)) {
					endereco.setEstados(est);
				}
			}
			endereco.setCep(cep);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setDtCadastro(dtCadastro);
						
		}

		rs.close();
		pst.close();
		connection.close();
		
		return endereco;
	}
	
	public List<EntidadeDominio> visualizar(String filter,int idCliente) throws SQLException {
		openConnection();
		List<EntidadeDominio> enderecos = new	ArrayList<EntidadeDominio>();
		Endereco endereco = null;
		
		
		
		StringBuilder sql = new StringBuilder();
		
		if (!filter.equals("")) {
			sql.append("SELECT * FROM endereco T1 INNER JOIN cliente T2 on T2.cli_usu_id = T1.end_us_id");
			sql.append(" WHERE t2.cli_usu_id = " + idCliente + "");
			sql.append(" OR end_descricao LIKE '%" + filter + "%'");
			sql.append(" OR end_logrd LIKE '%" + filter + "%'");
			sql.append(" OR end_num LIKE '%" + filter + "%'");
			sql.append(" OR end_cep LIKE '%" + filter + "%'");
			sql.append(" OR end_bairro LIKE '%" + filter + "%'");
			sql.append(" OR end_cidade LIKE '%" + filter + "%'");
			sql.append(" OR end_estado LIKE '%" + filter + "%'");
			sql.append(" OR end_pais LIKE '%" + filter + "%'");
			sql.append(" OR end_tpRes LIKE '%" + filter + "%'");
			sql.append(" OR end_tpLog LIKE '%" + filter + "%'");
			sql.append(" OR end_tpEnd LIKE '%" + filter + "%'");
			sql.append(" OR end_pref LIKE '%" + filter + "%'");
		
				
		
		} else {
		sql.append("SELECT * FROM endereco T1 INNER JOIN cliente T2 on T2.cli_usu_id = T1.end_us_id  WHERE t2.cli_usu_id = " + idCliente + "");
		
		}
		PreparedStatement stmt = connection.prepareStatement(sql.toString());		
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			endereco = new Endereco();
			int id = rs.getInt("end_id");
			int idUsu = rs.getInt("end_us_id");
			String descricao = rs.getString("end_descricao");
			String logradouro = rs.getString("end_logrd");
			int numero = rs.getInt("end_num");
			String residencia = rs.getString("end_tpRes");
			String tpLog = rs.getString("end_tpLog");
			String tpEnd = rs.getString("end_tpEnd");
			String bairro = rs.getString("end_bairro");
			String cidade = rs.getString("end_cidade");
			String estado = rs.getString("end_estado");
			String cep = rs.getString("end_cep");
			String pais = rs.getString("end_pais");
			Date dtCadastro = rs.getDate("dtCadastro");
			Boolean pref = rs.getBoolean("end_pref");
			
			endereco.setId(id);
			endereco.setIdUsuario(idUsu);
			endereco.setDescricao(descricao);
			endereco.setPreferencial(pref);
			for(TipoResidencia res : TipoResidencia.values()) {
				if(res.getDescricao().contentEquals(residencia)) {
					endereco.setTpResid(res);
				}
			}
			for(TipoEndereco end : TipoEndereco.values()) {
				if(end.getDescricao().contentEquals(tpEnd)) {
					endereco.setTpEnd(end);
				}
			}
			for(TipoLogradouro log : TipoLogradouro.values()) {
				if(log.getDescricao().contentEquals(tpLog)) {
					endereco.setTpLogrdo(log);
				}
			}
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			for(Estados est : Estados.values()) {
				if(est.getDescricao().contentEquals(estado)) {
					endereco.setEstados(est);
				}
			}
			endereco.setCep(cep);
			endereco.setEstado(estado);
			endereco.setPais(pais);
			endereco.setDtCadastro(dtCadastro);
						
			enderecos.add(endereco);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return enderecos;
	}


	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Boolean verificarPrefEnd(EntidadeDominio entidade,int idCliente) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Endereco endereco = (Endereco) entidade;
		
		
		StringBuilder sql = new StringBuilder();
		
		try {
			if (endereco.getPreferencial() == true) {
				sql.append("SELECT COUNT(*) FROM endereco T1 INNER JOIN cliente T2 ON T2.cli_usu_id = T1.end_us_id ");
				sql.append("WHERE T1.end_pref = 1 ");
				sql.append(" AND T2.cli_usu_id = " + idCliente + "");
				pst = connection.prepareStatement(sql.toString());
				rs = pst.executeQuery();
			}
			while (rs.next()) {
				connection.close();
				return true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		
		return false;
		
	}	
	
	
		
	
}