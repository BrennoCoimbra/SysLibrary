package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Categoria;
import br.com.syslib.dominio.EntidadeDominio;

public class CategoriaDAO extends AbstractJdbcDAO{
	public CategoriaDAO() {
		super("categorias", "cat_id");
		
	}

	public List<EntidadeDominio> getLista() throws SQLException {
		
		PreparedStatement stmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from categorias;");
		try {
			openConnection();
			stmt = connection.prepareStatement(sb.toString());
			ResultSet rs = stmt.executeQuery();
			List<EntidadeDominio> categorias = new	ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Categoria categoria	= new Categoria();
				categoria.setId(rs.getInt("cat_id"));
				categoria.setNome(rs.getString("cat_nome"));

				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			return categorias;
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return null;
	
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from categorias order by 2;");
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> categorias = new ArrayList<>();
			Categoria c = new Categoria();
			while(rs.next()){
				c = new Categoria();
				c.setId(rs.getInt("cat_id"));
				c.setNome(rs.getString("cat_nome"));
				categorias.add(c);
			}
			return categorias;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {

		
	}

	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Categoria categoria = (Categoria) entidadeDominio;

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categorias WHERE cat_id = ?");
		stmt.setLong(1, categoria.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return categoria;
	}
	
	public EntidadeDominio getEntidadeDominio(int idCategoria) throws SQLException {
		openConnection();
		Categoria categoria = new Categoria();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categorias WHERE cat_id = ?");
		stmt.setLong(1, idCategoria);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));
		}
		
		rs.close();
		stmt.close();
		connection.close();
		
		return categoria;
	}
	
	public List<EntidadeDominio> listar() throws SQLException {
		openConnection();
		List<EntidadeDominio> categorias = new	ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categorias");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Categoria categoria	= new Categoria();
			categoria.setId(rs.getInt("cat_id"));
			categoria.setNome(rs.getString("cat_nome"));

			categorias.add(categoria);
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return categorias;
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		return false;
	}


}
