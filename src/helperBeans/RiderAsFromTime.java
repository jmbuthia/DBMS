package helperBeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dataManager.UserDB;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class RiderAsFromTime implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private Date timeAssigned;
	 private Date timeUnAssigned;
	 private String riderFirstName;
	 private String riderLastName;
	 private String riderPhoneNumber;
	 private String ownerPhoneNumber;
	 private String ownerIdNo;
	 private String motorbikeNumberPlate;
	 
	 public Date getTimeAssigned() {
		return timeAssigned;
	}
	public void setTimeAssigned(Date timeAssigned) {
		this.timeAssigned = timeAssigned;
	}
	public Date getTimeUnAssigned() {
		return timeUnAssigned;
	}
	public void setTimeUnAssigned(Date timeUnAssigned) {
		this.timeUnAssigned = timeUnAssigned;
	}
	public String getRiderFirstName() {
		return riderFirstName;
	}
	public void setRiderFirstName(String riderFirstName) {
		this.riderFirstName = riderFirstName;
	}
	public String getRiderLastName() {
		return riderLastName;
	}
	public void setRiderLastName(String riderLastName) {
		this.riderLastName = riderLastName;
	}
	public String getRiderPhoneNumber() {
		return riderPhoneNumber;
	}
	public void setRiderPhoneNumber(String riderPhoneNumber) {
		this.riderPhoneNumber = riderPhoneNumber;
	}
	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}
	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}
	public String getOwnerIdNo() {
		return ownerIdNo;
	}
	public void setOwnerIdNo(String ownerIdNo) {
		this.ownerIdNo = ownerIdNo;
	}
	public String getMotorbikeNumberPlate() {
		return motorbikeNumberPlate;
	}
	public void setMotorbikeNumberPlate(String motorbikeNumberPlate) {
		this.motorbikeNumberPlate = motorbikeNumberPlate;
	}
	public ArrayList<RiderAsFromTime> getAllRiderOfBodaAsFromTime(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		 return UserDB.getAllRiderOfBodaAsFromTime(this.motorbikeNumberPlate,sdf.format(timeAssigned),sdf.format(timeUnAssigned));
	}
	public ArrayList<RiderAsFromTime> getMyAssignedBoda(){
		
		RegistrationDetails rd=(RegistrationDetails) SessionBean.getSession().getAttribute("registrationDetails");
	
		 return UserDB.getMyAssignedBoda(rd.getIdNo());
	}
public ArrayList<RiderAsFromTime> getMyBodaRiders(){
		
		RegistrationDetails rd=(RegistrationDetails) SessionBean.getSession().getAttribute("registrationDetails");
	
		 return UserDB.getMyBodaRiders(rd.getIdNo());
	}
public String minimumAge(){
	 long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR)  ;
    int currMonth = now.get(Calendar.MONTH) + 1;
    int days = now.get(Calendar.DATE);
    String min=years+"/"+currMonth+"/"+days;

    return min;
    
}

public String maximumAge(){
	 long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) ;
    int currMonth = now.get(Calendar.MONTH) -2;
    int days = now.get(Calendar.DATE);
    String max=years+"/"+currMonth+"/"+days;
    
    return max;
  
    
}
public String yearmin(){
	 long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR);
 
    return String.valueOf(years);
    
}
public String yearmax(){
	 long currentTime = System.currentTimeMillis();
    Calendar now = Calendar.getInstance();
    now.setTimeInMillis(currentTime);
	int years = now.get(Calendar.YEAR) -1;
 
    return String.valueOf(years);
    
}


}
