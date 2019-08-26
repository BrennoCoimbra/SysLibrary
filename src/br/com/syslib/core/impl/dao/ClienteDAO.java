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
import br.com.syslib.enuns.Estados;
import br.com.syslib.enuns.Genero;
import br.com.syslib.enuns.TipoLogradouro;
import br.com.syslib.enuns.TipoTelefone;
import br.com.syslib.enuns.TipoUsuario;

public class ClienteDAO extends AbstractJdbcDAO {

	public ClienteDAO() {
		super("cliente", "cli_usu_id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		
		Cliente cliente = (Cliente) entidade;
		int idCliente;

		try {
			connection.setAutoCommit(false);
			StringBuilder sqlUsuario = new StringBuilder();
			StringBuilder sqlCliente = new StringBuilder();
			StringBuilder sqlTelefone = new StringBuilder();
			

			sqlUsuario.append("INSERT INTO USUARIO(us_nome,us_email,us_senha,us_tipoUsuario_id,us_dtCadastro)");
			sqlUsuario.append("VALUES(?,?,?,?,sysdate())");

			pst = connection.prepareStatement(sqlUsuario.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setString(3, cliente.getSenha());
			pst.setInt(4, cliente.getTipoUsuario().getCodigo());

			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {

				idCliente = rs.getInt(1);

				sqlCliente.append(
						"INSERT INTO CLIENTE(cli_usu_id,cli_ranking,cli_status,cli_genero,cli_data_nasc,cli_cpf,cli_dtCadastro)");
				sqlCliente.append("VALUES(?,?,?,?,?,?,sysdate())");

				pst1 = connection.prepareStatement(sqlCliente.toString());

				pst1.setInt(1, idCliente);
				pst1.setString(2, cliente.getRanking());
				pst1.setBoolean(3, cliente.getAtivo());
				pst1.setInt(4, cliente.getGenero().getCodigo());
				pst1.setString(5, cliente.getDataNasc());
				pst1.setString(6, cliente.getCpf());
				pst1.execute();
				pst1.close();

				sqlTelefone.append("INSERT INTO CLIENTE_TELEFONE(tel_id,tel_tipo,tel_ddd,tel_numero,tel_dtCadastro)");
				sqlTelefone.append("VALUES(?,?,?,?,sysdate())");

				pst2 = connection.prepareStatement(sqlTelefone.toString());
				pst2.setInt(1, idCliente);
				pst2.setInt(2, cliente.getTelefone().getTpTelefone().getCodigo());
				pst2.setString(3, cliente.getTelefone().getTelDDD());
				pst2.setString(4, cliente.getTelefone().getNumTel());
				pst2.execute();
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
		Cliente cliente = (Cliente) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sqlUsuario = new StringBuilder();
			StringBuilder sqlCliente = new StringBuilder();
			StringBuilder sqlTelefone = new StringBuilder();

			sqlUsuario.append("UPDATE USUARIO SET us_nome = ? ,us_email = ?");
			sqlUsuario.append("WHERE us_id = ?");

			pst = connection.prepareStatement(sqlUsuario.toString());
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getEmail());
			pst.setInt(3, cliente.getId());
			pst.executeUpdate();

			sqlCliente.append("UPDATE CLIENTE SET cli_genero = ?, cli_data_nasc = ?, cli_cpf = ?");
			sqlCliente.append("WHERE cli_usu_id = ?");

			pst1 = connection.prepareStatement(sqlCliente.toString());

			pst1.setInt(1, cliente.getGenero().getCodigo());
			pst1.setString(2, cliente.getDataNasc());
			pst1.setString(3, cliente.getCpf());
			pst1.setInt(4, cliente.getId());
			pst1.execute();
			pst1.close();

			sqlTelefone.append("UPDATE CLIENTE_TELEFONE SET tel_tipo = ?,tel_ddd = ?,tel_numero = ?");
			sqlTelefone.append("WHERE tel_id = ?");

			pst2 = connection.prepareStatement(sqlTelefone.toString());
			pst2.setInt(1, cliente.getTelefone().getTpTelefone().getCodigo());
			pst2.setString(2, cliente.getTelefone().getTelDDD());
			pst2.setString(3, cliente.getTelefone().getNumTel());
			pst2.setInt(4, cliente.getId());

			pst2.execute();
			pst2.close();

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
		openConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Cliente cliente = (Cliente) entidade;
		
		if (cliente.getId() == null) {
			try {
				if (cliente.getCpf() != null || cliente.getEmail() != null) {
					StringBuilder sql = new StringBuilder();
					sql.append("SELECT * FROM CLIENTE T1 INNER JOIN USUARIO T2 ON T2.us_id = T1.cli_usu_id"
							+ " WHERE cli_cpf = ? OR us_email = ?");
					pst = connection.prepareStatement(sql.toString());
					pst.setString(1, cliente.getCpf());
					pst.setString(2, cliente.getEmail());
					rs = pst.executeQuery();
				}

				while (rs.next()) {
					connection.close();
					return true;

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
		} else {
			return false;
		}
		return false;

	}
	
	public List<EntidadeDominio> visualizar(String filtro) throws SQLException {
		openConnection();
	
		List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM cliente T1 INNER JOIN usuario T2 ON T2.us_id = T1.cli_usu_id "
				+ "INNER JOIN endereco T3 ON T3.end_us_id = T1.cli_usu_id "
				+ "INNER JOIN cartaocredito T4 ON T4.cartao_idUsuario = T1.cli_usu_id "
				+ "INNER JOIN cliente_telefone T5 ON T5.tel_id = T1.cli_usu_id "
				+ "WHERE T3.end_pref = 1");
		if (!filtro.equals("")) {
			sql.append(" AND T2.us_nome LIKE '%" + filtro + "%'");
			sql.append(" OR T2.us_email LIKE '%" + filtro + "%'");
			sql.append(" OR T1.cli_status LIKE '%" + filtro + "%'");
			sql.append(" OR T1.cli_ranking LIKE '%" + filtro + "%'");
			sql.append(" OR T1.cli_genero LIKE '%" + filtro + "%'");
			sql.append(" OR T1.cli_data_nasc LIKE '%" + filtro + "%'");
			sql.append(" OR T1.cli_data_nasc LIKE '%" + filtro + "%'");
			sql.append(" OR T1.cli_cpf LIKE '%" + filtro + "%'");
			sql.append(" OR T5.tel_tipo LIKE '%" + filtro + "%'");
			sql.append(" OR T5.tel_numero LIKE '%" + filtro + "%'");
			sql.append(" OR T5.tel_ddd LIKE '%" + filtro + "%'");
		}
		
		PreparedStatement stmt = connection.prepareStatement(sql.toString());
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			int id = rs.getInt("T1.cli_usu_id");
			String nome = rs.getString("T2.us_nome");
			String email = rs.getString("T2.us_email");
			int status = rs.getInt("T1.cli_status");
			String ranking = rs.getString("T1.cli_ranking");
			int genero = rs.getInt("T1.cli_genero");
			String dtNasc = rs.getString("T1.cli_data_nasc");
			String cpf = rs.getString("T1.cli_cpf");
			int tpTelefone = rs.getInt("T5.tel_tipo");
			String numeroTel = rs.getString("T5.tel_numero");
			String ddd = rs.getString("T5.tel_ddd");
			String tpLog = rs.getString("T3.end_tpLog");
			String log = rs.getString("T3.end_logrd");
			int num = rs.getInt("T3.end_num");
			String bairro = rs.getString("T3.end_bairro");
			String estado = rs.getString("T3.end_estado");
			String cidade = rs.getString("T3.end_cidade");
			Boolean pref = rs.getBoolean("T3.end_pref");
			String pais = rs.getString("T3.end_pais");
			
			Cliente cliente	= new Cliente();
			cliente.setId(id);
			cliente.setNome(nome);
			cliente.setEmail(email);
			cliente.setAtivo((status == 1) ? true : false);
			cliente.setRanking(ranking);
			for(Genero gen : Genero.values()) {
				if(gen.getCodigo() == genero) {
					cliente.setGenero(gen);
				}
			}
			cliente.setDataNasc(dtNasc);
			cliente.setCpf(cpf);
			for(TipoTelefone tpTel : TipoTelefone.values()) {
				if(tpTel.getCodigo() == tpTelefone) {
					cliente.getTelefone().setTpTelefone(tpTel);					
				}
			}
			cliente.getTelefone().setNumTel(numeroTel);
			cliente.getTelefone().setTelDDD(ddd);
			cliente.getEndereco().setLogradouro(log);
			for(TipoLogradouro tLog : TipoLogradouro.values()) {
				if(tLog.getDescricao().contentEquals(tpLog)) {
					cliente.getEndereco().setTpLogrdo(tLog);
					
				}
			}
			cliente.getEndereco().setNumero(num);
			cliente.getEndereco().setBairro(bairro);
			cliente.getEndereco().setCidade(cidade);
			cliente.getEndereco().setPais(pais);
			cliente.getEndereco().setPreferencial(pref);
			for(Estados es : Estados.values()) {
				if(es.getDescricao().contentEquals(estado)) {
					cliente.getEndereco().setEstados(es);
				}
			}
			
			
			
			
			
			clientes.add(cliente);
		}

		rs.close();
		stmt.close();
		connection.close();
		
		return clientes;
	}
	
	public List<EntidadeDominio> visualizar() throws SQLException {
		return visualizar("");
	}
	
	public EntidadeDominio getEntidadeDominio(int idCliente) throws SQLException {
		PreparedStatement pst = null;
		String sql = null;
		
		Cliente cliente = new Cliente();
		
		
		sql = "SELECT * FROM cliente T1 INNER JOIN usuario T2 ON T2.us_id = T1.cli_usu_id  WHERE T1.cli_usu_id =?";
		
		
			openConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, idCliente);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			
				int id = rs.getInt("T1.cli_usu_id");
				String nome = rs.getString("T2.us_nome");
				String email = rs.getString("T2.us_email");
				int status = rs.getInt("T1.cli_status");
				String ranking = rs.getString("T1.cli_ranking");
				int genero = rs.getInt("T1.cli_genero");
				String dtNasc = rs.getString("T1.cli_data_nasc");
				String cpf = rs.getString("T1.cli_cpf");
			
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setEmail(email);
				cliente.setAtivo((status == 1) ? true : false);
				cliente.setRanking(ranking);
				for(Genero gen : Genero.values()) {
					if(gen.getCodigo() == genero) {
						cliente.setGenero(gen);
					}
				}
				cliente.setDataNasc(dtNasc);
				cliente.setCpf(cpf);
		}
			

			
			rs.close();
			pst.close();
			return cliente;
							
		}
	
	public EntidadeDominio getEntidadeDominioCliente(EntidadeDominio entidade) throws SQLException {
		PreparedStatement pst = null;
		String sql = null;		
		Cliente cliente = new Cliente();
		
		
		sql = "SELECT * FROM cliente T1 INNER JOIN usuario T2 ON T2.us_id = T1.cli_usu_id  WHERE T1.cli_usu_id =?";
		
		
			openConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, cliente.getId());
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
			
				int id = rs.getInt("T1.cli_usu_id");
				String nome = rs.getString("T2.us_nome");
				String email = rs.getString("T2.us_email");
				int status = rs.getInt("T1.cli_status");
				String ranking = rs.getString("T1.cli_ranking");
				int genero = rs.getInt("T1.cli_genero");
				String dtNasc = rs.getString("T1.cli_data_nasc");
				String cpf = rs.getString("T1.cli_cpf");
				
				
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setEmail(email);
				cliente.setAtivo((status == 1) ? true : false);
				cliente.setRanking(ranking);
				for(Genero gen : Genero.values()) {
					if(gen.getCodigo() == genero) {
						cliente.setGenero(gen);
					}
				}
				cliente.setDataNasc(dtNasc);
				cliente.setCpf(cpf);
		}
			

			
			rs.close();
			pst.close();
			return cliente;
							
		}

	@Override
	public void excluir(EntidadeDominio entidade) {
		openConnection();
		PreparedStatement pst = null;
		StringBuilder sb;

		try {
			connection.setAutoCommit(false);

			sb = new StringBuilder();
			sb.append("UPDATE cliente SET cli_status = 0 WHERE cli_usu_id = ?");
			pst = connection.prepareStatement(sb.toString());
			pst.setInt(1, entidade.getId());
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
				if (ctrlTransaction)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
