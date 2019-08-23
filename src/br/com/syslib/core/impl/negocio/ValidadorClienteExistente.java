package br.com.syslib.core.impl.negocio;

import java.sql.SQLException;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.ClienteDAO;
import br.com.syslib.dominio.EntidadeDominio;

public class ValidadorClienteExistente implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		boolean resultado = false;
        ClienteDAO dao = new ClienteDAO();      
        
        try {
            resultado = dao.verificarCadastro(entidade);
        } catch (SQLException ex) {
             return "Erro ao consultar";
        }
        if (!resultado) {
            return null;
        }
        return "O cadastro ja existe!";
    }
}
