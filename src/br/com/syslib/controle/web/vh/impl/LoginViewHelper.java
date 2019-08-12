package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.syslib.core.aplicacao.Resultado;
import br.com.syslib.core.util.Logged;
import br.com.syslib.dominio.EntidadeDominio;
import br.com.syslib.dominio.Usuario;
import br.com.syslib.enuns.TipoUsuario;

public class LoginViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Usuario user = new Usuario();
		user.setEmail(email);
		user.setSenha(senha);
		
		return user;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
        ArrayList<EntidadeDominio> usuarioLogado = session.getAttribute("usuarioLogado") == null ? new ArrayList<>() : (ArrayList) session.getAttribute("usuarioLogado");
        List<EntidadeDominio> entidade = (List<EntidadeDominio>) resultado.getEntidades();
		
		
		RequestDispatcher d = null;
		
		if (resultado.getMsg() != null) {
			request.setAttribute("msg", resultado.getMsg());
			
			d = request.getRequestDispatcher("errors.jsp");
		} else if (resultado.getEntidades().isEmpty()) {
			request.setAttribute("msg", "Usuário e/ou Senha incorretos.");
			
			d = request.getRequestDispatcher("errors.jsp");
		} else {
			for (EntidadeDominio ed : resultado.getEntidades()) {
				if (ed instanceof Usuario) {
					Usuario usuario = (Usuario) ed;
					
					if (usuario.getEmail().equals(request.getParameter("email")) && 
							usuario.getSenha().equals(request.getParameter("senha"))) {
						
						Logged.setUsuario(usuario);
						
						if (usuario.getTipoUsuario() == TipoUsuario.COMMON) {
							d = request.getRequestDispatcher("index.jsp");
						} else if (usuario.getTipoUsuario() == TipoUsuario.EMPLOYEE) {
							d = request.getRequestDispatcher("dashboard.jsp");
						}
					}
				}
			}
		}
		
		d.forward(request, response);
	}
}