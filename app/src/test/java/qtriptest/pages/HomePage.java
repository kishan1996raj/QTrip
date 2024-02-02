package qtriptest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
       
    }

    @FindBy(id = "autocomplete")
    WebElement searchBox;
    @FindBy(xpath = "//a[text()='Reservations']")
    WebElement reservationButton;
    @FindBy(xpath = "//a[text()='Home']")
    WebElement homeButton;
    @FindBy(xpath = "//*[@id='results']/h5[text()='No City found']")
    WebElement noCityautoComplete;

    public boolean searchBoxInput(String city) {

        boolean status = false;
        searchBox.sendKeys(city);
        WebElement autocompleteElement =
                driver.findElement(By.xpath("//a/li[@id=" + city + " or text()=' " + city + " ']"));
        if (autocompleteElement.isDisplayed()) {
            autocompleteElement.click();
            if (driver.getCurrentUrl().equals(
                    "https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/?city=" + city)) {
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
