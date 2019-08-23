package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.ControleAcesso;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.enuns.TipoAcesso;

public class ControleAcessoDAO extends AbstractJdbcDAO {
	

	public ControleAcessoDAO() {
		super("controle_acesso", "ca_id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {		
		openConnection();
		StringBuilder sql;
		PreparedStatement pst = null;
		
		ControleAcesso ControleAcesso = (ControleAcesso) entidade;
		
		try {
			connection.setAutoCommit(false);

			sql = new StringBuilder();
			sql.append("INSERT INTO controle_acesso (ca_acesso, ca_tpacesso, dtCadastro) ");
			sql.append("VALUES (?,?,?)");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setInt(1, ControleAcesso.getIdAcesso());
			pst.setInt(2, ControleAcesso.getTipoAcesso().getCodigo());
			
			Timestamp time = new Timestamp(ControleAcesso.getDtCadastro().getTime());
			pst.setTimestamp(3, time);
			
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
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		return null;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		PreparedStatement stmt;
		ControleAcesso ControleAcesso = (ControleAcesso) entidadeDominio;
		
		stmt = connection.prepareStatement("SELECT * FROM controle_acesso WHERE ca_id = ?");
		stmt.setLong(1, ControleAcesso.getId());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("ca_id");
			int idAcesso = rs.getInt("ca_acesso");
			int iTipoAcesso = rs.getInt("ca_tpacesso");
			
			ControleAcesso.setId(id);
			ControleAcesso.setIdAcesso(idAcesso);
			
			for (TipoAcesso tipoAcesso : TipoAcesso.values()) {
				if (iTipoAcesso == tipoAcesso.getCodigo()) {
					ControleAcesso.setTipoAcesso(tipoAcesso);
				}
			}
		}
		rs.close();
		stmt.close();
		connection.close();

		return ControleAcesso;
	}
	
	public List<EntidadeDominio> getEntidadeDominioAcesso(int idAcesso) throws SQLException {
		openConnection();
		PreparedStatement stmt;
		ControleAcesso ControleAcesso;
		List<EntidadeDominio> controlesDeAcesso = new ArrayList<EntidadeDominio>();
		
		stmt = connection.prepareStatement("SELECT * FROM ControleAcesso WHERE ca_id = ?");
		stmt.setLong(1, idAcesso);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("ca_id");
			int iAcesso = rs.getInt("ca_acesso");
			int iTipoAcesso = rs.getInt("ca_tpacesso");
			
			ControleAcesso = new ControleAcesso();
			ControleAcesso.setId(id);
			ControleAcesso.setIdAcesso(iAcesso);
			
			for (TipoAcesso tipoAcesso : TipoAcesso.values()) {
				if (iTipoAcesso == tipoAcesso.getCodigo()) {
					ControleAcesso.setTipoAcesso(tipoAcesso);
				}
			}
			
			controlesDeAcesso.add(ControleAcesso);
		}
		rs.close();
		stmt.close();
		connection.close();

		return controlesDeAcesso;
	}
	
	public List<EntidadeDominio> getEntidadeDominioTipoAcesso(int idTipoAcesso) throws SQLException {
		openConnection();
		PreparedStatement stmt;
		ControleAcesso ControleAcesso;
		List<EntidadeDominio> controlesDeAcesso = new ArrayList<EntidadeDominio>();
		
		stmt = connection.prepareStatement("SELECT * FROM controle_acesso WHERE ca_tpacesso = ?");
		stmt.setLong(1, idTipoAcesso);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("ca_id");
			int iAcesso = rs.getInt("ca_acesso");
			int iTipoAcesso = rs.getInt("ca_tpacesso");
			
			ControleAcesso = new ControleAcesso();
			ControleAcesso.setId(id);
			ControleAcesso.setIdAcesso(iAcesso);
			
			for (TipoAcesso tipoAcesso : TipoAcesso.values()) {
				if (iTipoAcesso == tipoAcesso.getCodigo()) {
					ControleAcesso.setTipoAcesso(tipoAcesso);
				}
			}
			
			controlesDeAcesso.add(ControleAcesso);
		}
		rs.close();
		stmt.close();
		connection.close();

		return controlesDeAcesso;
	}
	
	public List<EntidadeDominio> getLista() throws SQLException {
		openConnection();
		PreparedStatement stmt;
		ControleAcesso ControleAcesso;
		List<EntidadeDominio> controlesDeAcesso = new ArrayList<EntidadeDominio>();
		
		stmt = connection.prepareStatement("SELECT * FROM controle_acesso");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("ca_id");
			int iAcesso = rs.getInt("ca_acesso");
			int iTipoAcesso = rs.getInt("ca_tpacesso");
			
			ControleAcesso = new ControleAcesso();
			ControleAcesso.setId(id);
			ControleAcesso.setIdAcesso(iAcesso);
			
			for (TipoAcesso tipoAcesso : TipoAcesso.values()) {
				if (iTipoAcesso == tipoAcesso.getCodigo()) {
					ControleAcesso.setTipoAcesso(tipoAcesso);
				}
			}
			
			controlesDeAcesso.add(ControleAcesso);
		}
		rs.close();
		stmt.close();
		connection.close();

		return controlesDeAcesso;
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}