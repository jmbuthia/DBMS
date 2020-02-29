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


@WebFilter("/user/rider/*")
public class RiderFilter implements Filter {
	@SuppressWarnings("unused")
	private FilterConfig fc;
    
    public RiderFilter() {
       
    }

	
	public void destroy() {
	
	}


	 public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) res;
	        Login login = (Login) request.getSession().getAttribute("login");
	        String loginURL = request.getContextPath() + "/login.xhtml";
	        if(login != null && login.isLoggedIn()){
	        	if(login.getCategory().equals("rider")||login.getCategory().equals("admin")||login.getCategory().equals("teller")){
	        		chain.doFilter(req, res);
	        	}
	        	if(login.getCategory().equals("general")){
	        		response.sendRedirect(request.getContextPath() + "/user/generalUser.xhtml");
	        	
	        	}
	        
	        	if(login.getCategory().equals("owner")){
	        		response.sendRedirect(request.getContextPath() + "/user/owner/ownerHome.xhtml");
	        	
	        	}
	        
	        	        	
	        	
	        	
	        }
	       else {
	        		response.sendRedirect(loginURL);
	        		         	
	        	
	        	}
	            
	        
	    }


	   

		public void init(FilterConfig fConfig) throws ServletException {
	        this.fc = fConfig;
	    }

}
