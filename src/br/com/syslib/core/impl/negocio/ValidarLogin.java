package br.com.syslib.core.impl.negocio;

import java.sql.SQLException;
import java.util.List;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.UsuarioDAO;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;

public class ValidarLogin implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {      
        
        List<EntidadeDominio> usuarios;         
        UsuarioDAO dao = new UsuarioDAO();
        GeradorHash hash = new GeradorHash();
       
        try {
            usuarios = dao.consultar(entidade);
        } catch (SQLException ex) {
            return "Erro ao verificar existência!";
        }
        if (usuarios.size()>0){            
            Usuario user = (Usuario) entidade;
            Usuario userBD = (Usuario) usuarios.get(0); 
            
           if(!userBD.getTipoUsuario().getDescriao().equals("admin")) { 
            if (user.getEmail().equals(userBD.getEmail())) {
            	/* aqui eu pego a senha q o usuario digitou e gero um hash dela e comparo com a senha de hash 
            	gravada no banco, se o hash gerado for diferente do hash do banco entao o usuario errou a senha.
            	se o hash gerado for igual ao do banco entao o usuario acertou a senha.
            	*/
            	user.setSenha(hash.senhaHash(user.getSenha()));
                if (user.getSenha().equals(userBD.getSenha())) {
                	user.setTipoUsuario(userBD.getTipoUsuario());
                    user.setId(userBD.getId());             
                   return null; 
                   
                }
            }
            
           }
           return null;
        }
        return "Usuário ou senha incorretos!";

    }
}
