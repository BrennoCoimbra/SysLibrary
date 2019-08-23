package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.syslib.dominio.Autor;
import br.com.syslib.dominio.Categoria;
import br.com.syslib.dominio.Editora;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Livro;
import br.com.syslib.dominio.LivroAutor;
import br.com.syslib.dominio.LivroCategoria;
import br.com.syslib.enuns.Precificacoes;

public class LivroDAO extends AbstractJdbcDAO {

	public LivroDAO() {
		super("livros", "liv_id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		Livro livro = (Livro) entidade;
		int idLivro;

		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO LIVROS(liv_titulo,liv_ano,liv_edicao,liv_ISBN,liv_num_pag,"
					+ "liv_sinopse,liv_altura,liv_largura,liv_peso,liv_profundidade,liv_codbarras,"
					+ "liv_isativo,liv_motivo,liv_editora,liv_precificacao,liv_dtCadastro)");

			sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate())");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, livro.getTitulo());
			pst.setString(2, livro.getAno());
			pst.setString(3, livro.getEdicao());
			pst.setString(4, livro.getISBN());
			pst.setInt(5, livro.getNumPaginas());
			pst.setString(6, livro.getSinopse());
			pst.setDouble(7, livro.getAltura());
			pst.setDouble(8, livro.getLargura());
			pst.setDouble(9, livro.getPeso());
			pst.setDouble(10, livro.getProfundidade());
			pst.setString(11, livro.getCodBarras());
			pst.setBoolean(12, livro.getAtivo());
			pst.setString(13, livro.getMotivo());
			pst.setInt(14, livro.getEditora().getId());
			pst.setInt(15, livro.getPreficacao().getCodigo());

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next() && livro.getAutores().size() > 0) {
				idLivro = rs.getInt(1);
				sql = new StringBuilder();
				sql.append("INSERT INTO livro_autor (lv_idLivro,lv_idAutor,dtCadastro) VALUES");
				boolean isSecondOrMore = false;

				for (Autor autor : livro.getAutores()) {
					if (isSecondOrMore) {
						sql.append(",");
					}

					sql.append(" (" + idLivro + ", " + autor.getId() + "," + "sysdate()" + ")");
					isSecondOrMore = true;
				}
				pst1 = connection.prepareStatement(sql.toString());
				pst1.executeUpdate();
				pst1.close();

				sql = new StringBuilder();
				sql.append("INSERT INTO livro_categoria (lc_idLivro,lc_idCategoria, dtCadastro) VALUES");
				isSecondOrMore = false;

				for (Categoria categoria : livro.getCategorias()) {
					if (isSecondOrMore) {
						sql.append(",");
					}
					sql.append(" (" + idLivro + ", " + categoria.getId() + "," + "sysdate()" + ")");
					isSecondOrMore = true;
				}

				pst2 = connection.prepareStatement(sql.toString());
				pst2.executeUpdate();
				pst2.close();

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
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;


		Livro livro = (Livro) entidade;

		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE livros SET liv_isativo = ?, liv_motivo = ?, liv_ISBN = ?, liv_codbarras = ?, liv_titulo = ?, liv_ano = ?, liv_edicao = ?, liv_num_pag = ?, " + 
					"liv_editora = ?, liv_sinopse = ?, liv_altura = ?, liv_largura = ?, liv_peso = ?, liv_profundidade = ?, liv_precificacao = ?, liv_dtCadastro = sysdate() "); 
	
			sql.append("WHERE liv_id=?");

			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, (livro.getAtivo() ? 1 : 0));
			pst.setString(2, livro.getMotivo());
			pst.setString(3, livro.getISBN());
			pst.setString(4, livro.getCodBarras());
			pst.setString(5, livro.getTitulo());
			pst.setString(6, livro.getAno());
			pst.setString(7, livro.getEdicao());
			pst.setInt(8, livro.getNumPaginas());
			pst.setInt(9, livro.getEditora().getId());
			pst.setString(10, livro.getSinopse());
			pst.setDouble(11, livro.getAltura());
			pst.setDouble(12, livro.getLargura());
			pst.setDouble(13, livro.getPeso());
			pst.setDouble(14, livro.getProfundidade());
			pst.setInt(15, livro.getPreficacao().getCodigo());
			pst.setInt(16, livro.getId());
			
			pst.executeUpdate();
			connection.commit();
			
			if(livro.getCategorias() != null) {
				StringBuilder sql1 = new StringBuilder();
				sql1 = new StringBuilder();
				sql1.append("DELETE FROM livro_categoria WHERE lc_idLivro =?");
				pst1 = connection.prepareStatement(sql1.toString());
				
				pst1.setInt(1, livro.getId());
				
				pst1.executeUpdate();
				connection.commit();
				pst1.close();
				
				for(Categoria categoria:livro.getCategorias()) {
					try {
					sql = new StringBuilder();
					sql.append("INSERT INTO livro_categoria(lc_idLivro, lc_idCategoria, dtCadastro) ");
					sql.append("VALUES (?,?,sysdate())");
					
					pst = connection.prepareStatement(sql.toString());
					
					pst.setInt(1, livro.getId());
					pst.setInt(2, categoria.getId());
					
					pst.executeUpdate();
					
					connection.commit();
					
					}catch(Exception e) {
						
					}
				}
		}
			
			if(livro.getAutores() != null) {
				StringBuilder sql2 = new StringBuilder();
				sql2 = new StringBuilder();
				sql2.append("DELETE FROM livro_autor WHERE lv_idLivro =?");
				pst2 = connection.prepareStatement(sql2.toString());
				
				pst2.setInt(1, livro.getId());
				
				pst2.executeUpdate();
				connection.commit();
				pst2.close();
				
				for(Autor autor:livro.getAutores()) {
					try {
					sql = new StringBuilder();
					sql.append("INSERT INTO livro_autor(lv_idLivro, lv_idAutor, dtCadastro) ");
					sql.append("VALUES (?,?,sysdate())");
					
					pst = connection.prepareStatement(sql.toString());
					
					pst.setInt(1, livro.getId());
					pst.setInt(2, autor.getId());
					
					pst.executeUpdate();
					
					connection.commit();
					
					}catch(Exception e) {
						
					}
				}
		}
			
			
			
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
		PreparedStatement pst = null;

		Livro livro = (Livro) entidade;
		String sql = null;

		if (livro.getTitulo() == null) {
			livro.setTitulo("");
		}

		if (livro.getId() == null && livro.getTitulo().equals("")) {
			sql = "SELECT * FROM livros";
		} else if (livro.getId() != null && livro.getTitulo().equals("")) {
			sql = "SELECT * FROM livros WHERE liv_id=?";
		} else if (livro.getId() == null && !livro.getTitulo().equals("")) {
			sql = "SELECT * FROM livros WHERE titulo like ?";

		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			if (livro.getId() != null && livro.getTitulo().equals("")) {
				pst.setInt(1, livro.getId());
			} else if (livro.getId() == null && !livro.getTitulo().equals("")) {
				pst.setString(1, "%" + livro.getTitulo() + "%");
			}

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				int precific = rs.getInt("liv_precificacao");
				Livro liv = new Livro();
//				Autor a = new Autor();
//				Categoria c = new Categoria();
				Editora e = new Editora();
				liv.setId(rs.getInt("liv_id"));
				liv.setISBN(rs.getString("liv_ISBN"));
				liv.setCodBarras(rs.getString("liv_codbarras"));
				liv.setTitulo(rs.getString("liv_titulo"));
				liv.setNumPaginas(rs.getInt("liv_num_pag"));
				// p.setQuantidade(rs.getInt("liv_quantidade"));
				// a.setAutores(rs.getArray("aut_nome"));
				// c.setCatId(rs.getInt("liv_cat"));
				liv.setAno(rs.getString("liv_ano"));
				e.setId(rs.getInt("liv_editora"));
				liv.setSinopse(rs.getString("liv_sinopse"));
				liv.setAltura(rs.getDouble("liv_altura"));
				liv.setLargura(rs.getDouble("liv_largura"));
				liv.setPeso(rs.getDouble("liv_peso"));
				liv.setProfundidade(rs.getDouble("liv_profundidade"));
				for(Precificacoes precf : Precificacoes.values()) {
					if(precf.getCodigo() == precific) {
						liv.setPreficacao(precf);
					}					
				}	
				java.sql.Date dtCadastroEmLong = rs.getDate("liv_dtCadastro");
				Calendar dt_teste = Calendar.getInstance();
				dt_teste.set(dtCadastroEmLong.getYear(), dtCadastroEmLong.getMonth(), dtCadastroEmLong.getDate());
				liv.setDtCadastro(dt_teste.getTime());
				liv.setAtivo(rs.getBoolean("liv_isativo"));
				livros.add(liv);
			}
			return livros;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<EntidadeDominio> visualizar(String filtro) throws SQLException {
		openConnection();
		List<Autor> autores;
		List<Categoria> categorias;
		List<EntidadeDominio> livros = new	ArrayList<EntidadeDominio>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM livros");
		if (!filtro.equals("")) {
			sql.append(" WHERE liv_titulo LIKE '%" + filtro + "%'");
			sql.append(" OR liv_ISBN LIKE '%" + filtro + "%'");
			sql.append(" OR liv_ano LIKE '%" + filtro + "%'");
			sql.append(" OR liv_edicao LIKE '%" + filtro + "%'");
			sql.append(" OR liv_num_pag LIKE '%" + filtro + "%'");
			sql.append(" OR liv_sinopse LIKE '%" + filtro + "%'");
			sql.append(" OR liv_altura LIKE '%" + filtro + "%'");
			sql.append(" OR liv_largura LIKE '%" + filtro + "%'");
			sql.append(" OR liv_peso LIKE '%" + filtro + "%'");
			sql.append(" OR liv_profundidade LIKE '%" + filtro + "%'");
		}
		
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("liv_id");
			String titulo = rs.getString("liv_titulo");
			int idEditora = rs.getInt("liv_editora");
			int numPags = rs.getInt("liv_num_pag");
			int ativo = rs.getInt("liv_isativo");
			String ISBN = rs.getString("liv_ISBN");
			String motivo = rs.getString("liv_motivo");
			
			Editora editora = (Editora) new EditoraDAO().getEntidadeDominio(idEditora);
			List<EntidadeDominio> livrosAutores = new LivroAutorDAO().getEntidadeDominioLivro(id);
			List<EntidadeDominio> livrosCategorias = new LivroCategoriaDAO().getEntidadeDominioLivro(id);
			
			autores = new ArrayList<Autor>();
			for (EntidadeDominio ed : livrosAutores) {
				LivroAutor livroAutor = (LivroAutor) ed;
				autores.add(livroAutor.getAutor());
			}
			
			categorias = new ArrayList<Categoria>();
			for (EntidadeDominio ed : livrosCategorias) {
				LivroCategoria livroCategoria = (LivroCategoria) ed;
				categorias.add(livroCategoria.getCategoria());
			}
			
			Livro livro	= new Livro();
			livro.setId(id);
			livro.setTitulo(titulo);
			livro.setNumPaginas(numPags);
			livro.setISBN(ISBN);
			livro.setAtivo((ativo == 1) ? true : false);
			livro.setMotivo(motivo);
			livro.setEditora(editora);
			livro.setAutores(autores);
			livro.setCategorias(categorias);
			
			
			livros.add(livro);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return livros;
	}
	
	public List<EntidadeDominio> visualizar() throws SQLException {
		return visualizar("");
	}
	

	@Override
	public void excluir(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		StringBuilder sb;

		try {
			connection.setAutoCommit(false);

			sb = new StringBuilder();
			sb.append("DELETE FROM livro_categoria WHERE lc_idLivro = ?");
			pst = connection.prepareStatement(sb.toString());
			pst.setInt(1, entidade.getId());
			pst.executeUpdate();

			sb = new StringBuilder();
			sb.append("DELETE FROM livro_autor WHERE lv_idLivro = ?;");
			pst1 = connection.prepareStatement(sb.toString());
			pst1.setInt(1, entidade.getId());
			pst1.executeUpdate();

			sb = new StringBuilder();
			sb.append("DELETE FROM ");
			sb.append(table);
			sb.append(" WHERE ");
			sb.append(idTable);
			sb.append(" = ");
			sb.append("?;");
			pst2 = connection.prepareStatement(sb.toString());
			pst2.setInt(1, entidade.getId());
			pst2.executeUpdate();

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
				pst1.close();
				pst2.close();
				if (ctrlTransaction)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public EntidadeDominio getEntidadeDominio(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		String sql = null;

		Livro livro = (Livro) entidade;

		sql = "SELECT * FROM livros WHERE liv_id=?";

		try {

			openConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, livro.getId());

			ResultSet rs = pst.executeQuery();

			Livro liv = new Livro();
			Editora e = new Editora();
			int precific = rs.getInt("liv_precificacao");
			liv.setId(rs.getInt("liv_id"));
			liv.setISBN(rs.getString("liv_ISBN"));
			liv.setCodBarras(rs.getString("liv_codbarras"));
			liv.setTitulo(rs.getString("liv_titulo"));
			liv.setNumPaginas(rs.getInt("liv_num_pag"));
			// p.setQuantidade(rs.getInt("liv_quantidade"));
			// a.setAutores(rs.getArray("aut_nome"));
			// c.setCatId(rs.getInt("liv_cat"));
			liv.setAno(rs.getString("liv_ano"));
			e.setId(rs.getInt("liv_editora"));
			liv.setSinopse(rs.getString("liv_sinopse"));
			liv.setAltura(rs.getDouble("liv_altura"));
			liv.setLargura(rs.getDouble("liv_largura"));
			liv.setPeso(rs.getDouble("liv_peso"));
			liv.setProfundidade(rs.getDouble("liv_profundidade"));
			for(Precificacoes precf : Precificacoes.values()) {
				if(precf.getCodigo() == precific) {
					liv.setPreficacao(precf);
				}					
			}	
			liv.setMotivo(rs.getString("liv_motivo"));
			java.sql.Date dtCadastroEmLong = rs.getDate("liv_dtCadastro");
			Calendar dt_teste = Calendar.getInstance();
			dt_teste.set(dtCadastroEmLong.getYear(), dtCadastroEmLong.getMonth(), dtCadastroEmLong.getDate());
			liv.setDtCadastro(dt_teste.getTime());
			liv.setAtivo(rs.getBoolean("liv_isativo"));

			return liv;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public EntidadeDominio getEntidadeDominio(int idLivro) throws SQLException {
		PreparedStatement pst = null;
		String sql = null;
		List<Autor> autores;
		List<Categoria> categorias;
		Livro liv = new Livro();
		
		
		sql = "SELECT * FROM livros WHERE liv_id=?";
		
		
			openConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, idLivro);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			
			int id = rs.getInt("liv_id");
			int idEditora = rs.getInt("liv_editora");
			int precific = rs.getInt("liv_precificacao");
			liv.setId(rs.getInt("liv_id"));
			liv.setISBN(rs.getString("liv_ISBN"));
			liv.setCodBarras(rs.getString("liv_codbarras"));
			liv.setEdicao(rs.getString("liv_edicao"));
			liv.setTitulo(rs.getString("liv_titulo"));
			liv.setNumPaginas(rs.getInt("liv_num_pag"));
			liv.setAno(rs.getString("liv_ano"));
			liv.setSinopse(rs.getString("liv_sinopse"));
			liv.setAltura(rs.getDouble("liv_altura"));
			liv.setLargura(rs.getDouble("liv_largura"));
			liv.setPeso(rs.getDouble("liv_peso"));
			liv.setProfundidade(rs.getDouble("liv_profundidade"));
			for(Precificacoes precf : Precificacoes.values()) {
				if(precf.getCodigo() == precific) {
					liv.setPreficacao(precf);
				}					
			}			
			liv.setMotivo(rs.getString("liv_motivo"));
			java.sql.Date dtCadastroEmLong = rs.getDate("liv_dtCadastro");
			Calendar dt_teste = Calendar.getInstance();
			dt_teste.set(dtCadastroEmLong.getYear(), dtCadastroEmLong.getMonth(), dtCadastroEmLong.getDate());
			liv.setDtCadastro(dt_teste.getTime());
			liv.setAtivo(rs.getBoolean("liv_isativo"));
			
			
			
			
			List<EntidadeDominio> livrosAutores = new LivroAutorDAO().getEntidadeDominioLivro(id);
			List<EntidadeDominio> livrosCategorias = new LivroCategoriaDAO().getEntidadeDominioLivro(id);
			
			Editora editora = (Editora) new EditoraDAO().getEntidadeDominio(idEditora);
				
			liv.setEditora(editora);

			autores = new ArrayList<Autor>();
			for (EntidadeDominio ed : livrosAutores) {
				LivroAutor livroAutor = (LivroAutor) ed;
				autores.add(livroAutor.getAutor());
			}
			
			liv.setAutores(autores);
			
			categorias = new ArrayList<Categoria>();
			for (EntidadeDominio ed : livrosCategorias) {
				LivroCategoria livroCategoria = (LivroCategoria) ed;
				categorias.add(livroCategoria.getCategoria());
			}
			
			liv.setCategorias(categorias);
		}
			

			
			rs.close();
			pst.close();
			return liv;
							
		}
	
	public EntidadeDominio buscarISBN(String isbn) throws SQLException {
		PreparedStatement pst = null;
		String sql = null;
		Livro liv = new Livro();
		
		
		sql = "SELECT * FROM livros WHERE liv_ISBN=?";
		
		
			openConnection();
			pst = connection.prepareStatement(sql);
			pst.setString(1, isbn);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			
			int id = rs.getInt("liv_id");
			int precific = rs.getInt("liv_precificacao");
			
			
			liv.setId(rs.getInt("liv_id"));
			liv.setIdLivro(rs.getInt("liv_id"));
			liv.setISBN(rs.getString("liv_ISBN"));
			liv.setTitulo(rs.getString("liv_titulo"));
			for(Precificacoes precf : Precificacoes.values()) {
				if(precf.getCodigo() == precific) {
					liv.setPreficacao(precf);
				}					
			}			

		}

			rs.close();
			pst.close();
			return liv;
							
		}
	
	public List<EntidadeDominio> listarAtivos() throws SQLException {
		openConnection();
		List<Autor> autores;
		List<Categoria> categorias;
		List<EntidadeDominio> livros = new	ArrayList<EntidadeDominio>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM livros WHERE liv_isativo = 1");
		
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("liv_id");
			String titulo = rs.getString("liv_titulo");
			int idEditora = rs.getInt("liv_editora");
			int numPags = rs.getInt("liv_num_pag");
			int ativo = rs.getInt("liv_isativo");
			String ISBN = rs.getString("liv_ISBN");
			String motivo = rs.getString("liv_motivo");
			
			Editora editora = (Editora) new EditoraDAO().getEntidadeDominio(idEditora);
			List<EntidadeDominio> livrosAutores = new LivroAutorDAO().getEntidadeDominioLivro(id);
			List<EntidadeDominio> livrosCategorias = new LivroCategoriaDAO().getEntidadeDominioLivro(id);
			
			autores = new ArrayList<Autor>();
			for (EntidadeDominio ed : livrosAutores) {
				LivroAutor livroAutor = (LivroAutor) ed;
				autores.add(livroAutor.getAutor());
			}
			
			categorias = new ArrayList<Categoria>();
			for (EntidadeDominio ed : livrosCategorias) {
				LivroCategoria livroCategoria = (LivroCategoria) ed;
				categorias.add(livroCategoria.getCategoria());
			}
			
			Livro livro	= new Livro();
			livro.setId(id);
			livro.setTitulo(titulo);
			livro.setNumPaginas(numPags);
			livro.setISBN(ISBN);
			livro.setAtivo((ativo == 1) ? true : false);
			livro.setMotivo(motivo);
			livro.setEditora(editora);
			livro.setAutores(autores);
			livro.setCategorias(categorias);
			
			livros.add(livro);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return livros;
	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	}
