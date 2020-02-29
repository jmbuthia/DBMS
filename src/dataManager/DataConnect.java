package dataManager;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
 
public class DataConnect {
 
    public static Connection getConnection() {
    	System.out.println("data connection called"
    			+ "trying to connect");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dbms", "root", "");
            System.out.println("database \"dbms\" connected");
            return con;
        } catch (Exception ex) {
        	 RequestContext.getCurrentInstance().update("growl");
			  FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_FATAL,
		                       "Database Error",
		                       "Failed to connect to database, call us on 0717925741."));
            System.out.println("Database.getConnection() Error -->"
                    + ex.getMessage());
            return null;
        }
    }
 
    public static void close(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
        }
    }

}
