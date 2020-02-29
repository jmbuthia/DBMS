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
public class MotorcycleDetails implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String idNo;
private String numberPlate;
private String engineNo;
private String frameNo;
private String model;
private String status;
private String isAssigned;
private Date insuranceExpiryDate;
private Date renewedExpiryDate;
private double amount;

public Date getRenewedExpiryDate() {
	return renewedExpiryDate;
}
public void setRenewedExpiryDate(Date renewedExpiryDate) {
	this.renewedExpiryDate = renewedExpiryDate;
}
private String id;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
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
public String getNumberPlate() {
	return numberPlate;
}
public void setNumberPlate(String numberPlate) {
	this.numberPlate = numberPlate;
}
public String getEngineNo() {
	return engineNo;
}
public void setEngineNo(String engineNo) {
	this.engineNo = engineNo;
}
public String getFrameNo() {
	return frameNo;
}
public void setFrameNo(String frameNo) {
	this.frameNo = frameNo;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getIsAssigned() {
	return isAssigned;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public void setIsAssigned(String isAssigned) {
	this.isAssigned = isAssigned;
}
public Date getInsuranceExpiryDate() {
	return insuranceExpiryDate;
}
public void setInsuranceExpiryDate(Date insuranceExpiryDate) {
	this.insuranceExpiryDate = insuranceExpiryDate;
}
public String minimumAge(){
	 long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) ;
    int currMonth = now.get(Calendar.MONTH) + 1;
    int days = now.get(Calendar.DATE);
    String min=years+"/"+currMonth+"/"+days;

    return min;
    
}

public String maximumAge(){
	 long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR)+1;
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
	int years = now.get(Calendar.YEAR) +1;
 
    return String.valueOf(years);
    
}

public String DaysToExpire(){
	System.out.println("DaysToExpire() is called");
	System.out.println("insuranceExpiryDate is =="+ insuranceExpiryDate);
	String dateIs="";
	Age age=AgeCalculator.calculateAgeAfter(insuranceExpiryDate);
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


public String renewInsurance(){
		
		System.out.println("inside renewInsurance method");
		String url="";
		System.out.println("this.DaysToExpire()!=Already Expired == "+this.DaysToExpire());	
		
		if(this.DaysToExpire()!="Already Expired"){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "Sorry your Insurance certificate is not Expired."));
		   	url= "motorbikeDetails";
			
		}
else {
	Age age = AgeCalculator.calculateAgeAfter(renewedExpiryDate);
	System.out.println("Age is"+ age);
	if(age.getYears()>=1&&age.getMonths()>=0&&age.getDays()>0||age.getYears()<0){
		RequestContext.getCurrentInstance().update("growl");  
     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_WARN,
	                       "Error",
	                       "Renewed Insurance Expiry Date must be greater than today and not more than 1 years after today."));
		
	}else {

		int test=UserDB.renewInsurance(numberPlate,renewedExpiryDate);
		if(test>0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_INFO,
		                       "Success",
		                       "Your motorbike Insurance Expiry Date was saved Successfully."));
		   		  
		   	
		   	 url= "motorbikeDetails";
		
			
		
		}
		else{RequestContext.getCurrentInstance().update("growl");  
	 	
		 FacesContext.getCurrentInstance().addMessage(
	           null,
	           new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                   "Error",
	                   "An error occured while saving your details, please try again."));
		url= "motorbikeDetails";		
		}
			

		
	}				}
		

		
		return url;
	}


public ArrayList<MotorcycleDetails> getAllMyBodaAmount(){
	
	 return UserDB.getAllMyBodaAmount(getIdNo());
}

public String addBoda(){
	System.out.println("inside addBoda method");
	String url="";
	if(UserDB.motorbikeNumberPlateExists(numberPlate.toUpperCase())||UserDB.motorbikeNumberPlateExistsInRequest(numberPlate.toUpperCase())){
		RequestContext.getCurrentInstance().update("growl");  
     	
   	 FacesContext.getCurrentInstance().addMessage(
               null,
               new FacesMessage(FacesMessage.SEVERITY_ERROR,
                       "Error",
                       "Motorbike already exists, if you have sent the request earlier visit DBMS offices with the Logbook."));
   	url= "requestAddBoda";
	
	}
	else{
		Age age = AgeCalculator.calculateAgeAfter(insuranceExpiryDate);
		System.out.println("Age is"+ age);
		if(age.getYears()>=1&&age.getMonths()>=0&&age.getDays()>0||age.getYears()<0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_WARN,
		                       "Error",
		                       "Insurance must be renewed and not more than 1 years after today."));
			
		}else {
			
		
	
		
		int isSaved=UserDB.saveRequestAddBoda(new MotorcycleDetails(idNo,numberPlate.toUpperCase(), engineNo.toUpperCase(), frameNo.toUpperCase(), model.toUpperCase(),"Pending","false", insuranceExpiryDate));
		
		if(isSaved>0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Your Request was sent Successfully, Visit DBMS offices with Logbook and the Motorbike, 2 helmets and reflectors."));
	   		  
	   	
	   	 url= "generalUser";
		}
		
		if(isSaved!=1) {
			 RequestContext.getCurrentInstance().update("growl");  
	     	
	    	 FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_FATAL,
	                        "Error",
	                        "Error occured while saving your details, please verify the data and try again."));
		
	    	 url= "requestAddBoda";
		}
		
	}
		//comment stop
		}
	return url;
}
public MotorcycleDetails() {
	
}
public MotorcycleDetails(String idNo, String numberPlate, String engineNo, String frameNo, String model, String status,
		String isAssigned, Date insuranceExpiryDate) {
	super();
	this.idNo = idNo;
	this.numberPlate = numberPlate;
	this.engineNo = engineNo;
	this.frameNo = frameNo;
	this.model = model;
	this.status = status;
	this.isAssigned = isAssigned;
	this.insuranceExpiryDate = insuranceExpiryDate;
}
public void renewHelper(){
setInsuranceExpiryDate(null);
	System.out.println("renewHelper");
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	String model=request.getParameter("model");
	String numberPlate=request.getParameter("numberPlate");
	String insuranceExpiryDate=request.getParameter("insuranceExpiryDate");
	
	String[] days=insuranceExpiryDate.split("-");
	System.out.println("days"+days[0]);
	System.out.println("days"+days[1]);
	System.out.println("days"+days[2]);
	Date d =new Date(Integer.parseInt(days[0])-1900,Integer.parseInt(days[1])-1, Integer.parseInt(days[2]));
	
	System.out.println(d);
	Date d1=new Date();
	System.out.println(d1);
	
	System.out.println(numberPlate);
	setModel(model);
	setNumberPlate(numberPlate);
	setInsuranceExpiryDate(d);
	
	
}
public void transactionsViewer(){

	System.out.println("transactionsViewer");
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();

	String numberPlate=request.getParameter("numberPlate");
		
	System.out.println(numberPlate);

	setNumberPlate(numberPlate);

	
	
}
public ArrayList<Transactions> getAllMyBodaTransactions(){
	 
	 return UserDB.getAllMyBodaTransactions(getNumberPlate());
}

public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}



}
