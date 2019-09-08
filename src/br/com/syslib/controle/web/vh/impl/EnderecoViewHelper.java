package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.EnderecoDAO;
import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.enuns.Estados;
import br.com.syslib.enuns.TipoEndereco;
import br.com.syslib.enuns.TipoLogradouro;
import br.com.syslib.enuns.TipoResidencia;

public class EnderecoViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Endereco endereco = null; 
		String idEndereco;
		
		if(operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
			String idUsu = request.getParameter("idUsuario");
			String id = request.getParameter("endereco_id");
			String descricao = request.getParameter("descricao");
			String tpEnd = request.getParameter("tpEnd");
			String residencia = request.getParameter("tpRes");
			String tpLog = request.getParameter("tpLog");
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");			
			String cep = request.getParameter("cep");
			String pais = request.getParameter("pais");
			String preferencial = request.getParameter("pref");
			
			if (numero == null || numero.trim().equals(""))
				numero = "0";
			
			endereco = new Endereco();
			
			endereco.setIdUsuario(Integer.parseInt(idUsu));
			endereco.setDescricao(descricao);
			endereco.setPreferencial(preferencial.equals("1") ? true : false);
			
			for (TipoEndereco end : TipoEndereco.values()) {
				if (tpEnd != null && Integer.parseInt(tpEnd) == end.getCodigo()) {
					endereco.setTpEnd(end);
				}
			}
			
			for (TipoResidencia res : TipoResidencia.values()) {
				if (residencia != null && Integer.parseInt(residencia) == res.getCodigo()) {
					endereco.setTpResid(res);
				}
			}
			for (TipoLogradouro log : TipoLogradouro.values()) {
				if (tpLog != null && Integer.parseInt(tpLog) == log.getCodigo()) {
					endereco.setTpLogrdo(log);
				}
			}
			
			
			
			endereco.setLogradouro(logradouro);
			endereco.setNumero(Integer.parseInt(numero));
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			for (Estados est : Estados.values()) {
				if (estado != null && Integer.parseInt(estado) == est.getCodigo()) {
					endereco.setEstados(est);
				}
			}
			endereco.setCep(cep);
			endereco.setPais(pais);
			endereco.setDtCadastro(new Date());
			
			if (id != null && !id.trim().equals("")) {
				endereco.setId(Integer.parseInt(id));
			}
		} else if(operacao.equals("EXCLUIR") || operacao.equals("CONSULTAR")) {
			idEndereco = request.getParameter("endereco_id");
			int id = 0;
			
			if(idEndereco != null && !idEndereco.trim().equals("")) {
				id = Integer.parseInt(idEndereco);
				
				endereco = new Endereco();
				endereco.setId(id);
			}
		} else {
			Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
			idEndereco = request.getParameter("endereco_id");
			int id = 0;
			
			if(idEndereco != null && !idEndereco.trim().equals("")) {
				id = Integer.parseInt(idEndereco);
			}
			
			if (resultado != null) {
				for(EntidadeDominio e : resultado.getEntidades()) {
					if(e != null && e.getId() != null && id > 0 && e.getId() == id){
						endereco = (Endereco) e;
					}
				}
			}
		}
		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		
		RequestDispatcher d = null;		
		String operacao = request.getParameter("operacao");
		HttpSession session = request.getSession();
		EntidadeDominio usu = (EntidadeDominio) session.getAttribute("usuario");
		
		if(resultado.getMsg() == null) {
			request.getSession().setAttribute("resultado", resultado);
			
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				if(session.getAttribute("pedido") != null) {
				d = request.getRequestDispatcher("form-pedido-endereco.jsp");	
				} else {
				d = request.getRequestDispatcher("form-endereco.jsp");
				}
			} else if (operacao.equals("VISUALIZAR")) {
				try {
					String filter = request.getParameter("search");
					Integer idCliente = usu.getId();
					List<EntidadeDominio> enderecos = new EnderecoDAO().visualizar(filter,idCliente);
					request.setAttribute("enderecos", enderecos);
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar endereços.");
				}
				
				d = request.getRequestDispatcher("consultar-endereco.jsp");
				
			} else if (operacao.equals("CONSULTAR")) {
				try {
					int IdEndereco = Integer.parseInt(request.getParameter("endereco_id"));
					Endereco endereco = (Endereco) new EnderecoDAO().getEntidadeDominioEndreco(IdEndereco);
					request.setAttribute("enderecos", endereco);
				} catch (SQLException e) {
					resultado.setMsg("Não foi possível listar endereço.");;
				}
				
				d = request.getRequestDispatcher("form-endereco.jsp");
				
			} else if (operacao.equals("EXCLUIR")) {
				d = request.getRequestDispatcher("consultar-endereco.jsp");
				
			} else if (operacao.equals("SAIR")){
	            request.getSession().invalidate();
	            d = request.getRequestDispatcher("index.jsp");
	            d.forward(request, response);
		
			}
		} else {
			
			request.setAttribute("msg", resultado.getMsg());
			
			d = request.getRequestDispatcher("errors.jsp");
		}
				
		if (d != null)
			d.forward(request, response);
	}
}