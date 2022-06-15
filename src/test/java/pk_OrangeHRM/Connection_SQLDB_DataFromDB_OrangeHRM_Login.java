package pk_OrangeHRM;

import java.sql.SQLException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Connection_SQLDB_DataFromDB_OrangeHRM_Login extends BaseClass {
	ChromeDriver driver;
	 
    @BeforeTest
    public void LaunchApp() throws Exception {
 
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
        // driver.manage().window().maximize();
    }
    
    @Test
    public void ConnectSQLDB_With_LoginApp() throws ClassNotFoundException, SQLException, InterruptedException,
            InstantiationException, IllegalAccessException {
 
        ArrayList<String> sqlData = ConnectMySQLDatabase("jdbc:mysql://localhost:3306/orangehrm", "root", "root");
        System.out.println("SQL Data from DB Example Class : " + sqlData + "\n Array List Size : " + sqlData.size());
 
        for (String sqlValues : sqlData) {
            System.out.println(sqlValues + " split : 0\t" + sqlValues.split("~")[0]);
            System.out.println(sqlValues + " split : 1\t" + sqlValues.split("~")[1]);
            String uname=sqlValues.split("~")[0];
            String upass=sqlValues.split("~")[1];
            //String expresult=sqlValues.split("~")[1];
            driver.findElement(By.id("txtUsername")).clear();
            driver.findElement(By.id("txtUsername")).sendKeys(uname);
            driver.findElement(By.id("txtPassword")).clear();
            driver.findElement(By.id("txtPassword")).sendKeys(upass);
            driver.findElement(By.id("btnLogin")).click();
            Thread.sleep(4000);
            /*driver.findElementById("welcome").click();
            Thread.sleep(3000);
            driver.findElementByLinkText("Logout").click();
            Thread.sleep(3000);*/
        }
 
    }
    @AfterTest
    public void CloseApp() throws Exception {
 
        driver.quit();
    }
}
