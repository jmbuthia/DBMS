package dataManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import helperBeans.AES_EncryptDecrypt;
import helperBeans.AssignBoda;
import helperBeans.AssignRider;
import helperBeans.DrivingLicenseDetails;
import helperBeans.ForgotPassword;
import helperBeans.HelpDesk;
import helperBeans.MotorcycleDetails;
import helperBeans.RegistrationDetails;
import helperBeans.RiderAsFromTime;
import helperBeans.SessionBean;
import helperBeans.Transactions;


public class UserDB {
	
	public static ArrayList<MotorcycleDetails> getAllMyBodaAmount(String idNo) {
		System.out.println("inside getAllMyBodaAmount method");
		System.out.println("idNo == "+ idNo);
			
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
			String query="SELECT `motorcycleDetails`.`numberPlate`, `engineNo`, "
					+ "`frameNo`, `model`, `insuranceExpiryDate`,`amount` FROM "
					+ "`motorcycleDetails`,`motorbikeAndOwner` WHERE "
					+ "`motorcycleDetails`.`numberPlate`=`motorbikeAndOwner`.`numberPlate` AND"
					+ " `motorbikeAndOwner`.`idNo`='"+idNo+"'";
			
	      PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<MotorcycleDetails> al = new ArrayList<MotorcycleDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	MotorcycleDetails md= new MotorcycleDetails();
	    
	           md.setAmount(rs.getDouble("amount"));
	           md.setEngineNo(rs.getString("engineNo"));
	           md.setFrameNo(rs.getString("frameNo"));
	           md.setInsuranceExpiryDate(rs.getDate("insuranceExpiryDate"));
	           md.setModel(rs.getString("model"));
	           md.setNumberPlate(rs.getString("numberPlate"));
	            	  	          	
	            
	                al.add(md);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyBodaAmount catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyBodaAmount -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	public static ArrayList<RiderAsFromTime> getMyBodaRiders(String idNo) {
		System.out.println("inside getMyBodaRiders method");
		System.out.println("idNo == "+ idNo);
			
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
			String query="SELECT `firstName`,`lastName`,`phone`,`model`,`frameNo`,"
					+ "`motorbikeNumberPlate`, `insuranceExpiryDate` FROM "
					+ "`registrationDetails`,`motorcycleDetails`,`riderMotorbikeAndOwner` WHERE "
					+ "motorcycleDetails.numberPlate=riderMotorbikeAndOwner.motorbikeNumberPlate AND "
					+ "riderMotorbikeAndOwner.ownerIdNumber='"+idNo+"' AND "
					+ "registrationDetails.idNo=riderMotorbikeAndOwner.riderIdNo";
			
	      PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RiderAsFromTime> al = new ArrayList<RiderAsFromTime>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RiderAsFromTime riderAsFromTime= new RiderAsFromTime();
	    
	            	//String ownerPhoneNumber=getPhoneNumber(rs.getString("ownerIdNumber"));
	            	
	            	riderAsFromTime.setOwnerIdNo(rs.getString("model"));
	            	riderAsFromTime.setRiderFirstName(rs.getString("firstName"));
	            	riderAsFromTime.setMotorbikeNumberPlate(rs.getString("motorbikeNumberPlate"));
	            	riderAsFromTime.setRiderLastName(rs.getString("lastName"));
	            	riderAsFromTime.setRiderPhoneNumber(rs.getString("phone"));
	            	riderAsFromTime.setTimeAssigned(rs.getDate("insuranceExpiryDate"));
	            	riderAsFromTime.setOwnerPhoneNumber(rs.getString("frameNo"));
	            	
	          
	            	
	            	          	
	            
	                al.add(riderAsFromTime);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getMyBodaRiders catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getMyBodaRiders -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	public static ArrayList<RiderAsFromTime> getMyAssignedBoda(String idNo) {
		System.out.println("inside getMyAssignedBoda method");
		System.out.println("idNo == "+ idNo);
			
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
			String query="SELECT `firstName`,`lastName`,`phone`,`model`,`frameNo`,"
					+ "`motorbikeNumberPlate`, `insuranceExpiryDate` FROM "
					+ "`registrationDetails`,`motorcycleDetails`,`riderMotorbikeAndOwner` WHERE "
					+ "motorcycleDetails.numberPlate=riderMotorbikeAndOwner.motorbikeNumberPlate AND "
					+ "riderMotorbikeAndOwner.riderIdNo='"+idNo+"' AND "
					+ "registrationDetails.idNo=riderMotorbikeAndOwner.ownerIdNumber";
			
	      PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RiderAsFromTime> al = new ArrayList<RiderAsFromTime>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RiderAsFromTime riderAsFromTime= new RiderAsFromTime();
	    
	            	//String ownerPhoneNumber=getPhoneNumber(rs.getString("ownerIdNumber"));
	            	
	            	riderAsFromTime.setOwnerIdNo(rs.getString("model"));
	            	riderAsFromTime.setRiderFirstName(rs.getString("firstName"));
	            	riderAsFromTime.setMotorbikeNumberPlate(rs.getString("motorbikeNumberPlate"));
	            	riderAsFromTime.setRiderLastName(rs.getString("lastName"));
	            	riderAsFromTime.setRiderPhoneNumber(rs.getString("phone"));
	            	riderAsFromTime.setTimeAssigned(rs.getDate("insuranceExpiryDate"));
	            	riderAsFromTime.setOwnerPhoneNumber(rs.getString("frameNo"));
	            	
	          
	            	
	            	          	
	            
	                al.add(riderAsFromTime);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getMyAssignedBoda catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getMyAssignedBoda -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	
	public static ArrayList<MotorcycleDetails> getAllMyMotorbikesDetails(String idNo) {
		System.out.println("inside getAllMyMotorbikesDetails(idNo) method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `motorbikeAndOwner`.`idNo`, "
	         		+ "`motorbikeAndOwner`.`numberPlate`,"
	         		+ "motorcycleDetails.engineNo,motorcycleDetails.frameNo,"
	         		+ "motorcycleDetails.model,"
	         		+ "motorcycleDetails.insuranceExpiryDate FROM `motorbikeAndOwner`, "
	         		+ "`motorcycleDetails` WHERE"
	         		+ " motorbikeAndOwner.numberPlate=motorcycleDetails.numberPlate AND "
	         		+ "motorbikeAndOwner.idNo='"+idNo+"'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<MotorcycleDetails> al = new ArrayList<MotorcycleDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	MotorcycleDetails md = new MotorcycleDetails();
	            	md.setId(rs.getString("idNo"));
	            	md.setNumberPlate(rs.getString("numberPlate"));
	            	md.setEngineNo(rs.getString("engineNo"));
	            	md.setFrameNo(rs.getString("frameNo"));
	            	md.setModel(rs.getString("model"));
	            	md.setInsuranceExpiryDate(rs.getDate("insuranceExpiryDate"));
	            	
	            	           	          	
	            
	                al.add(md);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyMotorbikesDetails(idNo) catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyMotorbikesDetails(idNo) -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static ArrayList<MotorcycleDetails> getAllMotorbikesDetails() {
		System.out.println("inside getAllMotorbikesDetails() method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `motorbikeAndOwner`.`idNo`, "
	         		+ "`motorbikeAndOwner`.`numberPlate`,"
	         		+ "motorcycleDetails.engineNo,motorcycleDetails.frameNo,"
	         		+ "motorcycleDetails.model,"
	         		+ "motorcycleDetails.insuranceExpiryDate FROM `motorbikeAndOwner`, "
	         		+ "`motorcycleDetails` WHERE"
	         		+ " motorbikeAndOwner.numberPlate=motorcycleDetails.numberPlate";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<MotorcycleDetails> al = new ArrayList<MotorcycleDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	MotorcycleDetails md = new MotorcycleDetails();
	            	md.setId(rs.getString("idNo"));
	            	md.setNumberPlate(rs.getString("numberPlate"));
	            	md.setEngineNo(rs.getString("engineNo"));
	            	md.setFrameNo(rs.getString("frameNo"));
	            	md.setModel(rs.getString("model"));
	            	md.setInsuranceExpiryDate(rs.getDate("insuranceExpiryDate"));
	            	
	            	           	          	
	            
	                al.add(md);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMotorbikesDetails() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMotorbikesDetails() -->" + e.getMessage());
	            return (null);
	        }
	    }


	public static ArrayList<Transactions> getAllMyTransactions(String idNo) {
		System.out.println("inside getAllMyTransactions(idNo) method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		double total=0.0;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `transactionId`, `dateDeposited`, `nameOfDepositor`, `idNoOfDepositor`, `numberPlate`, `amount`,`timestamp` FROM"
	         		+ " `transactions` WHERE `idNoOfDepositor`='"+idNo+"'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Transactions> al = new ArrayList<Transactions>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Transactions t = new Transactions();
	            	t.setTransactionId(rs.getString("transactionId"));
	            	t.setAmount(rs.getDouble("amount"));
	            	t.setDateDeposited(rs.getDate("dateDeposited"));
	            	t.setIdNoOfDepositor(rs.getString("idNoOfDepositor"));
	            	t.setNumberPlate(rs.getString("numberPlate"));
	            	t.setNameOfDepositor(rs.getString("nameOfDepositor"));
	            	t.setTimeRecorded(rs.getString("timestamp"));
	            	
	            	  total+=  rs.getDouble("amount");   
	            	  t.setTotalamount(total);
	            
	                al.add(t);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyTransactions(idNo) catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyTransactions(idNo) -->" + e.getMessage());
	            return (null);
	        }
	    }
	public static ArrayList<Transactions> getAllMyBodaTransactions(String numberPlate) {
		System.out.println("inside getAllMyBodaTransactions(numberPlate) method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		double total=0.0;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `transactionId`, `dateDeposited`, `nameOfDepositor`, `idNoOfDepositor`, `numberPlate`, `amount`,`timestamp` FROM"
	         		+ " `transactions` WHERE `numberPlate`='"+numberPlate+"'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Transactions> al = new ArrayList<Transactions>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Transactions t = new Transactions();
	            	t.setTransactionId(rs.getString("transactionId"));
	            	t.setAmount(rs.getDouble("amount"));
	            	t.setDateDeposited(rs.getDate("dateDeposited"));
	            	t.setIdNoOfDepositor(rs.getString("idNoOfDepositor"));
	            	t.setNumberPlate(rs.getString("numberPlate"));
	            	t.setNameOfDepositor(rs.getString("nameOfDepositor"));
	            	t.setTimeRecorded(rs.getString("timestamp"));
	            	
	            	  total+=  rs.getDouble("amount");   
	            	  t.setTotalamount(total);
	            
	                al.add(t);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyBodaTransactions(numberPlate) catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyBodaTransactions(numberPlate) -->" + e.getMessage());
	            return (null);
	        }
	    }
	


	public static ArrayList<Transactions> getAllMyTransactionsAdmin() {
		System.out.println("inside getAllMyTransactionsAdmin() method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		double total=0.0;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `transactionId`, `dateDeposited`, `nameOfDepositor`, `idNoOfDepositor`, `numberPlate`, `amount`,`timestamp` FROM"
	         		+ " `transactions`";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<Transactions> al = new ArrayList<Transactions>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	Transactions t = new Transactions();
	            	t.setTransactionId(rs.getString("transactionId"));
	            	t.setAmount(rs.getDouble("amount"));
	            	t.setDateDeposited(rs.getDate("dateDeposited"));
	            	t.setIdNoOfDepositor(rs.getString("idNoOfDepositor"));
	            	t.setNumberPlate(rs.getString("numberPlate"));
	            	t.setNameOfDepositor(rs.getString("nameOfDepositor"));
	            	t.setTimeRecorded(rs.getString("timestamp"));
	            	
	            	  total+=  rs.getDouble("amount");   
	            	  t.setTotalamount(total);
	            
	                al.add(t);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllMyTransactionsAdmin() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllMyTransactionsAdmin() -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	public static int renewLicense(String licenseNo,java.util.Date renewedExpiryDate){
		System.out.println("Connection pool is calling renewLicense data method");
		
		System.out.println("inside renewLicense method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
        System.out.println("UserDB.renewLicense date is "+sdf.format(renewedExpiryDate));
        System.out.println("UserDB.renewLicense license is "+licenseNo);

        String query="UPDATE `drivingLicenseDetails` SET `drivingLicenseExpiryDate`='"+sdf.format(renewedExpiryDate)+"' WHERE `drivingLicenseNo`='"+licenseNo+"'";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
	

	
	public static int renewInsurance(String numberPlate,java.util.Date renewedExpiryDate){
		System.out.println("Connection pool is calling reneInsurance data method");
		
		System.out.println("inside reneInsurance method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;
        System.out.println("UserDB.reneInsurance date is "+sdf.format(renewedExpiryDate));
        System.out.println("UserDB.reneInsurance number plate is "+numberPlate);

        String query="UPDATE `motorcycleDetails` SET `insuranceExpiryDate`='"+sdf.format(renewedExpiryDate)+"' WHERE `numberPlate`='"+numberPlate+"'";

		
		
		try {
			ps=connection.prepareStatement(query);
			/*ps.setString(1,licenseNo);
			
			ps.setString(2,sdf.format(renewedExpiryDate));
			
			*/
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
	

	public static int assignBodaToRider(String riderDrivingLicenseNo, String motorbikeNumberPlate){
		System.out.println("inside assignBodaToRider method");
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    
	 	

        String query1="UPDATE `motorcycleDetails` SET `isAssigned`='true' WHERE `numberPlate`='"+motorbikeNumberPlate+"'";
        String query2="UPDATE `drivingLicenseDetails` SET `isAssigned`='true' WHERE  `drivingLicenseNo`='"+riderDrivingLicenseNo+"'";
        
        String query3="INSERT INTO `riderMotorbikeAndOwner`"
        		+ "(`riderIdNo`, `motorbikeNumberPlate`, `ownerIdNumber`) VALUES "
        		+ "('"+UserDB.getRiderIdNo(riderDrivingLicenseNo)+"','"+motorbikeNumberPlate+"',"
        				+ "'"+UserDB.getBodabodaOwnerIdNo(motorbikeNumberPlate)+"')";
      

        String query4="INSERT INTO `riderMotorbikeAndOwnerHistory`(`riderIdNo`, `motorbikeNumberPlate`, `ownerIdNumber`) VALUES "
        		+ "('"+UserDB.getRiderIdNo(riderDrivingLicenseNo)+"','"+motorbikeNumberPlate+"',"
        				+ "'"+UserDB.getBodabodaOwnerIdNo(motorbikeNumberPlate)+"')";
      
		System.out.println("in the assignBodaToRider");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
			s.addBatch(query3);
			s.addBatch(query4);
		
			int count[]=s.executeBatch();
			System.out.println("completed assignBodaToRider method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to assignBodaToRider");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	
	
	public static int recordPayments(Transactions t,double totalAmount){
		System.out.println("inside recordPayments method");
		System.out.println(totalAmount);
		System.out.println(t.getAmount());
		System.out.println(t.getNumberPlate());
		System.out.println(t.getTransactionId());
		System.out.println(t.getDateDeposited());
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	 	

        String query1="UPDATE `motorbikeAndOwner` SET `amount`="+totalAmount+" WHERE "
        		+ "`numberPlate`='"+t.getNumberPlate()+"'";
        String query2="INSERT INTO `transactions`"
        		+ "(`transactionId`, `dateDeposited`, `nameOfDepositor`, `idNoOfDepositor`, `numberPlate`, `amount`)"
        		+ " VALUES ('"+t.getTransactionId()+"','"+sdf.format(t.getDateDeposited())+"','"+t.getNameOfDepositor()+"','"+t.getIdNoOfDepositor()+"',"
        				+ "'"+t.getNumberPlate()+"',"+t.getAmount()+")";
        
       
      
		System.out.println("in the recordPayments");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
		
		
			int count[]=s.executeBatch();
			System.out.println("completed recordPayments method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to recordPayments");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	



	public static int unAssignBodaToRider(String riderDrivingLicenseNo,String timeAssigned, String motorbikeNumberPlate){
		System.out.println("inside unAssignBodaToRider method");
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    
	 	

        String query1="UPDATE `motorcycleDetails` SET `isAssigned`='false' WHERE `numberPlate`='"+motorbikeNumberPlate+"'";
        String query2="UPDATE `drivingLicenseDetails` SET `isAssigned`='false' WHERE  `drivingLicenseNo`='"+riderDrivingLicenseNo+"'";
        
        String query3="DELETE FROM `riderMotorbikeAndOwner` WHERE `motorbikeNumberPlate`='"+motorbikeNumberPlate+"'";
      

        String query4="UPDATE `riderMotorbikeAndOwnerHistory` SET `timeUnAssigned`=now() "
        		+ "WHERE `timeAssigned`='"+timeAssigned+"' AND"
        				+ " `motorbikeNumberPlate`='"+motorbikeNumberPlate+"'";
      
		System.out.println("in the unAssignBodaToRider");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
			s.addBatch(query3);
			s.addBatch(query4);
		
			int count[]=s.executeBatch();
			System.out.println("completed unAssignBodaToRider method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to unAssignBodaToRider");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	

	
	public static ArrayList<AssignBoda> getAllBodasWithRiderToUnAssign() {
		System.out.println("inside getAllBodasWithRiderToUnAssign method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT DISTINCT registrationDetails.firstName,"
	         		+ "registrationDetails.lastName,drivingLicenseDetails.drivingLicenseNo,"
	         		+ "riderMotorbikeAndOwner.motorbikeNumberPlate,motorcycleDetails.model,"
	         		+ "riderMotorbikeAndOwnerHistory.timeAssigned FROM "
	         		+ "registrationDetails INNER JOIN drivingLicenseDetails ON "
	         		+ "registrationDetails.idNo=drivingLicenseDetails.idNo INNER JOIN "
	         		+ "riderMotorbikeAndOwner ON "
	         		+ "riderMotorbikeAndOwner.riderIdNo=registrationDetails.idNo INNER JOIN "
	         		+ "motorcycleDetails ON "
	         		+ "riderMotorbikeAndOwner.motorbikeNumberPlate=motorcycleDetails.numberPlate INNER JOIN "
	         		+ "riderMotorbikeAndOwnerHistory ON "
	         		+ "riderMotorbikeAndOwnerHistory.motorbikeNumberPlate=motorcycleDetails.numberPlate WHERE "
	         		+ "drivingLicenseDetails.isAssigned='true' AND motorcycleDetails.isAssigned='true' AND riderMotorbikeAndOwnerHistory.timeUnAssigned IS NULL";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<AssignBoda> al = new ArrayList<AssignBoda>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            			AssignBoda ab = new AssignBoda(rs.getString("drivingLicenseNo"), rs.getString("firstName"),
	            					rs.getString("lastName")," " ," " ,
	            					rs.getString("motorbikeNumberPlate"), rs.getString("model"), rs.getString("timeAssigned"));
	            	
	            	          	
	            
	                al.add(ab);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllBodasWithRiderToUnAssign catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllBodasWithRiderToUnAssign -->" + e.getMessage());
	            return (null);
	        }
	    }
	

	
	public static ArrayList<AssignBoda> getAllBodasWithoutRiderToAssign() {
		System.out.println("inside getAllBodasWithoutRiderToAssign method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT registrationDetails.firstName,"
	         		+ " registrationDetails.lastName,motorbikeAndOwner.idNo,"
	         		+ " motorcycleDetails.numberPlate,motorcycleDetails.model,"
	         		+ "motorcycleDetails.insuranceExpiryDate FROM "
	         		+ "registrationDetails INNER JOIN motorbikeAndOwner ON "
	         		+ "registrationDetails.idNo=motorbikeAndOwner.idNo "
	         		+ "INNER JOIN motorcycleDetails ON "
	         		+ "motorbikeAndOwner.numberPlate=motorcycleDetails.numberPlate WHERE"
	         		+ " motorcycleDetails.isAssigned='false'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<AssignBoda> al = new ArrayList<AssignBoda>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	String riderFirstName="";
	            	String riderLastName="";
	            	String riderDrivingLicenseNo="";
	            	HttpSession session= SessionBean.getSession();
	            	FacesContext fc= FacesContext.getCurrentInstance();
	            	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	            			String riderFirstName1=request.getParameter("riderFirstName");
	            			String riderLastName1=request.getParameter("riderLastName");
	            			String riderDrivingLicenseNo1=request.getParameter("licenseNo");
	            			if(riderDrivingLicenseNo1!=null){
	            				System.out.println("rider in UserDB is not null");
	            				if(session.getAttribute("riderDrivingLicenseNo")!=null){
	            					session.removeAttribute("riderDrivingLicenseNo");
	            					
	            				}
	            				if(session.getAttribute("riderFirstName")!=null){
	            					session.removeAttribute("riderFirstName");
	            					
	            				}
	            				if(session.getAttribute("riderLastName")!=null){
	            					session.removeAttribute("riderLastName");
	            					
	            				}
	            			
	            			session.setAttribute("riderFirstName", riderFirstName1);
	            			session.setAttribute("riderLastName", riderLastName1);
	            			session.setAttribute("riderDrivingLicenseNo", riderDrivingLicenseNo1);
	            			riderFirstName=riderFirstName1;
	            			riderLastName=riderLastName1;
	            			riderDrivingLicenseNo=riderDrivingLicenseNo1;
	            			
	            			}
	            			if(riderDrivingLicenseNo1==null){
	            				System.out.println("rider in UserDB is null");
	            				riderFirstName=(String) session.getAttribute("riderFirstName");
	            				riderLastName=(String) session.getAttribute("riderLastName");
	            				riderDrivingLicenseNo=(String) session.getAttribute("riderDrivingLicenseNo");
	            			}
	            			
	            			AssignBoda ab = new AssignBoda(riderDrivingLicenseNo, riderFirstName,
	            					riderLastName, rs.getString("firstName"), rs.getString("lastName"),
	            					rs.getString("numberPlate"), rs.getString("model"), rs.getString("insuranceExpiryDate"));
	            	
	            	          	
	            
	                al.add(ab);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllBodasWithoutRiderToAssign catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllBodasWithoutRiderToAssign -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	
	public static ArrayList<AssignBoda> getAllBodasWithoutRider() {
		System.out.println("inside getAllBodasWithoutRider method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT registrationDetails.firstName,"
	         		+ " registrationDetails.lastName,motorbikeAndOwner.idNo,"
	         		+ " motorcycleDetails.numberPlate,motorcycleDetails.model,"
	         		+ "motorcycleDetails.insuranceExpiryDate FROM "
	         		+ "registrationDetails INNER JOIN motorbikeAndOwner ON "
	         		+ "registrationDetails.idNo=motorbikeAndOwner.idNo "
	         		+ "INNER JOIN motorcycleDetails ON "
	         		+ "motorbikeAndOwner.numberPlate=motorcycleDetails.numberPlate WHERE"
	         		+ " motorcycleDetails.isAssigned='false'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<AssignBoda> al = new ArrayList<AssignBoda>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	String riderFirstName="";
	            	String riderLastName="";
	            	String riderDrivingLicenseNo="";
	            	HttpSession session= SessionBean.getSession();
	            	FacesContext fc= FacesContext.getCurrentInstance();
	            	HttpServletRequest request=(HttpServletRequest) fc.getExternalContext().getRequest();
	            			String riderFirstName1=request.getParameter("riderFirstName");
	            			String riderLastName1=request.getParameter("riderLastName");
	            			String riderDrivingLicenseNo1=request.getParameter("licenseNo");
	            			if(riderDrivingLicenseNo1!=null){
	            				System.out.println("rider in UserDB is not null");
	            				if(session.getAttribute("riderDrivingLicenseNo")!=null){
	            					session.removeAttribute("riderDrivingLicenseNo");
	            					
	            				}
	            				if(session.getAttribute("riderFirstName")!=null){
	            					session.removeAttribute("riderFirstName");
	            					
	            				}
	            				if(session.getAttribute("riderLastName")!=null){
	            					session.removeAttribute("riderLastName");
	            					
	            				}
	            			
	            			session.setAttribute("riderFirstName", riderFirstName1);
	            			session.setAttribute("riderLastName", riderLastName1);
	            			session.setAttribute("riderDrivingLicenseNo", riderDrivingLicenseNo1);
	            			riderFirstName=riderFirstName1;
	            			riderLastName=riderLastName1;
	            			riderDrivingLicenseNo=riderDrivingLicenseNo1;
	            			
	            			}
	            			if(riderDrivingLicenseNo1==null){
	            				System.out.println("rider in UserDB is null");
	            				riderFirstName=(String) session.getAttribute("riderFirstName");
	            				riderLastName=(String) session.getAttribute("riderLastName");
	            				riderDrivingLicenseNo=(String) session.getAttribute("riderDrivingLicenseNo");
	            			}
	            			
	            			AssignBoda ab = new AssignBoda(riderDrivingLicenseNo, riderFirstName,
	            					riderLastName, rs.getString("firstName"), rs.getString("lastName"),
	            					rs.getString("numberPlate"), rs.getString("model"), rs.getString("insuranceExpiryDate"));
	            	
	            	          	
	            
	                al.add(ab);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllBodasWithoutRiderToAssign catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllBodasWithoutRiderToAssign -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	
	public static ArrayList<RiderAsFromTime> getAllRiderOfBodaAsFromTime(String numberPlate,String from, String to) {
		System.out.println("inside getAllRiderOfBodaAsFromTime method");
		System.out.println("motorbike number Plate == "+ numberPlate);
		System.out.println("TimeAssigned == "+ from);
		System.out.println("TimeUnAssigned == "+ to);
		
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
			String query="SELECT `firstName`,`lastName`,`phone`,`riderIdNo`,"
	         		+ "`motorbikeNumberPlate`, `ownerIdNumber`, `timeAssigned`,	`timeUnAssigned` FROM  "
	         		+ "`registrationDetails`,`riderMotorbikeAndOwnerHistory` WHERE "
	         		+ "	riderMotorbikeAndOwnerHistory.motorbikeNumberPlate='"+numberPlate+"'"
	         		+ "	AND registrationDetails.idNo=riderMotorbikeAndOwnerHistory.riderIdNo AND (`timeAssigned`>='"+from+" 00:00:00' OR `timeAssigned` IS NULL AND "
	         		+ "`timeUnAssigned` <='"+to+" 23:59:59' OR `timeAssigned`>='"+from+" 00:00:00' AND `timeUnAssigned` IS NULL)";
			
	         	PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RiderAsFromTime> al = new ArrayList<RiderAsFromTime>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RiderAsFromTime riderAsFromTime= new RiderAsFromTime();
	    
	            	String ownerPhoneNumber=getPhoneNumber(rs.getString("ownerIdNumber"));
	            	
	            	riderAsFromTime.setOwnerIdNo(rs.getString("ownerIdNumber"));
	            	riderAsFromTime.setRiderFirstName(rs.getString("firstName"));
	            	riderAsFromTime.setMotorbikeNumberPlate(rs.getString("motorbikeNumberPlate"));
	            	riderAsFromTime.setRiderLastName(rs.getString("lastName"));
	            	riderAsFromTime.setRiderPhoneNumber(rs.getString("phone"));
	            	riderAsFromTime.setTimeAssigned(rs.getDate("timeAssigned"));
	            	riderAsFromTime.setTimeUnAssigned(rs.getDate("timeUnAssigned"));
	            	riderAsFromTime.setOwnerPhoneNumber(ownerPhoneNumber);
	            	
	            	          	
	            
	                al.add(riderAsFromTime);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRiderOfBodaAsFromTime catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRiderOfBodaAsFromTime -->" + e.getMessage());
	            return (null);
	        }
	    }
	

	public static ArrayList<AssignRider> getAllRidersWithoutBodaToAssign() {
		System.out.println("inside getAllRidersWithoutBodaToAssign method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT registrationDetails.firstName,"
	         		+ "registrationDetails.lastName,registrationDetails.idNo,"
	         		+ "drivingLicenseDetails.drivingLicenseNo,"
	         		+ "drivingLicenseDetails.drivingLicenseExpiryDate FROM "
	         		+ "registrationDetails INNER JOIN drivingLicenseDetails ON "
	         		+ "registrationDetails.idNo=drivingLicenseDetails.idNo WHERE "
	         		+ "drivingLicenseDetails.isAssigned='false'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<AssignRider> al = new ArrayList<AssignRider>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	AssignRider ar = new AssignRider();
	            	ar.setRiderId(rs.getString("idNo"));
	            	
	            	ar.setFirstName(rs.getString("firstName"));
	            	ar.setLastName(rs.getString("lastName"));
	            	ar.setDrivingLicenseNo(rs.getString("drivingLicenseNo"));
	            	ar.setExiresOn(rs.getString("drivingLicenseExpiryDate"));
	            	          	
	            
	                al.add(ar);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRidersWithoutBodaToAssign catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRidersWithoutBodaToAssign -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	
	public static HelpDesk getRegisteredUserHelp(String time){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HelpDesk hd=new HelpDesk();
		
		String query="SELECT `idNo`, `phone`, `message`, `time` FROM `helpRegisteredUsers` WHERE `time` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,time);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				hd.setIdNo(rs.getString("idNo"));
				hd.setPhone(rs.getString("phone"));
				hd.setMessage(rs.getString("message"));
		
									
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getRegisteredUserHelp method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return hd;
		
		
	}
	
	public static String getPhoneNumber(String idNo){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String phone="";
		
		String query="SELECT `phone` FROM `registrationDetails` WHERE `idNo` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				phone=rs.getString("phone");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getPhoneNumber method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return phone;
		
		
	}

	
	public static String getNumberPlate(String riderIdNo){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String motorbikeNumberPlate="";
		
		String query="SELECT `motorbikeNumberPlate` FROM `riderMotorbikeAndOwner` WHERE  `riderIdNo` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,riderIdNo);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				motorbikeNumberPlate=rs.getString("motorbikeNumberPlate");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getNumberPlate method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return motorbikeNumberPlate;
		
		
	}
	
	public static String getBodabodaOwnerIdNo(String numberPlate){
		System.out.println("In getBodabodaOwnerIdNo method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String idNo="";
		
		String query="SELECT `idNo` FROM `motorbikeAndOwner` WHERE `numberPlate` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,numberPlate);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				idNo=rs.getString("idNo");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getBodabodaOwnerIdNo method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return idNo;
		
		
	}

	public static String getRiderIdNo(String drivingLicenseNo){
		System.out.println("In getRiderIdNo method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String idNo="";
		
		String query="SELECT `idNo` FROM `drivingLicenseDetails` WHERE  `drivingLicenseNo` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,drivingLicenseNo);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				idNo=rs.getString("idNo");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getRiderIdNo method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return idNo;
		
		
	}
	
	public static String getTimeAssigned(String riderIdNo,String motorbikeNumberPlate){
		System.out.println("In getTimeAssigned method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       String timeAssigned="0";
		
		String query="SELECT `timeAssigned` FROM `riderMotorbikeAndOwnerHistory` WHERE "
				+ "riderMotorbikeAndOwnerHistory.riderIdNo='"+riderIdNo+"' AND "
						+ "( riderMotorbikeAndOwnerHistory.motorbikeNumberPlate='"+motorbikeNumberPlate+"' AND `timeUnAssigned` IS NULL)";
		
		
		try {
			 ps=connection.prepareStatement(query);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				timeAssigned=rs.getString("timeAssigned");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			timeAssigned="-1";
			
			System.out.println("Exception occured in getTimeAssigned method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return timeAssigned;
		
		
	}



	public static double getAmount(String motorbikeNumberPlate){
		System.out.println("In getAmount method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       double amount=0.0;
		
		String query="SELECT `amount` FROM `motorbikeAndOwner` WHERE `numberPlate`='"+motorbikeNumberPlate+"'";
		
		
		try {
			 ps=connection.prepareStatement(query);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				amount=rs.getDouble("amount");
													
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			amount=-1.0;
			
			System.out.println("Exception occured in getAmount method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return amount;
		
		
	}


	
	public static HelpDesk getNonUserHelp(String time){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        HelpDesk hd=new HelpDesk();
		
		String query="SELECT * FROM "
				+ "`dbms`.`helpAllVisitors` WHERE `time` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,time);
						
			rs=ps.executeQuery();
		
			while(rs.next()){
				hd.setName(rs.getString("name"));
				hd.setPhone(rs.getString("phone"));
				hd.setMessage(rs.getString("message"));
		
									
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getRegistrationDetails method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return hd;
		
		
	}
	
	public static int deleteRequestAddBoda(String numberPlate){
		
		System.out.println("inside deleteRequestAddBoda method");
		System.out.println("number plate is  "+numberPlate);
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query="DELETE FROM `requestAddBoda` WHERE numberPlate='"+numberPlate+"'";
		
		try {
			System.out.println("Trying to delete add boda request");
			System.out.println("in the deleteRequestAddBoda");

			ps=connection.prepareStatement(query);					
			
			System.out.println("done deleting the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to delete==");
			System.out.println("exception occured when trying to deleteRequestAddBoda");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;

		}finally {
			
            pool.freeConnection(connection);
            try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	

	public static int deleteRequestAddLicense(String drivingLicenseNo){
		System.out.println("inside deleteRequestAddLicense method");
		System.out.println("License number plate is  "+drivingLicenseNo);
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query="DELETE FROM `requestAddDrivingLicense` WHERE drivingLicenseNo='"+drivingLicenseNo+"'";
		
		try {
			System.out.println("Trying to delete add license request");
			System.out.println("in the deleteRequestAddLicense");

			ps=connection.prepareStatement(query);					
			
			System.out.println("done deleting the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to delete==");
			System.out.println("exception occured when trying to deleteRequestAddLicense");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;

		}finally {
			
            pool.freeConnection(connection);
            try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	
	
	public static int deleteOrReplyNonUserHelp(HelpDesk hd, String repliedMessage, String status, String time){
		System.out.println("inside deleteOrReplyNonUserHelp method");
		System.out.println("name is "+hd.getName());
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    
	 	

        String query1="DELETE FROM `helpAllVisitors` WHERE time='"+time+"'";
        
        String query2="INSERT INTO `helpAllVisitorsHistory`"
        		+ "(`name`, `phone`, `message`, `repliedMessage`, `status`)"
        		+ " VALUES ('"+hd.getName()+"','"+hd.getPhone()+"','"+hd.getMessage()+"','"+repliedMessage+"','"+status+"')";
      

		System.out.println("Trying to delete and set History");
		System.out.println("in the deleteOrReplyNonUserHelp");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
		
			int count[]=s.executeBatch();
			System.out.println("completed deleteOrReplyNonUserHelp method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to deleteOrReplyNonUserHelp");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	

		
	public static int approveBodaRequest(MotorcycleDetails md){
		System.out.println("inside approveBodaRequest method");
		System.out.println("numberPlate is "+md.getNumberPlate());
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	    String category="";
	    String whichCategory=getCategory(md.getId());
	    System.out.println("category b4 is"+whichCategory);
	    if(whichCategory.equals("general")){
	    	category="owner";
	    }
	    if(whichCategory.equals("rider")){
	    	category="owner/rider";
	    }
	    if(whichCategory.equals("admin")){
	    	category="admin";
	    }
	    if(whichCategory.equals("owner/rider")){
	    	category="owner/rider";
	    }
	    if(whichCategory.equals("owner")){
	    	category="owner";
	    }
	    if(whichCategory.equals("teller")){
	    	category="teller";
	    }
    
	    System.out.println("category after is"+category);
	 	if(hasSalaryAccount(md.getId())){

        String query1="UPDATE `registrationDetails` SET `category`='"+category+"' WHERE `idNo`='"+md.getId()+"'";
        
        String query2="UPDATE `requestAddBoda` SET `status`='Approved' WHERE `idNo`='"+md.getId()+"' AND `numberPlate`='"+md.getNumberPlate()+"'";
        
        String query3="INSERT INTO `motorcycleDetails`(`numberPlate`, `engineNo`, `frameNo`, "
        		+ "`model`, `insuranceExpiryDate`, `isAssigned`) "
        		+ "VALUES ('"+md.getNumberPlate()+"','"+md.getEngineNo()+"','"+md.getFrameNo()+"'"
        				+ ",'"+md.getModel()+"','"+md.getInsuranceExpiryDate()+"','false')";
      
        String query4="INSERT INTO `motorbikeAndOwner`(`idNo`, `numberPlate`, `amount`) "
        		+ "VALUES ('"+md.getId()+"','"+md.getNumberPlate()+"',0.00)";
        
        String query5="UPDATE `account` SET `accountMode`='"+category+"' WHERE `accountNo`='"+md.getId()+"'";

		System.out.println("Trying to approve add Boda Request");
		System.out.println("in the approveBodaRequest");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
			s.addBatch(query3);
			s.addBatch(query4);
			s.addBatch(query5);
		
			int count[]=s.executeBatch();
			System.out.println("completed approveBodaRequest method");
			connection.commit();
		
			return count[0]+count[1];						 
	 	
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				
			System.out.println("exception occured when trying to approveBodaRequest");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}else {


	        String query1="UPDATE `registrationDetails` SET `category`='"+category+"' WHERE `idNo`='"+md.getId()+"'";
	        
	        String query2="UPDATE `requestAddBoda` SET `status`='Approved' WHERE `idNo`='"+md.getId()+"' AND `numberPlate`='"+md.getNumberPlate()+"'";
	        
	        String query3="INSERT INTO `motorcycleDetails`(`numberPlate`, `engineNo`, `frameNo`, "
	        		+ "`model`, `insuranceExpiryDate`, `isAssigned`) "
	        		+ "VALUES ('"+md.getNumberPlate()+"','"+md.getEngineNo()+"','"+md.getFrameNo()+"'"
	        				+ ",'"+md.getModel()+"','"+md.getInsuranceExpiryDate()+"','false')";
	      
	        String query4="INSERT INTO `motorbikeAndOwner`(`idNo`, `numberPlate`, `amount`) "
	        		+ "VALUES ('"+md.getId()+"','"+md.getNumberPlate()+"',0.00)";
	        
	        String query5="INSERT INTO `account`(`accountNo`, `accountType`, `accountMode`,"
	        		+ " `amount`, `isFreezed`) "
	        		+ "VALUES ('"+md.getId()+"','Salary','"+category+"',0.00,'false')";

			System.out.println("Trying to approve add Boda Request");
			System.out.println("in the approveBodaRequest");

			
			try {
				connection.setAutoCommit( false );
				s.addBatch(query1);
				s.addBatch(query2);
				s.addBatch(query3);
				s.addBatch(query4);
				s.addBatch(query5);
			
				int count[]=s.executeBatch();
				System.out.println("completed approveBodaRequest method");
				connection.commit();
			
				return count[0]+count[1];						 
		 	
				
			} catch (SQLException e) {

					try {
						connection.rollback();
						System.out.println("It roll back");
					} catch (SQLException e1) {
						System.out.println("unable to roll back");
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					
				System.out.println("exception occured when trying to approveBodaRequest");
				
				System.out.println("error is=="+e);
				e.printStackTrace();
				 System.out.println(e);
		            return 0;
			}finally {
				
	            pool.freeConnection(connection);
	            try {
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
	}	
	
	public static int approveLicenseRequest(DrivingLicenseDetails drd){
		System.out.println("inside approveLicenseRequest method");
		System.out.println("id is is"+drd.getId());
		System.out.println("License number is "+drd.getDrivingLicenseNo());
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    String category="";
    String whichCategory=getCategory(drd.getId());
    System.out.println("category b4 is"+whichCategory);
    if(whichCategory.equals("general")){
    	category="rider";
    }
    if(whichCategory.equals("owner")){
    	category="owner/rider";
    }
    if(whichCategory.equals("admin")){
    	category="admin";
    }
    if(whichCategory.equals("owner/rider")){
    	category="owner/rider";
    }
    if(whichCategory.equals("teller")){
    	category="teller";
    }
	 	
    System.out.println("category after is"+category);
    if(hasSalaryAccount(drd.getId())){

        String query1="UPDATE `registrationDetails` SET `category`='"+category+"' WHERE `idNo`='"+drd.getId()+"'";
        
        String query2="UPDATE `requestAddDrivingLicense` SET `status`='Approved' WHERE `idNo`='"+drd.getId()+"'";
        
        String query3="INSERT INTO `drivingLicenseDetails`"
        		+ "(`idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`, `isAssigned`) "
        		+ "VALUES ('"+drd.getId()+"','"+drd.getDrivingLicenseNo()+"','"+drd.getReferenceNo()+"',"
        				+ "'"+drd.getDrivingLicenseExpiryDate()+"','false')";
      
        
        
        String query4="UPDATE `account` SET `accountMode`='"+category+"' WHERE `accountNo`='"+drd.getId()+"'";

		System.out.println("Trying to approve add Boda Request");
		System.out.println("in the approveBodaRequest");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
			s.addBatch(query3);
			s.addBatch(query4);
		
			int count[]=s.executeBatch();
			System.out.println("completed approveLicenseRequest method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to approveLicenseRequest");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }else {


        String query1="UPDATE `registrationDetails` SET `category`='"+category+"' WHERE `idNo`='"+drd.getId()+"'";
        
        String query2="UPDATE `requestAddDrivingLicense` SET `status`='Approved' WHERE `idNo`='"+drd.getId()+"'";
        
        String query3="INSERT INTO `drivingLicenseDetails`"
        		+ "(`idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`, `isAssigned`) "
        		+ "VALUES ('"+drd.getId()+"','"+drd.getDrivingLicenseNo()+"','"+drd.getReferenceNo()+"',"
        				+ "'"+drd.getDrivingLicenseExpiryDate()+"','false')";
      
        
        
        String query4="INSERT INTO `account`(`accountNo`, `accountType`, `accountMode`,"
        		+ " `amount`, `isFreezed`) "
        		+ "VALUES ('"+drd.getId()+"','Salary','"+category+"',0.00,'false')";

		System.out.println("Trying to approve add Boda Request");
		System.out.println("in the approveBodaRequest");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
			s.addBatch(query3);
			s.addBatch(query4);
		
			int count[]=s.executeBatch();
			System.out.println("completed approveLicenseRequest method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to approveLicenseRequest");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    
		
	}
		
		
	}	
	
	public static int deleteOrReplyRegisteredUserHelp(HelpDesk hd, String repliedMessage, String status, String time){
		System.out.println("inside deleteOrReplyRegisteredUserHelp method");
		System.out.println("name is "+hd.getName());
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
	 	

        String query1="DELETE FROM `helpRegisteredUsers` WHERE time='"+time+"'";
        
        String query2="INSERT INTO `helpRegisteredUsersHistory`"
        		+ "(`idNo`, `phone`, `message`, `repliedMessage`, `status`)"
        		+ " VALUES ('"+hd.getIdNo()+"','"+hd.getPhone()+"','"+hd.getMessage()+"','"+repliedMessage+"','"+status+"')";
      

		System.out.println("Trying to delete and set History");
		System.out.println("in the deleteOrReplyRegisteredUserHelp");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
		
			int count[]=s.executeBatch();
			System.out.println("completed deleteOrReplyRegisteredUserHelp method");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to deleteOrReplyRegisteredUserHelp");
			
			System.out.println("error is=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	
	
	public static ArrayList<DrivingLicenseDetails> getAllRequestAddLicenseToApprove() {
		System.out.println("inside getAllRequestAddLicenseToApprove method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();*/

		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`,"
	         		+ " `isAssigned`, `status` FROM `requestAddDrivingLicense` WHERE `status`='Pending'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<DrivingLicenseDetails> al = new ArrayList<DrivingLicenseDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	DrivingLicenseDetails drd = new DrivingLicenseDetails();
	            	drd.setIdNo(rs.getString("idNo"));
	            	drd.setDrivingLicenseNo(rs.getString("drivingLicenseNo"));
	            	drd.setReferenceNo(rs.getString("referenceNo"));
	            	drd.setIsAssigned(rs.getString("isAssigned"));
	            	drd.setDrivingLicenseExpiryDate(rs.getDate("drivingLicenseExpiryDate"));
	            	drd.setStatus(rs.getString("status"));
	            	
	            
	                al.add(drd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entries found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRequestAddLicenseToApprove catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRequestAddLicenseToApprove -->" + e.getMessage());
	            return (null);
	        }
	    }


	public static ArrayList<DrivingLicenseDetails> getMyLicenseDetails(String idNo) {
		System.out.println("inside getMyLicenseDetails method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();*/

		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `drivingLicenseNo`, `referenceNo`,"
	         		+ " `drivingLicenseExpiryDate`, `isAssigned` FROM `drivingLicenseDetails` WHERE `idNo`='"+idNo+"'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<DrivingLicenseDetails> al = new ArrayList<DrivingLicenseDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	DrivingLicenseDetails drd = new DrivingLicenseDetails();
	            	drd.setIdNo(rs.getString("idNo"));
	            	drd.setDrivingLicenseNo(rs.getString("drivingLicenseNo"));
	            	drd.setReferenceNo(rs.getString("referenceNo"));
	            	drd.setIsAssigned(rs.getString("isAssigned"));
	            	drd.setDrivingLicenseExpiryDate(rs.getDate("drivingLicenseExpiryDate"));
	            	//drd.setStatus(rs.getString("status"));
	            	
	            
	                al.add(drd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entries found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getMyLicenseDetails catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getMyLicenseDetails -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	public static ArrayList<MotorcycleDetails> getAllRequestAddBodaToApprove() {
		System.out.println("inside getAllRequestAddBodaToApprove method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `numberPlate`, `engineNo`, `frameNo`, `model`,"
	         		+ " `insuranceExpiryDate`, `status` FROM `requestAddBoda` WHERE `status`='Pending'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<MotorcycleDetails> al = new ArrayList<MotorcycleDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	MotorcycleDetails md = new MotorcycleDetails();
	            	md.setIdNo(rs.getString("idNo"));
	            	md.setNumberPlate(rs.getString("numberPlate"));
	            	md.setEngineNo(rs.getString("engineNo"));
	            	md.setFrameNo(rs.getString("frameNo"));
	            	md.setInsuranceExpiryDate(rs.getDate("insuranceExpiryDate"));
	            	md.setModel(rs.getString("model"));
	            	md.setStatus(rs.getString("status"));
	            	
	            
	                al.add(md);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRequestAddBodaToApprove catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRequestAddBodaToApprove -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static ArrayList<MotorcycleDetails> getAllRequestAddBoda(String idNo) {
		System.out.println("inside getAllRequestAddBoda method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `numberPlate`, `engineNo`, `frameNo`, `model`,"
	         		+ " `insuranceExpiryDate`, `status` FROM `requestAddBoda` WHERE `idNo`='"+idNo+"'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<MotorcycleDetails> al = new ArrayList<MotorcycleDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	MotorcycleDetails md = new MotorcycleDetails();
	            	md.setIdNo(rs.getString("idNo"));
	            	md.setNumberPlate(rs.getString("numberPlate"));
	            	md.setEngineNo(rs.getString("engineNo"));
	            	md.setFrameNo(rs.getString("frameNo"));
	            	md.setInsuranceExpiryDate(rs.getDate("insuranceExpiryDate"));
	            	md.setModel(rs.getString("model"));
	            	md.setStatus(rs.getString("status"));
	            	
	            
	                al.add(md);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRegisteredUser() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRegisteredUser() -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static MotorcycleDetails getBodaDetailsInRequest(String numberPlate) {
		System.out.println("inside getBodaDetailsInRequest method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();

	//	Connection con=null;
		try {
			//con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `numberPlate`, `engineNo`, `frameNo`, "
	         		+ "`model`, `insuranceExpiryDate`, `status` FROM `requestAddBoda` WHERE `numberPlate`='"+numberPlate+"'";
				PreparedStatement ps = con.prepareStatement(query);
				MotorcycleDetails md = new MotorcycleDetails();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	
	            	md.setId(rs.getString("idNo"));
	            	md.setNumberPlate(rs.getString("numberPlate"));
	            	md.setEngineNo(rs.getString("engineNo"));
	            	md.setFrameNo(rs.getString("frameNo"));
	            	md.setInsuranceExpiryDate(rs.getDate("insuranceExpiryDate"));
	            	md.setModel(rs.getString("model"));
	            	md.setStatus(rs.getString("status"));
	            
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return md;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getBodaDetailsInRequest() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getBodaDetailsInRequest() -->" + e.getMessage());
	            return (null);
	        }
	    }
		
	public static DrivingLicenseDetails getLicenseDetailsInRequest(String drivingLicenseNo) {
		System.out.println("inside getLicenseDetailsInRequest method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();

	//	Connection con=null;
		try {
		//	con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`, "
	         		+ "`isAssigned`, `status` FROM `requestAddDrivingLicense` WHERE `drivingLicenseNo`='"+drivingLicenseNo+"'";
				PreparedStatement ps = con.prepareStatement(query);
	           
				DrivingLicenseDetails drd = new DrivingLicenseDetails();
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	
	            	drd.setId(rs.getString("idNo"));
	            	drd.setDrivingLicenseNo(rs.getString("drivingLicenseNo"));
	            	drd.setReferenceNo(rs.getString("referenceNo"));
	            	drd.setIsAssigned(rs.getString("isAssigned"));
	            	drd.setDrivingLicenseExpiryDate(rs.getDate("drivingLicenseExpiryDate"));
	            	drd.setStatus(rs.getString("status"));
	            	
	          
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return drd;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getDrivingLicenseDetails() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getDrivingLicenseDetails -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static ArrayList<DrivingLicenseDetails> getAllRequestAddLicense(String idNo) {
		System.out.println("inside getAllRequestAddLicense method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`,"
	         		+ " `isAssigned`, `status` FROM `requestAddDrivingLicense` WHERE `idNo`='"+idNo+"'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<DrivingLicenseDetails> al = new ArrayList<DrivingLicenseDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	DrivingLicenseDetails drd = new DrivingLicenseDetails();
	            	drd.setIdNo(rs.getString("idNo"));
	            	drd.setDrivingLicenseNo(rs.getString("drivingLicenseNo"));
	            	drd.setReferenceNo(rs.getString("referenceNo"));
	            	drd.setIsAssigned(rs.getString("isAssigned"));
	            	drd.setDrivingLicenseExpiryDate(rs.getDate("drivingLicenseExpiryDate"));
	            	drd.setStatus(rs.getString("status"));
	            	
	            
	                al.add(drd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRequestAddLicense catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRequestAddLicense -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static ArrayList<RegistrationDetails> getAllRegisteredUsers() {
		System.out.println("inside getAllRegisteredUser method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `firstName`, `middleName`, `lastName`, `dob`, `phone`, `category`, `gender` FROM `registrationDetails`";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RegistrationDetails> al = new ArrayList<RegistrationDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RegistrationDetails rd = new RegistrationDetails();
	            	rd.setIdNo(rs.getString("idNo"));
	            	rd.setFirstName(rs.getString("firstName"));
	            	rd.setMiddleName(rs.getString("middleName"));
	            	rd.setLastName(rs.getString("lastName"));
	            	rd.setDob(rs.getDate("dob"));
	            	rd.setPhone(rs.getString("phone"));
	            	rd.setCategory(rs.getString("category"));
	            	rd.setGender(rs.getString("gender"));
	            
	                al.add(rd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRegisteredUser() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRegisteredUser() -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static ArrayList<RegistrationDetails> getAllRegisteredRiders() {
		System.out.println("inside getAllRegisteredRiders method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `firstName`, `middleName`, `lastName`, "
	         		+ "`dob`, `phone`, `category`, `gender` "
	         		+ "FROM `registrationDetails` "
	         		+ "WHERE `category`='rider' OR `category`='owner/rider'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RegistrationDetails> al = new ArrayList<RegistrationDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RegistrationDetails rd = new RegistrationDetails();
	            	rd.setIdNo(rs.getString("idNo"));
	            	rd.setFirstName(rs.getString("firstName"));
	            	rd.setMiddleName(rs.getString("middleName"));
	            	rd.setLastName(rs.getString("lastName"));
	            	rd.setDob(rs.getDate("dob"));
	            	rd.setPhone(rs.getString("phone"));
	            	rd.setCategory(rs.getString("category"));
	            	rd.setGender(rs.getString("gender"));
	            
	                al.add(rd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRegisteredRiders() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRegisteredRiders() -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	
	public static ArrayList<RegistrationDetails> getAllRegisteredFemaleUsers() {
		System.out.println("inside getAllRegisteredFemaleUser method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `firstName`, `middleName`, `lastName`, `dob`, `phone`, `category`, `gender` FROM `registrationDetails` WHERE 	`gender`='female'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RegistrationDetails> al = new ArrayList<RegistrationDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RegistrationDetails rd = new RegistrationDetails();
	            	rd.setIdNo(rs.getString("idNo"));
	            	rd.setFirstName(rs.getString("firstName"));
	            	rd.setMiddleName(rs.getString("middleName"));
	            	rd.setLastName(rs.getString("lastName"));
	            	rd.setDob(rs.getDate("dob"));
	            	rd.setPhone(rs.getString("phone"));
	            	rd.setCategory(rs.getString("category"));
	            	rd.setGender(rs.getString("gender"));
	            
	                al.add(rd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRegisteredFemaleUser() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRegisteredFemaleUser() -->" + e.getMessage());
	            return (null);
	        }
	    }
	public static ArrayList<RegistrationDetails> getAllRegisteredMaleUsers() {
		System.out.println("inside getAllRegisteredMaleUser method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `firstName`, `middleName`, `lastName`, `dob`, `phone`, `category`, `gender` FROM `registrationDetails` WHERE `gender`='male'";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<RegistrationDetails> al = new ArrayList<RegistrationDetails>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	RegistrationDetails rd = new RegistrationDetails();
	            	rd.setIdNo(rs.getString("idNo"));
	            	rd.setFirstName(rs.getString("firstName"));
	            	rd.setMiddleName(rs.getString("middleName"));
	            	rd.setLastName(rs.getString("lastName"));
	            	rd.setDob(rs.getDate("dob"));
	            	rd.setPhone(rs.getString("phone"));
	            	rd.setCategory(rs.getString("category"));
	            	rd.setGender(rs.getString("gender"));
	            
	                al.add(rd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getAllRegisteredMaleUser() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getAllRegisteredMaleUser() -->" + e.getMessage());
	            return (null);
	        }
	    }


	public static ArrayList<ForgotPassword> getForgotPasswordHistory() {
		System.out.println("inside getForgotPasswordHistory method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `confirmationCode`,`date` FROM `forgotPasswordHistory`";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<ForgotPassword> al = new ArrayList<ForgotPassword>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	ForgotPassword fp = new ForgotPassword();
	            	fp.setIdNo(rs.getString("idNo"));
	            	fp.setConfirmationCode(rs.getString("confirmationCode"));
	              	fp.setDob(rs.getDate("date"));
	            	
	            
	                al.add(fp);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getForgetPasswordHistory() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getForgetPasswordHistory() -->" + e.getMessage());
	            return (null);
	        }
	    }
	
	public static ArrayList<ForgotPassword> getChangePasswordHistory() {
		System.out.println("inside getChangePasswordHistory method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`,`date` FROM `changePasswordHistory`";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<ForgotPassword> al = new ArrayList<ForgotPassword>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	ForgotPassword fp = new ForgotPassword();
	            	fp.setIdNo(rs.getString("idNo"));
	              	fp.setDob(rs.getDate("date"));
	            	
	            
	                al.add(fp);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getChangePasswordHistory() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getChangePasswordHistory() -->" + e.getMessage());
	            return (null);
	        }
	    }
		
	public static ArrayList<HelpDesk> getHelpRegisteredUsers() {
		System.out.println("inside getHelpRegisteredUsers method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `idNo`, `phone`, `message`, `time` FROM `helpRegisteredUsers`";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<HelpDesk> al = new ArrayList<HelpDesk>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	HelpDesk hd = new HelpDesk();
	            	hd.setIdNo(rs.getString("idNo"));
	            	hd.setPhone(rs.getString("phone"));
	            	hd.setMessage(rs.getString("message"));
	            	hd.setName(rs.getString("time"));
	              
	            	
	            
	                al.add(hd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getHelpRegisteredUsers() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getHelpRegisteredUsers() -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	public static ArrayList<HelpDesk> getHelpAllVisitors() {
		System.out.println("inside getHelpAllVisitors method");
		/*ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
*/
		Connection con=null;
		try {
			con=DataConnect.getConnection();
	         String query="SELECT `name`, `phone`, `message`, `time` FROM `helpAllVisitors`";
				PreparedStatement ps = con.prepareStatement(query);
	            ArrayList<HelpDesk> al = new ArrayList<HelpDesk>();
	            
	            ResultSet rs = ps.executeQuery();
	            boolean found = false;
	            while (rs.next()) {
	            	HelpDesk hd = new HelpDesk();
	            	hd.setName(rs.getString("name"));
	            	hd.setPhone(rs.getString("phone"));
	            	hd.setMessage(rs.getString("message"));
	            	hd.setIdNo(rs.getString("time"));
	              
	            	
	            
	                al.add(hd);
	                found = true;
	                System.out.println("setting data...");
	            }
	            rs.close();
	            if (found) {
	            	System.out.println("returning the list");
	                return al;
	            } else {
	            	System.out.println("no entry found");
	            	return null; // no entires found
	                
	            }
	        } catch (Exception e) {
	        	System.out.println("Error occured In getHelpAllVisitors() catch exception..........."
	        			+ "..........................."
	        			+ "..........................."
	        			+ ".............................");
	            System.out.println("Error In getHelpAllVisitors() -->" + e.getMessage());
	            return (null);
	        }
	    }

	
	public static int validate(String idNo, String password) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
    
	       PreparedStatement ps = null;
	       ResultSet rs=null;

	       try {
	      
	           ps = con.prepareStatement("Select idNo, password from registrationDetails where idNo = ? and password = ?");
	           ps.setString(1, idNo);
	           try {
				ps.setString(2, AES_EncryptDecrypt.encrypt(password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Error encrypting password");
				e.printStackTrace();
			}
	           

	            rs = ps.executeQuery();

	           if (rs.next()) {
	               //result found, means valid inputs
	        	   System.out.println(rs.getString(1));
	        	   System.out.println(rs.getString(2));
	               return 1;
	           }
	       } catch (SQLException ex) {
	           System.out.println("Login error -->" + ex.getMessage());
	           return -1;
	       } finally {
	    	   DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);//from here
	            if (con != null) {
	            	pool.freeConnection(con);
	            } else {
	            	return -1;
	            }
	       }
	       return 0;
	   }
	
	public static boolean hasSalaryAccount(String idNo) {
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
	       PreparedStatement ps = null;
	       ResultSet rs=null;

	       try {
	      
	           ps = con.prepareStatement("SELECT `accountNo` FROM `account` WHERE `accountNo`= ?");
	           ps.setString(1, idNo);
	           rs = ps.executeQuery();

	           if (rs.next()) {
	               //result found, means valid inputs
	        	   System.out.println(rs.getString(1));
	        	  // System.out.println(rs.getString(2));
	               return true;
	           }
	       } catch (SQLException ex) {
	           System.out.println("Login error -->" + ex.getMessage());
	           return false;
	       } finally {
	    	   DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(con);
	       }
	       return false;
	   }
	
	public static int setProfilePicture(String idNo,String password, String path){

		System.out.println("inside setProfilePicture method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        

        String query="UPDATE `dbms`.`registrationDetails` SET"
        		+ " `registrationDetails`.`profilePicture`=?"
        		+ "WHERE `idNo`=? AND `password`=?";

		System.out.println("Trying to update rProfilePicture record");
		System.out.println("in the setProfilePicture function");

		
		try {
			ps=connection.prepareStatement(query);
			//ps.setString(1,account.getIdNo());
			ps.setString(1,path);
			ps.setString(2,idNo);
			ps.setString(3,password);
			
			
			
			System.out.println("done updating setting ProfilePicture");
			return ps.executeUpdate();
			 
						 
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception occured when trying"
					+ " to update ProfilePicture");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
			
	public static RegistrationDetails getRegistrationDetails(String idNo, String password){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        RegistrationDetails rd=null;
		
		String query="SELECT * FROM "
				+ "`dbms`.`registrationDetails` WHERE `idNo` =? AND `password` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			try {
				ps.setString(2,AES_EncryptDecrypt.encrypt(password));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
			rs=ps.executeQuery();
		
			while(rs.next()){
				rd=new RegistrationDetails(rs.getString("idNo"),
						rs.getString("firstName"),rs.getString("middleName"),
						rs.getString("lastName"),rs.getString("password"),
						rs.getString("profilePicture"),rs.getString("category"),
						rs.getDate("dob"),rs.getString("phone"),rs.getString("gender"));
					
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getRegistrationDetails method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return rd;
		
		
	}
	
	public static int saveRequestAddBoda(MotorcycleDetails md){
		System.out.println("Connection pool is calling saveRequestAddBoda data method");
		
		System.out.println("inside saveRequestAddBoda method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;

        String query="INSERT INTO `requestAddBoda`"
        		+ "(`idNo`, `numberPlate`, `engineNo`, `frameNo`, `model`, `insuranceExpiryDate`,`status`)"
        		+ " VALUES (?,?,?,?,?,?,?)";

		System.out.println("Trying to save the data");
		System.out.println("in the saving data function");
		System.out.println("values");
		System.out.println("idNo"+md.getIdNo());
		System.out.println("Number Plate"+md.getNumberPlate());
		System.out.println("Engine No"+md.getEngineNo());
		System.out.println("frame No"+md.getFrameNo());
		System.out.println("formated Date"+sdf.format(md.getInsuranceExpiryDate()));
		System.out.println("model"+md.getModel());
		System.out.println("status"+md.getStatus());
		
		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,md.getIdNo());
			ps.setString(2,md.getNumberPlate());
			ps.setString(3,md.getEngineNo());
			ps.setString(4,md.getFrameNo());
			ps.setString(5,md.getModel());
			ps.setString(6,sdf.format(md.getInsuranceExpiryDate()));
			ps.setString(7,md.getStatus());
			
			
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to saveRequestAddBoda==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
		
	public static int saveRequestAddLicense(DrivingLicenseDetails drd){
		System.out.println("Connection pool is calling RequestAddLicense data method");
		
		System.out.println("inside saveRequestAddLicense method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;

        String query="INSERT INTO `requestAddDrivingLicense`"
        		+ "(`idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`, `isAssigned`, `status`)"
        		+ " VALUES (?,?,?,?,?,?)";

		System.out.println("Trying to save the data");
		System.out.println("in the saving data function");
		System.out.println("values");
		System.out.println("idNo"+drd.getIdNo());
		System.out.println("Driving License Number"+drd.getDrivingLicenseNo());
		System.out.println("Reference No"+drd.getReferenceNo());
		System.out.println("Driving License Expiry Date"+sdf.format(drd.getDrivingLicenseExpiryDate()));
		System.out.println("isAssigned"+drd.getIsAssigned());
		System.out.println("status"+drd.getStatus());
		
		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,drd.getIdNo());
			ps.setString(2,drd.getDrivingLicenseNo());
			ps.setString(3,drd.getReferenceNo());
			ps.setString(4,sdf.format(drd.getDrivingLicenseExpiryDate()));
			ps.setString(5,drd.getIsAssigned());
			ps.setString(6,drd.getStatus());
			
			
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to saveRequestAddLicense==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
	
	public static int saveRegistrationDetails(RegistrationDetails rd){
		System.out.println("Connection pool is calling saveRegistrationDetails data method");
		
		System.out.println("inside saveRegistrationDetails method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        PreparedStatement ps = null;

        String query="INSERT INTO `dbms`.`registrationDetails` "
        		+ "(`idNo`,`firstName`,`middleName`, `lastName`,`dob`, `phone`,"
        		+ " `password`, `category`, `profilePicture`,`gender`) VALUES (?,?,?,?,?,?,?,?,?,?)";

		System.out.println("Trying to save the data");
		System.out.println("in the saving data function");
		System.out.println("values");
		System.out.println("FirstName"+rd.getFirstName());
		System.out.println("LastName"+rd.getLastName());
		System.out.println("IdNo"+rd.getIdNo());
		System.out.println("Dob"+rd.getDob());
		System.out.println("formated Date"+sdf.format(rd.getDob()));
		System.out.println("Password"+rd.getPassword());
		System.out.println("gender"+rd.getGender());
		System.out.println("category"+rd.getCategory());
		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,rd.getIdNo());
			ps.setString(2,rd.getFirstName());
			ps.setString(3,rd.getMiddleName());
			ps.setString(4,rd.getLastName());
			ps.setString(5,sdf.format(rd.getDob()));
			ps.setString(6,rd.getPhone());
			ps.setString(7,rd.getPassword());
			ps.setString(8,rd.getCategory());
			ps.setString(9,rd.getProfilePicture());
			ps.setString(10,rd.getGender());
			
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
			if(connection!=null){
				 pool.freeConnection(connection);	
			}else {
				return -1;
			}
           
		}
		
		
	}
	
	public static int saveHelpAllVisitors(String name,String phone,String message){
		System.out.println("Connection pool is calling saveHelpAllVisitors data method");
		
		System.out.println("inside saveHelpAllVisitors method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query="INSERT INTO `dbms`.`helpAllVisitors` "
        		+ "(`name`,`phone`,`message`) VALUES (?,?,?)";

		System.out.println("Trying to save the data");
		System.out.println("in the saving data function");
		System.out.println("values");
		System.out.println("FirstName"+name);
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,name);
			ps.setString(2,phone);
			ps.setString(3,message);
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
	
	public static int saveHelpRegisteredUsers(String idNo,String phone,String message){
		System.out.println("Connection pool is calling saveHelpAllVisitors data method");
		
		System.out.println("inside saveHelpRegisteredUsers method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query="INSERT INTO `dbms`.`helpRegisteredUsers` "
        		+ "(`idNo`,`phone`,`message`) VALUES (?,?,?)";

		System.out.println("Trying to save the data");
		System.out.println("in the saving data function");
		System.out.println("values");
		System.out.println("id Number"+idNo);
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			ps.setString(2,phone);
			ps.setString(3,message);
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
	
	public static boolean drivingLicenseIdNumberExists(String idNo) {
		System.out.println("idNo in drivingLicenseIdNumberExists is  "+idNo);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `idNo` FROM `drivingLicenseDetails` WHERE `idNo`='"+idNo+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

	
	public static boolean drivingLicenseNumberExists(String drivingLicenseNo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `drivingLicenseNo` FROM `drivingLicenseDetails` WHERE `drivingLicenseNo`='"+drivingLicenseNo+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

	
	public static boolean drivingLicenseIdNumberExistsInRequest(String idNo) {
		System.out.println("idNo in drivingLicenseIdNumberExistsInRequest is  "+idNo);
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `idNo` FROM `requestAddDrivingLicense` WHERE `idNo`='"+idNo+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
	
	
	
	
	public static boolean drivingLicenseNumberExistsInRequest(String drivingLicenseNo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `drivingLicenseNo` FROM `requestAddDrivingLicense` WHERE `drivingLicenseNo`='"+drivingLicenseNo+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

	
	public static boolean motorbikeNumberPlateExists(String numberPlate) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `numberPlate` FROM `motorcycleDetails` WHERE `numberPlate`='"+numberPlate+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

		
	public static boolean motorbikeNumberPlateExistsInRequest(String numberPlate) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `numberPlate` FROM `requestAddBoda` WHERE `numberPlate`='"+numberPlate+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }


		
	public static int userIdNoExists(String idNo) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT `registrationDetails`.`idNo` FROM `dbms`.`registrationDetails`"
        		+ "WHERE `registrationDetails`.`idNo`='"+idNo+"'";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if(rs.next()){
            	return 1;
            }else {
				return 0;
			}
            
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);//from here
            if(connection!=null){
            	pool.freeConnection(connection);
            }else {
				return -1;
			}
            
        }
    }
	
	
	public static RegistrationDetails getFogotPasswordDetails(String idNo, String dob){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        RegistrationDetails rd=null;
        int nil=0;
        java.util.Date today=new java.util.Date();
        		
		String query="SELECT * FROM "
				+ "`dbms`.`registrationDetails` WHERE `idNo` =? AND `dob`=?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			ps.setString(2,dob);
			rs=ps.executeQuery();
		
			while(rs.next()){
				rd=new RegistrationDetails(rs.getString("idNo"),
						rs.getString("firstName"),rs.getString("middleName"),
						rs.getString("lastName"),rs.getString("password"),
						rs.getString("profilePicture"),rs.getString("category"),
						rs.getDate("dob"),rs.getString("phone"),rs.getString("gender"));
				nil=1;
					
			}
			
			if(nil==0){
				rd=new RegistrationDetails("0","0","0","0","0","0","0",today,"0","0");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getFogotPasswordDetails method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return rd;
		
		
	}
	
	public static String getCategory(String idNo){
		System.out.println("called getCategory method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String category="";
		
		String query="SELECT * FROM "
				+ "`dbms`.`registrationDetails` WHERE `idNo` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			rs=ps.executeQuery();
		
			while(rs.next()){
				
				category=rs.getString("category");
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getCategory method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return category;
		
		
	}
		
	public static String getConfirmationCode(String idNo){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String confirmationCode="";
		
		String query="SELECT * FROM "
				+ "`dbms`.`forgotPassword` WHERE `idNo` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			rs=ps.executeQuery();
		
			while(rs.next()){
				
				confirmationCode=rs.getString("confirmationCode");
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			System.out.println("Exception occured in getConfirmationCode method");
			e.printStackTrace();
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return confirmationCode;
		
		
	}
	
	public static boolean isFogotPasswordDetailsSet(String idNo){
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean test=false;
		
		String query="SELECT * FROM "
				+ "`dbms`.`forgotPassword` WHERE `idNo` =?";
		
		
		try {
			 ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			rs=ps.executeQuery();
		
			while(rs.next()){
			test=true;
					
			}
			
			
			
		} catch (SQLException e) {
		
			
			System.out.println("Exception occured in getFogotPasswordDetails method");
			e.printStackTrace();
			test=false;
		
		} finally {
			
			 DBUtil.closeResultSet(rs);
	            DBUtil.closePreparedStatement(ps);
	            pool.freeConnection(connection);
		}
		
		return test;
		
		
	}
	
	public static int saveFogotPasswordDetails(String idNo, String confirmationCode){
		System.out.println("Connection pool is calling saveFogotPasswordDetails data method");
		
		System.out.println("inside saveFogotPasswordDetails method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
   
        PreparedStatement ps = null;

        String query="INSERT INTO `dbms`.`forgotPassword` "
        		+ "(`idNo`,`confirmationCode`"
        		+ ") VALUES (?,?)";

		System.out.println("Trying to save the data");
		System.out.println("in the saving data function");
		System.out.println("values");
		System.out.println("FirstName"+idNo);
		System.out.println("LastName"+confirmationCode);
		
		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
			ps.setString(2,confirmationCode);

			
			System.out.println("done saving the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to save==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
		

	
	public static int resetPassword(String idNo,String confirmationCode,String newPassword){
		System.out.println("inside resetpassword method");
		System.out.println("idNo is "+idNo);
		System.out.println("newPassword is "+newPassword);
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
       String newPs="";
	try {
		newPs = AES_EncryptDecrypt.encrypt(newPassword);
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	 	

        String query1="UPDATE `dbms`.`registrationDetails` SET"
        		+ " `registrationDetails`.`password`='"+newPs+"'"
        		+ "WHERE `idNo`='"+idNo+"'";
        
        String query2="INSERT INTO `forgotPasswordHistory`(`idNo`, `confirmationCode`, `newPassword`)"
        		+ " VALUES ('"+idNo+"','"+confirmationCode+"','"+newPs+"')";

		System.out.println("Trying to reset Password");
		System.out.println("in the resetPassword function");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
		
			int count[]=s.executeBatch();
			System.out.println("done reseting password");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to reset password");
			
			System.out.println("error id=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}	
	
	public static int changePassword(String idNo,String oldPassword,String newPassword){
		System.out.println("inside change password");
		System.out.println("idNo is "+idNo);
		System.out.println("newPassword is "+newPassword);
		
		System.out.println("connecting dbms data");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        Statement s=null;
		try {
			s = connection.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
       String newPs="";
	try {
		newPs = AES_EncryptDecrypt.encrypt(newPassword);
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	   String oldPs="";
	try {
		oldPs = AES_EncryptDecrypt.encrypt(oldPassword);
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	

        String query1="UPDATE `dbms`.`registrationDetails` SET"
        		+ " `registrationDetails`.`password`='"+newPs+"'"
        		+ "WHERE `idNo`='"+idNo+"' AND `password`='"+oldPs+"'";
        
        String query2="INSERT INTO `changePasswordHistory`(`idNo`, `oldPassword`, `newPassword`)"
        		+ " VALUES ('"+idNo+"','"+oldPs+"','"+newPs+"')";

		System.out.println("Trying to change Password");
		System.out.println("in the changePassword function");

		
		try {
			connection.setAutoCommit( false );
			s.addBatch(query1);
			s.addBatch(query2);
		
			int count[]=s.executeBatch();
			System.out.println("done changing password");
			connection.commit();
			return count[0]+count[1];						 
			 
			
		} catch (SQLException e) {

				try {
					connection.rollback();
					System.out.println("It roll back");
				} catch (SQLException e1) {
					System.out.println("unable to roll back");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println("exception occured when trying to change password");
			
			System.out.println("error id=="+e);
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
            pool.freeConnection(connection);
            try {
				s.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static int deleteForgotPasswordDetails(String idNo){
		System.out.println("Connection pool is calling deleteForgotPasswordDetails");
		
		System.out.println("inside deleteForgotPasswordDetails method");
		ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query="DELETE FROM `dbms`.`forgotPassword` "
        		+ "WHERE (`idNo`=?)";

		System.out.println("Trying to delete data");
		System.out.println("in the saving deleteForgotPasswordDetails function");

		
		try {
			ps=connection.prepareStatement(query);
			ps.setString(1,idNo);
					
			
			System.out.println("done deleting the data");
			return ps.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception trying to delete==");
			e.printStackTrace();
			 System.out.println(e);
	            return 0;
		}finally {
			
			DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
		}
		
		
	}
	

}