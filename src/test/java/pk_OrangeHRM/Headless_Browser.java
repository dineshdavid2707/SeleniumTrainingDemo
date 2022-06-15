package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Headless_Browser {
	WebDriver driver=null;
	 
    @Test
    public void Chrome_Headless() {
 
        //WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
        //FirefoxOptions options = new FirefoxOptions();
        ChromeOptions options = new ChromeOptions();

        options.setHeadless(false);
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        //driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
 
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        driver.findElement(By.name("txtUsername")).sendKeys("Admin");
        driver.findElement(By.name("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        driver.quit();
    }
}
