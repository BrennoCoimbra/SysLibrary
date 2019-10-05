package br.com.syslib.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;

import br.com.syslib.core.IStrategy;
import br.com.syslib.core.impl.dao.CupomDAO;
import br.com.syslib.dominio.Cupom;
import br.com.syslib.dominio.EntidadeDominio;

public class ValidadorCupomTroca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Cupom cupomBD;
        Cupom cp = (Cupom) entidade;    
        Cupom cupom = new Cupom();
        
        double valorTotal;
        double valorDesconto;
        double sobraDoCupom;
        
        CupomDAO dao = new CupomDAO();
        List<EntidadeDominio> entidadesBanco = new ArrayList<>();
        try {
            entidadesBanco = dao.consultar(entidade);
            if (!entidadesBanco.isEmpty()) {
                cupomBD = (Cupom) entidadesBanco.get(0);
                if (cp.getNomeCupom() != null && cupomBD.getNomeCupom() != null) {

                    if (cp.getNomeCupom().equals(cupomBD.getNomeCupom())) {
                        if ("TROCA AUTORIZADA".equals(cupomBD.getStatusCupom())) {
                            if (cp.getValorPedido() > 0) {
                                valorDesconto = cupomBD.getValorCupom();
                                //qnd valor do desconto é maior que o valor do pedido
                                valorTotal = cp.getValorPedido() - valorDesconto;
                                if (valorTotal < 0) {
                                    sobraDoCupom = (-1) * valorTotal;
                                    cp.setDescontoPedido(cp.getDescontoPedido() + valorDesconto - sobraDoCupom);
                                    cp.setIdCupom(cupomBD.getIdCupom());
                                    cp.setDataCupom(cupomBD.getDataCupom());
                                    cp.setIdCupomCliente(cupomBD.getIdCupomCliente());
                                    cp.setIdPedido(cupomBD.getIdPedido());
                                    cp.setNomeCupom(cupomBD.getNomeCupom());
                                    cp.setValorCupom(sobraDoCupom);                                    
                                    cp.setiSubtotal(cupomBD.getValorCupom() - sobraDoCupom);
                                    //ped.getCupom().add(cupom);
                                    dao.alterar(entidade);
                                    cp.setValorPedido(0.00);
                                    return "DRIVEOK";
                                } else {
                                	//qnd valor do desconto é menor que o valor do pedido
                                    valorDesconto = cp.getDescontoPedido() + valorDesconto;
                                    cp.setDataCupom(cupomBD.getDataCupom());
                                    cp.setIdCupomCliente(cupomBD.getIdCupomCliente());
                                    cp.setIdCupom(cupomBD.getIdCupom());
                                    cp.setIdPedido(cupomBD.getIdPedido());
                                    cp.setStatusCupom("USADO");                                    
                                    cp.setNomeCupom(cupomBD.getNomeCupom());
                                    cp.setValorCupom(cupomBD.getValorCupom());
                                    cp.getCupom().add(cupom);
                                    cp.setValorPedido(valorTotal);
                                    cp.setDescontoPedido(valorDesconto);
                                    cp.getCupom().clear();
                                    cp.getCupom().add(cp);
                                    dao.alterar(entidade);
                                    return "DRIVEOK";
                                }
                            } else {
                                return "Você não precisa de Cupom!";
                            }
                        } else {
                            return "O Cupom não está ativo!";
                        }
                    }
                }
            }
        } catch (Exception e) {
            return "Erro ao verificar cupom!";
        }
        return "Cupom inválido!";
    }
}
