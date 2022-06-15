package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_AssignLeave_With_FollowingSibling_Example {
	WebDriver driver;
	  @Test
	  public void Login() throws InterruptedException {
	        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
	        driver.findElement(By.id("btnLogin")).click();
	        // Verify that Dashboard page displayed
	        String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
	        String ExpElement = "Dashboard";
	        Assert.assertEquals(ActElement, ExpElement);
	        System.out.println("Actual Element::"+ActElement);
	        // ----------------To Click on  Leave List Function----------------
	 
	        driver.findElement(By.xpath("//span[contains(text(),'Assign')]//following-sibling::span[text()='Leave List']")).click();
	        Thread.sleep(3000);
	        driver.findElement(By.xpath("//span[contains(text(),'Entitlements')]//preceding-sibling::span[text()='My Leave']")).click();
	  }
	  @BeforeTest
	  public void LaunchBrowser() {
	      WebDriverManager.chromedriver().setup();
	        // WebDriverManager.firefoxdriver().setup();
	        // WebDriverManager.edgedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	  }
	 
	  @AfterTest
	  public void CloseBrowser() {
	        // driver.close();//Close will close only current chrome browser
	        driver.quit();//Quit will close all open chrome browser
	  }
}
