package br.com.syslib.core.impl.negocio;

import java.sql.SQLException;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.EnderecoDAO;
import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;

public class ValidadorPreferenciaEnd implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		boolean resultado = false;
        EnderecoDAO dao = new EnderecoDAO();  
        Endereco end = (Endereco) entidade;
        int idCliente = end.getIdUsuario();
        
        try {
            resultado = dao.verificarPrefEnd(entidade,idCliente);
        } catch (SQLException ex) {
             return "Erro ao consultar";
        }
        if (!resultado) {
            return null;
        }
        return "O cadastro ja tem um endereço preferencial!";
    }
}
