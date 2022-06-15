package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Login_Logout_Pre_Post_TestNG {
	ChromeDriver driver;
	
	@BeforeTest
	public void LaunchBrowser() {
		//Launch Chrome
		 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 //Enter URL
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	}
	
  @Test
  public void WeborderLoginVerification() {
	     
	        //Enter User Login Details
			//driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
			//driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
	        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_username']")).sendKeys("Tester");
		    driver.findElement(By.xpath("//input[@id='ctl00_MainContent_password']")).sendKeys("test");
			
		    //Click Login Btn
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			//driver.findElement(By.id("ctl00_MainContent_login_button")).click();
			
			//Exp Result : Verify User landed in DashBoard
			if(driver.findElement(By.linkText("Order")).isDisplayed()) {
				System.out.println("Login Successfully");
			}
			else {
				System.out.println("Login Failed");
			}
			//Click Logout
			driver.findElement(By.linkText("Logout")).click();
			
  }
  
  @AfterTest
  public void CloseBrowser() {

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
