package webFilters;

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

import logicController.Login;

@WebFilter("/user/*")
public class GeneralUserFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig fc;
    
    public GeneralUserFilter() {
       
    }

	public void destroy() {
		
	}

	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        Login login = (Login) request.getSession().getAttribute("login");
	        String loginURL = request.getContextPath() + "/login.xhtml";
	        if(login != null && login.isLoggedIn()){
	        	chain.doFilter(req, res);
	        	
	        }
	       else {
	        		response.sendRedirect(loginURL);
	        		         	
	        	
	        	}
	            
	        
	    }


	   

		public void init(FilterConfig fConfig) throws ServletException {
	        this.fc = fConfig;
	    }


	}
