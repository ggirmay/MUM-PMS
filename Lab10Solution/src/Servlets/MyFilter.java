package Servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class MyFilter
 */
@WebFilter(description = "MyFilter", urlPatterns = { "", "/", "/*", "/html"})
public class MyFilter implements Filter {
 private int hitcounter;
    
    public MyFilter() {
        // TODO Auto-generated constructor stub
    }


	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		this.hitcounter++;
		request.getServletContext().setAttribute("totalHitCount", this.hitcounter);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}

	
