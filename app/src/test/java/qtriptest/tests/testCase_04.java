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

public class testCase_04 {
    static RemoteWebDriver driver;
    public static int index;
    public static String lastUsername = "";

    @BeforeSuite(alwaysRun = true)
    public static void driver() throws MalformedURLException {
        DriverSingleton sbc1 = DriverSingleton.createDriverInsatance();
        driver = sbc1.getDriver();
    }

    @Test(description = "Booking History flow", enabled = true, priority = 4, groups= {"Reliability Flow"},dataProvider = "data-provider",
            dataProviderClass = DP.class)
    public static void TestCase04(String username, String password, String dataset1,
            String dataset2, String dataset3) throws InterruptedException {
        SoftAssert sf = new SoftAssert();

        RegisterPage register = new RegisterPage(driver);

        register.navigateToRegisterPage();
        Thread.sleep(5000);
        sf.assertTrue(register.userRegistration(username, password, true));
        lastUsername = register.lastGeneratedEmailAddress;
        LoginPage login = new LoginPage(driver);
        sf.assertTrue(login.navigateToLoginPage());
        Thread.sleep(5000);
        sf.assertTrue(login.userLogin(lastUsername, password));
        String[] data1 = dataset1.split(";");
        String city1 = data1[0];
        String adventure1 = data1[1];
        String guest1 = data1[2];
        String date1 = data1[3];
        String count1 = data1[4];
        HomePage homepage = new HomePage(driver);
        sf.assertTrue(homepage.navigateToHomePage(), "Navigation to home page failed");
        sf.assertTrue(homepage.searchBoxInput(city1), "Operation for searching city failed");
        AdventurePage adv = new AdventurePage(driver);
        adv.searchAdventure(adventure1);
        AdventureDetailsPage advdet = new AdventureDetailsPage(driver);
        advdet.enterCredentials(guest1, date1, count1);
        Thread.sleep(3000);
        String[] data2 = dataset2.split(";");
        String city2 = data2[0];
        String adventure2 = data2[1];
        String guest2 = data2[2];
        String date2 = data2[3];
        String count2 = data2[4];
        sf.assertTrue(homepage.navigateToHomePage(), "Navigation to home page failed");
        Thread.sleep(3000);
        sf.assertTrue(homepage.searchBoxInput(city2), "Operation for searching city failed");
        Thread.sleep(3000);
        adv.searchAdventure(adventure2);
        Thread.sleep(3000);
        advdet.enterCredentials(guest2, date2, count2);
        Thread.sleep(3000);
        String[] data3 = dataset3.split(";");
        String city3 = data3[0];
        String adventure3 = data3[1];
        String guest3 = data3[2];
        String date3 = data3[3];
        String count3 = data3[4];
        sf.assertTrue(homepage.navigateToHomePage(), "Navigation to home page failed");
        Thread.sleep(3000);
        sf.assertTrue(homepage.searchBoxInput(city3), "Operation for searching city failed");
        Thread.sleep(3000);
        adv.searchAdventure(adventure3);
        Thread.sleep(3000);
        advdet.enterCredentials(guest3, date3, count3);
        Thread.sleep(3000);
        HistoryPage history = new HistoryPage(driver);
        sf.assertTrue(advdet.navigateToHistoryPage(), "Navigate to history page failed");
        Thread.sleep(3000);
        sf.assertTrue(history.verifyBookingDetails(adventure1, guest1, date1, count1),
                "Verifying boking details failed");
                Thread.sleep(3000);
        sf.assertTrue(history.verifyBookingDetails(adventure2, guest2, date2, count2),
                "Verifying boking details failed");
                Thread.sleep(3000);
        sf.assertTrue(history.verifyBookingDetails(adventure3, guest3, date3, count3),
                "Verifying boking details failed");
                Thread.sleep(3000);
        sf.assertAll();

    }

    @AfterSuite()
    public static void closeBrowser() {
        driver.quit();
    }

}
