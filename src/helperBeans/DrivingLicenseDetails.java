package helperBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import dataManager.Age;
import dataManager.AgeCalculator;
import dataManager.UserDB;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class DrivingLicenseDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idNo;
	private String id;
	private Date renewedExpiryDate;
	public Date getRenewedExpiryDate() {
		return renewedExpiryDate;
	}
	public void setRenewedExpiryDate(Date renewedExpiryDate) {
		this.renewedExpiryDate = renewedExpiryDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String drivingLicenseNo;
	private String referenceNo;
	private String status;
	private Date drivingLicenseExpiryDate;
	private String isAssigned;
	public String getIsAssigned() {
		return isAssigned;
	}
	public void setIsAssigned(String isAssigned) {
		this.isAssigned = isAssigned;
	}
	public String getIdNo() {
		 HttpSession session= SessionBean.getSession();
		 RegistrationDetails rd=(RegistrationDetails)session.getAttribute("registrationDetails");
		 idNo=rd.getIdNo();
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public Date getDrivingLicenseExpiryDate() {
		return drivingLicenseExpiryDate;
	}
	public void setDrivingLicenseExpiryDate(Date drivingLicenseExpiryDate) {
		this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public DrivingLicenseDetails() {
		
	}
	
	public DrivingLicenseDetails(String idNo, String drivingLicenseNo, String referenceNo, String status,
			Date drivingLicenseExpiryDate, String isAssigned) {
		super();
		this.idNo = idNo;
		this.drivingLicenseNo = drivingLicenseNo;
		this.referenceNo = referenceNo;
		this.status = status;
		this.drivingLicenseExpiryDate = drivingLicenseExpiryDate;
		this.isAssigned = isAssigned;
	}
	
	public String minimumAge(){
		 long currentTime = System.currentTimeMillis();
	    Calendar now = Calendar.getInstance();
	    now.setTimeInMillis(currentTime);
		int years = now.get(Calendar.YEAR) ;
	    int currMonth = now.get(Calendar.MONTH)+1;
	    int days = now.get(Calendar.DATE);
	    String min=years+"/"+currMonth+"/"+days;

	    return min;
	    
	}

	public String maximumAge(){
		 long currentTime = System.currentTimeMillis();
	    Calendar now = Calendar.getInstance();
	    now.setTimeInMillis(currentTime);
		int years = now.get(Calendar.YEAR)+3;
	    int currMonth = now.get(Calendar.MONTH) + 1;
	    int days = now.get(Calendar.DATE);
	    String max=years+"/"+currMonth+"/"+days;
	    
	    return max;
	  
	    
	}
	public String yearmin(){
		 long currentTime = System.currentTimeMillis();
	    Calendar now = Calendar.getInstance();
	    now.setTimeInMillis(currentTime);
		int years = now.get(Calendar.YEAR) ;
	 
	    return String.valueOf(years);
	    
	}
	public String yearmax(){
		 long currentTime = System.currentTimeMillis();
	    Calendar now = Calendar.getInstance();
	    now.setTimeInMillis(currentTime);
		int years = now.get(Calendar.YEAR) +3;
	 
	    return String.valueOf(years);
	    
	}
	public String DaysToExpire(){
		System.out.println("DaysToExpire() is called");
		String dateIs="";
		Age age=AgeCalculator.calculateAgeAfter(this.drivingLicenseExpiryDate);
		if(age.getYears()<0){
			dateIs="Already Expired";
			
		}else {
			if(age.getYears()==0&&age.getMonths()==0&&age.getDays()==0){
				dateIs="Will Expire Today";	
			}else {
				dateIs=age.getYears()+" Years "+age.getMonths()+" Months "+age.getDays()+" Days.";
			}
			
		}
		
		return dateIs;
	}



	public String addLicense(){
		
		System.out.println("inside addLicense method");
		String url="";
		System.out.println("test1"+UserDB.drivingLicenseIdNumberExistsInRequest(this.getIdNo()));
		System.out.println("test2"+UserDB.drivingLicenseIdNumberExists(this.getIdNo()));
		if(UserDB.drivingLicenseIdNumberExistsInRequest(this.getIdNo())||UserDB.drivingLicenseIdNumberExists(this.getIdNo())){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "Sorry you cannot have two Driving License, its also a crime in Kenya. If you have sent the request earlier visit DBMS offices with the Driving License."));
		   	url= "generalUser";
			
		}
else {
	
	if(UserDB.drivingLicenseNumberExists(drivingLicenseNo)||UserDB.drivingLicenseNumberExistsInRequest(drivingLicenseNo)){
		RequestContext.getCurrentInstance().update("growl");  
     	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Error",
                       "Driving License already exists, if you have sent the request earlier visit DBMS offices with the Driving License."));
   	url= "requestAddLicense";
	
	}
	else{
		Age age = AgeCalculator.calculateAgeAfter(drivingLicenseExpiryDate);
		System.out.println("Age is"+ age);
		if(age.getYears()>=3&&age.getMonths()>=0&&age.getDays()>0||age.getYears()<0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "Driving License must be renewed and not more than 3 years after today."));
		   	url= "requestAddLicense";
			
		}
		else {
			
		int isSaved=UserDB.saveRequestAddLicense(new DrivingLicenseDetails(idNo, drivingLicenseNo, referenceNo.toUpperCase(), "Pending", drivingLicenseExpiryDate, "false"));
		
		if(isSaved>0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Your Request was sent Successfully, Visit DBMS offices with Driving License."));
	   		  
	   	
	   	 url= "generalUser";
		}
		
		if(isSaved!=1) {
			 RequestContext.getCurrentInstance().update("growl");  
	     	
	    	 FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_FATAL,
	                        "Error",
	                        "Error occured while saving your details, please try again."));
		
	    	 url= "requestAddLicense";
		}
		
	}
		
				}
		
}
		
		return url;
	}
	
	public void renewHelper(){

		System.out.println("renewHelper");
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
		String idNo=request.getParameter("idNo");
		System.out.println(idNo);
		ArrayList<DrivingLicenseDetails> list=UserDB.getMyLicenseDetails(idNo);
		setDrivingLicenseExpiryDate(list.get(0).getDrivingLicenseExpiryDate());
		setDrivingLicenseNo(list.get(0).getDrivingLicenseNo());
		setId(list.get(0).id);
		setReferenceNo(list.get(0).getReferenceNo());
		
		
		
		
	}


public String renewLicense(){
		
		System.out.println("inside renewLicense method");
		String url="";
			
		
		if(this.DaysToExpire()!="Already Expired"){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "Sorry your Driving License is not Expired."));
		   	url= "licenseDetails";
			
		}
else {
	Age age = AgeCalculator.calculateAgeAfter(renewedExpiryDate);
	System.out.println("Age is"+ age);
	if(age.getYears()>=3&&age.getMonths()>=0&&age.getDays()>0||age.getYears()<0){
		RequestContext.getCurrentInstance().update("growl");  
     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_WARN,
	                       "Error",
	                       "Renewed Driving Expiry Date must be greater than today and not more than 3 years after today."));
		
	}else {
		int test=UserDB.renewLicense(drivingLicenseNo,renewedExpiryDate);
		if(test>0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_INFO,
		                       "Success",
		                       "Your Driving License Expiry Date was saved Successfully."));
		   		  
		   	
		   	 url= "licenseDetails";
		
			
		
		}
		else{RequestContext.getCurrentInstance().update("growl");  
	 	
		 FacesContext.getCurrentInstance().addMessage(
	           null,
	           new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                   "Error",
	                   "An error occured while saving your details, please try again."));
		url= "licenseDetails";		
		}

		
	}
			
				}
		

		
		return url;
	}

	
}
