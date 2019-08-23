package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Autor;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.LivroAutor;

public class LivroAutorDAO extends AbstractJdbcDAO {

	public LivroAutorDAO() {
		super("livro_autor", "lv_id");
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
		LivroAutor livroAutor = (LivroAutor) entidadeDominio;
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM livro_autor WHERE lv_id = ?");
		stmt.setLong(1, livroAutor.getId());;
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("lv_id");
			int iLivro = rs.getInt("lv_idLivro");
			int iAutor = rs.getInt("lv_idAutor");
			
			Livro livro = (Livro) new LivroDAO().getEntidadeDominio(iLivro);
			Autor autor = (Autor) new AutorDAO().getEntidadeDominio(iAutor);
			
			livroAutor.setId(id);
			livroAutor.setLivro(livro);
			livroAutor.setAutor(autor);
		}
		rs.close();
		stmt.close();
		connection.close();

		return livroAutor;
	}
	
	public List<EntidadeDominio> getEntidadeDominioLivro(int idLivro) throws SQLException {
		openConnection();
		LivroAutor livroAutor;
		List<EntidadeDominio> livrosAutores = new ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM livro_autor WHERE lv_idLivro = ?");
		stmt.setInt(1, idLivro);
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("lv_id");
			int iAutor = rs.getInt("lv_idAutor");
			
			Autor autor = (Autor) new AutorDAO().getEntidadeDominio(iAutor);
			
			livroAutor = new LivroAutor();
			livroAutor.setId(id);
			livroAutor.setAutor(autor);
			
			livrosAutores.add(livroAutor);
		}
		
		rs.close();
		stmt.close();
		connection.close();
		
		return livrosAutores;
}
	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
