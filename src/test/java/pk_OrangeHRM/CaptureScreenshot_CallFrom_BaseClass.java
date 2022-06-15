package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureScreenshot_CallFrom_BaseClass {
	ChromeDriver driver;

	@Test(priority = 1)
	public void OrangeHRM_Login() throws Exception {
		driver.findElement(By.name("txtUsername")).sendKeys("Admin");
		driver.findElement(By.name("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
	}

	@Test(priority = 2)
	public void OrangeHRM_Logout() throws Exception {
		driver.findElement(By.id("welcome")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("logout")).click();
	}

	@BeforeTest
	public void LaunchBrowser()

	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}

	@AfterMethod
	public void CaptureScreenShot(ITestResult result) throws Exception {
		if (ITestResult.FAILURE == result.getStatus()) {
			BaseClass.getScreenshotfailure(driver, result.getName());

		} else if (ITestResult.SUCCESS == result.getStatus()) {
			BaseClass.getScreenshotSuccess(driver, result.getName());
		}

	}

	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}
}
