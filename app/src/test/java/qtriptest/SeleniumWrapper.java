package qtriptest;

import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumWrapper {

    public static boolean click(WebElement element, RemoteWebDriver driver) {
        try {
            if (element.isDisplayed()) {
                scrolIntoView(element, driver);
                element.click();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("An exception occured " + e.getMessage());
            return false;

        }
    }

    public static void scrolIntoView(WebElement element, RemoteWebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static boolean sendKeys(WebElement element,RemoteWebDriver driver, String text)
            throws InterruptedException {
        try {
            if (element.isDisplayed()) {
                element.clear();
                Thread.sleep(1000);
                element.sendKeys(text);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("An exception occured " + e.getMessage());
            return false;
        }
    }

    public static boolean navigate(String url, RemoteWebDriver driver) {
        try {
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
                if(driver.getCurrentUrl().equals(url)) {
                    return true;
                } else {
                    return false; 
                }
            } else {
                System.out.println("Already navigated to the target URL");
                return true; 
            }
        } catch (Exception e) {
            System.out.println("An exception occurred: " + e.getMessage());
            return false;
        }
    }

    public static WebElement findElement(RemoteWebDriver driver, By locator, int retrycount) {
        int count = 0;
        WebElement element = null;
        while (count < retrycount) {
            try {
                element = driver.findElement(locator);
                if (element != null && element.isDisplayed()) {
                    return element;
                }
            } catch (NoSuchElementException e) {
                // Log or handle the specific case of element not found
                System.out.println("Element not found: " + locator);
            } catch (Exception e) {
                // Log or handle other exceptions
                System.out.println("An exception occurred: " + e.getMessage());
            }
            count++;
        }
        // If element is still null after all retry attempts, it means it wasn't found
        return null;

    }



}

