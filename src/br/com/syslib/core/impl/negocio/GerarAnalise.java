package br.com.syslib.core.impl.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.RelatorioDAO;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Relatorio;

public class GerarAnalise implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Relatorio) {
			Relatorio relatorio = (Relatorio) entidade;
			RelatorioDAO relDAO = new RelatorioDAO();			
			List<Relatorio> relBD;
			List<Relatorio> rel = new ArrayList<Relatorio>();
			Map<String, Double> analise = new HashMap<String, Double>();
			
			try {
				rel = relDAO.getRelatorioByDia(relatorio);
			} catch (Exception e) {
				return "Erro ao consultar o BD";
			}
			if(!rel.equals(null)) {
				relBD = (List<Relatorio>) rel;
				for(Relatorio relat : relBD) {
					analise.put(relat.getTitulo(), relat.getQtde());
				}
				Gson gson = new Gson();
				String json = gson.toJson(analise);
				relatorio.setAnalise(json);
				return "OK";
			} else {
				return "Erro";
			}
			
			
		} else {
			return "Entidade não é Analise";
		}
	
	}

}
