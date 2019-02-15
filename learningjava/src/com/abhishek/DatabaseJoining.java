package com.abhishek;

import java.sql.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class DatabaseJoining {
	private final static Logger logger = Logger.getLogger(DatabaseJoining.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.debug("Driver class registered");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			logger.debug("Connection with driver established");
			Statement stmt=con.createStatement();
			logger.debug("Statement creation completed");
			ResultSet rs =stmt.executeQuery("select * from employee");
			logger.debug("Execution of queries took place");
			if(rs!=null)
			{
				while(rs.next()) {
					logger.debug("loop is running");
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
				}
			}
			con.close();
			logger.debug("Connection was closed successfully");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
