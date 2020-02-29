package helperBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class WatchTutorial {
	private String fileName;
	private String fromOutCome;
	private String filePath;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFromOutCome() {
		return fromOutCome;
	}
	public void setFromOutCome(String fromOutCome) {
		this.fromOutCome = fromOutCome;
	}
	
	public String GetVideo(){
		System.out.println("called GetVideo");
		String video="";
		HttpServletRequest request=SessionBean.getRequest();
		setFromOutCome(request.getRequestURI());
		FacesContext fc= FacesContext.getCurrentInstance();
		HttpServletRequest request1=(HttpServletRequest) fc.getExternalContext().getRequest();
		video=request1.getParameter("video");
		setFileName(video);
		setFilePath("/resources/video/"+video);
		System.out.println("filename is "+fileName);
		System.out.println("filePath is "+filePath);
		System.out.println("the current url from GetVideo function is "+request.getRequestURI());
		HttpSession session=SessionBean.getSession();
		if(session.getAttribute("filePath")!=null){
			session.removeAttribute("filePath");
			
		}
		if(session.getAttribute("fileName")!=null){
			session.removeAttribute("fileName");
			
		}
		session.setAttribute("filePath", filePath);
		session.setAttribute("fileName", fileName);
		return "/user/watchTutorial?faces-redirect=true";
		
	}

}
