package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrder_Login_Logout_Priority_TestNG {
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
	
  @Test(priority = 0)
  public void WeborderLoginVerification() {
	     
	        //Enter User Login Details
			driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
			driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
			//Click Login Btn
			driver.findElement(By.id("ctl00_MainContent_login_button")).click();
			//Exp Result : Verify User landed in DashBoard
			boolean landingPage=driver.findElement(By.linkText("Order")).isDisplayed();
			if(landingPage == true ) {
				System.out.println("Login Successfully");
			}
			else {
				System.out.println("Login Failed");
			}
			
  }
  
  @Test(priority = 1)
  public void Logout() {
	
		//Click Logout
		driver.findElement(By.linkText("Logout")).click();
		boolean logout = driver.findElement(By.id("ctl00_MainContent_login_button")).isDisplayed();
		if(logout == true) {
			System.out.println("Logout Successfully");
		}
		else {
			System.out.println("Logout Failed");
		}
  }
  
  @AfterTest
  public void CloseBrowser() {

		//Close Browser
		driver.quit();
	}
}
