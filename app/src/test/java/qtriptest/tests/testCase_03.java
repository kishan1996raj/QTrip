package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_03 {
    static RemoteWebDriver driver;
    public static int index;
    public static String lastUsername="";
   
    @BeforeSuite(alwaysRun = true)
    public static void driver() throws MalformedURLException{
        DriverSingleton sbc1 = DriverSingleton.createDriverInsatance();
        driver = sbc1.getDriver();
    }

    @Test(description = "Booking and Cancellation flow",enabled = true,priority = 3,groups= {"Booking and Cancellation Flow"}, dataProvider =  "data-provider", dataProviderClass = DP.class)
    public static void TestCase03(String userName, String passWord, String city, String adventure, String guest, String date, String count) throws InterruptedException{
     SoftAssert sf = new SoftAssert();
 
     RegisterPage register = new RegisterPage(driver);
 
     register.navigateToRegisterPage();
     Thread.sleep(5000);
     sf.assertTrue(register.userRegistration(userName,passWord, true));
     lastUsername = register.lastGeneratedEmailAddress;
     LoginPage login = new LoginPage(driver);
     sf.assertTrue(login.navigateToLoginPage());
     Thread.sleep(5000);
     sf.assertTrue(login.userLogin(lastUsername, passWord));
     HomePage homepage = new HomePage(driver);
     sf.assertTrue(homepage.navigateToHomePage(), "Navigation to home page failed");
     sf.assertTrue(homepage.searchBoxInput(city),"Operation for searching city failed");
     AdventurePage adv = new AdventurePage(driver);
     adv.searchAdventure(adventure);
     AdventureDetailsPage advdet = new AdventureDetailsPage(driver);
     advdet.enterCredentials(guest, date, count);
     Thread.sleep(3000);
     HistoryPage history = new HistoryPage(driver);
     sf.assertTrue(advdet.navigateToHistoryPage(),"Navigate to history page failed"); 
     Thread.sleep(3000);
     sf.assertTrue( history.verifyBookingDetails(adventure, guest, date, count),"Verifying boking details failed");;
     index = history.index;
     sf.assertTrue(history.cancelCurrentBooking(adventure, guest, date, count, index),"cancelling current Booking failed");
     history.refreshHistory();
     history.logout();
     sf.assertAll();

    }
    @AfterSuite()
    public static void closeBrowser(){
        driver.quit();
    }
}

    

