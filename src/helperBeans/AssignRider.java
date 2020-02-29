package helperBeans;

import java.io.Serializable;

public class AssignRider implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String riderId;
	private String firstName;
	private String lastName;
	private String drivingLicenseNo;
	private String exiresOn;
	public String getRiderId() {
		return riderId;
	}
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDrivingLicenseNo() {
		return drivingLicenseNo;
	}
	public void setDrivingLicenseNo(String drivingLicenseNo) {
		this.drivingLicenseNo = drivingLicenseNo;
	}
	public String getExiresOn() {
		return exiresOn;
	}
	public void setExiresOn(String exiresOn) {
		this.exiresOn = exiresOn;
	}
	
	

}
