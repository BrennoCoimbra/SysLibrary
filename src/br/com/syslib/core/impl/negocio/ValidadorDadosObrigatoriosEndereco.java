package br.com.syslib.core.impl.negocio;

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
			String logradouro = endereco.getLogradouro();
			int numero = endereco.getNumero();
			String bairro = endereco.getBairro();
			String cidade = endereco.getCidade();
			String estado = endereco.getEstado();
			String cep = endereco.getCep();
			String uf = endereco.getEstado();
			
			if(idusuario <= 0)
				return "Erro grave, IdUsuario não encontrado, avise o Desenvolvedor.";
				
			if(logradouro == null || numero <= 0 || bairro == null || cidade == null|| estado == null || cep == null || uf == null)
				return "Logradouro, Número, Bairro, Cidade, Estado, CEP e UF são de preenchimento obrigatório!";
			
			if(logradouro.trim().equals("") || bairro.trim().equals("") || cidade.trim().equals("") || estado.trim().equals("") || cep.trim().equals("") || uf.trim().equals(""))
				return "Logradouro, Número, Bairro, Cidade, Estado, CEP e UF são de preenchimento obrigatório!";
		} else {
			return "Deve ser registrado um endereço!";
		}
		return null;
	}

}
