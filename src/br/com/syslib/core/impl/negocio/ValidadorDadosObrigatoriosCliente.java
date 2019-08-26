package br.com.syslib.core.impl.negocio;

import java.util.regex.Pattern;

import br.com.syslib.core.IStrategy;
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
		String privacidade = cliente.getPrivacidade();
		String dataNas = cliente.getDataNasc();
		String ddd = cliente.getTelefone().getTelDDD();
		String numeroTel = cliente.getTelefone().getNumTel();
		Boolean status = cliente.getAtivo();
		String tptel = cliente.getTelefone().getTpTelefone().getDescricao();
		
		if(status == true) {
		
		if(cliente.getId() == null) {
		
			
			// Criterio 4:
			Pattern p4 = Pattern.compile("[A-Z]");
			if (!p4.matcher(nome).find())
				return "Nome deve conter apenas letras.\n";
			// Critério 5:
			Pattern p5 = Pattern.compile("[a-z]");
			if (!p5.matcher(nome).find())
				return "Nome deve conter apenas letras.\n";
		
		if (privacidade == null) 
			erros.append("Não aceitou a Politica de Privacidade.\n");
		
		if(tptel.equals("Residencial")) {
			Pattern p6 = Pattern.compile("[0-9]{8}");
			if (!p6.matcher(numeroTel).find())
				return "Para telefone residencial, apenas valores numericos e 8 digitos.\n";
		}
		
		if(tptel.equals("Celular")) {
			Pattern p6 = Pattern.compile("[0-9]{9}");
			if (!p6.matcher(numeroTel).find())
				return "Para telefone celular, apenas valores numericos e 9 digitos.\n";
		}
		
		
		if(nome == null || cpf == null || email == null || dataNas == null || ddd == null || numeroTel == null)
			erros.append("Nome, CPF, E-mail, Data Nascimento, DDD e Telefone são de preenchimento obrigatório!\n");
		if(nome.trim().equals("") || cpf.trim().equals("") || email.trim().equals("") || dataNas.trim().equals("") ||
				ddd.trim().equals("") || numeroTel.trim().equals(""))
			
			erros.append("Nome, CPF, E-mail, Data Nascimento, DDD e Telefone são de preenchimento obrigatório!\n");
			
		
		
		} else if (cliente.getId() != null) {
			
			
			if(tptel.equals("Residencial")) {
				Pattern p6 = Pattern.compile("[0-9]");
				if (p6.matcher(numeroTel).find() && numeroTel.length() != 8)
					return "Para telefone residencial, apenas valores numericos e 8 digitos.\n";
			}
			
			if(tptel.equals("Celular")) {
				Pattern p6 = Pattern.compile("[0-9]");
				if (p6.matcher(numeroTel).find() && numeroTel.length() != 9)
					return "Para telefone celular, apenas valores numericos e 9 digitos.\n";
			}
			
			// Criterio 4:
			Pattern p4 = Pattern.compile("[0-9]");
			if (p4.matcher(nome).find())
				return "Nome deve conter apenas letras.\n";
			// Critério 5:
			Pattern p5 = Pattern.compile("[-+*/=%@$#]");
			if (p5.matcher(nome).find())
				return "Nome deve conter apenas letras.\n";
		
		if(nome == null || cpf == null || email == null || dataNas == null || ddd == null || numeroTel == null)
			erros.append("Nome, CPF, E-mail, Data Nascimento, DDD e Telefone são de preenchimento obrigatório!\n");
		if(nome.trim().equals("") || cpf.trim().equals("") || email.trim().equals("") || dataNas.trim().equals("") ||
				ddd.trim().equals("") || numeroTel.trim().equals(""))
			
			erros.append("Nome, CPF, E-mail, Data Nascimento, DDD e Telefone são de preenchimento obrigatório!\n");
		}
		} else {
			return null;
		}
		if (erros.length() > 0)
			return erros.toString();
		} else {
		return "Deve ser registrado um usuário!";
			}
		return null;
		}

	}
