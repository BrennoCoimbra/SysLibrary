package br.com.syslib.core.impl.negocio;

import java.sql.SQLException;
import java.util.regex.Pattern;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.UsuarioDAO;
import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.EntidadeDominio;

public class ValidadorDadosObrigatoriosCliente implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		StringBuilder erros = new StringBuilder();

	
	if(entidade instanceof Cliente){
		Cliente cliente = (Cliente) entidade;
		
		String nome = cliente.getNome();
		String cpf = cliente.getCpf();
		String email = cliente.getEmail();
		String senha = cliente.getSenha();
		String senhaRepetida = cliente.getSenhaRepetida();
		String privacidade = cliente.getPrivacidade();
		
		// Criterio 1: 
	    if (senha.length() < 8) 
	    	return "Senha com 8 caracteres ou mais";
	    // Criterio 2: 
	    Pattern p2 = Pattern.compile ("[A-Z]");
	    if ( ! p2.matcher (senha).find()) 
	    	return "Senha tem que ter letras maiúsculas";
	    // Criterio 3: 
	    Pattern p3 = Pattern.compile ("[a-z]");
	    if ( ! p3.matcher (senha).find()) 
	    	return "Senha tem que ter letras minúsculas";
	    // Criterio 4: 
	    Pattern p4 = Pattern.compile ("[0-9]");
	    if ( ! p4.matcher (senha).find()) 
	    	return "Senha tem que ter dígitos";
	    // Critério 5: 
	    Pattern p5 = Pattern.compile ("[-+*/=%@$#]");
	    if ( ! p5.matcher (senha).find()) 
	    	return "Senha tem que ter um dos seguintes caracteres especiais: + - * / = %";
		
		if (!senha.equals(senhaRepetida) && privacidade == null) 
			erros.append("Senhas diferentes e Não aceitou a Politica de Privacidade.\n");
		 else if (!senha.equals(senhaRepetida)&& privacidade != null && privacidade.equals("on"))
			erros.append("Senhas diferentes.\n");
		else if (senha.equals(senhaRepetida) && privacidade == null)
			erros.append("Não aceitou a Politica de Privacidade.\n");
			
		try {
			if (new UsuarioDAO().cpfExiste(cpf))
				erros.append("Já existe cadastro para este CPF.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(nome == null || cpf == null || email == null || senha == null)
			erros.append("Nome, CPF, E-mail e Senha são de preenchimento obrigatório!\n");
		if(nome.trim().equals("") || cpf.trim().equals("") || email.trim().equals("") || senha.trim().equals(""))
			erros.append("Nome, CPF, E-mail e Senha são de preenchimento obrigatório!\n");
		
		if (erros.length() > 0)
			return erros.toString();
		} else {
		return "Deve ser registrado um usuário!";
			}
		return null;
		}

	}
