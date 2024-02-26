
package qtriptest.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    RemoteWebDriver driver;
    public int index;

    public HistoryPage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
@FindBy(xpath = "//div[@class='nav-link login register']")
WebElement Logout;
   

    public List<List<String>> getTableDetails(RemoteWebDriver driver) {
        List<WebElement> tableRows = driver.findElements(By.xpath("//table/tbody/tr"));
        List<List<String>> tableData = new ArrayList<>();
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String th = row.findElement(By.tagName("th")).getText();
            
            List<String> rowData = new ArrayList<>();
            rowData.add(th);
            for (WebElement cell : cells) {
                // Extract text from each cell and add it to rowData list
                rowData.add(cell.getText());
            }
            // Add the rowData list to tableData list
            tableData.add(rowData);
        }
        return tableData;
    }

    public boolean verifyBookingDetails(String adventure, String guestName, String date, String count) {
        List<List<String>> tableData = getTableDetails(driver);
        boolean result1 = false;
       
            List<String> elementsToCheck = new ArrayList<>();
            elementsToCheck.add(adventure);
            elementsToCheck.add(guestName);
            elementsToCheck.add(date);
            elementsToCheck.add(count);
           int count1 = 0; 
           for(List<String> td:tableData){
           
            boolean found = true;
          
              
                if (!td.contains(adventure)) {
                    System.out.println("Input not found in table data.");
                    found = false;
                
                    continue;
                }
            count1++;
            if(found){
                for(String result: td){
                    System.out.print(result+ " | ");
                }
                WebElement transaction = driver.findElement(By.xpath("//table/tbody/tr["+count1+"]/th"));
                if(transaction.isDisplayed()){
                    System.out.println(" ");
                   System.out.println("transaction id found");
                   System.out.println(transaction.getText());
                }
                return true;
            }
        

    }
    return result1;
    }

    public boolean cancelCurrentBooking(String adventure, String guestName, String date, String count,
            int index) throws InterruptedException {
               
                List<List<String>> tableData = getTableDetails(driver);
                boolean result1 = false;
                int count1 = 0;
                for(List<String> td: tableData){
                boolean found = true;
                
              
                    if (!td.contains(adventure)) {
                        System.out.println("Input not found in table data.");
                        found = false;
                        continue;
                    }
            
                count1++;
                if(found){
                     WebElement cancel = driver.findElement(By.xpath("//table/tbody/tr["+count1+"]/td[8]"));
                     if(cancel.isDisplayed()){
                        System.out.println("found cancel button");
                        cancel.click();
                       Thread.sleep(2000);
                     }
                    return true;
                }
            
    
        }
        return result1;


    }

    public void refreshHistory() throws InterruptedException{
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/adventures/reservations/");
        Thread.sleep(2000);
    }

    public void logout(){
       Logout.click();
    }
}


