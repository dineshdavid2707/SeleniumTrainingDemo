package pk_OrangeHRM;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptureScreenshot_Example {
	ChromeDriver driver;
    // Absolute Path, rather use the relative path
    // String filePath_failure = "D:\\F Drive\\Selenium Training
    // Data\\workspace\\Maven_Selenium_WebDriver_4\\Screenshot_Failure";
	//String filePath = System.getProperty("user.dir");
    String filePath = "E:\\Selenium Training\\Workspace\\SeleniumTrainingDemo";
 
    String filepath_failure = filePath + "\\Screenshot_Failure";
    String filePath_Success = filePath + "\\Screenshot_Success";
    
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
        driver.findElement(By.linkText("Logoutt")).click();
    }
    
    @BeforeTest
    public void LaunchBrowser()
 
    {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //WebDriverManager.edgedriver().setup();

        //EdgeOptions options = new EdgeOptions();
        //FirefoxOptions options = new FirefoxOptions();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        //options.setHeadless(true);
        // options.setHeadless(true);
        //options.addArguments("incognito");
        //driver = new EdgeDriver(options);
        //driver = new FirefoxDriver(options);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }
    
    @AfterMethod
    public void CaptureScreenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // FileUtils.copyFile(Browserscreenshot, new
            // File(Relativepath_failure+"\\Login.png"));
            FileUtils.copyFile(Browserscreenshot,
                    new File(filepath_failure + "\\" + result.getName() + "_" + System.currentTimeMillis() + ".png"));
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            File Browserscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // FileUtils.copyFile(Browserscreenshot, new
            // File(filePath_Success+"\\Login.png"));
            FileUtils.copyFile(Browserscreenshot,
                    new File(filePath_Success + "\\" + result.getName() + "_" + System.nanoTime() + ".png"));
        }
    }
}
