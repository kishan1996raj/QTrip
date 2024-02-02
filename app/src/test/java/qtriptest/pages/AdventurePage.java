package qtriptest.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {

    WebDriver driver;

    public AdventurePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//select[@id = 'duration-select']")
    WebElement filterDropdown;
    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center'][1]/div")
    WebElement clearFilter;
    @FindBy(xpath = "//select[@id = 'category-select']")
    WebElement categoryDropdown;
    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center'][2]/div")
    WebElement clearCategory;
    @FindBy(xpath = "//input[@id='search-adventures']")
    WebElement searchadventure;
    @FindBy(xpath = "//div[@class='filter-bar-tile d-flex align-items-center'][3]/div")
    WebElement clearAdventure;
    @FindBy(xpath = "//div[@class='col-6 col-lg-3 mb-4']")
    WebElement adventureParent;

    public void searchFilter(int i) {
        Select sc = new Select(filterDropdown);
        sc.selectByIndex(i);
    }

    public void searchCategory(int i) {
        Select sc = new Select(categoryDropdown);
        sc.selectByIndex(i);
    }

    public void searchadvetureI(String text) {
        searchadventure.sendKeys(text);
    }
   
    public void clearFilter(){
       clearFilter.click();
    }

    public void clearCategory(){
        clearCategory.click();
    }

    public void clearAdventure(){
        clearAdventure.click();
    }

    public boolean selectYourAdventure(String text){
         WebElement adventure =adventureParent.findElement(By.xpath("//a/div/div/div/h5[text()='"+text+"']"));
         adventure.click();
         if(driver.getCurrentUrl().contains("?adventure")){
            return true;
         }
         return false;
    }


}
