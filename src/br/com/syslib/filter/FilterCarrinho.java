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

import br.com.syslib.dominio.Pedido;


public class FilterCarrinho implements Filter{

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

		String url = req.getServletPath();
		Pedido pedido = session.getAttribute("pedido") == null ? null : (Pedido) session.getAttribute("pedido");		
		//String tempo = req.getParameter("btn");
		
		// retorna null caso nao tenha itens no carrinho
		if(pedido == null && !url.equalsIgnoreCase("/Login")) {			
			// redirecionamento
			request.setAttribute("msg", "Você perdeu a compra ou não adicionou nenhum item!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/carrinho.jsp?url="+url); //passando por parametros
			dispatcher.forward(request, response);	
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
