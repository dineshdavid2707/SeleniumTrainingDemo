package pk_OrangeHRM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class ConnectSQLDB {
	@Test
	public void  ConnectSQLDB() throws  ClassNotFoundException, SQLException {                                                   
	//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"       
	String dbUrl = "jdbc:mysql://localhost:3306/orangehrm";                   
	//Database Username       
	String username = "root";   
	//Database Password       
	String password = "root";               
	//Query to Execute       
	String query = "select * from login;";   
	//Step1 : Load mysql jdbc driver       
	Class.forName("com.mysql.jdbc.Driver");           
	//Step2: Create Connection to DB       
	Connection con = DriverManager.getConnection(dbUrl,username,password);
	//Step3 : Create Statement Object       
	Statement stmt = con.createStatement();                   
	//Step4 :  Execute the SQL Query. Store results in ResultSet       
	ResultSet rs= stmt.executeQuery(query);                           
	// While Loop to iterate through all data and print results       
	while (rs.next()){
	String Username = rs.getString("uname");                                       
	String Password = rs.getString(2);                                                  
	System.out.println(Username+"  "+Password);
	}       
	//Step5 : closing DB Connection       
	con.close();           
	}
}
