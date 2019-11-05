package br.com.syslib.core.impl.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.RelatorioDAO;
import br.com.syslib.core.util.ConverteDate;
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
			Gson gson = new Gson();
			Map<Object,Object> map = new HashMap<Object,Object>();
			List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
			Map<String, Double> analise = new HashMap<String, Double>();
			String dataPoints = null;


			
			try {
				rel = relDAO.getRelatorioByDia(relatorio);
			} catch (Exception e) {
				return "Erro ao consultar o BD";
			}
			if(!rel.equals(null)) {
				relBD = (List<Relatorio>) rel;
				for(Relatorio relat : relBD) {	
					
					String xVal = relat.getTitulo();
					Double yVal = relat.getQtde();
					String yDate = ConverteDate.converteDateString(relat.getData());

					map = new HashMap<Object,Object>();
					
					map.put("name",(xVal)); 
					map.put("valor", (yVal));		
					map.put("date",(yDate));

					list.add(map);
					
					analise.put(relat.getTitulo(), relat.getQtde());

				}

					dataPoints = gson.toJson(list);
					relatorio.setAnalise(dataPoints);
					//System.out.println(dataPoints);
	
				return "OK";
			} else {
				return "Erro";
			}
			
			
		} else {
			return "Entidade não é Analise";
		}
	
	}

}
