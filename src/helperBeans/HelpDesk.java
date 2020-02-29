package helperBeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dataManager.UserDB;

@SuppressWarnings("deprecation")
@ManagedBean
public class HelpDesk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String idNo;
	private String message;
	private String phone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void submitMessage(){
		 RequestContext primeContext = RequestContext.getCurrentInstance();
		System.out.println("Called submitMessage method");
		String group="";
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		group=request.getParameter("category");
		
	if(group.equals("nonStaff")){
		int test=UserDB.saveHelpAllVisitors(name, phone, message);
		if(test>0){
		RequestContext.getCurrentInstance().update("growl");  
    	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_INFO,
                       "Success",
                       "Your message was sent successfully, you will be contacted."));
   	primeContext.execute("PF('help').hide();");
		}else {
			RequestContext.getCurrentInstance().update("growl");  
	    	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "An error occured while sending your message, please try again."));
		   	//primeContext.execute("help.show()");
		}
	
	}
	if(group.equals("staff")){
		HttpSession session = SessionBean.getSession();
		RegistrationDetails rd= (RegistrationDetails) session.getAttribute("registrationDetails");
		setIdNo(rd.getIdNo());
		setPhone(rd.getPhone());
		int testResult=UserDB.saveHelpRegisteredUsers(idNo, phone, message);
		
		if(testResult>0){
			RequestContext.getCurrentInstance().update("growl");  
	    	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_INFO,
		                       "Success",
		                       "Your message was sent successfully, you will be contacted."));
		   	primeContext.execute("PF('help').hide();");
			
		}else {
			RequestContext.getCurrentInstance().update("growl");  
	    	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "An error occured while sending your message, please try again."));
			
		}
		
		
		
	}
}
}
