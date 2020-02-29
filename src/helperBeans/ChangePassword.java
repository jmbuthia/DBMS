package helperBeans;


import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dataManager.UserDB;
@SuppressWarnings("deprecation")
@ManagedBean

public class ChangePassword implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oldPassword;
	private String idNo;
	private String newPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
	
	  public String changePwd(){
		 
		  System.out.println("called change password method");
		  System.out.println("idno is "+idNo);
		  System.out.println("old password is "+oldPassword);
		  System.out.println("new password is " +newPassword);
		  
		  HttpSession session = SessionBean.getSession();
		  RegistrationDetails rd= (RegistrationDetails) session.getAttribute("registrationDetails");
		  String fromOutCome="";
		  System.out.println(" request url is "+fromOutCome);
		  System.out.println("idnumber in session is "+rd.getIdNo());
		  if(rd==null||idNo==null){
			  RequestContext.getCurrentInstance().update("growl");
			  FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "No value of id number supplied."));
			  fromOutCome="user/changePassword?faces-redirect=true";
			  
			  
		  }
		  if(idNo.equals(rd.getIdNo())){
			  int check=UserDB.changePassword(idNo,oldPassword,newPassword);
			  if(check>1){
				  RequestContext.getCurrentInstance().update("growl");
				  FacesContext.getCurrentInstance().addMessage(
			               null,
			               new FacesMessage(FacesMessage.SEVERITY_INFO,
			                       "Success",
			                       "Hey, "+rd.getFirstName()+" your password was  changed successfuly. Press Done button to continue"));
				
				 
				  fromOutCome="user/changePassword?faces-redirect=true";
				  
				  
			  }else {
				  RequestContext.getCurrentInstance().update("growl");
				  FacesContext.getCurrentInstance().addMessage(
			               null,
			               new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                       "Error",
			                       "Solly, "+rd.getFirstName()+" failed to change password try again."));
				  fromOutCome="user/changePassword?faces-redirect=true";
				
			}
			  
		  }else {
			  RequestContext.getCurrentInstance().update("growl");  
		     	
			   	 FacesContext.getCurrentInstance().addMessage(
			               null,
			               new FacesMessage(FacesMessage.SEVERITY_FATAL,
			                       "Error",
			                       "Hey, "+rd.getFirstName()+" you can't change password for another account."));
			   	fromOutCome="user/changePassword?faces-redirect=true";
			
		}
		  
	    	
			return fromOutCome;
	    	
	    }
	

}
