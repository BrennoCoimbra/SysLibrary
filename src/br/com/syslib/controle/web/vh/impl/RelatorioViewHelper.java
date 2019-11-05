package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Relatorio;
import br.com.syslib.enuns.TipoPeriodo;

public class RelatorioViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Relatorio relatorio = null; 
		
		if(operacao.equals("SALVAR")) {
			String dtInicial = request.getParameter("dtInicial");
			String dtFinal = request.getParameter("dtFinal");
			String tipoPeriodo = request.getParameter("tipoPeriodo");
			//String tipoRelatorio = request.getParameter("tipoRelatorio");
			Calendar dataInicial = null;
			Calendar dataFinal = null;
			
			relatorio = new Relatorio();
			
			try {
				if (dtInicial != null && !dtInicial.trim().equals("")) {
					dataInicial = Calendar.getInstance();
					dataInicial.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dtInicial));
					relatorio.setDataInicial(dataInicial.getTime());
				}
				
				if (dtFinal != null && !dtFinal.trim().equals("")) {
					dataFinal = Calendar.getInstance();
					dataFinal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dtFinal));
					relatorio.setDataFinal(dataFinal.getTime());
				}
				
				if (tipoPeriodo != null && !tipoPeriodo.trim().equals("")) {
					for (TipoPeriodo tp : TipoPeriodo.values()) {
						if (Integer.parseInt(tipoPeriodo) == tp.getCodigo()) {
							relatorio.setTipoPeriodo(tp);
						}
					}
				}
				/*
				if (tipoRelatorio != null && !tipoRelatorio.trim().equals("")) {
					for (TipoRelatorio tr : TipoRelatorio.values()) {
						if (Integer.parseInt(tipoRelatorio) == tr.getCodigo()) {
							relatorio.setTipoRelatorio(tr);
						}
					}
				}*/
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return relatorio;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				request.setAttribute("exibir", 1);
				d = request.getRequestDispatcher("reports.jsp");
			}
		} else {
			request.setAttribute("msg", resultado.getMsg());
			
			d = request.getRequestDispatcher("msg-erro.jsp");
		}
				
		if (d != null)
			d.forward(request, response);
		
	}
}