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

public class HDFC_NetBanking {
	WebDriver driver;
	@BeforeTest
	 public void UserLogin() {
		  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
	      driver.manage().window().maximize();
	  }
	
	@Test
	public void HDFCLogin() {
		driver.navigate().to("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("10000");
		driver.findElement(By.linkText("CONTINUE")).click();
		//driver.findElement(null)
	}
	
	/*
	 * @AfterTest public void CloseBrowser() { driver.quit(); }
	 */
	 
}
