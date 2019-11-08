// NOTE: The API is of Oracle, but trying to connect to Impala. 
// Need to find Impala API for conn pool creation.

package com.uob.edag.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;


import com.uob.edag.util.PropertyLoadUtil;


public class ImpalaConnectionAccess {


	private  final static String DRIVER = PropertyLoadUtil.getProperty("impala.driver");
//	private static final  String DRIVER = Util.getProperties("impala.driver");
	//private static final String URL = Util.getProperties("impala.url");
	private static final String URL = PropertyLoadUtil.getProperty("impala.url");
    
	//private static final String DRIVER = "com.cloudera.impala.jdbc4.Driver";
	//private static String URL = "jdbc:impala://apledausg08.sg.uobnet.com:21050;AuthMech=1;KrbHostFQDN=apledausg08.sg.uobnet.com;KrbRealm=SG.UOBNET.COM;KrbServiceName=impala";

	public static Logger logger = null;

	private static Connection connection = null;

	/**
	 * Get the impala the database connection
	 * 
	 * @return impala connection
	 */
	public static Connection getConnection() {

		try {
			if (connection != null && !connection.isClosed())
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
				// uat
			//UserGroupInformation.loginUserFromKeytab("uncuedasg",
			//	"C:\\Users\\vencp9\\Documents\\Edag portal\\uncuedasg.keytab");
			*/	
			Class.forName(DRIVER);
			// sit
			//jdbc:impala://apledausg08.sg.uobnet.com:21050;AuthMech=1;KrbHostFQDN=apledausg08.sg.uobnet.com;KrbRealm=SG.UOBNET.COM;KrbServiceName=impala
	     	//connection = DriverManager.getConnection("jdbc:impala://apledatsg97.sg.uobnet.com:21050;AuthMech=1;KrbHostFQDN=apledatsg97.sg.uobnet.com;KrbRealm=TST.UOBNET.COM;KrbServiceName=impala");
		     connection = DriverManager.getConnection(URL);
	     	//jdbc:impala://apledausg08.sg.uobnet.com:21050;AuthMech=1;KrbHostFQDN=apledausg08.sg.uobnet.com;KrbRealm=SG.UOBNET.COM;KrbServiceName=impala
			// UAT
			// jdbc:hive2://apledausg02.sg.uobnet.com:10000/default;principal=hive/_HOST@SG.UOBNET.COM

			System.out.println("impala connection successful");

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

			System.err.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		return rs;
	}
	public void release() {

		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
