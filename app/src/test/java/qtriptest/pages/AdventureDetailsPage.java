package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {

    WebDriver driver;
    
    public AdventureDetailsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }


    @FindBy(xpath = "//input[@name='name']")
    WebElement nameTxtField;
    @FindBy(xpath = "//input[@name='date']")
    WebElement datePicker;
    @FindBy(xpath = "//input[@name='person']")
    WebElement personCount;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement reserveButton;
    @FindBy(xpath = "//div[@id='reserved-banner']")
    WebElement confirmationMessage;
    @FindBy(xpath = "//div[@id='reservation-panel-available']")
    WebElement reservationForm;
    @FindBy(xpath="//a[normalize-space()='Reservations']")
    WebElement reserveBtton;


    
    public boolean adventureReservationForm(){
        if(reservationForm.isDisplayed()){
            return true;
        }
        return false;
    }

    public boolean reservationStatus(){
        if(confirmationMessage.isDisplayed()){
            return true;
        }
        return false;
    }

    public boolean enterCredentials(String name, String date, String count) throws InterruptedException{
      nameTxtField.clear();
      Thread.sleep(2000);
      nameTxtField.sendKeys(name);
      datePicker.clear();
      Thread.sleep(2000);
      datePicker.sendKeys(date);

    
      personCount.clear();
      Thread.sleep(2000);
      personCount.sendKeys(count);
      reserveButton.click();
      return adventureReservationForm();
    }

    public boolean navigateToHistoryPage() throws InterruptedException{
        boolean status = false;
        reserveBtton.click();
        Thread.sleep(3000);
        if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/index.html")){
            status = true;
        }
        return status;
    }



    









}