package br.com.syslib.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;

public class CupomDAO extends AbstractJdbcDAO {
	
	public CupomDAO() {
		super("cupom_troca", "cupom_id");
			
		}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {


	}

	@Override
	public boolean verificarCadastro(EntidadeDominio entidade) throws SQLException {
		
		return false;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		 openConnection();
	     List<EntidadeDominio> cupons = new ArrayList<EntidadeDominio>();
	     Cupom cup = (Cupom) entidade;
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     
	        if (!cup.getCupom().isEmpty()) {
	        	
	        } else { 
	        
	       	StringBuilder sql = new StringBuilder();
			sql.append("Select * from  cupom_troca where cupom_cod = ? and cupom_cli_id = ? ");
			pst = connection.prepareStatement(sql.toString());
            pst.setString(1, cup.getNomeCupom());
            pst.setInt(2, cup.getIdCupomCliente());
            rs = pst.executeQuery();
            while (rs.next()) {
                Cupom cupom = new Cupom();
                cupom.setStatusCupom(rs.getString("cupom_status"));
                cupom.setValorCupom(rs.getDouble("cupom_valor"));
                cupom.setNomeCupom(rs.getString("cupom_cod"));
                cupom.setDataCupom(rs.getString("cupom_data"));
                cupom.setIdPedido(rs.getInt("cupom_pedido_id"));
                cupom.setIdCupom(rs.getInt("cupom_id"));
                cupom.setIdCupomCliente(rs.getInt("cupom_cli_id"));
                cupons.add(cupom);
            }
        }

        connection.close();
        return cupons;
    
		
	}

}
