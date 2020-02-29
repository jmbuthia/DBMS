package helperBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dataManager.SendSms;
import dataManager.UserDB;


@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped

public class ForgotPassword implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String idNo;
private String confirmationCode;
private Date dob;
private String newPassword;
public Date getDob() {
	return dob;
}
public void setDob(Date dob) {
	this.dob = dob;
}
public String getIdNo() {
	return idNo;
}
public void setIdNo(String idNo) {
	this.idNo = idNo;
}
public String getConfirmationCode() {
	 
	 return confirmationCode;
}
public void setConfirmationCode(String confirmationCode) {
	this.confirmationCode = confirmationCode;
}
public String getNewPassword() {
	return newPassword;
}
public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
}
public String sendConfirmationCode(){
	System.out.println("Called sendConfirmCode method");
	System.out.println("idNo is "+idNo);
	System.out.println("dob is "+dob);
	
	
	String url="";
	String c=AES_EncryptDecrypt.getRandomNumber();
	
	System.out.println("confirmation code is "+c);
	
	if(UserDB.isFogotPasswordDetailsSet(idNo)){
		UserDB.deleteForgotPasswordDetails(idNo);
		
	}
	
	 if(UserDB.userIdNoExists(idNo)==1){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 RegistrationDetails rd=UserDB.getFogotPasswordDetails(idNo, sdf.format(dob));
		 System.out.println("test null"+ rd.getIdNo());
		 		 if(rd.getIdNo().equals(idNo)){
			 
			 int x=UserDB.saveFogotPasswordDetails(idNo, c);
			 if(x>0){
				 
				 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
				  executor.execute(new SendSms(rd.getPhone(), 
						  "Dear "+rd.getFirstName()+" use "+c+" as confirmation code while setting your new password to DBMS."));
				  url="resetPassword?faces-redirect=true";
				  
			 }else {
				 RequestContext.getCurrentInstance().update("growl");  
			     	
		    	 FacesContext.getCurrentInstance().addMessage(
		                null,
		                new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                        "Error",
		                        "An error occured while saving the data, try again"));
				url="#";
				
			}
			 
			 
			 
		 }else {
			 RequestContext.getCurrentInstance().update("growl");  
		     	
	    	 FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        "Error",
	                        "Date should match the one you use while registering."));
			url="#";
			
		}
		 
		 
	 }else {
		 RequestContext.getCurrentInstance().update("growl");  
     	
    	 FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Error",
                        "User Account does not exists, Please register"));
		url="#";
	}
	return url;
	
}

public String resetPassword(){
	String url="";
	String confirmCode=UserDB.getConfirmationCode(idNo);
	System.out.println("inside reset password in managedbean");
	System.out.println("idno is "+idNo);
	System.out.println("confirmaion code is "+confirmationCode);
	System.out.println("confirmcode is "+confirmCode);
	if(confirmCode.equals(confirmationCode)){

		int test=UserDB.resetPassword(idNo, confirmationCode, newPassword);
		if(test>0){
			UserDB.deleteForgotPasswordDetails(idNo);
			
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_INFO,
		                       "Congraturation",
		                       "Password was reset successfully, use your id number and password you have set to login."));
		   	 
				url="login?faces-redirect=true";
				
				HttpSession session= SessionBean.getSession();
			   	 session.setMaxInactiveInterval(10);
			   	
			   	 session.setAttribute("message","You reset password successfully, please login using the current password.");
			
			
		}else {
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "An error occured while resetting your password."));
				url="#";
			
		}
		
	}else {
		RequestContext.getCurrentInstance().update("growl");  
     	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Error",
                       "Confirmation Code should math the one you were sent through S.M.S"));
		url="#";
		
	}
	
	
	return url;
	
}



}
