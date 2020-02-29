package logicController;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dataManager.UserDB;
import helperBeans.RegistrationDetails;
import helperBeans.SessionBean;

@SuppressWarnings("deprecation")
@ManagedBean(name="login")

@SessionScoped
public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idNo;
	private String firstName;
	private String password;
	private String profilePicture;
	private String category;
	private String phone;
	private String lastName;
	boolean loggedIn;

	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String login(){
		System.out.println("Called login method");
		String group="";
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		group=request.getParameter("category");
		
	if(group.equals("nonStaff")){
		
		 int valid = UserDB.validate(idNo, password);
		 if(UserDB.userIdNoExists(idNo)==1){
			 if(UserDB.getCategory(idNo).equals("general")||
					 UserDB.getCategory(idNo).equals("owner/rider")||
					 UserDB.getCategory(idNo).equals("rider")||
					 UserDB.getCategory(idNo).equals("owner")||
					 UserDB.getCategory(idNo).equals("admin")||
					 UserDB.getCategory(idNo).equals("teller")){
			 
			 if (valid==1) {
		            HttpSession session = SessionBean.getSession();
		            RegistrationDetails rd=UserDB.getRegistrationDetails(idNo, password);
		            session.setAttribute("registrationDetails", rd);
		            session.setAttribute("disable", "true");
		            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("registrationDetails",rd);
		        	
		            setIdNo(rd.getIdNo());
		            setFirstName(rd.getFirstName());
		            setProfilePicture(rd.getProfilePicture());
		            setCategory(rd.getCategory());
		            setPhone(rd.getPhone());
		            setLastName(rd.getLastName());
		            loggedIn = true;
		            String navigate="";
		            if(UserDB.getCategory(idNo).equals("general")){
		            	navigate= "user/generalUser?faces-redirect=true";
		            }
		            if(UserDB.getCategory(idNo).equals("owner")){
		            	navigate= "user/owner/ownerHome?faces-redirect=true";	
		            }
		            if(UserDB.getCategory(idNo).equals("rider")){
		            	navigate= "user/rider/riderHome?faces-redirect=true";
		            }
		            if(UserDB.getCategory(idNo).equals("owner/rider")){
		            	navigate= "user/ownerRider/ownerRiderHome?faces-redirect=true";	
		            }
		            
		            
		            
		            
		            if(UserDB.getCategory(idNo).equals("teller")){
		            	navigate= "teller/tellerHome?faces-redirect=true";
		            }
		            if(UserDB.getCategory(idNo).equals("admin")){
		            	navigate= "admin/adminHome?faces-redirect=true";	
		            }
		            
		            
		            System.out.println("navigation is "+navigate);
		            return navigate;
		            
		            
		        } else if(valid==0) {
		        	   RequestContext.getCurrentInstance().update("growl,messages");  
		        	
		        	 FacesContext.getCurrentInstance().addMessage(
		                    null,
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                            "Error",
		                            "Incorrect id or Passoword, Please enter correct id and Password"));
		        	 loggedIn = false;
		            return "login";
		        }
		        else if(valid==-1) {
		        	   RequestContext.getCurrentInstance().update("growl,messages");  
		        	
		        	 FacesContext.getCurrentInstance().addMessage(
		                    null,
		                    new FacesMessage(FacesMessage.SEVERITY_WARN,
		                            "Sorry",
		                            "Please try again later, we are currently updating our database."));
		        	 loggedIn = false;
		            return "login";
		        }
			 }else {
				
				 RequestContext.getCurrentInstance().update("growl,messages");  
		        	
	        	 FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                            "Error",
	                            "Staff uses staff section to login, Please navigate to home page and expand icon on the left."));
	        	 loggedIn = false;
	            return "login";
				 
				 
				 
			}
		    
			 
		
		 
		 }else  {
			 if(UserDB.userIdNoExists(idNo)==0){
				 RequestContext.getCurrentInstance().update("growl,messages");  
		        	
		       	 FacesContext.getCurrentInstance().addMessage(
		                   null,
		                   new FacesMessage(FacesMessage.SEVERITY_WARN,
		                           "Error",
		                           "IdNo does not exist, Please register first."));
		       	 loggedIn = false;
		           return "register";
			 }else if(UserDB.userIdNoExists(idNo)==-1) {
				 RequestContext.getCurrentInstance().update("growl,messages");  
		        	
		       	 FacesContext.getCurrentInstance().addMessage(
		                   null,
		                   new FacesMessage(FacesMessage.SEVERITY_WARN,
		                           "Sorry",
		                           "Please try again later, we are currently updating our database."));
		       	 loggedIn = false;
		           return "login";
				 
				
			}
			 
			
		}
		 
		
		
		
		
		
		
	}else {/*
		
		//staff
		
		 boolean valid = UserDB.validate(idNo, password);
		 if(UserDB.userIdNoExists(idNo)){
			 if(UserDB.getCategory(idNo).equals("admin")||
					 UserDB.getCategory(idNo).equals("teller")){
			 
			 if (valid) {
		            HttpSession session = SessionBean.getSession();
		            RegistrationDetails rd=UserDB.getRegistrationDetails(idNo, password);
		            session.setAttribute("registrationDetails", rd);
		            session.setAttribute("disable", "true");		        	
		            setIdNo(rd.getIdNo());
		            setFirstName(rd.getFirstName());
		            setProfilePicture(rd.getProfilePicture());
		            setCategory(rd.getCategory());
		            setPhone(rd.getPhone());
		            setLastName(rd.getLastName());
		            String navigate="";
		            loggedIn = true;
		            if(UserDB.getCategory(idNo).equals("teller")){
		            	navigate= "teller/tellerHome?faces-redirect=true";
		            }
		            if(UserDB.getCategory(idNo).equals("admin")){
		            	navigate= "admin/adminHome?faces-redirect=true";	
		            }
		            System.out.println("navigation is "+navigate);
		            return navigate;
		           
		        } else {
		        	   RequestContext.getCurrentInstance().update("growl");  
		        	
		        	 FacesContext.getCurrentInstance().addMessage(
		                    null,
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                            "Error",
		                            "Incorrect id or Passoword, Please enter correct id and Password"));
		        	 loggedIn = false;
		            return "#";
		        }
			 }else {
				
				 RequestContext.getCurrentInstance().update("growl");  
		        	
	        	 FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_WARN,
	                            "Error",
	                            "Only Staff are allowed to use that section, login here."));
	        	 loggedIn = false;
	            return "login";
				 
				 
				 
			}
		    
			 
		
		 
		 }else {
			 RequestContext.getCurrentInstance().update("growl");  
	        	
       	 FacesContext.getCurrentInstance().addMessage(
                   null,
                   new FacesMessage(FacesMessage.SEVERITY_WARN,
                           "Error",
                           "IdNo does not exist, Please register first."));
       	 loggedIn = false;
           return "register";
			
		}
		 
		
		
		
		
		
	*/  
		}
	return "#";
	       }
	 
	    //logout event, invalidate session
	    public String logout() {
	    	System.out.println("logout button pressed");
	        HttpSession session = SessionBean.getSession();
	        session.invalidate();
	        return "";
	    }
	    public String changeProfilePicture(){
	    	 HttpSession session = SessionBean.getSession();
	    	 String url="";
	    	 RegistrationDetails details=(RegistrationDetails) session.getAttribute("registrationDetails");
	    	 String name=(String) session.getAttribute("name");
	    	 int x=UserDB.setProfilePicture(details.getIdNo(), details.getPassword(),"images/profile/"+name);
	    			 if(x>0){

	    		    	 session.removeAttribute("registrationDetails");
	    		    	 
	    		            RegistrationDetails rd=UserDB.getRegistrationDetails(idNo, password);
	    		            session.setAttribute("registrationDetails", rd);
	    		            
	    		            setIdNo(rd.getIdNo());
	    		            setFirstName(rd.getFirstName());
	    		            setProfilePicture(rd.getProfilePicture());
	    		            setCategory(rd.getCategory());
	    		            setPhone(rd.getPhone());
	    		            setLastName(rd.getLastName());
	    		    	
	    				 FacesContext.getCurrentInstance().addMessage(null, 
	    						 new FacesMessage(
	    								 FacesMessage.SEVERITY_INFO, "Success", "profile Picture changed."));
	    				url= "user/generalUser?faces-redirect=true";
	    				 }
	    			 else {
	    				 FacesContext.getCurrentInstance().addMessage(null, 
	    						 new FacesMessage(
	    								 FacesMessage.SEVERITY_ERROR, "solly", "failed while changing profile Picture."));
	    				 url="user/cropImage";
	    			 }
					
	    			 return url;	
					}
	    	 
	  
}
