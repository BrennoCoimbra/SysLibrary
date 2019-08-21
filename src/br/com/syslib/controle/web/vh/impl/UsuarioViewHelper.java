package br.com.syslib.controle.web.vh.impl;

import java.io.IOException;

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

public class UsuarioViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");
		Usuario user = new Usuario();
		
		if(operacao.equals("CONSULTAR")) {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		user.setEmail(email);
		user.setSenha(senha);
		
		} else if (operacao.equals("SAIR")) {
            HttpSession session = request.getSession();
            EntidadeDominio usuarioLogado = (EntidadeDominio) session.getAttribute("usuario");
            user = (Usuario) usuarioLogado;
            user.setId(user.getId());
          
        } else if("ALTERAR".equals("operacao")) {
        	
        } 
		
		return user;
		
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String operacao = request.getParameter("operacao");
		RequestDispatcher d = null;
		
		if(operacao.equals("SAIR")){
            request.getSession().invalidate();
            d = request.getRequestDispatcher("index.jsp");
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
						
						
						
						if (usuario.getTipoUsuario() == TipoUsuario.CUSTOMER) {							
							d = request.getRequestDispatcher("profile.jsp");
						} else if (usuario.getTipoUsuario() == TipoUsuario.ADMIN) {
							d = request.getRequestDispatcher("form-livro.jsp");
						}
					}
				}
			}
		}
		 
		d.forward(request, response);
	}
}