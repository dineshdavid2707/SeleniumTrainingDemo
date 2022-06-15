package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Yatra_UserLogin {
	WebDriver driver;
	@BeforeTest
	 public void UserLogin() {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
	      driver.manage().window().maximize();
	  }
	
		/*
		 * @Test public void MyAccount() throws InterruptedException {
		 * driver.get("https://www.yatra.com/"); Thread.sleep(2000); WebElement
		 * account=driver.findElement(By.xpath("//a[text()='My Account']")); Actions
		 * action = new Actions(driver);
		 * action.moveToElement(account).build().perform(); Thread.sleep(2000);
		 * driver.findElement(By.xpath("//a[@title='Login']")).click();
		 * 
		 * Boolean landingpage = driver.findElement(By.
		 * xpath("//p[text()='Please Login/Register using your Email/Mobile to continue']"
		 * )).isDisplayed(); if(landingpage == true) {
		 * System.out.println("Landed in Login Page"); }else {
		 * System.out.println("Not Landed in Login Page"); } }
		 */
  
	@Test
	public void HDFCLogin() {
		driver.navigate().to("https://netbanking.hdfcbank.com/netbanking/");
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("10000");
		//driver.findElement(By.name("fldLoginUserId")).sendKeys("10000");
	}
	
	/*
	 * @AfterTest public void CloseBrowser() { driver.quit(); }
	 */
	 
}
