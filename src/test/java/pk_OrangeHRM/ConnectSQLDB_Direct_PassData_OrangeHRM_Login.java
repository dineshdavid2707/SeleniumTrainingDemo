package pk_OrangeHRM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConnectSQLDB_Direct_PassData_OrangeHRM_Login {
	ChromeDriver driver;
	@BeforeMethod
	public void LaunchApp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Test
	public void ConnectMySQLDB() throws ClassNotFoundException, SQLException, InterruptedException {
		String dbUrl = "jdbc:mysql://localhost:3306/orangehrm";
		String username = "root";
		String password = "root";
		String query = "select * from login;";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbUrl,username,password);
		Statement stmt = con.createStatement();
		ResultSet rs= stmt.executeQuery(query);
		while (rs.next()){
			
			String Username = rs.getString(1);
			String Password = rs.getString(2);
			System. out.println(Username+"  "+Password);
			//Pass Data to Login OrnageHRM
			//driver.findElementById("txtUsername").clear();
            driver.findElement(By.id("txtUsername")).sendKeys(Username);
          //driver.findElementById("txtPassword").clear();
            driver.findElement(By.id("txtPassword")).sendKeys(Username);
            driver.findElement(By.id("btnLogin")).click();
            /*Thread.sleep(3000);
            driver.findElementById("welcome").click();
            Thread.sleep(3000);
            driver.findElementByLinkText("Logout").click();
            Thread.sleep(3000);*/
		}
		// closing DB Connection
		con.close();
	}
	@AfterTest
	public void CloseApp() {
		driver.quit();
	}
}
