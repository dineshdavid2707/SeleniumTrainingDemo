package pk_OrangeHRM;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Spree_AccCreate_AddressUpdate {
	WebDriver driver;
	@Test
    public void Login_ValidCredential() throws InterruptedException {
        // Launch the Browser
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Enter the URL
        driver.get("https://demo.spreecommerce.org/");
        //Click on Icon
        driver.findElement(By.xpath("//button[@id='account-button']//*[name()='svg']")).click();

        //click on login
        driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='spree_user_email']")).sendKeys("selenium@spree.com");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='spree_user_password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@name='commit']")).click();
        Thread.sleep(3000);
        driver.findElement(By.linkText("Add new address")).click();
        Thread.sleep(2000);


        //driver.findElement(By.xpath("//input[@id='address_label']")).sendKeys("HOME14");
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        driver.findElement(By.xpath("//input[@id='address_label']")).sendKeys("Home" + randomInt);



        driver.findElement(By.xpath("//input[@id='address_firstname']")).sendKeys("Lavanya");
        driver.findElement(By.xpath("//input[@id='address_lastname']")).sendKeys("p");
        driver.findElement(By.xpath("//input[@id='address_address1']")).sendKeys("793 Bell Street, New York, NY 10018, USA");
        driver.findElement(By.xpath("//input[@id='address_address2']")).sendKeys("new york");
        driver.findElement(By.xpath("//input[@id='address_city']")).sendKeys("bell street");
        Select  select1=new Select(driver.findElement(By.id("address_state_id")));
        Thread.sleep(3000);
        select1.selectByVisibleText("New York");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@id='address_zipcode']")).sendKeys("10001");

        Select  select12=new Select(driver.findElement(By.id("address_country_id")));
        select12.selectByVisibleText("United States");
        driver.findElement(By.xpath("//input[@id='address_phone']")).sendKeys("1234567890");
        driver.findElement(By.xpath("//input[@name='commit']")).click();

        //verify address
        String ExpHomeName = "Home" + randomInt;
        System.out.println(ExpHomeName);
        Thread.sleep(2000);
        WebElement objHm =driver.findElement(By.xpath("//h4[text()='" + ExpHomeName + "']"));
        String valueIneed = objHm.getText();
        System.out.println("Cell value is : " + valueIneed);
        Assert.assertEquals(ExpHomeName, valueIneed);
        Thread.sleep(2000);

        //Delete User from Search List
 
        //driver.findElementByXPath("//a[text()='"+ExpUserName+"']/parent::td/preceding-sibling::td/input").click();
        driver.findElement(By.xpath("//h4[text()='" + ExpHomeName + "']//..//..//../div[@class='col']/a[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@id='delete-address-popup-confirm']")).click();
        //driver.findElement(By.xpath("//a[@data-hook='remove_address']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Address has been successfully removed.']")).isDisplayed();

        System.out.println("Test Success");
        driver.quit();
}
		
}
