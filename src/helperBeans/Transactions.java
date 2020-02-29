package helperBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import dataManager.Age;
import dataManager.AgeCalculator;
import dataManager.NumberToWordsConverter;
import dataManager.SendSms;
import dataManager.UserDB;
@SuppressWarnings("deprecation")
@ManagedBean
public class Transactions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionId;
	private String nameOfDepositor;
	private String nameOfDepositor2;
	private String idNoOfDepositor;
	private String idNoOfDepositor2;
	private String numberPlate;
	private String numberPlate2;
	private double amount;
	private double totalamount;
	private Date dateDeposited;
	private String timeRecorded;
	private String disabled;
	
	public String getTimeRecorded() {
		return timeRecorded;
	}
	public void setTimeRecorded(String timeRecorded) {
		this.timeRecorded = timeRecorded;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getNameOfDepositor() {
		
		return nameOfDepositor;
	}
	public void setNameOfDepositor(String nameOfDepositor) {
		this.nameOfDepositor = nameOfDepositor;
	}
	public String getIdNoOfDepositor() {
		
		return idNoOfDepositor;
	}
	public void setIdNoOfDepositor(String idNoOfDepositor) {
		this.idNoOfDepositor = idNoOfDepositor;
	}
	public String getNumberPlate() {
		
		return numberPlate;
	}
	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDateDeposited() {
		return dateDeposited;
	}
	public void setDateDeposited(Date dateDeposited) {
		this.dateDeposited = dateDeposited;
	}
	public String getNameOfDepositor2() {
		RegistrationDetails rd=(RegistrationDetails) SessionBean.getSession().getAttribute("registrationDetails");
		nameOfDepositor2=rd.getFirstName()+" "+rd.getLastName();
		return nameOfDepositor2;
	}
	public void setNameOfDepositor2(String nameOfDepositor2) {
		this.nameOfDepositor2 = nameOfDepositor2;
	}
	public String getIdNoOfDepositor2() {
		RegistrationDetails rd=(RegistrationDetails) SessionBean.getSession().getAttribute("registrationDetails");
		idNoOfDepositor2=rd.getIdNo();
		return idNoOfDepositor2;
	}
	
	public void setIdNoOfDepositor2(String idNoOfDepositor2) {
		this.idNoOfDepositor2 = idNoOfDepositor2;
	}
	public String getNumberPlate2() {
		RegistrationDetails rd=(RegistrationDetails) SessionBean.getSession().getAttribute("registrationDetails");
		numberPlate2=UserDB.getNumberPlate(rd.getIdNo());
		return numberPlate2;
	}
	public void setNumberPlate2(String numberPlate2) {
		this.numberPlate2 = numberPlate2;
	}
	public double getTotalAmount() {
		
		return getTotalamount();
	}
	
	public void saveDeposit(){
		String timeAssigned=UserDB.getTimeAssigned(getIdNoOfDepositor2(), getNumberPlate2());
		if(timeAssigned.equals("0")||timeAssigned.equals("-1")){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "You record Payment from the time you were Assigned boda, please try with correct date."));
			
		}else {
			Age age = AgeCalculator.calculateAgeBefore(dateDeposited);
			System.out.println("Age= "+age);
			System.out.println("timeAssigned"+timeAssigned);
			String[] dtime=timeAssigned.split(" ");
			String[] dt=dtime[0].split("-");
			Date d=new Date(Integer.parseInt(dt[0])-1900,Integer.parseInt(dt[1])-1, Integer.parseInt(dt[2]), 00, 00, 00);
			//Date date1=new Date(Integer.parseInt(dt[0])-1900,Integer.parseInt(dt[1]), Integer.parseInt(dt[2]));
			//System.out.println("date1"+date1);
			System.out.println("year"+dt[0]);
			System.out.println("month"+dt[1]);
			System.out.println("day"+dt[2]);
			System.out.println("dateDeposited"+dateDeposited);
			System.out.println("d"+d);
			Age age1 = AgeCalculator.calculateAgeBefore(d);
			System.out.println("Age1= "+age1);
			System.out.println("Age1 yy= "+age1.getYears());
			System.out.println("Age1 mm= "+age1.getMonths());
			System.out.println("Age1 dd= "+age1.getDays());
			
			if(age.getYears()<0||age.getYears()>=age1.getYears()&&age.getMonths()>=age1.getMonths()&&age.getDays()>age1.getDays()||
					age.getYears()>age1.getYears()&&age.getMonths()>=age1.getMonths()&&age.getDays()>=age1.getDays()||
					age.getYears()>=age1.getYears()&&age.getMonths()>age1.getMonths()&&age.getDays()>=age1.getDays()){
				RequestContext.getCurrentInstance().update("growl");  
		     	
			   	 FacesContext.getCurrentInstance().addMessage(
			               null,
			               new FacesMessage(FacesMessage.SEVERITY_ERROR,
			                       "Error",
			                       "Date deposited should be between when you were assigned boda and now, please try with correct date."));
				
			}
			else {

				
				double previousAmount=UserDB.getAmount(getNumberPlate2());
				double totalAmount=previousAmount+amount;
				System.out.println(previousAmount);
				System.out.println(totalAmount);
				Transactions t=new Transactions();
				t.setAmount(amount);
				t.setDateDeposited(dateDeposited);
				t.setTransactionId(transactionId);
				t.setIdNoOfDepositor(getIdNoOfDepositor2());
				t.setNameOfDepositor(getNameOfDepositor2());
				t.setNumberPlate(getNumberPlate2());
		
				int test=UserDB.recordPayments(t,totalAmount);
				if(test>1){
					int amountInt=(int)amount;
					RequestContext.getCurrentInstance().update("growl");  
			     	
				   	 FacesContext.getCurrentInstance().addMessage(
				               null,
				               new FacesMessage(FacesMessage.SEVERITY_INFO,
				                       "Success",
				                       "Your Payment record was saved Successfully."));
				   	 //sending notification to registered user
				   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
					  executor.execute(new SendSms(UserDB.getPhoneNumber(idNoOfDepositor2), 
							  "Thank you "+getNameOfDepositor2()+" for recording Payment. Transaction Id is "+getTransactionId()+""
							  		+ " and amount is "+NumberToWordsConverter.convert(amountInt)+"("+getAmount()+")"));
					  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getBodabodaOwnerIdNo(getNumberPlate2())), 
							  ""+getNameOfDepositor2()+" deposited "+NumberToWordsConverter.convert(amountInt)+"("+getAmount()+") into your account. Transaction Id is "+getTransactionId()+""
							  		+ "."));
					  
					  try {
						  executor.shutdown();
						  System.out.println("Trying to wait thread to finish");
						executor.awaitTermination(10,TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						System.out.println("Failed to wait for some minutes to shutdown executor");
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
				   	
					
				}
				else {
					RequestContext.getCurrentInstance().update("growl");  
			     	
				   	 FacesContext.getCurrentInstance().addMessage(
				               null,
				               new FacesMessage(FacesMessage.SEVERITY_ERROR,
				                       "Error",
				                       "Your record failed while saving, please try again."));
				}
				
			
			}
		}
		

		
	}
	public ArrayList<Transactions> getAllMyTransactions(){
		 
		 return UserDB.getAllMyTransactions(getIdNoOfDepositor2());
	}
	public ArrayList<Transactions> getAllMyTransactionsAdmin(){
		 
		 return UserDB.getAllMyTransactionsAdmin();
	}
	
	public String minimumAge(){
		 long currentTime = System.currentTimeMillis();
	     Calendar now = Calendar.getInstance();
	     now.setTimeInMillis(currentTime);
		int years = now.get(Calendar.YEAR) ;
	     int currMonth = now.get(Calendar.MONTH) +1;
	     int days = now.get(Calendar.DATE);
	     String min=years+"/"+currMonth+"/"+days;

	     return min;
	     
	}
	
public void directMe(){
	RequestContext.getCurrentInstance().update("messages");  
 	
  	 FacesContext.getCurrentInstance().addMessage(
              null,
              new FacesMessage(FacesMessage.SEVERITY_ERROR,
                      "Error",
                      "You cannot record payment since you are not assigned to any motorbike"));
  	// this.disabled="true";
  	
	
}
	public String maximumAge(){
		String maximum=UserDB.getTimeAssigned(getIdNoOfDepositor2(), getNumberPlate2());
		System.out.println(maximum);
		if(maximum.equals("0")){
			RequestContext.getCurrentInstance().update("growl");  
	     	
		   	 FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "You cannot record payment since you are not assigned to any motorbike"));
		   	 directMe();
		   	 //this.setDisabled("true");
		return new Date().toString();

		}
		//setDisabled("false");
		String[] maxi=maximum.split(" ");
		//System.out.println(maxi[0]);
		String val=maxi[0];
		String[] maxi2=val.split("-");
		String maxi3=maxi2[0]+"/"+maxi2[1]+"/"+maxi2[2];
		 long currentTime = System.currentTimeMillis();
	     Calendar now = Calendar.getInstance();
	     now.setTimeInMillis(currentTime);
	//	int years = now.get(Calendar.YEAR) ;
	    // int currMonth = now.get(Calendar.MONTH) + 1;
	   //  int days = now.get(Calendar.DATE)-5;
	    // String max=years+"/"+currMonth+"/"+days;
	     
	     return maxi3;
	   
	     
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
		int years = now.get(Calendar.YEAR) -1;
	  
	     return String.valueOf(years);
	     
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public String getDisabled() {
		if(UserDB.getTimeAssigned(getIdNoOfDepositor2(), getNumberPlate2()).equals("0")){
			disabled="true";
			
		}
		else {
			disabled="false";
		}
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	

}
