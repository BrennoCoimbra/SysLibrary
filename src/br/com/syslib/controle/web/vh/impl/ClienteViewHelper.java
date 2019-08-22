package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.dominio.Cliente;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.enuns.Genero;
import br.com.syslib.enuns.TipoTelefone;
import br.com.syslib.enuns.TipoUsuario;

public class ClienteViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		Cliente cliente = null;
		String idUsuario;
		
		if (operacao.equals ("SALVAR") || (operacao.equals("ALTERAR"))){
			//String ativo = request.getParameter("txtAtivo");
			String id = request.getParameter("idUsuario");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String confirmaSenha = request.getParameter("confirmaSenha");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String privacidade = request.getParameter("privacidade");
			String dataNasc = request.getParameter("dataNasc");
			String genero = request.getParameter("genero");
			String tpTelefone = request.getParameter("tpTelefone");
			String ddd = request.getParameter("ddd");
			String telefone = request.getParameter("telefone");
			
			
			cliente = new Cliente();
			//cliente.setAtivo(ativo.equals("1") ? true : false);
			cliente.setEmail(email);
			cliente.setSenha(senha);
			cliente.setConfirmaSenha(confirmaSenha);
			cliente.setNome(nome);
			cliente.setTipoUsuario(TipoUsuario.CUSTOMER);
			cliente.setPrivacidade(privacidade);
			cliente.setCpf(cpf);		
			if (id != null && !id.trim().equals("")) {
				cliente.setId(Integer.parseInt(id));
			}
			cliente.setDataNasc(dataNasc);
			for(Genero gen : Genero.values()) {
				if(genero != null && Integer.parseInt(genero) == gen.getCodigo()) {
					cliente.setGenero(gen);
				}
			}
			
			
			for (TipoTelefone tpTel : TipoTelefone.values()) {
				if (tpTelefone != null && Integer.parseInt(tpTelefone) == tpTel.getCodigo()) {
					cliente.getTelefone().setTpTelefone(tpTel);
				}
			}
			cliente.getTelefone().setTelDDD(ddd);
			cliente.getTelefone().setNumTel(telefone);
			
			
			
		} else if (operacao.equals("EXCLUIR") || operacao.equals("CONSULTAR")) {
			idUsuario = request.getParameter("IdUsuario");
			int id = 0;

			if (idUsuario != null && !idUsuario.trim().equals("")) {
				id = Integer.parseInt(idUsuario);

				cliente = new Cliente();
				cliente.setId(id);
			}
		} else {
			Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
			idUsuario = request.getParameter("txtIdUsuario");
			int id = 0;

			if (idUsuario != null && !idUsuario.trim().equals("")) {
				id = Integer.parseInt(idUsuario);
			}

			if (resultado != null) {
				for (EntidadeDominio e : resultado.getEntidades()) {
					if (e != null && e.getId() != null && id > 0 && e.getId() == id) {
						cliente = (Cliente) e;
					}
				}
			}
		}

		return cliente;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		EntidadeDominio usu = (EntidadeDominio) session.getAttribute("usuario");

		
		if(resultado.getMsg() == null) {
			request.getSession().setAttribute("resultado", resultado);
			
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Usuario cadastrado com sucesso!");
				
				d = request.getRequestDispatcher("login.jsp");
				
			} else if (operacao.equals("ALTERAR")) {
				
				session.setAttribute("usuario", usu);
				for (EntidadeDominio ed : resultado.getEntidades()) {
					if (ed instanceof Cliente) {
						Cliente usuario = (Cliente) ed;
												
							//adiciona usuario logado na sessao						
							session.setAttribute("usuario",usuario);	
				d = request.getRequestDispatcher("profile.jsp");

					}
			} 
			}
		} else {
			request.setAttribute("msg", resultado.getMsg());
			d = request.getRequestDispatcher("errors.jsp");
		}
		
		if (d != null)
			d.forward(request,response);
	}
}