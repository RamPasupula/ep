package com.uob.edag.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.uob.edag.util.EncryptionUtil;
import com.uob.edag.util.PropertyLoadUtil;
import com.uob.edag.util.Util;

public class OracleConnection {
	private static final String DRIVER = PropertyLoadUtil.getProperty("oracle.driver");
	private static final String URL = PropertyLoadUtil.getProperty("oracle.url");
	private static final String USERNAME = PropertyLoadUtil.getProperty("oracle.username");
	private static final String PASSWORD = PropertyLoadUtil.getProperty("oracle.password");
	Connection connection = null;
	
	static {
		try {
			System.out.println("Oracle JDBC Driver Registered!");
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException, Exception {
		try {
			EncryptionUtil u = new EncryptionUtil();
    		String password = u.decrypt(PASSWORD);
			connection = DriverManager.getConnection(URL, USERNAME, password);
		} catch (Exception e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		return connection;
	}

	public Connection getConnection(String connectionString, String uid, String pwd) throws ClassNotFoundException, SQLException {
		try {
			connection = DriverManager.getConnection(connectionString, uid, pwd);
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
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
	
	public ResultSet execute(String query, String connectionString, String uid, String pwd) throws Exception {
		ResultSet rs = null;
		try {
			Connection con = getConnection(connectionString, uid, pwd);
			Statement statement = con.createStatement();
			rs = statement.executeQuery(query);
		} catch (Exception e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			throw e;
		}
		return rs;
	}
	
	public void close() throws Exception {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
