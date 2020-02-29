package dataManager;

import java.sql.*;
import javax.sql.DataSource;

//import javax.annotation.Resource;
/*import javax.naming.InitialContext;
import javax.naming.NamingException;*/
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {
	private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
   // @Resource(name="jdbc/johnson") DataSource ds;

    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/bodaboda");//
    /*        try {
            	System.out.println("URL : " + dataSource.getConnection().getMetaData().getURL());
            	System.out.println("Username : " + dataSource.getConnection().getMetaData().getUserName());
            	System.out.println("Password : " + dataSource.getConnection().getClientInfo().getProperty("password"));
            	//Connection con=pool.getConnection();
            	//System.out.println("Password : " + con.getClientInfo().getProperty("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
            if(dataSource==null){
            	System.out.println("Data source is null");
            }
           // dataSource=ds;
            System.out.println("in connection pool try..");
            
        } catch (NamingException e) {
        	/*RequestContext.getCurrentInstance().update("growl");
			  FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Database Error",
		                       "Fail to connect to the database, please contact database admin."));
			*/  

            System.out.println(e);
            System.out.println("in connection pool catch exception..");
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public Connection getConnection() {
        try {
        	System.out.println("Connection pool is running");
            return dataSource.getConnection();
            
        } catch (SQLException e) {
        	/*RequestContext.getCurrentInstance().update("growl");
			  FacesContext.getCurrentInstance().addMessage(
		               null,
		               new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                       "Database Error",
		                       "Fail to getconnection to the database, please contact database admin."));
            */System.out.println(e);
            System.out.println("fail to get connection....john");
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
        
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    }