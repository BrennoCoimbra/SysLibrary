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
          
		} else if (operacao.equals("ALTERAR")) {
        	String idUsuario = request.getParameter("idUsuario");
            String senha = request.getParameter("senhaAntiga");
            String senha1 = request.getParameter("novaSenha");
            String senha2 = request.getParameter("confNovaSenha");
            user.setSenha(senha);
            user.setSenha1(senha1);
            user.setSenha2(senha2);
        	user.setId(Integer.parseInt(idUsuario));
        } 
		
		
		
		return user;
		
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
		
			if(operacao.equals("SAIR")){
	            request.getSession().invalidate();
	            d = request.getRequestDispatcher("index.jsp");
	            
		
		} else if(operacao.equals("ALTERAR")){

            d = request.getRequestDispatcher("profile.jsp");

			}else if(operacao.equals("CONSULTAR")) {
					session.setAttribute("usuario", usu);
					for (EntidadeDominio ed : resultado.getEntidades()) {
						if (ed instanceof Cliente) {
							Cliente usuario = (Cliente) ed;
													
								//adiciona usuario logado na sessao						
								session.setAttribute("usuario",usuario);												
								
								if (usuario.getTipoUsuario() == TipoUsuario.CUSTOMER) {							
									d = request.getRequestDispatcher("profile.jsp");
								} else if (usuario.getTipoUsuario() == TipoUsuario.ADMIN) {
									d = request.getRequestDispatcher("adm/dashboard.jsp");
								}
							
						} else if(ed instanceof Usuario){
							Usuario usuario = (Usuario) ed;
							session.setAttribute("usuario",usuario);
							request.setAttribute("resultado", resultado);                       
			                d = request.getRequestDispatcher("adm/dashboard.jsp");
			
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