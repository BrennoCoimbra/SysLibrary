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
       
        try {
            usuarios = dao.consultar(entidade);
        } catch (SQLException ex) {
            return "Erro ao verificar existência!";
        }
        if (usuarios.size()>0){            
            Usuario user = (Usuario) entidade;
            Usuario userBD = (Usuario) usuarios.get(0);            
            if (user.getEmail().equals(userBD.getEmail())) {
               
                if (user.getSenha().equals(userBD.getSenha())) {
                    user.setTipoUsuario(userBD.getTipoUsuario());
                    user.setId(userBD.getId());             
                   return null; 
                   
                }
            }
        }
        return "Usuário ou senha incorretos!";

    }
}
