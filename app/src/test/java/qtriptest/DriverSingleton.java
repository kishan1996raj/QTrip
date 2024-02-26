package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

public class DriverSingleton {

    private static DriverSingleton instance = null;
    private static RemoteWebDriver driver;

    private DriverSingleton() throws MalformedURLException{
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"),capabilities);
        System.out.println("CreateDriver()");
        driver.manage().window().maximize();
    }
    public RemoteWebDriver getDriver(){
        return driver;
    }

    public static DriverSingleton createDriverInsatance() throws MalformedURLException{
        if(instance==null){
            instance = new DriverSingleton();
        }
        return instance;
    }

}