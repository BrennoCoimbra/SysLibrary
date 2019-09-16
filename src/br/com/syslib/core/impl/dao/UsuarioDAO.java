package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;
import br.com.syslib.enuns.Genero;
import br.com.syslib.enuns.TipoTelefone;
import br.com.syslib.enuns.TipoUsuario;

public class UsuarioDAO extends AbstractJdbcDAO {
	
	public UsuarioDAO() {
		super("usuario", "us_id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Usuario usuario = (Usuario) entidade;
		
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			
			sql.append("INSERT INTO USUARIO(us_nome,us_email,us_senha,us_tipoUsuario_id,us_dtCadastro)");
			
			sql.append("VALUES(?,?,?,?,sysdate())");
			
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setString(3, usuario.getSenha());	
			pst.setInt(4, usuario.getTipoUsuario().getCodigo());
						
			
			
			pst.executeUpdate();
	
			connection.commit();
			
		  }	catch (SQLException e) {
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
		Usuario usuario = (Usuario) entidade;
		
		
		try {
			connection.setAutoCommit(false);
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE usuario SET us_senha = ?, us_dtCadastro = sysdate() WHERE us_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getSenha1());
			pst.setInt(2, usuario.getId());
		
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
		openConnection();
		PreparedStatement pst = null;
		List<EntidadeDominio> usuarios = new ArrayList<EntidadeDominio>();
		ResultSet rs = null;
		
		Usuario usuario = (Usuario) entidade;
		
		//if ("ADMIN".equals(usuario.getTipoUsuario()) || usuario.getTipoUsuario()==null) {
			
	if (usuario.getTipoUsuario() == TipoUsuario.ADMIN || usuario.getTipoUsuario()==null) {	
		try {
			
			if(usuario.getId() != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM usuario WHERE us_id = ?");
				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, usuario.getId());
				 rs = pst.executeQuery();
			} else if(usuario.getEmail() != null) {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM usuario WHERE us_email = ?");
				pst = connection.prepareStatement(sql.toString());
				pst.setString(1, usuario.getEmail());
				 rs = pst.executeQuery();
			}
			

			
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setId(Integer.parseInt(rs.getString("us_id")));
				usuario.setNome(rs.getString("us_nome"));
				usuario.setEmail(rs.getString("us_email"));
				usuario.setSenha(rs.getString("us_senha"));				
				usuario.setTipoUsuario(TipoUsuario.getTipoUsuario(Integer.parseInt(rs.getString("us_tipoUsuario_id"))));
				usuario.setDtCadastro(rs.getDate("us_dtCadastro"));
				int status = rs.getInt("us_ativo");
				if(status ==1) {
				usuario.setAtivo(true);
				} else {
				usuario.setAtivo(false);	
				}
				usuarios.add(usuario);
			}
			return usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return usuarios;
	} else {
		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
        
        
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM USUARIO T0 INNER JOIN CLIENTE T1 ON T1.cli_usu_id = T0.us_id "
					+ " INNER JOIN CLIENTE_TELEFONE T2 ON T2.tel_id = T1.cli_usu_id WHERE T0.us_email = ? AND BINARY T0.us_senha = ?");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getEmail());
			pst.setString(2, usuario.getSenha());
			
			 rs = pst.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(Integer.parseInt(rs.getString("cli_usu_id")));
				cliente.setNome(rs.getString("us_nome"));
				cliente.setEmail(rs.getString("us_email"));
				cliente.setSenha(rs.getString("us_senha"));	
				cliente.setCpf(rs.getString("cli_cpf"));
				cliente.setTipoUsuario(TipoUsuario.getTipoUsuario(Integer.parseInt(rs.getString("us_tipoUsuario_id"))));
				cliente.setDtCadastro(rs.getDate("us_dtCadastro"));
				cliente.setDataNasc(rs.getString("cli_data_nasc"));
				cliente.getTelefone().setTelDDD(rs.getString("tel_ddd"));
				cliente.getTelefone().setNumTel(rs.getString("tel_numero"));
				cliente.setAtivo(rs.getBoolean("cli_status"));
				int tpTel = rs.getInt("tel_tipo");				
				
				for (TipoTelefone tpTelBD : TipoTelefone.values()) {
					if (tpTelBD.getCodigo() == tpTel) {
						cliente.getTelefone().setTpTelefone(tpTelBD);
					}
				}
				int gen = rs.getInt("cli_genero");
				for (Genero genBD : Genero.values()) {
					if (genBD.getCodigo() == gen) {
						cliente.setGenero(genBD);
						
					}
				}
				
				
				
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return clientes;
		
	}
		
		
		
	}
	
	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		return ctrlTransaction;
		
	}
	
	public List<EntidadeDominio> getEntidadeDominio(int idUsuario) throws SQLException {
		openConnection();
		PreparedStatement pst;
		List<EntidadeDominio> usuarios = new	ArrayList<EntidadeDominio>();
		
		Usuario usuario = null;
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM usuario");
		sql.append(" WHERE us_id = ?");
		
		pst = connection.prepareStatement(sql.toString());
		pst.setInt(1, idUsuario);
		
		ResultSet rs = pst.executeQuery();
		
		while (rs.next()) {			
			int id = rs.getInt("us_id");
			String nome = rs.getString("us_nome");
						
			usuario = new Usuario();
			usuario.setId(id);
			usuario.setNome(nome);
			
						
			usuarios.add(usuario);
		}


		rs.close();
		pst.close();
		connection.close();
		
		return usuarios;
	}
}
