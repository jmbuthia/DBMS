package helperBeans;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@SuppressWarnings("deprecation")
@ManagedBean

public class FileDownload {
	private StreamedContent file;
	private String fileName;
	private String filePath;
	
    public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public StreamedContent getFile() {
        return file;
    }

	public FileDownload() { 
    	HttpSession session=SessionBean.getSession();
    	if(session.getAttribute("filePath")!=null){
			filePath=(String)session.getAttribute("filePath");
			
		}
		if(session.getAttribute("fileName")!=null){
			fileName=(String)session.getAttribute("fileName");
			
		}
    	
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(filePath);
        file = new DefaultStreamedContent(stream, "video/mp4", fileName);
    }
 
   

	

}
