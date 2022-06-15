package pk_OrangeHRM;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebOrders_Add_Update_Delete_Order {
	WebDriver driver;
	int randomInt;
	
	@BeforeTest
	public void LaunchBrowser() {
	 WebDriverManager.chromedriver().setup();
	 driver= new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.get("http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx");
	
	}
	
	@Test(priority = 1)
	  public void Log_In() throws InterruptedException{
		  driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		  driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		  driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		 //String Element = driver.findElement(By.linkText("Order"));
		  //System.out.println(Element);
	  }
	
	@Test(priority = 2)
	public void Add_NewOrder() {
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(1000);
		driver.findElement(By.linkText("Order")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys("10");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys("ChennaiCustomerName"+ randomInt);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("StreetName");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("CityName");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("StateName");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys("123456");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys("5346897653469746");
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("09/22");
		driver.findElement(By.linkText("Process")).click();
		Boolean webOrder_Added=driver.findElement(By.xpath("//strong[contains(text(),'successfully added')]")).isDisplayed();
	    if(webOrder_Added == true) {
	    	System.out.println("WebOrder Added Successfully");
	    }
	    else {
	    	System.out.println("WebOrder Added Successfully");
	    }
	}
	
	@Test(priority = 3)
	public void verifyAdded_WebOrder() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("View all orders")).click();
		String ExpUserName = "ChennaiCustomerName" + randomInt;
		System.out.println("Expected User Data ::"+ExpUserName);
		WebElement Actusername = driver.findElement(By.xpath("//td[text()='" + ExpUserName + "']"));
		String valueIneed = Actusername.getText();
		System.out.println("Cell value is : " + valueIneed);
		Assert.assertEquals(ExpUserName, valueIneed);
	}
	
	@Test(priority = 4)
    public void ModifyAdded_WebOrder() throws InterruptedException {
		driver.findElement(By.xpath("//td[contains(text(),'09/22')]//following::input[@alt='Edit']")).click();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).clear();
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("ChennaiCity"+randomInt);
		driver.findElement(By.linkText("Update")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("View all orders")).click();
		String ExpUserName = "ChennaiCity" + randomInt;
		System.out.println("Expected User Data ::"+ExpUserName);
		WebElement Actusername = driver.findElement(By.xpath("//td[text()='" + ExpUserName + "']"));
		String valueIneed = Actusername.getText();
		System.out.println("Cell value is : " + valueIneed);
		Assert.assertEquals(ExpUserName, valueIneed);
	}

	/*
	 * @Test(priority = 5) public void DeleteAdded_WebOrder() { String ExpUserName =
	 * "ChennaiCustomerName" + randomInt;
	 * System.out.println("Expected User Data ::"+ExpUserName);
	 * 
	 * }
	 */
}
