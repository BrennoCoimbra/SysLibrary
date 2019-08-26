package br.com.syslib.core.impl.negocio;

import java.util.regex.Pattern;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;

public class ValidadorDadosObrigatoriosEndereco implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Endereco){
	      
			Endereco endereco = (Endereco) entidade;
						
			int idusuario = endereco.getIdUsuario();
			String descricao = endereco.getDescricao();
			String tpEnd = endereco.getTpEnd().getDescricao();
			String tpRes = endereco.getTpResid().getDescricao();
			String tpLog = endereco.getTpLogrdo().getDescricao();
			String logradouro = endereco.getLogradouro();
			int numero = endereco.getNumero();
			String bairro = endereco.getBairro();
			String cidade = endereco.getCidade();
			String estado = endereco.getEstados().getDescricao();
			String cep = endereco.getCep();
			String uf = endereco.getEstados().getDescricao();
			
			
		
			
			Pattern p4 = Pattern.compile("[0-9]{8}");
			if (!p4.matcher(cep).find())
				return "CEP invalido.\n";
			
			if(idusuario <= 0)
				return "Erro grave, IdUsuario não encontrado, avise o Desenvolvedor.";
				
			if(descricao == null || tpEnd == null || tpRes == null || tpLog == null || logradouro == null || numero <= 0 || bairro == null || cidade == null|| estado == null || cep == null || uf == null)
				return "Tipo de Endereço, Tipo de Residência, Tipo de logradouro, Logradouro, Número, Bairro, Cidade, Estado, CEP e UF são de preenchimento obrigatório!";
			
			if(tpLog.trim().equals("") || tpRes.trim().equals("") || tpEnd.trim().equals("") || descricao.trim().equals("") || logradouro.trim().equals("") || bairro.trim().equals("") || cidade.trim().equals("") || estado.trim().equals("") || cep.trim().equals("") || uf.trim().equals(""))
				return "Tipo de Endereço, Tipo de Residência, Tipo de logradouro, Logradouro, Número, Bairro, Cidade, Estado, CEP e UF são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um endereço!";
		}
		return null;
	}

}
