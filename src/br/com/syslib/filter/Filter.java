package br.com.syslib.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Filter implements javax.servlet.Filter {

	@Override
    public void init(FilterConfig filterConfig) throws ServletException {     
        }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	 
    	HttpSession session = ((HttpServletRequest) request).getSession();

         if(session.getAttribute("logado") != null){
               chain.doFilter(request, response);
         }
         else if(request.getParameter("email") != null
             && request.getParameter("senha") != null 
             &&  ((HttpServletRequest)request).getRequestURL().toString().equals("/SysLibrary/autenticado/Login")){     
              chain.doFilter(request, response);
         }
         else{
              ((HttpServletResponse) response).sendRedirect("./login.jsp");
              return;
          }  
     }

    @Override
    public void destroy() {
         }
    
   
    
}
