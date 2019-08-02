package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Categoria;
import br.com.syslib.dominio.Editora;
import br.com.syslib.dominio.EntidadeDominio;

public class EditoraDAO extends AbstractJdbcDAO {

	public EditoraDAO() {
		super("editoras", "edt_id");
		// TODO Auto-generated constructor stub
	}

	public List<EntidadeDominio> getLista() throws SQLException {
		
		PreparedStatement stmt = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from editoras;");
		try {
			openConnection();
			stmt = connection.prepareStatement(sb.toString());
			ResultSet rs = stmt.executeQuery();
			List<EntidadeDominio> categorias = new	ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Categoria categoria	= new Categoria();
				categoria.setId(rs.getInt("edt_id"));
				categoria.setNome(rs.getString("edt_nome"));

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
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

	}



	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from editoras order by 2;");
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> editoras = new ArrayList<>();
			Editora e = new Editora();
			while(rs.next()){
				e = new Editora();
				e.setId(rs.getInt("edt_id"));
				e.setNome(rs.getString("edt_nome"));		
				editoras.add(e);
			}
			return editoras;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Editora editora = (Editora) entidadeDominio;

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM editoras WHERE edt_id = ?");
		stmt.setLong(1, editora.getId());
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			editora.setId(rs.getInt("edt_id"));
			editora.setNome(rs.getString("edt_nome"));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return editora;
	}
	
	public EntidadeDominio getEntidadeDominio(int idEditora) throws SQLException {
		openConnection();
		Editora editora = new Editora();

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM editoras WHERE edt_id = ?");
		stmt.setInt(1, idEditora);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			editora.setId(rs.getInt("edt_id"));
			editora.setNome(rs.getString("edt_nome"));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return editora;
	}

}
