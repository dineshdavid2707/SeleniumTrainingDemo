package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_DataProvider_UserLogin_AllScenario {
	WebDriver driver;

	// ChromeDriver driver;
	@BeforeTest
	public void LaunchBrowser() {
		WebDriverManager.chromedriver().setup();
		// WebDriverManager.firefoxdriver().setup();
		// WebDriverManager.edgedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterTest
	public void CloseBrowser() {
		// driver.close();//Close will close only current chrome browser
		driver.quit();
	}

	
	
	
	// @Test(dataProvider="Login")
	@Test(dataProvider = "LoginScenario", dataProviderClass = OrangeHRM_TestData.class)
	public void LoginToOrangeHRM(String Uname, String Upass, String expMSG) throws InterruptedException {

		driver.findElement(By.id("txtUsername")).clear();
		driver.findElement(By.id("txtUsername")).sendKeys(Uname);
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtPassword")).sendKeys(Upass);
		driver.findElement(By.id("btnLogin")).click();

		if (expMSG.equalsIgnoreCase("Dashboard")) {
			System.out.println("Inside Dashboard Check");
			String ActElement = driver.findElement(By.linkText("Dashboard")).getText();
			String ExpElement = "Dashboard";
			Assert.assertEquals(ActElement, ExpElement);
			System.out.println(ActElement);
			Thread.sleep(3000);
			driver.findElement(By.partialLinkText("Welcome")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Logout")).click();
			Boolean loginPage = driver.findElement(By.id("logInPanelHeading")).isDisplayed();
			if (loginPage == true) {
				System.out.println("User Logged Out & Landed in Login Page");
			} else {
				System.out.println("User Not able to LogOut");
			}

		} else {
			System.out.println("Verifying Invalid Scenario");
			String ActElement = driver.findElement(By.xpath("//span[@id='spanMessage']")).getText();
			// String ExpElement = "Password cannot be empty";
			Assert.assertEquals(ActElement, expMSG);
			System.out.println(ActElement);
		}
	}
}
