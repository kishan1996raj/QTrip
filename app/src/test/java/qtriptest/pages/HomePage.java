package qtriptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    RemoteWebDriver driver;
    @FindBy(id = "autocomplete")
    WebElement searchBox;
    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservationButton;
    @FindBy(xpath = "//a[text()='Home']")
    WebElement homeButton;
    @FindBy(xpath = "//*[@id='results']/h5[text()='No City found']")
    WebElement noCityautoComplete;
  
 
    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
       
    }


    public boolean navigateToHomePage(){
        boolean status = false;
        String Url = "https://qtripdynamic-qa-frontend.vercel.app";
        if (driver.getCurrentUrl() != Url) {
            driver.get(Url);
            status = true;
        }
        return status;
    }

    public boolean searchBoxInput(String city) throws InterruptedException {
    
        boolean status = false;
        String cityL = city.toLowerCase();
        Thread.sleep(3000);
        searchBox.sendKeys(cityL);
        //add wait here
        Thread.sleep(3000);
        String city1 = city.substring(0,1).toUpperCase()+city.substring(1);
        WebDriverWait wait = new WebDriverWait(driver,20);
    WebElement autocompleteElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='results']/a[@id='"+cityL+"']")));      
        if (autocompleteElement.isDisplayed()) {
            autocompleteElement.click();
            Thread.sleep(10000);
            if (driver.getCurrentUrl().equals(
                    "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city="+cityL)) {
                status = true;
            }
            return status;
        } else if (noCityautoComplete.isDisplayed()) {
            System.out.println("No City found for the search" + city);
            return true;
        }
        return status;
    }


}
