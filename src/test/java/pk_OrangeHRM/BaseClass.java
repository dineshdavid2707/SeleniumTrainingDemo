package pk_OrangeHRM;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
	//static ChromeDriver driver;
	// Read DB Function
	// Connection objectmy
	static Connection con = null;
	// Statement object
	private static Statement stmt;

	// static String filePath = System.getProperty("user.dir");
	static String filePath = "E:\\Selenium Training\\Workspace\\SeleniumTrainingDemo";
	static String Relativepath_failure = filePath + "\\Screenshot_Failure";
	static String Relativepath_success = filePath + "\\Screenshot_Success";
	
	//Extent Report
	//builds a new report using the html template 
	static ExtentSparkReporter htmlReporter;
	static ExtentReports extent;
    //helps to generate the logs in test report.
	static ExtentTest test;

	// Constant for Database URL
	/*
	 * public static String DB_URL = "jdbc:mysql://localhost:3306/orangehrm"; //
	 * Constant for Database Username public static String DB_USER = "root"; //
	 * Constant for Database Password public static String DB_PASSWORD = "root";
	 */

	public ArrayList<String> ConnectMySQLDatabase(String DB_URL, String DB_USER, String DB_PASSWORD)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// String[][] arrayDBData = null;
		// Make the database connection
		String dbClass = "com.mysql.jdbc.Driver";
		// String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		Class.forName(dbClass);
		// Get connection to DB
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		// Statement object to send the SQL statement to the Database
		stmt = con.createStatement();
		String query = "select uname,upass from login";
		// Get the contents of userinfo table from DB
		ResultSet res = stmt.executeQuery(query);
		// Print the result untill all the records are printed
		// res.next() returns true if there is any next record else returns
		// false
		ArrayList<String> sqlData = new ArrayList<String>();
		while (res.next()) {
			System.out.print("\t" + res.getString("uname"));
			System.out.println("\t" + res.getString("upass"));
			sqlData.add(res.getString("uname") + "~" + res.getString("upass"));
			// Adminadmin123
		}
		// Close DB connection
		if (con != null) {
			con.close();
		}
		return sqlData;
	}

	// To Capture Failure Screenshot

	public static String getScreenshotfailure(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = Relativepath_failure + "//" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	// To Capture Success Screenshot
	public static String getScreenshotSuccess(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = Relativepath_success + "//" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
	//To Create a report
	public static void lauchReport() {
		// initialize the HtmlReporter
    	//htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/ExtentReport.html");
        htmlReporter = new ExtentSparkReporter("E:\\Selenium Training\\Workspace\\SeleniumTrainingDemo\\test-output\\ExtentReport.html");
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //To add system or environment info by using the setSystemInfo method.
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome Latest");
        extent.setSystemInfo("QA Name", "Dinesh");
 
        //configuration items to change the look and feel
        //add content, manage tests etc
        //htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("OrangeHRM - Extent Report");
        htmlReporter.config().setReportName("Smoke Test Report");
        //htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	//To Generate Extent Report
	public static void ExtentReport(ITestResult result) throws Exception {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
            String screenshotPath = getScreenshotfailure(ExtentReport_OrangeHRM.driver, result.getName());
            //To add it in the extent report 
            test.addScreenCaptureFromPath(screenshotPath);//This is for Screenshot
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
            String screenshotPath = getScreenshotSuccess(ExtentReport_OrangeHRM.driver, result.getName());
            //To add it in the extent report 
            test.addScreenCaptureFromPath(screenshotPath);//This is for Screenshot
        }
	}
}
