package br.com.syslib.core.impl.negocio;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.EnderecoDAO;
import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Frete;

public class ValidadorCalculoFrete implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		if(entidade instanceof Frete) {
			Frete frete = (Frete) entidade;
			int idEnd = frete.getIdEnd();			;
			EnderecoDAO endDAO = new EnderecoDAO();
			EntidadeDominio ent = new Endereco();
			Endereco endBD;


			
			try {
				ent = endDAO.getEntidadeDominioEndreco(idEnd);
			} catch (Exception e) {
				return "Erro ao buscar id do endereço";
			}
			
			if(!ent.equals(null)) {
				endBD = (Endereco) ent;
				int codigoEst = endBD.getEstados().getCodigo();
			if(codigoEst != 0) {
				// estados da regiao NORTE
				if(codigoEst == 1 || codigoEst == 3 || codigoEst == 4 || codigoEst == 22 || codigoEst == 23 || codigoEst == 14 || codigoEst == 27) {
				frete.setValorFrete(30.00);
				// estados da regiao NORDESTE
				} else if (codigoEst == 2 || codigoEst == 5 || codigoEst == 6 || codigoEst == 10 || codigoEst == 15 || codigoEst == 17 || codigoEst == 18 || codigoEst == 26 || codigoEst == 20) {
				frete.setValorFrete(25.00);
				// estados da regiao CENTRO-OESTE
				} else if (codigoEst == 7 || codigoEst == 11 || codigoEst == 12) {
				frete.setValorFrete(20.00);
				// estados da regiao SUDESTE/SUL
				} else if (codigoEst == 13 || codigoEst == 8 || codigoEst == 19 || codigoEst == 16 || codigoEst == 24 || codigoEst == 21) {
				frete.setValorFrete(15.00);
				// frete p/ SP
				} else if (codigoEst == 25){
				frete.setValorFrete(10.00);
				}
			} else {
				return "Deve ser escolhido um endereço, para calcular o frete!";
			}	
				
				
				return "FRETEOK";
			}
		} else {
			return "Entidade não é Frete!";
		}
		
		return null;
	}

}
