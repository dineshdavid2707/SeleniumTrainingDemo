package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Title_URL_LandingPage_Verification {
	WebDriver driver;
	
	@Test(priority = 1)
    public void Login_001() {
 
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        // Verifying Current URL
        String ExpURL_Login = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
        String ActURL_Login = driver.getCurrentUrl();
        Assert.assertEquals(ActURL_Login, ExpURL_Login);
        // Verifying Title
        String ExpTitle_Login = "OrangeHRM";
        String ActTile_Login = driver.getTitle();
        Assert.assertEquals(ActTile_Login, ExpTitle_Login);
        //Verify the Dashboard page
        String Exp_Page_Value = "Dashboard";
        String Act_Page_Value = driver.findElement(By.xpath("//h1[normalize-space()='Dashboard']")).getText();
        Assert.assertEquals(Act_Page_Value, Exp_Page_Value);
    }
	
	@Test(priority = 2)
    public void Logout() throws InterruptedException {
 
        driver.findElement(By.id("welcome")).click();
        Thread.sleep(3000);
 
        driver.findElement(By.linkText("Logout")).click();
        String ActLogpanel = driver.findElement(By.id("logInPanelHeading")).getText();
        String ExpLogpanel = "LOGIN Panel";
        Assert.assertEquals(ActLogpanel, ExpLogpanel);
        System.out.println(ActLogpanel);
 
        // Verifying Current URL
        String ExpURL_Logout = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";
        String ActURL_Logout = driver.getCurrentUrl();
        Assert.assertEquals(ActURL_Logout, ExpURL_Logout);
 
    }
	
	// Pre-Condition
    @BeforeTest
    // @Test(priority=1)
    public void LaunchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // This will wait for Page to load
        // driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        // This will store or rememember the cookies or navigation in terms of
        // back and forward
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
    }
    
 // Post Condition
    @AfterTest
    // @Test(priority=3)
    public void CloseBrowser() {
        driver.quit();
    }
}
