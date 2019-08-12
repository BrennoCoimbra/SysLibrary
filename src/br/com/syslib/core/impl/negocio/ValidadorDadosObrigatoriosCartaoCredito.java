package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.CartaoCredito;
import br.com.syslib.dominio.EntidadeDominio;

public class ValidadorDadosObrigatoriosCartaoCredito implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof CartaoCredito){
			CartaoCredito cartaoCredito = (CartaoCredito) entidade;
			String descricao = cartaoCredito.getDescricao();
			String nomeNoCartao = cartaoCredito.getNomeCartao();
			String numeroDoCartao = cartaoCredito.getNumeroCartao();
			int mesValidade = cartaoCredito.getMes();
			int anoValidade = cartaoCredito.getAno();
			int codigoSeguranca = cartaoCredito.getCodigoSeguranca();
						
			if(descricao == null || nomeNoCartao == null || numeroDoCartao == null || mesValidade <= 0 || anoValidade <= 0 || codigoSeguranca <= 0)
				return "Descricao, Nome no Cartão, Número do Cartão, Mês e Ano Validade e Código de Segurança são de preenchimento obrigatório!";
			
			if(nomeNoCartao.trim().equals("") || numeroDoCartao.trim().equals(""))
				return "Nome no Cartão e Número do Cartão são de preenchimento obrigatório!";
			
			if(mesValidade < 0 || mesValidade > 12 ) {
				return "Mes invalido!";
			}
			
			
		} else {
			return "Deve ser registrado um cartão de crédito!";
		}
		return null;
	}
	
	

}
