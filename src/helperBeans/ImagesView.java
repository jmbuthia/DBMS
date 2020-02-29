package helperBeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@SuppressWarnings("deprecation")
@ManagedBean(name="imagesView")
public class ImagesView {
     
    private List<String> images;
    private String date;
   
   // private List<String> description;
     
   
	

	public String getDate() {
		
		 GregorianCalendar currentDate = new GregorianCalendar();
		    int year = currentDate.get(Calendar.YEAR);
		    int m=currentDate.get(Calendar.MONTH);
		    String month="";
		    if(m==0){
		    	month="January";
		    }
		    if(m==1){
		    	month="February";
		    }
		    if(m==2){
		    	month="March";
		    }
		    if(m==3){
		    	month="April";
		    }
		    if(m==4){
		    	month="May";
		    }
		    if(m==5){
		    	month="June";
		    }
		    if(m==6){
		    	month="July";
		    }
		    if(m==7){
		    	month="August";
		    }
		    if(m==8){
		    	month="September";
		    }
		    if(m==9){
		    	month="October";
		    }
		    if(m==10){
		    	month="November";
		    }
		    if(m==11){
		    	month="December";
		    }
		    date=month+", "+String.valueOf(year);
		return date;
	}



	@PostConstruct
    public void init() {
        images = new ArrayList<String>();
        /*description = new ArrayList<String>();
        description.add("bodaa boda insurance cover");
        description.add("transport to school");
        description.add("boda boda school");
        description.add("i own my boda");
        description.add("i own my boda");
        description.add("boda boda stage");
        description.add("boda boda stage");
        description.add("boda boda stage");
        description.add("boda boda stage");*/
       
        for (int i = 1; i <= 9; i++) {
            images.add("boda" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
   /* public List<String> getDescription() {
        return description;
    }*/
    /*
  public static void main(String[] args) {
	   ImagesView i=new ImagesView();
	System.out.println(i.getDate());
}
  
  */
}
