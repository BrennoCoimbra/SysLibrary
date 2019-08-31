package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		PreparedStatement pst1 = null;
		Estoque estoque = (Estoque) entidade;
			
		
		try {
			
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO estoque (es_idLivro, es_nValor, es_idFornecedor, es_qtdeTotal,es_dataEnt,es_tpMov,dtCadastro) ");
			sql.append("VALUES (?,?,?,?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1,estoque.getIdLivro()); 
			pst.setDouble(2, estoque.getValorCompra());
			pst.setInt(3, estoque.getFornecedor().getId());
			pst.setInt(4, estoque.getQtde());
			pst.setString(5, estoque.getDataEnt());
			pst.setInt(6, estoque.getTpMov().getCodigo());
			
			pst.executeUpdate();
			
			if(estoque.getValorVenda() != 0) {
				StringBuilder sql1 = new StringBuilder();
				sql1.append("UPDATE livros SET liv_valorVenda = ? WHERE liv_id = " + estoque.getIdLivro() + "");
				pst1 = connection.prepareStatement(sql1.toString());
				pst1.setDouble(1, estoque.getValorVenda());
				
				pst1.executeUpdate();
				
				pst1.close();
			} 
			
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
		PreparedStatement pst = null;
		Estoque estoque = (Estoque) entidade;
			
		
		try {
			
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO estoque (es_idLivro, es_qtdeTotal,es_tpMov,dtCadastro) ");
			sql.append("VALUES (?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1,estoque.getIdLivro()); 
			pst.setInt(2, estoque.getQtde());
			pst.setInt(3, estoque.getTpMov().getCodigo());
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public EntidadeDominio verificarUltPrecCompra(EntidadeDominio entidade) throws SQLException {
		openConnection();
		
		
		Estoque estoque = (Estoque) entidade;
		Estoque est = new  Estoque();	
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT es_idLivro,avg(es_nValor) as 'avg' from estoque WHERE es_idLivro = " + estoque.getIdLivro() + " group by es_idLivro");
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
		
		est.setIdLivro(rs.getInt("es_idLivro"));
		est.setValorCompra(rs.getDouble("avg"));
		
		}
		rs.close();
		stmt.close();
		return est;
	}
	
	public EntidadeDominio getQtdeEstoque(int idLivro) throws SQLException{
		openConnection();
		Estoque estoque = new Estoque();	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select es_idLivro,SUM(es_qtdeTotal) as 'qtdeTotal' from estoque where es_idLivro = " + idLivro + "");
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
		
		estoque.setIdLivro(rs.getInt("es_idLivro"));
		estoque.setQtde(rs.getInt("qtdeTotal"));
		
		}
		rs.close();
		stmt.close();
		return estoque;
	}
}

