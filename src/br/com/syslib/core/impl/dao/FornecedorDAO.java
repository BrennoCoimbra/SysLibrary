package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Fornecedor;

public class FornecedorDAO extends AbstractJdbcDAO {
	
	public FornecedorDAO() {
		super("fornecedor", "id");
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
	
	public List<EntidadeDominio> visualizar() throws SQLException {
		openConnection();
		List<EntidadeDominio> fornecedores = new ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM fornecedor");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Fornecedor fornecedor	= new Fornecedor();
			fornecedor.setId(rs.getInt("id"));
			fornecedor.setNomeFornecedor(rs.getString("razao"));

			fornecedores.add(fornecedor);
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return fornecedores;
	}

	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Fornecedor fornecedor = (Fornecedor) entidadeDominio;

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
		stmt.setLong(1, fornecedor.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			fornecedor.setId(rs.getInt("id"));
			fornecedor.setNomeFornecedor(rs.getString("razao"));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return fornecedor;
	}
	
	public EntidadeDominio getEntidadeDominio(int idFornecedor) throws SQLException {
		openConnection();
		Fornecedor fornecedor = new Fornecedor();

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
		stmt.setInt(1, idFornecedor);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			fornecedor.setId(rs.getInt("id"));
			fornecedor.setNomeFornecedor(rs.getString("razao"));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return fornecedor;
	}
}


