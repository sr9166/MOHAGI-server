package com.mohagi.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    
	static Connection conn = null;
	static Statement st = null;
	
    public static Statement getStatement() {
    	if(st == null) {
    		// create our mysql database connection
    	    String myUrl = "jdbc:mysql://localhost/mohagi";
			try {
				conn = DriverManager.getConnection(myUrl, "root", "root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    	    // create the java statement
    	    try {
				st = conn.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return st;
    }
    
    public static Connection getConnection() {
    	return conn;
    }
}
