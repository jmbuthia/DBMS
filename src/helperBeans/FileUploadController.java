package helperBeans;
/*import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
  
@ManagedBean
public class FileUploadController {
   private String destination="/root/workspacemars/tt/WebContent/resources/pdfs/";

    public void upload(FileUploadEvent event) {  
    	System.out.println("inside upload method");
    	System.out.println(event.getFile().getFileName());
    	String path= FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images");
        System.out.println(path);
    	FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }  
 
    public void copyFile(String fileName, InputStream in) {
           try {
              System.out.println("Called copy file method");
              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(destination + fileName));
              
                int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
              
                in.close();
                out.flush();
                out.close();
              
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
}


package helperBeans;
*/
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
  
@SuppressWarnings("deprecation")
@ManagedBean
public class FileUploadController {
   private String path;
   private String image;
   private String name;

    public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

	public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

	public void upload(FileUploadEvent event) {  
    	System.out.println("inside upload method");
    	System.out.println(event.getFile().getFileName());
    	System.out.println(event.getFile().getFileName().substring(event.getFile().getFileName().indexOf(".")));
    	String extention=event.getFile().getFileName().substring(event.getFile().getFileName().indexOf("."));
    	System.out.println("extention"+extention);
    	
    	setPath(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources"));
    
        System.out.println(path);
        HttpSession session = SessionBean.getSession();
        RegistrationDetails rd= (RegistrationDetails) session.getAttribute("registrationDetails");
        
    	setImage("snap_crop/" + rd.getIdNo()+extention);
    	setName(rd.getIdNo()+extention);
    	HttpSession session1 = SessionBean.getSession();
    	if(session1.getAttribute("image")!=null){
    		session1.removeAttribute("image");
    	}
    	if(session1.getAttribute("name")!=null){
    		session1.removeAttribute("name");
    	}
    	session1.setAttribute("image", image);
    	session1.setAttribute("name", name);
    	//session1.setMaxInactiveInterval(60*30);
    	
        // Do what you want with the file        
        try {
            copyFile(rd.getIdNo()+extention, event.getFile().getInputstream());
            FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage("Error! ", event.getFile().getFileName() + " Failed to upload.");  
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
 
    }  
	
 
    public void copyFile(String fileName, InputStream in) {
           try {
              System.out.println("Called copy file method");
              File f=new File(path+"/snap_crop/" + fileName);
              if(f.exists()){
            	  f.delete();
            	  
              }
              
                // write the inputStream to a FileOutputStream
                OutputStream out = new FileOutputStream(new File(path+"/snap_crop/" + fileName));
              System.out.println("path is "+path+"/snap_crop/" + fileName);
                int read = 0;
                byte[] bytes = new byte[1024];
              
                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
              
                in.close();
                out.flush();
                out.close();
           
                System.out.println("New file created!");
                } catch (IOException e) {
                System.out.println(e.getMessage());
                }
    }
    
	
}
