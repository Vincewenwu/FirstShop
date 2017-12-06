package me.ilt.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//解决全站乱码问题
public class EncodingFilter implements Filter {
	
    protected FilterConfig filterConfig = null;
    private String defaultEncoding = "UTF-8";
    
	public void init(FilterConfig filterConfig) throws ServletException {
		    this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		
		String encoding =  filterConfig.getInitParameter("encoding");
		if (encoding==null || encoding.isEmpty()) {
             encoding = defaultEncoding;            
        }
		request.setCharacterEncoding(encoding); 
		response.setContentType("text/html;charset=" + encoding);
		chain.doFilter(request, response);
		
	}

	public void destroy() {
		
	}



}
