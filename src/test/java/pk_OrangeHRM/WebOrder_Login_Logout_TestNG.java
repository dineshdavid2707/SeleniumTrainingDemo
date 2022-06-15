package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Login_Logout_TestNG {
  @Test
  public void WeborderLoginVerification() {
	        //Launch Chrome
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			//Enter URL
			driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
			//Enter User Login Details
			driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
			driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
			//Click Login Btn
			driver.findElement(By.id("ctl00_MainContent_login_button")).click();
			//Exp Result : Verify User landed in DashBoard
			if(driver.findElement(By.linkText("Order")).isDisplayed()) {
				System.out.println("Login Successfully");
			}
			else {
				System.out.println("Login Failed");
			}
			
			//Click Logout
			driver.findElement(By.linkText("Logout")).click();
			
			if(driver.findElement(By.id("ctl00_MainContent_login_button")).isDisplayed()) {
				System.out.println("Logout Successfully");
			}
			else {
				System.out.println("Logout Failed");
			}
			//Close Browser
			driver.quit();
  }
}
