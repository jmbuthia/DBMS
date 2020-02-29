package helperBeans;

import java.io.File;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.CroppedImage;
 
@SuppressWarnings("deprecation")
@ManagedBean(name="cropperView")
public class CropperView {
     
    private CroppedImage croppedImage;
    public String getDisable() {
		return disable;
	}

	public void setDisable(String disable) {
		this.disable = disable;
	}

	public String disable="true";
    private String newImageName;
 
    public CroppedImage getCroppedImage() {
        return croppedImage;
    }
 
    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }
 
    public void crop() {
    	HttpSession session = SessionBean.getSession();
    	System.out.println("called crop method");
        if(croppedImage == null) {
        	System.out.println("inside crop is null, in crop method");
        	return;
        }
        session.removeAttribute("disable");
        
            setNewImageName(getRandomImageName());
           // HttpSession session = SessionBean.getSession();
            HttpSession session1 = SessionBean.getSession();
            //RegistrationDetails rd= (RegistrationDetails) session.getAttribute("registrationDetails");
            String image= (String) session1.getAttribute("image");
            String name= (String) session1.getAttribute("name");
            System.out.println("in crop method, image is "+ image);
            System.out.println("in crop method,name is "+name);
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String newFileName = servletContext.getRealPath("")  + "resources" + File.separator + "images" + 
                        File.separator+"profile"+ File.separator + name;
             System.out.println("newFileName is "+newFileName);
             File f=new File(newFileName);
             session.setAttribute("disable", "false");
             if(f.exists()){
            	 f.delete();
             }
            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
                imageOutput.close();
               /* FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Started", "Cropping started."));*/
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            }
             setDisable("false");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
       
        	// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry", "No image to Crop."));
        	
        }
       /*else {
    	   FacesMessage msg = new FacesMessage("Sorry!", "No image to Crop.");  
           FacesContext.getCurrentInstance().addMessage(null, msg);
      	 return;
	}*/
         
    
     
    private String getRandomImageName() {
        int i = (int) (Math.random() * 100000);
         
        return String.valueOf(i);
    }
     
    public String getNewImageName() {
        return newImageName;
    }
 
    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }


}
