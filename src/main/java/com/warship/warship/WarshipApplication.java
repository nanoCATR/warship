package com.warship.warship;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
public class WarshipApplication {


	public static void main(String[] args) {
		SpringApplication.run(WarshipApplication.class, args);
	}

	private static Connection con=null;
	public Connection getConnection(String user, String password, String path) throws SQLException {

		if (con==null || con.isClosed()) {
			Properties props = new Properties();

			props.setProperty("user",user);
			props.setProperty("password", password);
			props.setProperty("path", path);
			con = DriverManager.getConnection(props.getProperty("path"), props);
		}
		return con;
	}

}
