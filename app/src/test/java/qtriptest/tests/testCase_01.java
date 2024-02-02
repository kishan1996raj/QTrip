package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_01 {

    static RemoteWebDriver driver;
    public static String lastUsername="";
    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException{
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),capabilities);
        System.out.println("CreateDriver()");
        driver.manage().window().maximize();
    }

    @Test(description = "verifyiung functionality of login and register page", enabled = true, dataProvider = "data-provider" , dataProviderClass = DP.class )
      public static void TestCase01(String username, String password) throws InterruptedException{
     
            RegisterPage register = new RegisterPage(driver);
            Assert.assertTrue(register.navigateToRegisterPage());
            Thread.sleep(5000);
            Assert.assertTrue(register.userRegistration(username,password, true));
            lastUsername = register.lastGeneratedEmailAddress;
            LoginPage login = new LoginPage(driver);
            Assert.assertTrue(login.navigateToLoginPage());
            Thread.sleep(5000);
            Assert.assertTrue(login.userLogin(lastUsername, password));
            Assert.assertTrue(login.userLogout());
        
    }
    @AfterSuite()
    public static void closeBrowser(){
        driver.quit();
    }
}
