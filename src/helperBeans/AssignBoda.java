package helperBeans;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import dataManager.SendSms;
import dataManager.UserDB;
@SuppressWarnings("deprecation")
@ManagedBean
public class AssignBoda implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String riderDrivingLicenseNo;
	private String riderFirstName;
	private String riderLastName;
	private String ownerFirstName;
	private String ownerLastName;
	private String motorbikeNumberPlate;
	private String motorbikeModel;
	private String motorbikeExpiryDate;
	public String getRiderLastName() {
		return riderLastName;
	}
	public void setRiderLastName(String riderLastName) {
		this.riderLastName = riderLastName;
	}
	public String getRiderDrivingLicenseNo() {
		return riderDrivingLicenseNo;
	}
	public void setRiderDrivingLicenseNo(String riderDrivingLicenseNo) {
		this.riderDrivingLicenseNo = riderDrivingLicenseNo;
	}
	public String getRiderFirstName() {
		return riderFirstName;
	}
	public void setRiderFirstName(String riderFirstName) {
		this.riderFirstName = riderFirstName;
	}
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public String getMotorbikeNumberPlate() {
		return motorbikeNumberPlate;
	}
	public void setMotorbikeNumberPlate(String motorbikeNumberPlate) {
		this.motorbikeNumberPlate = motorbikeNumberPlate;
	}
	public String getMotorbikeModel() {
		return motorbikeModel;
	}
	public void setMotorbikeModel(String motorbikeModel) {
		this.motorbikeModel = motorbikeModel;
	}
	public String getMotorbikeExpiryDate() {
		return motorbikeExpiryDate;
	}
	public void setMotorbikeExpiryDate(String motorbikeExpiryDate) {
		this.motorbikeExpiryDate = motorbikeExpiryDate;
	}
	public AssignBoda() {
		
	}
	public AssignBoda(String riderDrivingLicenseNo, String riderFirstName, String riderLastName, String ownerFirstName,
			String ownerLastName, String motorbikeNumberPlate, String motorbikeModel, String motorbikeExpiryDate) {
		super();
		this.riderDrivingLicenseNo = riderDrivingLicenseNo;
		this.riderFirstName = riderFirstName;
		this.riderLastName = riderLastName;
		this.ownerFirstName = ownerFirstName;
		this.ownerLastName = ownerLastName;
		this.motorbikeNumberPlate = motorbikeNumberPlate;
		this.motorbikeModel = motorbikeModel;
		this.motorbikeExpiryDate = motorbikeExpiryDate;
	}
public void assign(){
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	String motorbikeNumberPlate=request.getParameter("motorbikeNumberPlate");
	String riderDrivingLicenseNo=request.getParameter("licenseNo");
	String riderFirstName=request.getParameter("riderFirstName");
	String riderLastName=request.getParameter("riderLastName");
	String ownerFirstName=request.getParameter("ownerFirstName");
	String ownerLastName=request.getParameter("ownerLastName");
	System.out.println("motorbike= "+motorbikeNumberPlate+" \n dl= "+riderDrivingLicenseNo+" \n name= "
	+riderFirstName+" \n l name= "+riderLastName+" \n owner f name= "+ownerFirstName+" \n owner l name= "+ownerLastName);
	int isSaved=UserDB.assignBodaToRider(riderDrivingLicenseNo, motorbikeNumberPlate);
	if(isSaved>0){
			  
		  
		RequestContext.getCurrentInstance().update("growl");  
     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Rider is Assigned motorbike successfully"));
	   	 //sending notification to rider and owner
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getBodabodaOwnerIdNo(motorbikeNumberPlate)), 
				  "Dear "+ownerFirstName+" "+ownerLastName+" your motorbike "+motorbikeNumberPlate+" have been assigned to "+riderFirstName+" "+riderLastName+" as a rider."));
		  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getRiderIdNo(riderDrivingLicenseNo)), 
				  "Dear "+riderFirstName+" "+riderLastName+" you have been assigned motorbike "+motorbikeNumberPlate+" belongs to "+ownerFirstName+" "+ownerLastName+"."));
		  try {
			  executor.shutdown();
			  System.out.println("Trying to wait thread to finish");
			executor.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Failed to wait for some minutes to shutdown executor");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else {
		RequestContext.getCurrentInstance().update("growl");  
     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                       "Error",
	                       "An error occured while Saving the data, please try again."));

	}
}

public void unAssign(){
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	String motorbikeNumberPlate=request.getParameter("motorbikeNumberPlate");
	String riderDrivingLicenseNo=request.getParameter("licenseNo");
	String timeAssigned=request.getParameter("timeAssigned");
	
	System.out.println("motorbike= "+motorbikeNumberPlate+" \n dl= "+riderDrivingLicenseNo+" \n time Assigned=  "+timeAssigned+"");
	int isSaved=UserDB.unAssignBodaToRider(riderDrivingLicenseNo,timeAssigned, motorbikeNumberPlate);
	if(isSaved>0){
			  
		  
		RequestContext.getCurrentInstance().update("growl");  
     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Rider is Un-Assigned from motorbike successfully"));
	   	 //sending notification to rider and owner
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getBodabodaOwnerIdNo(motorbikeNumberPlate)), 
				  "Your motorbike "+motorbikeNumberPlate+" have been un-assigned to the current rider."));
		  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getRiderIdNo(riderDrivingLicenseNo)), 
				  "You have been un-assigned motorbike "+motorbikeNumberPlate+"."));
		  try {
			  executor.shutdown();
			  System.out.println("Trying to wait thread to finish");
			executor.awaitTermination(10,TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Failed to wait for some minutes to shutdown executor");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else {
		RequestContext.getCurrentInstance().update("growl");  
     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                       "Error",
	                       "An error occured while Un-Assigning rider, please try again."));

	}
}


}
