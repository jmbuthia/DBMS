package helperBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

import dataManager.Age;
import dataManager.AgeCalculator;
import dataManager.UserDB;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class ExpiryDateCounter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getDaysToExpire() {
		daysToExpire=this.DaysToExpire();
		return daysToExpire;
	}
	public void setDaysToExpire(String daysToExpire) {
		this.daysToExpire = daysToExpire;
	}
	private String daysToExpire;
	
	public String DaysToExpire(){
		HttpSession session= SessionBean.getSession();
		 RegistrationDetails rd=(RegistrationDetails)session.getAttribute("registrationDetails");
		 String idNo=rd.getIdNo();
		 System.out.println("id number in Expirlydate is "+idNo);
		 ArrayList<DrivingLicenseDetails> list=UserDB.getMyLicenseDetails(idNo);
		 Date d=list.get(0).getDrivingLicenseExpiryDate();
		System.out.println("date is "+d);
		String dateIs="";
		Age age=AgeCalculator.calculateAgeAfter(d);
		if(age.getYears()<0){
			dateIs="Already Expired";
			
		}else {
			dateIs=age.getYears()+" Years "+age.getMonths()+" Months "+age.getDays()+"Days.";
		}
		
		return dateIs;
	}

}
