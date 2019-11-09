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

import br.com.syslib.dominio.EntidadeDominio;


public class FilterAutenticacao implements Filter{

	// Executa alguma coisa quando a aplicação é iniciada
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
		
		
	}
	
	// Intercepta todas as nossas requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Saber se o usuario esta na session		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		//pagina que está tentando acesso
		//System.out.println(req.getServletPath());
		// url para autenticar
		String urlAutenticar = req.getServletPath();
		EntidadeDominio user = (EntidadeDominio) session.getAttribute("usuario");		
		
		// retorna null caso nao esteja logado
		if(user == null && !urlAutenticar.equalsIgnoreCase("/Login")) {			
			// redirecionamento
			request.setAttribute("msg", "Você não está logado!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp?url="+urlAutenticar); //passando por parametros
			dispatcher.forward(request, response);	
			//System.out.println("usuario nao logado!");
			return; // para o processo para redirecionar
		}
		
		//executa as ações do request e response
		chain.doFilter(request, response);
		//System.out.println("interceptando autenticacao");
		
		
	}
	
	// Faz alguma coisa quando a aplicação é derrubada
	@Override
	public void destroy() {
		
		
	}

	
   
    
}
