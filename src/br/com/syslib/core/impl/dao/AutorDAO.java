package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Autor;
import br.com.syslib.dominio.EntidadeDominio;

public class AutorDAO extends AbstractJdbcDAO {

	public AutorDAO() {
	super("autores", "aut_id");
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
		PreparedStatement pst = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select * from autores order by 2;");
		try{
			openConnection();
			pst = connection.prepareStatement(sb.toString());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> autores = new ArrayList<>();
			Autor a = new Autor();
			while(rs.next()){
				a = new Autor();
				a.setId(rs.getInt("aut_id"));
				a.setNome(rs.getString("aut_nome"));
				autores.add(a);
			}
			return autores;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidadeDominio) throws SQLException {
		openConnection();
		Autor autor = (Autor) entidadeDominio;
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM autores WHERE aut_id = ?");
		stmt.setLong(1, autor.getId());;
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			autor.setId(rs.getInt("aut_id"));
			autor.setNome(rs.getString("aut_nome"));			
		}
		rs.close();
		stmt.close();
		connection.close();

		return autor;
	}

	public EntidadeDominio getEntidadeDominio(int idAutor) throws SQLException {
		openConnection();
		Autor autor = new Autor();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM autores WHERE aut_id = ?");
		stmt.setLong(1, idAutor);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			autor.setId(rs.getInt("aut_id"));
			autor.setNome(rs.getString("aut_nome"));
		}
		
		rs.close();
		stmt.close();
		connection.close();

		return autor;
}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
