package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.syslib.core.impl.dao.ControleAcessoDAO;
import br.com.syslib.dominio.Acesso;
import br.com.syslib.dominio.EntidadeDominio;

public class AcessoDAO extends AbstractJdbcDAO {

	protected AcessoDAO() {
		super("acesso", "ac_id");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Acesso acesso = (Acesso) entidadeDominio;

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM acesso WHERE ac_id = ?");
		stmt.setLong(1, acesso.getId());
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("ac_id");
			String descricao =rs.getString("ac_descricao");
			Date dtCadastro = rs.getDate("dtCadastro");
			
			acesso = new Acesso();
			acesso.setId(id);
			acesso.setDescricao(descricao);
			acesso.setDtCadastro(dtCadastro);
			acesso.setControlesAcesso(new ControleAcessoDAO().getEntidadeDominioAcesso(id));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return acesso;
	}
	
	public EntidadeDominio getEntidadeDominio(int idAcesso) throws SQLException {
		openConnection();
		Acesso acesso = null;

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM acesso WHERE ac_id = ?");
		stmt.setLong(1,idAcesso);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			int id = rs.getInt("ac_id");
			String descricao =rs.getString("ac_descricao");
			Date dtCadastro = rs.getDate("dtCadastro");
			
			acesso = new Acesso();
			acesso.setId(id);
			acesso.setDescricao(descricao);
			acesso.setDtCadastro(dtCadastro);
			acesso.setControlesAcesso(new ControleAcessoDAO().getEntidadeDominioAcesso(id));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return acesso;
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
