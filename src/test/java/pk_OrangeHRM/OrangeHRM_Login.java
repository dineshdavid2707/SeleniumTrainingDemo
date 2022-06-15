package pk_OrangeHRM;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHRM_Login {

	@Test
	public void Login_Verification() {
		//Launch Chrome
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		//Enter URL
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		//Enter User Login Details
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		//Click Login Btn
		driver.findElement(By.id("btnLogin")).click();
		//Exp Result : Verify User landed in DashBoard
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
		//Close Browser
		driver.quit();
	}

}
