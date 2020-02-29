package logicController;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dataManager.UserDB;
import helperBeans.AES_EncryptDecrypt;
import helperBeans.RegistrationDetails;
import helperBeans.SessionBean;
import dataManager.Age;
import dataManager.AgeCalculator;
import dataManager.SendSms;

@SuppressWarnings("deprecation")
@ManagedBean
@RequestScoped
public class Register implements Serializable{
		
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String idNo;
	private String firstName;
	private String middleName="";
	private String lastName;
	private String password;
	private String profilePicture;
	private String category;
	private Date dob;
	private String phone;
	private String gender;
	/*private String message=" ";
	*/

/*public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}*/


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


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPassword() {
	
		return password;
	}


	public void setPassword(String password) {
		try {
			this.password =AES_EncryptDecrypt.encrypt(password) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	public Date getDob() {
	
		return dob;
	}


	public void setDob(Date dob) {
		
		this.dob = dob;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


public String register(){
	System.out.println("inside register method");
	String url="";
	if(UserDB.userIdNoExists(idNo)==1){
		RequestContext.getCurrentInstance().update("growl");  
     	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Error",
                       "User already exists, please login."));
   	url= "register";
	
	}
	else{
		Age age = AgeCalculator.calculateAgeBefore(dob);
		System.out.println("Age is"+ age);
		//Date d1=new Date();
		//Date d2=new Date();
		if(age.getYears()<18){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "You must be 18 years and over."));
			
		}else {
			
		
		//comment start
		
		int isSaved=UserDB.saveRegistrationDetails(new RegistrationDetails(idNo, firstName, middleName, lastName, password, "images/profile/defaultprofilepicture.jpg", "general", dob, phone, gender));
		
		if(isSaved>0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Data saved successfully, login now."));
	   	 HttpSession session= SessionBean.getSession();
	   	 session.setMaxInactiveInterval(10);
	   	
	   	 session.setAttribute("message","You registered successfully, please login now.");
	   	 //sending notification to registered user
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(phone, 
				  "Thank you "+firstName+" for registering to DBMS. Use Id Number and password to login. Remember to keep your password secure."));
		  
		  try {
			  executor.shutdown();
			  System.out.println("Trying to wait thread to finish");
			executor.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Failed to wait for some minutes to shutdown executor");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	   	
	   	 url= "login?faces-redirect=true";
		}
		
		else if(isSaved==0) {
			 RequestContext.getCurrentInstance().update("growl");  
	     	
	    	 FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_FATAL,
	                        "Error",
	                        "Error occured while savind your details, please try again."));
		
	    	 url= "register";
		}
		else if(isSaved==-1) {
			 RequestContext.getCurrentInstance().update("growl,messages");  
	     	
		        	
	       	 FacesContext.getCurrentInstance().addMessage(
	                   null,
	                   new FacesMessage(FacesMessage.SEVERITY_WARN,
	                           "Sorry",
	                           "Please try again later, we are currently updating our database."));
		
	    	 url= "register";
		}
		
	}
		//comment stop
		}
	return url;
}
public String minimumAge(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) - 18;
     int currMonth = now.get(Calendar.MONTH) + 1;
     int days = now.get(Calendar.DATE);
     String min=years+"/"+currMonth+"/"+days;

     return min;
     
}

public String maximumAge(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -100;
     int currMonth = now.get(Calendar.MONTH) + 1;
     int days = now.get(Calendar.DATE);
     String max=years+"/"+currMonth+"/"+days;
     
     return max;
   
     
}
public String yearmin(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -18;
  
     return String.valueOf(years);
     
}
public String yearmax(){
	 long currentTime = System.currentTimeMillis();
     Calendar now = Calendar.getInstance();
     now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -100;
  
     return String.valueOf(years);
     
}
/*public static void main(String[] args) {
	Date d1 =new Date();
	Register r=new Register();
	r.setDob(new Date());
	System.out.println(r.getDob());
	Date d =new Date(90,8,1);
	Date d2 =new Date();
		
	System.out.println(r.getDob().getTime()-d.getTime());
	System.out.println(d.getTime()-d1.getTime());
	System.out.println(d2.getTime()-d.getTime());
	
}*/


}
