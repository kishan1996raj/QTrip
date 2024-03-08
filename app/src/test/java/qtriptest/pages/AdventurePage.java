

package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {

    RemoteWebDriver driver;

    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    
    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center'][1]/div")
    WebElement clearFilter;
    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center'][2]/div")
    WebElement clearCategory;
    @FindBy(xpath = "//input[@id='search-adventures']")
    WebElement searchadventure;
    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center'][3]/div")
    WebElement clearAdventure;
    @FindBy(xpath = "//div[@class='activity-card-text text-md-center w-100 mt-3']")
    List<WebElement> adventures;
    @FindBy(xpath = "//div[@class='category-banner']")
    List<WebElement> categoryBanner;
    @FindBy(xpath = "//input[@id='search-adventures']")
    WebElement searchAdventure;



    public void searchFilter(String i) throws InterruptedException {
        By by = By.xpath("//select[@id = 'duration-select']");
        WebElement filterDropdown = SeleniumWrapper.findElement(driver, by, 3);
        Select sc = new Select(filterDropdown);
        sc.selectByVisibleText(i);
        Thread.sleep(3000);

    }

    public void searchCategory(String i) throws InterruptedException {
        By by = By.xpath("//select[@id = 'category-select']");
        WebElement categoryDropdown = SeleniumWrapper.findElement(driver, by, 3);
        Select sc = new Select(categoryDropdown);
        sc.selectByVisibleText(i);
        Thread.sleep(3000);
    }

    public void searchadvetureI(String text) throws InterruptedException {
        SeleniumWrapper.sendKeys(searchadventure, driver, text);
       
    }

    public void clearFilter() {
        SeleniumWrapper.click(clearFilter, driver);
    }

    public void clearCategory() {
        SeleniumWrapper.click( clearCategory, driver);
    }

    public void clearAdventure() {
        SeleniumWrapper.click( clearAdventure, driver);
    }

    public boolean searchAdventure(String text) throws InterruptedException{
        SeleniumWrapper.sendKeys(searchAdventure, driver, text);
        Thread.sleep(2000);
        By by = By.xpath("//h5[normalize-space()='"+text+"']");
        WebElement result = SeleniumWrapper.findElement(driver,by, 3);
        Thread.sleep(3000);
       if(result.getText().equalsIgnoreCase(text)){
           SeleniumWrapper.click(result, driver);
        Thread.sleep(2000);
           return true;
       }
       return false;
    }


    public boolean selectYourAdventure(String text) {
        for (WebElement adventurePArent : adventures) {
            String text1 = adventurePArent
                    .findElement(By.xpath("//a/div/div/div/h5[text()='" + text + "']")).getText();
            if (text1.equalsIgnoreCase(text)) {
                SeleniumWrapper.click(adventurePArent, driver);
            }
        }
        if (driver.getCurrentUrl().contains("?adventure")){
            return true;
        }
        return false;
    }

    public int checkCountOfFilteredAdventure() {
        // adventures = new ArrayList<WebElement>();
        int count = 0;
        // for(WebElement adv: adventures){
        //   if(adv.isDisplayed())
        //   count++;
        // }
        count = adventures.size();
      
        System.out.println("count of filterd adventures :"+count);
        return count;
    }

    public int[] getTimeDuration(String s) {
        String[] string_hour = s.split(" ");;
        String int_hour = string_hour[0];
        String[] a = int_hour.split("-");
        int[] num = new int[2];
        for (int i = 0; i < a.length; i++) {
            num[i] = Integer.parseInt(a[i]);
        }
        return num;
    }

    public boolean checkTimeIntervalOfAdventure(String hours) {

        int[] durationarr = getTimeDuration(hours);
        int minTime = durationarr[0];
        System.out.println("Minimum duration of time: "+minTime);
        int maxTime = durationarr[1];
        System.out.println("Maximum duration of time: "+maxTime);
       
        List<Integer> time = new ArrayList<Integer>();
       
        for (WebElement adv : adventures) {
            WebElement advtime = adv.findElement(By.xpath(
                    ".//div[@class='d-block d-md-flex justify-content-between flex-wrap pl-3 pr-3'][2]/p"));
            String duration = advtime.getText();
            int dur = Integer.parseInt(duration.replaceAll("[^0-9]", ""));
            System.out.println("Duration for the adventure is :"+dur);

            if(!(minTime <= dur && maxTime >= dur)){
                return false;
            }
          
        }
        return true;
    }

    public boolean checkCategoryBanner(String category) {
        String categoryText = "";
        for (WebElement ctgr : categoryBanner) {
            categoryText = ctgr.getText();
            if (!categoryText.equalsIgnoreCase(category)) {
                return false;
            }
        }
        return true;
    }


}
