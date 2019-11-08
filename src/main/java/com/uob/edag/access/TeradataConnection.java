package com.uob.edag.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.uob.edag.util.EncryptionUtil;
import com.uob.edag.util.PropertyLoadUtil;

public class TeradataConnection {

	private static final String DRIVER = PropertyLoadUtil.getProperty("teradata.driver");
	private static final  String URL = PropertyLoadUtil.getProperty("teradata.url");
	private static final  String USERNAME = PropertyLoadUtil.getProperty("teradata.username");
	private static final String PASSWORD = PropertyLoadUtil.getProperty("teradata.password");
	
	Connection connection = null;

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Teradata JDBC Driver?");
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException, Exception {
		try {
			EncryptionUtil u = new EncryptionUtil();
			String password = u.decrypt(PASSWORD);
			connection = DriverManager.getConnection(URL, USERNAME, password);
			System.out.println("Teradata connection successfull !");
		} catch (Exception e) {

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
