package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage {
    RemoteWebDriver driver;
    @FindBy(id = "floatingInput")
    public WebElement emailTxtBox;
    @FindBy(id = "floatingPassword")
    WebElement passwordTxtBox;
    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement loginButton;
    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutButton;

    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20),this);
        }

    public boolean navigateToLoginPage() {
        boolean status = false;
        String Url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login";
        status =  SeleniumWrapper.navigate(Url, driver);
        return status;
    }

    public boolean userLogin(String email, String password) throws InterruptedException {
        boolean status = false;
        SeleniumWrapper.sendKeys(emailTxtBox, driver, email);
        SeleniumWrapper.sendKeys(passwordTxtBox, driver, password);
        Thread.sleep(3000);
        SeleniumWrapper.click(loginButton, driver);
        if(logoutButton.isDisplayed()) {
            status = true;
        }
        return status;
    }

    public boolean  userLogout(){
        boolean status = false;
        SeleniumWrapper.click(logoutButton, driver);
        if(driver.getCurrentUrl().equals("https://qtripdynamic-qa-frontend.vercel.app/")){
          status = true;
        }
        return status;
    }
}
