package br.com.syslib.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Categoria;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.LivroCategoria;

public class LivroCategoriaDAO extends AbstractJdbcDAO {

	public LivroCategoriaDAO() {
		super("livro_categoria", "lc_id");
	}

	public LivroCategoriaDAO(Connection connection) {
		super(connection, "livro_categoria", "id");
		ctrlTransaction = false;
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
		LivroCategoria livroCategoria = (LivroCategoria) entidadeDominio;
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM livro_categoria WHERE lc_id = ?");
		stmt.setLong(1, livroCategoria.getId());
		;
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("lc_id");
			int iLivro = rs.getInt("lc_idLivro");
			int iCategoria = rs.getInt("lc_idCategoria");

			Livro livro = (Livro) new LivroDAO().getEntidadeDominio(iLivro);
			Categoria categoria = (Categoria) new CategoriaDAO().getEntidadeDominio(iCategoria);

			livroCategoria.setId(id);
			livroCategoria.setLivro(livro);
			livroCategoria.setCategoria(categoria);
		}

		rs.close();
		stmt.close();
		connection.close();

		return livroCategoria;
	}

	public List<EntidadeDominio> getEntidadeDominioLivro(int idLivro) throws SQLException {
		openConnection();
		LivroCategoria livroCategoria;
		List<EntidadeDominio> livrosCategorias = new ArrayList<EntidadeDominio>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM livro_categoria WHERE lc_idLivro = ?");
		stmt.setInt(1, idLivro);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("lc_id");
			int iCategoria = rs.getInt("lc_idCategoria");

			Categoria categoria = (Categoria) new CategoriaDAO().getEntidadeDominio(iCategoria);

			livroCategoria = new LivroCategoria();
			livroCategoria.setId(id);
			livroCategoria.setCategoria(categoria);

			livrosCategorias.add(livroCategoria);
		}

		rs.close();
		stmt.close();
		connection.close();

		return livrosCategorias;
	}

}
