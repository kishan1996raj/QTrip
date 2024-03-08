package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.reportUtils.Extent_TestManager;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import javax.xml.crypto.MarshalException;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    static RemoteWebDriver driver;
   
    @BeforeSuite(alwaysRun = true)
    public void createDriver() throws MalformedURLException{
        DriverSingleton sbc1 = DriverSingleton.createDriverInsatance();
        driver = sbc1.getDriver();
    }


    @BeforeMethod
    public void startTest(Method m){
        Extent_TestManager.startTest(m.getName());
    }

    @AfterMethod
    public void afterTestMethod(ITestResult result) throws IOException{
        if(result.getStatus()==ITestResult.SUCCESS){
            Extent_TestManager.testLogger(LogStatus.PASS,driver, "Test passed");
        }else if(result.getStatus()==ITestResult.FAILURE){
            Extent_TestManager.testLogger(LogStatus.FAIL,driver, result.getThrowable().getMessage());
        }else{
            Extent_TestManager.testLogger(LogStatus.SKIP, driver, "Test Skipped");
        }

        Extent_TestManager.endTest();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        driver.close();
		driver.quit();
        ReportSingleton.getReport().flush();
    }

    
}
