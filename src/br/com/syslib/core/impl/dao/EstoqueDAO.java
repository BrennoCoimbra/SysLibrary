package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Estoque;

public class EstoqueDAO extends AbstractJdbcDAO {

	public EstoqueDAO() {
		super("estoque", "es_id");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Estoque estoque = (Estoque) entidade;
		
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO estoque (es_idLivro, es_nValor, es_idFornecedor, es_qtdeTotal,dtCadastro) ");
			sql.append("VALUES (?,?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1,estoque.getIdLivro()); 
			pst.setDouble(2, estoque.getValorCompra());
			pst.setInt(3, estoque.getFornecedor().getId());
			pst.setInt(4, estoque.getQtde());

			
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
