package com.cafelite.configuration;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfiguration {

	private static String url = "jdbc:mysql://127.0.0.1:3306/food_odering_system";
	private static String userName = "root";
	private static String password = "neth123$";
	private static Connection connection;

	  //Creating universal method to open connect will mysql database
	public static Connection getConnection() {
		try {
			 //Registering with mysql Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database connection is not success!!!");
		}
		return connection;
	}
}
