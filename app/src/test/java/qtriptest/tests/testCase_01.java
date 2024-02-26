package qtriptest.tests;
import qtriptest.DriverSingleton;
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
    public static void driver() throws MalformedURLException{
        DriverSingleton sbc1 = DriverSingleton.createDriverInsatance();
        driver = sbc1.getDriver();
    }
   

    @Test(description = "verifying functionality of login and register page", enabled = true, priority = 1,groups = {"Login Flow"}, dataProvider = "data-provider" , dataProviderClass = DP.class )
      public static void TestCase01(String username, String password) throws InterruptedException, MalformedURLException{
           
            RegisterPage register = new RegisterPage(driver);
             Assert.assertTrue(register.navigateToRegisterPage());
            register.navigateToRegisterPage();
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
