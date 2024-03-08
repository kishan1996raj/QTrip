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
import org.testng.asserts.SoftAssert;

public class testCase_01 extends BaseTest {
    
    public static String lastUsername="";
   

    @Test(description = "verifying functionality of login and register page", enabled = true, priority = 1,groups = {"Login Flow"}, dataProvider = "data-provider" , dataProviderClass = DP.class )
      public static void TestCase01(String username, String password) throws InterruptedException, MalformedURLException{
          SoftAssert sf = new SoftAssert();
            RegisterPage register = new RegisterPage(driver);
            sf.assertTrue(register.navigateToRegisterPage());
            Thread.sleep(5000);
            sf.assertTrue(register.userRegistration(username,password, true));
            lastUsername = register.lastGeneratedEmailAddress;
            LoginPage login = new LoginPage(driver);
            sf.assertTrue(login.navigateToLoginPage());
            Thread.sleep(5000);
            sf.assertTrue(login.userLogin(lastUsername, password));
            sf.assertTrue(login.userLogout());
            sf.assertAll();
        
    }
   
}
