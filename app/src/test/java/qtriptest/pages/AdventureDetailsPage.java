package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdventureDetailsPage {

    WebDriver driver;
    
    public AdventureDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='name']")WebElement nameTxtField;
}