package dataManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

public class SendSms implements Runnable {
	String phone;
	String message;
	public SendSms(String phone,String message){
		this.message=message;
		this.phone=phone;
		
	}
	

	@Override
	public void run() {
		sendSmsSynchronous(phone, message);
		// TODO Auto-generated method stub

	}
	private synchronized boolean  sendSmsSynchronous(final String phone, final String message)
    {
		System.out.println("The sendSms method is called by the class in run method");
       // final ProcessBuilder processBuilder = new ProcessBuilder().command("gsmsendsms", "-d", "/dev/ttyUSB0", "-b", "19200", phone, message);
		System.out.println("In the sendSmsSynchronous method and phone is : "+phone);
		System.out.println("In the sendSmsSynchronous method and message is : "+message);
		 final ProcessBuilder processBuilder = new ProcessBuilder().command("gsmsendsms","-d","/dev/ttyUSB0","-b","19200",phone,message);
        Process gsmsendsmsProcess;
        try
        {
            gsmsendsmsProcess = processBuilder.start();
            gsmsendsmsProcess.waitFor();
        }
        catch (final Exception e)
        {
        	 RequestContext.getCurrentInstance().update("growl");
			  FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Error",
		                       "Failed to send sms to "+phone));
        	System.out.println("error in sending sms");
        	System.out.println("Error is ===="+e);
            return false;
        }
     
        return gsmsendsmsProcess.exitValue() == 0;
    }

}
