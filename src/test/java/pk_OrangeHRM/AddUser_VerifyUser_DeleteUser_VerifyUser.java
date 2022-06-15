package pk_OrangeHRM;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddUser_VerifyUser_DeleteUser_VerifyUser {
	ChromeDriver driver;
	
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
  @Test(priority = 1)
  public void Sign_On() throws InterruptedException{
	  driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	  driver.findElement(By.name("txtPassword")).sendKeys("admin123");
	  driver.findElement(By.id("btnLogin")).click();
	  String Element = driver.findElement(By.linkText("Dashboard")).getText();
	  System.out.println(Element);
  }
  
  @Test(priority = 2)
  public void AddUsers_Page() {
	  WebElement admin = driver.findElement(By.id("menu_admin_viewAdminModule"));
	  Actions action = new Actions(driver);
	  action.moveToElement(admin).build().perform();
	  WebElement usermanagement = driver.findElement(By.linkText("User Management"));
	  action.moveToElement(usermanagement).build().perform();
	  driver.findElement(By.linkText("Users")).click();
	  //User Click on Add Button to add users
	  driver.findElement(By.id("searchBtn")).click();
	  driver.findElement(By.id("btnAdd")).isDisplayed();
  }
  
  @Test(priority = 3)
  public void VerifyDeletedUser() throws InterruptedException{
	  driver.findElement(By.id("btnAdd")).click();
	  Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
	  //SelectPass.selectByValue("1");
	  //SelectPass.selectByIndex(0);
	  SelectPass.selectByVisibleText("Admin");
	  
	 driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");
	 Random randomGenerator = new Random();
	 int randomInt = randomGenerator.nextInt(1000);
	 driver.findElement(By.id("systemUser_userName")).sendKeys("abhi" + randomInt);
	 //String ExpUserName=driver.findElementById("systemUser_userName").getText();
	 driver.findElement(By.id("systemUser_password")).sendKeys("admin123");
	 driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("admin123");
	 Thread.sleep(5000);
	 driver.findElement(By.id("btnSave")).click();
	 //Verify the Added User in WebTable
	  Thread.sleep(2000);
	  String ExpUserName = "abhi" + randomInt;
	  driver.navigate().refresh();
	  Thread.sleep(2000);
	  WebElement cellIneed = driver.findElement(By.xpath("//*[text()='"+ ExpUserName +"']"));
	  //WebElement cellIneed = driver.findElementByXPath("//*[text()='"+ ExpUserName +"']");
	  String ActualUserName = cellIneed.getText();
	  System.out.println("Cell value is : " + ActualUserName);
	  Assert.assertEquals(ExpUserName, ActualUserName);
	  //Delete User from Search List       
	  driver.findElement(By.xpath("//a[text()='"+ExpUserName+"']/parent::td/preceding-sibling::td/input")).click();
	  //driver.findElementByXPath("//a[text()='"+ExpUserName+"']/parent::td/preceding-sibling::td/input").click();
	  //driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td/input").click();
	  //driver.findElementByXPath("//td/a[text()='"+ExpUserName+"']//parent::td/../td[text()='Admin']").isDisplayed();
	  //driver.findElementByXPath("//a[text()='"+ExpUserName+"']/parent::td/../td[text()='Admin']/../td/input").click();;
	  Thread.sleep(2000);
	  driver.findElement(By.id("btnDelete")).click();
	  driver.findElement(By.id("dialogDeleteBtn")).click();
	  //driver.findElementById("btnDelete").click();
	  //driver.findElementById("dialogDeleteBtn").click();
	  Thread.sleep(5000);
	  //Verify that user got deleted from the WebTable
	  Boolean deleteduser = driver.getPageSource().contains(ExpUserName);
	  Assert.assertFalse(deleteduser);
  }
}
