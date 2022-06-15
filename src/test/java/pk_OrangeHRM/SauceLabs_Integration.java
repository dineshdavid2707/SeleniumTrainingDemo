package pk_OrangeHRM;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class SauceLabs_Integration {
	WebDriver driver;
	//public static final String USERNAME = "abhikdixit";
	//public static final String ACCESS_KEY = "d246025c-7be6-497f-9206-2db3dd761350";
	public static final String URL = "https://dineshdavid:04931599-03cd-45d0-a2af-742a29b91af5@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	//public static final String URL = "https://oauth-020poornima-22055:f35868f7-6639-463f-83cc-882448b42ced@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
	//public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
    
	@Test(priority=1)
	public void SauceLab_Example() throws MalformedURLException, InterruptedException {
	// TODO Auto-generated method stub
	// Below Configuration for Win10/Chrome
	//MutableCapabilities sauceOptions = new MutableCapabilities();
	//DesiredCapabilities sauceOptions = new DesiredCapabilities();

	EdgeOptions browserOptions = new EdgeOptions();
	//ChromeOptions browserOptions = new ChromeOptions();
	//SafariOptions browserOptions = new SafariOptions();
	//FirefoxOptions browserOptions = new FirefoxOptions();
	//browserOptions.setExperimentalOption("w3c", true);
	//browserOptions.setCapability("platformName", "macOS 10.13");
	browserOptions.setCapability("platformName", "Windows 10");
	//browserOptions.setCapability("platformName", "Windows 7");
	browserOptions.setCapability("browserVersion", "latest");
	//browserOptions.setCapability("sauce:options", sauceOptions);

	driver = new RemoteWebDriver(new java.net.URL(URL), browserOptions);
	driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	driver.findElement(By.name("txtUsername")).sendKeys("Admin");
	driver.findElement(By.name("txtPassword")).sendKeys("admin123");
	driver.findElement(By.id("btnLogin")).click();
	Thread.sleep(8000);
	String Element = driver.findElement(By.linkText("Dashboard")).getText();
	System.out.println(Element);

	//driver.quit();



	//Below Configuration for Mac/Chrome Latest Version
	/*MutableCapabilities sauceOptions = new MutableCapabilities();
	//ChromeOptions browserOptions = new ChromeOptions();
	FirefoxOptions browserOptions = new FirefoxOptions();
	browserOptions.setCapability("platformName", "Windows 10");
	browserOptions.setCapability("browserVersion", "latest");
	browserOptions.setCapability("sauce:options", sauceOptions);*/
	}
	
	@Test(priority=2,description="Logout from OrangeHRM")
	public void Logout() throws InterruptedException {
	driver.findElement(By.xpath("//a[contains(text(),'Welcome')]")).click();
	Thread.sleep(3000);
	driver.findElement(By.linkText("Logout")).click();
	driver.findElement(By.id("logInPanelHeading")).isDisplayed();
	}
	
	 @AfterMethod
	public void afterTest(ITestResult result)
	{
	((JavascriptExecutor) driver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));
	}
	
	@AfterTest()
	public void CloseBrowser()
	{
	driver.quit();
	}
}
