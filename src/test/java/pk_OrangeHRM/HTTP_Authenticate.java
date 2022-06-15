package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTTP_Authenticate {
	ChromeDriver driver;
	@BeforeTest
    public void LaunchBrowser() {
          WebDriverManager.chromedriver().setup();
          driver = new ChromeDriver();
          driver.manage().window().maximize();
    }
	@Test
    public void HTTPAuth() throws InterruptedException {
        //driver.navigate().to("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        String confmsg=driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).getText();
        System.out.println(confmsg);
    }
	
	@AfterTest
    public void CloseBrowser() {
          driver.quit();
    }
}
