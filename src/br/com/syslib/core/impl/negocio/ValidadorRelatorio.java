package br.com.syslib.core.impl.negocio;

import java.util.Date;

import br.com.syslib.core.IStrategy;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Relatorio;

public class ValidadorRelatorio implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Relatorio){
			Relatorio relatorio = (Relatorio) entidade;
			
			Date dataInicial = relatorio.getDataInicial();
			Date dataFinal = relatorio.getDataFinal();
			
			if(dataInicial == null || dataFinal == null)
				return "Data Inicial e Data Final são obrigatórias.";
			
			if(dataInicial != null || dataFinal != null) {
				if (dataInicial.after(dataFinal))
					return "Data Inicial deve ser menor que Data Final.";
			}
		} else {
			return "Entidade não é um relatorio!";
		}
		return null;
	}
}