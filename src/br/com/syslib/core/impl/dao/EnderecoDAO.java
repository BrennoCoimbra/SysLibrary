package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;

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
			sql.append("INSERT INTO endereco (end_us_id, end_descricao, end_logrd, end_num, end_cep, end_bairro, end_cidade, end_estado, end_pais, dtCadastro) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, endereco.getIdUsuario());
			pst.setString(2, endereco.getDescricao());
			pst.setString(3, endereco.getLogradouro());
			pst.setInt(4, endereco.getNumero());
			pst.setString(5, endereco.getCep());
			pst.setString(6, endereco.getBairro());
			pst.setString(7, endereco.getCidade());
			pst.setString(8, endereco.getEstado());
			pst.setString(9, endereco.getPais());

			
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
			sql.append("UPDATE endereco SET end_descricao = ?, end_logrd = ?, end_num = ?, end_bairro = ?, end_cidade = ?, " +
					"end_estado = ?, end_cep = ?, end_pais = ?, dtCadastro = sysdate() WHERE end_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, endereco.getDescricao());
			pst.setString(2, endereco.getLogradouro());
			pst.setInt(3, endereco.getNumero());
			pst.setString(4, endereco.getBairro());
			pst.setString(5, endereco.getCidade());
			pst.setString(6, endereco.getEstado());
			pst.setString(7, endereco.getCep());
			pst.setString(8, endereco.getPais());
			
			pst.setInt(9, endereco.getId());
			
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
			String bairro = rs.getString("end_bairro");
			String cidade = rs.getString("end_cidade");
			String estado = rs.getString("end_estado");
			String cep = rs.getString("end_cep");
			String pais = rs.getString("end_pais");
			Date dtCadastro = rs.getDate("dtCadastro");
			
			endereco = new Endereco();
			endereco.setId(id);
			endereco.setIdUsuario(idUsuario);
			endereco.setDescricao(descricao);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
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
			endereco.setEstado(estado);
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
			
			endereco = new Endereco();
			endereco.setId(id);
			endereco.setIdUsuario(idUsu);
			endereco.setDescricao(descricao);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(numero);
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
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
	
	public List<EntidadeDominio> visualizar(String filter) throws SQLException {
		openConnection();
		List<EntidadeDominio> enderecos = new	ArrayList<EntidadeDominio>();
		Endereco endereco = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM endereco");
		if (!filter.equals("")) {
			sql.append(" WHERE end_descricao LIKE '%" + filter + "%'");
		}
		
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
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
			endereco.setEstado(estado);
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
	
}