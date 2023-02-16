package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSteps {
	
	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try{
			//Step1. Load and register the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded succesfully....");
		
			//Step2. Establish the connection with database
			String url = "jdbc:mysql://localhost:3306/mydb";
			//username and password would vary from user to user
			String userName = "root";
			String passWord = "";
			connection = DriverManager.getConnection(url,userName,passWord);
			System.out.println("connection established succesfully...");
			System.out.println("The implement class name is "+connection.getClass().getName());
		
			//Step3. Create statement Object and send the query
			String sqlSelectQuery = "select id,fname,lname,city from employee";
			statement = connection.createStatement();
			System.out.println("The implementation class name is ::"+statement.getClass().getName());
			resultSet =statement.executeQuery(sqlSelectQuery);
			System.out.println("The implementation class name is ::"+resultSet.getClass().getName());
			System.out.println();
			
			System.out.println("EID\tFNAME\tLNAME\tECITY");
		
			//Step4. Process the resultSet
			while (resultSet.next())
			{
				Integer eid = resultSet.getInt(1);
				String fname = resultSet.getString(2);
				String lname = resultSet.getString(3);
				String ecity = resultSet.getString(4);
				System.out.println(eid+"\t"+fname+"\t"+lname+"\t"+ecity);
			}
		}
		
		//Step5. Handle Exception
		catch (ClassNotFoundException ce){
			ce.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//Step6. Close the connection
		finally{
			//closing the resources
			if (connection!=null){
				try{
					connection.close();
					System.out.println("Connection closed...");
				}
				catch (SQLException se){
					se.printStackTrace();
				}
			}
		}
	}
}
