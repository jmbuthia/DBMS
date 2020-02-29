package helperBeans;


import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import dataManager.UserDB;
@SuppressWarnings("deprecation")
@ManagedBean(name="registrationDetails")
@RequestScoped
public class RegistrationDetails implements Serializable{

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
	
	
	public RegistrationDetails(){
		
	}
	
	public RegistrationDetails(String idNo, String firstName, String profilePicture, String category, String phone) {
		super();
		this.idNo = idNo;
		this.firstName = firstName;
		this.profilePicture = profilePicture;
		this.category = category;
		this.phone = phone;
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
			this.password = AES_EncryptDecrypt.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error encrypting the password");
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

	public RegistrationDetails(String idNo, String firstName, String middleName, String lastName, String password,
			String profilePicture, String category, Date dob, String phone, String gender) {
		super();
		this.idNo = idNo;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.password = password;
		this.profilePicture = profilePicture;
		this.category = category;
		this.dob = dob;
		this.phone = phone;
		this.gender = gender;
	}
	
	
	
	public String register(){
		
		System.out.println("inside register method");
		int isSaved=UserDB.saveRegistrationDetails(new RegistrationDetails(idNo, firstName, middleName, lastName, password, "images/profile/defaultprofilepicture.jpg", "general", dob, phone, gender));
		
		if(isSaved>0){
			RequestContext.getCurrentInstance().update("growl");  
	     	
	   	 FacesContext.getCurrentInstance().addMessage(
	               null,
	               new FacesMessage(FacesMessage.SEVERITY_INFO,
	                       "Success",
	                       "Data saved successfully, login now."));
	   	 return "index?faces-redirect=true";
		}
		
		else {
			 RequestContext.getCurrentInstance().update("growl");  
	     	
	    	 FacesContext.getCurrentInstance().addMessage(
	                null,
	                new FacesMessage(FacesMessage.SEVERITY_ERROR,
	                        "Error",
	                        "Error occured while savind your details, please try again."));
	    	 return "registrationDetails?faces-redirect=true";
		}
		
	}

}
