package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;

public class ValidadorDadosObrigatoriosLogin implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Usuario){
			Usuario usuario = (Usuario) entidade;
			
			String email = usuario.getEmail();
			String senha = usuario.getSenha();
			
			if(email == null || senha == null){
				return "E-mail e Senha são de preenchimento obrigatório!";
			}
			
			if(email.trim().equals("") || senha.trim().equals(""))
				return "E-mail e Senha são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um usuário!";
		}
		return null;
	}
}