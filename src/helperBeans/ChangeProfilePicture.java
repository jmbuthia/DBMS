package helperBeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class ChangeProfilePicture implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fromOutCome;

	public String getFromOutCome() {
		return fromOutCome;
	}

	public void setFromOutCome(String fromOutCome) {
		this.fromOutCome = fromOutCome;
	}
	
public String ChangePicture(){
	HttpServletRequest request=SessionBean.getRequest();
	setFromOutCome(request.getRequestURI());
	System.out.println("the current url from changepicture function is "+request.getRequestURI());
	return "/user/changeProfilePicture";
	
}
public String ChangePassword(){
	HttpServletRequest request=SessionBean.getRequest();
	setFromOutCome(request.getRequestURI());
	HttpSession s=SessionBean.getSession();
	if(s.getAttribute("fromOutCome")==null){
		s.setAttribute("fromOutCome", request.getRequestURI());
	}
	else {
		s.removeAttribute("fromOutCome");
		s.setAttribute("fromOutCome", request.getRequestURI());
		
	}
	
	System.out.println("the current url from change password function  is "+request.getRequestURI());
	return "/user/changePassword?faces-redirect=true";
	
}
}
