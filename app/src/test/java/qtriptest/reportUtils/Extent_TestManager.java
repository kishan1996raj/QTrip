package qtriptest.reportUtils;

import qtriptest.ReportSingleton;
import java.io.File;
import java.io.IOException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Extent_TestManager {
    static ExtentReports report = ReportSingleton.getReport();
    static ExtentTest test;

    public static String capture(RemoteWebDriver driver) throws IOException{

        File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(System.getProperty("user.dir")+"/QtripSnapShot/"+System.currentTimeMillis()+".png");
        String errlpath = destFile.getAbsolutePath();
        FileUtils.copyFile(srcFile, destFile);
        return errlpath;

    }
   

    public static synchronized ExtentTest startTest(String testName){
        test = report.startTest(testName);
        return test;
    }

    public static synchronized void testLogger(LogStatus status,RemoteWebDriver driver, String description) throws IOException{
         test.log(status, test.addScreenCapture(capture(driver))+description);
    }

    public static synchronized void endTest(){
        report.endTest(test);
    }
    
}
