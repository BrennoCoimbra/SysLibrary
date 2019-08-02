package br.com.syslib.core.impl.negocio;

import java.util.regex.Pattern;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;

public class ValidarCPF implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if (entidade instanceof Usuario) {
			Usuario usuario = (Usuario) entidade;
			String cpf = usuario.getCpf();

			if (cpf == null)
				return "CPF não pode ser nulo!";
			if (cpf.trim().equals(""))
				return "CPF deve ser preenchido!";
			if (cpf.trim() != null && cpf.length() !=11)
				return "CPF deve conter 11 caracteres";
			
		    Pattern p2 = Pattern.compile ("[A-Z]");
		    if (  p2.matcher (cpf).find()) 
		    	return "CPF somente digitos";
		    
		    Pattern p3 = Pattern.compile ("[a-z]");
		    if (  p3.matcher (cpf).find()) 
		    	return "CPF somente digitos";
		    
		    Pattern p4 = Pattern.compile ("[0-9]");
		    if ( ! p4.matcher (cpf).find()) 
		    	return "CPF somente digitos";
		    
		    Pattern p5 = Pattern.compile ("[-+*/=%@$#]");
		    if (  p5.matcher (cpf).find()) 
		    	return "CPF somente digitos";
			
		}	else {
				return "CPF não pode ser válidado, pois entidade não é de um tipo válido!";
			}
		
		return null;
	}
	
	

}
