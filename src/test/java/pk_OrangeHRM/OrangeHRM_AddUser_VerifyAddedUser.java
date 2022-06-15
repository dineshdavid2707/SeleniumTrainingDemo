package pk_OrangeHRM;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_AddUser_VerifyAddedUser {
	ChromeDriver driver;
	
	@BeforeTest
	public void LauchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
  @Test
  public void AddUser_VerifyUser() throws InterruptedException {
	  driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	  driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	  driver.findElement(By.name("txtPassword")).sendKeys("admin123");
	  driver.findElement(By.id("btnLogin")).click();
	  String Element = driver.findElement(By.linkText("Dashboard")).getText();
	  System.out.println(Element);
	  
	  WebElement admin =driver.findElement(By.id("menu_admin_viewAdminModule"));
	  Actions action = new Actions(driver);
	  action.moveToElement(admin).build().perform();
	  Thread.sleep(2000);
	  WebElement usermanagement = driver.findElement(By.linkText("User Management"));
	  action.moveToElement(usermanagement).build().perform();
	  Thread.sleep(2000);
	  driver.findElement(By.linkText("Users")).click();
	  
	 // driver.findElement(By.linkText("Admin")).click();
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("btnAdd")).isDisplayed();
	  //Click on Add User Button
	  driver.findElement(By.id("btnAdd")).click();
	  
	 
	  
	 Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
	//SelectPass.selectByValue("1");
	//SelectPass.selectByIndex(0);
	SelectPass.selectByVisibleText("Admin");
	driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");
	
	Random randomGenerator = new Random();
	int randomInt = randomGenerator.nextInt(1000);
	driver.findElement(By.id("systemUser_userName")).sendKeys("Dinesh" + randomInt);
	//String ExpUserName=driver.findElementById("systemUser_userName").getText();
	driver.findElement(By.id("systemUser_password")).sendKeys("admin123");
	driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("admin123");
	Thread.sleep(2000);
	driver.findElement(By.id("btnSave")).click();
	
	//Verify the Added User in WebTable

	Thread.sleep(2000);
	String ExpUserName = "Dinesh" + randomInt;
	System.out.println("Expected User Data ::"+ExpUserName);
	// WebElement cellIneed =
	// driver.findElementByXPath("//*[@id='resultTable']/tbody/tr[1]/td[2]/a");
	//WebElement cellIneed = driver.findElementByXPath("//a[text()='abhidixit" + randomInt + "')]");
	//driver.navigate().refresh();
	
	
	WebElement Actusername = driver.findElement(By.xpath("//a[text()='" + ExpUserName + "']"));
	// a[contains(text(),'abhidixit699')]
	// ---//a[text()='"+ExpUserName+"']
	String valueIneed = Actusername.getText();
	System.out.println("Cell value is : " + valueIneed);
	Assert.assertEquals(ExpUserName, valueIneed);
	driver.close();
  }
  
    @AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
  
}
