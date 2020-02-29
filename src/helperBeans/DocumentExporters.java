package helperBeans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.primefaces.context.RequestContext;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import dataManager.SendSms;
import dataManager.UserDB;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class DocumentExporters {
	
	private String replyMessage;
	private String phone;
	private String time;
	private MotorcycleDetails motorcycleDetails;
	private DrivingLicenseDetails drivingLicenseDetails;
	
	
	public MotorcycleDetails getMotorcycleDetails() {
		return motorcycleDetails;
	}
	public void setMotorcycleDetails(MotorcycleDetails motorcycleDetails) {
		this.motorcycleDetails = motorcycleDetails;
	}
	public DrivingLicenseDetails getDrivingLicenseDetails() {
		return drivingLicenseDetails;
	}
	public void setDrivingLicenseDetails(DrivingLicenseDetails drivingLicenseDetails) {
		this.drivingLicenseDetails = drivingLicenseDetails;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getReplyMessage() {
		return replyMessage;
	}
	public void setReplyMessage(String replyMessage) {
		this.replyMessage = replyMessage;
	}
	
	public ArrayList<MotorcycleDetails> getAllRequestAddBodaToApprove(){
		
		 return UserDB.getAllRequestAddBodaToApprove();
	 }
	public ArrayList<AssignRider> getAllRidersWithoutBodaToAssign(){
		
		 return UserDB.getAllRidersWithoutBodaToAssign();
	 }
	public ArrayList<AssignBoda> getAllBodasWithoutRiderToAssign(){
		
		 return UserDB.getAllBodasWithoutRiderToAssign();
	 }
	public ArrayList<AssignBoda> getAllBodasWithoutRider(){
		
		 return UserDB.getAllBodasWithoutRider();
	 }
	public ArrayList<AssignBoda> getAllBodasWithRiderToUnAssign(){
		
		 return UserDB.getAllBodasWithRiderToUnAssign();
	 }
	
	public ArrayList<DrivingLicenseDetails> getAllRequestAddLicenseToApprove(){
		 
		 return UserDB.getAllRequestAddLicenseToApprove();
	}
	public ArrayList<DrivingLicenseDetails> getMyLicenseDetails(){
		 HttpSession session= SessionBean.getSession();
		 RegistrationDetails rd=(RegistrationDetails)session.getAttribute("registrationDetails");
		 String idNo=rd.getIdNo();
		 System.out.println("id number in DocumentExporter.getmylicenseDetails is "+idNo);
		 return UserDB.getMyLicenseDetails(idNo);
	}
	public ArrayList<MotorcycleDetails> getAllMyMotorbikesDetails(){
		 HttpSession session= SessionBean.getSession();
		 RegistrationDetails rd=(RegistrationDetails)session.getAttribute("registrationDetails");
		 String idNo=rd.getIdNo();
		 System.out.println("id number in DocumentExporter.getAllmymotorbikesDetails is "+idNo);
		 return UserDB.getAllMyMotorbikesDetails(idNo);
	}
	
	public ArrayList<MotorcycleDetails> getAllMotorbikesDetails(){
		 
		 return UserDB.getAllMotorbikesDetails();
	}
	 public ArrayList<MotorcycleDetails> getAllRequestAddBoda(){
		 
		 HttpSession session= SessionBean.getSession();
		 RegistrationDetails rd=(RegistrationDetails)session.getAttribute("registrationDetails");
		 String idNo=rd.getIdNo();
		 return UserDB.getAllRequestAddBoda(idNo);
	 }


	 public ArrayList<DrivingLicenseDetails> getAllRequestAddLicense(){
		 
		 HttpSession session= SessionBean.getSession();
		 RegistrationDetails rd=(RegistrationDetails)session.getAttribute("registrationDetails");
		 //String idNo=rd.getIdNo();
		 return UserDB.getAllRequestAddLicense(rd.getIdNo());
	 }
	public ArrayList<HelpDesk> getHelpAllVisitors() {
		return UserDB.getHelpAllVisitors();
		
	}
	public ArrayList<HelpDesk> getHelpRegisteredUsers() {
		return UserDB.getHelpRegisteredUsers();
		
	}
	public ArrayList<RegistrationDetails> getAllRegisteredUsers() {
		return UserDB.getAllRegisteredUsers();
		
	}
	public ArrayList<RegistrationDetails> getAllRegisteredRiders() {
		return UserDB.getAllRegisteredRiders();
		
	}
	public ArrayList<RegistrationDetails> getAllRegisteredFemaleUsers() {
		return UserDB.getAllRegisteredFemaleUsers();
		
	}
	public ArrayList<RegistrationDetails> getAllRegisteredMaleUsers() {
		return UserDB.getAllRegisteredMaleUsers();
		
	}
	
	public ArrayList<ForgotPassword> getForgotPasswordHistory() {
		return UserDB.getForgotPasswordHistory();
		
	}
	
	public ArrayList<ForgotPassword> getChangePasswordHistory() {
		return UserDB.getChangePasswordHistory();
		
	}
	

public void postProcessXLS(Object document) {
    HSSFWorkbook wb = (HSSFWorkbook) document;
    HSSFSheet sheet = wb.getSheetAt(0);
    CellStyle style = wb.createCellStyle();
    style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());

    for (Row row : sheet) {
        for (Cell cell : row) {
            cell.setCellValue(cell.getStringCellValue().toUpperCase());
            cell.setCellStyle(style);
        }
    }
}

public void replyHelper(){

	System.out.println("replyHelper");
	String time;
	String from;
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	time=request.getParameter("time");
	from=request.getParameter("from");
	
	if(from.equals("nonRegisteredUser")){
	HelpDesk hd=UserDB.getNonUserHelp(time);
	System.out.println("The time in replyHelper method is== "+time);
	//setting the phone number
	setPhone(hd.getPhone());
	setTime(time);
	System.out.println("phone number is "+hd.getPhone());
	}
	
	if(from.equals("registeredUser")){
	HelpDesk hd=UserDB.getRegisteredUserHelp(time);
	System.out.println("The time in replyHelper method is== "+time);
	//setting the phone number
	setPhone(hd.getPhone());
	setTime(time);
	System.out.println("phone number is "+hd.getPhone());
	}
		
}

public void approveBodaAndLicenseRequest(){

	System.out.println("approveBodaAndLicenseRequest method");
	
	String numberPlateOrLicenseNo;
	String from;
	
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	numberPlateOrLicenseNo=request.getParameter("numberPlateOrLicenseNo");
	from=request.getParameter("from");
	
	if(from.equals("boda")){
	MotorcycleDetails md=UserDB.getBodaDetailsInRequest(numberPlateOrLicenseNo);
	//System.out.println("The number plate in approveBodaAndLicenseRequest method is== "+numberPlateOrLicenseNo);
	//setting the motorbike details in database
	//setMotorcycleDetails(md);
	int test=UserDB.approveBodaRequest(md);
	
	//System.out.println("number plate is "+motorcycleDetails.getNumberPlate());
	if(test>0){
		
		RequestContext.getCurrentInstance().update("growl");  
     	
    	 FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success",
                        "You approved request successfully"));
    	 //sending notification to owner
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getBodabodaOwnerIdNo(md.getNumberPlate())), 
				  "Your application to add boda boda "+md.getNumberPlate()+"  is accepted."));
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
                        "Failed to approve the request please try again."));
   
   
	}
	}
	
	if(from.equals("license")){
		DrivingLicenseDetails drd=UserDB.getLicenseDetailsInRequest(numberPlateOrLicenseNo);
	System.out.println("The Driving License number in approveBodaAndLicenseRequest method is== "+numberPlateOrLicenseNo);
	//setting the license details
	//setDrivingLicenseDetails(drd);
	
	//System.out.println("License number is "+drivingLicenseDetails.getDrivingLicenseNo());
int test=UserDB.approveLicenseRequest(drd);
	
	//System.out.println("number plate is "+motorcycleDetails.getNumberPlate());
	if(test>0){
		RequestContext.getCurrentInstance().update("growl");  
     	
    	 FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Success",
                        "You approved request successfully"));
    	 //sending notification to rider
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(UserDB.getPhoneNumber(UserDB.getRiderIdNo(drd.getDrivingLicenseNo())), 
				  "Your application to be a rider with driving License number "+drd.getDrivingLicenseNo()+"  is accepted."));
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
                        "Failed to approve the request please try again."));
   
   
	}
	}
	
}

public void editBodaAndLicenseHelper(){

	System.out.println("editBodaAndLicenseHelper");
	
	String numberPlateOrLicenseNo;
	String from;
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	numberPlateOrLicenseNo=request.getParameter("numberPlateOrLicenseNo");
	from=request.getParameter("from");
	
	if(from.equals("boda")){
	MotorcycleDetails md=UserDB.getBodaDetailsInRequest(numberPlateOrLicenseNo);
	System.out.println("The number plate in editBodaAndLicenseHelper method is== "+numberPlateOrLicenseNo);
	//setting the motorbike details
	setMotorcycleDetails(md);
	
	System.out.println("number plate is "+motorcycleDetails.getNumberPlate());
	}
	
	if(from.equals("license")){
		DrivingLicenseDetails drd=UserDB.getLicenseDetailsInRequest(numberPlateOrLicenseNo);
	System.out.println("The numberplate in editBodaAndLicenseHelper method is== "+numberPlateOrLicenseNo);
	//setting the license details
	setDrivingLicenseDetails(drd);
	
	System.out.println("License number is "+drivingLicenseDetails.getDrivingLicenseNo());
	}
		
}


public String reply(){
		
	System.out.println("replying");
	
	
	
	HelpDesk hd=UserDB.getNonUserHelp(time);
	System.out.println("The time in reply method is== "+time);
	HttpSession session = SessionBean.getSession();
	 RegistrationDetails rd=(RegistrationDetails) session.getAttribute("registrationDetails");
	int x=UserDB.deleteOrReplyNonUserHelp(hd,replyMessage,"replied By "+rd.getFirstName()+" idNO "+rd.getIdNo(), time);
	if(x>0){
		//sending reply message to nonRegistered user through phone
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(phone,"DBMS Reply Message: "+replyMessage));
		  
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO,
                         "Success",
                         "You replied message successfully"));
    
         
	}
		else {
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                         "Error",
                         "Failed to deliver the message"));
         
	}
	return "helpNonRegisteredUsers?faces-redirect=true";


	
}

public String replyRegisteredUser(){
	
	System.out.println("replying");
	
	
	
	HelpDesk hd=UserDB.getRegisteredUserHelp(time);
	System.out.println("The time in replyRegisteredUser method is== "+time);
	HttpSession session = SessionBean.getSession();
	 RegistrationDetails rd=(RegistrationDetails) session.getAttribute("registrationDetails");
	int x=UserDB.deleteOrReplyRegisteredUserHelp(hd,replyMessage,"replied By "+rd.getFirstName()+" idNO "+rd.getIdNo(), time);
	if(x>0){
		//sending reply message to nonRegistered user through phone
	   	 ExecutorService executor = Executors.newFixedThreadPool(1);//creating a pool of 1 threads
		  executor.execute(new SendSms(phone,"DBMS Reply Message: "+replyMessage));
		  
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO,
                         "Success",
                         "You replied message successfully"));
     
         
	}
		else {
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                         "Error",
                         "Failed to deliver the message"));
         
	}
	return "helpRegisteredUsers?faces-redirect=true";


	
}



public String delete(){
	System.out.println("Deleting");
	String time;
	String from;
	String url="#";
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	time=request.getParameter("time");
	from=request.getParameter("from");
	
	if(from.equals("nonRegisteredUser")){
		
	
	HelpDesk hd=UserDB.getNonUserHelp(time);
	System.out.println("The time in deleting method is== "+time);
	 HttpSession session = SessionBean.getSession();
	 RegistrationDetails rd=(RegistrationDetails) session.getAttribute("registrationDetails");
	int x=UserDB.deleteOrReplyNonUserHelp(hd,"","Deleted By "+rd.getFirstName()+" idNO "+rd.getIdNo(), time);
	if(x>0){
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO,
                         "Success",
                         "You deleted the record successfully"));
         
	}
		else {
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                         "Error",
                         "Failed to delete the record"));
         
	
		
	}
	
	url= "helpNonRegisteredUsers";
	}

	if(from.equals("registeredUser")){
		
	
	HelpDesk hd=UserDB.getRegisteredUserHelp(time);
	System.out.println("The time in deleting method is== "+time);
	 HttpSession session = SessionBean.getSession();
	 RegistrationDetails rd=(RegistrationDetails) session.getAttribute("registrationDetails");
	int x=UserDB.deleteOrReplyRegisteredUserHelp(hd,"","Deleted By "+rd.getFirstName()+" idNO "+rd.getIdNo(), time);
	if(x>0){
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO,
                         "Success",
                         "You deleted the record successfully"));
         
	}
		else {
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                         "Error",
                         "Failed to delete the record"));
         
	
		
	}
	
	url= "helpRegisteredUsers";
	}
	return url;
	
	
}

public String deleteBodaAndLicenseRequest(){
	System.out.println("in the deleteBodaAndLicenseRequest method");
	String numberPlateOrLicenseNo;
	String from;
	String url="#";
	FacesContext fc= FacesContext.getCurrentInstance();
	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	numberPlateOrLicenseNo=request.getParameter("numberPlateOrLicenseNo");
	from=request.getParameter("from");
	
	if(from.equals("boda")){
	
	System.out.println("The number plate in the deleteRequestAddBoda method is== "+numberPlateOrLicenseNo);
	int x=UserDB.deleteRequestAddBoda(numberPlateOrLicenseNo);
	if(x>0){
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO,
                         "Success",
                         "You deleted the record successfully"));
     	url= "approveAddBoda";
	}
		else {
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                         "Error",
                         "Failed to delete the record"));
         
	
     	url= "approveAddBoda";
	}
	
	
	}

	if(from.equals("license")){
		
	
	
		System.out.println("The number plate in the deleteRequestAddBoda method is== "+numberPlateOrLicenseNo);
	int x=UserDB.deleteRequestAddLicense(numberPlateOrLicenseNo);
	if(x>0){
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_INFO,
                         "Success",
                         "You deleted the record successfully"));
     	url= "approveAddLicense";
	}
		else {
		
     	   RequestContext.getCurrentInstance().update("growl");  
     	
     	 FacesContext.getCurrentInstance().addMessage(
                 null,
                 new FacesMessage(FacesMessage.SEVERITY_ERROR,
                         "Error",
                         "Failed to delete the record"));
         
	
     	url= "approveAddLicense";
	}
	
	
	}
	return url;
	
	
}

public void preProcessPDF(Object document) throws IOException,BadElementException,DocumentException{
	Document pdf=(Document) document;
	pdf.open();
	pdf.setPageSize(PageSize.A4);
	ServletContext servletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	String logo=servletContext.getRealPath("")+File.separator+""+File.separator+"resources/images/pdflogo.png";
	pdf.add(Image.getInstance(logo));
	pdf.addCreator("jmbuthia12@gmail.com");
	pdf.addAuthor("Johnson Mbuthia");
	pdf.addTitle("List of registered users");
	pdf.addCreationDate();
	pdf.addKeywords("Johnson Mbuthia:- name of the Developer");
	pdf.addProducer();
	pdf.setPageCount(1);
	pdf.addHeader("Johnson Mbuthia","Developer");

	pdf.addSubject("DBMS System Documents");
	pdf.addTitle("DBMS generated Document-(Johnson)");


	((Document) document).add(new Paragraph(""
			+ "                 Digital Bodaboda Management System generated document. All rights reserved."
			+ "                  "
			+ "                                 (...Generated at  "+new Date()+ "...)"
					+ "                                                                "
					+ "              "));
}
/*public static void main(String[] args) {
	DocumentExporters de=new DocumentExporters();
	System.out.println(de.allRegisteredUsers.get(0).getIdNo());
}
*/
}
