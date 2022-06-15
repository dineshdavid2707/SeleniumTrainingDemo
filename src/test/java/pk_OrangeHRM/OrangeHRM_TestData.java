package pk_OrangeHRM;

import java.io.IOException;
import java.util.IllegalFormatException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class OrangeHRM_TestData {

	 @DataProvider(name = "Login")
	    public Object[][] getDataforLogin() {
	        // Multidimensional Object
	        // 3X3 or 4X3 or 4X5
	        return new Object[][] {	
	                {"Admin", "admin123" },
	                {"raman", "admin123" },
	                {"dixit", "admin123" },
	                {"dinesh", "admin123"}
	                };
	    }
	 
	 @DataProvider(name = "LoginScenario")
	 public Object[][] getDataforLoginDifferentScenarios() {
	        return new Object[][] { 
	                { "admin", "", "Password cannot be empty"},
	                { "", "admin123", "Username cannot be empty" }, 
	                { "admin", "admin123", "Dashboard" },
	                { "AdminWrong", "admin123", "Invalid credentials" },
	                { "admin", "admin", "Invalid credentials" } 
	                };
	    }
	 @DataProvider(name = "AddUser")
	 public Object[][] getDataforAddUser() {
	        return new Object[][] { 
	                { "", "", "","","Employee does not exist"},
	                { "", "Newgen T","admin123","admin123","Employee does not exist"}, 
	                { "Fiona Grace", "","admin123","admin123", "Required" },
	                { "Fiona Grace", "Newgen T","","", "Required Password" },
	                { "Fiona Grace", "Newgen T","admin123","", "Passwords Empty" } ,
	                { "Fiona Grace", "Newgen T","admin123","qwert123", "Different Passwords" },
	                { "Fiona Grace", "Newgen T","admin123","admin123", "Username"}};
	 
	    }
	 @DataProvider(name = "LoginExcelData")
	 public Object[][] GetDataFromExcel() throws EncryptedDocumentException, IllegalFormatException, IOException {
		 ReadExcel excel = new ReadExcel();
		// String RelativePath = System.getProperty("user.dir");
		 Object[][] testObjArray = excel.getExcelData("E:\\Selenium Training\\Workspace\\SeleniumTrainingDemo\\OrangeHRMData.xlsx","UserLogin");
		 System.out.println(testObjArray);
		 return testObjArray;
	       // return new Object[][] {};
	 
	    }
}
