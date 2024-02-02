package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
    RemoteWebDriver driver;
    public String lastGeneratedEmailAddress="";
    @FindBy(name = "email")public WebElement emailAddressTxtBox;
    @FindBy(name = "password")public WebElement passwordTxtBox;
    @FindBy(name = "confirmpassword" )public WebElement confirmPasswordTxtBox;
    @FindBy(xpath = "//button[text()='Register Now']")public WebElement registerButton;
    
    public RegisterPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
       }
    

    
    public boolean navigateToRegisterPage() {
        boolean status = false;
        String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/register/";
        if (driver.getCurrentUrl() != Url) {
            driver.get(Url);
            status = true;
        }
        return status;
    }

    public boolean userRegistration(String emailaddress, String password, boolean makeUserDynamic)
            throws InterruptedException {
        boolean status = false;
        String test_emailaddress="";
        if (makeUserDynamic) {
          test_emailaddress = UUID.randomUUID().toString() + emailaddress;
            System.out.println(test_emailaddress);
        }
        else 
        test_emailaddress = emailaddress;
        
        emailAddressTxtBox.sendKeys(test_emailaddress);
        passwordTxtBox.sendKeys(password);
        confirmPasswordTxtBox.sendKeys(password);

        registerButton.click();
        Thread.sleep(2000);
        if (driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/pages/login") ) {
            status = true;
        }
       this.lastGeneratedEmailAddress = test_emailaddress;
        return status;
    }
}
