package pk_OrangeHRM;

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

public class OrangeHRM_DataProvider_AddUser {

ChromeDriver driver;
	
	@BeforeTest
	public void LauchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	 @AfterTest
		public void CloseBrowser() {
			driver.quit();
		}
	 
	 @Test(priority = 1)
	 public void userLogin() {
		 driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		  driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		  driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		  driver.findElement(By.id("btnLogin")).click();
		  String Element = driver.findElement(By.linkText("Dashboard")).getText();
		  System.out.println(Element);
		  
	 }
	 
	 @Test(priority = 2)
	 public void navigateToAddUser() throws InterruptedException {
		 WebElement admin =driver.findElement(By.id("menu_admin_viewAdminModule"));
		  Actions action = new Actions(driver);
		  action.moveToElement(admin).build().perform();
		  Thread.sleep(2000);
		  WebElement usermanagement = driver.findElement(By.linkText("User Management"));
		  action.moveToElement(usermanagement).build().perform();
		  Thread.sleep(2000);
		  driver.findElement(By.linkText("Users")).click();
		  driver.findElement(By.id("btnAdd")).click();
			/*
			 * Select SelectPass = new
			 * Select(driver.findElement(By.id("systemUser_userType")));
			 * SelectPass.selectByVisibleText("Admin");
			 */
	 }

	 @Test(priority = 3 , dataProvider = "AddUser", dataProviderClass = OrangeHRM_TestData.class)
	 public void addUserScenario(String empName , String UName , String Pswd , String CPswd , String expMSG) throws InterruptedException {
		// driver.findElement(By.id("systemUser_userType")).clear();
		 Select SelectPass = new Select(driver.findElement(By.id("systemUser_userType")));
		 SelectPass.selectByVisibleText("Admin");
		 driver.findElement(By.id("systemUser_employeeName_empName")).clear();
		 driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys(empName);
		 driver.findElement(By.id("systemUser_userName")).clear();
		 driver.findElement(By.id("systemUser_userName")).sendKeys(UName);
		 driver.findElement(By.id("systemUser_password")).clear();
		 driver.findElement(By.id("systemUser_password")).sendKeys(Pswd);
		 driver.findElement(By.id("systemUser_confirmPassword")).clear();
		 driver.findElement(By.id("systemUser_confirmPassword")).sendKeys(CPswd);
		 driver.findElement(By.id("btnSave")).click();
		 Thread.sleep(2000);
		 
		 if(expMSG.equalsIgnoreCase("Username")) {
			 String ActElement = driver.findElement(By.xpath("//a[text()='Username']")).getText();
		     String ExpElement = expMSG;
			 Assert.assertEquals(ActElement, ExpElement);
		 }
		 else if (expMSG.equalsIgnoreCase("Employee does not exist")){
			 String ActElement = driver.findElement(By.xpath("//span[@for='systemUser_employeeName_empName']")).getText();
		     String ExpElement = expMSG;
			 Assert.assertEquals(ActElement, ExpElement);
		 }
		 else if (expMSG.equalsIgnoreCase("Required")) {
			 String ActElement = driver.findElement(By.xpath("//span[@for='systemUser_userName']")).getText();
		     String ExpElement = expMSG;
			 Assert.assertEquals(ActElement, ExpElement);
		 }
		 else if (expMSG.equalsIgnoreCase("Required Password")) {
			 String ActElement = driver.findElement(By.xpath("//span[@for='systemUser_password']")).getText();
		     String ExpElement = "Required";
			 Assert.assertEquals(ActElement, ExpElement);
		 }
		 else if (expMSG.equalsIgnoreCase("Passwords Empty")) {
			 String ActElement = driver.findElement(By.xpath("//span[@for='systemUser_confirmPassword']")).getText();
		     String ExpElement = "Passwords do not match";
			 Assert.assertEquals(ActElement, ExpElement);
		 }
		 else if (expMSG.equalsIgnoreCase("Different Passwords")) {
			 String ActElement = driver.findElement(By.xpath("//span[@for='systemUser_confirmPassword']")).getText();
		     String ExpElement = "Passwords do not match";
			 Assert.assertEquals(ActElement, ExpElement);
		 }
	 }
}
