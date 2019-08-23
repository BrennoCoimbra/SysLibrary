package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.impl.dao.EnderecoDAO;
import br.com.syslib.core.util.Logged;
import br.com.syslib.dominio.Endereco;
import br.com.syslib.dominio.EntidadeDominio;

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
			String logradouro = request.getParameter("logradouro");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");			
			String cep = request.getParameter("cep");
			String pais = request.getParameter("pais");
			
			if (numero == null || numero.trim().equals(""))
				numero = "0";
			
			endereco = new Endereco();
			
			endereco.setIdUsuario(Integer.parseInt(idUsu));
			endereco.setDescricao(descricao);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(Integer.parseInt(numero));
			endereco.setBairro(bairro);
			endereco.setCidade(cidade);
			endereco.setEstado(estado);
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
		
		
		if(resultado.getMsg() == null) {
			request.getSession().setAttribute("resultado", resultado);
			
			if (operacao.equals("SALVAR") || operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("form-endereco.jsp");
				
			} else if (operacao.equals("VISUALIZAR")) {
				try {
					String filter = request.getParameter("search");
					List<EntidadeDominio> enderecos = new EnderecoDAO().visualizar(filter);
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
				
			}
		} else {
			request.setAttribute("msg", resultado.getMsg());
			
			d = request.getRequestDispatcher("errors.jsp");
		}
				
		if (d != null)
			d.forward(request, response);
	}
}