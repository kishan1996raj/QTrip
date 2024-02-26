package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class testCase_02 {
   
    static RemoteWebDriver driver;
    public static String lastUsername="";
   

    @BeforeSuite(alwaysRun = true)
    public static void driver() throws MalformedURLException{
        DriverSingleton sbc1 = DriverSingleton.createDriverInsatance();
        driver = sbc1.getDriver();
    }

    @Test(description = "verifying Home page and Adventure page functionalities", enabled =true,priority = 2 ,groups= {"Search and Filter flow"}

    ,dataProvider = "data-provider", dataProviderClass = DP.class)public static void TestCase02(String CityName, String Category_Filter, String DurationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResult) throws InterruptedException{
        SoftAssert sf = new SoftAssert();
        HomePage homepage = new HomePage(driver);
        sf.assertTrue(homepage.navigateToHomePage(), "Navigation to home page failed");
        sf.assertTrue(homepage.searchBoxInput(CityName),"Operation for searching city failed");
        AdventurePage adventurePage = new AdventurePage(driver);
        adventurePage.searchFilter(DurationFilter);
        Thread.sleep(5000);
        sf.assertTrue(adventurePage.checkTimeIntervalOfAdventure(DurationFilter),"CheckTimeIntervalOfAdventure functionality failed");
       
        adventurePage.searchCategory(Category_Filter);
        Thread.sleep(5000);

        sf.assertEquals(adventurePage.checkCountOfFilteredAdventure(),Integer.parseInt(ExpectedFilteredResults),"Expected filterd results doesn't matches with actual filterd result");
        adventurePage.clearFilter();
        adventurePage.clearCategory();
        Thread.sleep(5000);
        sf.assertEquals(adventurePage.checkCountOfFilteredAdventure(),Integer.parseInt(ExpectedUnFilteredResult),"Expected filterd results doesn't matches with actual filterd result");
        sf.assertAll();
 }


}
