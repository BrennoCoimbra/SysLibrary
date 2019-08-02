package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.util.Logged;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;
import br.com.syslib.enuns.TipoUsuario;

public class UsuarioViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String operacao = request.getParameter("operacao");
		Usuario usuario = null;
		String idUsuario;
		
		if (operacao.equals ("SALVAR") || (operacao.equals("ALTERAR"))){
			String id = request.getParameter("idUsuario");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha");
			String confirmaSenha = request.getParameter("confirmaSenha");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String privacidade = request.getParameter("privacidade");
			
			usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setConfirmaSenha(confirmaSenha);
			usuario.setNome(nome);
			usuario.setTipoUsuario(TipoUsuario.COMMON);
			usuario.setPrivacidade(privacidade);
			usuario.setCpf(cpf);
			if (id != null && !id.trim().equals("")) {
				usuario.setId(Integer.parseInt(id));
			}
		} else if (operacao.equals("EXCLUIR") || operacao.equals("CONSULTAR")) {
			idUsuario = request.getParameter("IdUsuario");
			int id = 0;

			if (idUsuario != null && !idUsuario.trim().equals("")) {
				id = Integer.parseInt(idUsuario);

				usuario = new Usuario();
				usuario.setId(id);
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
						usuario = (Usuario) e;
					}
				}
			}
		}

		return usuario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher d = null;
		
		if (resultado.getMsg() != null) {
			request.setAttribute("msg", resultado.getMsg());

			d = request.getRequestDispatcher("errors.jsp");
		} else {
			String operacao = request.getParameter("operacao");
			
			if (operacao.equals("ALTERAR")) {
				int ultimaEntidade = resultado.getEntidades().size() - 1;
				Logged.setUsuario((Usuario) resultado.getEntidades().get(ultimaEntidade));
				
				d = request.getRequestDispatcher("profile.jsp");
			} else if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("login.jsp");
			}
		}
		
		if (d != null)
			d.forward(request, response);
	}
}