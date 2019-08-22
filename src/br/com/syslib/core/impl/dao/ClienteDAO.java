package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.EntidadeDominio;

public class ClienteDAO extends AbstractJdbcDAO {
	
	
	public ClienteDAO() {
		super("cliente","cli_usu_id");
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
	            
				  sqlCliente.append("INSERT INTO CLIENTE(cli_usu_id,cli_ranking,cli_status,cli_genero,cli_data_nasc,cli_cpf,cli_dtCadastro)");
				  sqlCliente.append("VALUES(?,?,?,?,?,?,sysdate())");
			
            pst1 = connection.prepareStatement(sqlCliente.toString());
			
            pst1.setInt(1, idCliente);
            pst1.setString(2, cliente.getRanking());
            pst1.setString(3, cliente.getStatusCliente());            
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
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		Cliente cliente = (Cliente) entidade;
		int idCliente;
		
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
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
