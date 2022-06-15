package pk_OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoSpree_Navigation {
	ChromeDriver driver;
	//Pre-Condition
    @BeforeTest
    public void LaunchBrowser(){
         WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            //This will wait for Page to load
            //driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
          //This will store or remember the cookies or navigation in terms of back and forward
            driver.navigate().to("https://demo.spreecommerce.org/");
    }
    //Post Condition
    @AfterTest
    public void CloseBrowser(){
        //driver.quit();
    }
    
    @Test(priority = 1)
    public void Navigate_WomenSection() {
    	driver.findElement(By.xpath("//a[@href='/t/categories/women'][1]")).click();
    	String ExpURL="https://demo.spreecommerce.org/t/categories/women";
    	String ActURL=driver.getCurrentUrl();
    	Assert.assertEquals(ExpURL, ActURL);
    	//Verify Title
    	String ExpTitle="Women - Spree Demo Site";
    	String ActTitle=driver.getTitle();
    	Assert.assertEquals(ExpTitle, ActTitle);
    	//Verify Text
    	Boolean Women_Visible = driver.findElement(By.xpath("//span[text()='Women']")).isDisplayed();
        if(Women_Visible == true) {
        	System.out.println("Landed in Women Page");
        }else {
        	System.out.println("Not Landed in Women Page");
        }
    }
    @Test(priority = 2)
    public void Navigate_Forward() throws InterruptedException {
    	//Navigate back to Spree Page
        driver.navigate().back();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[contains(text(),'Summer Collection')]")).isDisplayed();
        //Navigate Forward
        driver.navigate().forward();
        String ExpURLForward="https://demo.spreecommerce.org/t/categories/women";
        String ActURLForward=driver.getCurrentUrl();
        Assert.assertEquals(ExpURLForward, ActURLForward);
        
    }
    @Test(priority = 3)
    public void Navigate_Refresh() {
    	 driver.navigate().refresh();
         String ExpURLForward1="https://demo.spreecommerce.org/t/categories/women";
         String ActURLForward1=driver.getCurrentUrl();
         Assert.assertEquals(ExpURLForward1, ActURLForward1);
    }
}
