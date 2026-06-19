package testbases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {
    public WebDriver driver;
    public Logger logger;

    @BeforeClass
    public void setUp() {
        logger = LogManager.getLogger(this.getClass());
        logger.info("Ths is starting");
        driver = new ChromeDriver();
        driver.get("https://sync-inv2.vercel.app/");
        driver.manage().window().maximize();

    }

//    @AfterClass
//    public void tearDown() {
//        driver.quit();
//    }

    public String captureScreenshot(String tname) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        String tfp = System.getProperty("user.dir")+"\\screenshots\\"+timeStamp+".png";
        File targetFile = new File(tfp);
        screenshot.renameTo(targetFile);
        return tfp;
    }
}
