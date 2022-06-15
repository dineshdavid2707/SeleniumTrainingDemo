package pk_OrangeHRM;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PDFFile_ReadData {
	ChromeDriver driver;
    //String strURL="file:///D:/F%20Drive/EItin_DIXIT_BLR-AUH_TLWMAW43.pdf";
	String strURL="file:///C:/Users/Administrator/Documents/CMC/Vinitha CMC Application.pdf";
    //Launching PDF file from your local system
    @Test(priority=1,enabled=false)
    public void testVerifyPDFTextInBrowser() throws IOException {
 
        URL url = new URL(strURL);
        InputStream is= url.openStream();
        BufferedInputStream file = new BufferedInputStream(is);
        PDDocument document =null;
        document=PDDocument.load(file);
        String pdfContent = new PDFTextStripper().getText(document);
        System.out.println(pdfContent);
        Assert.assertTrue(pdfContent.contains("VINITHASVINI A"));

    }
    //Launching PDF in browser and verifying
    @Test(priority=2,enabled=true)
    public void testVerifyPDFTextUsing_Get() throws IOException {	
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("C:/Users/Administrator/Documents/CMC/Vinitha CMC Application.pdf");
        String CurrentURL=driver.getCurrentUrl();
        URL url = new URL(CurrentURL);
        InputStream is= url.openStream();
        BufferedInputStream file = new BufferedInputStream(is);
        PDDocument document =null;
        document=PDDocument.load(file);
        String pdfContent = new PDFTextStripper().getText(document);
        System.out.println(pdfContent);
        Assert.assertTrue(pdfContent.contains("SEMI-SKILLED WORK"));

    }
// Note: We can't read or parse pdf which are scanned, as its treated as image

    @AfterTest
    public void tearDown() throws InterruptedException {

        driver.quit();
 
    }
}
