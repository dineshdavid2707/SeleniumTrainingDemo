package pk_OrangeHRM;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobilDimension {
	ChromeDriver driver;
  @Test
  public void signOn() {
	  driver.get("https://www.borrowlenses.com/");
  }
  @BeforeTest
  public void LaunchBrowser() {
        WebDriverManager.chromedriver().setup();
        //ChromeOptions iPhoneOption = new ChromeOptions();
        driver = new ChromeDriver();
        Dimension d = new Dimension(390, 844);
        //driver.manage().window().maximize();
        driver.manage().window().setSize(d);
 
  }
  @AfterTest
  public void CloseBrowser() {
    //    driver.quit();
  }
}
