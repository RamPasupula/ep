package com.uob.edag.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveConnection {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {

			Class.forName("org.apache.hive.jdbc.HiveDriver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your Hive JDBC Driver?");
			e.printStackTrace();
			throw e;
		}

		System.out.println("Hive JDBC Driver Registered!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:hive2://apledausg02.sg.uobnet.com:10000/default;principal=hive/_HOST@SG.UOBNET.COM");
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
}
