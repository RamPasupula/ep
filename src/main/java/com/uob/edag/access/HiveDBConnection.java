/**
 * 
 */
package com.uob.edag.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.uob.edag.util.PropertyLoadUtil;
import com.uob.edag.util.Util;

/**
 * @author vencp9
 *
 */
public class HiveDBConnection {
	private static Connection connection = null;

	
	/*private static final  String DRIVER = Util.getProperties("hive.driver");
	private static final String URL = Util.getProperties("hive.url");
	*/
	private static final String DRIVER = PropertyLoadUtil.getProperty("hive.driver");
	private static final  String URL = PropertyLoadUtil.getProperty("hive.url");

	
	/**
	 * Get the hive the database connection
	 * 
	 * @return hive connection
	 */
	public static Connection getConnection() {

		try {
			if (connection != null && ! connection.isClosed())
				return connection;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return getConnect();
	}

	/**
	 * Get the connection object
	 * 
	 * @return Connection object
	 */
	private static Connection getConnect() {
		try {
			
			
		/*	
			org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();	
			conf.set("hadoop.security.authentication", "Kerberos");	
			UserGroupInformation.setConfiguration(conf);	
			// sit	
			 UserGroupInformation.loginUserFromKeytab("ownedasg",	"C:\\Users\\vencp9\\Documents\\Edag portal\\ownedasg.keytab");	
				*/
				
			// uat	
		//	UserGroupInformation.loginUserFromKeytab("uncuedasg","C:\\Users\\vencp9\\Documents\\Edag portal\\uncuedasg.keytab");	
		
			//Class.forName(DRIVER);	
			// sit	
		//	connection =DriverManager.getConnection("jdbc:hive2://apledatsg98.sg.uobnet.com:10000/;principal=hive/apledatsg98.sg.uobnet.com@TST.UOBNET.COM");	
				
				//
			// UAT	
			// jdbc:hive2://apledausg02.sg.uobnet.com:10000/default;principal=hive/_HOST@SG.UOBNET.COM	
				
				
			System.out.println("hive connection successful");	

			
			
	        Class.forName(DRIVER);
		    connection = DriverManager.getConnection(URL);
			System.out.println("hive connection successful");
			

		} catch (SQLException exception) {
			exception.printStackTrace();
			while (exception != null) { // grab the exception caught to tell us
										// the problem.
				System.err.println("SQLState:   " + exception.getSQLState());
				System.err.println("Message:    " + exception.getMessage());
				System.err.println("Error code: " + exception.getErrorCode());
				exception = exception.getNextException();

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public ResultSet execute(String query) throws Exception {
		ResultSet rs = null;
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			rs = statement.executeQuery(query);
		} catch (Exception e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		return rs;
	}

	public static void closeConnection() {
		try {
			if (null != connection  && ! connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}
