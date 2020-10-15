

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adocao.entities.Usuario;

/**
 * Servlet Filter implementation class CadastroFilter
 */
@WebFilter("/animal.jsf")
public class CadastroFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CadastroFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
	HttpSession session = (HttpSession)req.getSession();
	
	Usuario usuario = (Usuario)session.getAttribute("usuario");
	if(usuario!=null) {
		chain.doFilter(request, response);	
	}else {
		res.sendRedirect(req.getContextPath()+"/usuario.jsf");		
		}
	
		
	}
	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
