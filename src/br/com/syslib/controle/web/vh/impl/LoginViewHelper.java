package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;

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
		String url = request.getParameter("url");
		
		Usuario user = new Usuario();
		user.setEmail(email);
		user.setSenha(senha);
		
		if ("Sair".equals(request.getParameter("operacao"))) {
            HttpSession session = request.getSession();
            ArrayList<EntidadeDominio> usuarioLogado = (ArrayList) session.getAttribute("usuario");
            user = (Usuario) usuarioLogado.get(0);
            user.setId(user.getId());
           // user.setIdUsuario(user.getIdUsuario());
        }
		
		return user;
		
		
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String operacao = request.getParameter("operacao");
		RequestDispatcher d = null;
		
		if(operacao.equals("SAIR")){
            request.getSession().invalidate();
            d = request.getRequestDispatcher("/login.jsp");
            d.forward(request, response);
		 }
		
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
						
						//adiciona usuario logado na sessao
						HttpServletRequest req = (HttpServletRequest) request;
						HttpSession session = req.getSession();
						session.setAttribute("usuario",usuario);
						
						d = request.getRequestDispatcher("index.jsp");
						
//						if (usuario.getTipoUsuario() == TipoUsuario.COMMON) {							
//							d = request.getRequestDispatcher("index.jsp");
//						} else if (usuario.getTipoUsuario() == TipoUsuario.EMPLOYEE) {
//							d = request.getRequestDispatcher("dashboard.jsp");
//						}
					}
				}
			}
		}
		 
		d.forward(request, response);
	}
}