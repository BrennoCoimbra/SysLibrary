package br.com.syslib.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.syslib.dominio.Usuario;
import br.com.syslib.enuns.TipoUsuario;

public class FilterAutorizacao implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//Saber se o usuario esta na session		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//pagina que está tentando acesso
		System.out.println(req.getServletPath());
		// url para autenticar
		String urlAutenticar = req.getServletPath();
		Usuario user = (Usuario) session.getAttribute("usuario");		
		
		// retorna null caso nao esteja logado
		if(user.getTipoUsuario() == TipoUsuario.ADMIN && !urlAutenticar.equalsIgnoreCase("/Login")) {			
			chain.doFilter(request, response);
			System.out.println("interceptando autorizacao");
		}
		request.setAttribute("msg", "Sem autorização!");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?url="+urlAutenticar); //passando por parametros
		dispatcher.forward(request, response);	
		System.out.println("Usuário não é administrador!");
		return; // para o processo para redirecionar
		
		//executa as ações do request e response
		
		
		
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
